package pixlepix.complexmachines.common.crops;



import java.util.ArrayList;
import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import pixlepix.complexmachines.common.Config;

	public class IrongrainCrop extends BasicCrop{

		public IrongrainCrop() {
			super(42, Config.itemStartingID+19);
		}

		@Override
		public String getTextureBase() {
			return "ComplexMachines:irongrain_";
		}

		@Override
		public boolean skipSustainCheck(){
			return true;
		}
		
		public int getInfertileRate(){
			return 125;
		}
		public int getFertileRate(){
			return 60;
		}
		
		@Override
		public boolean canPlantGrow(World world, int x, int y, int z) {
			
			//57 ==diamond block id
			return world.getBlockId(x, y, z)==42;
		}
		@Override
		public String getName() {
			return "Iron Grain";
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
			list.add(new ItemStack(Item.ingotIron));
			return list;
		}

		public void updateTick(World world, int x, int y, int z, Random random){

			int oldMeta=world.getBlockMetadata(x, y, z);
			super.updateTick(world, x, y, z, random);

			if(world.getBlockMetadata(x, y, z)==3&&oldMeta!=3){
				int attempts=0;
				while(attempts<15){
					int targetX=x+new Random().nextInt(20);
					targetX-=10;
					
					int targetY=y+new Random().nextInt(20);
					targetY-=10;
					
					int targetZ=z+new Random().nextInt(20);
					targetZ-=10;
					if(world.getBlockId(targetX, targetY, targetZ)!=0){
						world.destroyBlock(targetX, targetY, targetZ, true);
						attempts=20;
					}else{
						attempts++;
					}
				}
			}
		}

	


}
