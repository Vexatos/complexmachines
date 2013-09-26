package archadia.complexmachines.api;

import net.minecraft.item.ItemStack;

/**
 * @author Archadia
 * DONT USE!
 */
public interface IComplexAdvancedMachine {
	
	public void setMaxTicks(int amt);
	
	public int getMaxTicks();

	public void setTicks(int ticks);
	
	public int getTicks();
	
	public void setInventorySize(int amt);
}
