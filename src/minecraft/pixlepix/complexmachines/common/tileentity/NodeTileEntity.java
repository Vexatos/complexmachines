package pixlepix.complexmachines.common.tileentity;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.common.ForgeDirection;
import pixlepix.complexmachines.common.CoordTuple;
import pixlepix.complexmachines.common.PowerConsumerComplexTileEntity;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.multiblock.TileEntityMulti;
import buildcraft.api.transport.PipeManager;

public class NodeTileEntity extends PowerConsumerComplexTileEntity {
	public final double WATTS_PER_TICK = 5000;
	public final double TRANSFER_LIMIT = 12500;
	private int drawingTicks = 0;
	private double joulesStored = 0;
	public static double maxJoules = 2000000;
	public int ticks = 0;

	
	ArrayList<Link> links;

	ArrayList<InTransit> inTransit;
	
	private int playersUsing = 0;
	public int orientation;

	private boolean initialized;

	@Override
	public void initiate() {
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (!this.worldObj.isRemote) {

				if (getJoules() > 4000) {
					if(worldObj.getTotalWorldTime()%10==0){
						Iterator<Link> iter=links.iterator();
							while(iter.hasNext()){
								Link link=iter.next();
								CoordTuple originTuple=link.origin;
								TileEntity entity=worldObj.getBlockTileEntity((int)originTuple.x, (int)originTuple.y, (int)originTuple.z);
								ItemStack stack=this.tryGrabFromPosition(originTuple.getVector(), ForgeDirection.VALID_DIRECTIONS[link.originSide]);
								if(stack!=null){
									CoordTuple outputTuple=originTuple.advance(link.destination, 0.6);
									EntityItem entityItem=new EntityItem(this.worldObj,originTuple.x,originTuple.y,originTuple.z,stack);
									inTransit.add(new InTransit(entityItem,link));
									worldObj.spawnEntityInWorld(entityItem);
								}
						}	
					}
					

				}

			}
	

		

		this.joulesStored = Math.min(this.joulesStored, this.getMaxJoules());
		this.joulesStored = Math.max(this.joulesStored, 0d);
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
	
	
	
	

	

}