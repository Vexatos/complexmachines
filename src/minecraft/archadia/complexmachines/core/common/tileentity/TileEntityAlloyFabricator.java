package archadia.complexmachines.core.common.tileentity;

import archadia.complexmachines.prefab.tileentity.ElectricConsumerMachine;


/**
 * @author Archadia
 *
 */
public class TileEntityAlloyFabricator extends ElectricConsumerMachine {
		
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
  