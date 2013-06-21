package pixlepix.complexmachines.common.crops;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import pixlepix.complexmachines.common.Config;

public class ReplanterCrop extends BasicCrop {
	
	
	public ReplanterCrop() {
		super(36, Config.itemStartingID+12);
	}

	@Override
	public String getTextureBase() {
		return "ComplexMachines:replanter_";
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
		return "Replanter";
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
			System.out.println("Replanting Crops");
			List <EntityItem> items=world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(x-10, y-1, z-10, x+10, y+1, z+10));
			
			Iterator<EntityItem> iter=items.iterator();
			
			while(iter.hasNext()){
				System.out.println("Iteriating through items");
				EntityItem entity=iter.next();
				if (entity.getEntityItem().getItem() instanceof BasicSeeds){
					System.out.println("Found a seed");
					BasicSeeds seeds=(BasicSeeds) entity.getEntityItem().getItem();
					seeds.plantSeed(entity.getEntityItem(), world, (int)Math.floor(entity.posX), (int)Math.floor(entity.posY)-1, (int)Math.floor(entity.posZ));
				}
			}
			
			
		}
	}
	
	
	
	public int getInfertileRate(){
		return 1;
	}
	public int getFertileRate(){
		return 1;
	}

}
