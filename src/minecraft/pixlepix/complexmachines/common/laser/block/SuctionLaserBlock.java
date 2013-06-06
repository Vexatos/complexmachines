package pixlepix.complexmachines.common.laser.block;

import pixlepix.complexmachines.common.laser.tileentity.HarmingLaserBeamTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.SuctionLaserBeamTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SuctionLaserBlock extends LaserBlock {


	
	
	
	public SuctionLaserBlock() {
		super(17);
	}
	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}
	static int blockIdIncrement=17;
	String textureBase="ComplexMachines:";
	public String textureSpecific="SuctionLaser";

	@Override
	public Class getTileEntityClass() {
		return SuctionLaserBeamTileEntity.class;
	}

	

	@Override
	public String getName() {
		return "SuctionLaserBeam";
	}
	
}
