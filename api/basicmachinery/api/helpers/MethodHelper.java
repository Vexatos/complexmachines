package basicmachinery.api.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * @author Archadia
 *
 */
public class MethodHelper {
	
	public String getUsableName(Item item) {
		if(item != null) {
			String input = item.getUnlocalizedName();
			if(input.contains("item.")) {
				String output = input.replace("item.", "");
				return output;
			}
		}
		return null;
	}
}
