package pixlepix.complexmachines.common;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;

import pixlepix.complexmachines.common.block.*;
import pixlepix.complexmachines.common.crops.BreederCrop;
import pixlepix.complexmachines.common.crops.CultivatorCrop;
import pixlepix.complexmachines.common.itemblock.MotorItemBlock;
import pixlepix.complexmachines.common.laser.LaserEmitter;
import pixlepix.complexmachines.common.laser.block.*;
import pixlepix.complexmachines.common.laser.tileentity.HarmingLaserBeamTileEntity;

public class BetterLoader {

	public static ArrayList<Block> blocks=new ArrayList<Block>();
	public static ArrayList<Class> classes=new ArrayList<Class>();
	
	public void populateClasses(){
		classes.add(Controller.class);

		classes.add(BreederCrop.class);

		classes.add(CultivatorCrop.class);
		classes.add(FillerMachine.class);


		classes.add(Flux.class);

		classes.add(ExtractorMachine.class);


		classes.add(NodeBlock.class);

		classes.add(FocalPoint.class);

		classes.add(FocalPointControlled.class);

		classes.add(Grinder.class);

		classes.add(Motor.class);

		classes.add(OceanGenerator.class);

		classes.add(ReplacerMachine.class);

		classes.add(SinglePointGenerator.class);

		classes.add(LaserEmitter.class);

		classes.add(DebuffLaserBlock.class);

		classes.add(ElecrtricLaserBlock.class);

		classes.add(GlassLaserBlock.class);

		classes.add(HarmingLaserBlock.class);

		classes.add(LaserBlock.class);

		classes.add(RedstoneLaserBlock.class);

		classes.add(SuctionLaserBlock.class);

		classes.add(TripwireLaserBlock.class);
		
	}
	
	
	public void loadBlocks(){
		
		populateClasses();
		
		
		
		try{
			for(int i=0;i<classes.size();i++){
				
				Class currentClass=classes.get(i);
				Class clazz=(Class<Block>)currentClass;
				Block newBlock=((Block) clazz.newInstance()).setHardness(0.5F).setStepSound(Block.soundAnvilFootstep);
				if(((IBlock)newBlock).inCreativeTab()){
					newBlock.setCreativeTab(ComplexMachines.creativeTab);
				}
				blocks.add(newBlock);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Block getBlock(Class clazz){

		for(int i=0;i<blocks.size();i++){

			Block currentBlock=blocks.get(i);

			if(clazz.isInstance(currentBlock)){
				return currentBlock;
			}
		}
		System.out.println("Failed to find block in BetterLoader. Crash incoming.");
		return null;
	}
	public void mainload(){
		System.out.println(blocks);
		for(int i=0;i<blocks.size();i++){
			
			Block currentBlock=blocks.get(i);
			if(currentBlock instanceof IBlock){
				IBlock currentIBlock=(IBlock)currentBlock;
				LanguageRegistry.addName(currentBlock, currentIBlock.getName());

				MinecraftForge.setBlockHarvestLevel(currentBlock, "pickaxe", 0);
				if(currentIBlock.hasItemBlock()){

					GameRegistry.registerBlock(currentBlock, currentIBlock.getItemBlock(), currentIBlock.getName());
				}else{
					GameRegistry.registerBlock(currentBlock, currentIBlock.getName());
				}
				currentIBlock.addRecipe();

				GameRegistry.registerTileEntity(currentIBlock.getTileEntityClass(), currentIBlock.getName()+"Complex Machines Tile Entity");
			}
			
		}
	}
	
	
	
}
