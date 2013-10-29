package com.archadia.complexmachines.core.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import archadia.basicmachinery.core.prefab.BlockBase;

import com.archadia.complexmachines.core.common.ComplexMachines;
import com.archadia.complexmachines.core.common.tileentity.TileEntityIronClad;

/**
 * @author Archadia
 *
 */
public class BlockIronClad extends BlockBase {

	public BlockIronClad(int id, Material material, String name) {
		super(id, material, name);
		setHardness(2F);
		setIconMax(6);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te instanceof TileEntityIronClad && !player.isSneaking()) {
			player.openGui(ComplexMachines.instance, 5, world, x, y, z);
			return true;
		}
		return false;
    }
	
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityIronClad();
	}
	
	public Icon getIcon(int side, int meta)
	{
		if(side == 1 || side == 6) return icons[1];
		if(side > 1 && side != 6) return icons[0];
		return null;
	}

	public void registerIcons(IconRegister ir) {
		icons[0] = ir.registerIcon("complexmachines:ironclad_side_off_32x");
		icons[1] = ir.registerIcon("complexmachines:basic_top_off_32x");
	}
}
