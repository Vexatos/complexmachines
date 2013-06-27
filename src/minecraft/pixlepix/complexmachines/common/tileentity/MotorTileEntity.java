package pixlepix.complexmachines.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import pixlepix.complexmachines.api.AirshipSpecialMoveData;
import pixlepix.complexmachines.api.IAirshipSpecialMove;
import pixlepix.complexmachines.common.AirshipBlockRegistry;
import pixlepix.complexmachines.common.AirshipDelayedBlock;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.CoordTuple;
import pixlepix.complexmachines.common.PowerConsumerComplexTileEntity;
import pixlepix.complexmachines.common.item.RangeExtender;
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
import mekanism.api.IStrictEnergyAcceptor;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.common.ForgeDirection;

public class MotorTileEntity extends PowerConsumerComplexTileEntity implements IInventory {
	
	private static final double TRANSFER_LIMIT = 25000;
	public double joulesStored;
	public int maxJoules=25000;
	public int direction=1;
	private ItemStack inventory;
	public int momentum;
	public ForgeDirection momentumDirection;

	private int playersUsing;
	
	public void initiate() {
		
		
	}
	
	
		
		
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		// TODO Auto-generated method stub
		return inventory;
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {

		if (this.inventory != null) {
			ItemStack var3;

			if (this.inventory.stackSize <= par2) {
				var3 = this.inventory;
				this.inventory = null;
				return var3;
			} else {
				var3 = this.inventory.splitStack(par2);

				if (this.inventory.stackSize == 0) {
					this.inventory = null;
				}

				return var3;
			}
		} else
			return null;

	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.inventory != null) {
			ItemStack var2 = this.inventory;
			this.inventory = null;
			return var2;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		
			this.inventory = par2ItemStack;

			if (par2ItemStack != null
					&& par2ItemStack.stackSize > this.getInventoryStackLimit()) {
				par2ItemStack.stackSize = this.getInventoryStackLimit();
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
		
	
	public static boolean needsSupport(int id){
		int[] support={6,26,27,28,31,32,37,38,39,40,50,55,59,63,64,65,66,68,69,70,71,72,75,76,77,83,93,94,96,104,105,106,111,115,131,132,141,142,143,147,148,149,150,157};
		for(int i=0;i<support.length;i++){
			if(support[i]==id){
				return true;
			}
		}
		return false;
	}
	
	public ForgeDirection getDirection(){
		ForgeDirection[] directions={ForgeDirection.DOWN,ForgeDirection.UP,ForgeDirection.EAST,ForgeDirection.WEST,ForgeDirection.NORTH,ForgeDirection.SOUTH};
		
		for(ForgeDirection movementDirection:directions){
			if(worldObj.getIndirectPowerOutput(xCoord+movementDirection.offsetX, yCoord+movementDirection.offsetY, zCoord+movementDirection.offsetZ, movementDirection.ordinal())){
				return movementDirection;
			}
		}
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
		// System.out.println(getJoules());
			if(MinecraftServer.getServer().worldServers[0].getTotalWorldTime()%100==99){
				AirshipBlockRegistry.empty();
			}
				if (getJoules() > 10000) {
					if(MinecraftServer.getServer().worldServers[0].getTotalWorldTime()%100==0){
						ForgeDirection direction=getDirection();
						for(int i=0;i<(getRange()+2);i++){
							if(worldObj.getBlockId(xCoord+(direction.offsetX*i), yCoord+(direction.offsetY*i),zCoord+(direction.offsetZ*i))==49)
							{
								return;
							}
						}
						if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)){
							return;
						}
						
						if(getDirection()!=null){
							setJoules(getJoules()-10000);
							
							move(getDirection());
						}


					}

			
			}

		

		this.joulesStored = Math.min(this.joulesStored, this.getMaxJoules());
		this.joulesStored = Math.max(this.joulesStored, 0d);

		}
	
	
	public void moveBlock(ForgeDirection direction, CoordTuple target, boolean center){
		
			if(ComplexMachines.isProtected(xCoord, zCoord)){
				return;
			}
		int targetX = (int)target.x+direction.offsetX;
		int targetY = (int)target.y+direction.offsetY;
		int targetZ = (int)target.z+direction.offsetZ;
		
		
		
		int meta=worldObj.getBlockMetadata((int)target.x,(int)target.y,(int)target.z);
		int targetId=worldObj.getBlockId(targetX, targetY, targetZ);
			AirshipBlockRegistry.register(targetX, targetY, targetZ);
			int materialId=worldObj.getBlockId((int)target.x, (int)target.y, (int)target.z);
			TileEntity oldEntity=worldObj.getBlockTileEntity((int)target.x, (int)target.y, (int)target.z);
			NBTTagList list=new NBTTagList();
			NBTTagCompound data=new NBTTagCompound();
			AirshipSpecialMoveData specialData=null;
			if(oldEntity != null){
				oldEntity.writeToNBT(data);
				list.appendTag(data);
				if(oldEntity instanceof IAirshipSpecialMove){
					specialData=((IAirshipSpecialMove)oldEntity).beforeMove(direction);
				}
			}
			Block targetBlockType = this.blockType;
			
			if(!(materialId==7)){
				AirshipBlockRegistry.addBlock(new AirshipDelayedBlock(targetX,targetY,targetZ,materialId,meta,worldObj,list,(int)target.x,(int)target.y,(int)target.z,specialData));
			}
			if(needsSupport(materialId)){
				worldObj.setBlock((int)target.x, (int)target.y, (int)target.z, 0);
			}
			
		}
		
	
	
