package pixlepix.complexmachines.common.crops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import pixlepix.complexmachines.common.Config;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
		list.add(new ItemStack(seed,1,0));
		return list;
	}

	
	public boolean areArraysComparable(ArrayList<Integer> first, ArrayList<Integer> second){
		int matches=0;
		
		Iterator<Integer> firstIter=first.iterator();
		while(firstIter.hasNext()){
			int searchingFor=firstIter.next();
			Iterator<Integer> secondIter=second.iterator();
			while(secondIter.hasNext()){
				int testingFor=secondIter.next();
				if(searchingFor==testingFor){
					matches++;
					secondIter.remove();
				}
				
				
			}
		}
		
		if(matches>=4){
			return true;
		}
		
		return false;
		
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList[] getTiers(){
		ArrayList[] list=new ArrayList[1];
		
		ArrayList newList=new ArrayList();
		newList.add(59);
		newList.add(141);
		newList.add(142);
		newList.add(83);
		list[0]=newList;
		
		return list;
		
		
	}
	
	public int findTierOfGrowth(World world, int x, int y, int z){
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(world.getBlockId(x+1, y, z));
		list.add(world.getBlockId(x-1, y, z));
		list.add(world.getBlockId(x, y, z+1));
		list.add(world.getBlockId(x, y, z-1));
		ArrayList[] tiers=getTiers();
		for(int i=0;i<tiers.length;i++){
			if(areArraysComparable(tiers[i],list)){
				return i+1;
			}
		}
		return 0;
	}
	
	public int isValidGrowth(World world, int x, int y, int z, Random random){
		
		return 0;
	}
	
	public void updateTick(World world, int x, int y, int z, Random random){
		super.updateTick(world, x, y, z, random);
		if(world.getBlockMetadata(x, y, z)==3){
			
			int tier=findTierOfGrowth(world,x,y,z);
			if(tier!=0){
				System.out.println("invalid Breeder Configuraton");
			}else{
				System.out.println("Breeder tier: "+tier);
			}
			
		}
	}
	

}
