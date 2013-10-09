package basicmachinery.api.helpers;

import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * @author Archadia
 *
 */
public class WorldHelper {

	World worldObj;
	
	public void spawnSeriesOfParticles(World world, String name, double x, double y, double z, double xVel, double yVel, double zVel, int numberOfTimes) {
		int tries = 0;
		
		while(tries < numberOfTimes) {
			world.spawnParticle(name, x, y, z, xVel, yVel, zVel);
			tries++;
		}
	}
	
	public Block getBlockAt(int x, int y, int z) {
		int blockID = worldObj.getBlockId(x, y, z);
		return null;
	}
	
	public void noParticleBreakBlock(World world, Block block, int x, int y, int z, int size) {
		if(!world.isRemote) {
			block.dropBlockAsItem(world, x, y, z, block.blockID, size);
		}
		world.setBlockToAir(x, y, z);
	}
}
