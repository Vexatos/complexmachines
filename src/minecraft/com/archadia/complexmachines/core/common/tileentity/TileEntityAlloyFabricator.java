package com.archadia.complexmachines.core.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.archadia.complexmachines.prefab.te.TileElectricMachine;
import com.google.common.io.ByteArrayDataInput;



/**
 * @author Archadia
 *
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
  