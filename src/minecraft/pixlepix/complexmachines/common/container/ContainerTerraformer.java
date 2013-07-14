package pixlepix.complexmachines.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import pixlepix.complexmachines.common.tileentity.MotorTileEntity;
import pixlepix.complexmachines.common.tileentity.TerraformerTileEntity;

public class ContainerTerraformer extends Container {
	private TerraformerTileEntity tileEntity;

	public ContainerTerraformer(InventoryPlayer par1InventoryPlayer, TerraformerTileEntity tileEntity) {
		this.tileEntity = tileEntity;
		// this.addSlotToContainer(new Slot(tileEntity, 0, 55, 25)); // To be
		// drawn into wire

		int var3;

		for (var3 = 0; var3 < 3; ++var3) {
			for (int var4 = 0; var4 < 9; ++var4) {
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var4+ var3 * 9 + 9,8 + var4 * 18, 84 + var3 * 18));
			}
		}
		for (var3 = 0; var3 < 9; ++var3) {
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var3,8 + var3 * 18, 142));
		}

			this.addSlotToContainer(new Slot(tileEntity, 0, 7,4));
			this.addSlotToContainer(new Slot(tileEntity, 1, 7,22));

			this.addSlotToContainer(new Slot(tileEntity, 2, 7,40));
			this.addSlotToContainer(new Slot(tileEntity, 3, 7,58));
			

			this.addSlotToContainer(new Slot(tileEntity, 4, 45,4));
			this.addSlotToContainer(new Slot(tileEntity, 5, 44,58));

			this.addSlotToContainer(new Slot(tileEntity, 6, 121,4));
			this.addSlotToContainer(new Slot(tileEntity, 7, 121,22));

			this.addSlotToContainer(new Slot(tileEntity, 8, 121,40));
			
		

		tileEntity.openChest();
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return this.tileEntity.isUseableByPlayer(par1EntityPlayer);
	}

	/**
	 * Called to transfer a stack from one inventory to the other eg. when shift
	 * clicking.
	 */

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            
            if (par2 < 36)
            {
                if (!this.mergeItemStack(itemstack1, 36, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else 
            if (!this.mergeItemStack(itemstack1, 0, 36, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
	}

}