package archadia.complexmachines.network.packet;

import ljdp.easypacket.EasyPacketData;
import archadia.complexmachines.core.common.tileentity.TileEntityWireMill;
import archadia.complexmachines.prefab.tileentity.ElectricContainer;
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
