package com.archadia.complexmachines.core.common.tileentity;

import com.archadia.complexmachines.prefab.te.TileElectricMachine;

/**
 * @author Archadia
 */
public class TileEntityAlloyFabricator extends TileElectricMachine {

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
