package pixlepix.complexmachines.common;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class MinearRegistry implements ITickHandler {

	
	
	public static int quadrantOneSecurity=0;

	public static String quadrantOnePlayer="Player";
	public static int quadrantOneDimension;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
	}
	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(MinecraftServer.getServer().worldServers[0].getTotalWorldTime()%Config.minearRefreshRate==1){
			quadrantOneSecurity=0;
		}
		if(MinecraftServer.getServer().worldServers[0].getTotalWorldTime()%Config.minearRefreshRate==3){
			ArrayList<EntityPlayer> players=new ArrayList<EntityPlayer>();
			
			for(WorldServer worldServer:MinecraftServer.getServer().worldServers){
				players.addAll(worldServer.playerEntities);
			}
			if(players.size()<1){
				return;
				
			}
			EntityPlayer targetPlayer=players.get(new Random().nextInt(players.size()));
			if(isProtected(quadrantOneSecurity,targetPlayer)){
				quadrantOnePlayer=targetPlayer.username;
				quadrantOneDimension=targetPlayer.worldObj.provider.dimensionId;
				//targetPlayer.sendChatToPlayer(EnumColor.RED+"You are being tracked by minear");
			}else{
				targetPlayer.addChatMessage(EnumColor.AQUA+"You have successfully avoided been tracked by minear");
			}
			
		}
	}
	public boolean isProtected(int strength, EntityPlayer player){
		for(int i=0;i<player.inventory.getSizeInventory();i++){
			ItemStack stack=player.inventory.getStackInSlot(i);
			if(stack!=null&&stack.getItem()==ComplexMachines.minearCloaker){
				if(getStrength(stack)>strength){
					return false;
				}
			}
		}
		return true;
		
	}
	
	private int getStrength(ItemStack stack) {
		if(stack!=null){
			if(stack.stackTagCompound!=null){
				return stack.stackTagCompound.getInteger("Strength");
					
			}
		}
		return 0;
	}
	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.WORLD);
	}
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Minear Block Registry";
	}

}
