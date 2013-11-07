package com.archadia.complexmachines.core.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * @author Archadia
 *
 */
public class ComplexTab extends CreativeTabs {

	public ComplexTab() {
		super("Complex Machines");
	
	}

	public ItemStack getIconItemStack() {
		return new ItemStack(ComplexMachines.extractor);
	}
}
