package pixlepix.complexmachines.common.crops;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import pixlepix.complexmachines.common.Config;

public class PowderweedCrop extends BasicCrop {

public PowderweedCrop() {
		super(39, Config.itemStartingID+16);
	}

	@Override
	public String getTextureBase() {
		return "ComplexMachines:powderweed_";
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
		return "Powderweed";
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
		list.add(new ItemStack(Item.gunpowder,5,0));
		return list;
	}

	

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
		if(entity instanceof EntityPlayer)
		world.createExplosion(null, x,y,z, 2, true);
	}
	
	
	
	
	
	
	
	
	
	

	}

