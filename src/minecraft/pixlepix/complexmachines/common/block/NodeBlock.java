package pixlepix.complexmachines.common.block;

import pixlepix.complexmachines.client.ClientProxy;
import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.BetterLoader;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.IBlock;
import pixlepix.complexmachines.common.itemblock.FillerItemBlock;
import pixlepix.complexmachines.common.itemblock.MotorItemBlock;
import pixlepix.complexmachines.common.tileentity.FillerMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.NodeTileEntity;
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

public class NodeBlock extends BasicComplexBlock {

	
	

	static int blockIdIncrement=1;
	public NodeBlock() {
		super(19);
	}
	
	@Override
	public boolean onMachineActivated(World par1World, int x, int y, int z,
			EntityPlayer par5EntityPlayer, int side, float hitX, float hitY,
			float hitZ) {
		if (!par1World.isRemote) {
			par5EntityPlayer.openGui(ComplexMachines.instance, 4, par1World, x,
					y, z);
			return true;
		}

		return true;
	}
	String textureBase="ComplexMachines:";
	public String textureSpecific="CapacitatorFront";

	 
	 public String textureSpecificTop="CapacitatorFront";

	public String textureSpecificConnector="CapacitatorInput";
	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}
	@Override
	public String getTop() {
		// TODO Auto-generated method stub
		return "CapacitatorFront";
	}

	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		 return "CapacitatorInput";
	}
	@Override
	public Class getTileEntityClass() {
		return NodeTileEntity.class;
	}

	@Override
	public void addRecipe() {

		GameRegistry.addRecipe(new ItemStack(ComplexMachines.loader.getBlock(NodeBlock.class)), "xyx", "yzy", "xyx", 'x', new ItemStack(stone), 'z',new ItemStack(Item.diamond), 'y', new ItemStack(Item.ingotIron));
	}

	@Override
	public String getName() {
		return "Linker Node";
	}

	
	@Override
	public boolean threeSidedTextures(){
		return true;
	}
	
	

	

}
