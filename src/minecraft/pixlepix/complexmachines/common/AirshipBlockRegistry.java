package pixlepix.complexmachines.common;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class AirshipBlockRegistry {
	public static ArrayList<CoordTuple> moved=new ArrayList<CoordTuple>();
	public static boolean delayedPlaced=false;
	public static ArrayList<AirshipDelayedBlock> delayed=new ArrayList<AirshipDelayedBlock>();
	public static ArrayList<AirshipDelayedBlock> normal=new ArrayList<AirshipDelayedBlock>();
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
	
	public static void placeDelayed(){
			for(int i=0;i<normal.size();i++){
				AirshipDelayedBlock toPlace=normal.get(i);
				toPlace.world.setBlock(toPlace.x, toPlace.y, toPlace.z, toPlace.id,toPlace.meta,3);
				
				toPlace.world.setBlock(toPlace.oldX, toPlace.oldY, toPlace.oldZ, 0);
				if(toPlace.list!=null){
					if(toPlace.list.tagCount()>0){
						NBTTagCompound restoreData=(NBTTagCompound) toPlace.list.tagAt(0);
						TileEntity newEntity=toPlace.world.getBlockTileEntity(toPlace.x, toPlace.y, toPlace.z);
		               
		                newEntity.readFromNBT(restoreData);
						newEntity.xCoord=toPlace.x;
						newEntity.yCoord=toPlace.y;
						newEntity.zCoord=toPlace.z;
						toPlace.world.setBlockTileEntity(toPlace.x, toPlace.y, toPlace.z, newEntity);
						
					}
				}
			
			}

			normal=new ArrayList<AirshipDelayedBlock>();
			for(int i=0;i<delayed.size();i++){
				AirshipDelayedBlock toPlace=delayed.get(i);
				toPlace.world.setBlock(toPlace.x, toPlace.y, toPlace.z, toPlace.id,toPlace.meta,3);
				toPlace.world.setBlock(toPlace.oldX, toPlace.oldY, toPlace.oldZ, 0);
			}

			delayed=new ArrayList<AirshipDelayedBlock>();
		}
		
	
	
	public static void addDelayed(AirshipDelayedBlock block){
		delayed.add(block);
	}
	public static void addBlock(AirshipDelayedBlock block){
		normal.add(block);
	}
	
	
}
