package archadia.complexmachines.core.common.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import archadia.complexmachines.core.common.ComplexMachines;
import archadia.complexmachines.prefab.item.ItemBase;

/**
 * @author Archadia
 *
 */
public class ItemWire extends ItemBase {

	public ItemWire(int id, String name) {
		super(id, name);
		setCreativeTab(ComplexMachines.tabComplexMachines);
		setUnlocalizedName(name);
	}
}
