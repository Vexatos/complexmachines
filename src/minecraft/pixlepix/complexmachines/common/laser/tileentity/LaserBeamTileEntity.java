package pixlepix.complexmachines.common.laser.tileentity;

import pixlepix.complexmachines.common.laser.LaserEmitterTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;

public class LaserBeamTileEntity extends TileEntity {
	public int ticks = 0;
	private boolean initialized;
	int ordinalNorth=ForgeDirection.NORTH.ordinal();

	int ordinalSouth=ForgeDirection.SOUTH.ordinal();

	int ordinalEast=ForgeDirection.EAST.ordinal();

	int ordinalWest=ForgeDirection.WEST.ordinal();
	
	public AxisAlignedBB getLaserAABB(){

		int meta=worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		if(meta==0){
			meta=3;
		}
		ForgeDirection direction=ForgeDirection.VALID_DIRECTIONS[meta];
		System.out.println(direction.ordinal());
		
		switch(direction.ordinal()){
		
			case 5:
			 return AxisAlignedBB.getBoundingBox(xCoord-.5, yCoord-.5, zCoord-.5, xCoord+20.5, yCoord+.5, zCoord+.5);
			
			case 4:
			 return AxisAlignedBB.getBoundingBox(xCoord-20.5, yCoord-.5, zCoord-.5, xCoord+.5, yCoord+.5, zCoord+.5);
			 
			case 3:
			 return AxisAlignedBB.getBoundingBox(xCoord-.5, yCoord-.5, zCoord-.5, xCoord+0.5, yCoord+.5, zCoord+20.5);
			
			case 2:
			 return AxisAlignedBB.getBoundingBox(xCoord-.5, yCoord-.5, zCoord-20.5, xCoord+0.5, yCoord+.5, zCoord+.5);
			 
		}
		return null;
	}
	
	//Used for ElectricLaserBeam
	public AxisAlignedBB getExpandedLaserAABB(){

		int meta=worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		if(meta==0){
			meta=3;
		}
		ForgeDirection direction=ForgeDirection.VALID_DIRECTIONS[meta];
		System.out.println(direction.ordinal());
		
		switch(direction.ordinal()){
		
			case 5:
			 return AxisAlignedBB.getBoundingBox(xCoord-25.5, yCoord-25.5, zCoord-25.5, xCoord+45.5, yCoord+25.5, zCoord+25.5);
			
			case 4:
			 return AxisAlignedBB.getBoundingBox(xCoord-45.5, yCoord-25.5, zCoord-25.5, xCoord+25.5, yCoord+25.5, zCoord+25.5);
			 
			case 3:
			 return AxisAlignedBB.getBoundingBox(xCoord-25.5, yCoord-25.5, zCoord-25.5, xCoord+25.5, yCoord+25.5, zCoord+45.5);
			
			case 2:
			 return AxisAlignedBB.getBoundingBox(xCoord-25.5, yCoord-25.5, zCoord-45.5, xCoord+25.5, yCoord+25.5, zCoord+25.5);
			 
		}
		return null;
	}
	public LaserEmitterTileEntity entity;
	public void setEntity(LaserEmitterTileEntity entity){
		this.entity=entity;
	}
	public void updateEntity() {
		if(!worldObj.isRemote&&worldObj.getTotalWorldTime()%500==1){
			worldObj.setBlock(xCoord, yCoord, zCoord, 0);
		}

	}
	
	
	

	public void initiate() {
		this.initialized = true;
	}
}
