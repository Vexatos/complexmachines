package archadia.complexmachines.core.common.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import archadia.complexmachines.core.common.ComplexMachines;
import archadia.complexmachines.core.common.infopacket.InfoPacket;
import archadia.complexmachines.prefab.item.ItemBase;

/**
 * @author Archadia
 *
 */
public class ItemInfoPacket extends ItemBase {

	InfoPacket packet;
	
	TileEntity reactionTile;
	
	public ItemInfoPacket(int id, String name, int packetamt, TileEntity tile) {
		super(id, name);
		setCreativeTab(ComplexMachines.tabComplexMachines);
		setUnlocalizedName(name);
		packet = getNewPacket(name, packetamt);
		setReactionTile(tile);
	}
	
	private InfoPacket getNewPacket(String name, int packetamt) {
		return new InfoPacket(name, packetamt);
	}
	
	public InfoPacket getCurrentPacket() {
		return packet;
	}
	
	public void setReactionTile(TileEntity tile) {
		reactionTile = tile;
	}
	
	public TileEntity getReactionTile() {
		return reactionTile;
	}
	
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
    	for(int i = 0; i < packet.getPacketSize(); i++) {
    		list.add("" + packet.get(i));
    	}
    }

}
