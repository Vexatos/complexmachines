package com.archadia.complexmachines.network.packet;

import com.archadia.complexmachines.core.common.tileentity.TileEntityWireMill;
import com.archadia.complexmachines.prefab.tileentity.ElectricContainer;

import ljdp.easypacket.EasyPacketData;
import cpw.mods.fml.common.network.Player;

/**
 * @author Archadia
 *
 */
public class PacketElectricProcessor extends PacketTileEntity {

	 protected ElectricContainer tile;

	 @EasyPacketData
	 int processTicks;
	 
	 @EasyPacketData
	 double currentJoules;

	 public PacketElectricProcessor(ElectricContainer tileEntity) {
		 super(tileEntity);
		 processTicks = tileEntity.processTicks;
		 currentJoules = tileEntity.getEnergyStored();
	 }
	 
	 public PacketElectricProcessor() {
		 super();
	 }

	 @Override
	 public boolean isChunkDataPacket() {
		 return super.isChunkDataPacket();
	 }

	 public double getJoules() {
		 return currentJoules;
	 }
	 
	 @Override
	 public void onReceive(Player player) {
		 super.onReceive(player);
		 if (this.tileEntity instanceof ElectricContainer) {
			 this.tile = (ElectricContainer) this.tileEntity;
			 this.tile.setTicks(this.processTicks);
			 
			 this.tile.setEnergyStored((float) currentJoules);
		 }
	 }    
}
