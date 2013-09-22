package archadia.complexmachines.common.block;

import archadia.complexmachines.common.ComplexMachines;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author Archadia
 *
 */
public class BlockModOre extends BlockBase {

	public BlockModOre(int id, Material material, String name) {
		super(id, material, name);
		setUnlocalizedName(name);
		setCreativeTab(ComplexMachines.tabComplexMachines);
	}
}
