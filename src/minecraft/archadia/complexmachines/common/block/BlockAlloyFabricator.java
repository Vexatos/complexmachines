package archadia.complexmachines.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import archadia.complexmachines.common.ComplexMachines;
import archadia.complexmachines.common.tileentity.TileEntityAlloyFabricator;

/**
 * @author Archadia
 *
 */
public class BlockAlloyFabricator extends BlockBase {

	public BlockAlloyFabricator(int id, Material material, String name) {
		super(id, material, name);
		setCreativeTab(ComplexMachines.tabComplexMachines);
		setHardness(2F);
		setUnlocalizedName(name);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te instanceof TileEntityAlloyFabricator && !player.isSneaking()) {
			player.openGui(ComplexMachines.instance, 0, world, x, y, z);
			return true;
		}
		return false;
    }
	
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityAlloyFabricator();
	}
}
