package pixlepix.complexmachines.common.laser.block;

import pixlepix.complexmachines.common.laser.tileentity.HarmingLaserBeamTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.SuctionLaserBeamTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.TripwireLaserBeamTileEntity;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TripwireLaserBlock extends LaserBlock {
	static int blockIdIncrement=20;
	String textureBase="ComplexMachines:";
	public String textureSpecific="GlassLaser";
	public TripwireLaserBlock() {
		super(20);
	}
	@Override
	public Class getTileEntityClass() {
		return TripwireLaserBeamTileEntity.class;
	}

	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}

	@Override
	public String getName() {
		
		return "TripwireLaserBeam";
		
	}
	
}
