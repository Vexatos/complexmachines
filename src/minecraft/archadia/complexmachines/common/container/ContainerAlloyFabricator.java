package archadia.complexmachines.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import archadia.complexmachines.common.slot.SlotOutput;
import archadia.complexmachines.common.tileentity.TileEntityAlloyFabricator;

/**
 * @author Archadia
 *
 */
public class ContainerAlloyFabricator extends Container {
    private TileEntityAlloyFabricator fabricator;

    public ContainerAlloyFabricator(InventoryPlayer par1InventoryPlayer, TileEntityAlloyFabricator tileEnt) {
        this.fabricator = tileEnt;
        
        this.addSlotToContainer(new Slot(tileEnt, 0, 43, 13));
        this.addSlotToContainer(new Slot(tileEnt, 1, 62, 13));
        this.addSlotToContainer(new Slot(tileEnt, 2, 81, 13));
        
        this.addSlotToContainer(new Slot(tileEnt, 3, 43, 57));
        this.addSlotToContainer(new SlotOutput(tileEnt, 4, 128, 33));
        
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
        
    }
        
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
    	return null;
    }
}