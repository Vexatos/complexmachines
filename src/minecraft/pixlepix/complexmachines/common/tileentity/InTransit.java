package pixlepix.complexmachines.common.tileentity;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class InTransit {
	EntityItem stack;
	Link link;
	int ticks=0;
	public InTransit(EntityItem stack, Link link){
		this.stack=stack;
		this.link=link;
	}

	
	public void writeToNBT(NBTTagCompound nbt){
		nbt.setInteger("linkId", link.id);
		nbt.setInteger("entityId", stack.entityId);

		nbt.setInteger("ticks", ticks);
	}
	public InTransit(NBTTagCompound nbt, NodeTileEntity entity){
		this.ticks=nbt.getInteger("ticks");
		int entityId=nbt.getInteger("entityId");
		stack=(EntityItem)entity.worldObj.getEntityByID(entityId);
		int linkId=nbt.getInteger("linkId");
		ArrayList<Link> links=entity.links;
		Iterator<Link> iter=links.iterator();
		boolean foundLink;
		while(iter.hasNext()){
			Link testLink=iter.next();
			if(testLink.id==linkId){
				foundLink=true;
				this.link=testLink;
				break;
			}
		}
		
		
		
	}
}
