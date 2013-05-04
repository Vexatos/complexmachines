package pixlepix.complexmachines.common;

import java.util.ArrayList;

import net.minecraft.world.World;

public class AirshipDelayedBlock {

	public int x;
	public int y;
	public int z;
	public int id;
	public int meta;
	public World world;
	
	public AirshipDelayedBlock(int x,int y,int z,int id, int meta, World world){
		this.x=x;
		this.y=y;
		this.z=z;
		this.id=id;
		this.meta=meta;
		this.world=world;
	}
	public static ArrayList ids=new ArrayList();
	public static boolean shouldBeDelayed(int id){
		int[] ids={6,50,75,76,69};
		
		for(int i=0;i<ids.length;i++){
			if(id==ids[i]){
				return true;
			}
		}
		
		return false;
		
	}
	
	
}
