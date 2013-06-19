package pixlepix.complexmachines.common.block;

import pixlepix.complexmachines.client.ClientProxy;
import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.itemblock.OceanGeneratorItemBlock;
import pixlepix.complexmachines.common.itemblock.ReplacerItemBlock;
import pixlepix.complexmachines.common.tileentity.OceanGeneratorTileEntity;
import pixlepix.complexmachines.common.tileentity.ReplacerMachineTileEntity;
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

public class ReplacerMachine extends BasicComplexBlock {
	static int blockIdIncrement=6;
	public ReplacerMachine() {
		super(6);
	}
	String textureBase="ComplexMachines:";
	public String textureSpecific="ReplacerFront";

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
	 public String textureSpecificTop="ReplacerTop";

	public String textureSpecificConnector="ReplacerInput";

	@Override
	public Class getTileEntityClass() {
		return ReplacerMachineTileEntity.class;
	}

	@Override
	public void addRecipe() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ComplexMachines.loader.getBlock(ReplacerMachine.class)),true,new Object[]{"xxx", "yzy", "xxx", 'x',"plateSteel", 'y', new ItemStack(ComplexMachines.loader.getBlock(FillerMachine.class)), 'z', "ingotSteel"}));
		
	}

	@Override
	public String getName() {
		return "Replacer";
	}

	@Override
	public boolean hasItemBlock() {
		return true;
	}

	@Override
	public Class getItemBlock() {
		return ReplacerItemBlock.class;
		
	}
	@Override
	public boolean threeSidedTextures(){
		return true;
	}

}
