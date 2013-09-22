package archadia.complexmachines.common.tileentity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import archadia.complexmachines.common.ComplexMachines;
import archadia.complexmachines.common.helper.ArchHelper;
import archadia.complexmachines.common.helper.recipes.AlloyRecipes;

/**
 * @author Archadia
 *
 */
public class TileEntityAlloyFabricator extends TileEntityBasicMachine {
		
	public TileEntityAlloyFabricator() {
		inventory = new ItemStack[5];
	}
	
	public boolean isInvNameLocalized() {
		return true;
	}
	
	public void updateEntity() {
		if(!worldObj.isRemote) {
			if(canMix()) {
				mixAlloy();
			}
		}
	}
	
	private boolean canMix() {
		if (inventory[0] == null || inventory[1] == null) {
        	return false;
        } else {
            ItemStack itemstack =  AlloyRecipes.alloy().getResult(inventory[0].itemID, inventory[1].itemID);
            if (itemstack == null) {
            	return false;
            }
            if (inventory[4] == null) {
            	return true;
            }
            if (!inventory[4].isItemEqual(itemstack)) { 
            	return false;
            }
            int result = inventory[4].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }
	
	public void mixAlloy() {
		if(canMix()) {
			ItemStack itemstack = AlloyRecipes.alloy().getResult(inventory[0].itemID, inventory[1].itemID);
	
	        if (inventory[4] == null)
	        {
	        	inventory[4] = itemstack.copy();
	        }
	        else if (inventory[4].isItemEqual(itemstack))
	        {
	        	inventory[4].stackSize += itemstack.stackSize;
	        }
	
	        --inventory[0].stackSize;
	
	        if (inventory[0].stackSize <= 0)
	        {
	            inventory[0] = null;
	        }
	        
	        --inventory[1].stackSize;
	
	        if (inventory[1].stackSize <= 0)
	        {
	            inventory[1] = null;
	        }
		}
	}
}
  