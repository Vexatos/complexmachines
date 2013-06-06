package pixlepix.complexmachines.common.block;

import pixlepix.complexmachines.client.ClientProxy;
import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.itemblock.ExtractorItemBlock;
import pixlepix.complexmachines.common.itemblock.MotorItemBlock;
import pixlepix.complexmachines.common.tileentity.ExtractorMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.FillerMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.MotorTileEntity;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.prefab.block.BlockAdvanced;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Motor extends BasicComplexBlock {
	
	
	
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY,float hitZ) {
			super.onBlockActivated(par1World, x,  y, z, par5EntityPlayer,  side, hitX, hitY,hitZ);
			par5EntityPlayer.openGui(ComplexMachines.instance, 3, par1World, x, y, z);
			return true;
		

	}
	public Motor() {
		super(22);
	}
	static int blockIdIncrement=22;
	String textureBase="ComplexMachines:";
	public String textureSpecific="MotorFront";

	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}

	@Override
	public String getTop() {
		// TODO Auto-generated method stub
		return textureSpecificTop;
	}

	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		 return textureSpecificConnector;
	}
	 public String textureSpecificTop="MotorTop";

	public String textureSpecificConnector="MotorInput";

	@Override
	public Class getTileEntityClass() {
		return MotorTileEntity.class;
	}

	@Override
	public void addRecipe() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ComplexMachines.loader.getBlock(Motor.class)),true,new Object[]{"xyx", "yzy", "xyx", 'x', "circuitBasic", 'y', "plateSteel", 'z', "motor"}));
		
	}

	@Override
	public String getName() {
		return "Motor";
	}

	@Override
	public boolean hasItemBlock() {
		return true;
	}

	@Override
	public Class getItemBlock() {
		return MotorItemBlock.class;
		
	}
	@Override
	public boolean threeSidedTextures(){
		return true;
	}

}