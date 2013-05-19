package pixlepix.complexmachines.common.itemblock;

import java.util.List;

import org.lwjgl.input.Keyboard;

import pixlepix.complexmachines.common.EnumColor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ControllerItemBlock extends ItemBlock {

	public ControllerItemBlock(int par1) {
		super(par1);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4){
		if(!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			list.add("Hold " + EnumColor.AQUA + "shift" + EnumColor.GREY + " for more details.");
		}
		else {
			
			list.add(EnumColor.AQUA + "This is part of an airship");
			list.add(EnumColor.ORANGE + "Activates nearby motors");
			list.add(EnumColor.ORANGE + "Power it with redstone");
			list.add(EnumColor.ORANGE + "Power it in the direction");
			list.add(EnumColor.ORANGE + "You want to move it");
			list.add(EnumColor.ORANGE + "Also needs a motor to move");
			list.add(EnumColor.DARK_GREEN + "2KW while in use");
		}
	}

}
