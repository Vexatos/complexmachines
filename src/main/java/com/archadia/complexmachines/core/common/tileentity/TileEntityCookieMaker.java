package com.archadia.complexmachines.core.common.tileentity;

import com.archadia.complexmachines.prefab.te.TileElectricMachine;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Random;

/**
 * @author Archadia
 */
public class TileEntityCookieMaker extends TileElectricMachine {

  private final static TileEntityCookieMaker tileEntityBase = new TileEntityCookieMaker();

  public final static TileEntityCookieMaker instance() {
    return tileEntityBase;
  }

  public TileEntityCookieMaker() {
    setInventorySize(4);
    setMaxTicks(40);
  }

  @Override
  public void updateEntity() {
    boolean flag1 = false;
    if(!this.worldObj.isRemote) {
      if(this.canProcess() && getEnergyStored(null) >= 1000) {
        this.processTicks++;

        if(this.processTicks == getMaxTicks()) {
          this.processTicks = 0;
          processItems();
          storage.extractEnergy(1000, true);
          flag1 = true;
        }
      } else {
        this.processTicks = 0;
      }
    }
    if(flag1) {
      this.markDirty();
    }
  }

  public int getProcessProgressScaled(int par1) {
    return this.processTicks * par1 / 200;
  }

  @Override
  public String getInventoryName() {
    return "Cookie Maker";
  }

  public boolean canProcess() {
    if(inventory[0] == null || inventory[1] == null || inventory[2] == null) {
      return false;
    }
    /*System.out.println(String.valueOf(inventory[0].getItem() == Items.wheat && inventory[0].stackSize >= 2) + " - " + String.valueOf(
        inventory[1].getItem() == Items.dye && inventory[1].getItemDamage() == 3) + " - " + String.valueOf(inventory[2].getItem() == Items.milk_bucket));*/
    if(inventory[0].getItem() == Items.wheat && inventory[0].stackSize >= 2) {
      if(inventory[1].getItem() == Items.dye && inventory[1].getItemDamage() == 3) {
        if(inventory[2].getItem() == Items.milk_bucket) {
          ItemStack itemstack = new ItemStack(Items.cookie, 12);
          if(inventory[3] == null) {
            return true;
          }
          if(!inventory[3].isItemEqual(itemstack)) {
            return false;
          }
          int result = inventory[3].stackSize + itemstack.stackSize;
          return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
      }
    }
    return false;
  }

  public void processItems() {
    if(canProcess()) {
      int cookieStackSize = 8;
      Random random = new Random();
      double randomCookieOutput = random.nextDouble();
      if(randomCookieOutput >= 0.95) {
        cookieStackSize = 12;
      } else if(randomCookieOutput >= 0.2) {
        cookieStackSize = 10;
      }
      ItemStack itemstack = new ItemStack(Items.cookie, cookieStackSize);
      if(inventory[3] == null) {
        inventory[3] = itemstack.copy();
      } else if(inventory[3].isItemEqual(itemstack)) {
        inventory[3].stackSize += itemstack.stackSize;
      }

      if(inventory[0].getItem() == Items.wheat) {
        int wheatLoss = 2;
        if(random.nextDouble() <= 0.1) {
          wheatLoss = 1;
        }
        inventory[0].stackSize -= wheatLoss;
      } else {
        --inventory[0].stackSize;
      }

      if(inventory[0].stackSize <= 0) {
        inventory[0] = null;
      }

      --inventory[1].stackSize;

      if(inventory[1].stackSize <= 0) {
        inventory[1] = null;
      }

      if(inventory[2].getItem() == Items.milk_bucket) {
        if(random.nextDouble() > 0.15) {
          ItemStack bucket = new ItemStack(Items.bucket);
          inventory[2] = bucket.copy();
        }
      } else {
        --inventory[2].stackSize;
      }

      if(inventory[2].stackSize <= 0) {
        inventory[2] = null;
      }
    }
  }
}
