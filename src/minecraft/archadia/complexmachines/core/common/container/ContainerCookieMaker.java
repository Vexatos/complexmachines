package archadia.complexmachines.core.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import archadia.complexmachines.core.common.slot.SlotOutput;
import archadia.complexmachines.core.common.tileentity.TileEntityCookieMaker;
import archadia.complexmachines.prefab.container.ContainerBasicMachine;
import archadia.complexmachines.prefab.tileentity.TileEntityAdvancedMachine;

/**
 * @author Archadia
 *
 */
public class ContainerCookieMaker extends ContainerBasicMachine {
	
	private TileEntityCookieMaker tileEnt;

    public ContainerCookieMaker(InventoryPlayer par1InventoryPlayer, TileEntityAdvancedMachine tile) {
        super.bindPlayerInventory(par1InventoryPlayer);
    	
    	this.tileEnt = (TileEntityCookieMaker) tile;
    	
    	addSlotToContainer(new Slot(tile, 0, 26, 9));
    	addSlotToContainer(new Slot(tile, 1, 26, 33));
    	addSlotToContainer(new Slot(tile, 2, 26, 57));
    	addSlotToContainer(new SlotOutput(tile, 3, 100, 33));
    }
}