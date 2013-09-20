package archadia.complexmachines.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import archadia.complexmachines.common.ComplexMachines;
import archadia.complexmachines.common.helper.ArchHelper;
import archadia.complexmachines.common.helper.ArchLoader;

/**
 * @author Archadia
 *
 */
public class BlockBase extends Block {

	/**
	 * @param par1
	 * @param par2Material
	 */
	public BlockBase(int id, Material material, String name) {
		super(id, material);
		setUnlocalizedName(name);
		setCreativeTab(ComplexMachines.tabComplexMachines);
	}
	
	public static ArchLoader archLoader = new ArchLoader();
	
	public void registerIcons(IconRegister ir) {
		this.blockIcon = ir.registerIcon("complexmachines:" + ArchHelper.getUsableNames(getUnlocalizedName()));
	}
}
