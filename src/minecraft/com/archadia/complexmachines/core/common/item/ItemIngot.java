package com.archadia.complexmachines.core.common.item;

import java.util.List;

import com.archadia.complexmachines.core.client.icon.IconWidgets;
import com.archadia.complexmachines.core.common.ComplexMachines;
import com.archadia.complexmachines.helper.ArchLoader;
import com.archadia.complexmachines.prefab.item.ItemBase;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Archadia
 *
 */
public class ItemIngot extends ItemBase {
	
	public ItemIngot(int id, String name) {
		super(id, name);
		addToLibrary(this);
	}
	
	public void addToLibrary(Item i) {
		ArchLoader.ingotLibrary.add(i);
	}
}
