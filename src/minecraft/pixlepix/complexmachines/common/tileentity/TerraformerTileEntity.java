package pixlepix.complexmachines.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeDirection;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.PowerConsumerComplexTileEntity;

public class TerraformerTileEntity extends PowerConsumerComplexTileEntity implements IInventory {
	
	private static final double TRANSFER_LIMIT = 1000;
	public double joulesStored;
	public int maxJoules=2000;
	public int direction=0;
	private ItemStack[] inventory= new ItemStack[9];
	public int momentum;
	public ForgeDirection momentumDirection;

	private int playersUsing;
	
	public void initiate() {
		
		
	}
	
	
		
		
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 9;
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
		return "Terraformer";
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
		
	
	public static boolean needsSupport(int id){
		int[] support={6,26,27,28,31,32,37,38,39,40,50,55,59,63,64,65,66,68,69,70,71,72,75,76,77,83,93,94,96,104,105,106,111,115,131,132,141,142,143,147,148,149,150,157};
		for(int i=0;i<support.length;i++){
			if(support[i]==id){
				return true;
			}
		}
		return false;
	}
	
	
	
	public void updateEntity(){
		super.updateEntity();

		
			

				if (getJoules() > 10000) {
					

			
			}

		

		this.joulesStored = Math.min(this.joulesStored, this.getMaxJoules());
		this.joulesStored = Math.max(this.joulesStored, 0d);

		}
	
	
	


	@Override
	public boolean canConnect(ForgeDirection direction) {
		return direction.ordinal() == this.getBlockMetadata() + 2;
	}


	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {

		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false
				: par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D,
						this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {
		this.playersUsing++;

	}

	@Override
	public void closeChest() {
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return itemstack.itemID==Config.itemStartingID+4;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.inventory = new ItemStack[this.getSizeInventory()];
		try {
			this.setJoules(par1NBTTagCompound.getDouble("joulesStored"));
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
		par1NBTTagCompound.setDouble("joulesStored", this.getJoules());

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



}
