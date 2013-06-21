package pixlepix.complexmachines.common.crops;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import pixlepix.complexmachines.common.Config;

public class HydratorCrop extends BasicCrop {
	
	
	public HydratorCrop() {
		super(34, Config.itemStartingID+11);
	}

	@Override
	public String getTextureBase() {
		return "ComplexMachines:hydrator_";
	}

	@Override
	public boolean skipSustainCheck(){
		return false;
	}
	
	
	
	@Override
	public boolean canPlantGrow(World world, int x, int y, int z) {
		return true;
		//return world.getBlockId(x, y, z)==2;
	}
	@Override
	public String getName() {
		return "Hydrator";
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
		list.add(new ItemStack(seed,4,0));
		return list;
	}

	
	
	
	
	
	public void updateTick(World world, int x, int y, int z, Random random){
		int oldMeta=world.getBlockMetadata(x, y, z);
		super.updateTick(world, x, y, z, random);
		if(world.getBlockMetadata(x, y, z)==3&&oldMeta!=3){
			
			for(int i=x-25;i<x+25;i++){
				for(int j=z-25;j<z+25;j++){
					if(world.getBlockId(i, y-1, j)==60){
						world.setBlock(i, y-1, j, Config.blockStartingID+35);
					}
				}
			}
			
		}
	}
	
	
	

}