	public void move(ForgeDirection direction){
			
			//xCoordTuple[] nearby={new CoordTuple(xCoord+1,yCoord,zCoord+1),new CoordTuple(xCoord+1,yCoord,zCoord-1),new CoordTuple(xCoord-1,yCoord,zCoord+1),new CoordTuple(xCoord-1,yCoord,zCoord-1),new CoordTuple(xCoord+1,yCoord,zCoord),new CoordTuple(xCoord-1,yCoord,zCoord),new CoordTuple(xCoord,yCoord+1,zCoord),new CoordTuple(xCoord,yCoord-1,zCoord),new CoordTuple(xCoord,yCoord,zCoord+1),new CoordTuple(xCoord,yCoord,zCoord-1)};
			ArrayList<CoordTuple> near=new ArrayList<CoordTuple>();
			

			
			int xMin=xCoord-2;
			int yMin=yCoord-2;
			int zMin=zCoord-2;

			int xMax=xCoord+3;
			int yMax=yCoord+3;
			int zMax=zCoord+3;

			int xInc=1;
			int yInc=1;
			int zInc=1;

			boolean reverse=false;

			if(direction.offsetX==1){
				xMax=xCoord-2;
				xMin=xCoord+3;
				xInc=-1;
				reverse=true;
			}

			if(direction.offsetY==1){
				yMax=yCoord-2;
				yMin=yCoord+3;
				yInc=-1;
				reverse=true;
			}

			if(direction.offsetZ==1){
				zMax=zCoord-2;
				zMin=zCoord+3;
				zInc=-1;
				reverse=true;
			}

			if(!reverse){
				for(int i=xCoord-getRange();i<xCoord+getRange()+1;i++){
					for(int j=yCoord-getRange();j<yCoord+getRange()+1;j++){

						for(int k=zCoord-getRange();k<zCoord+getRange()+1;k++){
							//scan(direction,new CoordTuple(i,j,k), true);
								register(i,j,k,near);
							

						}	
					}
				}
			}else{

				for(int i=xCoord+getRange();i>(xCoord-getRange())-1;i--){
					for(int j=yCoord+getRange();j>(yCoord-getRange())-1;j--){
						for(int k=zCoord+getRange();k>(zCoord-getRange())-1;k--){

							//scan(direction,new CoordTuple(i,j,k), true);
								register(i,j,k,near);
							

						}	
					}
				}

			}

			for(int l=0;l<near.size();l++){
				CoordTuple target=near.get(l);
				if(target!=null){
					moveBlock(direction, target,false);
				}
			}

			moveBlock(direction, new CoordTuple(xCoord,yCoord,zCoord),false);

			moveBlock(direction, new CoordTuple(xCoord-direction.offsetX,yCoord-direction.offsetY,zCoord-direction.offsetZ),true);
			moveBlock(direction, new CoordTuple(xCoord-2*direction.offsetX,yCoord-2*direction.offsetY,zCoord-2*direction.offsetZ),true);
			//moveBlock(direction, new CoordTuple(xCoord-3*direction.offsetX,yCoord-3*direction.offsetY,zCoord-3*direction.offsetZ),true);
			if(direction.offsetY==1){
			List<Entity> entities=worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(xCoord-getRange(), yCoord-getRange(), zCoord-getRange(), xCoord+getRange(), yCoord+getRange(), zCoord+getRange()));
				for(int i=0;i<entities.size();i++){

					Entity entity=entities.get(i);
					entity.setPosition(entity.posX, entity.posY+1.5, entity.posZ);
				}
			}else{
				List<Entity> entities=worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(xCoord-getRange(), yCoord-getRange(), zCoord-getRange(), xCoord+getRange(), yCoord+getRange(), zCoord+getRange()));
				for(int i=0;i<entities.size();i++){

					Entity entity=entities.get(i);
					entity.setPosition(entity.posX+direction.offsetX, entity.posY+.2, entity.posZ+direction.offsetZ);
				}
			}

		
	}
	

	
	
	private void register(int i, int j, int k, @SuppressWarnings("rawtypes") ArrayList near) {
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
		return itemstack.itemID==Config.itemStartingID+4;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		try {
			this.joulesStored = par1NBTTagCompound.getDouble("joulesStored");
		} catch (Exception e) {
		}

		NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");

				this.inventory = ItemStack.loadItemStackFromNBT(var4);
			
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

		for (int var3 = 0; var3 < 1; ++var3) {
			if (this.inventory != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.inventory.writeToNBT(var4);
				var2.appendTag(var4);
			}
		}
		
		par1NBTTagCompound.setTag("Items", var2);
		par1NBTTagCompound.setInteger("direction", direction);
	}





	public int getRange(){
		ItemStack upgrades=inventory;
		if(upgrades!=null&&upgrades.getItem() instanceof RangeExtender){
			
			return upgrades.stackSize+2;
		}else{
			return 2;
		}
	}

	
	

	public void setDirection(int direction2) {
		// TODO Auto-generated method stub
		this.direction=direction2;
	}
	

	@Override
	public boolean canReceiveEnergy(ForgeDirection side) {
		// TODO Auto-generated method stub
		return this.canConnect(side);
	}
	@Override
	public double getMaxJoules() {
		return 11000;
	}

}
