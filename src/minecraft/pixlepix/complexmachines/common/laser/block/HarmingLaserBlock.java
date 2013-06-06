package pixlepix.complexmachines.common.laser.block;

import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.laser.tileentity.HarmingLaserBeamTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.LaserBeamTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.core.UniversalElectricity;

public class HarmingLaserBlock extends LaserBlock {

	
	
	static int blockIdIncrement=11;
	String textureBase="ComplexMachines:";
	public String textureSpecific="HarmingLaserBeam";

	@Override
	public Class getTileEntityClass() {
		return HarmingLaserBeamTileEntity.class;
	}
	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}
	public HarmingLaserBlock(int i) {
		super(i);
	}
	public HarmingLaserBlock() {
		super(11);
	}

	

	@Override
	public String getName() {
		return "HarmingLaserBeam";
	}
}
