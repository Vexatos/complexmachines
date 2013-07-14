package pixlepix.complexmachines.common.tileentity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntityChest;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.PowerConsumerComplexTileEntity;

import com.google.common.io.ByteArrayDataInput;

public class ReplacerMachineTileEntity extends PowerConsumerComplexTileEntity {
	public final double WATTS_PER_TICK = 5000;
	public final double TRANSFER_LIMIT = 125000;
	private int drawingTicks = 0;
	private double joulesStored = 0;
	public static double maxJoules = 5000000;
	public int ticks;
	Random rand = new Random();
	private int playersUsing = 0;
	public int orientation;

	

	@Override
	public void initiate() {
	}

	public int getBlockFromChest(TileEntityChest chest) {

		for (int i = 0; i < chest.getSizeInventory(); i++) {
			if (chest.getStackInSlot(i) != null) {
				int id = chest.getStackInSlot(i).itemID;
				if (Block.blocksList.length > id && Block.blocksList[id] != null) {
					return chest.getStackInSlot(i).itemID;
				}
			}
		}

		return 0;

	}

	public int takeBlockFromChest(TileEntityChest chest, int id) {

		for (int i = 0; i < chest.getSizeInventory(); i++) {
			if (chest.getStackInSlot(i) != null) {
				if (chest.getStackInSlot(i).itemID == id) {
					if (chest.getStackInSlot(i).stackSize > 1) {
						chest.getStackInSlot(i).stackSize--;
					} else {
						chest.setInventorySlotContents(i, null);
					}
					return 1;
				}
			}
		}

		return 0;

	}

	@Override
	public void updateEntity() {
		// System.out.println("Recieving ticks");
		super.updateEntity();

		if (!this.worldObj.isRemote) {
			

				if (getJoules() > 10000)

				{
					ticks++;
					if (ticks > 2) {
						ticks = 0;
						if (worldObj.getBlockTileEntity(xCoord, yCoord + 1,
								zCoord) != null
								&& worldObj.getBlockTileEntity(xCoord,
										yCoord + 1, zCoord) instanceof TileEntityChest) {
							TileEntityChest inputChest = (TileEntityChest) worldObj
									.getBlockTileEntity(xCoord, yCoord + 1,
											zCoord);
							int idToReplace = getBlockFromChest(inputChest);
							int lowerBoundX = xCoord;

							int lowerBoundY = yCoord;

							int lowerBoundZ = zCoord;

							int upperBoundX = xCoord;

							int upperBoundY = yCoord;

							int upperBoundZ = zCoord;

							lowerBoundX = xCoord - 50;

							lowerBoundY = yCoord - 2;

							lowerBoundZ = zCoord - 50;

							upperBoundX = xCoord + 50;

							upperBoundY = yCoord + 50;

							upperBoundZ = zCoord + 50;
							if (idToReplace != 0) {
								for (int cycleX = lowerBoundX; cycleX < upperBoundX; cycleX++) {
									for (int cycleY = lowerBoundY; cycleY < upperBoundY; cycleY++) {
										for (int cycleZ = lowerBoundZ; cycleZ < upperBoundZ; cycleZ++) {
											if (worldObj.getBlockId(cycleX,cycleY, cycleZ) == 1) {
												if(!ComplexMachines.isProtected(cycleX, cycleZ)){
													worldObj.setBlock(cycleX,cycleY, cycleZ,idToReplace);
												}
												// System.out.println("X: "+cycleX+"Y: "+cycleY+"Z: "+cycleZ);
												setJoules(getJoules() - 10000);
												takeBlockFromChest(inputChest,idToReplace);
												return;
											}
										}
									}
								}
							}

						}
					}
				}
		}

		this.joulesStored = Math.min(this.joulesStored, this.getMaxJoules());
		this.joulesStored = Math.max(this.joulesStored, 0d);
	}

	@Override
	public void handlePacketData(INetworkManager inputNetwork, int type,
			Packet250CustomPayload packet, EntityPlayer player,
			ByteArrayDataInput dataStream) {
		try {
			this.joulesStored = dataStream.readDouble();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public double getMaxJoules() {
		// TODO Auto-generated method stub
		return 20000;
	}

	
}
