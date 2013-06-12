package pixlepix.complexmachines.common;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import pixlepix.complexmachines.api.IAirshipSpecialMove;
import pixlepix.complexmachines.common.tileentity.MotorTileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class AirshipBlockRegistry {
	public static ArrayList<CoordTuple> moved=new ArrayList<CoordTuple>();
	public static boolean delayedPlaced=false;
	public static ArrayList<AirshipDelayedBlock> delayed=new ArrayList<AirshipDelayedBlock>();
	public static ArrayList<AirshipDelayedBlock> normal=new ArrayList<AirshipDelayedBlock>();

	public static ArrayList<AirshipDelayedTileEntity> tileEntityError=new ArrayList<AirshipDelayedTileEntity>();
	public static void empty(){
		moved=new ArrayList<CoordTuple>();
		delayed=new ArrayList<AirshipDelayedBlock>();
		
	}
	public static boolean check(int x,int y, int z){
		for(int i=0;i<moved.size();i++){
			CoordTuple checking=moved.get(i);
			if(checking.x==x&&checking.y==y&&checking.z==z){
				return true;
			}
		}
		return false;
	}
	
	public static void register(int x,int y, int z){
		moved.add(new CoordTuple(x,y,z));
	}
	public static void fixEntities(){
		

        Iterator iterator = tileEntityError.iterator();
		for(int i=0;i<tileEntityError.size();i++){
			AirshipDelayedTileEntity data=tileEntityError.get(i);
			try{
				data.world.setBlockTileEntity(data.x, data.y, data.z, data.entity);
				iterator.remove();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	public static void placeDelayed(){
			for(int i=0;i<normal.size();i++){

				AirshipDelayedBlock toPlace=normal.get(i);
				if(MotorTileEntity.needsSupport(toPlace.id)){
					delayed.add(toPlace);
				}else{
					
				if(toPlace.world.getBlockTileEntity(toPlace.x, toPlace.y, toPlace.z)!=null){
					toPlace.world.getBlockTileEntity(toPlace.x, toPlace.y, toPlace.z).invalidate();
				}
				if(toPlace.world.getBlockTileEntity(toPlace.oldX, toPlace.oldY, toPlace.oldZ)!=null){
					toPlace.world.getBlockTileEntity(toPlace.oldX, toPlace.oldY, toPlace.oldZ).invalidate();
				}
				toPlace.world.setBlock(toPlace.x, toPlace.y, toPlace.z, toPlace.id,toPlace.meta,3);
				
				toPlace.world.setBlock(toPlace.oldX, toPlace.oldY, toPlace.oldZ, 0);
				if(toPlace.list!=null){
					if(toPlace.list.tagCount()>0){
						NBTTagCompound restoreData=(NBTTagCompound) toPlace.list.tagAt(0);
						restoreData.setInteger("x", toPlace.x);

						restoreData.setInteger("y", toPlace.y);

						restoreData.setInteger("z", toPlace.z);
						TileEntity newEntity=toPlace.world.getBlockTileEntity(toPlace.x, toPlace.y, toPlace.z);
						if (newEntity!=null){
							newEntity.readFromNBT(restoreData);
							if(newEntity instanceof IAirshipSpecialMove&&toPlace.data!=null){
								((IAirshipSpecialMove)newEntity).afterMove(toPlace.data);
							}
						}
						
						//TODO Bad practice is fun!
						try{
							toPlace.world.setBlockTileEntity(toPlace.x, toPlace.y, toPlace.z, newEntity);
						}
						catch(ConcurrentModificationException e){
							tileEntityError.add(new AirshipDelayedTileEntity(toPlace.x, toPlace.y, toPlace.z, newEntity, toPlace.world));
							
							
						}
						
					}
				}
				}
			}
			fixEntities();
			normal=new ArrayList<AirshipDelayedBlock>();
			for(int i=0;i<delayed.size();i++){
				
				AirshipDelayedBlock toPlace=delayed.get(i);

				toPlace.world.setBlock(toPlace.x, toPlace.y, toPlace.z, toPlace.id,toPlace.meta,3);
				toPlace.world.setBlock(toPlace.oldX, toPlace.oldY, toPlace.oldZ, 0);
			}

			fixEntities();
			delayed=new ArrayList<AirshipDelayedBlock>();
		}
		
	
	
	public static void addDelayed(AirshipDelayedBlock block){
		delayed.add(block);
	}
	public static void addBlock(AirshipDelayedBlock block){
		normal.add(block);
	}
	
	
}
