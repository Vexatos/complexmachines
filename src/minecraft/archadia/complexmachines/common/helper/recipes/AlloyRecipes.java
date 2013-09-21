package archadia.complexmachines.common.helper.recipes;

import net.minecraft.item.ItemStack;
import archadia.complexmachines.common.helper.ArchHelper;
import archadia.complexmachines.common.helper.map.DualMap;

/**
 * @author Archadia
 *
 */
public class AlloyRecipes {
	
    private static final AlloyRecipes alloyBase = new AlloyRecipes();
	
    public static final AlloyRecipes alloy() {
        return alloyBase;
    }
    
    public ItemStack getResult(ItemStack input1, ItemStack input2) {
    	ItemStack item = (ItemStack) this.alloyRecipes.get(input1, input2);
    	ArchHelper.println("" + item);
        return (ItemStack)this.alloyRecipes.get(input1, input2);
    }
    
	public DualMap<ItemStack, ItemStack, ItemStack> alloyRecipes = new DualMap<ItemStack, ItemStack, ItemStack>();
	
	public void addAlloyRecipe(ItemStack id, ItemStack id2, ItemStack itemstack) {
		alloyRecipes.put(id, id2, itemstack);
	}
}
