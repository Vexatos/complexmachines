package pixlepix.complexmachines.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import pixlepix.complexmachines.common.AirshipBlockRegistry;
import pixlepix.complexmachines.common.AirshipDelayedBlock;
import pixlepix.complexmachines.common.CoordTuple;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.common.ForgeDirection;

public class MotorTileEntity extends TileEntity {
	
	public int ticks=0;
	public int momentum;
	public ForgeDirection momentumDirection;
	
	public void initiate() {
		
		
	}
	
	
		public static boolean setBlockDirectlyMain(World world,int par1, int par2, int par3, int par4, int par5, int par6, boolean support)
	    {
	        if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000)
	        {
	            if (par2 < 0)
	            {
	                return false;
	            }
	            else if (par2 >= 256)
	            {
	                return false;
	            }
	            else
	            {
	                Chunk chunk = world.getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
	                int k1 = 0;

	                if ((par6 & 1) != 0)
	                {
	                    k1 = chunk.getBlockID(par1 & 15, par2, par3 & 15);
	                }

	                boolean flag = setBlockIDWithMetadataDirect(chunk,par1 & 15, par2, par3 & 15, par4, par5,support,world);
	                world.theProfiler.startSection("checkLight");
	                world.updateAllLightTypes(par1, par2, par3);
	                world.theProfiler.endSection();

	                if (flag)
	                {
	                    if ((par6 & 2) != 0 && (!world.isRemote || (par6 & 4) == 0))
	                    {
	                        world.markBlockForUpdate(par1, par2, par3);
	                    }

	                    if (!world.isRemote && (par6 & 1) != 0)
	                    {
	                    	
	                        world.notifyBlockChange(par1, par2, par3, k1);
	                        Block block = Block.blocksList[par4];

	                        if (block != null && block.hasComparatorInputOverride())
	                        {
	                            world.func_96440_m(par1, par2, par3, par4);
	                        }
	                    }
	                }

	                return flag;
	            }
	        }
	        else
	        {
	            return false;
	        }
	    }

		public static boolean setBlockIDWithMetadataDirect(Chunk chunk, int par1, int par2, int par3, int par4, int par5, boolean support, World world)
	    {
	        int j1 = par3 << 4 | par1;

	        if (par2 >= chunk.precipitationHeightMap[j1] - 1)
	        {
	            chunk.precipitationHeightMap[j1] = -999;
	        }

	        int k1 = chunk.heightMap[j1];
	        int l1 = chunk.getBlockID(par1, par2, par3);
	        int i2 = chunk.getBlockMetadata(par1, par2, par3);

	        if (l1 == par4 && i2 == par5)
	        {
	            return false;
	        }
	        else
	        {
	            ExtendedBlockStorage extendedblockstorage = chunk.getBlockStorageArray()[par2 >> 4];
	            boolean flag = false;

	            if (extendedblockstorage == null)
	            {
	                if (par4 == 0)
	                {
	                    return false;
	                }

	                extendedblockstorage = chunk.getBlockStorageArray()[par2 >> 4] = new ExtendedBlockStorage(par2 >> 4 << 4, !chunk.worldObj.provider.hasNoSky);
	                flag = par2 >= k1;
	            }

	            int j2 = chunk.xPosition * 16 + par1;
	            int k2 = chunk.zPosition * 16 + par3;

	            if (l1 != 0 && !chunk.worldObj.isRemote)
	            {
	                Block.blocksList[l1].onSetBlockIDWithMetaData(chunk.worldObj, j2, par2, k2, i2);
	            }

	            extendedblockstorage.setExtBlockID(par1, par2 & 15, par3, par4);

	            if (l1 != 0)
	            {
	                if (!chunk.worldObj.isRemote)
	                {
	                    Block.blocksList[l1].breakBlock(chunk.worldObj, j2, par2, k2, l1, i2);
	                }
	                else if (Block.blocksList[l1] != null && Block.blocksList[l1].hasTileEntity(i2))
	                {
	                    TileEntity te = world.getBlockTileEntity(j2, par2, k2);
	                    if (te != null && te.shouldRefresh(l1, par4, i2, par5, world, j2, par2, k2))
	                    {
	                        chunk.worldObj.removeBlockTileEntity(j2, par2, k2);
	                    }
	                }
	            }

	            if (extendedblockstorage.getExtBlockID(par1, par2 & 15, par3) != par4)
	            {
	                return false;
	            }
	            else
	            {
	                extendedblockstorage.setExtBlockMetadata(par1, par2 & 15, par3, par5);

	                if (flag)
	                {
	                    chunk.generateSkylightMap();
	                }
	                else
	                {
	                    
	                }

	                TileEntity tileentity;

	                if (par4 != 0)
	                {
	                    if (!chunk.worldObj.isRemote)
	                    {
	                    	
	                    	
	                    	if(!support){
	                    	
	                    		//Block.blocksList[par4].onBlockAdded(chunk.worldObj, j2, par2, k2);
	                    	}else{
	                    		System.out.println("Moving block that requires support");
	                    	}
	                    	
	                    }

	                    if (Block.blocksList[par4] != null && Block.blocksList[par4].hasTileEntity(par5))
	                    {
	                        tileentity = chunk.getChunkBlockTileEntity(par1, par2, par3);

	                        if (tileentity == null)
	                        {
	                            tileentity = Block.blocksList[par4].createTileEntity(chunk.worldObj, par5);
	                            chunk.worldObj.setBlockTileEntity(j2, par2, k2, tileentity);
	                        }

	                        if (tileentity != null)
	                        {
	                            tileentity.updateContainingBlockInfo();
	                            tileentity.blockMetadata = par5;
	                        }
	                    }
	                }

	                chunk.isModified = true;
	                return true;
	            }
	        }
	    }
	
		
	
	public boolean needsSupport(int id){
		int[] support={6,26,27,28,31,32,37,38,39,40,50,55,59,63,64,65,66,68,69,70,71,72,75,76,77,83,93,94,96,104,105,106,111,115,131,132,141,142,143,147,148,149,150,157};
		for(int i=0;i<support.length;i++){
			if(support[i]==id){
				return true;
			}
		}
		return false;
	}
	
	public void updateEntity(){
		if(ticks<0){
			
		}
		ticks--;
	}
	public void scan(ForgeDirection direction, CoordTuple target, boolean center){
		if(!worldObj.isRemote){
			int targetX = target.x+direction.offsetX;
			int targetY = target.y+direction.offsetY;
			int targetZ = target.z+direction.offsetZ;
			
			
			
			int meta=worldObj.getBlockMetadata(target.x,target.y,target.z);
			int targetId=worldObj.getBlockId(targetX, targetY, targetZ);
			if((targetId>7&&targetId<12)||targetId==0||(worldObj.getBlockTileEntity(targetX, targetY, targetZ) instanceof MotorTileEntity&&center)){
				
				int materialId=worldObj.getBlockId(target.x, target.y, target.z);
				
				if(needsSupport(materialId)){
					AirshipBlockRegistry.addDelayed(new AirshipDelayedBlock(targetX,targetY,targetZ,materialId,meta,worldObj));
					worldObj.setBlock(target.x, target.y, target.z, 0);
				//worldObj.setBlock(targetX, targetY, targetZ, materialId, meta, 3);
				}
				
				
				
				//if(newEntity!=null&&!(newEntity instanceof MotorTileEntity)){
				
				
			}
			}
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
			TileEntity oldEntity=worldObj.getBlockTileEntity(target.x, target.y, target.z);
			NBTTagList list=new NBTTagList();
			NBTTagCompound data=new NBTTagCompound();
			if(oldEntity != null){
				oldEntity.writeToNBT(data);
				list.appendTag(data);
				oldEntity.invalidate();
			}
			Block targetBlockType = this.blockType;
			/*if(AirshipDelayedBlock.shouldBeDelayed(materialId)){
				AirshipBlockRegistry.addDelayed(new AirshipDelayedBlock(targetX,targetY,targetZ,materialId,meta, worldObj));
				worldObj.setBlock(target.x, target.y, target.z, 0);
				System.out.println(worldObj);
				return;
			}
			*/

			worldObj.setBlock(target.x, target.y, target.z, 0);
			
			//AirshipBlockRegistry.addDelayed(new AirshipDelayedBlock(targetX,targetY,targetZ,materialId,meta,worldObj));
			
			worldObj.setBlock(targetX, targetY, targetZ, materialId, meta, 3);
			
			
			
			//if(newEntity!=null&&!(newEntity instanceof MotorTileEntity)){
			if(list.tagCount()>0){
				NBTTagCompound restoreData=(NBTTagCompound) list.tagAt(0);
				TileEntity newEntity=worldObj.getBlockTileEntity(targetX, targetY, targetZ);
               
                newEntity.readFromNBT(data);
				newEntity.xCoord=targetX;
				newEntity.yCoord=targetY;
				newEntity.zCoord=targetZ;
				worldObj.setBlockTileEntity(targetX, targetY, targetZ, newEntity);
				
			}
		}
		}
	}
	
	public void move(ForgeDirection direction){
		
		if(ticks<0){
			
			for(int i=xCoord-4;i<xCoord+4;i++){
				for(int j=yCoord-4;j<yCoord+4;j++){
				
					for(int k=zCoord-4;k<zCoord+4;k++){
						scan(direction, new CoordTuple(i,j,k),true);
						
					}
				}
			}
			ticks=3;
			//xCoordTuple[] nearby={new CoordTuple(xCoord+1,yCoord,zCoord+1),new CoordTuple(xCoord+1,yCoord,zCoord-1),new CoordTuple(xCoord-1,yCoord,zCoord+1),new CoordTuple(xCoord-1,yCoord,zCoord-1),new CoordTuple(xCoord+1,yCoord,zCoord),new CoordTuple(xCoord-1,yCoord,zCoord),new CoordTuple(xCoord,yCoord+1,zCoord),new CoordTuple(xCoord,yCoord-1,zCoord),new CoordTuple(xCoord,yCoord,zCoord+1),new CoordTuple(xCoord,yCoord,zCoord-1)};
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
					
						for(int k=zCoord-2;k<zCoord+3;k++){
							scan(direction,new CoordTuple(i,j,k), true);
							if(!isTrailing(i,j,k,direction)){
								register(i,j,k,near);
							}
							
						}	
					}
				}
			}else{
				
				for(int i=xCoord+2;i>xCoord-3;i--){
					for(int j=yCoord+2;j>yCoord-3;j--){
						for(int k=zCoord+2;k>zCoord-3;k--){

							scan(direction,new CoordTuple(i,j,k), true);
							if(!isTrailing(i,j,k,direction)){
								register(i,j,k,near);
							}
							
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
			//moveBlock(direction, new CoordTuple(xCoord-3*direction.offsetX,yCoord-3*direction.offsetY,zCoord-3*direction.offsetZ),true);
			if(direction.offsetY==1){
			List<Entity> entities=worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(xCoord-1.5, yCoord-1.5, zCoord-1.5, xCoord+1.5, yCoord+1.5, zCoord+1.5));
				for(int i=0;i<entities.size();i++){
					
					Entity entity=entities.get(i);
					entity.setPosition(entity.posX, entity.posY+1.5, entity.posZ);
				}
			}
			
		}
	}

	public boolean isTrailing(int x,int y, int z, ForgeDirection direction){
		if(x==xCoord-direction.offsetX&&y==yCoord-direction.offsetY&&z==zCoord-direction.offsetZ){
			//System.out.println(x+y+z);
			return true;
		}
		if(x==xCoord-2*direction.offsetX&&y==yCoord-2*direction.offsetY&&z==zCoord-2*direction.offsetZ){
			return true;
		}
		if(x==xCoord-3*direction.offsetX&&y==yCoord-3*direction.offsetY&&z==zCoord-3*direction.offsetZ){
			return true;
		}
		
		return false;
	}
	
	
	private void register(int i, int j, int k, ArrayList near) {
		if(xCoord!=i||yCoord!=j||zCoord!=k){
			
			if(worldObj.getBlockId(i, j, k)!=0){
				near.add(new CoordTuple(i,j,k));
			}
		}
		
	}
	

}
