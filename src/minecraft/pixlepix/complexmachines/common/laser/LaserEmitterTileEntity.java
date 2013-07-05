package pixlepix.complexmachines.common.laser;

import java.util.Random;

import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.CoordTuple;
import pixlepix.complexmachines.common.PowerConsumerComplexTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.LaserBeamTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.SuctionLaserBeamTileEntity;
import pixlepix.complexmachines.common.tileentity.FluxTileEntity;

import mekanism.api.IStrictEnergyAcceptor;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.core.block.IElectricityStorage;
import universalelectricity.core.electricity.ElectricityNetworkHelper;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.electricity.IElectricityNetwork;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.network.PacketManager;
import universalelectricity.prefab.tile.TileEntityElectricityRunnable;

import com.google.common.io.ByteArrayDataInput;

public class LaserEmitterTileEntity extends PowerConsumerComplexTileEntity {
	public final double WATTS_PER_TICK = 5000;
	public final double TRANSFER_LIMIT = 12500;
	private int drawingTicks = 0;
	private double joulesStored = 0;

	public static double maxJoules = 25000;
	public int ticks = 0;
	/**
	 * The ItemStacks that hold the items currently being used in the wire mill;
	 * 0 = battery; 1 = input; 2 = output;
	 */

	public boolean tripped;

	private ItemStack[] inventory = new ItemStack[1];

	private int playersUsing = 0;
	public int orientation;
	private int targetID = 0;
	private int targetMeta = 0;

	private boolean initialized;
	private int internalId;
	public int laserBeamId;

	@Override
	public void initiate() {
		this.initialized = true;
	}
	public void removeBeam(){
		ForgeDirection direction=ForgeDirection.getOrientation(this.getBlockMetadata() + 2).getOpposite();
		if(worldObj.getBlockTileEntity(xCoord+direction.offsetX, yCoord, zCoord+direction.offsetZ)instanceof LaserBeamTileEntity){
			worldObj.setBlock(xCoord+direction.offsetX, yCoord, zCoord+direction.offsetZ,0);
		}
	}
	public void formBeam(int max, ForgeDirection laserDirection){
		for (int i = 1; i < max; i++) {
			
			TileEntity entity=worldObj.getBlockTileEntity(xCoord + laserDirection.offsetX*i, yCoord, zCoord + laserDirection.offsetZ * i);
			if (worldObj.getBlockId(xCoord + laserDirection.offsetX * i, yCoord, zCoord + laserDirection.offsetZ * i) == 0||entity instanceof LaserBeamTileEntity||entity instanceof FluxTileEntity) {
			
				worldObj.setBlock(xCoord+ laserDirection.offsetX * i, yCoord,zCoord + laserDirection.offsetZ * i,laserBeamId,laserDirection.ordinal(),3);

				TileEntity newEntity=worldObj.getBlockTileEntity(xCoord + laserDirection.offsetX*i, yCoord, zCoord + laserDirection.offsetZ * i);
				if (newEntity instanceof LaserBeamTileEntity) {
					LaserBeamTileEntity laserEntity = (LaserBeamTileEntity)newEntity;
					laserEntity.setEntity(this);

				}
					if (newEntity instanceof SuctionLaserBeamTileEntity) {
						SuctionLaserBeamTileEntity suctionEntity = (SuctionLaserBeamTileEntity)newEntity;
						suctionEntity.xDirection=-1*laserDirection.offsetX;
						suctionEntity.zDirection=-1*laserDirection.offsetZ;
					}


			}else {
				return;
			}
		}

	}

	public void beamMatching(int internalId){
		switch(internalId){

		case 276:
			laserBeamId=Config.blockStartingID+11;
			break;
		/*case 20:
			laserBeamId=Config.blockStartingID+12;
			//System.out.println("Laser of glass");
			break;
		*/
			//TODO: Fix Tripwire
		case 331:
			laserBeamId=Config.blockStartingID+14;
			//System.out.println("Laser of glass");
			break;
		case 399:
			laserBeamId=Config.blockStartingID+13;
			//System.out.println("Laser of glass");
			break;
		case 287:
			laserBeamId=Config.blockStartingID+20;
			//System.out.println("Laser of glass");
			break;
		case 326:
			laserBeamId=Config.blockStartingID+16;
			//System.out.println("Laser of glass");
			break;
		case 295:
			laserBeamId=Config.blockStartingID+17;
			//System.out.println("Laser of glass");
			break;
		case 328:
			laserBeamId=Config.blockStartingID+18;
			//System.out.println("Laser of glass");
			break;
		

		default:
			laserBeamId=Config.blockStartingID+10;
			break;


		}
	}
	public void unTrip(){
		if(worldObj.getTotalWorldTime()%200==0){
			worldObj.notifyBlockChange(xCoord, yCoord, zCoord, Config.blockStartingID+9);
			tripped=false;
		}
	}
	



	@Override
	public void updateEntity() {
		super.updateEntity();

		ForgeDirection inputDirection = ForgeDirection.getOrientation(this.getBlockMetadata() + 2);

		// System.out.println(getJoules());
		if (!this.worldObj.isRemote) {
				unTrip();

				ticks++;
				if (getJoules() > 10000) {
					ForgeDirection laserDirection = inputDirection.getOpposite();
					int id=worldObj.getBlockId(xCoord+ laserDirection.offsetX, yCoord, zCoord + laserDirection.offsetZ);
					if (worldObj.getTotalWorldTime()%500==2 ||id==0) {
						setJoules(getJoules() - 8000);

						beamMatching(internalId);
						int max=2;
						if(laserBeamId==Config.blockStartingID+17){
							max=4;
						}

						formBeam(max,laserDirection);


				}

			
		}else{
			removeBeam();
		}

		
		this.joulesStored = Math.min(this.joulesStored, this.getMaxJoules());
		this.joulesStored = Math.max(this.joulesStored, 0d);

		}
	}





	@Override
	public void handlePacketData(INetworkManager inputNetwork, int type,
			Packet250CustomPayload packet, EntityPlayer player,
			ByteArrayDataInput dataStream) {
		try {
			this.drawingTicks = dataStream.readInt();
			this.disabledTicks = dataStream.readInt();
			this.joulesStored = dataStream.readDouble();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		try {
			this.joulesStored = par1NBTTagCompound.getDouble("joulesStored");
			this.internalId = par1NBTTagCompound.getInteger("InternalId");
		} catch (Exception e) {
		}

		NBTTagList var2 = par1NBTTagCompound.getTagList("Items");

	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setDouble("joulesStored", this.getJoules());
		par1NBTTagCompound.setInteger("InternalId", this.internalId);

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








	public boolean onMachineActivated(World world, int x, int y, int z,
			EntityPlayer entityPlayer, int side, float hitX, float hitY,
			float hitZ) {

		ItemStack target=entityPlayer.getHeldItem();
		if(target!=null){
			internalId=target.itemID;
		}else{
			internalId=0;
		}
		removeBeam();
		return true;
	}



	public void notifyTripwire() {
		tripped=true;

	}

	
	



}