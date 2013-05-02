package pixlepix.complexmachines.common;

import java.util.ArrayList;

import net.minecraft.world.World;

public class CoordTuple
{
	public final int x;
	public final int y;
	public final int z;

	public static CoordTuple[] getNearby(int x,int y, int z){
		CoordTuple[] out=new CoordTuple[27];
		int index=0;
		for(int i=x-1;i<x+1;i++){
			for(int j=y-1;j<y+1;j++){
				for(int k=z-1;k<z+1;k++){
					out[index]=new CoordTuple(i,j,k);
					index++;
				}
			}
		}
		return out;
	}
	
	public CoordTuple(int posX, int posY, int posZ)
	{
		x = posX;
		y = posY;
		z = posZ;
	}
	public int getBlock(World world){
		return world.getBlockId(x, y, z);
	}
	public boolean equals(CoordTuple coord)
	{
		if (coord == null)
			return false;
		else if (coord == this)
			return true;
		else if (coord.x == this.x && coord.y == this.y && coord.z == this.z)
			return true;
		else
			return false;
	}

	public boolean equals(int posX, int posY, int posZ)
	{
		if (this.x == posX && this.y == posY && this.z == posZ)
			return true;
		else
			return false;
	}

	public String toString()
	{
	    return "X: "+x+", Y: "+y+", Z: "+z;
	}
}