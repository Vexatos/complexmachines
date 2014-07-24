package com.archadia.complexmachines.core.common.container;

import com.archadia.complexmachines.core.common.slot.SlotOutput;
import com.archadia.complexmachines.core.common.tileentity.TileEntityCookieMaker;
import com.archadia.complexmachines.prefab.container.ContainerBasicMachine;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

/**
 * @author Archadia
 */
public class ContainerCookieMaker extends ContainerBasicMachine {

  private TileEntityCookieMaker tileEnt;

  public ContainerCookieMaker(InventoryPlayer par1InventoryPlayer, TileEntityCookieMaker tile) {
    super.bindPlayerInventory(par1InventoryPlayer);

    this.tileEnt = (TileEntityCookieMaker) tile;

    addSlotToContainer(new Slot(tile, 0, 26, 9));
    addSlotToContainer(new Slot(tile, 1, 26, 33));
    addSlotToContainer(new Slot(tile, 2, 26, 57));
    addSlotToContainer(new SlotOutput(tile, 3, 100, 33));
  }
}
