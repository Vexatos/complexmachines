package pixlepix.complexmachines.common.crops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import pixlepix.complexmachines.common.Config;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BreederCrop extends BasicCrop {

	public BreederCrop() {
		super(31, Config.itemStartingID+8);
	}

	@Override
	public String getTextureBase() {
		return "ComplexMachines:breeder_";
	}

	@Override
	public boolean canPlantGrow(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String getName() {
		return "Breeder";
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

	
	public boolean areArraysComparable(int[] first, int[] second){
		int matches=0;
		
		
		Arrays.sort(first);
		Arrays.sort(second);
		for(int i=0;i<first.length;i++){
			if(first[i]!=second[i]){
				return false;
			}
		}
		
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	public int[][] getTiers(){
		ArrayList[] list=new ArrayList[1];
		
		ArrayList newList=new ArrayList();
		
		int[][] tiers=new int[2][4];
		tiers[0]=new int[]{59,141,142,83};

		tiers[1]=new int[]{Config.blockStartingID+32,Config.blockStartingID+33,Config.blockStartingID+34,Config.blockStartingID+36};
		return tiers;
		
	}
	
	public int findTierOfGrowth(World world, int x, int y, int z){
		
		int[] list={
		world.getBlockId(x+1, y, z),
		world.getBlockId(x-1, y, z),
		world.getBlockId(x, y, z+1),
		world.getBlockId(x, y, z-1),
		};
		int[][] tiers=getTiers();
		for(int i=0;i<tiers.length;i++){
			if(areArraysComparable(tiers[i],list)){
				return i+1;
			}
		}
		return -1;
	}
	
	public int isValidGrowth(World world, int x, int y, int z, Random random){
		
		return 0;
	}
	
	public void updateTick(World world, int x, int y, int z, Random random){
		super.updateTick(world, x, y, z, random);
		if(world.getBlockMetadata(x, y, z)==3){
			
			int tier=findTierOfGrowth(world,x,y,z);
			if(tier==-1){
				return;
			}
				int[][] tierList=getTiers();
				if(tierList.length>tier){
					int rand=new Random().nextInt(4);

					world.setBlock(x+1, y, z, 0);
					world.setBlock(x-1, y, z, 0);
					world.setBlock(x, y, z+1, 0);
					world.setBlock(x, y, z-1, 0);
					world.setBlock(x, y, z, tierList[tier][rand]);
				}
			
			
		}
	}

	
	

}
