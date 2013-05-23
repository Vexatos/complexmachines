package pixlepix.complexmachines.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import pixlepix.complexmachines.common.AirshipBlockRegistry;
import pixlepix.complexmachines.common.AirshipDelayedBlock;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.CoordTuple;
import pixlepix.complexmachines.common.laser.LaserEmitterTileEntity;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.core.block.IElectricityStorage;
import universalelectricity.core.electricity.ElectricityNetworkHelper;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.electricity.IElectricityNetwork;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import universalelectricity.prefab.network.PacketManager;
import universalelectricity.prefab.tile.TileEntityElectricityRunnable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.common.ForgeDirection;

public class MotorTileEntity extends TileEntityElectricityRunnable implements IInventory, IElectricityStorage {
	
	private static final double TRANSFER_LIMIT = 25000;
	public double joulesStored;
	public int maxJoules=25000;
	public int direction=0;
	private ItemStack[] inventory = new ItemStack[1];
	public int momentum;
	public ForgeDirection momentumDirection;

	private int playersUsing;
	
	public void initiate() {
		
		
	}
	
	public void consumePower(){
		ForgeDirection inputDirection = ForgeDirection.getOrientation(this.getBlockMetadata() + 2);
		TileEntity inputTile = VectorHelper.getTileEntityFromSide(
				this.worldObj, new Vector3(this), inputDirection);

		IElectricityNetwork inputNetwork = ElectricityNetworkHelper
				.getNetworkFromTileEntity(inputTile,
						inputDirection.getOpposite());

		if (inputNetwork != null) {
			if (this.joulesStored < maxJoules) {
				inputNetwork.startRequesting(this,Math.min(this.getMaxJoules() - this.getJoules(),this.TRANSFER_LIMIT) / this.getVoltage(),this.getVoltage());
				ElectricityPack electricityPack = inputNetwork.consumeElectricity(this);
				this.setJoules(this.joulesStored+ electricityPack.getWatts());

				if (UniversalElectricity.isVoltageSensitive) {
					if (electricityPack.voltage > this.getVoltage()) {
						this.worldObj.createExplosion(null, this.xCoord,
								this.yCoord, this.zCoord, 2f, true);
					}
				}
			} else {
				inputNetwork.stopRequesting(this);
			}
		}
	}
		
		
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		// TODO Auto-generated method stub
		return inventory[0];
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
		
	
	public boolean needsSupport(int id){
		int[] support={6,26,27,28,31,32,37,38,39,40,50,55,59,63,64,65,66,68,69,70,71,72,75,76,77,83,93,94,96,104,105,106,111,115,131,132,141,142,143,147,148,149,150,157};
		for(int i=0;i<support.length;i++){
			if(support[i]==id){
				return true;
			}
		}
		return false;
	}
	
	public ForgeDirection getDirection(){
		switch(direction){

		case 1:
			return ForgeDirection.UP;
		case 2:
			return ForgeDirection.DOWN;
		case 3:
			return ForgeDirection.NORTH;
		case 4:
			return ForgeDirection.EAST;
		case 5:
			return ForgeDirection.SOUTH;
		case 6:
			return ForgeDirection.WEST;
		
		}
		return null;
	}
	
	public void updateEntity(){
		super.updateEntity();

		ForgeDirection inputDirection = ForgeDirection.getOrientation(this.getBlockMetadata() + 2);

		System.out.println(""+worldObj.isRemote+direction);
		// System.out.println(getJoules());
		if (!this.worldObj.isRemote) {
				consumePower();

				if (getJoules() > 10000) {
					if (worldObj.getTotalWorldTime()%100==0) {

						setJoules(getJoules()-10000);
						System.out.println(getDirection());
						if(getDirection()!=null){
							System.out.println(getDirection().name());
							move(getDirection());
						}


					}

			
			}else{
				System.out.println(getJoules());
			}

		if (!this.worldObj.isRemote) {
			if (this.ticks % 3 == 0 && this.playersUsing > 0) {
//				PacketManager.sendPacketToClients(this.getDescriptionPacket(),
	//					this.worldObj, new Vector3(this), 12);
			}
		}

		this.joulesStored = Math.min(this.joulesStored, this.getMaxJoules());
		this.joulesStored = Math.max(this.joulesStored, 0d);

		}
	}
	public void scan(ForgeDirection direction, CoordTuple target, boolean center){
		if(!worldObj.isRemote){
			int targetX = target.x+direction.offsetX;
			int targetY = target.y+direction.offsetY;
			int targetZ = target.z+direction.offsetZ;
			
			
			
			int meta=worldObj.getBlockMetadata(target.x,target.y,target.z);
			int targetId=worldObj.getBlockId(targetX, targetY, targetZ);
			if((targetId>7&&targetId<12)||targetId==0||(worldObj.getBlockTileEntity(targetX, targetY, targetZ) instanceof MotorTileEntity&&center)){
				
				int materialId=worldObj.getBlockId(target.x, target.y, target.z);
				
				if(needsSupport(materialId)){
					AirshipBlockRegistry.addDelayed(new AirshipDelayedBlock(targetX,targetY,targetZ,materialId,meta,worldObj,target.x,target.y,target.z));
					//worldObj.setBlock(target.x, target.y, target.z, 0);
				}
				
				
				
				//if(newEntity!=null&&!(newEntity instanceof MotorTileEntity)){
				
				
			}
			}
		}
	
