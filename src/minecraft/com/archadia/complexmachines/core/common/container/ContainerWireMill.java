package com.archadia.complexmachines.core.common.container;

import com.archadia.complexmachines.core.common.slot.SlotOutput;
import com.archadia.complexmachines.core.common.tileentity.TileEntityWireMill;
import com.archadia.complexmachines.prefab.container.ContainerBasicMachine;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

/**
 * @author Archadia
 *
 */
public class ContainerWireMill extends ContainerBasicMachine {
	
	private TileEntityWireMill tileEnt;

    public ContainerWireMill(InventoryPlayer par1InventoryPlayer, TileEntityWireMill tile) {
        super.bindPlayerInventory(par1InventoryPlayer);
    	
    	this.tileEnt = (TileEntityWireMill) tile;
        
        addSlotToContainer(new Slot(tile, 0, 55, 37));
        addSlotToContainer(new SlotOutput(tile, 1, 105, 37));
    }
}
