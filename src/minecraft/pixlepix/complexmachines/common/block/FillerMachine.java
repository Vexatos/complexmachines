package pixlepix.complexmachines.common.block;

import pixlepix.complexmachines.client.ClientProxy;
import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.BetterLoader;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.IBlock;
import pixlepix.complexmachines.common.itemblock.FillerItemBlock;
import pixlepix.complexmachines.common.tileentity.FillerMachineTileEntity;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FillerMachine extends BasicComplexBlock {

	
	

	static int blockIdIncrement=1;
	public FillerMachine() {
		super(1);
	}
	String textureBase="ComplexMachines:";
	public String textureSpecific="FillerModel";

	 
	 public String textureSpecificTop="null";

	public String textureSpecificConnector="null";
	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}
	@Override
	public boolean hasModel(){
		return true;
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
	@Override
	public Class getTileEntityClass() {
		return FillerMachineTileEntity.class;
	}

	@Override
	public void addRecipe() {

		GameRegistry.addRecipe(new ItemStack(ComplexMachines.loader.getBlock(FillerMachine.class)), "xyx", "yzy", "xyx", 'x', new ItemStack(stone), 'y',new ItemStack(Item.diamond), 'z', new ItemStack(Item.ingotIron));
	}

	@Override
	public String getName() {
		return "Filler";
	}

	@Override
	public boolean hasItemBlock() {
		return true;
	}

	@Override
	public Class getItemBlock() {
		return FillerItemBlock.class;
		
	}
	@Override
	public boolean threeSidedTextures(){
		return false;
	}
	
	

	

}
