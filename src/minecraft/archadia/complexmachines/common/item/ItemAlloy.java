package archadia.complexmachines.common.item;

import archadia.complexmachines.common.ComplexMachines;
import net.minecraft.item.Item;

/**
 * @author Archadia
 *
 */
public class ItemAlloy extends ItemIngot {

	public ItemAlloy(int id, String name) {
		super(id, name);
		setCreativeTab(ComplexMachines.tabComplexMachines);
		setUnlocalizedName(name);
	}

}
