package pixlepix.complexmachines.common.item;


import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import pixlepix.complexmachines.common.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import universalelectricity.core.item.ItemElectric;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
public class BeaconFormer extends ItemElectric
{
    public BeaconFormer(int par1)
    {
        super(par1);
        this.setUnlocalizedName("Beacon Former");
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
			
			list.add(EnumColor.AQUA + "Places a beacon above nearby players");
			list.add(EnumColor.DARK_GREEN + "100KJ per ore mined");
		}
	}
    
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
    		
    		int targetId=world.getBlockId(x, y, z);
    		if (!player.worldObj.isRemote)
    			return false;
    		List<EntityPlayer> entities=player.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(player.posX-30,player.posY-30,player.posZ-30,player.posX+30,player.posY+30,player.posZ+30));
    		for(int i=0;i<entities.size();i++){
    			EntityPlayer target=entities.get(i);
    		    player.worldObj.setBlock((int)target.posX, (int)target.posY, (int)target.posZ, Config.blockStartingID+10, 10, 3);
    		}
    			
    	
    	
    	
    	
		return false;
        
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