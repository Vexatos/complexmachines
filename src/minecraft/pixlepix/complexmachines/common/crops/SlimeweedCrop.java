package pixlepix.complexmachines.common.crops;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import pixlepix.complexmachines.common.Config;

public class SlimeweedCrop extends BasicCrop {
	
	
	public SlimeweedCrop() {
		super(40, Config.itemStartingID+17);
	}

	@Override
	public String getTextureBase() {
		return "ComplexMachines:slimeweed_";
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
		return "Slimeweed";
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

		list.add(new ItemStack(Item.slimeBall,2,0));
		return list;
	}

	
	
	
	
	
	public void updateTick(World world, int x, int y, int z, Random random){
		int oldMeta=world.getBlockMetadata(x, y, z);
		super.updateTick(world, x, y, z, random);
			
			if((world.getBlockMetadata(x, y, z)==3&&oldMeta!=3)||(world.getBlockMetadata(x, y, z)==2&&oldMeta!=2)||(world.getBlockMetadata(x, y, z)==1&&oldMeta!=1)){
				List<EntityLiving> entityList=world.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(x-10, y-10, z-10, x+10, y+10, z+10));
				Iterator<EntityLiving> iter=entityList.iterator();
				while(iter.hasNext()){
					EntityLiving entity=iter.next();
					PotionEffect slow = new PotionEffect( Potion.moveSlowdown.getId(), 2000,5 );
					PotionEffect weak = new PotionEffect( Potion.wither.getId(), 2000,5 );
					PotionEffect wither = new PotionEffect( Potion.weakness.getId(), 2000,5 );
					entity.addPotionEffect(slow);
					entity.addPotionEffect(weak);
					entity.addPotionEffect(wither);
				}
			}
			
		
	}
	
	
	

}
