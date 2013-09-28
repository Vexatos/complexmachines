package archadia.complexmachines.network.packet;

import archadia.complexmachines.core.common.tileentity.TileEntityExtractor;
import archadia.complexmachines.core.common.tileentity.TileEntityWireMill;
import archadia.complexmachines.prefab.tileentity.ElectricContainer;

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
