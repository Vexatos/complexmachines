package com.archadia.complexmachines.core.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.prefab.network.PacketManager;

import com.archadia.complexmachines.core.common.ComplexMachines;
import com.archadia.complexmachines.prefab.te.TileElectricMachine;
import com.google.common.io.ByteArrayDataInput;

/**
 * @author Archadia
 *
 */
public class TileEntityIronClad extends TileElectricMachine {
	
	private final static TileEntityIronClad tileEntityBase = new TileEntityIronClad();   
	 		
	public final static TileEntityIronClad instance() {
		return tileEntityBase;
	}
	 
	public TileEntityIronClad() {
		setInventorySize(2);
		setMaxTicks(200);
	}
	
	public void updateEntity() {

	}
	
    public int getProcessProgressScaled(int par1) {   
    	return this.processTicks * par1 / 200;
    }
	
	public String getInvName() {
		return "Iron Clad";
	}
	
	@Override
	public float getRequest(ForgeDirection direction) {
		return 0;
	}

	@Override
	public float getProvide(ForgeDirection direction) {
		return 0;
	}

	@Override
	public float getMaxEnergyStored() {
		return 10000;
	}

	@Override
	public Packet getDescriptionPacket() {
		return PacketManager.getPacket(ComplexMachines.CHANNEL, this, this.getEnergyStored());
	}

	@Override
	public void handlePacketData(INetworkManager network, int packetType,
			Packet250CustomPayload packet, EntityPlayer player,
			ByteArrayDataInput dataStream) {
		this.energyStored = dataStream.readInt();
	}
}
