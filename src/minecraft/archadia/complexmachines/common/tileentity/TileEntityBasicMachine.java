package archadia.complexmachines.common.tileentity;

import archadia.complexmachines.common.helper.ArchHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * @author Archadia
 *
 */
public class TileEntityBasicMachine extends TileEntityBasicContainer {

	public boolean canProcess() {
		return false;
	}
	
	public void nullSlot(int slot) {
		inventory[slot] = null;
	}
	
	public void splitSlot(int slot, int amt) {
		if(!worldObj.isRemote) {
			if(inventory[slot] != null) {
				inventory[slot].splitStack(amt);
			}
		}
	}
	
	public boolean checkInput(int slot, Item item, int size) {
		if(inventory[slot] != null) {
			if(inventory[slot].itemID == item.itemID) {
				if(inventory[slot].stackSize == size) {
					return true;
				}
			}
		}
		return false;
	}	
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length)
            {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);
    }

	
	public void setOutput(int slot, ItemStack input, int amt) {
		if(!worldObj.isRemote) {
			if(inventory[slot] != null) {
				inventory[slot].stackSize += amt;
			}
			if(inventory[slot] == null) {
				inventory[slot] = input;
				inventory[slot].stackSize = amt;
			}
		}
	}
	
}
