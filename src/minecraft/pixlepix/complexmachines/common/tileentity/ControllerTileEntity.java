package pixlepix.complexmachines.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import pixlepix.complexmachines.common.AirshipBlockRegistry;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.PowerConsumerComplexTileEntity;

import com.google.common.io.ByteArrayDataInput;

import universalelectricity.core.UniversalElectricity;
import universalelectricity.core.block.IElectricityStorage;
import universalelectricity.core.electricity.ElectricityNetworkHelper;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.electricity.IElectricityNetwork;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.tile.TileEntityElectricityRunnable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;

public class ControllerTileEntity extends PowerConsumerComplexTileEntity{

	private static final double TRANSFER_LIMIT = 10000;



	public void initiate() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void updateEntity(){
		super.updateEntity();

		if (!this.worldObj.isRemote) {

			

			if(worldObj.getTotalWorldTime()%100==96){
				AirshipBlockRegistry.empty();
			}
			if(worldObj.getTotalWorldTime()%100==2){
				AirshipBlockRegistry.placeDelayed();
			}
		if(worldObj.getTotalWorldTime()%100==0&&getJoules()>10000){
			ForgeDirection[] directions={ForgeDirection.DOWN,ForgeDirection.UP,ForgeDirection.EAST,ForgeDirection.WEST,ForgeDirection.NORTH,ForgeDirection.SOUTH};
			
			for(ForgeDirection movementDirection:directions){
				if(worldObj.getIndirectPowerOutput(xCoord+movementDirection.offsetX, yCoord+movementDirection.offsetY, zCoord+movementDirection.offsetZ, movementDirection.ordinal())){
					if(!ComplexMachines.isProtected(xCoord, zCoord)){
						moveDirection(movementDirection);
					}
				}
			}
			
			
			
			
					
				}
				
				
			}
			
		}
		
		
	
			
	
			public void moveDirection(ForgeDirection movementDirection){
				
				

				int xMax=xCoord+20;
				int xMin=xCoord-20;
				
				int yMax=yCoord+20;
				int yMin=yCoord-20;
				

				int zMax=zCoord+20;
				int zMin=zCoord-20;
				
				if(movementDirection.offsetX>0){
					xMin-=5;
				}
				if(movementDirection.offsetX<0){
					xMin+=5;
				}
				if(movementDirection.offsetY>0){
					yMin-=5;
				}
				if(movementDirection.offsetY<0){
					yMin+=5;
				}
				if(movementDirection.offsetZ>0){
					zMin-=5;
				}
				if(movementDirection.offsetZ<0){
					zMin+=5;
				}
				
					setJoules(getJoules()-10000);
					for(int i=xMin;i<xMax;i++){
						for(int j=yMin;j<yMax;j++){
							for(int k=zMin;k<zMax;k++){
								
								
								if(worldObj.getBlockTileEntity(i, j, k)instanceof MotorTileEntity){
									
									((MotorTileEntity)worldObj.getBlockTileEntity(i, j, k)).move(movementDirection);
									
								}
							}
							}
					}
				
				
			}



			



			
	
	
		}