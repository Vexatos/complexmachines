package com.archadia.complexmachines.core.common.container;

import com.archadia.complexmachines.core.common.slot.SlotOutput;
import com.archadia.complexmachines.core.common.tileentity.TileEntityAlloyFabricator;
import com.archadia.complexmachines.prefab.container.ContainerBasicMachine;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

/**
 * @author Archadia
 */
public class ContainerAlloyFabricator extends ContainerBasicMachine {
  private TileEntityAlloyFabricator fabricator;

  public ContainerAlloyFabricator(InventoryPlayer par1InventoryPlayer, TileEntityAlloyFabricator tile) {
    super.bindPlayerInventory(par1InventoryPlayer);

    this.fabricator = (TileEntityAlloyFabricator) tile;

    this.addSlotToContainer(new Slot(tile, 0, 43, 13));
    this.addSlotToContainer(new Slot(tile, 1, 62, 13));
    this.addSlotToContainer(new Slot(tile, 2, 81, 13));
    this.addSlotToContainer(new Slot(tile, 3, 43, 57));
    this.addSlotToContainer(new SlotOutput(tile, 4, 128, 33));
  }
}
