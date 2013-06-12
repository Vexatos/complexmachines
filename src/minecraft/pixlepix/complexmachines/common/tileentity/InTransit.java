package pixlepix.complexmachines.common.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class InTransit {
	EntityItem stack;
	Link link;
	public InTransit(EntityItem stack, Link link){
		this.stack=stack;
		this.link=link;
	}

}
