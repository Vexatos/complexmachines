package archadia.complexmachines.core.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import archadia.complexmachines.core.common.ComplexMachines;
import archadia.complexmachines.core.common.tileentity.TileEntityExcavator;
import archadia.complexmachines.prefab.block.BlockBase;

/**
 * @author Archadia
 *
 */
public class BlockExcavator extends BlockBase {

	/**
	 * @param id
	 * @param material
	 */
	public BlockExcavator(int id, Material material, String name) {
		super(id, material, name);
		setCreativeTab(ComplexMachines.tabComplexMachines);
		setHardness(2F);
		setUnlocalizedName(name);
	}
	
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityExcavator();
	}
}
