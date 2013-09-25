package archadia.complexmachines.core.common.tileentity;

import net.minecraft.item.ItemStack;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.network.PacketManager;
import archadia.complexmachines.helper.recipes.WiremillRecipes;
import archadia.complexmachines.prefab.tileentity.TileEntityAdvancedMachine;

/**
 * @author Archadia
 *
 */
public class TileEntityCookieMaker extends TileEntityAdvancedMachine {
	
	private final static TileEntityCookieMaker tileEntityBase = new TileEntityCookieMaker();   
	 	
	public final static TileEntityCookieMaker instance() {
		return tileEntityBase;
	}
	 
	public TileEntityCookieMaker() {
		setInventorySize(2);
		setMaxTicks(200);
	}
	
	public void updateEntity() {

	}
	
	public int getProcessProgressScaled(int par1) {   
		return this.processTicks * par1 / 200;
	}
	
	public String getInvName() {
		return "Cookie Maker";
	}
	
	public boolean canProcess() {
		return false;
	}
	
	public void processItems() {
		
	}
}
