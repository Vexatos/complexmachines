package com.archadia.complexmachines.prefab.item;

import com.archadia.complexmachines.api.IItemBase;
import com.archadia.complexmachines.core.common.ComplexMachines;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

/**
 * @author Archadia
 *
 */
public class ItemBase extends Item implements IItemBase {

	String itemname;
	
	public ItemBase(int id, String name) {
		super(id);
		setCreativeTab(ComplexMachines.tabComplexMachines);
		setUnlocalizedName(name);		
		itemname = name;
	}
	
	public ItemBase(int id) {
		super(id);
		setCreativeTab(ComplexMachines.tabComplexMachines);
	}
	
	public void registerIcons(IconRegister ir) {
		this.itemIcon = ir.registerIcon("complexmachines:" + itemname);
	}
}
