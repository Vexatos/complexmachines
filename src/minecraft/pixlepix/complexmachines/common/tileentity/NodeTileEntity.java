package pixlepix.complexmachines.common.tileentity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;
import pixlepix.complexmachines.common.CoordTuple;
import pixlepix.complexmachines.common.FakeEntityItem;
import pixlepix.complexmachines.common.PowerConsumerComplexTileEntity;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.multiblock.TileEntityMulti;
import buildcraft.api.transport.PipeManager;

public class NodeTileEntity extends PowerConsumerComplexTileEntity implements IInventory {
	public final double WATTS_PER_TICK = 5000;
	public final double TRANSFER_LIMIT = 12500;
	private int drawingTicks = 0;
	private double joulesStored = 0;
	public static double maxJoules = 2000000;
	public int ticks = 0;

    public ItemStack[] inventory=new ItemStack[27];

	
	ArrayList<Link> links=new ArrayList<Link>();

	ArrayList<InTransit> inTransit=new ArrayList<InTransit>();
	
	private int playersUsing = 0;
	public int orientation;

	private boolean initialized;

	@Override
	public void initiate() {
		
	}
	
	
	
	public void formLink(int firstX, int firstY, int firstZ, int secondX, int secondY, int secondZ, int firstSide, int secondSide){
		CoordTuple origin=new CoordTuple(firstX,firstY,firstZ);
		CoordTuple destination=new CoordTuple(secondX, secondY, secondZ);
		double distX=origin.x-destination.x;
		double distY=origin.y-destination.y;
		double distZ=origin.z-destination.z;
		
		if(distX==0&&distY==0&&distZ==0){
			return;
		}
		if(distX>25||distX<-25||distY>25||distY<-25||distZ>25||distZ<-25){
			return;
		}
		
		links.add(new Link(origin, firstSide, destination, secondSide,links.size()));
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
			//if(!worldObj.isRemote){
				if (getJoules() > 1000) {
					if(worldObj.getTotalWorldTime()%10==0){
						Iterator<Link> iter=links.iterator();
							while(iter.hasNext()){
								Link link=iter.next();
								CoordTuple originTuple=link.origin;
								TileEntity entity=worldObj.getBlockTileEntity((int)originTuple.x, (int)originTuple.y, (int)originTuple.z);
								ItemStack stack=this.tryGrabFromPosition(originTuple.getVector(), ForgeDirection.VALID_DIRECTIONS[link.originSide]);
								if(stack!=null){
									this.setJoules(this.getJoules()-1000);
									CoordTuple outputTuple=originTuple.advance(link.destination, 1.1);
									EntityItem entityItem=new EntityItem(this.worldObj,outputTuple.x,outputTuple.y,outputTuple.z,stack);
									inTransit.add(new InTransit(entityItem,link));
									worldObj.spawnEntityInWorld(entityItem);
								}
						}	
					}
							Iterator<InTransit> iterItem=inTransit.iterator();
							while(iterItem.hasNext()){
								InTransit item=iterItem.next();
								item.ticks++;
								Link link=item.link;
								CoordTuple originTuple=item.link.origin;

								EntityItem entity=item.stack;
								CoordTuple destTuple=item.link.destination;
								CoordTuple pathTuple=new CoordTuple(destTuple.x-entity.posX,destTuple.y-entity.posY, destTuple.z-entity.posZ );
								Vector3 path=new Vector3(pathTuple.x,pathTuple.y,pathTuple.z);
								path.normalize();
								path.multiply(0.3);
								
								entity.motionX=path.x;
								entity.motionY=path.y;
								entity.motionZ=path.z;
								entity.isAirBorne=true;
								
								//entity.setVelocity(0, 0, 0);
								/*entity.setPosition(
										originTuple.x+((pathTuple.x)*(item.ticks/200)),
										originTuple.y+((pathTuple.y)*(item.ticks/200)),
										originTuple.x+((pathTuple.z)*(item.ticks/200))
										);
								*/
								entity.onGround=false;
								
								
								
								//More code from assembly line
								
								entity.delayBeforeCanPickup = 20;
								entity.onGround=false;
								
								
								
								boolean foundSneaking = false;
								for (EntityPlayer player : (List<EntityPlayer>) worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(entity.posX - 1, entity.posY - 1, entity.posZ - 1, entity.posX + 1, entity.posY + 1, entity.posZ + 1)))
								{
									if (player.isSneaking())
										foundSneaking = true;
										break;
								}
								
								if (foundSneaking){
									((EntityItem) entity).delayBeforeCanPickup = 0;
									iterItem.remove();
									
								}else{
									((EntityItem) entity).delayBeforeCanPickup = 20;
								}
								
								
								
								if(Math.abs(pathTuple.x)<1&&Math.abs(pathTuple.y)<1&&Math.abs(pathTuple.z)<1){
									ItemStack leftovers=this.tryPlaceInPosition(entity.getEntityItem(), new Vector3(destTuple.x,destTuple.y,destTuple.z), ForgeDirection.VALID_DIRECTIONS[link.destinationSide]);
									if (leftovers!=null)
									worldObj.spawnEntityInWorld(new EntityItem(this.worldObj,destTuple.x,destTuple.y,destTuple.z,leftovers));
									worldObj.removeEntity(entity);
								}
	
								
								
							}	
					//}
					

				

			
	

		

		this.joulesStored = Math.min(this.joulesStored, this.getMaxJoules());
		this.joulesStored = Math.max(this.joulesStored, 0d);
			}
	}

	
	
	
	
	
	
	
	
	
	
	//Borrowed code from assembly line
	//Thanks to darkcow
	/**
	 * Tries to place an itemStack in a specific position if it is an inventory.
	 * 
	 * @return The ItemStack remained after place attempt
	 */
	private ItemStack tryPlaceInPosition(ItemStack itemStack, Vector3 position, ForgeDirection direction)
	{
		TileEntity tileEntity = position.getTileEntity(this.worldObj);

		if (tileEntity != null && itemStack != null)
		{
			/**
			 * Try to put items into a chest.
			 */
			if (tileEntity instanceof TileEntityMulti)
			{
				Vector3 mainBlockPosition = ((TileEntityMulti) tileEntity).mainBlockPosition;

				if (mainBlockPosition != null)
				{
					if (!(mainBlockPosition.getTileEntity(this.worldObj) instanceof TileEntityMulti))
						return tryPlaceInPosition(itemStack, mainBlockPosition, direction);
				}
			}
			else if (tileEntity instanceof TileEntityChest)
			{
				TileEntityChest[] chests = { (TileEntityChest) tileEntity, null };

				/**
				 * Try to find a double chest.
				 */
				for (int i = 2; i < 6; i++)
				{
					ForgeDirection searchDirection = ForgeDirection.getOrientation(i);
					Vector3 searchPosition = position.clone();
					searchPosition.modifyPositionFromSide(searchDirection);

					if (searchPosition.getTileEntity(this.worldObj) != null)
					{
						if (searchPosition.getTileEntity(this.worldObj).getClass() == chests[0].getClass())
						{
							chests[1] = (TileEntityChest) searchPosition.getTileEntity(this.worldObj);
							break;
						}
					}
				}

				for (TileEntityChest chest : chests)
				{
					if (chest != null)
					{
						for (int i = 0; i < chest.getSizeInventory(); i++)
						{
							itemStack = this.addStackToInventory(i, chest, itemStack);
							if (itemStack == null)
							{
								return null;
							}
						}
					}
				}
			}
			
			else if (tileEntity instanceof ISidedInventory)
			{
				ISidedInventory inventory = (ISidedInventory) tileEntity;
				int[] slots = inventory.getAccessibleSlotsFromSide(direction.getOpposite().ordinal());
				for (int i = 0; i < slots.length; i++)
				{
					if (inventory.canInsertItem(slots[i], itemStack, direction.getOpposite().ordinal()))
					{
						itemStack = this.addStackToInventory(slots[i], inventory, itemStack);
					}
					if (itemStack == null)
					{
						return null;
					}
				}

			}
			else if (tileEntity instanceof net.minecraftforge.common.ISidedInventory)
			{
				net.minecraftforge.common.ISidedInventory inventory = (net.minecraftforge.common.ISidedInventory) tileEntity;

				int startIndex = inventory.getStartInventorySide(direction.getOpposite());

				for (int i = startIndex; i < startIndex + inventory.getSizeInventorySide(direction); i++)
				{
					itemStack = this.addStackToInventory(i, inventory, itemStack);
					if (itemStack == null)
					{
						return null;
					}
				}
			}
			else if (tileEntity instanceof IInventory)
			{
				IInventory inventory = (IInventory) tileEntity;

				for (int i = 0; i < inventory.getSizeInventory(); i++)
				{
					itemStack = this.addStackToInventory(i, inventory, itemStack);
					if (itemStack == null)
					{
						return null;
					}
				}
			}
		}

		if (itemStack.stackSize <= 0)
		{
			return null;
		}

		return itemStack;
	}

	public ItemStack addStackToInventory(int slotIndex, IInventory inventory, ItemStack itemStack)
	{
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
			else if (stackInInventory.isItemEqual(itemStack) && stackInInventory.isStackable())
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

		return itemStack;
	}

	/**
	 * Tries to take a item from a inventory at a specific position.
	 * 
	 * @param position
	 * @return
	 */
	private ItemStack tryGrabFromPosition(Vector3 position, ForgeDirection direction)
	{
		ItemStack returnStack = null;
		TileEntity tileEntity = position.getTileEntity(this.worldObj);

		if (tileEntity != null)
		{
			/**
			 * Try to put items into a chest.
			 */
			if (tileEntity instanceof TileEntityMulti)
			{
				Vector3 mainBlockPosition = ((TileEntityMulti) tileEntity).mainBlockPosition;

				if (mainBlockPosition != null)
				{
					if (!(mainBlockPosition.getTileEntity(this.worldObj) instanceof TileEntityMulti))
						return tryGrabFromPosition(mainBlockPosition, direction);
				}
			}
			else if (tileEntity instanceof TileEntityChest)
			{
				TileEntityChest[] chests = { (TileEntityChest) tileEntity, null };

				/**
				 * Try to find a double chest.
				 */
				for (int i = 2; i < 6; i++)
				{
					ForgeDirection searchDirection = ForgeDirection.getOrientation(i);
					Vector3 searchPosition = position.clone();
					searchPosition.modifyPositionFromSide(searchDirection);

					if (searchPosition.getTileEntity(this.worldObj) != null)
					{
						if (searchPosition.getTileEntity(this.worldObj).getClass() == chests[0].getClass())
						{
							chests[1] = (TileEntityChest) searchPosition.getTileEntity(this.worldObj);
							break;
						}
					}
				}

				chestSearch:
				for (TileEntityChest chest : chests)
				{
					if (chest != null)
					{
						for (int i = 0; i < chest.getSizeInventory(); i++)
						{
							ItemStack itemStack = this.removeStackFromInventory(i, chest);

							if (itemStack != null)
							{
								returnStack = itemStack;
								break chestSearch;
							}
						}
					}
				}
			}
			else if (tileEntity instanceof ISidedInventory)
			{
				ISidedInventory inventory = (ISidedInventory) tileEntity;

				int[] slots = inventory.getAccessibleSlotsFromSide(direction.ordinal());

				for (int i = 0; i < slots.length; i++)
				{
					int slot = slots[i];
					ItemStack itemStack = this.removeStackFromInventory(i, inventory);
					if (itemStack != null && inventory.canExtractItem(slot, itemStack, direction.ordinal()))
					{
						returnStack = itemStack;
						break;
					}
				}
			}
			else if (tileEntity instanceof net.minecraftforge.common.ISidedInventory)
			{
				net.minecraftforge.common.ISidedInventory inventory = (net.minecraftforge.common.ISidedInventory) tileEntity;

				int startIndex = inventory.getStartInventorySide(direction);

				for (int i = startIndex; i < startIndex + inventory.getSizeInventorySide(direction); i++)
				{
					ItemStack itemStack = this.removeStackFromInventory(i, inventory);

					if (itemStack != null)
					{
						returnStack = itemStack;
						break;
					}
				}
			}
			else if (tileEntity instanceof IInventory)
			{
				IInventory inventory = (IInventory) tileEntity;

				for (int i = 0; i < inventory.getSizeInventory(); i++)
				{
					ItemStack itemStack = this.removeStackFromInventory(i, inventory);
					if (itemStack != null)
					{
						returnStack = itemStack;
						break;
					}
				}
			}
		}

		return returnStack;
	}

	public ItemStack removeStackFromInventory(int slotIndex, IInventory inventory)
	{
		if (inventory.getStackInSlot(slotIndex) != null)
		{
			ItemStack itemStack = inventory.getStackInSlot(slotIndex).copy();

			
				itemStack.stackSize = 1;
				inventory.decrStackSize(slotIndex, 1);
				return itemStack;
			
		}

		return null;
	}

	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.inventory[i];
	}

	@Override
    public ItemStack decrStackSize(int slotID, int amount)
    {
        if(getStackInSlot(slotID) != null)
        {
            ItemStack tempStack;

            if(getStackInSlot(slotID).stackSize <= amount)
            {
                tempStack = getStackInSlot(slotID);
                setInventorySlotContents(slotID, null);
                return tempStack;
            }
            else {
                tempStack = getStackInSlot(slotID).splitStack(amount);

                if(getStackInSlot(slotID).stackSize == 0)
                {
                	setInventorySlotContents(slotID, null);
                }

                return tempStack;
            }
        }
        else {
            return null;
        }
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.inventory[par1] != null)
        {
            ItemStack itemstack = this.inventory[par1];
            this.inventory[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
		public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	    {
	        this.inventory[par1] = par2ItemStack;

	        if (par2ItemStack != null && par2ItemStack.stackSize > 64)
	        {
	            par2ItemStack.stackSize = 64;
	        }
		
	}

	@Override
	public String getInvName() {
		return "Node";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.inventory = new ItemStack[this.getSizeInventory()];
		try {
			this.joulesStored = par1NBTTagCompound.getDouble("joulesStored");
		} catch (Exception e) {
			System.out.println("Corrupt power NBT data");
		}

		NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");

			if (var5 >= 0 && var5 < this.inventory.length) {
				this.inventory[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}
		
		
		
		
		
		var2 = par1NBTTagCompound.getTagList("Links");
		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");

			if (var5 >= 0) {
				Link newLink=new Link(var4);
				links.add(newLink);
			}
		}
		
		/*
		var2 = par1NBTTagCompound.getTagList("InTransit");
		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");

			if (var5 >= 0) {
				InTransit newItem=new InTransit(var4,this);
				if(newItem.link!=null){
					inTransit.add(newItem);
				}
			}
		}
		*/
		//TODO
		//Fix NPE in world obj
		
		
		
		
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
		
		
		

		var2 = new NBTTagList();
		for (int var3 = 0; var3 < this.links.size(); ++var3) {
			if (this.links.get(var3) != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.links.get(var3).writeToNBT(var4);
				var2.appendTag(var4);
			}
		}
		par1NBTTagCompound.setTag("Links", var2);
		
		var2 = new NBTTagList();
		for (int var3 = 0; var3 < this.inTransit.size(); ++var3) {
			if (this.inTransit.get(var3) != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.inTransit.get(var3).writeToNBT(var4);
				var2.appendTag(var4);
			}
		}
		par1NBTTagCompound.setTag("InTransit", var2);
		
	}
	
	
	

	

}