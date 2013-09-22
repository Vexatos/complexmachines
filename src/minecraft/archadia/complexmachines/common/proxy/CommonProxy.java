package archadia.complexmachines.common.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import archadia.complexmachines.common.container.ContainerAlloyFabricator;
import archadia.complexmachines.common.container.ContainerWireMill;
import archadia.complexmachines.common.gui.GuiAlloyFabricator;
import archadia.complexmachines.common.gui.GuiWireMill;
import archadia.complexmachines.common.tileentity.TileEntityAlloyFabricator;
import archadia.complexmachines.common.tileentity.TileEntityWireMill;
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
		}
		return null;
	}

}
