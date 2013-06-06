package pixlepix.complexmachines.common.tileentity;

import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.PowerProducerComplexTileEntity;
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
import universalelectricity.core.block.IConductor;
import universalelectricity.core.block.IElectricityStorage;
import universalelectricity.core.electricity.ElectricityNetworkHelper;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.electricity.IElectricityNetwork;
import universalelectricity.core.item.IItemElectric;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.network.PacketManager;
import universalelectricity.prefab.tile.TileEntityElectrical;
import universalelectricity.prefab.tile.TileEntityElectricityRunnable;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.Loader;

public class OceanGeneratorTileEntity extends PowerProducerComplexTileEntity {
	private double drawingTicks = 0;
	private double joulesStored = 0;
	public double electricOutput = 0;
	public static double maxJoules = 2000000;
	public int ticks = 1000;
	/**
	 * The ItemStacks that hold the items currently being used in the wire mill;
	 * 0 = battery; 1 = input; 2 = output;
	 */

	public int orientation;

	@Override
	public void initiate() {
		getOceanTiles();
	}

	public void updateEntity() {
		// System.out.println("Focal Points have been spawned at "+"  "+xCoord+"  "+yCoord+"  "+zCoord);
		super.updateEntity();
		if(worldObj.getTotalWorldTime()%1000==0){
			getOceanTiles();
		}
		
	}

	private void getOceanTiles() {
		// System.out.println(powerRunning);
		ticks++;
		if (ticks > 2000 || electricOutput < 1) {

			ticks = 0;
			int lowerBoundX = xCoord;

			int lowerBoundY = yCoord;

			int lowerBoundZ = zCoord;

			int upperBoundX = xCoord;

			int upperBoundY = yCoord;

			int upperBoundZ = zCoord;
			lowerBoundX = xCoord - 30;

			lowerBoundY = yCoord - 30;

			lowerBoundZ = zCoord - 30;

			upperBoundX = xCoord + 30;

			upperBoundY = yCoord + 30;

			upperBoundZ = zCoord + 30;

			for (int cycleX = lowerBoundX; cycleX < upperBoundX; cycleX++) {
				for (int cycleY = lowerBoundY; cycleY < upperBoundY; cycleY++) {
					for (int cycleZ = lowerBoundZ; cycleZ < upperBoundZ; cycleZ++) {
						if (worldObj.getBlockId(cycleX, cycleY, cycleZ) == 9) {
							electricOutput += Config.oceanGeneratorOutput;
						}
					}
				}
			}

		}

	}

	

}
