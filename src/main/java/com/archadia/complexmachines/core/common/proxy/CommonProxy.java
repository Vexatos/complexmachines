package com.archadia.complexmachines.core.common.proxy;

import com.archadia.complexmachines.core.client.gui.*;
import com.archadia.complexmachines.core.common.container.*;
import com.archadia.complexmachines.core.common.tileentity.*;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Archadia
 */
public class CommonProxy implements IGuiHandler {

  @Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    TileEntity tile_entity = world.getTileEntity(x, y, z);
    switch(ID){
      case 0:
        return new ContainerAlloyFabricator(player.inventory, (TileEntityAlloyFabricator) tile_entity);
      case 1:
        return new ContainerWireMill(player.inventory, (TileEntityWireMill) tile_entity);
      case 2:
        return new ContainerGrinder(player.inventory, (TileEntityGrinder) tile_entity);
      case 3:
        return new ContainerExtractor(player.inventory, (TileEntityExtractor) tile_entity);
      case 4:
        return new ContainerCookieMaker(player.inventory, (TileEntityCookieMaker) tile_entity);
      case 5:
        return new ContainerIronClad(player.inventory, (TileEntityIronClad) tile_entity);
    }
    return null;
  }

  @Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    TileEntity tile_entity = world.getTileEntity(x, y, z);
    switch(ID){
      case 0:
        return new GuiAlloyFabricator(player.inventory, (TileEntityAlloyFabricator) tile_entity);
      case 1:
        return new GuiWireMill(player.inventory, (TileEntityWireMill) tile_entity);
      case 2:
        return new GuiGrinder(player.inventory, (TileEntityGrinder) tile_entity);
      case 3:
        return new GuiExtractor(player.inventory, (TileEntityExtractor) tile_entity);
      case 4:
        return new GuiCookieMaker(player.inventory, (TileEntityCookieMaker) tile_entity);
      case 5:
        return new GuiIronClad(player.inventory, (TileEntityIronClad) tile_entity);
    }
    return null;
  }

}
