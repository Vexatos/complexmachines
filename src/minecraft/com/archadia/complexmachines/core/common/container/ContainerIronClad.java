package com.archadia.complexmachines.core.common.container;

import com.archadia.complexmachines.core.common.tileentity.TileEntityExtractor;
import com.archadia.complexmachines.core.common.tileentity.TileEntityIronClad;
import com.archadia.complexmachines.prefab.container.ContainerBasicMachine;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

/**
 * @author Archadia
 *
 */
public class ContainerIronClad extends ContainerBasicMachine {
	
	private TileEntityIronClad tile;
	
	public ContainerIronClad(InventoryPlayer par1InventoryPlayer, TileEntityIronClad tile2) {
        super.bindPlayerInventory(par1InventoryPlayer);
    	
    	this.tile = (TileEntityIronClad) tile2;
    	
    }
}
