package archadia.complexmachines.core.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import archadia.complexmachines.core.common.tileentity.TileEntityExtractor;
import archadia.complexmachines.core.common.tileentity.TileEntityIronClad;
import archadia.complexmachines.prefab.container.ContainerBasicMachine;

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
