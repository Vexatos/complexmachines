package com.archadia.complexmachines.core.common.tileentity;

import com.archadia.complexmachines.prefab.te.TileElectricMachine;

/**
 * @author Archadia
 *
 */
public class TileEntityIronClad extends TileElectricMachine {
	
	private final static TileEntityIronClad tileEntityBase = new TileEntityIronClad();   
	 		
	public final static TileEntityIronClad instance() {
		return tileEntityBase;
	}
	 
	public TileEntityIronClad() {
		setInventorySize(2);
		setMaxTicks(200);
	}
	
	public void updateEntity() {

	}
	
    public int getProcessProgressScaled(int par1) {   
    	return this.processTicks * par1 / 200;
    }
	
	public String getInvName() {
		return "Iron Clad";
	}
}
