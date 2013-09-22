package archadia.complexmachines.common.item;

import archadia.complexmachines.common.ComplexMachines;
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
		setCreativeTab(ComplexMachines.tabComplexMachines);
		setUnlocalizedName(name);		
		itemname = name;
	}

	public void registerIcons(IconRegister ir) {
		itemIcon = ir.registerIcon("complexmachines:"+itemname);
	}
}
