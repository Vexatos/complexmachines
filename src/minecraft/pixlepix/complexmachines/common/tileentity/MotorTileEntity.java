package pixlepix.complexmachines.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import pixlepix.complexmachines.common.AirshipBlockRegistry;
import pixlepix.complexmachines.common.CoordTuple;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;

public class MotorTileEntity extends TileEntity {
	
	public int ticks=0;
	
	public void initiate() {
		
		
	}
	
	public void updateEntity(){
		ticks--;
	}
	
	public void moveBlock(ForgeDirection direction, CoordTuple target, boolean center){
		if(!worldObj.isRemote){
		int targetX = target.x+direction.offsetX;
		int targetY = target.y+direction.offsetY;
		int targetZ = target.z+direction.offsetZ;

		int meta=worldObj.getBlockMetadata(target.x,target.y,target.z);
		int targetId=worldObj.getBlockId(targetX, targetY, targetZ);
		if((targetId>7&&targetId<12)||targetId==0||(worldObj.getBlockTileEntity(targetX, targetY, targetZ) instanceof MotorTileEntity&&center)){
			AirshipBlockRegistry.register(targetX, targetY, targetZ);
			int materialId=worldObj.getBlockId(target.x, target.y, target.z);
			
			NBTTagCompound data=new NBTTagCompound();
			Block targetBlockType = this.blockType;
			TileEntity newEntity=worldObj.getBlockTileEntity(targetX, targetY, targetZ);
		
			//System.out.println(meta);
			worldObj.setBlock(targetX, targetY, targetZ, materialId);
			worldObj.setBlockMetadataWithNotify(targetX, targetY, targetZ, meta, 2);
			
			
			TileEntity oldEntity=worldObj.getBlockTileEntity(targetX, targetY, targetZ);
			//if(newEntity!=null&&!(newEntity instanceof MotorTileEntity)){
			if(newEntity!=null){
				newEntity.xCoord=targetX;
				newEntity.yCoord=targetY;
				newEntity.zCoord=targetZ;
				
				
				worldObj.setBlockTileEntity(targetX, targetY, targetZ, newEntity);
			}

			worldObj.setBlock(target.x, target.y, target.z, 0);
		}
		}
	}
	
	public void move(ForgeDirection direction){
		if(ticks<0){
			ticks=3;
			CoordTuple[] nearby={new CoordTuple(xCoord+1,yCoord,zCoord+1),new CoordTuple(xCoord+1,yCoord,zCoord-1),new CoordTuple(xCoord-1,yCoord,zCoord+1),new CoordTuple(xCoord-1,yCoord,zCoord-1),new CoordTuple(xCoord+1,yCoord,zCoord),new CoordTuple(xCoord-1,yCoord,zCoord),new CoordTuple(xCoord,yCoord+1,zCoord),new CoordTuple(xCoord,yCoord-1,zCoord),new CoordTuple(xCoord,yCoord,zCoord+1),new CoordTuple(xCoord,yCoord,zCoord-1)};
			ArrayList<CoordTuple> near=new ArrayList<CoordTuple>();
			int xMin=xCoord-2;
			int yMin=yCoord-2;
			int zMin=zCoord-2;
			
			int xMax=xCoord+3;
			int yMax=yCoord+3;
			int zMax=zCoord+3;
			
			int xInc=1;
			int yInc=1;
			int zInc=1;
			
			boolean reverse=false;
			
			if(direction.offsetX==1){
				xMax=xCoord-2;
				xMin=xCoord+3;
				xInc=-1;
				reverse=true;
			}
			
			if(direction.offsetY==1){
				yMax=yCoord-2;
				yMin=yCoord+3;
				yInc=-1;
				reverse=true;
			}
			
			if(direction.offsetZ==1){
				zMax=zCoord-2;
				zMin=zCoord+3;
				zInc=-1;
				reverse=true;
			}
			
			if(!reverse){
				for(int i=xCoord-2;i<xCoord+3;i++){
					for(int j=yCoord-2;j<yCoord+3;j++){
						System.out.println("Second loop");
						for(int k=zCoord-2;k<zCoord+3;k++){
							register(i,j,k,near);
							
						}	
					}
				}
			}else{
				
				for(int i=xCoord+2;i>xCoord-3;i--){
					for(int j=yCoord+2;j>yCoord-3;j--){
						for(int k=zCoord+2;k>zCoord-3;k--){
							register(i,j,k,near);
							
						}	
					}
				}
				
			}
				
			
						
		
					
			for(int l=0;l<near.size();l++){
				CoordTuple target=near.get(l);
				if(target!=null&&!AirshipBlockRegistry.check(target.x, target.y, target.z)){
					moveBlock(direction, target,false);
				}
			}

			moveBlock(direction, new CoordTuple(xCoord,yCoord,zCoord),false);

			moveBlock(direction, new CoordTuple(xCoord-direction.offsetX,yCoord-direction.offsetY,zCoord-direction.offsetZ),true);
			moveBlock(direction, new CoordTuple(xCoord-2*direction.offsetX,yCoord-2*direction.offsetY,zCoord-2*direction.offsetZ),true);
			moveBlock(direction, new CoordTuple(xCoord-3*direction.offsetX,yCoord-3*direction.offsetY,zCoord-3*direction.offsetZ),true);

			List<Entity> entities=worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(xCoord-1.5, yCoord-1.5, zCoord-1.5, xCoord+1.5, yCoord+1.5, zCoord+1.5));
			for(int i=0;i<entities.size();i++){
				Entity entity=entities.get(i);
				entity.setPosition(entity.posX, entity.posY+2, entity.posZ);
			}
		}
	}

	private void register(int i, int j, int k, ArrayList near) {
		if(xCoord!=i||yCoord!=j||zCoord!=k){
			System.out.println("X: "+i+"Y: "+j+"Z:");
			if(worldObj.getBlockId(i, j, k)!=0){
				near.add(new CoordTuple(i,j,k));
			}
		}
		
	}
	

}
