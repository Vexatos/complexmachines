package pixlepix.complexmachines.common.laser.block;

import pixlepix.complexmachines.common.laser.tileentity.HarmingLaserBeamTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.LaserBeamTileEntity;
import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GlassLaserBlock extends HarmingLaserBlock {
	static int blockIdIncrement=12;
	public GlassLaserBlock() {
		super(12);
	}
	String textureBase="ComplexMachines:";
	public String textureSpecific="GlassLaser";

	@Override
	public Class getTileEntityClass() {
		return HarmingLaserBeamTileEntity.class;
	}

	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}

	@Override
	public String getName() {
		return "GlassLaserBeam";
	}

}
