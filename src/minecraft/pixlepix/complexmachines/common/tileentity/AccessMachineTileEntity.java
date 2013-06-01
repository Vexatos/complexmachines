package pixlepix.complexmachines.common.tileentity;

import mekanism.api.IStrictEnergyAcceptor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.core.block.IElectricityStorage;
import universalelectricity.core.electricity.ElectricityNetworkHelper;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.electricity.IElectricityNetwork;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.tile.TileEntityElectricityRunnable;

import com.google.common.io.ByteArrayDataInput;

public class AccessMachineTileEntity extends TileEntityElectricityRunnable
		implements IPacketReceiver, IElectricityStorage, IStrictEnergyAcceptor {
	public final double WATTS_PER_TICK = 5000;
	public final double TRANSFER_LIMIT = 12500;
	private int drawingTicks = 0;
	private double joulesStored = 0;
	public boolean powered=false;
	public static double maxJoules = 2000000;
	public int ticks = 0;
	/**
	 * The ItemStacks that hold the items currently being used in the wire mill;
	 * 0 = battery; 1 = input; 2 = output;
	 */
	private ItemStack[] inventory = new ItemStack[3];

	private int playersUsing = 0;
	public int orientation;
	private int targetID = 0;
	private int targetMeta = 0;

	private boolean initialized;

	@Override
	public void initiate() {
		this.initialized = true;
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
				if (this.joulesStored < AccessMachineTileEntity.maxJoules) {
					inputNetwork.startRequesting(
							this,
							Math.min(this.getMaxJoules() - this.getJoules(),
									this.TRANSFER_LIMIT) / this.getVoltage(),
							this.getVoltage());
					ElectricityPack electricityPack = inputNetwork
							.consumeElectricity(this);
					this.setJoules(this.joulesStored
							+ electricityPack.getWatts());

					if (UniversalElectricity.isVoltageSensitive) {
						if (electricityPack.voltage > this.getVoltage()) {
							this.worldObj.createExplosion(null, this.xCoord,
									this.yCoord, this.zCoord, 2f, true);
						}
					}
				} else {
					inputNetwork.stopRequesting(this);
				}
				if (getJoules() > 10) {
					powered=true;
					setJoules(this.getJoules()-10);
				}else{
					powered=false;
				}

			}
		}

		if (!this.worldObj.isRemote) {
			if (this.ticks % 3 == 0 && this.playersUsing > 0) {
				
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
			this.drawingTicks = dataStream.readInt();
			this.disabledTicks = dataStream.readInt();
			this.joulesStored = dataStream.readDouble();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads a tile entity from NBT.
	 */

	/**
	 * Writes a tile entity to NBT.
	 */

	@Override
	public double getVoltage() {
		return 480;
	}

	/**
	 * @return The amount of ticks required to draw this item
	 */

	public int getDrawingTimeLeft() {
		return this.drawingTicks;
	}

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
		return AccessMachineTileEntity.maxJoules;
	}

	@Override
	public boolean canConnect(ForgeDirection direction) {
		return direction.ordinal() == this.getBlockMetadata() + 2;
	}
	@Override
	public double getEnergy() {
		return this.getJoules();
	}

	@Override
	public void setEnergy(double energy) {
		this.setJoules(energy);
		
	}

	@Override
	public double getMaxEnergy() {
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
		return this.canConnect(side);
	}

}