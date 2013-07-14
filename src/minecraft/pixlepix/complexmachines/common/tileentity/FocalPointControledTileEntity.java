package pixlepix.complexmachines.common.tileentity;

import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.PowerProducerComplexTileEntity;
import universalelectricity.core.block.IConductor;

public class FocalPointControledTileEntity extends PowerProducerComplexTileEntity{
	public double electricOutput=Config.focalPointOutput;
	
	public int orientation;
	private int targetID = 0;

	private boolean initialized;
	private IConductor connectedElectricUnit;

	@Override
	public void initiate() {
		this.electricOutput=Config.focalPointOutput;
		this.initialized = true;
	}

}
