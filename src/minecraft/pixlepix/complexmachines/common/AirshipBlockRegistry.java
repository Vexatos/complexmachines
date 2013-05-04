package pixlepix.complexmachines.common;

import java.util.ArrayList;

public class AirshipBlockRegistry {
	public static ArrayList<CoordTuple> moved=new ArrayList<CoordTuple>();
	public static boolean delayedPlaced=false;
	public static ArrayList<AirshipDelayedBlock> delayed=new ArrayList<AirshipDelayedBlock>();
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
		if(!delayedPlaced){
			for(int i=0;i<delayed.size();i++){
				AirshipDelayedBlock toPlace=delayed.get(i);
				System.out.println(toPlace.x);

				System.out.println(toPlace.y);

				System.out.println(toPlace.z);

				System.out.println(toPlace.id);

				System.out.println(toPlace.meta);

				System.out.println(toPlace.world);
				toPlace.world.setBlock(toPlace.x, toPlace.y, toPlace.z, toPlace.id,toPlace.meta,3);
				
			}
		}
		delayedPlaced=true;
	}
	
	public static void addDelayed(AirshipDelayedBlock block){
		delayed.add(block);
	}
	
	
}
