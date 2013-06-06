package pixlepix.complexmachines.common.tileentity;

import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.PowerProducerComplexTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import net.minecraftforge.common.MinecraftForge;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.core.block.IConductor;
import universalelectricity.core.block.IElectricityStorage;
import universalelectricity.core.electricity.ElectricityNetworkHelper;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.electricity.IElectricityNetwork;
import universalelectricity.core.item.IItemElectric;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.network.PacketManager;
import universalelectricity.prefab.tile.TileEntityElectrical;
import universalelectricity.prefab.tile.TileEntityElectricityRunnable;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.Loader;

public class SinglePointTileEntity extends PowerProducerComplexTileEntity{
	
	public static double maxJoules = 2000000;
	/**
	 * The ItemStacks that hold the items currently being used in the wire mill;
	 * 0 = battery; 1 = input; 2 = output;
	 */

	private int playersUsing = 0;
	public int orientation;
	private int targetID = 0;
	private int targetMeta = 0;

	private boolean initialized;
	private IConductor connectedElectricUnit;

	@Override
	public void initiate() {
		this.initialized = true;
	}

	public void updateEntity() {
		
		ticks++;
		if(ticks%20==0){
			for(int i=0;i<256;i++){
				if(worldObj.getBlockId(xCoord, i, zCoord)==Config.blockStartingID+4&&i!=yCoord){
					worldObj.setBlock(xCoord, yCoord, zCoord, 0);
					return;
				}
			}
		}
		
		
		if (!this.worldObj.isRemote) {
			if (atCorrectLocation()) {
				super.updateEntity(); 
				

			

		}
	}
	}
	private boolean atCorrectLocation() {
		int target=Config.singlePointRadius;
			if(xCoord==target&&zCoord==target){
				return true;
			}
			if(xCoord==target&&zCoord==-1*target){
				return true;
			}
			if(xCoord==-1*target&&zCoord==target){
				return true;
			}
			if(xCoord==-1*target&&zCoord==-1*target){
				return true;
			}
			
		
		return false;
	}

	

}
