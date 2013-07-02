package pixlepix.complexmachines.common.tileentity;

import java.util.ArrayList;

import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.CoordTuple;
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
		
		//System.out.println("Calculating Strength");
		int strength=1;
		
		int targetId=Config.blockStartingID+46;
		
			ArrayList<CoordTuple> list=new ArrayList<CoordTuple>();

			ArrayList<CoordTuple> found=new ArrayList<CoordTuple>();
			list.add(new CoordTuple(xCoord,yCoord,zCoord));
			while(list.size()>0){
				CoordTuple curr=list.get(0);
				ArrayList<CoordTuple> nearby=new ArrayList<CoordTuple>();
				int curX=(int)curr.x;
				int curY=(int)curr.y;
				int curZ=(int)curr.z;
				list.remove(0);
				nearby.add(new CoordTuple(curX+1,curY,curZ));
				nearby.add(new CoordTuple(curX-1,curY,curZ));
				nearby.add(new CoordTuple(curX,curY+1,curZ));
				nearby.add(new CoordTuple(curX,curY-1,curZ));
				nearby.add(new CoordTuple(curX,curY,curZ+1));
				nearby.add(new CoordTuple(curX,curY,curZ-1));
				for(int i=0;i<nearby.size();i++){
					if(nearby.get(i).getBlock(worldObj)==targetId){
						boolean alreadyFound=false;
						for(int j=0;j<found.size();j++){
							CoordTuple foundTuple=found.get(j);
							CoordTuple nearbyTuple=nearby.get(i);
							if(	foundTuple.x==nearbyTuple.x&&foundTuple.y==nearbyTuple.y&&foundTuple.z==nearbyTuple.z){
								alreadyFound=true;
								break;
								
							}
						}
						if(!alreadyFound){
							
							list.add(nearby.get(i));
							found.add(nearby.get(i));
						}
					}
				
			}
			}
		
		return found.size();
		
	}



	public void rightClick(EntityPlayer player) {
		if(worldObj.isRemote){
			return;
		}

		if(this.calculateStrength()>MinearRegistry.quadrantOneSecurity){
			MinearRegistry.quadrantOneSecurity=this.calculateStrength();

			player.sendChatToPlayer(EnumColor.DARK_GREEN+"Siezed control of minear");

			
		}
		if(this.calculateStrength()==MinearRegistry.quadrantOneSecurity){
			EntityPlayer targetPlayer=DimensionManager.getWorld(MinearRegistry.quadrantOneDimension).getPlayerEntityByName(MinearRegistry.quadrantOnePlayer);
			if(targetPlayer !=null){
				player.sendChatToPlayer(EnumColor.ORANGE+targetPlayer.username);
				player.sendChatToPlayer(EnumColor.AQUA+"X: "+targetPlayer.posX+"Y: "+targetPlayer.posY+"Z: "+targetPlayer.posZ);
			
			}else{
				player.sendChatToPlayer(EnumColor.RED+"No Players Found");
			}
		}else{
			player.sendChatToPlayer(EnumColor.RED+"You don't have minear control.");

			player.sendChatToPlayer(EnumColor.RED+"Opposing force is "+MinearRegistry.quadrantOneSecurity);
		}
	}

	

	

}