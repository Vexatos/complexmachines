package archadia.complexmachines.common.tileentity;

import net.minecraft.item.ItemStack;

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
		if(inventory[slot] != null) {
			if(inventory[slot].stackSize <= 1) {
				inventory[slot] = null;
			}
			if(inventory[slot].stackSize > 1) {
				inventory[slot].splitStack(amt);
			}
		}
	}
	
	public void setOutput(ItemStack input, int slot, int amt) {
		if(inventory[slot] != null) {
			inventory[slot].stackSize += amt;
		}
		if(inventory[slot] == null) {
			inventory[slot] = input;
			inventory[slot].stackSize = amt;
		}
	}
	
}
