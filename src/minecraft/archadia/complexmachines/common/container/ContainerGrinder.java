package archadia.complexmachines.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import archadia.complexmachines.common.slot.SlotOutput;
import archadia.complexmachines.common.tileentity.TileEntityGrinder;
import archadia.complexmachines.common.tileentity.TileEntityWireMill;

/**
 * @author Archadia
 *
 */
public class ContainerGrinder extends Container {
	
	private TileEntityGrinder tile;

    public ContainerGrinder(InventoryPlayer par1InventoryPlayer, TileEntityGrinder tileEnt) {
        this.tile = tileEnt;
        
        addSlotToContainer(new Slot(tileEnt, 0, 8, 8));
        addSlotToContainer(new Slot(tileEnt, 1, 43, 8));
        addSlotToContainer(new Slot(tileEnt, 2, 78, 8));
        
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
