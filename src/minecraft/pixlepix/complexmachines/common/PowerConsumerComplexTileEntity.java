package pixlepix.complexmachines.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import pixlepix.complexmachines.common.tileentity.FillerMachineTileEntity;

import com.google.common.io.ByteArrayDataInput;

import mekanism.api.IStrictEnergyAcceptor;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.core.block.IElectricityStorage;
import universalelectricity.core.electricity.ElectricityNetworkHelper;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.electricity.IElectricityNetwork;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import universalelectricity.prefab.network.IPacketReceiver;

public class PowerConsumerComplexTileEntity extends BasicComplexTileEntity implements IPacketReceiver, IElectricityStorage, IStrictEnergyAcceptor {

	public final double WATTS_PER_TICK = 5000;
	public final double TRANSFER_LIMIT = 12500;
	private double joulesStored = 0;
	public static double maxJoules = 2000000;
	public int ticks = 0;

	private int playersUsing = 0;
	public int orientation;

	@Override
	public void initiate() {
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (!this.worldObj.isRemote) {

			ForgeDirection inputDirection = ForgeDirection.getOrientation(this
					.getBlockMetadata() + 2);
			TileEntity inputTile = VectorHelper.getTileEntityFromSide(
					this.worldObj, new Vector3(this), inputDirection);

			IElectricityNetwork inputNetwork = ElectricityNetworkHelper
					.getNetworkFromTileEntity(inputTile,
							inputDirection.getOpposite());

			if (inputNetwork != null) {
				if (this.joulesStored < getMaxJoules()) {
					inputNetwork.startRequesting(this,Math.min(this.getMaxJoules() - this.getJoules(),this.TRANSFER_LIMIT) / this.getVoltage(),this.getVoltage());
					ElectricityPack electricityPack = inputNetwork.consumeElectricity(this);
					this.setJoules(this.joulesStored+ electricityPack.getWatts());

					if (UniversalElectricity.isVoltageSensitive) {
						if (electricityPack.voltage > this.getVoltage()) {
							this.worldObj.createExplosion(null, this.xCoord,
									this.yCoord, this.zCoord, 2f, true);
						}
					}
				} else {
					inputNetwork.stopRequesting(this);
				}
				

			}
		}

		

		this.joulesStored = Math.min(this.joulesStored, this.getMaxJoules());
		this.joulesStored = Math.max(this.joulesStored, 0d);
	}

	@Override
	public void handlePacketData(INetworkManager inputNetwork, int type,
			Packet250CustomPayload packet, EntityPlayer player,
			ByteArrayDataInput dataStream) {
		try {
			this.joulesStored = dataStream.readDouble();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public double getVoltage() {
		return 480;
	}

	/**
	 * @return The amount of ticks required to draw this item
	 */

	

	@Override
	public double getJoules() {
		return this.joulesStored;
	}

	@Override
	public void setJoules(double joules) {
		this.joulesStored = joules;
	}

	@Override
	public double getMaxJoules() {
		return maxJoules;
	}

	@Override
	public boolean canConnect(ForgeDirection direction) {
		return direction.ordinal() == this.getBlockMetadata() + 2;
	}
	@Override
	public double getEnergy() {
		// TODO Auto-generated method stub
		return this.getJoules();
	}

	@Override
	public void setEnergy(double energy) {
		this.setJoules(energy);
		
	}

	@Override
	public double getMaxEnergy() {
		// TODO Auto-generated method stub
		return this.getMaxJoules();
	}

	@Override
	public double transferEnergyToAcceptor(double amount) {
		double energyTransfered=Math.max(getMaxEnergy()-this.getEnergy(),amount );
		this.setEnergy(this.getEnergy()+energyTransfered);
		return amount-energyTransfered;
	}

	@Override
	public boolean canReceiveEnergy(ForgeDirection side) {
		// TODO Auto-generated method stub
		return this.canConnect(side);
	}
	
}
