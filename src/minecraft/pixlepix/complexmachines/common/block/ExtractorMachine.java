package pixlepix.complexmachines.common.block;

import pixlepix.complexmachines.client.ClientProxy;
import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.itemblock.ExtractorItemBlock;
import pixlepix.complexmachines.common.itemblock.FillerItemBlock;
import pixlepix.complexmachines.common.tileentity.ExtractorMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.FillerMachineTileEntity;
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

public class ExtractorMachine extends BasicComplexBlock {
	
	
	public ExtractorMachine() {
		super(2);
	}
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
	
	@Override
	public boolean hasModel(){
		return true;
	}
	
	 
	 static int blockIdIncrement=2;
		String textureBase="ComplexMachines:";
		public String textureSpecific="ExtractorModel";

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
			return ExtractorMachineTileEntity.class;
		}

		@Override
		public void addRecipe() {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ComplexMachines.loader.getBlock(ExtractorMachine.class)),true,new Object[]{"xyx", "xzx", "xxx", 'x', "plateSteel",'y', new ItemStack(Item.pickaxeDiamond), 'z', "circuitElite"}));
			
		}

		@Override
		public String getName() {
			return "Extractor";
		}

		@Override
		public boolean hasItemBlock() {
			return true;
		}

		@Override
		public Class getItemBlock() {
			return ExtractorItemBlock.class;
			
		}
		@Override
		public boolean threeSidedTextures(){
			return false;
		}

}
