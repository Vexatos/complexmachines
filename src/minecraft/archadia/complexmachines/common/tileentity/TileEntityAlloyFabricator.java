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
public class TileEntityAlloyFabricator extends TileEntityBasicMachine {
	
	public TileEntityAlloyFabricator() {
		inventory = new ItemStack[5];
	}
	
	public void updateEntity() {
		if(!worldObj.isRemote) {
			if(inventory[0] != null) {
				if(checkInput(0, ComplexMachines.ingotCopper, 9) && checkInput(1, Item.ingotIron, 1)) {
					nullSlot(0);
					nullSlot(1);
					setOutput(4, new ItemStack(ComplexMachines.C194), 2);
				}
			}
		}
	}
}
  