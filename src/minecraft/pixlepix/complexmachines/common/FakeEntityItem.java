package pixlepix.complexmachines.common;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FakeEntityItem extends EntityItem {

	

	public FakeEntityItem(World par1World, double par2, double par4,double par6, ItemStack par8ItemStack) {
		super(par1World, par2, par4, par6, par8ItemStack);
		// TODO Auto-generated constructor stub
	}

	/**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float par1) {}

    /**
     * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
     * and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround
     */
    protected void updateFallState(double par1, boolean par3) {}
	
}
