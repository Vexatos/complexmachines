package pixlepix.complexmachines.common.block;

import java.util.Random;

import pixlepix.complexmachines.client.ClientProxy;
import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.itemblock.ReplacerItemBlock;
import pixlepix.complexmachines.common.itemblock.SinglePointItemBlock;
import pixlepix.complexmachines.common.tileentity.ReplacerMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.SinglePointTileEntity;
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

public class SinglePointGenerator extends BasicComplexBlock {
	static int blockIdIncrement=4;
	public SinglePointGenerator() {
		super(4);
	}
	String textureBase="ComplexMachines:";
	public String textureSpecific="SingleModel";

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
	public boolean hasModel(){
		return true;
	}
	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		 return textureSpecific;
	}
	
	@Override
	public Class getTileEntityClass() {
		return SinglePointTileEntity.class;
	}

	@Override
	public void addRecipe() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ComplexMachines.loader.getBlock(SinglePointGenerator.class)),true,new Object[]{"xyx", "yxy", "xyx", 'x',"circuitBasic", 'y', "circuitElite"}));
		
	}

	@Override
	public String getName() {
		return "Single Point";
	}

	@Override
	public boolean hasItemBlock() {
		return true;
	}

	@Override
	public Class getItemBlock() {
		return SinglePointItemBlock.class;
		
	}
	@Override
	public boolean threeSidedTextures(){
		return false;
	}
}
