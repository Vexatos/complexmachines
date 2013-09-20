package archadia.complexmachines.common.helper.recipes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import archadia.complexmachines.common.helper.map.DualMap;
import archadia.complexmachines.common.helper.map.TripleMap;
import net.minecraft.item.ItemStack;

/**
 * @author Archadia
 *
 */
public class MachineryRecipes {	
	
	public void addAlloyRecipe3P(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack output) {
		RecipeV3.ALLOY.put(input1, input2, input3, output);
	}
	
	public void addAlloyRecipe2P(ItemStack input1, ItemStack input2, ItemStack output) {
		RecipeV2.ALLOY.put(input1, input2, output);
	}
	
	public enum RecipeV1 {
		NOTHING(null);
		
		public HashMap recipes;

		RecipeV1(HashMap map) {
			recipes = map;
		}
		
		public boolean conRec(ItemStack input)
		{
			for(Object obj : getRecipes().entrySet())
			{
				if(obj instanceof Map.Entry)
				{
					Map.Entry entry = (Map.Entry)obj;

					if(entry.getKey() instanceof ItemStack)
					{
						if(((ItemStack)entry.getKey()).isItemEqual(input))
						{
							return true;
						}
					}
				}
			}

			return false;
		}

		public void put(Object input, Object output)
		{
			recipes.put(input, output);
		}
		
		public HashMap getRecipes() {
			return recipes;
		}
	}

	public enum RecipeV2 {
		NOTHING(null),
		ALLOY(new DualMap<ItemStack, ItemStack, ItemStack>());
		
		public DualMap recipes;

		RecipeV2(DualMap map) {
			recipes = map;
		}
		
		public boolean conRec(ItemStack input)
		{
			for(Object obj : ((Map) getRecipes()).entrySet())
			{
				if(obj instanceof Map.Entry)
				{
					Map.Entry entry = (Map.Entry)obj;

					if(entry.getKey() instanceof ItemStack)
					{
						if(((ItemStack)entry.getKey()).isItemEqual(input))
						{
							return true;
						}
					}
				}
			}

			return false;
		}

		public void put(Object input, Object input2, Object output)
		{
			recipes.put(input, input2, output);
		}
		
		public DualMap getRecipes() {
			return recipes;
		}
	}

	public enum RecipeV3 {
		NOTHING(null),
		ALLOY(new TripleMap<ItemStack, ItemStack, ItemStack, ItemStack>());
		
		public TripleMap recipes;

		RecipeV3(TripleMap map) {
			recipes = map;
		}
		
		public boolean conRec(ItemStack input)
		{
			for(Object obj : ((Map) getRecipes()).entrySet())
			{
				if(obj instanceof Map.Entry)
				{
					Map.Entry entry = (Map.Entry)obj;

					if(entry.getKey() instanceof ItemStack)
					{
						if(((ItemStack)entry.getKey()).isItemEqual(input))
						{
							return true;
						}
					}
				}
			}

			return false;
		}

		public void put(Object input, Object input2, Object input3, Object output)
		{
			recipes.put(input, input2, input3, output);
		}
		
		public TripleMap getRecipes() {
			return recipes;
		}
	}
}
