package pixlepix.complexmachines.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.EnumColor;
import pixlepix.complexmachines.common.FakePlayer;
import pixlepix.complexmachines.common.tileentity.NodeTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LinkerItem extends Item {

	public LinkerItem(int par1) {
		super(par1);		

        this.setUnlocalizedName("Link Former");
        maxStackSize=1;
        this.setCreativeTab(ComplexMachines.creativeTab);
	}

	@Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
    	
    		
    			if(!world.isRemote){
	    			int x=par4;
	    			int y=par5;
	    			int z=par6;
			    	int targetId=world.getBlockId(x, y, z);
			    	TileEntity entity=world.getBlockTileEntity(x, y, z);	

			    	
			    	if(itemStack.stackTagCompound == null)
		        	{
		        		itemStack.setTagCompound(new NBTTagCompound());
		        	}
			    	
    				NBTTagCompound compound=itemStack.stackTagCompound;
    				int oldX=compound.getInteger("x");
    				int oldY=compound.getInteger("y");
    				int oldZ=compound.getInteger("z");
    				int side=compound.getInteger("side");
			    		
			    		if(itemStack.stackTagCompound.hasKey("x")){
			    			if(entity instanceof NodeTileEntity){
			    				NodeTileEntity nodeEntity=(NodeTileEntity)entity;
			    				nodeEntity.formLink(oldX, oldY, oldZ, x, y, z, side, par7);

					    		player.sendChatToPlayer(EnumColor.AQUA+"Link created");
					    		compound.removeTag("x");
			    				compound.removeTag("y");

			    				compound.removeTag("z");

			    				compound.removeTag("side");
			    				
			    				
			    			}else{

						    	entity=world.getBlockTileEntity(oldX, oldY, oldZ);	

				    			if(entity instanceof NodeTileEntity){

				    				NodeTileEntity nodeEntity=(NodeTileEntity)entity;

				    				nodeEntity.formLink(oldX, oldY, oldZ, x, y, z, side, par7);

						    		player.sendChatToPlayer(EnumColor.AQUA+"Link created");
				    			}else{
				    				player.sendChatToPlayer(EnumColor.RED+"You must include a node in the link");
				    				
				    				return false;
				    			}
				    			compound.removeTag("x");
			    				compound.removeTag("y");

			    				compound.removeTag("z");

			    				compound.removeTag("side");
			    			}
			    		}else{
				    		itemStack.stackTagCompound.setInteger("x", x);
				    		
				
				    		itemStack.stackTagCompound.setInteger("y", y);
				
				    		itemStack.stackTagCompound.setInteger("z", z);

				    		itemStack.stackTagCompound.setInteger("side", par7);
				    		
				    		player.sendChatToPlayer(EnumColor.ORANGE+"Click again to form a link");
			    		}
    			}
		    		
		    	
    		
    	
    	
    	
    	

		return true;
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
        this.itemIcon = par1IconRegister.registerIcon("ComplexMachines:Linker");
    }
	
}
