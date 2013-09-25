package archadia.complexmachines.core.common.tileentity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import archadia.complexmachines.api.ExtractorHelper;
import archadia.complexmachines.core.common.ComplexMachines;
import archadia.complexmachines.prefab.tileentity.TileEntityAdvancedMachine;

/**
 * @author Archadia
 *
 */
public class TileEntityExtractor extends TileEntityAdvancedMachine {
	
	private final static TileEntityExtractor tileEntityBase = new TileEntityExtractor();   
 	
	private Random rand = new Random();
		
	public void addExtractorVanillaOre() {
		ExtractorHelper.instance().addExtractorValidOre(Block.oreCoal.blockID);
		ExtractorHelper.instance().addExtractorValidOre(Block.oreIron.blockID);
		ExtractorHelper.instance().addExtractorValidOre(Block.oreGold.blockID);
		ExtractorHelper.instance().addExtractorValidOre(Block.oreRedstone.blockID);
		ExtractorHelper.instance().addExtractorValidOre(Block.oreLapis.blockID);
	}
	
	public final static TileEntityExtractor instance() {
		return tileEntityBase;
	}
	 
	public TileEntityExtractor() {
		setInventorySize(8);
		setMaxTicks(200);
		addExtractorVanillaOre();
	}
	
	public void updateEntity() {
		super.updateEntity();

		if(!worldObj.isRemote) {
			if(inventory[7] != null) {
				if(inventory[7].itemID == Item.pickaxeDiamond.itemID) {
					if(ComplexMachines.oldExtractorMode) {
						findOre();
					} else {
						if(worldObj.getWorldTime()%20 == 0) findOre();
					}
				}
			}
		}
	}
	
	private void findOre() {
		boolean oreFound = false;
		int tries = 0;
		while(!oreFound) {
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
				inventory[7].setItemDamage(inventory[7].getItemDamage() + 10);
				ItemStack drop = Block.blocksList[targetId].getBlockDropped(worldObj, targetX, targetY , targetZ, worldObj.getBlockMetadata(targetX, targetY, targetZ), 0).get(0);
				worldObj.setBlock(targetY, targetY, targetZ, 0);
				dropItems(drop);
			}
		}
	}
	
	private boolean isOre(int id) {
		for(int oreID : ExtractorHelper.instance().getValidOres()) {
			if(id == oreID) {
				return true;
			}
		}
		return false;
	}
	
	private TileEntityChest findChest() {

		TileEntity entityBeingChecked = worldObj.getBlockTileEntity(xCoord,
				yCoord, zCoord);
		if (worldObj.getBlockTileEntity(xCoord + 1, yCoord, zCoord) instanceof TileEntityChest) {
			return (TileEntityChest) worldObj.getBlockTileEntity(xCoord + 1,
					yCoord, zCoord);
		}
		if (worldObj.getBlockTileEntity(xCoord - 1, yCoord, zCoord) instanceof TileEntityChest) {
			return (TileEntityChest) worldObj.getBlockTileEntity(xCoord - 1,
					yCoord, zCoord);
		}
		if (worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntityChest) {
			return (TileEntityChest) worldObj.getBlockTileEntity(xCoord,
					yCoord + 1, zCoord);
		}
		if (worldObj.getBlockTileEntity(xCoord, yCoord - 1, zCoord) instanceof TileEntityChest) {
			return (TileEntityChest) worldObj.getBlockTileEntity(xCoord,
					yCoord - 1, zCoord);
		}
		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord + 1) instanceof TileEntityChest) {
			return (TileEntityChest) worldObj.getBlockTileEntity(xCoord,
					yCoord, zCoord + 1);
		}
		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord - 1) instanceof TileEntityChest) {
			return (TileEntityChest) worldObj.getBlockTileEntity(xCoord,
					yCoord, zCoord - 1);
		}
		return null;
	}
	
	private void dropItems(ItemStack item) {
		ItemStack itemStack = item;

		if(itemStack != null) {
			for (int i = 0; i < this.inventory.length;i++) {
				if(findChest() == null) {
					itemStack = this.addStackToInventory(i, this, itemStack);
				} else {
					itemStack = this.addStackToInventory(i, findChest(), itemStack);
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
}
