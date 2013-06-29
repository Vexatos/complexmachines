package pixlepix.complexmachines.common;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.Player;

public class MinearRegistry implements ITickHandler {

	
	
	public static int quadrantOneSecurity=0;

	public static String quadrantOnePlayer;
	public static int quadrantOneDimension;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
	}
	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(MinecraftServer.getServer().worldServers[0].getTotalWorldTime()%2000==1){
			quadrantOneSecurity=0;
		}
		if(MinecraftServer.getServer().worldServers[0].getTotalWorldTime()%2000==3){
			ArrayList<EntityPlayer> players=new ArrayList<EntityPlayer>();
			
			for(WorldServer worldServer:MinecraftServer.getServer().worldServers){
				players.addAll(worldServer.playerEntities);
			}

			EntityPlayer targetPlayer=players.get(new Random().nextInt(players.size()));
			quadrantOnePlayer=targetPlayer.username;
			quadrantOneDimension=targetPlayer.worldObj.getWorldInfo().getDimension();
			
		}
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
