package archadia.complexmachines.prefab.tileentity.insulator;

import net.minecraft.item.ItemStack;

/**
 * @author Archadia
 *
 */
public class AdvancedInsulatorTileEntity extends InsulatorContainerTileEntity {

	protected int processTicks;
	protected static int processMaxTicks;
	protected boolean canOperate = false;
	
	public void setMaxTicks(int amt) {
		processMaxTicks = amt;
	}
	
	public int getMaxTicks() {
		return processMaxTicks;
	}

	public int getTicks() {
		return processTicks;
	}
	
	public void setStatusMode(boolean bool) {
		canOperate = bool;
	}
	
	public boolean getStatusMode() {
		return canOperate;
	}
	
	public void setInventorySize(int amt) {
		inventory = new ItemStack[amt];
	}
}
