package com.archadia.complexmachines.prefab.item;

import com.archadia.complexmachines.api.IItemBase;
import com.archadia.complexmachines.core.common.ComplexMachines;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import universalelectricity.compatibility.ItemUniversalElectric;

/**
 * @author Archadia
 *
 */
public class ItemEBase extends ItemUniversalElectric implements IItemBase {

	String itemname;
	
	public ItemEBase(int id, String name) {
		super(id);
		setCreativeTab(ComplexMachines.tabComplexMachines);
		setUnlocalizedName(name);		
		itemname = name;
	}
	
	public ItemEBase(int id) {
		super(id);
		setCreativeTab(ComplexMachines.tabComplexMachines);
	}

	@Override
	public float getMaxElectricityStored(ItemStack theItem) {
		return 0;
	}

	public void registerIcons(IconRegister ir) {
		this.itemIcon = ir.registerIcon("complexmachines:" + itemname);
	}
}
