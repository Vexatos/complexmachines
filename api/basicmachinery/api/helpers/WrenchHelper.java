package basicmachinery.api.helpers;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import basicmachinery.api.BMLoader;
import basicmachinery.api.block.BlockWrenchable;

/**
 * @author Archadia
 *
 */
public class WrenchHelper {

	BMLoader loader = new BMLoader();
	MethodHelper methHelper = new MethodHelper();
	WorldHelper worldHelper = new WorldHelper();
	
	public boolean canWrench(Block block) {
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			if(block instanceof BlockWrenchable) {
				return true;
			}
		}
		return false;
	}
	
	public void useWrench(EntityPlayer player, World world, int x, int y, int z, Block block) {
		if(canWrench(block)) {
			if(player.getCurrentEquippedItem() != null) {
				if(player.getCurrentEquippedItem().itemID == loader.getItem("wrench").itemID) {
					worldHelper.noParticleBreakBlock(world, block, x, y, z, 1);
					worldHelper.spawnSeriesOfParticles(world, "smoke", x + 0.5, y, z + 0.5, 0.0, 0.1, 0.0, 50);
					player.getCurrentEquippedItem().setItemDamage(player.getCurrentEquippedItem().getItemDamage() + 100);
					if(player.getCurrentEquippedItem().getItemDamage() > player.getCurrentEquippedItem().getMaxDamage()) {
						player.destroyCurrentEquippedItem();
					}
				} else {
					System.err.println("BASICMACHINERY NOT INSTALLED! INSTALL PLEASE.");
				}
					
			}
		}
	}
	
	
}
