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
public class TileEntityGrinder extends TileElectricMachine {
	
	private final static TileEntityGrinder tileEntityBase = new TileEntityGrinder();   
	
	private static int processTicks; 
	
	public final static TileEntityGrinder instance() {
		return tileEntityBase;
	}
	
	public TileEntityGrinder() {
		setInventorySize(3);
		setMaxTicks(200);
	}
	
	public void updateEntity() {
		boolean flag1 = false;
        if (!this.worldObj.isRemote)
        {
        	if(isProcessing()) {
	            this.processTicks++;
				
	            if (this.processTicks == getMaxTicks()) {
	            	this.processTicks = 0;
	                processItems();
	                flag1 = true;
	            }
        	}
        }
        if (flag1)
        {
            this.onInventoryChanged();
        }
	}
	
	public int getProcessProgressScaled(int par1) {		
		return this.processTicks * par1 / 200;
	}
	
	public String getInvName() {
		return "Grinder";
	}
	
	public boolean isProcessing() {
		if(inventory[0] == null && inventory[1] == null && inventory[2] == null) {
			return false;
		}
		if(inventory[0] != null) {
			return true;
		}
		if(inventory[1] != null) {
			return true;
		}
		if(inventory[2] != null) {
			return true;
		}
		return false;
	}
	
	public void processItems() {
		if(inventory[0] != null) {
			--inventory[0].stackSize;
	    	
	        if (inventory[0].stackSize <= 0)
	        {
	            inventory[0] = null;
	        }
		}
		if(inventory[1] != null) {
			--inventory[1].stackSize;
	    	
	        if (inventory[1].stackSize <= 0)
	        {
	            inventory[1] = null;
	        }
		}
		if(inventory[2] != null) {
			--inventory[2].stackSize;
	    	
	        if (inventory[2].stackSize <= 0)
	        {
	            inventory[2] = null;
	        }
		}
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
