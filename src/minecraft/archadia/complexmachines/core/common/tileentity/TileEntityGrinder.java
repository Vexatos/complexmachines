package archadia.complexmachines.core.common.tileentity;

import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.network.PacketManager;
import archadia.complexmachines.prefab.tileentity.conductor.ConductorConsumerTileEntity;

/**
 * @author Archadia
 * 
 */
public class TileEntityGrinder extends ConductorConsumerTileEntity {
	
	private final static TileEntityGrinder tileEntityBase = new TileEntityGrinder();   
	
	private static int processTicks; 
	
	public final static TileEntityGrinder instance() {
		return tileEntityBase;
	}
	
	public TileEntityGrinder() {
		setInventorySize(3);
		setMaxTicks(200);
	}
	
	public void updateEntity() {
		boolean flag1 = false;
        if (!this.worldObj.isRemote)
        {
        	if(isProcessing()) {
	            this.processTicks++;
				PacketManager.sendPacketToClients(getDescriptionPacket(this.processTicks), this.worldObj, new Vector3(this.xCoord, this.yCoord, this.zCoord), 12);
				
	            if (this.processTicks == processMaxTicks) {
	            	this.processTicks = 0;
	    			PacketManager.sendPacketToClients(getDescriptionPacket(this.processTicks), this.worldObj, new Vector3(this.xCoord, this.yCoord, this.zCoord), 12);
	                processItems();
	                flag1 = true;
	            }
        	}
        }
        if (flag1)
        {
            this.onInventoryChanged();
        }
	}
	
	public int getProcessProgressScaled(int par1) {		
		return this.processTicks * par1 / 200;
	}
	
	public int getEnergyCapacityScaled(int par1) {
		return 0;
	}
	
	public String getInvName() {
		return "Grinder";
	}
	
	public boolean isProcessing() {
		if(inventory[0] == null && inventory[1] == null && inventory[2] == null) {
			return false;
		}
		if(inventory[0] != null) {
			return true;
		}
		if(inventory[1] != null) {
			return true;
		}
		if(inventory[2] != null) {
			return true;
		}
		return false;
	}
	
	public void processItems() {
		if(inventory[0] != null) {
			--inventory[0].stackSize;
	    	
	        if (inventory[0].stackSize <= 0)
	        {
	            inventory[0] = null;
	        }
		}
		if(inventory[1] != null) {
			--inventory[1].stackSize;
	    	
	        if (inventory[1].stackSize <= 0)
	        {
	            inventory[1] = null;
	        }
		}
		if(inventory[2] != null) {
			--inventory[2].stackSize;
	    	
	        if (inventory[2].stackSize <= 0)
	        {
	            inventory[2] = null;
	        }
		}
	}

	@Override
	public double getMaxJoules() {
		return 0;
	}
}
