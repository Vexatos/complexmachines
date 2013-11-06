package com.archadia.complexmachines.prefab.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

/**
 * @author Archadia
 *
 */
public class ItemBase extends Item {

	protected String iconPath;
	
	public ItemBase(int id, String name) {
		super(id);
		setUnlocalizedName(name);
		iconPath = name;
	}
	
	public void registerIcons(IconRegister ir) {
		this.itemIcon = ir.registerIcon("complexmachines:" + iconPath);
	}
}
