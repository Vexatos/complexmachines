package pixlepix.complexmachines.common.crops;

import java.util.List;

import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mekanism.api.EnumColor;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;

public class Monsterweed extends Item {

	
	
	

	
	 @Override
	 @SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("ComplexMachines:monsterweed");
	}
	

    public Monsterweed(int i)
    {
        super(i);
        this.setUnlocalizedName("MonsterWeedItem");
        this.setCreativeTab(ComplexMachines.creativeTab);

    }
	
	

	
	
	
	
}
