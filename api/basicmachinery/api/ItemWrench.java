package basicmachinery.api;

import com.archadia.basicmachinery.core.common.item.ItemBase;

/**
 * @author Archadia
 *
 */
public class ItemWrench extends ItemBase {

	public ItemWrench(int id, String name) {
		super(id, name);
		setMaxStackSize(1);
		setMaxDamage(1000);
	}
}
