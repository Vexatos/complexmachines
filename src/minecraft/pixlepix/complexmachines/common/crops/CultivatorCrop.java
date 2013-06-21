package pixlepix.complexmachines.common.crops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import pixlepix.complexmachines.common.Config;

public class CultivatorCrop extends BasicCrop{

	public CultivatorCrop() {
		super(32, Config.itemStartingID+9);
	}

	@Override
	public String getTextureBase() {
		return "ComplexMachines:cultivator_";
	}

	@Override
	public boolean skipSustainCheck(){
		return true;
	}
	
	public int getInfertileRate(){
		return 5;
	}
	public int getFertileRate(){
		return 5;
	}
	
	@Override
	public boolean canPlantGrow(World world, int x, int y, int z) {
		return true;
		//return world.getBlockId(x, y, z)==2;
	}
	@Override
	public String getName() {
		return "Cultivator";
	}

	@Override
	public ArrayList<ItemStack> getSeedDrop() {
		// TODO Auto-generated method stub
		ArrayList<ItemStack> list=new ArrayList<ItemStack>(); 
		list.add(new ItemStack(seed,1,0));
		return list;
	}

	@Override
	public ArrayList<ItemStack> getHarvestDrop() {
		ArrayList<ItemStack> list=new ArrayList<ItemStack>(); 
		list.add(new ItemStack(seed,2,0));
		return list;
	}

	
	
	
	
	
	public void updateTick(World world, int x, int y, int z, Random random){
		super.updateTick(world, x, y, z, random);
		if(world.getBlockMetadata(x, y, z)==3){
			
			int targetX=x-1;
			int targetY=y;
			int targetZ=z;
			if(world.getBlockId(targetX, targetY, targetZ)==0&&world.getBlockId(targetX,targetY-1,targetZ)==2){
				world.setBlock(targetX, targetY, targetZ, Config.blockStartingID+32);
			}
			targetX=x+1;
			targetZ=z;
			if(world.getBlockId(targetX, targetY, targetZ)==0&&world.getBlockId(targetX,targetY-1,targetZ)==2){
				world.setBlock(targetX, targetY, targetZ, Config.blockStartingID+32);
			}
			targetX=x;
			targetZ=z+1;
			if(world.getBlockId(targetX, targetY, targetZ)==0&&world.getBlockId(targetX,targetY-1,targetZ)==2){
				world.setBlock(targetX, targetY, targetZ, Config.blockStartingID+32);
			}
			targetX=x;
			targetZ=z-1;
			if(world.getBlockId(targetX, targetY, targetZ)==0&&world.getBlockId(targetX,targetY-1,targetZ)==2){
				world.setBlock(targetX, targetY, targetZ, Config.blockStartingID+32);
			}

			world.setBlock(x, y-1, z, Config.blockStartingID+35);
			world.destroyBlock(x, y, z, true);
		}
	}

}
