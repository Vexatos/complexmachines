package pixlepix.complexmachines.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.PowerProducerComplexTileEntity;
import universalelectricity.core.block.IConductor;




public class GrinderTileEntity extends PowerProducerComplexTileEntity implements IInventory {
	public double electricOutput=Config.grinderOutput;
	public int ticksOfPowerRemaining = 0;

	private IConductor connectedElectricUnit;

	private ItemStack[] inventory = new ItemStack[6];

	private int playersUsing = 0;
	public int orientation;

	

	@Override
	public void updateEntity() {
		// System.out.println(ticksOfPowerRemaining);

		if (!this.worldObj.isRemote) {

			

				for (int i = 0; i < inventory.length; i++) {
					ItemStack toGrind = inventory[i];
					if (inventory[i] != null) {
						if (toGrind.stackSize == 1) {
							inventory[i] = null;
						} else {
							inventory[i].stackSize--;
						}
						this.electricOutput=100000;
						System.out.println(electricOutput);
						super.updateEntity();
						return;

					} 
				}
			}

		}

		

	

	

	@Override
	public float getVoltage() {
		return 120;
	}

	/**
	 * @return The amount of ticks required to draw this item
	 */

	
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		// TODO Auto-generated method stub
		return inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {

		if (this.inventory[par1] != null) {
			ItemStack var3;

			if (this.inventory[par1].stackSize <= par2) {
				var3 = this.inventory[par1];
				this.inventory[par1] = null;
				return var3;
			} else {
				var3 = this.inventory[par1].splitStack(par2);

				if (this.inventory[par1].stackSize == 0) {
					this.inventory[par1] = null;
				}

				return var3;
			}
		} else
			return null;

	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.inventory[par1] != null) {
			ItemStack var2 = this.inventory[par1];
			this.inventory[par1] = null;
			return var2;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		if (par1 < inventory.length) {
			this.inventory[par1] = par2ItemStack;

			if (par2ItemStack != null
					&& par2ItemStack.stackSize > this.getInventoryStackLimit()) {
				par2ItemStack.stackSize = this.getInventoryStackLimit();
			}
		}
	}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return "Grinder";
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {

		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false: par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D,this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {
		this.playersUsing++;

	}

	@Override
	public void closeChest() {
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.inventory = new ItemStack[this.getSizeInventory()];
		try {
			this.setEnergyStored((float)par1NBTTagCompound.getDouble("joulesStored"));
		} catch (Exception e) {
		}

		NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");

			if (var5 >= 0 && var5 < this.inventory.length) {
				this.inventory[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setDouble("joulesStored", this.getEnergyStored());

		NBTTagList var2 = new NBTTagList();

		for (int var3 = 0; var3 < this.inventory.length; ++var3) {
			if (this.inventory[var3] != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.inventory[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}
		par1NBTTagCompound.setTag("Items", var2);
	}







	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}

}
