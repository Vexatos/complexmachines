package pixlepix.complexmachines.common.tileentity;

import pixlepix.complexmachines.common.ComplexMachines;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.StatCollector;
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

public class FillerMachineTileEntity extends PowerConsumerComplexTileEntity {
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

		if (!this.worldObj.isRemote) {

				if (getJoules() > 10000) {
					if (worldObj.getTotalWorldTime()%20 == 0) {

						int lowerBoundX = xCoord;

						int lowerBoundY = yCoord;

						int lowerBoundZ = zCoord;

						int upperBoundX = xCoord;

						int upperBoundY = yCoord;

						int upperBoundZ = zCoord;
						lowerBoundX = xCoord - 15;

						lowerBoundY = yCoord - 0;

						lowerBoundZ = zCoord - 15;

						upperBoundX = xCoord + 15;

						upperBoundY = yCoord + 15;

						upperBoundZ = zCoord + 15;
						boolean blocksChanged = false;
						for (int cycleX = lowerBoundX; cycleX < upperBoundX; cycleX++) {
							for (int cycleY = upperBoundY; cycleY > lowerBoundY; cycleY--) {
								for (int cycleZ = lowerBoundZ; cycleZ < upperBoundZ; cycleZ++) {
									if (worldObj.getBlockId(cycleX, cycleY,cycleZ) == 0) {
										
										if(!ComplexMachines.isProtected(cycleX, cycleZ)){
											worldObj.setBlock(cycleX, cycleY,cycleZ, 1);
										}
										setJoules(getJoules() - 10000);
										return;

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

	

	

}