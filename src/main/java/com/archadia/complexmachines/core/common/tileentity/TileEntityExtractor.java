package com.archadia.complexmachines.core.common.tileentity;

import com.archadia.complexmachines.core.common.ComplexMachines;
import com.archadia.complexmachines.prefab.te.TileElectricMachine;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.Random;

/**
 * @author Archadia
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
          if(inventory[7] == new ItemStack(Items.diamond_pickaxe)) {
            if(ComplexMachines.oldExtractorMode) {
              findOre();
            } else {
              if(worldObj.getWorldTime() % 20 == 0) {
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
    while(!oreFound && getEnergyStored(null) > 100000) {
      tries++;

      if(tries > 5) {
        return;
      }

      int targetX = xCoord - 150;
      int targetZ = zCoord - 150;
      int targetY = rand.nextInt(15) + 5;

      targetX += (rand.nextInt(300));

      targetZ += (rand.nextInt(300));

      Block targetBlock = worldObj.getBlock(targetX, targetY, targetZ);

      boolean ore = isOre(targetBlock);

      if(worldObj.getChunkFromBlockCoords(targetX, targetZ).isChunkLoaded && ore) {
        oreFound = true;
        storage.extractEnergy(100000, true);
        inventory[7].setItemDamage(inventory[7].getItemDamage() + ComplexMachines.extractorPickDegradeRate);
        if(inventory[7].getItemDamage() > inventory[7].getMaxDamage()) {
          inventory[7].setItemDamage(inventory[7].getMaxDamage());
        }

        if(inventory[7].getItemDamage() == 1561) {
          inventory[7] = null;
        }

        //ItemStack drop = targetBlock.getBlockDropped(worldObj, targetX, targetY , targetZ, worldObj.getBlockMetadata(targetX, targetY, targetZ), 0).get(0);
        ItemStack drop = new ItemStack(targetBlock.getItemDropped(worldObj.getBlockMetadata(targetX, targetY, targetZ), worldObj.rand, 0));
        worldObj.setBlock(targetY, targetY, targetZ, Blocks.air);
        dropItems(drop);
      }
    }
  }

  private boolean isOre(Block name) {
    return name.getUnlocalizedName().contains("ore");
  }

  private IInventory findInventory() {

    if(worldObj.getTileEntity(xCoord + 1, yCoord, zCoord) instanceof IInventory) {
      return (IInventory) worldObj.getTileEntity(xCoord + 1, yCoord, zCoord);
    }
    if(worldObj.getTileEntity(xCoord - 1, yCoord, zCoord) instanceof IInventory) {
      return (IInventory) worldObj.getTileEntity(xCoord - 1,
          yCoord, zCoord);
    }
    if(worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof IInventory) {
      return (IInventory) worldObj.getTileEntity(xCoord,
          yCoord + 1, zCoord);
    }
    if(worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof IInventory) {
      return (IInventory) worldObj.getTileEntity(xCoord,
          yCoord - 1, zCoord);
    }
    if(worldObj.getTileEntity(xCoord, yCoord, zCoord + 1) instanceof IInventory) {
      return (IInventory) worldObj.getTileEntity(xCoord,
          yCoord, zCoord + 1);
    }
    if(worldObj.getTileEntity(xCoord, yCoord, zCoord - 1) instanceof IInventory) {
      return (IInventory) worldObj.getTileEntity(xCoord,
          yCoord, zCoord - 1);
    }
    return null;
  }

  private void dropItems(ItemStack item) {
    ItemStack itemStack = item;

    if(itemStack != null) {
      for(int i = 0; i < this.inventory.length; i++) {
        if(findInventory() != null) {
          itemStack = this.addStackToInventory(i, findInventory(), itemStack);
          return;
        }
      }
    }

  }

  public ItemStack addStackToInventory(int slotIndex, IInventory inventory, ItemStack itemStack) {
    if(itemStack != null) {
      if(inventory.getSizeInventory() > slotIndex) {
        ItemStack stackInInventory = inventory.getStackInSlot(slotIndex);

        if(stackInInventory == null) {
          inventory.setInventorySlotContents(slotIndex, itemStack);
          if(inventory.getStackInSlot(slotIndex) == null) {
            return itemStack;
          }
          return null;
        }
        if(stackInInventory.isItemEqual(itemStack) && stackInInventory.isStackable()) {
          stackInInventory = stackInInventory.copy();
          int stackLim = Math.min(inventory.getInventoryStackLimit(), itemStack.getMaxStackSize());
          int rejectedAmount = Math.max((stackInInventory.stackSize + itemStack.stackSize) - stackLim, 0);
          stackInInventory.stackSize = Math.min(Math.max((stackInInventory.stackSize + itemStack.stackSize - rejectedAmount), 0), inventory.getInventoryStackLimit());
          itemStack.stackSize = rejectedAmount;
          inventory.setInventorySlotContents(slotIndex, stackInInventory);
        }
      }

      if(itemStack.stackSize <= 0) {
        return null;
      }
    }

    return itemStack;
  }

  public String getInvName() {
    return "Extractor";
  }
}
