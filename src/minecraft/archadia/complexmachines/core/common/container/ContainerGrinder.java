package archadia.complexmachines.core.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import archadia.complexmachines.core.common.slot.SlotOutput;
import archadia.complexmachines.core.common.tileentity.TileEntityGrinder;
import archadia.complexmachines.core.common.tileentity.TileEntityWireMill;
import archadia.complexmachines.prefab.container.ContainerBasicMachine;
import archadia.complexmachines.prefab.tileentity.TileEntityAdvancedMachine;

/**
 * @author Archadia
 *
 */
public class ContainerGrinder extends ContainerBasicMachine {
	
	private TileEntityGrinder tile;

    public ContainerGrinder(InventoryPlayer par1InventoryPlayer, TileEntityAdvancedMachine tile2) {
    	super.bindPlayerInventory(par1InventoryPlayer);
    	this.tile = (TileEntityGrinder) tile2;
        
        addSlotToContainer(new Slot(tile2, 0, 8, 8));
        addSlotToContainer(new Slot(tile2, 1, 43, 8));
        addSlotToContainer(new Slot(tile2, 2, 78, 8));
    }

}
