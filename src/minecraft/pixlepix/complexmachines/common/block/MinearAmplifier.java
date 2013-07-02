package pixlepix.complexmachines.common.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.tileentity.MinearTransmitterTileEntity;
import pixlepix.complexmachines.common.tileentity.TerraformerTileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class MinearAmplifier extends BasicComplexBlock {
	
	
	public MinearAmplifier() {
		super(46);
	}
	static int blockIdIncrement=23;
	String textureBase="ComplexMachines:";
	public String textureSpecific="Amplifier";

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
	 public String textureSpecificTop="Amplifier";

	public String textureSpecificConnector="Amplifier";

	@Override
	public Class getTileEntityClass() {
		return null;
	}
	
	
	
	

	@Override
	public void addRecipe() {
		//GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ComplexMachines.loader.getBlock(MinearAmplifier.class)),true,new Object[]{"xyx", "xyx", "xyx", 'x', "ingotCopper", 'y', "ingotSteel"}));
		
	}

	@Override
	public String getName() {
		return "Amplifier";
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