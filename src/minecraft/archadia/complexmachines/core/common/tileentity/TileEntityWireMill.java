package archadia.complexmachines.core.common.tileentity;

import net.minecraft.item.ItemStack;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.network.PacketManager;
import archadia.complexmachines.helper.recipes.WiremillRecipes;
import archadia.complexmachines.prefab.tileentity.conductor.ConductorConsumerTileEntity;

/**
 * @author Archadia
 *
 */
public class TileEntityWireMill extends ConductorConsumerTileEntity {
		
	private final static TileEntityWireMill tileEntityBase = new TileEntityWireMill();   
	 	
	public final static TileEntityWireMill instance() {
		return tileEntityBase;
	}
	 
	public TileEntityWireMill() {
		setInventorySize(2);
		setMaxTicks(200);
	}
	
	public void updateEntity() {
		boolean flag1 = false;
        if (!this.worldObj.isRemote)
        {
            if (this.canProcess())
            {
            	this.processTicks++;
    			PacketManager.sendPacketToClients(getDescriptionPacket(this.processTicks), this.worldObj, new Vector3(this.xCoord, this.yCoord, this.zCoord), 12);
    	    	
    			if(!worldObj.isRemote) {
    			System.out.println("UPDATEENTITY() SERVER: " + getTicks());
    			}
    			
    	    	if(worldObj.isRemote) {
        	    	System.out.println("UPDATEENTITY() CLIENT: " + getTicks());
    	    	}
    	    	
                if (this.processTicks == processMaxTicks)
                {
                	this.processTicks = 0;
        			PacketManager.sendPacketToClients(getDescriptionPacket(this.processTicks), this.worldObj, new Vector3(this.xCoord, this.yCoord, this.zCoord), 12);
                    processItems();
                    flag1 = true;
                }
            } else {
            	this.processTicks = 0;
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
	
	public String getInvName() {
		return "Wire Mill";
	}
	
	public boolean canProcess() {
		if (inventory[0] == null) {
        	return false;
        } else {
            ItemStack itemstack =  WiremillRecipes.recipes().getResult(inventory[0].itemID);
            if (itemstack == null) {
            	return false;
            }
            if (inventory[1] == null) {
            	return true;
            }
            if (!inventory[1].isItemEqual(itemstack)) { 
            	return false;
            }
            int result = inventory[1].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }
	
	public void processItems() {
		if(canProcess()) {
            ItemStack itemstack =  WiremillRecipes.recipes().getResult(inventory[0].itemID);
	
	        if (inventory[1] == null)
	        {
	        	inventory[1] = itemstack.copy();
	        }
	        else if (inventory[1].isItemEqual(itemstack))
	        {
	        	inventory[1].stackSize += itemstack.stackSize;
	        } 
	
	        --inventory[0].stackSize;
	
	        if (inventory[0].stackSize <= 0)
	        {
	            inventory[0] = null;
	        }
		}
	}

	@Override
	public double getMaxJoules() {
		return 0;
	}
}
