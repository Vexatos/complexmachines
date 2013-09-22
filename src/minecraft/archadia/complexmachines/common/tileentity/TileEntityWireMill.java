package archadia.complexmachines.common.tileentity;

import archadia.complexmachines.common.helper.recipes.AlloyRecipes;
import archadia.complexmachines.common.helper.recipes.WiremillRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Archadia
 *
 */
public class TileEntityWireMill extends TileEntityBasicMachine {
	
	public TileEntityWireMill() {
		inventory = new ItemStack[2];
	}
	
	public void updateEntity() {
		if(!worldObj.isRemote) {
			if(canProcess()) {
				processItems();
			}
		}
	}
	
	public String getInvName() {
		return "Wire Mill";
	}
	
	public boolean canProcess() {
		if (inventory[0] == null) {
        	return false;
        } else {
            ItemStack itemstack =  WiremillRecipes.recipes().getResult(inventory[0].itemID);
            if (itemstack == null) {
            	return false;
            }
            if (inventory[1] == null) {
            	return true;
            }
            if (!inventory[1].isItemEqual(itemstack)) { 
            	return false;
            }
            int result = inventory[1].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }
	
	public void processItems() {
		if(canProcess()) {
            ItemStack itemstack =  WiremillRecipes.recipes().getResult(inventory[0].itemID);
	
	        if (inventory[1] == null)
	        {
	        	inventory[1] = itemstack.copy();
	        }
	        else if (inventory[1].isItemEqual(itemstack))
	        {
	        	inventory[1].stackSize += itemstack.stackSize;
	        }
	
	        --inventory[0].stackSize;
	
	        if (inventory[0].stackSize <= 0)
	        {
	            inventory[0] = null;
	        }
		}
	}
}
