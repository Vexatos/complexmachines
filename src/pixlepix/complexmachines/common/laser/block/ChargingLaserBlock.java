package pixlepix.complexmachines.common.laser.block;

import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.laser.tileentity.ChargingLaserBeamTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.DebuffLaserBeamTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.core.UniversalElectricity;

public class ChargingLaserBlock extends LaserBlock {

	
	public ChargingLaserBlock(int id) {
		super(id);
		this.setUnlocalizedName("Charging Beam");
		this.setBlockUnbreakable();
	}

	public ChargingLaserBlock() {
		super(ComplexMachines.blockStartingID + 13);
		this.setStepSound(soundMetalFootstep);
		this.setUnlocalizedName("Charging Beam");
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int x, int y, int z,
			EntityLiving par5EntityLiving, ItemStack itemStack) {

		par1World.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
		((ChargingLaserBeamTileEntity) par1World.getBlockTileEntity(x, y, z))
				.initiate();

	}
	
	@Override
	public TileEntity createTileEntity(World var1, int metadata) {
		return new ChargingLaserBeamTileEntity();

	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {

		blockIcon = par1IconRegister.registerIcon("ComplexMachines:ChargingBeam");
	}
}
