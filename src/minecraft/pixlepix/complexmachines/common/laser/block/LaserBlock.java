package pixlepix.complexmachines.common.laser.block;

import java.util.Random;

import pixlepix.complexmachines.client.ClientProxy;
import pixlepix.complexmachines.common.BasicComplexBlock;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.itemblock.LaserItemBlock;
import pixlepix.complexmachines.common.laser.LaserEmitter;
import pixlepix.complexmachines.common.laser.LaserEmitterTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.LaserBeamTileEntity;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.prefab.block.BlockAdvanced;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LaserBlock extends BasicComplexBlock {
	static int blockIdIncrement=10;
	String textureBase="ComplexMachines:";
	public String textureSpecific="LaserBeam";
	public LaserBlock() {
		super(10);
	}

	public LaserBlock(int i) {
		super(i);
	}
	
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}
	
	@Override
	public Class getTileEntityClass() {
		return LaserBeamTileEntity.class;
	}
	@Override
	public boolean inCreativeTab(){
		return false;
	}

	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}

	@Override
	public String getTop() {
		// TODO Auto-generated method stub
		return getFront();
	}

	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		 return getFront();
	}

	@Override
	public String getName() {
		return "LaserBeam";
	}

	@Override
	public boolean hasItemBlock() {
		return false;
	}

	@Override
	public Class getItemBlock() {
		return null;
		
	}
	

}
