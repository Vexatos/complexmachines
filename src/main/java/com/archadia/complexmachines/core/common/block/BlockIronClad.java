package com.archadia.complexmachines.core.common.block;

import com.archadia.complexmachines.core.common.ComplexMachines;
import com.archadia.complexmachines.core.common.tileentity.TileEntityIronClad;
import com.archadia.complexmachines.prefab.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * @author Archadia
 */
public class BlockIronClad extends BlockBase {

  public BlockIronClad(Material material, String name) {
    super(material, name);
    setHardness(2F);
    setIconMax(6);
  }

  public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    TileEntity te = world.getTileEntity(x, y, z);
    if(te instanceof TileEntityIronClad && !player.isSneaking()) {
      player.openGui(ComplexMachines.instance, 5, world, x, y, z);
      return true;
    }
    return false;
  }

  @Override
  public boolean hasTileEntity(int metadata) {
    return true;
  }

  @Override
  public TileEntity createTileEntity(World world, int metadata) {
    return new TileEntityIronClad();
  }

  @Override
  public IIcon getIcon(int side, int meta) {
    if(side == 1 || side == 6) {
      return icons[1];
    }
    if(side > 1 && side != 6) {
      return icons[0];
    }
    return null;
  }

  @Override
  public void registerBlockIcons(IIconRegister ir) {
    icons[0] = ir.registerIcon("complexmachines:ironclad_side_off_32x");
    icons[1] = ir.registerIcon("complexmachines:basic_top_off_32x");
  }
}
