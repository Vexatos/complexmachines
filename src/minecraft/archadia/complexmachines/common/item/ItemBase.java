package archadia.complexmachines.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

/**
 * @author Archadia
 *
 */
public class ItemBase extends Item {

	String itemname;
	
	public ItemBase(int id, String name) {
		super(id);
		itemname = name;
	}

	public void registerIcons(IconRegister ir) {
		itemIcon = ir.registerIcon("modech:"+itemname);
	}
}
