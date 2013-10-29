package com.archadia.complexmachines.core.common.item;

import com.archadia.complexmachines.core.common.ComplexMachines;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;

/**
 * @author Archadia
 *
 */
public class ItemFoodBase extends ItemFood {

	String itemname;
	
	public ItemFoodBase(int id, int par2, float par3, boolean par4, String name) {
		super(id, par2, par3, par4);
		setUnlocalizedName(name);		
		itemname = name;
	}
	
	public void registerIcons(IconRegister ir) {
		this.itemIcon = ir.registerIcon("complexmachines:" + itemname);
	}
	
}
