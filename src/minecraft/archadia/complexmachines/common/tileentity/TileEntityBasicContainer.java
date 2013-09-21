package archadia.complexmachines.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import archadia.complexmachines.common.helper.ArchHelper;

/**
 * @author Archadia
 *
 */
public class TileEntityBasicContainer extends TileEntity implements IInventory {

	protected ArchHelper helper = new ArchHelper();
	
	protected ItemStack[] inventory;
    
	public static int playersUsing;
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.inventory[i];
	}

	@Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (slot >= 0 && slot < inventory.length) {
            ItemStack itemstack = inventory[slot];
            inventory[slot] = null;
            return itemstack;
        } else {
            return null;
        }
    }
	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.inventory[i] != null) {
            ItemStack itemstack = this.inventory[i];
            this.inventory[i] = null;
            return itemstack;
        } else {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		 this.inventory[i] = itemstack;

	        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
	        {
	        	itemstack.stackSize = this.getInventoryStackLimit();
	        }
	}

	@Override
	public String getInvName() {
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {
		playersUsing++;
	}

	@Override
	public void closeChest() {
		playersUsing--;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}
}
