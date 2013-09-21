package archadia.complexmachines.common.tileentity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import archadia.complexmachines.common.ComplexMachines;

/**
 * @author Archadia
 *
 */
public class TileEntityAlloyFabricator extends TileEntityBasicMachine {
	
	public int waitingTime;
	
	public TileEntityAlloyFabricator() {
		inventory = new ItemStack[5];
	}
	
	public void updateEntity() {
		if(!worldObj.isRemote) {
			if(inventory[0] != null) {
				if(checkInput(0, ComplexMachines.ingotCopper, 9) && checkInput(1, Item.ingotIron, 1)) {
					helper.println("Node 1!");
					setOutput(4, new ItemStack(ComplexMachines.C194), 2);
					decrStackSize(0, 9);
					decrStackSize(1, 1);
				}
			}
		}
	}
}
  