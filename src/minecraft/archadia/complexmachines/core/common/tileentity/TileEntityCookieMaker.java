package archadia.complexmachines.core.common.tileentity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import archadia.complexmachines.prefab.tileentity.ElectricConsumerMachine;

/**
 * @author Archadia
 *
 */
public class TileEntityCookieMaker extends ElectricConsumerMachine {
	
	private final static TileEntityCookieMaker tileEntityBase = new TileEntityCookieMaker();   
	 	
	public final static TileEntityCookieMaker instance() {
		return tileEntityBase;
	}
	 
	public TileEntityCookieMaker() {
		setInventorySize(4);
		setMaxTicks(40);
	}
	
	public void updateEntity() {
		boolean flag1 = false;
        if (!this.worldObj.isRemote)
        {
            if (this.canProcess())
            {
            	this.processTicks++;
    	    	
                if (this.processTicks == getMaxTicks())
                {
                	this.processTicks = 0;
                    processItems();
                    flag1 = true;
                }
            } else {
            	this.processTicks = 0;
            }
        }
        if (flag1)
        {
            this.onInventoryChanged();
        }
	}
	
	public int getProcessProgressScaled(int par1) {   
		return this.processTicks * par1 / 200;
	}
	
	public String getInvName() {
		return "Cookie Maker";
	}
	
	public boolean canProcess() {
		if(inventory[0] == null || inventory[1] == null || inventory[2] == null) {
			return false;
		}
		if(inventory[0].itemID == Item.wheat.itemID && inventory[0].stackSize == 2) {
			if(inventory[1].itemID == Item.dyePowder.itemID) {
				if(inventory[2].itemID == Item.bucketMilk.itemID) {
					ItemStack itemstack = new ItemStack(Item.cookie);
		            if (inventory[3] == null) {
		            	return true;
		            }
		            if (!inventory[3].isItemEqual(itemstack)) { 
		            	return false;
		            }
		            int result = inventory[3].stackSize + itemstack.stackSize;
		            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
				}
			}
		}
		return false;
	}
	
	public void processItems() {
		if(canProcess()) {
            ItemStack itemstack = new ItemStack(Item.cookie, 8);
	
	        if (inventory[3] == null)
	        {
	        	inventory[3] = itemstack.copy();
	        }
	        else if (inventory[3].isItemEqual(itemstack))
	        {
	        	inventory[3].stackSize += itemstack.stackSize;
	        } 
	
	        if(inventory[0].itemID == Item.wheat.itemID) {
	        	inventory[0].stackSize -= 2;
	        } else {
		        --inventory[0].stackSize;
	        }
	
	        if (inventory[0].stackSize <= 0)
	        {
	            inventory[0] = null;
	        }
	        
	        --inventory[1].stackSize;
	    	
	        if (inventory[1].stackSize <= 0)
	        {
	            inventory[1] = null;
	        }
	        
	        --inventory[2].stackSize;
	    	
	        if (inventory[2].stackSize <= 0)
	        {
	            inventory[2] = null;
	        }
		}
	}
}
