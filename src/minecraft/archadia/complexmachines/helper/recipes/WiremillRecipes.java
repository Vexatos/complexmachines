package archadia.complexmachines.helper.recipes;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import archadia.complexmachines.helper.ArchHelper;

/**
 * @author Archadia
 *
 */
public class WiremillRecipes {

	 private final static WiremillRecipes recipebase = new WiremillRecipes();   
	 private Map wiremillRecipes = new HashMap<Integer, ItemStack>();
	 
	 public final static WiremillRecipes recipes() {
		 return recipebase;
	 }
	    
	 public ItemStack getResult(int input) {
		 ItemStack item = (ItemStack) this.wiremillRecipes.get(input);
		 if (item == null) {
			 return null;
		 }
		 return (ItemStack)this.wiremillRecipes.get(input);
	 }
	 
	 public void addWireMillRecipes(int input, ItemStack output) {
		 wiremillRecipes.put(input, output);
	 }
}
