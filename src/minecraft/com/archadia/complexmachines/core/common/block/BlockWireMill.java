package com.archadia.complexmachines.core.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import basicmachinery.api.block.BlockWrenchable;

import com.archadia.complexmachines.core.common.ComplexMachines;
import com.archadia.complexmachines.core.common.tileentity.TileEntityWireMill;

/**
 * @author Archadia
 *
 */
public class BlockWireMill extends BlockWrenchable {

	public BlockWireMill(int id, Material material, String name) {
		super(id, material, name);
		setHardness(2F);
		setIconMax(6);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te instanceof TileEntityWireMill && !player.isSneaking()) {
			player.openGui(ComplexMachines.instance, 1, world, x, y, z);
			return true;
		}
		return false;
    }
	
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityWireMill();
	}
	
	public Icon getIcon(int side, int meta)
	{
		if(side == 1 || side == 6) return icons[1];
		if(side > 1 && side != 6) return icons[0];
		return null;
	}

	public void registerIcons(IconRegister ir) {
		icons[0] = ir.registerIcon("complexmachines:wiremill_side_off_32x");
		icons[1] = ir.registerIcon("complexmachines:basic_top_off_32x");
	}
}
