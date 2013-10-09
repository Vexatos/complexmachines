package basicmachinery.api;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import archadia.basicmachinery.core.common.BasicMachinery;

/**
 * @author Archadia
 *
 */
public class Tab extends CreativeTabs {
	
	
	public Tab(String tabname) {
		super(tabname);
	}
	
	public ItemStack getIconItemStack(Block block) {
		return new ItemStack(block);
	}
}