package com.archadia.complexmachines.prefab.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

/**
 * @author Archadia
 */
public class ItemBase extends Item {

  protected String iconPath;

  public ItemBase(String name) {
    super();
    setUnlocalizedName(name);
    iconPath = name;
  }

  @Override
  public void registerIcons(IIconRegister ir) {
    this.itemIcon = ir.registerIcon("complexmachines:" + iconPath);
  }
}
