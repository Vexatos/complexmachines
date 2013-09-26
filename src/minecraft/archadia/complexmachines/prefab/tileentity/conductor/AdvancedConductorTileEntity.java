package archadia.complexmachines.prefab.tileentity.conductor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.network.PacketManager;
import archadia.complexmachines.core.common.ComplexMachines;

import com.google.common.io.ByteArrayDataInput;

/**
 * @author Archadia
 *
 */
public abstract class AdvancedConductorTileEntity extends ConductorContainerTileEntity {
	
	protected int processTicks;
	protected static int processMaxTicks;
	protected boolean canOperate = false;
	
	public void setMaxTicks(int amt) {
		processMaxTicks = amt;
	}
	
	public int getMaxTicks() {
		return processMaxTicks;
	}

	public int getTicks() {
		return processTicks;
	}
	
	public void setStatusMode(boolean bool) {
		canOperate = bool;
	}
	
	public boolean getStatusMode() {
		return canOperate;
	}
	
	public void setInventorySize(int amt) {
		inventory = new ItemStack[amt];
	}
}
