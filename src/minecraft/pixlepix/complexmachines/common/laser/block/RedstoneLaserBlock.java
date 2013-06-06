package pixlepix.complexmachines.common.laser.block;

import pixlepix.complexmachines.common.laser.tileentity.LaserBeamTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class RedstoneLaserBlock extends LaserBlock{

	
	
	@Override
	public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return 15;
    }
	public boolean canProvidePower(){
		return true;
	}
	@Override
	public String getFront() {
		// TODO Auto-generated method stub
		return textureSpecific;
	}
	@Override
	public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return 15;
    }
	@Override
	public boolean canConnectRedstone(IBlockAccess iba, int i, int j, int k, int dir) 
    { 
        return true; 
    }
	
	
	

	public RedstoneLaserBlock() {
		super(14);
	}
	static int blockIdIncrement=14;
	String textureBase="ComplexMachines:";
	public String textureSpecific="RedstoneLaser";

	

	

	@Override
	public String getName() {
		return "RedstoneLaserBeam";
	}
	
	
	
}
