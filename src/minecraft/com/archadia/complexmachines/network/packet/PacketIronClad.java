package com.archadia.complexmachines.network.packet;

import com.archadia.complexmachines.core.common.tileentity.TileEntityIronClad;
import com.archadia.complexmachines.prefab.tileentity.ElectricContainer;

/**
 * @author Archadia
 *
 */
public class PacketIronClad extends PacketElectricProcessor {
	
	 public PacketIronClad(TileEntityIronClad wiremill) {
		 super((ElectricContainer) wiremill);
	 }

	 public PacketIronClad() {
		 super();
	 }
}
