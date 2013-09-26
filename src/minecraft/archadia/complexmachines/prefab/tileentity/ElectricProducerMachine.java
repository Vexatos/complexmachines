package archadia.complexmachines.prefab.tileentity;

import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.block.IConductor;

/**
 * @author Archadia
 *
 */
public class ElectricProducerMachine extends ElectricContainer {
	
	public float electricOutput=0;
	
	private double joulesStored = 0;
	public static double maxJoules = 2000000;
	public int ticks = 10000;

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
	}
	
	@Override
	public float getVoltage() {
		return 120;
	}

	@Override
	public boolean canConnect(ForgeDirection direction) {
		return direction.ordinal() == this.getBlockMetadata() + 2;
	}
	
	@Override
	public float getRequest(ForgeDirection direction) {
		return 0;
	}

	@Override
	public float getProvide(ForgeDirection direction) {
		return this.electricOutput;
	}

	@Override
	public float getMaxEnergyStored() {
		return 100000;
	}
}
