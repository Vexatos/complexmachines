package com.archadia.complexmachines.core.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.prefab.network.PacketManager;

import com.archadia.complexmachines.core.common.ComplexMachines;
import com.archadia.complexmachines.helper.recipes.MachineRecipes;
import com.archadia.complexmachines.prefab.te.TileElectricMachine;
import com.google.common.io.ByteArrayDataInput;

/**
 * @author Archadia
 *
 */
public class TileEntityWireMill extends TileElectricMachine {

	public TileEntityWireMill() {
		setInventorySize(2);
		setMaxTicks(200);
	}
	
	public void updateEntity() {
		if (!this.worldObj.isRemote)
        {
            if (this.canProcess() && getEnergyStored() >= 5000)
            {
                System.out.println("Node 1");
            	this.processTicks++;
            	
                if (this.processTicks == getMaxTicks())
                {
                	this.processTicks = 0;
                    processItems();
                    setEnergyStored(getEnergyStored() - 2250);
                }
            } else {
            	this.processTicks = 0;
            }
            System.out.println("Energy Stored: " + getEnergyStored());
            System.out.println("Ticks: " + getTicks());
        }
 
	}
	
    public int getProcessProgressScaled(int par1) {   
    	return this.processTicks * par1 / 200;
    }
	
	public String getInvName() {
		return "Wire Mill";
	}
	
	public boolean canProcess() {
		if (inventory[0] == null) {
        	return false;
        } else {
            ItemStack itemstack = MachineRecipes.Recipe.WIREMILL.getResult(inventory[0].itemID);
            System.out.println("ItemStack: " + itemstack);
            if (itemstack == null) {
            	return false;
            }
            if (inventory[1] == null) {
            	return true;
            }
            if (!inventory[1].isItemEqual(itemstack)) { 
            	return false;
            }
            int result = inventory[1].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }
	
	public void processItems() {
		if(canProcess()) {
            ItemStack itemstack = MachineRecipes.Recipe.WIREMILL.getResult(inventory[0].itemID);
	
	        if (inventory[1] == null)
	        {
	        	inventory[1] = itemstack.copy();
	        }
	        else if (inventory[1].isItemEqual(itemstack))
	        {
	        	inventory[1].stackSize += itemstack.stackSize;
	        } 
	
	        --inventory[0].stackSize;
	
	        if (inventory[0].stackSize <= 0)
	        {
	            inventory[0] = null;
	        }
		}
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
	
	@Override
	public float getRequest(ForgeDirection direction) {
		return 2250;
	}

	@Override
	public float getProvide(ForgeDirection direction) {
		return 0;
	}

	@Override
	public float getMaxEnergyStored() {
		return 12500;
	}
	
}
