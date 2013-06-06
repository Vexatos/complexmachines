package pixlepix.complexmachines.common.laser.block;

import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.laser.tileentity.DebuffLaserBeamTileEntity;
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

public class DebuffLaserBlock extends LaserBlock {

	
	static int blockIdIncrement=13;
	String textureBase="ComplexMachines:";
	public String textureSpecific="DebuffLaser";

	public DebuffLaserBlock() {
		super(13);
	}
	
	@Override
	public Class getTileEntityClass() {
		return DebuffLaserBeamTileEntity.class;
	}
	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}
	

	@Override
	public String getName() {
		return "DebuffLaserBeam";
	}
}
