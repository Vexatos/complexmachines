package archadia.complexmachines.common.helper.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import archadia.complexmachines.common.helper.map.DualMap;

/**
 * @author Archadia
 *
 */
public class AlloyRecipes {
	
    private static final AlloyRecipes alloyBase = new AlloyRecipes();
	
    public static final AlloyRecipes alloy()
    {
        return alloyBase;
    }
    
    public ItemStack getSmeltingResult(int id, int id2)
    {
        return (ItemStack)this.alloyRecipes.get(Integer.valueOf(id), Integer.valueOf(id2));
    }
    
	public DualMap<Integer, Integer, ItemStack> alloyRecipes = new DualMap<Integer, Integer, ItemStack>();
	
	public void addAlloyRecipe(int id, int id2, ItemStack itemstack) {
		alloyRecipes.put(id, id2, itemstack);
	}
}
