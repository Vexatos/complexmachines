package archadia.complexmachines.common.tileentity;

import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import archadia.complexmachines.common.ComplexMachines;
import archadia.complexmachines.common.helper.ArchHelper;
import archadia.complexmachines.common.helper.MechRecipes.Recipe;

/**
 * @author Archadia
 *
 */
public class TileEntityAlloyFabricator extends TileEntityBasicContainer {
	
	public TileEntityAlloyFabricator() {
		inventory = new ItemStack[5];
	}
	
	@Override
	public String getInvName() {
        return null;
	}
	
	public boolean isInvNameLocalized() {
		return true;
	}
	
	public void updateEntity() {
		if(!worldObj.isRemote) {
			if(inventory[0] != null) {
				ArchHelper.println("Node 1!");
				if(inventory[0].itemID == ComplexMachines.ingotCopper.itemID && inventory[0].stackSize == 9) {
					ArchHelper.println("Node 2!");
					nullSlot(0);
					nullSlot(1);
					setOutput(new ItemStack(ComplexMachines.C194), 2);
				}
			}
		}
	}
	
	public boolean canProcess() {
		return false;
	}
	
	public void nullSlot(int slot) {
		inventory[slot] = null;
	}
	
	public void splitSlot(int slot, int amt) {
		if(inventory[slot] != null) {
			if(inventory[slot].stackSize <= 1) {
				inventory[slot] = null;
			}
			if(inventory[slot].stackSize > 1) {
				inventory[slot].splitStack(amt);
			}
		}
	}
	
	public void setOutput(ItemStack input, int amt) {
		ItemStack item = new ItemStack(ComplexMachines.C194);
		if(inventory[4] != null) {
			inventory[4].stackSize += amt;
		}
		if(inventory[4] == null) {
			inventory[4] = item;
			inventory[4].stackSize = amt;
		}
	}
}
  