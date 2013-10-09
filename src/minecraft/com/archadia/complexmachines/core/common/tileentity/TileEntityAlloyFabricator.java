package com.archadia.complexmachines.core.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;

import basicmachinery.api.tileentity.ElectricContainer;



/**
 * @author Archadia
 *
 */
public class TileEntityAlloyFabricator extends ElectricContainer {
		
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

	/* (non-Javadoc)
	 * @see universalelectricity.prefab.network.IPacketReceiver#handlePacketData(net.minecraft.network.INetworkManager, int, net.minecraft.network.packet.Packet250CustomPayload, net.minecraft.entity.player.EntityPlayer, com.google.common.io.ByteArrayDataInput)
	 */
	@Override
	public void handlePacketData(INetworkManager network, int packetType,
			Packet250CustomPayload packet, EntityPlayer player,
			ByteArrayDataInput dataStream) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see basicmachinery.api.tileentity.ElectricContainer#getDescriptionPacket()
	 */
	@Override
	public Packet getDescriptionPacket() {
		// TODO Auto-generated method stub
		return null;
	}
}
  