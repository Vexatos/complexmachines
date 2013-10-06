package com.archadia.complexmachines.core.common.container;

import com.archadia.complexmachines.core.common.tileentity.TileEntityGrinder;
import com.archadia.complexmachines.prefab.container.ContainerBasicMachine;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

/**
 * @author Archadia
 *
 */
public class ContainerGrinder extends ContainerBasicMachine {
	
	private TileEntityGrinder tile;

    public ContainerGrinder(InventoryPlayer par1InventoryPlayer, TileEntityGrinder tile2) {
    	super.bindPlayerInventory(par1InventoryPlayer);
    	this.tile = (TileEntityGrinder) tile2;
        
        addSlotToContainer(new Slot(tile2, 0, 8, 8));
        addSlotToContainer(new Slot(tile2, 1, 43, 8));
        addSlotToContainer(new Slot(tile2, 2, 78, 8));
    }

}
