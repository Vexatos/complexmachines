package archadia.complexmachines.core.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import archadia.complexmachines.core.common.slot.SlotOutput;
import archadia.complexmachines.core.common.tileentity.TileEntityWireMill;
import archadia.complexmachines.prefab.container.ContainerBasicMachine;

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
