package pixlepix.complexmachines.common;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AirshipDelayedTileEntity {
	
	public TileEntity entity;
	public int x;
	public int y;
	public int z;
	public World world;
	
	public AirshipDelayedTileEntity(int x, int y, int z, TileEntity entity, World w){
		this.entity=entity;
		this.x=x;
		this.y=y;
		this.z=z;
		this.world=w;
	}

}
