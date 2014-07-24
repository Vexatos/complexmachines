package com.archadia.complexmachines.prefab.block;

import com.archadia.complexmachines.core.common.ComplexMachines;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * @author Archadia
 */
public class BlockBase extends Block {

  protected IIcon[] icons = new IIcon[0];
  protected String iconPath;

  public BlockBase(Material material, String name) {
    super(material);
    setBlockName(name);
    setCreativeTab(ComplexMachines.tab);
    iconPath = name;
  }

  @Override
  public void registerBlockIcons(IIconRegister ir) {
    this.blockIcon = ir.registerIcon("complexmachines:" + iconPath);
  }

  protected void setIconMax(int max) {
    icons = new IIcon[max];
  }
}
