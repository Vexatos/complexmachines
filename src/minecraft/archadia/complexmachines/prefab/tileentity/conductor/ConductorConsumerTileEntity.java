package archadia.complexmachines.prefab.tileentity.conductor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.block.IElectrical;
import universalelectricity.core.block.IElectricalStorage;
import universalelectricity.prefab.network.IPacketReceiver;

import com.google.common.io.ByteArrayDataInput;

public abstract class ConductorConsumerTileEntity extends AdvancedConductorTileEntity implements IPacketReceiver,IElectrical, IElectricalStorage {

	public final double WATTS_PER_TICK = 5000;
	public final double TRANSFER_LIMIT = 12500;
	private double joulesStored = 0;
	public static double maxJoules = 2000000;
	public int ticks = 0;

	private int playersUsing = 0;
	public int orientation;
	
	@Override
	public float getMaxEnergyStored() {
		return getMaximumEnergy();
	}

	public int getMaximumEnergy(){
			return (int) this.getMaxJoules();
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
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
	public float getVoltage() {
		return 480;
	}

	public double getJoules() {
		return this.getEnergyStored();
	}
 
	public void setJoules(double joules) {
		this.setEnergyStored((float)joules);
	}

	public abstract double getMaxJoules();

	@Override
	public boolean canConnect(ForgeDirection direction) {
		return direction.ordinal() == this.getBlockMetadata() + 2;
	}
	
	@Override
	public float getRequest(ForgeDirection direction) {
		return 10000;
	}

	@Override
	public float getProvide(ForgeDirection direction) {
		return 0;
	}
	
}
