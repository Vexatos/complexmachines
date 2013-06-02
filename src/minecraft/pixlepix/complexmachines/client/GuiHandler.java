	
	package pixlepix.complexmachines.client;
	
	import pixlepix.complexmachines.common.container.ContainerAirship;
import pixlepix.complexmachines.common.container.ContainerExtractor;
import pixlepix.complexmachines.common.container.ContainerGrinder;
import pixlepix.complexmachines.common.tileentity.ExtractorMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.GrinderTileEntity;
import pixlepix.complexmachines.common.tileentity.MotorTileEntity;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
	
	// Create a class and implement IGuiHandler
public class GuiHandler implements IGuiHandler{
	// This is a required method to open you Gui and has 6 params
	// @param int id, this is the Gui Id
	// @param EntityPlayer, this is the player declaration
	// @param World, this is the world declaration
	// @param int x, y, z this is the players current x, y, z coords
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){
	// This gets the TileEntity the player is currently activating
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
	// This checks if the TileEntity is the TileTutorial
		if(tile_entity instanceof GrinderTileEntity){
	// If it is it returns a new ContainerTutorial instance
			return new ContainerGrinder(player.inventory,(GrinderTileEntity) tile_entity);
	}
		if(tile_entity instanceof MotorTileEntity){
			// If it is it returns a new ContainerTutorial instance
					return new ContainerAirship(player.inventory,(MotorTileEntity) tile_entity);
			}
	
	if(tile_entity instanceof ExtractorMachineTileEntity){
		// If it is it returns a new ContainerTutorial instance
		return new ContainerExtractor(player.inventory,(ExtractorMachineTileEntity) tile_entity);
		}
	
	// Returns null if not
	return null;
	}
	
	// This is another required method to open the Gui and has 6 params
	// @param int id, this is the Gui Id
	// @param EntityPlayer, this is the player declaration
	// @param World, this is the world declaration,
	// @param int x, y, z this is the players current x, y, z coords
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		if(tile_entity instanceof GrinderTileEntity){
			return new GuiGrinder(player.inventory, (GrinderTileEntity) tile_entity);
		}
		if(tile_entity instanceof MotorTileEntity){
			return new GuiAirship(player.inventory,(MotorTileEntity) tile_entity);
		}
		if(tile_entity instanceof ExtractorMachineTileEntity){
			return new GuiExtractor(player.inventory, (ExtractorMachineTileEntity) tile_entity);
		}
		
		// Returns null if not
		return null;
	}
	
	
	
	}