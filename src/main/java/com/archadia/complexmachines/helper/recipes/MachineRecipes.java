package com.archadia.complexmachines.helper.recipes;

import net.minecraft.item.ItemStack;

import java.util.HashMap;

/**
 * @author Archadia
 */
public class MachineRecipes {

  private static MachineRecipes instanceBase = new MachineRecipes();

  public static MachineRecipes instance() {
    return instanceBase;
  }

  public enum Recipe {
    WIREMILL(new HashMap<ItemStack, ItemStack>());

    public HashMap map;

    Recipe(HashMap map) {
      this.map = map;
    }

    public ItemStack getResult(ItemStack input) {
      ItemStack item = (ItemStack) this.map.get(input);
      if(item == null) {
        return null;
      }
      return (ItemStack) this.map.get(input);
    }

    public void put(Object input, Object output) {
      map.put(input, output);
    }
  }

  public void addWireMillRecipes(ItemStack input, ItemStack output) {
    Recipe.WIREMILL.put(input, output);
  }

}