	public void moveBlock(ForgeDirection direction, CoordTuple target, boolean center){
		
		if(!worldObj.isRemote){
		int targetX = target.x+direction.offsetX;
		int targetY = target.y+direction.offsetY;
		int targetZ = target.z+direction.offsetZ;
		
		
		
		int meta=worldObj.getBlockMetadata(target.x,target.y,target.z);
		int targetId=worldObj.getBlockId(targetX, targetY, targetZ);
			AirshipBlockRegistry.register(targetX, targetY, targetZ);
			int materialId=worldObj.getBlockId(target.x, target.y, target.z);
			TileEntity oldEntity=worldObj.getBlockTileEntity(target.x, target.y, target.z);
			NBTTagList list=new NBTTagList();
			NBTTagCompound data=new NBTTagCompound();
			if(oldEntity != null){
				oldEntity.writeToNBT(data);
				list.appendTag(data);
				oldEntity.invalidate();
			}
			Block targetBlockType = this.blockType;
			AirshipBlockRegistry.addBlock(new AirshipDelayedBlock(targetX,targetY,targetZ,materialId,meta,worldObj,list,target.x,target.y,target.z));
			
		}
		
	}
	
	public void move(ForgeDirection direction){
		
		 	System.out.println("1");
			for(int i=xCoord-4;i<xCoord+4;i++){
				for(int j=yCoord-4;j<yCoord+4;j++){

					for(int k=zCoord-4;k<zCoord+4;k++){

					 	System.out.println("2");
						scan(direction, new CoordTuple(i,j,k),true);

					}
				}
			}
			//xCoordTuple[] nearby={new CoordTuple(xCoord+1,yCoord,zCoord+1),new CoordTuple(xCoord+1,yCoord,zCoord-1),new CoordTuple(xCoord-1,yCoord,zCoord+1),new CoordTuple(xCoord-1,yCoord,zCoord-1),new CoordTuple(xCoord+1,yCoord,zCoord),new CoordTuple(xCoord-1,yCoord,zCoord),new CoordTuple(xCoord,yCoord+1,zCoord),new CoordTuple(xCoord,yCoord-1,zCoord),new CoordTuple(xCoord,yCoord,zCoord+1),new CoordTuple(xCoord,yCoord,zCoord-1)};
			ArrayList<CoordTuple> near=new ArrayList<CoordTuple>();
			

			
				for(int i=xCoord-2;i<xCoord+3;i++){
					for(int j=yCoord-2;j<yCoord+3;j++){

						for(int k=zCoord-2;k<zCoord+3;k++){

						 	System.out.println("3");
							scan(direction,new CoordTuple(i,j,k), true);
							
								register(i,j,k,near);
							

						}	
					}
				}
			


			 	System.out.println("4");

			for(int l=0;l<near.size();l++){
				CoordTuple target=near.get(l);
				if(target!=null&&!AirshipBlockRegistry.check(target.x, target.y, target.z)){
					moveBlock(direction, target,false);
				}
			}

			moveBlock(direction, new CoordTuple(xCoord,yCoord,zCoord),false);

			moveBlock(direction, new CoordTuple(xCoord-direction.offsetX,yCoord-direction.offsetY,zCoord-direction.offsetZ),true);
			moveBlock(direction, new CoordTuple(xCoord-2*direction.offsetX,yCoord-2*direction.offsetY,zCoord-2*direction.offsetZ),true);
			//moveBlock(direction, new CoordTuple(xCoord-3*direction.offsetX,yCoord-3*direction.offsetY,zCoord-3*direction.offsetZ),true);
			if(direction.offsetY==1){
			List<Entity> entities=worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(xCoord-1.5, yCoord-1.5, zCoord-1.5, xCoord+1.5, yCoord+1.5, zCoord+1.5));
				for(int i=0;i<entities.size();i++){

					Entity entity=entities.get(i);
					entity.setPosition(entity.posX, entity.posY+2, entity.posZ);
				}
			}

		
	}
	

	
	
	private void register(int i, int j, int k, ArrayList near) {
		if(xCoord!=i||yCoord!=j||zCoord!=k){
			
			if(worldObj.getBlockId(i, j, k)!=0){

				near.add(new CoordTuple(i,j,k));
			}
		}
		
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
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.inventory = new ItemStack[this.getSizeInventory()];
		try {
			this.joulesStored = par1NBTTagCompound.getDouble("joulesStored");
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

		this.direction = par1NBTTagCompound.getInteger("direction");
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
		par1NBTTagCompound.setInteger("direction", direction);
	}




	@Override
	public double getJoules() {
		// TODO Auto-generated method stub
		return joulesStored;
	}




	@Override
	public void setJoules(double joules) {
		// TODO Auto-generated method stub
		this.joulesStored=joules;
	}




	@Override
	public double getMaxJoules() {
		// TODO Auto-generated method stub
		return this.maxJoules;
	}

}
