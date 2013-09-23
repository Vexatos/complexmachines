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
	
	private TileEntityWireMill fabricator;

    public ContainerWireMill(InventoryPlayer par1InventoryPlayer, TileEntityWireMill tileEnt) {
        super.bindPlayerInventory(par1InventoryPlayer);
    	
    	this.fabricator = tileEnt;
        
        addSlotToContainer(new Slot(tileEnt, 0, 55, 37));
        addSlotToContainer(new SlotOutput(tileEnt, 1, 105, 37));
    }
}
