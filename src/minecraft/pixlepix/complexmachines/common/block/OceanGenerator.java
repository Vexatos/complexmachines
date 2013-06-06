package pixlepix.complexmachines.common.block;

import java.util.Random;

import pixlepix.complexmachines.client.ClientProxy;
import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.itemblock.ExtractorItemBlock;
import pixlepix.complexmachines.common.itemblock.OceanGeneratorItemBlock;
import pixlepix.complexmachines.common.tileentity.ExtractorMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.OceanGeneratorTileEntity;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.prefab.block.BlockAdvanced;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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

public class OceanGenerator extends BasicComplexBlock {
	static int blockIdIncrement=5;
	public OceanGenerator() {
		super(5);
	}
	String textureBase="ComplexMachines:";
	public String textureSpecific="OceanGeneratorModel";

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
	public boolean hasModel(){
		return true;
	}
	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		 return textureSpecificConnector;
	}
	 public String textureSpecificTop="null";

	public String textureSpecificConnector="null";

	@Override
	public Class getTileEntityClass() {
		return OceanGeneratorTileEntity.class;
	}

	@Override
	public void addRecipe() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ComplexMachines.loader.getBlock(OceanGenerator.class)),true,new Object[]{"xyx", "zyz", "xyx", 'x',new ItemStack(Item.bucketWater), 'y', "circuitElite", 'z', "plateSteel"}));
		
	}

	@Override
	public String getName() {
		return "OceanGenerator";
	}

	@Override
	public boolean hasItemBlock() {
		return true;
	}

	@Override
	public Class getItemBlock() {
		return OceanGeneratorItemBlock.class;
		
	}
	@Override
	public boolean threeSidedTextures(){
		return false;
	}
}
