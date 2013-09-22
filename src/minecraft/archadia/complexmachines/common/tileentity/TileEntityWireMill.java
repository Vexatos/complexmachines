package archadia.complexmachines.common.tileentity;

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
}
