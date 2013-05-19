package pixlepix.complexmachines.common.laser.tileentity;

import pixlepix.complexmachines.common.laser.LaserEmitterTileEntity;
import net.minecraft.tileentity.TileEntity;

public class LaserBeamTileEntity extends TileEntity {
	public int ticks = 0;
	private boolean initialized;
	private boolean assured=false;

	public LaserEmitterTileEntity entity;
	public void setEntity(LaserEmitterTileEntity entity){
		this.entity=entity;
	}
	
	public void destroy(){
		
	}
	public void updateEntity() {
		
		if(!worldObj.isRemote&&worldObj.getTotalWorldTime()%51==1){
			worldObj.setBlock(xCoord, yCoord, zCoord, 0);
		}

	}
	public void check(){
		boolean alive=false;
		int mismatches=0;
			if(entity!=null){
			
			if(!(xCoord==entity.xCoord)){
				mismatches++;
			}
			if(!(yCoord==entity.yCoord)){
				mismatches++;
			}
			if(!(zCoord==entity.zCoord)){
				mismatches++;
			}
			System.out.println(""+xCoord+yCoord+zCoord+mismatches);
			if(mismatches==1){
				if(entity.laserBeamId==worldObj.getBlockId(xCoord, yCoord, zCoord)){
					alive=true;
				}
			}
		}
		if(!alive){
			worldObj.setBlock(xCoord, yCoord, zCoord, 0);
		}
	}
	
	

	public void initiate() {
		this.initialized = true;
	}
}
