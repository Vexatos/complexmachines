package com.archadia.complexmachines.network.packet;

import com.archadia.complexmachines.core.common.tileentity.TileEntityWireMill;
import com.archadia.complexmachines.prefab.tileentity.ElectricContainer;

import ljdp.easypacket.EasyPacketData;
import cpw.mods.fml.common.network.Player;

/**
 * @author Archadia
 *
 */
public class PacketWireMill extends PacketTileEntity {

	 public PacketWireMill(TileEntityWireMill wiremill) {
		 super((ElectricContainer) wiremill);
	 }

	 public PacketWireMill() {
		 super();
	 }
}
