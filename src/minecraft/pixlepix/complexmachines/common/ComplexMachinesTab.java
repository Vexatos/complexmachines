package pixlepix.complexmachines.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import pixlepix.complexmachines.common.*;
import pixlepix.complexmachines.common.laser.LaserEmitter;

public class ComplexMachinesTab extends CreativeTabs {
	public ComplexMachinesTab() {
		super("tabComplexMachines");
	}

	@Override
	public ItemStack getIconItemStack() {
		
		return new ItemStack(ComplexMachines.loader.getBlock(LaserEmitter.class));
		
	}
}