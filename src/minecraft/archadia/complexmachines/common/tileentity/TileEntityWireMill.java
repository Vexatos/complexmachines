package archadia.complexmachines.common.tileentity;

import java.io.IOException;

import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.network.PacketManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.fluids.FluidStack;
import archadia.complexmachines.common.helper.ArchHelper;
import archadia.complexmachines.common.helper.recipes.WiremillRecipes;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.network.IPacketHandler;

/**
 * @author Archadia
 *
 */
public class TileEntityWireMill extends TileEntityBasicMachine implements IPacketReceiver {
	
	public static int processTime;
	
	private final static TileEntityWireMill tileEntityBase = new TileEntityWireMill();   
	 
	public final static TileEntityWireMill instance() {
		return tileEntityBase;
		
	}
	 
	public TileEntityWireMill() {
		inventory = new ItemStack[2];
        ArchHelper.println(""+processTime);
	}
	
	public void updateEntity() {
		boolean flag1 = false;
        if (!this.worldObj.isRemote)
        {
            if (this.canProcess())
            {
                ++processTime;
    			PacketManager.sendPacketToClients(PacketManager.getPacket("ComplexMachines", this, processTime), this.worldObj, new Vector3(this.xCoord, this.yCoord, this.zCoord), 12);

                if (processTime == 200)
                {
                	processTime = 0;
                    processItems();
                    flag1 = true;
                }
            } else {
            	processTime = 0;
            }
        }
        if (flag1)
        {
            this.onInventoryChanged();
        }
	}
	
    public int getProcessProgressScaled(int par1) {
    	return processTime * par1 / 200;
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
	public void handlePacketData(INetworkManager network, int packetType, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput dataStream) {		
		if(!worldObj.isRemote) {
			processTime = dataStream.readInt();
		}
	}
	
	@Override
	public Packet getDescriptionPacket() {
		return PacketManager.getPacket("ComplexMachines", this, this.processTime);
	}
}
