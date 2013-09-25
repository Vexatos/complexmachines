package archadia.complexmachines.core.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import archadia.complexmachines.core.common.slot.SlotOutput;
import archadia.complexmachines.core.common.tileentity.TileEntityAlloyFabricator;
import archadia.complexmachines.prefab.container.ContainerBasicMachine;
import archadia.complexmachines.prefab.tileentity.TileEntityAdvancedMachine;

/**
 * @author Archadia
 *
 */
public class ContainerAlloyFabricator extends ContainerBasicMachine {
    private TileEntityAlloyFabricator fabricator;

    public ContainerAlloyFabricator(InventoryPlayer par1InventoryPlayer, TileEntityAdvancedMachine tile) {
        super.bindPlayerInventory(par1InventoryPlayer);
    	
    	this.fabricator = (TileEntityAlloyFabricator) tile;
        
        this.addSlotToContainer(new Slot(tile, 0, 43, 13));
        this.addSlotToContainer(new Slot(tile, 1, 62, 13));
        this.addSlotToContainer(new Slot(tile, 2, 81, 13));
        this.addSlotToContainer(new Slot(tile, 3, 43, 57));
        this.addSlotToContainer(new SlotOutput(tile, 4, 128, 33));
    }
}