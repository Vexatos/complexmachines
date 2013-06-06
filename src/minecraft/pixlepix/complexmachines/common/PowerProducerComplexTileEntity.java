package pixlepix.complexmachines.common;

import mekanism.api.ICableOutputter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import pixlepix.complexmachines.common.tileentity.FillerMachineTileEntity;
import universalelectricity.core.block.IConductor;
import universalelectricity.core.block.IElectricityStorage;
import universalelectricity.core.electricity.ElectricityNetworkHelper;
import universalelectricity.core.electricity.IElectricityNetwork;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import universalelectricity.prefab.network.IPacketReceiver;

import com.google.common.io.ByteArrayDataInput;

public class PowerProducerComplexTileEntity extends BasicComplexTileEntity  implements IPacketReceiver, IElectricityStorage, ICableOutputter {

	
	
	
	
	
	public double electricOutput=0;
	
	private double joulesStored = 0;
	public static double maxJoules = 2000000;
	public int ticks = 10000;
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
	private IConductor connectedElectricUnit;

	@Override
	public void initiate() {
		this.initialized = true;
	}

	public void updateEntity() {
		super.updateEntity();
		
		if (!this.worldObj.isRemote) {
				ForgeDirection outputDirection = ForgeDirection.getOrientation(this.getBlockMetadata() + 2);
				TileEntity outputTile = VectorHelper.getConnectorFromSide(this.worldObj, new Vector3(this.xCoord, this.yCoord,this.zCoord), outputDirection);

				IElectricityNetwork network = ElectricityNetworkHelper.getNetworkFromTileEntity(outputTile, outputDirection);

				if (network != null) {
					if (network.getRequest().getWatts() > 0) {
						this.connectedElectricUnit = (IConductor) outputTile;
					} else {
						this.connectedElectricUnit = null;
					}
				} else {
					this.connectedElectricUnit = null;
				}


					if (this.connectedElectricUnit != null) {

						this.connectedElectricUnit.getNetwork().startProducing(
								this, (electricOutput / this.getVoltage()) / 20,
								this.getVoltage());

					}
				

			

		}
		
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
		return 120;
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
		return this.maxJoules;
	}

	@Override
	public boolean canConnect(ForgeDirection direction) {
		return direction.ordinal() == this.getBlockMetadata() + 2;
	}
	

	

	

	@Override
	public boolean canOutputTo(ForgeDirection side) {
		// TODO Auto-generated method stub
		return this.canConnect(side);
	}
	
	
	
	
	
}
