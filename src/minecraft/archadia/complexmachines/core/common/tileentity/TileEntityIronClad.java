package archadia.complexmachines.core.common.tileentity;

import net.minecraft.item.ItemStack;
import archadia.complexmachines.helper.ArchHelper;
import archadia.complexmachines.helper.recipes.MachineRecipes;
import archadia.complexmachines.network.PacketHandler;
import archadia.complexmachines.network.packet.PacketIronClad;
import archadia.complexmachines.network.packet.PacketWireMill;
import archadia.complexmachines.prefab.tileentity.ElectricProducerMachine;

/**
 * @author Archadia
 *
 */
public class TileEntityIronClad extends ElectricProducerMachine {
	
	private final static TileEntityIronClad tileEntityBase = new TileEntityIronClad();   
	 	
	public int heat = 1000;
	
	public final static TileEntityIronClad instance() {
		return tileEntityBase;
	}
	 
	public TileEntityIronClad() {
		setInventorySize(2);
		setMaxTicks(200);
	}
	
	public void updateEntity() {

	}

	
    public int getProcessProgressScaled(int par1) {   
    	return this.processTicks * par1 / 200;
    }
	
	public String getInvName() {
		return "Wire Mill";
	}
	
    public void sendUpdatePacket() {
        PacketIronClad packet = new PacketIronClad(this);
        int dimensionID = worldObj.provider.dimensionId;
        PacketHandler.instance().ironCladUpdateHandler.sendToAllPlayersInDimension(packet, dimensionID);
    }
}
