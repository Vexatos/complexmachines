package pixlepix.complexmachines.common.laser;

import pixlepix.complexmachines.client.ClientProxy;
import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.block.SinglePointGenerator;
import pixlepix.complexmachines.common.itemblock.LaserItemBlock;
import pixlepix.complexmachines.common.itemblock.SinglePointItemBlock;
import pixlepix.complexmachines.common.tileentity.SinglePointTileEntity;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.prefab.block.BlockAdvanced;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LaserEmitter extends BasicComplexBlock {
	static int blockIdIncrement=9;
	public LaserEmitter() {
		super(9);
	}
	String textureBase="ComplexMachines:";
	public String textureSpecific="LaserEmitterModel";

	@Override
	public Class getTileEntityClass() {
		return LaserEmitterTileEntity.class;
	}

	@Override
	public void addRecipe() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ComplexMachines.loader.getBlock(LaserEmitter.class)),true,new Object[]{"xyx", "yzy", "xyx", 'x', new ItemStack(Item.itemsList[264]), 'y', new ItemStack(Block.glowStone), 'z', "circuitElite"}));
		
	}

	@Override
	public String getName() {
		return "LaserEmitter";
	}
	@Override
	public boolean hasModel(){
		return true;
	}
	@Override
	public boolean hasItemBlock() {
		return true;
	}

	@Override
	public Class getItemBlock() {
		return LaserItemBlock.class;
		
	}
	
	
	@Override
	 public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ){
		super.onBlockActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ);
		LaserEmitterTileEntity entity=(LaserEmitterTileEntity)world.getBlockTileEntity(x,y,z);
	    return entity.onMachineActivated(world, x, y,z, entityPlayer, side, hitX, hitY, hitZ);
	 }
	
	@Override
	public boolean threeSidedTextures(){
		return false;
	}
	@Override
	public int isProvidingWeakPower(IBlockAccess iBlockAccess, int x, int y, int z, int par5)
    {
		TileEntity tileEntity=iBlockAccess.getBlockTileEntity(x, y, z);
		if(tileEntity instanceof LaserEmitterTileEntity){
			LaserEmitterTileEntity laserEmitterTileEntity=(LaserEmitterTileEntity)tileEntity;
			if(laserEmitterTileEntity.tripped){
				//System.out.println("Attempting to emit redstone power");
				return 15;
			}
		}
        return 0;
    }
	@Override
	public boolean canConnectRedstone(IBlockAccess iba, int i, int j, int k, int dir) 
    { 
        return true; 
    }
	@Override
	public int isProvidingStrongPower(IBlockAccess iBlockAccess, int x, int y, int z, int side)
    {
		TileEntity tileEntity=iBlockAccess.getBlockTileEntity(x, y, z);
		if(tileEntity instanceof LaserEmitterTileEntity){
			LaserEmitterTileEntity laserEmitterTileEntity=(LaserEmitterTileEntity)tileEntity;
			if(laserEmitterTileEntity.tripped){
				//System.out.println("Attempting to emit redstone power");
				return 15;
			}
		}
        return 0;
    }

	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}

	@Override
	public String getTop() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}

	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		 return textureSpecific;
	}
	
}
