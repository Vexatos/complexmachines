package com.archadia.complexmachines.core.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;

/**
 * @author Archadia
 */
public class ItemFoodBase extends ItemFood {

  String itemname;

  public ItemFoodBase(int par1, float par2, boolean par3, String name) {
    super(par1, par2, par3);
    setUnlocalizedName(name);
    itemname = name;
  }

  @Override
  public void registerIcons(IIconRegister ir) {
    this.itemIcon = ir.registerIcon("complexmachines:" + itemname);
  }

}
