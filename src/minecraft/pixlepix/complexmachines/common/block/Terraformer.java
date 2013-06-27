package pixlepix.complexmachines.common.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.tileentity.TerraformerTileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class Terraformer extends BasicComplexBlock {
	
	
	public Terraformer() {
		super(23);
	}
	static int blockIdIncrement=23;
	String textureBase="ComplexMachines:";
	public String textureSpecific="Terraformer";

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
	 public String textureSpecificTop="Terraformer";

	public String textureSpecificConnector="TerraformerInput";

	@Override
	public Class getTileEntityClass() {
		return TerraformerTileEntity.class;
	}
	
	
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY,float hitZ) {
		super.onBlockActivated(par1World, x,  y, z, par5EntityPlayer,  side, hitX, hitY,hitZ);
		par5EntityPlayer.openGui(ComplexMachines.instance, 4, par1World, x, y, z);
		return true;
	

	}
	
	
	

	@Override
	public void addRecipe() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ComplexMachines.loader.getBlock(Terraformer.class)),true,new Object[]{"xyx", "yzy", "xyx", 'x', "ingotCopper", 'y', "circuitBasic", 'z', "motor"}));
		
	}

	@Override
	public String getName() {
		return "Terraformer";
	}

	@Override
	public boolean hasItemBlock() {
		return false;
	}

	@Override
	public Class getItemBlock() {
		//return TerraformerItemBlock.class;
		return null;
	}
	@Override
	public boolean threeSidedTextures(){
		return true;
	}

}