package com.archadia.complexmachines.core.common.tileentity;

import com.archadia.complexmachines.helper.ArchHelper;
import com.archadia.complexmachines.helper.recipes.MachineRecipes;
import com.archadia.complexmachines.network.PacketHandler;
import com.archadia.complexmachines.network.packet.PacketIronClad;
import com.archadia.complexmachines.network.packet.PacketWireMill;
import com.archadia.complexmachines.prefab.tileentity.ElectricProducerMachine;

import net.minecraft.item.ItemStack;

/**
 * @author Archadia
 *
 */
public class TileEntityIronClad extends ElectricProducerMachine {
	
	private final static TileEntityIronClad tileEntityBase = new TileEntityIronClad();   
	 	
	public int heat = 1000;
	
	public final static TileEntityIronClad instance() {
		return tileEntityBase;
	}
	 
	public TileEntityIronClad() {
		setInventorySize(2);
		setMaxTicks(200);
	}
	
	public void updateEntity() {
		if(worldObj.getWorldTime()%20==0) {
		   worldObj.spawnParticle("smoke", this.xCoord + 0.25, this.yCoord + 1.2, this.zCoord + 0.25, 0.0D, 0.05D, 0.0D);
		   worldObj.spawnParticle("smoke", this.xCoord + 0.25, this.yCoord + 1.2, this.zCoord + 0.25, 0.0D, 0.05D, 0.0D);
		   worldObj.spawnParticle("smoke", this.xCoord + 0.25, this.yCoord + 1.2, this.zCoord + 0.25, 0.0D, 0.05D, 0.0D);
		   
		   worldObj.spawnParticle("smoke", this.xCoord + 0.75, this.yCoord + 1.2, this.zCoord + 0.25, 0.0D, 0.05D, 0.0D);
		   worldObj.spawnParticle("smoke", this.xCoord + 0.75, this.yCoord + 1.2, this.zCoord + 0.25, 0.0D, 0.05D, 0.0D);
		   worldObj.spawnParticle("smoke", this.xCoord + 0.75, this.yCoord + 1.2, this.zCoord + 0.25, 0.0D, 0.05D, 0.0D);

		   worldObj.spawnParticle("smoke", this.xCoord + 0.25, this.yCoord + 1.2, this.zCoord + 0.75, 0.0D, 0.05D, 0.0D);
		   worldObj.spawnParticle("smoke", this.xCoord + 0.25, this.yCoord + 1.2, this.zCoord + 0.75, 0.0D, 0.05D, 0.0D);
		   worldObj.spawnParticle("smoke", this.xCoord + 0.25, this.yCoord + 1.2, this.zCoord + 0.75, 0.0D, 0.05D, 0.0D);
		   
		   worldObj.spawnParticle("smoke", this.xCoord + 0.75, this.yCoord + 1.2, this.zCoord + 0.75, 0.0D, 0.05D, 0.0D);
		   worldObj.spawnParticle("smoke", this.xCoord + 0.75, this.yCoord + 1.2, this.zCoord + 0.75, 0.0D, 0.05D, 0.0D);
		   worldObj.spawnParticle("smoke", this.xCoord + 0.75, this.yCoord + 1.2, this.zCoord + 0.75, 0.0D, 0.05D, 0.0D);
		}
	}

	
    public int getProcessProgressScaled(int par1) {   
    	return this.processTicks * par1 / 200;
    }
	
	public String getInvName() {
		return "Wire Mill";
	}
	
    public void sendUpdatePacket() {
        PacketIronClad packet = new PacketIronClad(this);
        int dimensionID = worldObj.provider.dimensionId;
        PacketHandler.instance().ironCladUpdateHandler.sendToAllPlayersInDimension(packet, dimensionID);
    }
}
