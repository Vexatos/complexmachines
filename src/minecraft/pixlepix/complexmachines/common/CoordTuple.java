package pixlepix.complexmachines.common;

import java.util.ArrayList;

import universalelectricity.core.vector.Vector3;

import net.minecraft.world.World;

public class CoordTuple
{
	public final double x;
	public final double y;
	public final double z;

	public static CoordTuple[] getNearby(double x,double y, double z){
		CoordTuple[] out=new CoordTuple[27];
		int index=0;
		for(int i=(int)x-1;i<(int)x+1;i++){
			for(int j=(int)y-1;j<(int)y+1;j++){
				for(int k=(int)z-1;k<(int)z+1;k++){
					out[index]=new CoordTuple(i,j,k);
					index++;
				}
			}
		}
		return out;
	}
	
	public CoordTuple(double posX, double posY, double posZ)
	{
		x = posX;
		y = posY;
		z = posZ;
	}
	public int getBlock(World world){
		return world.getBlockId((int)x,(int) y, (int)z);
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

	public boolean equals(double posX, double posY, double posZ)
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
	public Vector3 getVector(){
		return new Vector3(x,y,z);
	}
	public CoordTuple advance(CoordTuple tuple, double increment){
		double newX=x;
		double newY=y;
		double newZ=z;
		if(tuple.x>x){
			newX+=increment;
		}
		if(tuple.y>y){
			newY+=increment;
		}
		if(tuple.z>z){
			newZ+=increment;
		}
		return new CoordTuple(newX,newY,newZ);
		
	}
}