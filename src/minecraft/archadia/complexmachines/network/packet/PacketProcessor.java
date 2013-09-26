package archadia.complexmachines.network.packet;

import ljdp.easypacket.EasyPacketData;
import archadia.complexmachines.core.common.tileentity.TileEntityWireMill;
import archadia.complexmachines.prefab.tileentity.ElectricContainer;
import cpw.mods.fml.common.network.Player;

/**
 * @author Archadia
 *
 */
public class PacketProcessor extends PacketTileEntity {

	 protected ElectricContainer tile;

	 @EasyPacketData
	 int processTicks;

	 public PacketProcessor(ElectricContainer tileEntity) {
		 super(tileEntity);
		 processTicks = tileEntity.processTicks;
	 }
	 
	 public PacketProcessor() {
		 super();
	 }

	 @Override
	 public boolean isChunkDataPacket() {
		 return super.isChunkDataPacket();
	 }

	 @Override
	 public void onReceive(Player player) {
		 super.onReceive(player);
		 if (this.tileEntity instanceof ElectricContainer) {
			 this.tile = (ElectricContainer) this.tileEntity;
			 this.tile.setTicks(this.processTicks);
		 }
	 }    
}
