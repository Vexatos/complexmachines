package pixlepix.complexmachines.common.tileentity;

import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.PowerProducerComplexTileEntity;
import universalelectricity.core.block.IConductor;

public class SinglePointTileEntity extends PowerProducerComplexTileEntity{
	
	public static double maxJoules = 2000000;
	/**
	 * The ItemStacks that hold the items currently being used in the wire mill;
	 * 0 = battery; 1 = input; 2 = output;
	 */

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
		
		ticks++;
		if(ticks%20==0){
			for(int i=0;i<256;i++){
				if(worldObj.getBlockId(xCoord, i, zCoord)==Config.blockStartingID+4&&i!=yCoord){
					worldObj.setBlock(xCoord, yCoord, zCoord, 0);
					return;
				}
			}
		}
		
		
		if (!this.worldObj.isRemote) {
			if (atCorrectLocation()) {
				this.electricOutput=500000;
				super.updateEntity(); 
				

			

		}
	}
	}
	private boolean atCorrectLocation() {
		int target=Config.singlePointRadius;
			if(xCoord==target&&zCoord==target){
				return true;
			}
			if(xCoord==target&&zCoord==-1*target){
				return true;
			}
			if(xCoord==-1*target&&zCoord==target){
				return true;
			}
			if(xCoord==-1*target&&zCoord==-1*target){
				return true;
			}
			
		
		return false;
	}

	

}
