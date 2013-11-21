package com.archadia.complexmachines.core.common.tileentity;

import java.util.HashSet;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
public class TileEntityExtractor extends TileElectricMachine {
	 		
	private Random rand = new Random();
			
	public TileEntityExtractor() {
		setInventorySize(8);
		setMaxTicks(200);
	}

	public void updateEntity() {
		super.updateEntity();

		if(!worldObj.isRemote) {
			if(findInventory() != null) {
				if(inventory[7] != null) {
					if(inventory[7].itemID == Item.pickaxeDiamond.itemID) {
						if(ComplexMachines.oldExtractorMode) {
							findOre();
						} else {
							if(worldObj.getWorldTime()%20 == 0) {
								findOre();
							}
						}
					}
				}
			}
		}
	}
	
	private void findOre() {
		boolean oreFound = false;
		int tries = 0;
		while (!oreFound && getEnergyStored() > 100000) {
			tries++;
			
			if(tries > 5) {
				return;
			}
			
			int targetX = xCoord - 150;
			int targetZ = zCoord - 150;
			int targetY = rand.nextInt(15) + 5;
	
			targetX += (rand.nextInt(300));
			
			targetZ += (rand.nextInt(300));
	
			int targetId = worldObj.getBlockId(targetX, targetY, targetZ);
			
			boolean ore = isOre(targetId);
			
			if (worldObj.getChunkFromBlockCoords(targetX, targetZ).isChunkLoaded && ore) {
				oreFound = true;
				setEnergyStored(getEnergyStored() - 100000);
				
				inventory[7].setItemDamage(inventory[7].getItemDamage() + ComplexMachines.extractorPickDegradeRate);
				if(inventory[7].getItemDamage() > inventory[7].getMaxDamage()) {
					inventory[7].setItemDamage(inventory[7].getMaxDamage());
				}
								
				if(inventory[7].getItemDamage() == 1561) {
					inventory[7] = null;
				}
				
				ItemStack drop = Block.blocksList[targetId].getBlockDropped(worldObj, targetX, targetY , targetZ, worldObj.getBlockMetadata(targetX, targetY, targetZ), 0).get(0);
				worldObj.setBlock(targetY, targetY, targetZ, 0);
				dropItems(drop);
			}
		}
	}
	
	private boolean isOre(int id) {
		return false;
	}
	
	private IInventory findInventory() {

		if (worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord) instanceof IInventory) {
			return (IInventory) worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord);
		}
		if (worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord) instanceof IInventory) {
			return (IInventory) worldObj.getBlockTileEntity(xCoord - 1,
					yCoord, zCoord);
		}
		if (worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord) instanceof IInventory) {
			return (IInventory) worldObj.getBlockTileEntity(xCoord,
					yCoord + 1, zCoord);
		}
		if (worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord) instanceof IInventory) {
			return (IInventory) worldObj.getBlockTileEntity(xCoord,
					yCoord - 1, zCoord);
		}
		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + 1) instanceof IInventory) {
			return (IInventory) worldObj.getBlockTileEntity(xCoord,
					yCoord, zCoord + 1);
		}
		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - 1) instanceof IInventory) {
			return (IInventory) worldObj.getBlockTileEntity(xCoord,
					yCoord, zCoord - 1);
		}
		return null;
	}
	
	private void dropItems(ItemStack item) {
		ItemStack itemStack = item;

		if(itemStack != null) {
			for (int i = 0; i < this.inventory.length;i++) {
				if(findInventory() != null) {
					itemStack = this.addStackToInventory(i, findInventory(), itemStack);
					return;
				}
			}
		}

	}

	public ItemStack addStackToInventory(int slotIndex, IInventory inventory, ItemStack itemStack)
	{
		if(itemStack != null) {
			if (inventory.getSizeInventory() > slotIndex)
			{
				ItemStack stackInInventory = inventory.getStackInSlot(slotIndex);
	
				if (stackInInventory == null)
				{
					inventory.setInventorySlotContents(slotIndex, itemStack);
					if (inventory.getStackInSlot(slotIndex) == null)
					{
						return itemStack;
					}
					return null;
				}
				if(stackInInventory.isItemEqual(itemStack) && stackInInventory.isStackable())
				{
					stackInInventory = stackInInventory.copy();
					int stackLim = Math.min(inventory.getInventoryStackLimit(), itemStack.getMaxStackSize());
					int rejectedAmount = Math.max((stackInInventory.stackSize + itemStack.stackSize) - stackLim, 0);
					stackInInventory.stackSize = Math.min(Math.max((stackInInventory.stackSize + itemStack.stackSize - rejectedAmount), 0), inventory.getInventoryStackLimit());
					itemStack.stackSize = rejectedAmount;
					inventory.setInventorySlotContents(slotIndex, stackInInventory);
				}
			}
	
			if (itemStack.stackSize <= 0)
			{
				return null;
			}
		}

		return itemStack;
	}

	public String getInvName() {
		return "Extractor";
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
		return 200000;
	}

	@Override
	public float getProvide(ForgeDirection direction) {
		return 0;
	}

	@Override
	public float getMaxEnergyStored() {
		return 200000;
	}
	
}
