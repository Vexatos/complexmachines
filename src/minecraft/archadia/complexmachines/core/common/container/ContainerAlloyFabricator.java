package archadia.complexmachines.core.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import archadia.complexmachines.core.common.slot.SlotOutput;
import archadia.complexmachines.core.common.tileentity.TileEntityAlloyFabricator;
import archadia.complexmachines.prefab.container.ContainerBasicMachine;

/**
 * @author Archadia
 *
 */
public class ContainerAlloyFabricator extends ContainerBasicMachine {
    private TileEntityAlloyFabricator fabricator;

    public ContainerAlloyFabricator(InventoryPlayer par1InventoryPlayer, TileEntityAlloyFabricator tileEnt) {
        super.bindPlayerInventory(par1InventoryPlayer);
    	
    	this.fabricator = tileEnt;
        
        this.addSlotToContainer(new Slot(tileEnt, 0, 43, 13));
        this.addSlotToContainer(new Slot(tileEnt, 1, 62, 13));
        this.addSlotToContainer(new Slot(tileEnt, 2, 81, 13));
        this.addSlotToContainer(new Slot(tileEnt, 3, 43, 57));
        this.addSlotToContainer(new SlotOutput(tileEnt, 4, 128, 33));
    }
}