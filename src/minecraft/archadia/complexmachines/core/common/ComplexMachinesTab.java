package archadia.complexmachines.core.common;

import cpw.mods.fml.common.Loader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * @author Archadia
 *
 */
public class ComplexMachinesTab extends CreativeTabs {
	public ComplexMachinesTab() {
		super("tabComplexMachines");
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ComplexMachines.C194);
	}
}