package archadia.complexmachines.core.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import archadia.complexmachines.core.common.slot.SlotOutput;
import archadia.complexmachines.core.common.tileentity.TileEntityExtractor;
import archadia.complexmachines.core.common.tileentity.TileEntityWireMill;
import archadia.complexmachines.prefab.container.ContainerBasicMachine;

/**
 * @author Archadia
 *
 */
public class ContainerExtractor extends ContainerBasicMachine {

	private TileEntityExtractor tile;
	
	public ContainerExtractor(InventoryPlayer par1InventoryPlayer, TileEntityExtractor tileEnt) {
        super.bindPlayerInventory(par1InventoryPlayer);
    	
    	this.tile = tileEnt;
    }
	
}
