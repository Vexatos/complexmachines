package pixlepix.complexmachines.common.crops;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;

public class MonsterweedCrop extends BasicCrop {
	
	
	public MonsterweedCrop() {
		super(37, Config.itemStartingID+13);
	}

	@Override
	public String getTextureBase() {
		return "ComplexMachines:monsterweed_";
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
		return "Monsterweed";
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
		list.add(new ItemStack(ComplexMachines.monsterweed,2,0));
		return list;
	}

	
	
	
	
	
	public void updateTick(World world, int x, int y, int z, Random random){
		int oldMeta=world.getBlockMetadata(x, y, z);
		super.updateTick(world, x, y, z, random);
		if((world.getBlockMetadata(x, y, z)==3&&oldMeta!=3)||(world.getBlockMetadata(x, y, z)==2&&oldMeta!=2)||(world.getBlockMetadata(x, y, z)==1&&oldMeta!=1)){
			Entity entity=new EntityCreeper(world);
				//entity.posX=x;
				//entity.posY=y+2;
				//entity.posZ=z;
			entity.setPosition(x, y+2, z);
				
			world.spawnEntityInWorld(entity);
		}
	}
	
	
	
	

}
