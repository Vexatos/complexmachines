package basicmachinery.api;

import com.archadia.basicmachinery.core.common.BasicMachinery;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * @author Archadia
 *
 */
public class Tab extends CreativeTabs {
	public Tab(String tabname) {
		super(tabname);
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(BasicMachinery.electricFurnace);
	}
}