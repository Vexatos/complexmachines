package com.archadia.complexmachines.network.packet;

import com.archadia.complexmachines.core.common.tileentity.TileEntityExtractor;
import com.archadia.complexmachines.core.common.tileentity.TileEntityWireMill;
import com.archadia.complexmachines.prefab.tileentity.ElectricContainer;

/**
 * @author Archadia
 *
 */
public class PacketExtractor extends PacketElectricProcessor {
	
	 public PacketExtractor(TileEntityExtractor wiremill) {
		 super((ElectricContainer) wiremill);
	 }

	 public PacketExtractor() {
		 super();
	 }
}
