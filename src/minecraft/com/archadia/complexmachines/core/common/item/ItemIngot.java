package com.archadia.complexmachines.core.common.item;

import net.minecraft.item.Item;
import basicmachinery.api.item.ItemBase;

import com.archadia.complexmachines.helper.ArchLoader;

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
