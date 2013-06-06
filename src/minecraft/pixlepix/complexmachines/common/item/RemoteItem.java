package pixlepix.complexmachines.common.item;


import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import pixlepix.complexmachines.common.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.item.ItemElectric;
import universalelectricity.prefab.implement.IToolConfigurator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
public class RemoteItem extends ItemElectric 
{
    public RemoteItem(int par1)
    {
        super(par1);
        this.setUnlocalizedName("Remote");
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
			
			list.add(EnumColor.AQUA + "Shift click to link to a block");

			list.add(EnumColor.AQUA + "Right click to simulate a right click");

			list.add(EnumColor.AQUA + "For a linked block");
			list.add(EnumColor.DARK_GREEN + "100KJ per log mined");
		}
	}
    
    
   
    
    
    
  //Code from forge essentials
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
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player){
    	
    		MovingObjectPosition position=getPlayerLookingSpot(player,false);
    		if(player.isSneaking()){
    			if(!world.isRemote){
    			int x=position.blockX;
    			int y=position.blockY;
    			int z=position.blockZ;
		    	int targetId=world.getBlockId(x, y, z);
		    	TileEntity entity=world.getBlockTileEntity(x, y, z);	
		    	
		    		if(itemStack.stackTagCompound == null)
		        	{
		        		itemStack.setTagCompound(new NBTTagCompound());
		        	}
		    		itemStack.stackTagCompound.setInteger("x", x);
		
		    		itemStack.stackTagCompound.setInteger("y", y);
		
		    		itemStack.stackTagCompound.setInteger("z", z);
		    		player.sendChatToPlayer(EnumColor.AQUA+"Linked remote to a block");
    			}
		    		
		    	
    		}else{
    		
    			int blockX=itemStack.stackTagCompound.getInteger("x");

    			int blockY=itemStack.stackTagCompound.getInteger("y");

    			int blockZ=itemStack.stackTagCompound.getInteger("z");
    			int blockId=world.getBlockId(blockX,blockY,blockZ);
    			Block blockType=Block.blocksList[blockId];
    			if(blockType!=null){
    				//TODO get better values for these
    				FakePlayer fakePlayer=new FakePlayer(world,player);
    				fakePlayer.posX=blockX;
    				fakePlayer.posY=blockY;
    				fakePlayer.posZ=blockZ;

    		    	TileEntity entity=world.getBlockTileEntity(blockX,blockY,blockZ);	
    				
    					blockType.onBlockActivated(world, blockX, blockY, blockZ, fakePlayer,1, 0.5F, 0.5F, 0.5F);
    				
    			}
    		}
    	
    	
    	
    	

		return itemStack;
    }

    
    @Override
    public double getMaxJoules(ItemStack itemStack)
    {
        return 100000;
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
        this.itemIcon = par1IconRegister.registerIcon("ComplexMachines:Remote");
    }
}