package archadia.complexmachines.core.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import archadia.complexmachines.core.common.tileentity.TileEntityExtractor;
import archadia.complexmachines.prefab.container.ContainerBasicMachine;

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
    	addSlotToContainer(new Slot(tile2, 8, 8, 34));
    	addSlotToContainer(new Slot(tile2, 9, 8, 52));
    }
	
}
