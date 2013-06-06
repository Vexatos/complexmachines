package pixlepix.complexmachines.common.block;

import pixlepix.complexmachines.client.ClientProxy;
import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.itemblock.GrinderItemBlock;
import pixlepix.complexmachines.common.tileentity.FocalPointControledTileEntity;
import pixlepix.complexmachines.common.tileentity.GrinderTileEntity;
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

public class Grinder extends BasicComplexBlock {
	
	
	
	@Override
	public boolean onMachineActivated(World par1World, int x, int y, int z,
			EntityPlayer par5EntityPlayer, int side, float hitX, float hitY,
			float hitZ) {
		if (!par1World.isRemote) {
			par5EntityPlayer.openGui(ComplexMachines.instance, 2, par1World, x,
					y, z);
			return true;
		}

		return true;
	}
	
	public Grinder() {
		super(7);
	}
	
	static int blockIdIncrement=7;
	public String textureSpecific="GrinderFront";

	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}

	@Override
	public String getTop() {
		// TODO Auto-generated method stub
		return "GrinderTop";
	}

	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		 return "GrinderInput";
	}
	 public String textureSpecificTop="null";

	public String textureSpecificConnector="null";

	@Override
	public Class getTileEntityClass() {
		return GrinderTileEntity.class;
	}

	@Override
	public void addRecipe() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ComplexMachines.loader.getBlock(Grinder.class)),true,new Object[]{"xxx", "xyx", "xxx", 'x', "plateSteel",'y', "circuitAdvanced"}));
		
	}

	@Override
	public String getName() {
		return "Grinder";
	}

	@Override
	public boolean hasItemBlock() {
		return true;
	}

	@Override
	public Class getItemBlock() {
		return GrinderItemBlock.class;
		
	}
	@Override
	public boolean threeSidedTextures(){
		return true;
	}


}
