package archadia.complexmachines.common.helper.recipes;

import java.util.Arrays;
import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import archadia.complexmachines.common.ComplexMachines;
import archadia.complexmachines.common.helper.ArchHelper;
import archadia.complexmachines.common.helper.map.DualMap;

/**
 * @author Archadia
 *
 */
public class AlloyRecipes {
	
    private final static AlloyRecipes alloyBase = new AlloyRecipes();
	
    public AlloyRecipes() {
    	
    }
    
    public final static AlloyRecipes alloy() {
        return alloyBase;
    }
    
    public ItemStack getResult(int input1, int input2) {
    	ItemStack item = (ItemStack) this.alloyRecipes.get(input1, input2);
    	ArchHelper.println(""+item);
        if (item == null)
        {
            return null;
        }
        return (ItemStack)this.alloyRecipes.get(input1, input2);
    }
    
	public DualMap<Integer, Integer, ItemStack> alloyRecipes = new DualMap<Integer, Integer, ItemStack>();
	
	public void addAlloyRecipe(int input, int input2, ItemStack itemstack) {
		alloyRecipes.put(input, input2, itemstack);
	}
}
