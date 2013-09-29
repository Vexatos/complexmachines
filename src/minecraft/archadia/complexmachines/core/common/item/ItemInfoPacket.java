package archadia.complexmachines.core.common.item;

import archadia.complexmachines.core.common.ComplexMachines;
import archadia.complexmachines.prefab.item.ItemBase;

/**
 * @author Archadia
 *
 */
public class ItemInfoPacket extends ItemBase {

	public ItemInfoPacket(int id, String name) {
		super(id, name);
		setCreativeTab(ComplexMachines.tabComplexMachines);
		setUnlocalizedName(name);
		setMaxStackSize(1);
	}
}
