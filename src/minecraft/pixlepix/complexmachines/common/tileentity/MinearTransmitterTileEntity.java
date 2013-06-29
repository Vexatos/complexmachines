package pixlepix.complexmachines.common.tileentity;

import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.EnumColor;
import pixlepix.complexmachines.common.MinearRegistry;
import pixlepix.complexmachines.common.PowerConsumerComplexTileEntity;
import mekanism.api.IStrictEnergyAcceptor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import net.minecraftforge.common.MinecraftForge;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.core.block.IElectricityStorage;
import universalelectricity.core.electricity.ElectricityNetworkHelper;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.electricity.IElectricityNetwork;
import universalelectricity.core.item.IItemElectric;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.network.PacketManager;
import universalelectricity.prefab.tile.TileEntityElectricityRunnable;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.Loader;

public class MinearTransmitterTileEntity extends PowerConsumerComplexTileEntity {
	public final double WATTS_PER_TICK = 5000;
	public final double TRANSFER_LIMIT = 12500;
	private int drawingTicks = 0;
	private double joulesStored = 0;
	public static double maxJoules = 2000000;
	public int ticks = 0;

	private int playersUsing = 0;
	public int orientation;

	private boolean initialized;

	@Override
	public void initiate() {
	}
	
	

	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if(MinecraftServer.getServer().worldServers[0].getTotalWorldTime()%2000==2){
			int strength=calculateStrength();
			if(MinearRegistry.quadrantOneSecurity<strength){
				MinearRegistry.quadrantOneSecurity=strength;
			}
		}

		
	}
	
	
	public int calculateStrength(){
		return 1;
	}



	public void rightClick(EntityPlayer player) {
		if(worldObj.isRemote){
			return;
		}
		// TODO Auto-generated method stub
		if(this.calculateStrength()==MinearRegistry.quadrantOneSecurity){
			EntityPlayer targetPlayer=DimensionManager.getWorld(MinearRegistry.quadrantOneDimension).getPlayerEntityByName(MinearRegistry.quadrantOnePlayer);
			if(targetPlayer !=null){
				player.sendChatToPlayer(EnumColor.ORANGE+targetPlayer.username);
				player.sendChatToPlayer(EnumColor.AQUA+"X: "+targetPlayer.posX+"Y: "+targetPlayer.posY+"Z: "+targetPlayer.posZ);
			}else{
				player.sendChatToPlayer(EnumColor.RED+"You don't have minear control");
			}
			}
	}

	

	

}