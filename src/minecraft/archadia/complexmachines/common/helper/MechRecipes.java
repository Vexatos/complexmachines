package archadia.complexmachines.common.helper;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;

/**
 * @author Archadia
 *
 */
public class MechRecipes {
	
	public void addAlloyRecipe(ItemStack key, ItemStack value) {
		Recipe.ALLOY.put(key, value);
	}
	
	public enum Recipe {
		ALLOY(new HashMap<ItemStack, ItemStack>());
		
		HashMap recipes;
		
		Recipe(HashMap map) {
			recipes = map;
		}
		
		public void put(Object key, Object value) {
			recipes.put(key, value);
		}
		
		public Map get() {
			return recipes;
		}
	}
}
