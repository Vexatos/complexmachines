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

public class MinearTransmitter extends BasicComplexBlock {
	
	
	public MinearTransmitter() {
		super(45);
	}
	static int blockIdIncrement=23;
	String textureBase="ComplexMachines:";
	public String textureSpecific="Transmitter";

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
	 public String textureSpecificTop="Transmitter";

	public String textureSpecificConnector="TransmitterInput";

	@Override
	public Class getTileEntityClass() {
		return MinearTransmitterTileEntity.class;
	}
	
	
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY,float hitZ) {
		super.onBlockActivated(par1World, x,  y, z, par5EntityPlayer,  side, hitX, hitY,hitZ);
		
		if(par1World.getBlockTileEntity(x, y, z)!=null&&par1World.getBlockTileEntity(x, y, z) instanceof MinearTransmitterTileEntity){
			((MinearTransmitterTileEntity)par1World.getBlockTileEntity(x, y, z)).rightClick(par5EntityPlayer);
		}
		return true;
	

	}
	

	@Override
	public void addRecipe() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ComplexMachines.loader.getBlock(MinearTransmitter.class)),true,new Object[]{"xx", 'x', new ItemStack(ComplexMachines.loader.getBlock(MinearAmplifier.class))}));
		
	}

	@Override
	public String getName() {
		return "Transmitter";
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