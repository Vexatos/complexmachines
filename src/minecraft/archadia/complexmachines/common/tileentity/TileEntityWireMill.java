package archadia.complexmachines.common.tileentity;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.BlockFurnace;
import net.minecraft.item.ItemStack;
import archadia.complexmachines.common.helper.ArchHelper;
import archadia.complexmachines.common.helper.recipes.WiremillRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Archadia
 *
 */
public class TileEntityWireMill extends TileEntityBasicMachine {
	
	public int processTime;
	
	private final static TileEntityWireMill tileEntityBase = new TileEntityWireMill();   
	 
	public final static TileEntityWireMill instance() {
		return tileEntityBase;
	}
	 
	public TileEntityWireMill() {
		inventory = new ItemStack[2];
	}
	
	public void updateEntity() {
        boolean flag1 = false;
        if (!this.worldObj.isRemote)
        {
            if (this.canProcess())
            {
                ArchHelper.println(""+processTime);
                ++processTime;

                if (processTime == 200)
                {
                	processTime = 0;
                    processItems();
                    flag1 = true;
                }
            } else {
            	processTime = 0;
            }
        }
        

        if (flag1)
        {
            this.onInventoryChanged();
        }
	}
	
    public int getProcessProgressScaled(int par1) {
    	return processTime * par1 / 200;
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
