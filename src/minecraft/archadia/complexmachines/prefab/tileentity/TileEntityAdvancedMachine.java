package archadia.complexmachines.prefab.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import archadia.complexmachines.core.common.ComplexMachines;

import com.google.common.io.ByteArrayDataInput;

import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.network.PacketManager;

/**
 * @author Archadia
 *
 */
public class TileEntityAdvancedMachine extends TileEntityBasicMachine implements IPacketReceiver {
	
	protected int processTicks;
	protected static int processMaxTicks;
	
	public void setMaxTicks(int amt) {
		processMaxTicks = amt;
	}
	
	public int getMaxTicks() {
		return processMaxTicks;
	}

	public int getTicks() {
		return processTicks;
	}
	
	
	public void setInventorySize(int amt) {
		inventory = new ItemStack[amt];
	}
	
	public Packet getDescriptionPacket(Object... obj) {
		return PacketManager.getPacket(ComplexMachines.CHANNEL, this, obj);
	}

	@Override
	public void handlePacketData(INetworkManager network, int packetType,
			Packet250CustomPayload packet, EntityPlayer player,
			ByteArrayDataInput dataStream) {
		processTicks = dataStream.readInt();
	}
}
