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

/**
 * @author Archadia
 *
 */
public class ContainerGrinder extends ContainerBasicMachine {
	
	private TileEntityGrinder tile;

    public ContainerGrinder(InventoryPlayer par1InventoryPlayer, TileEntityGrinder tileEnt) {
    	super.bindPlayerInventory(par1InventoryPlayer);
    	this.tile = tileEnt;
        
        addSlotToContainer(new Slot(tileEnt, 0, 8, 8));
        addSlotToContainer(new Slot(tileEnt, 1, 43, 8));
        addSlotToContainer(new Slot(tileEnt, 2, 78, 8));
    }

}
