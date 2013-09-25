package archadia.complexmachines.core.common.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import archadia.complexmachines.core.client.gui.GuiAlloyFabricator;
import archadia.complexmachines.core.client.gui.GuiCookieMaker;
import archadia.complexmachines.core.client.gui.GuiExtractor;
import archadia.complexmachines.core.client.gui.GuiGrinder;
import archadia.complexmachines.core.client.gui.GuiWireMill;
import archadia.complexmachines.core.common.container.ContainerAlloyFabricator;
import archadia.complexmachines.core.common.container.ContainerCookieMaker;
import archadia.complexmachines.core.common.container.ContainerExtractor;
import archadia.complexmachines.core.common.container.ContainerGrinder;
import archadia.complexmachines.core.common.container.ContainerWireMill;
import archadia.complexmachines.core.common.tileentity.TileEntityAlloyFabricator;
import archadia.complexmachines.core.common.tileentity.TileEntityCookieMaker;
import archadia.complexmachines.core.common.tileentity.TileEntityExtractor;
import archadia.complexmachines.core.common.tileentity.TileEntityGrinder;
import archadia.complexmachines.core.common.tileentity.TileEntityWireMill;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * @author Archadia
 *
 */
public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		switch(ID) {
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
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		switch(ID) {
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
		}
		return null;
	}

}
