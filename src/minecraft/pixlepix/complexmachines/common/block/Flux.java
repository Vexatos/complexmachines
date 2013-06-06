package pixlepix.complexmachines.common.block;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.itemblock.FillerItemBlock;
import pixlepix.complexmachines.common.tileentity.FillerMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.FluxTileEntity;
import universalelectricity.core.UniversalElectricity;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Flux extends BasicComplexBlock {

	
	static int blockIdIncrement=17;
	public Flux() {
		super(17);
	}
	String textureBase="ComplexMachines:";
	public String textureSpecific="Flux";

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
	 public String textureSpecificTop="null";

	public String textureSpecificConnector="null";

	@Override
	public Class getTileEntityClass() {
		return FluxTileEntity.class;
	}

	@Override
	public void addRecipe() {}

	@Override
	public String getName() {
		return "Flux";
	}

	@Override
	public boolean hasItemBlock() {
		return false;
	}

	
	@Override
	public boolean threeSidedTextures(){
		return false;
	}
	
	
	
	 
	
	
}
