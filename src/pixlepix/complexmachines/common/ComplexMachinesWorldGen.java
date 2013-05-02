package pixlepix.complexmachines.common;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import pixlepix.complexmachines.common.Config;

public class ComplexMachinesWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		if(Config.worldGen){
			if(world.provider.isSurfaceWorld()&&world.provider.getAverageGroundLevel()>20){
				world.setBlock(chunkX * 16 + random.nextInt(16),random.nextInt(10) + 5, chunkZ * 16 + random.nextInt(16), Config.blockStartingID + 3);
				
			}
		}
	}

}
