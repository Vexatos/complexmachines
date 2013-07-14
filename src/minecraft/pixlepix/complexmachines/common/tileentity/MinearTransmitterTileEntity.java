package pixlepix.complexmachines.common.tileentity;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.CoordTuple;
import pixlepix.complexmachines.common.EnumColor;
import pixlepix.complexmachines.common.MinearRegistry;
import pixlepix.complexmachines.common.PowerConsumerComplexTileEntity;

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

			player.addChatMessage(EnumColor.DARK_GREEN+"Siezed control of minear");

			
		}
		if(this.calculateStrength()==MinearRegistry.quadrantOneSecurity){
			EntityPlayer targetPlayer=DimensionManager.getWorld(MinearRegistry.quadrantOneDimension).getPlayerEntityByName(MinearRegistry.quadrantOnePlayer);
			if(targetPlayer !=null){
				player.addChatMessage(EnumColor.ORANGE+targetPlayer.username);
				player.addChatMessage(EnumColor.AQUA+"X: "+targetPlayer.posX+"Y: "+targetPlayer.posY+"Z: "+targetPlayer.posZ);
			
			}else{
				player.addChatMessage(EnumColor.RED+"No Players Found");
			}
		}else{
			player.addChatMessage(EnumColor.RED+"You don't have minear control.");

			player.addChatMessage(EnumColor.RED+"Opposing force is "+MinearRegistry.quadrantOneSecurity);
		}
	}



	@Override
	public double getMaxJoules() {
		// TODO Auto-generated method stub
		return 2000000;
	}

	

	

}