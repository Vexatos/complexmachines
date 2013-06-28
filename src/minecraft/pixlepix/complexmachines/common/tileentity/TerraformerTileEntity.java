package pixlepix.complexmachines.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.common.ForgeDirection;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.PowerConsumerComplexTileEntity;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import universalelectricity.prefab.network.PacketManager;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class TerraformerTileEntity extends PowerConsumerComplexTileEntity implements IInventory {
	
	private static final double TRANSFER_LIMIT = 1000;
	public double joulesStored;
	public int maxJoules=2000;
	public int direction=0;
	private ItemStack[] inventory= new ItemStack[9];
	public int momentum;
	public ForgeDirection momentumDirection;
	public int state=0;
	public int cycle;
	private int playersUsing;
	String owner;
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
		if (!this.worldObj.isRemote)
		{
			if (this.ticks % 10 == 0)
			{
				for (int i=0;i<worldObj.playerEntities.size();i++)
				{
					PacketDispatcher.sendPacketToPlayer(getDescriptionPacket(), (Player)worldObj.playerEntities.get(i));
				}
			}
		}

			//if (getJoules() > 10000) {
				
			
					
						
						if(worldObj.getTotalWorldTime()%500!=0){
							return;
						}
						if(getEnergy()<getNeededJoules()){
							return;
						}
						//System.out.println("X:"+xCycle+"Y:"+yCycle+"Z:"+zCycle);
						
						
						for(int i=getXMin();i<getXMax();i++){
							for(int j=getYMin();j<getYMax();j++){
								for(int k=getZMin();k<getZMax();k++){
									if(worldObj.getBlockTileEntity(i,j,k)==null){
										worldObj.destroyBlock(i,j,k, false);
									}
								}
							}
						}
						if(inventory[8]!=null){
							int id=inventory[8].itemID;
							int metadata=inventory[8].getItemDamage();
							if(inventory[8].getItem() instanceof ItemBlock){
								
								int j=getYMin();
								for(int i=getXMin();i<getXMax();i++){
									
										for(int k=getZMin();k<getZMax();k++){
											if(worldObj.getBlockTileEntity(i,j,k)==null){

												if(!ComplexMachines.isProtected(i,k)&&takeFromChest(id,metadata))
												worldObj.setBlock(i, j, k, id, metadata, 3);
											}
										}
									
								}
							}
						}
						if(inventory[6]!=null){
							int id=inventory[6].itemID;
							int metadata=inventory[6].getItemDamage();
							if(inventory[6].getItem() instanceof ItemBlock){
								
								int j=getYMax();
								for(int i=getXMin();i<getXMax();i++){
									
										for(int k=getZMin();k<getZMax();k++){
											if(worldObj.getBlockTileEntity(i,j,k)==null){

												if(!ComplexMachines.isProtected(i,k)&&takeFromChest(id,metadata))
												worldObj.setBlock(i, j, k, id, metadata, 3);
											}
										}
									
								}
							}
						}
						if(inventory[7]!=null){
							int id=inventory[7].itemID;
							int metadata=inventory[7].getItemDamage();
							if(inventory[7].getItem() instanceof ItemBlock){
								
								int i=getXMax();
								for(int j=getYMin();j<getYMax();j++){
									
										for(int k=getZMin();k<getZMax();k++){
											if(worldObj.getBlockTileEntity(i,j,k)==null){
												if(!ComplexMachines.isProtected(i,k)&&takeFromChest(id,metadata))
												worldObj.setBlock(i, j, k, id, metadata, 3);
											}
										}
									
								}
								i=getXMin();
								for(int j=getYMin();j<getYMax();j++){
									
										for(int k=getZMin();k<getZMax();k++){
											if(worldObj.getBlockTileEntity(i,j,k)==null){
												if(!ComplexMachines.isProtected(i,k)&&takeFromChest(id,metadata))
												worldObj.setBlock(i, j, k, id, metadata, 3);
											}
										}
									
								}
								
								
								
								
								
								
								
								
								
								
								int k=getZMax();
								for(int j=getYMin();j<getYMax();j++){
									
										for(i=getXMin();i<getXMax();i++){
											if(worldObj.getBlockTileEntity(i,j,k)==null){
												if(!ComplexMachines.isProtected(i,k)&&takeFromChest(id,metadata))
												worldObj.setBlock(i, j, k, id, metadata, 3);
											}
										}
									
								}
								k=getZMin();
								for(int j=getYMin();j<getYMax();j++){
									
										for(i=getXMin();i<getXMax();i++){
											if(worldObj.getBlockTileEntity(i,j,k)==null){
												if(!ComplexMachines.isProtected(i,k)&&takeFromChest(id,metadata))
												worldObj.setBlock(i, j, k, id, metadata, 3);
											}
										}
									
								}
								
								
								
								
								
								
							}
						}
						
						
						
						
						
				
				
				
				
			//}

		worldObj.setBlock(xCoord, yCoord, zCoord, 0);
		this.joulesStored = Math.min(this.joulesStored, this.getMaxJoules());
		this.joulesStored = Math.max(this.joulesStored, 0d);

		}
	
	
	


	@Override
	public boolean canConnect(ForgeDirection direction) {
		return direction.ordinal() == this.getBlockMetadata() + 2;
	}

	public int getXMax(){
		return xCoord+getUpgradesInSlot(0);
	}

	public int getXMin(){
		return xCoord-getUpgradesInSlot(1);
	}
	public int getZMax(){
		return zCoord+getUpgradesInSlot(2);
	}

	public int getZMin(){
		return zCoord-getUpgradesInSlot(3);
	}
	public int getYMax(){
		return yCoord+getUpgradesInSlot(4);
	}

	public int getYMin(){
		return yCoord-getUpgradesInSlot(5);
	}
	public int getXRange(){
		return getXMax()-getXMin();
	}
	public int getYRange(){
		return getYMax()-getYMin();
	}
	public int getZRange(){
		return getZMax()-getZMin();
	}
	
	public int getUpgradesInSlot(int slot){
		ItemStack stack=inventory[slot];
		if(stack!=null&&stack.getItem()==ComplexMachines.rangeExtender){
			return stack.stackSize+1;
		}
		return 1;
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
	
	public boolean takeFromChest(int id, int meta){
		
		
		int found=0;
		
		for (int dir = 0; dir < 6; dir++)
		{
			ForgeDirection direction = ForgeDirection.getOrientation(dir);
			TileEntity tileEntity = VectorHelper.getTileEntityFromSide(worldObj, new Vector3(this), direction);

			if (tileEntity instanceof TileEntityChest)
			{
				IInventory inventory = ((IInventory) tileEntity);
				
				TileEntityChest tileChest=(TileEntityChest)tileEntity;
				for(int i=0;i<inventory.getSizeInventory();i++){
					ItemStack stack=inventory.getStackInSlot(i);
					if(stack!=null&&stack.itemID==id&&stack.getItemDamage()==meta){
						found+=stack.stackSize;
						inventory.decrStackSize(i, 1);
						return true;
					}
				}

				inventory.onInventoryChanged();
				

					
						
						
					
				
			}
		}
		
		
		
		return false;
		
	}

	
	@Override
	public double getMaxJoules() {
		return getNeededJoules();
	}

	public double getNeededJoules() {
		return 10000*getXRange()*getYRange()*getZRange();
	}
	
	
	@Override
	public Packet getDescriptionPacket()
	{
		return PacketManager.getPacket("Complex Machines", this, this.getJoules());
	}

	@Override
	public void handlePacketData(INetworkManager network, int type, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput dataStream)
	{
		try
		{
			this.setJoules(dataStream.readDouble());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
