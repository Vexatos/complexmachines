package pixlepix.complexmachines.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.EnumColor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MinearCloaker extends Item {

	public MinearCloaker(int par1) {
		super(par1);		

        this.setUnlocalizedName("Minear Cloaker");
        maxStackSize=1;
        this.setCreativeTab(ComplexMachines.creativeTab);
	}

	@Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
    	
    		
    			if(!world.isRemote){
	    			if(world.getBlockId(par4, par5, par6)==Config.blockStartingID+46){
	    				world.destroyBlock(par4, par5, par6, false);
	    				if(itemStack.stackTagCompound == null)
			        	{
			        		itemStack.setTagCompound(new NBTTagCompound());
			        	}
	    				int damage=itemStack.stackTagCompound.getInteger("Strength");
	    				damage+=5;
	    				itemStack.stackTagCompound.setInteger("Strength", damage);
	    				//itemStack.setItemDamage(damage);
	    				player.addChatMessage(EnumColor.PINK+"Absorbed a Minear Amplifier");

	    				player.addChatMessage(EnumColor.PINK+"Strength is now: "+damage);
	    			}
    			}
		    		
		    	
    		
    	
    	
    	
    	

		return true;
    }
	
	
	public int getMaxDamage()
    {
        return 25000;
    }
	
	
	public static MovingObjectPosition getPlayerLookingSpot(EntityPlayer player, boolean restrict)
  	{
  		float var4 = 1.0F;
  		float var5 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * var4;
  		float var6 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * var4;
  		double var7 = player.prevPosX + (player.posX - player.prevPosX) * var4;
  		double var9 = player.prevPosY + (player.posY - player.prevPosY) * var4 + 1.62D - player.yOffset;
  		double var11 = player.prevPosZ + (player.posZ - player.prevPosZ) * var4;
  		Vec3 var13 = player.worldObj.getWorldVec3Pool().getVecFromPool(var7, var9, var11);
  		float var14 = MathHelper.cos(-var6 * 0.017453292F - (float) Math.PI);
  		float var15 = MathHelper.sin(-var6 * 0.017453292F - (float) Math.PI);
  		float var16 = -MathHelper.cos(-var5 * 0.017453292F);
  		float var17 = MathHelper.sin(-var5 * 0.017453292F);
  		float var18 = var15 * var16;
  		float var20 = var14 * var16;
  		double var21 = 50D;
  		if (player instanceof EntityPlayerMP && restrict)
  		{
  			var21 = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
  		}
  		Vec3 var23 = var13.addVector(var18 * var21, var17 * var21, var20 * var21);
  		return player.worldObj.rayTraceBlocks_do_do(var13, var23, false, !true);
  	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("ComplexMachines:MinearCloaker");
    }
	
}
