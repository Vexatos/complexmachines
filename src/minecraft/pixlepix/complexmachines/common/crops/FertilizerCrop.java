package pixlepix.complexmachines.common.crops;

import java.util.ArrayList;
import java.util.Random;

import pixlepix.complexmachines.common.Config;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FertilizerCrop extends BasicCrop {

	public FertilizerCrop() {
		super(33, Config.itemStartingID+10);
	}

	@Override
	public String getTextureBase() {
		return "ComplexMachines:fertilizer_";
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
		return "Fertilzier";
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
			
			int targetX=x;
			int targetY=y;
			int targetZ=z;
			
			targetX=x-1;
			targetZ=z;
			if(Block.blocksList[world.getBlockId(targetX, targetY, targetZ)] instanceof BasicCrop){
				world.setBlockMetadataWithNotify(targetX, targetY, targetZ, 3,3);
				
			}
			
			targetX=x+1;
			targetZ=z;
			if(Block.blocksList[world.getBlockId(targetX, targetY, targetZ)] instanceof BasicCrop){
				world.setBlockMetadataWithNotify(targetX, targetY, targetZ, 3,3);
				
			}
			
			targetX=x;
			targetZ=z-1;
			if(Block.blocksList[world.getBlockId(targetX, targetY, targetZ)] instanceof BasicCrop){
				world.setBlockMetadataWithNotify(targetX, targetY, targetZ, 3,3);
				
			}
			
			targetX=x-1;
			targetZ=z+1;
			if(Block.blocksList[world.getBlockId(targetX, targetY, targetZ)] instanceof BasicCrop){
				world.setBlockMetadataWithNotify(targetX, targetY, targetZ, 3,3);
				
			}
			
		}
	}
	
	
	
	

}
