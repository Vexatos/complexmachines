package pixlepix.complexmachines.common.item;


import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import pixlepix.complexmachines.common.*;
import pixlepix.complexmachines.common.mob.GeneticMob;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import universalelectricity.core.item.ItemElectric;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
public class GeneticRandomizer extends ItemElectric
{
    public GeneticRandomizer(int par1)
    {
        super(par1);
        this.setUnlocalizedName("Randomizer");
        maxStackSize=1;
        this.setCreativeTab(ComplexMachines.creativeTab);
    }
    @Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4){
    	super.addInformation(par1ItemStack,par2EntityPlayer, list, par4);
		if(!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			list.add("Hold " + EnumColor.AQUA + "shift" + EnumColor.GREY + " for more details.");
		}
		else {
			
			list.add(EnumColor.AQUA + "Randomizes the genes of mobs");
			list.add(EnumColor.DARK_GREEN + "100KJ per mob");
		}
	}
    @Override
    public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving)
    {
        if(!(par2EntityLiving instanceof EntityPlayer)){
        	par2EntityLiving.worldObj.spawnEntityInWorld(new GeneticMob(par2EntityLiving.worldObj));
        	par2EntityLiving.setDead();
        	
        }
    	
    	return true;
    }

    
    @Override
    public double getMaxJoules(ItemStack itemStack)
    {
        return 20000000;
    }
    
    @Override
    public double getVoltage(ItemStack itemStack)
    {
        return 240;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("ComplexMachines:ClusterMiner");
    }
}