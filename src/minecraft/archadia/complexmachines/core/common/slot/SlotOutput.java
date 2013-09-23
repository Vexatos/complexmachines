package archadia.complexmachines.core.common.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author Archadia
 *
 */
public class SlotOutput extends Slot {

	public SlotOutput(IInventory inv, int i, int j, int k) {
        super(inv, i, j, k);
	}
	public int getSlotStackLimit()
	{
	        return 1;
	}
	public boolean isItemValid(ItemStack itemstack)
	{
		return false;
	}
}
