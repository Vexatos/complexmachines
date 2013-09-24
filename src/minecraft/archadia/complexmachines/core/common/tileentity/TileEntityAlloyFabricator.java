package archadia.complexmachines.core.common.tileentity;

import net.minecraft.item.ItemStack;
import archadia.complexmachines.helper.recipes.AlloyRecipes;
import archadia.complexmachines.prefab.tileentity.TileEntityAdvancedMachine;

/**
 * @author Archadia
 *
 */
public class TileEntityAlloyFabricator extends TileEntityAdvancedMachine {
		
	public TileEntityAlloyFabricator() {
		setInventorySize(5);
		setMaxTicks(200);
	}
	
	public boolean isInvNameLocalized() {
		return true;
	}
	
	public String getInvName() {
		return "Alloy Fab";
	}
}
  