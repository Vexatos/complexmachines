package archadia.complexmachines.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.network.PacketManager;
import archadia.complexmachines.common.helper.recipes.WiremillRecipes;

import com.google.common.io.ByteArrayDataInput;

/**
 * @author Archadia
 * 
 */
public class TileEntityGrinder extends TileEntityBasicMachine implements IPacketReceiver {
	
	private final static TileEntityGrinder tileEntityBase = new TileEntityGrinder();   
	
	private static int processTicks; 
	
	public final static TileEntityGrinder instance() {
		return tileEntityBase;
	}
	 
	public TileEntityGrinder() {
		inventory = new ItemStack[3];
	}
	
	public void updateEntity() {
		boolean flag1 = false;
        if (!this.worldObj.isRemote)
        {
            if (this.canProcess())
            {
                ++processTicks;
    			PacketManager.sendPacketToClients(getDescriptionPacket(), this.worldObj, new Vector3(this.xCoord, this.yCoord, this.zCoord), 12);

                if (processTicks == 200) {
                	processTicks = 0;
        			PacketManager.sendPacketToClients(getDescriptionPacket(), this.worldObj, new Vector3(this.xCoord, this.yCoord, this.zCoord), 12);
                    processItems();
                    flag1 = true;
                }
            } else {
            	processTicks = 0;
            }
        }
        if (flag1)
        {
            this.onInventoryChanged();
        }
	}
	
	public int getProcessProgressScaled(int par1) {		
		return processTicks * par1 / 200;
	}
	
	public String getInvName() {
		return "Grinder";
	}
	
	public boolean canProcess() {
		if (inventory[0] == null && inventory[1] == null && inventory[2] == null) {
        	return false;
        } else {
        	return true;
        }
    }
	
	public void processItems() {
		if(canProcess()) {		
	        if (inventory[0] != null) {
	            --inventory[0].stackSize;
	        }
	        if(inventory[1] != null) {
		        --inventory[1].stackSize;
	        }	    	
	        if(inventory[2] != null) {
		        --inventory[2].stackSize;
	        }
	        
	        if(inventory[0].stackSize == 1) {
	        	inventory[0] = null;
	        }
	        if(inventory[1].stackSize == 1) {
	        	inventory[1] = null;
	        }
	        if(inventory[2].stackSize == 1) {
	        	inventory[2] = null;
	        }
		}
	}
	
	@Override
	public void handlePacketData(INetworkManager network, int packetType, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput dataStream) {		
		if(!worldObj.isRemote) {
			processTicks = dataStream.readInt();
		}
	}
	
	@Override
	public Packet getDescriptionPacket() {
		return PacketManager.getPacket("ComplexMachines", this, this.processTicks);
	}
}
