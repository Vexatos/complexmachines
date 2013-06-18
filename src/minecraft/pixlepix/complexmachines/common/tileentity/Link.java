package pixlepix.complexmachines.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import pixlepix.complexmachines.common.CoordTuple;

public class Link {
	CoordTuple origin;
	CoordTuple destination;
	int originSide;
	int destinationSide;
	int id;
	public Link(CoordTuple origin, int originSide, CoordTuple destination, int destinationSide, int id){
		this.origin=origin;
		this.destination=destination;
		this.originSide=originSide;
		this.destinationSide=destinationSide;
		this.id=id;
	}
	public Link(NBTTagCompound nbt){
		
		this.origin=new CoordTuple(nbt.getInteger("1X"),nbt.getInteger("1Y"),nbt.getInteger("1Z"));

		this.destination=new CoordTuple(nbt.getInteger("2X"),nbt.getInteger("2Y"),nbt.getInteger("2Z"));
		this.originSide=nbt.getInteger("1S");
		this.destinationSide=nbt.getInteger("2S");
		this.id=nbt.getInteger("ID");
	}
	
	
	public void writeToNBT(NBTTagCompound nbt){
		nbt.setInteger("1X", (int)origin.x);

		nbt.setInteger("1Y", (int)origin.y);

		nbt.setInteger("1Z", (int)origin.z);

		nbt.setInteger("2X", (int)destination.x);

		nbt.setInteger("2Y", (int)destination.y);

		nbt.setInteger("2Z", (int)destination.z);

		nbt.setInteger("1S", originSide);

		nbt.setInteger("2S", destinationSide);

		nbt.setInteger("ID", id);
	}
}
