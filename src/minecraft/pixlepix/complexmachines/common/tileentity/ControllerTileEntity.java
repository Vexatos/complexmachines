package pixlepix.complexmachines.common.tileentity;

import java.util.ArrayList;
import java.util.List;

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
		if(worldObj.getTotalWorldTime()%100==0&&getJoules()>10000){
			ArrayList<ForgeDirection> directions=new ArrayList();
			/*if(worldObj.getIndirectPowerLevelTo(xCoord, yCoord, zCoord, 0)>0){
				directions.add(ForgeDirection.UP);
			}
			
			if(worldObj.getIndirectPowerLevelTo(xCoord, yCoord, zCoord, 1)>0){
				directions.add(ForgeDirection.DOWN);
				System.out.println("Activated");
			}
			if(worldObj.getIndirectPowerLevelTo(xCoord, yCoord, zCoord, 2)>0){
				directions.add(ForgeDirection.NORTH);
				System.out.println("Activated");
			}
			if(worldObj.getIndirectPowerLevelTo(xCoord, yCoord, zCoord, 3)>0){
				directions.add(ForgeDirection.SOUTH);
				System.out.println("Activated");
			}
			if(worldObj.getIndirectPowerLevelTo(xCoord, yCoord, zCoord, 4)>0){
				directions.add(ForgeDirection.WEST);
				System.out.println("Activated");
			}
			if(worldObj.getIndirectPowerLevelTo(xCoord, yCoord, zCoord, 5)>0){
				directions.add(ForgeDirection.EAST);
				System.out.println("Activated");
			}
			*/
			ForgeDirection movementDirection=ForgeDirection.DOWN;
			if(worldObj.getBlockPowerInput(xCoord, yCoord, zCoord)>0){
				movementDirection=ForgeDirection.UP;
			}
			int max=yCoord+10;
			if(movementDirection.offsetY==-1){
				max=yCoord+15;
				
			}
				setJoules(getJoules()-10000);
				for(int i=xCoord-10;i<xCoord+10;i++){
					
					for(int j=yCoord-10;j<max;j++){
						for(int k=zCoord-10;k<zCoord+10;k++){
							if(worldObj.getBlockTileEntity(i, j, k)instanceof MotorTileEntity){
								
								((MotorTileEntity)worldObj.getBlockTileEntity(i, j, k)).move(movementDirection);
								
							}
						}
					}
					
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