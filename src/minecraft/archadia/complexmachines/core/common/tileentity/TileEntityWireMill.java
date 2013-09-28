package archadia.complexmachines.core.common.tileentity;

import net.minecraft.item.ItemStack;
import archadia.complexmachines.helper.ArchHelper;
import archadia.complexmachines.helper.recipes.MachineRecipes;
import archadia.complexmachines.network.PacketHandler;
import archadia.complexmachines.network.packet.PacketWireMill;
import archadia.complexmachines.prefab.tileentity.ElectricConsumerMachine;

/**
 * @author Archadia
 *
 */
public class TileEntityWireMill extends ElectricConsumerMachine {
		
	public final static int[] input = { 0 };
	public final static int[] output = { 1 };
	
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
            	ArchHelper.println("Joules = " + getEnergyStored());
            	this.processTicks++;
            	sendUpdatePacket();
            	
                if (this.processTicks == getMaxTicks())
                {
                	this.processTicks = 0;
                	sendUpdatePacket();
                    processItems();
                    flag1 = true;
                }
            } else {
            	this.processTicks = 0;
            	sendUpdatePacket();
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
            ItemStack itemstack = MachineRecipes.Recipe.WIREMILL.getResult(inventory[0].itemID);
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
	
    public void sendUpdatePacket() {
        PacketWireMill packet = new PacketWireMill(this);
        int dimensionID = worldObj.provider.dimensionId;
        PacketHandler.instance().wiremillUpdateHandler.sendToAllPlayersInDimension(packet, dimensionID);
    }
	
	public void processItems() {
		if(canProcess()) {
            ItemStack itemstack = MachineRecipes.Recipe.WIREMILL.getResult(inventory[0].itemID);
	
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
	
	public int[] getAccessibleSlotsFromSide(int var1) {
		if(var1==1){
			return this.input;
		}
		if(var1==0){
			return this.output;
		}
		return this.input;
	}
}
