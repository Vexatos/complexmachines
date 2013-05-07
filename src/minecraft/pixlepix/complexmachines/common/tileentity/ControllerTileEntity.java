package pixlepix.complexmachines.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import pixlepix.complexmachines.common.AirshipBlockRegistry;
import pixlepix.complexmachines.common.ComplexMachines;

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

public class ControllerTileEntity extends TileEntityElectricityRunnable implements IPacketReceiver, IElectricityStorage {

	private static final double TRANSFER_LIMIT = 10000;
	private double joulesStored;



	public void initiate() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void updateEntity(){
		super.updateEntity();

		if (!this.worldObj.isRemote) {

			ForgeDirection inputDirection = ForgeDirection.getOrientation(this
					.getBlockMetadata() + 2);
			TileEntity inputTile = VectorHelper.getTileEntityFromSide(
					this.worldObj, new Vector3(this), inputDirection);

			IElectricityNetwork inputNetwork = ElectricityNetworkHelper
					.getNetworkFromTileEntity(inputTile,
							inputDirection.getOpposite());

			if (inputNetwork != null) {
				if (this.joulesStored < getMaxJoules()) {
					inputNetwork.startRequesting(this,Math.min(this.getMaxJoules() - this.getJoules(),this.TRANSFER_LIMIT) / this.getVoltage(),this.getVoltage());
					ElectricityPack electricityPack = inputNetwork
							.consumeElectricity(this);
					this.setJoules(this.joulesStored
							+ electricityPack.getWatts());

					if (UniversalElectricity.isVoltageSensitive) {
						if (electricityPack.voltage > this.getVoltage()) {
							this.worldObj.createExplosion(null, this.xCoord,
									this.yCoord, this.zCoord, 2f, true);
						}
					}
				} else {
					inputNetwork.stopRequesting(this);
				}

			if(worldObj.getTotalWorldTime()%100==96){
				AirshipBlockRegistry.empty();
			}
			if(worldObj.getTotalWorldTime()%100==5){
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
			
			
			/*ForgeDirection movementDirection=ForgeDirection.DOWN;
			if(worldObj.getBlockPowerInput(xCoord, yCoord, zCoord)>0){
				movementDirection=ForgeDirection.UP;
			}
			*/
			
					
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



			@Override
			public double getVoltage() {
				return 480;
			}

			/**
			 * @return The amount of ticks required to draw this item
			 */

			
			@Override
			public double getJoules() {
				return this.joulesStored;
			}

			@Override
			public void setJoules(double joules) {
				this.joulesStored = joules;
			}

			@Override
			public double getMaxJoules() {
				return 200000;
			}

			@Override
			public boolean canConnect(ForgeDirection direction) {
				return direction.ordinal() == this.getBlockMetadata() + 2;
			}


			@Override
			public void handlePacketData(INetworkManager inputNetwork, int type,
					Packet250CustomPayload packet, EntityPlayer player,
					ByteArrayDataInput dataStream) {
				try {
					this.disabledTicks = dataStream.readInt();
					this.joulesStored = dataStream.readDouble();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
	
		}