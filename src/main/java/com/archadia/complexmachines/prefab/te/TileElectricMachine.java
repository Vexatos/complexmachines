package com.archadia.complexmachines.prefab.te;

import cofh.api.energy.TileEnergyHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class TileElectricMachine extends TileEnergyHandler implements IInventory {

  protected ItemStack[] inventory;

  public static int playersUsing;
  public int processTicks;
  public static int maxTicks;

  public void setMaxTicks(int t) {
    maxTicks = t;
  }

  public int getMaxTicks() {
    return maxTicks;
  }

  @Override
  public int getSizeInventory() {
    return inventory.length;
  }

  @Override
  public ItemStack getStackInSlot(int i) {
    return this.inventory[i];
  }

  @Override
  public ItemStack decrStackSize(int slot, int amount) {
    if(slot >= 0 && slot < inventory.length) {
      ItemStack itemstack = inventory[slot];
      inventory[slot] = null;
      this.markDirty();
      return itemstack;
    } else {
      return null;
    }
  }

  @Override
  public ItemStack getStackInSlotOnClosing(int i) {
    if(this.inventory[i] != null) {
      ItemStack itemstack = this.inventory[i];
      this.inventory[i] = null;
      return itemstack;
    } else {
      return null;
    }
  }

  @Override
  public void setInventorySlotContents(int i, ItemStack itemstack) {
    this.inventory[i] = itemstack;

    if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
      itemstack.stackSize = this.getInventoryStackLimit();
    }
  }

  @Override
  public String getInventoryName() {
    return null;
  }

  @Override
  public boolean hasCustomInventoryName() {
    return false;
  }

  @Override
  public int getInventoryStackLimit() {
    return 64;
  }

  @Override
  public boolean isUseableByPlayer(EntityPlayer entityplayer) {
    return true;
  }

  @Override
  public boolean canUpdate() {
    return true;
  }

  @Override
  public void openInventory() {
    playersUsing++;
  }

  @Override
  public void closeInventory() {
    playersUsing--;
  }

  @Override
  public boolean isItemValidForSlot(int i, ItemStack itemstack) {
    return true;
  }

  public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
    super.readFromNBT(par1NBTTagCompound);
    NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
    this.inventory = new ItemStack[this.getSizeInventory()];

    for(int i = 0; i < nbttaglist.tagCount(); ++i) {
      NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
      byte b0 = nbttagcompound1.getByte("Slot");

      if(b0 >= 0 && b0 < this.inventory.length) {
        this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
      }
    }
  }

  /**
   * Writes a tile entity to NBT.
   */
  public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
    super.writeToNBT(par1NBTTagCompound);
    NBTTagList nbttaglist = new NBTTagList();

    for(int i = 0; i < this.inventory.length; ++i) {
      if(this.inventory[i] != null) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.setByte("Slot", (byte) i);
        this.inventory[i].writeToNBT(nbttagcompound1);
        nbttaglist.appendTag(nbttagcompound1);
      }
    }
    par1NBTTagCompound.setTag("Items", nbttaglist);
  }

  public void setInventorySize(int amt) {
    inventory = new ItemStack[amt];
  }
}
