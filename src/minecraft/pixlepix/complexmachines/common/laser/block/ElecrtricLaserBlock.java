package pixlepix.complexmachines.common.laser.block;

import pixlepix.complexmachines.common.laser.tileentity.ElectricLaserBeamTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.HarmingLaserBeamTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ElecrtricLaserBlock extends LaserBlock {


	static int blockIdIncrement=16;
	public ElecrtricLaserBlock() {
		super(16);
	}
	String textureBase="ComplexMachines:";
	public String textureSpecific="ElectricLaser";

	@Override
	public Class getTileEntityClass() {
		return ElectricLaserBeamTileEntity.class;
	}
	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}
	

	@Override
	public String getName() {
		return "ElectricLaserBeam";
	}
	
}
