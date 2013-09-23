package archadia.complexmachines.core.common.block;

import net.minecraft.block.material.Material;
import archadia.complexmachines.core.common.ComplexMachines;
import archadia.complexmachines.prefab.block.BlockBase;

/**
 * @author Archadia
 *
 */
public class BlockMiningLaser extends BlockBase {

	public BlockMiningLaser(int par1, Material par2Material, String name) {
		super(par1, par2Material, name);
		setCreativeTab(ComplexMachines.tabComplexMachines);
		setUnlocalizedName(name);
	}
}
