package com.archadia.complexmachines.core.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import com.archadia.complexmachines.core.common.slot.SlotOutput;
import com.archadia.complexmachines.core.common.tileentity.TileEntityExtractor;
import com.archadia.complexmachines.prefab.container.ContainerBasicMachine;

/**
 * @author Archadia
 *
 */
public class ContainerExtractor extends ContainerBasicMachine {

	private TileEntityExtractor tile;
	
	public ContainerExtractor(InventoryPlayer par1InventoryPlayer, TileEntityExtractor tile2) {
        super.bindPlayerInventory(par1InventoryPlayer);
    	
    	this.tile = (TileEntityExtractor) tile2;
    	
    	for(int i = 0; i < 6; i++) {
        	addSlotToContainer(new Slot(tile2, i, 8 + i * 22, 8));
    	}
    	addSlotToContainer(new Slot(tile2, 7, 142, 45));
    }
}
