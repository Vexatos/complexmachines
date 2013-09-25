package archadia.complexmachines.core.common.item;

import net.minecraft.item.ItemStack;
import archadia.complexmachines.prefab.item.ItemEBase;

/**
 * @author Archadia
 *
 */
public class ItemBat extends ItemEBase {

	public ItemBat(int id, String name) {
		super(id, name);
	}

	@Override
	public float getMaxElectricityStored(ItemStack theItem) {
		return 1000000;
	}

}
