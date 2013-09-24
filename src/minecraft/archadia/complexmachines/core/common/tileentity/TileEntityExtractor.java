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
public class TileEntityExtractor extends TileEntityAdvancedMachine {
	
	private final static TileEntityExtractor tileEntityBase = new TileEntityExtractor();   
 	
	public final static TileEntityExtractor instance() {
		return tileEntityBase;
	}
	 
	public TileEntityExtractor() {
		setInventorySize(0);
		setMaxTicks(200);
	}
	
	public void updateEntity() {
		
	}
	
    public int getProcessProgressScaled(int par1) {   
    	return this.processTicks * par1 / 200;
    }
	
	public String getInvName() {
		return "Extractor";
	}

}
