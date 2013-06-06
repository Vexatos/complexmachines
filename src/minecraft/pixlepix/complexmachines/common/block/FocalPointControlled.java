package pixlepix.complexmachines.common.block;

import java.util.Random;

import pixlepix.complexmachines.client.ClientProxy;
import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.itemblock.FillerItemBlock;
import pixlepix.complexmachines.common.tileentity.FillerMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.FocalPointControledTileEntity;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FocalPointControlled extends BasicComplexBlock {
	private Icon connectorIcon;
	private Icon topIcon;

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}
	public FocalPointControlled() {
		super(8);
	}
	@Override
	public boolean hasModel(){
		return true;
	}
	static int blockIdIncrement=8;
	String textureBase="ComplexMachines:";
	public String textureSpecific="FocalPointControlledModel";

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
		return FocalPointControledTileEntity.class;
	}

	@Override
	public void addRecipe() {

		
	}

	@Override
	public String getName() {
		return "Controlled Focal Point";
	}

	@Override
	public boolean hasItemBlock() {
		return false;
	}

	@Override
	public Class getItemBlock() {
		return null;
		
	}
	@Override
	public boolean threeSidedTextures(){
		return false;
	}

}
