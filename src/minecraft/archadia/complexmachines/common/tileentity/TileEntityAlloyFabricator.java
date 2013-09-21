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
					setOutput(new ItemStack(ComplexMachines.C194), 4, 2);
				}
			}
		}
	}
}
  