package pixlepix.complexmachines.common;

import pixlepix.complexmachines.client.GuiHandler;

import pixlepix.complexmachines.common.block.Controller;
import pixlepix.complexmachines.common.Config;
import pixlepix.complexmachines.common.block.ExtractorMachine;
import pixlepix.complexmachines.common.block.FillerMachine;
import pixlepix.complexmachines.common.block.Flux;
import pixlepix.complexmachines.common.block.FocalPoint;
import pixlepix.complexmachines.common.block.FocalPointControlled;
import pixlepix.complexmachines.common.block.Grinder;
import pixlepix.complexmachines.common.block.Motor;
import pixlepix.complexmachines.common.block.OceanGenerator;
import pixlepix.complexmachines.common.block.ReplacerMachine;
import pixlepix.complexmachines.common.block.SinglePointGenerator;
import pixlepix.complexmachines.common.item.ClusterMinerItem;
import pixlepix.complexmachines.common.item.FellerItem;
import pixlepix.complexmachines.common.item.CubeFormerItem;
import pixlepix.complexmachines.common.itemblock.ControllerItemBlock;
import pixlepix.complexmachines.common.itemblock.ExtractorItemBlock;
import pixlepix.complexmachines.common.itemblock.FillerItemBlock;
import pixlepix.complexmachines.common.itemblock.GrinderItemBlock;
import pixlepix.complexmachines.common.itemblock.LaserItemBlock;
import pixlepix.complexmachines.common.itemblock.MotorItemBlock;
import pixlepix.complexmachines.common.itemblock.OceanGeneratorItemBlock;
import pixlepix.complexmachines.common.itemblock.ReplacerItemBlock;
import pixlepix.complexmachines.common.itemblock.SinglePointItemBlock;
import pixlepix.complexmachines.common.laser.LaserEmitter;
import pixlepix.complexmachines.common.laser.LaserEmitterTileEntity;
import pixlepix.complexmachines.common.laser.block.ChargingLaserBlock;
import pixlepix.complexmachines.common.laser.block.DebuffLaserBlock;
import pixlepix.complexmachines.common.laser.block.ElecrtricLaserBlock;
import pixlepix.complexmachines.common.laser.block.GlassLaserBlock;
import pixlepix.complexmachines.common.laser.block.HarmingLaserBlock;
import pixlepix.complexmachines.common.laser.block.LaserBlock;
import pixlepix.complexmachines.common.laser.block.MiningLaserBlock;
import pixlepix.complexmachines.common.laser.block.RedstoneLaserBlock;
import pixlepix.complexmachines.common.laser.block.StoneLaserBlock;
import pixlepix.complexmachines.common.laser.block.SuctionLaserBlock;
import pixlepix.complexmachines.common.laser.block.TripwireLaserBlock;
import pixlepix.complexmachines.common.laser.tileentity.ChargingLaserBeamTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.DebuffLaserBeamTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.ElectricLaserBeamTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.HarmingLaserBeamTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.LaserBeamTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.SuctionLaserBeamTileEntity;
import pixlepix.complexmachines.common.laser.tileentity.TripwireLaserBeamTileEntity;
import pixlepix.complexmachines.common.tileentity.ControllerTileEntity;
import pixlepix.complexmachines.common.tileentity.ExtractorMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.FillerMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.FluxTileEntity;
import pixlepix.complexmachines.common.tileentity.FocalPointControledTileEntity;
import pixlepix.complexmachines.common.tileentity.GrinderTileEntity;
import pixlepix.complexmachines.common.tileentity.MotorTileEntity;
import pixlepix.complexmachines.common.tileentity.OceanGeneratorTileEntity;
import pixlepix.complexmachines.common.tileentity.ReplacerMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.SinglePointTileEntity;
import universalelectricity.prefab.flag.FlagRegistry;
import universalelectricity.prefab.flag.ModFlag;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "ComplexMachines", name = "Complex Machines", version = "0.3.3")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ComplexMachines {
	private GuiHandler guiHandler = new GuiHandler();

	public static ComplexMachinesTab creativeTab = new ComplexMachinesTab();
	
	public static Item feller;
	public static Item clusterMiner;
	public static Item cubeFormer;
	
	public static ModFlag flag;
	
	public static Block controller;
	public static Block motor;
	
	public static boolean worldGen;
	public static Block flux;
	public static Block tripwireLaser;
	public static Block stoneLaser;
	public static Block suctionLaser;
	public static Block electricLaser;
	public static Block miningLaser;
	public static Block chargingLaser;
	public static Block redstoneLaser;
	public static Block debuffLaser;
	public static Block grinder;
	public static Block focalPointControlled;
	public static Block focalPoint;
	public static Block laserEmitter;
	public static Block laserBlock;
	public static Block glassLaserBlock;
	public static Block harmingLaserBlock;
	public static Block extractorMachine;
	public static Block fillerMachine;
	public static Block replacerMachine;
	public static Block oceanGenerator;
	public static Block singlePoint;
	
	public void loadBlocks(){
	flux = new Flux(Config.blockStartingID + 18)
			.setHardness(0.5F).setStepSound(Block.soundGravelFootstep).setCreativeTab(creativeTab);

	tripwireLaser = new TripwireLaserBlock(Config.blockStartingID + 20)
			.setHardness(0.5F).setStepSound(Block.soundGravelFootstep);
	stoneLaser = new StoneLaserBlock(Config.blockStartingID + 19)
			.setHardness(0.5F).setStepSound(Block.soundGravelFootstep);
	suctionLaser = new SuctionLaserBlock(Config.blockStartingID + 17)
			.setHardness(0.5F).setStepSound(Block.soundGravelFootstep);
	electricLaser = new ElecrtricLaserBlock(Config.blockStartingID + 16)
			.setHardness(0.5F).setStepSound(Block.soundGravelFootstep);
	miningLaser = new MiningLaserBlock(Config.blockStartingID + 15)
			.setHardness(0.5F).setStepSound(Block.soundGravelFootstep);
	redstoneLaser = new RedstoneLaserBlock(Config.blockStartingID + 14)
			.setHardness(0.5F).setStepSound(Block.soundGravelFootstep);

	debuffLaser = new DebuffLaserBlock(Config.blockStartingID + 13)
			.setHardness(0.5F).setStepSound(Block.soundGravelFootstep);

	grinder = new Grinder(Config.blockStartingID + 7)
			.setHardness(0.5F).setStepSound(Block.soundGravelFootstep)
			.setCreativeTab(creativeTab);
	

	controller = new Controller(Config.blockStartingID + 23)
			.setHardness(0.5F).setStepSound(Block.soundGravelFootstep)
			.setCreativeTab(creativeTab);
	

	chargingLaser = new ChargingLaserBlock(Config.blockStartingID + 21)

			.setHardness(0.5F).setStepSound(Block.soundGravelFootstep);
	
	focalPointControlled = new FocalPointControlled(
			Config.blockStartingID + 8).setHardness(0.5F)
			.setStepSound(Block.soundGravelFootstep)
			.setCreativeTab(creativeTab);

	focalPoint = new FocalPoint(Config.blockStartingID + 3)
			.setHardness(0.5F).setStepSound(Block.soundGravelFootstep)
			.setCreativeTab(creativeTab);

	laserEmitter = new LaserEmitter(
			Config.blockStartingID + 9).setHardness(0.5F)
			.setStepSound(Block.soundGravelFootstep)
			.setCreativeTab(creativeTab);

	laserBlock = new LaserBlock(Config.blockStartingID + 10)
			.setHardness(0.5F).setStepSound(Block.soundGravelFootstep);

	glassLaserBlock = new GlassLaserBlock(Config.blockStartingID+12)
			.setHardness(0.5F).setStepSound(Block.soundGravelFootstep);
	
	harmingLaserBlock = new HarmingLaserBlock(Config.blockStartingID + 11)
	.setHardness(0.5F).setStepSound(Block.soundGravelFootstep);



	extractorMachine = new ExtractorMachine(
			Config.blockStartingID + 2).setHardness(0.5F)
			.setStepSound(Block.soundGravelFootstep)
			.setCreativeTab(creativeTab);

	fillerMachine = new FillerMachine(
			Config.blockStartingID + 1).setHardness(0.5F)
			.setStepSound(Block.soundGravelFootstep)
			.setCreativeTab(creativeTab);

	replacerMachine = new ReplacerMachine(
			Config.blockStartingID + 6).setHardness(0.5F)
			.setStepSound(Block.soundGravelFootstep)
			.setCreativeTab(creativeTab);
	
	motor = new Motor(
			Config.blockStartingID + 22).setHardness(0.5F)
			.setStepSound(Block.soundGravelFootstep)
			.setCreativeTab(creativeTab);
	oceanGenerator = new OceanGenerator(
			Config.blockStartingID + 5).setHardness(0.5F)
			.setStepSound(Block.soundGravelFootstep)
			.setCreativeTab(creativeTab);

	singlePoint = new SinglePointGenerator(
			Config.blockStartingID + 4).setHardness(0.5F)
			.setStepSound(Block.soundGravelFootstep)
			.setCreativeTab(creativeTab);
	
	feller=new FellerItem(Config.itemStartingID+1);

	clusterMiner=new ClusterMinerItem(Config.itemStartingID+2);

	cubeFormer=new CubeFormerItem(Config.itemStartingID+3);

	
	}
	
	// The instance of your mod that Forge uses.
	@Instance("ComplexMachines")
	public static ComplexMachines instance;
	public static ComplexMachinesWorldGen generator = new ComplexMachinesWorldGen();
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "pixlepix.complexmachines.client.ClientProxy", serverSide = "pixlepix.complexmachines.common.CommonProxy")
	public static CommonProxy proxy;

	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Config.configure(event);
		System.out.println(Config.blockStartingID);
		loadBlocks();
	}

	public static boolean isProtected(int x, int z){
		int distance=(int) Math.sqrt((x*x)+(z*z));
		if(distance<Config.spawnProtectionRadius){
			return true;
		}
		return false;
	}
	
	
	
	@Init
	public void load(FMLInitializationEvent event) {

		
		//NBTTagCompound nbt=new NBTTagCompound();
		//nbt.setString("dim_1","dim_1");
		//flag=new ModFlag(nbt);
		FlagRegistry.registerFlag("ComplexMachines");
		proxy.registerRenderers();

		NetworkRegistry networkRegistry = NetworkRegistry.instance();
		networkRegistry.registerGuiHandler(this, guiHandler);
		

		LanguageRegistry.addName(motor, "Airship Motor");
		MinecraftForge.setBlockHarvestLevel(motor, "pickaxe", 0);
		GameRegistry.registerBlock(motor, MotorItemBlock.class);
		

		LanguageRegistry.addName(controller, "Airship Controller");
		MinecraftForge.setBlockHarvestLevel(controller, "pickaxe", 0);
		GameRegistry.registerBlock(controller, ControllerItemBlock.class);
		

		LanguageRegistry.addName(flux, "Flux");
		MinecraftForge.setBlockHarvestLevel(flux, "pickaxe", 0);
		GameRegistry.registerBlock(flux, "Flux");
		

		LanguageRegistry.addName(suctionLaser, "suction Laser");
		MinecraftForge.setBlockHarvestLevel(suctionLaser, "pickaxe", 0);
		GameRegistry.registerBlock(suctionLaser, "suction Laser");
		
		LanguageRegistry.addName(tripwireLaser, "Tripwire Laser");
		MinecraftForge.setBlockHarvestLevel(tripwireLaser, "pickaxe", 0);
		GameRegistry.registerBlock(tripwireLaser, "Tripwire Laser");
		
		
		LanguageRegistry.addName(stoneLaser, "Bridge Laser");
		MinecraftForge.setBlockHarvestLevel(stoneLaser, "pickaxe", 0);
		GameRegistry.registerBlock(stoneLaser, "Bridge Laser");
		
		LanguageRegistry.addName(electricLaser, "electric Laser");
		MinecraftForge.setBlockHarvestLevel(electricLaser, "pickaxe", 0);
		GameRegistry.registerBlock(electricLaser, "electric Laser");

		LanguageRegistry.addName(miningLaser, "mining Laser");
		MinecraftForge.setBlockHarvestLevel(miningLaser, "pickaxe", 0);
		GameRegistry.registerBlock(miningLaser, "mining Laser");
		
		LanguageRegistry
				.addName(focalPointControlled, "Controlled focal point");
		MinecraftForge.setBlockHarvestLevel(focalPointControlled, "pickaxe", 0);
		GameRegistry.registerBlock(focalPointControlled, "Controlled focal point");

		LanguageRegistry.addName(grinder, "Grinder");
		MinecraftForge.setBlockHarvestLevel(grinder, "pickaxe", 0);
		GameRegistry.registerBlock(grinder, GrinderItemBlock.class);
		

		LanguageRegistry.addName(debuffLaser, "Debuff Laser");
		MinecraftForge.setBlockHarvestLevel(debuffLaser, "pickaxe", 0);
		GameRegistry.registerBlock(debuffLaser, "Debuff Laser");
		

		LanguageRegistry.addName(redstoneLaser, "Redstone Laser");
		MinecraftForge.setBlockHarvestLevel(redstoneLaser, "pickaxe", 0);
		GameRegistry.registerBlock(redstoneLaser, "Redstone Laser");
		

		LanguageRegistry.addName(glassLaserBlock, "Glass laser");
		MinecraftForge.setBlockHarvestLevel(glassLaserBlock, "pickaxe", 0);
		GameRegistry.registerBlock(glassLaserBlock, "Glass laser");

		LanguageRegistry.addName(replacerMachine, "Replacer Machine");
		MinecraftForge.setBlockHarvestLevel(replacerMachine, "pickaxe", 0);
		GameRegistry.registerBlock(replacerMachine, ReplacerItemBlock.class);

		LanguageRegistry.addName(oceanGenerator, "Ocean Generator");
		MinecraftForge.setBlockHarvestLevel(oceanGenerator, "pickaxe", 0);
		GameRegistry.registerBlock(oceanGenerator, OceanGeneratorItemBlock.class);

		LanguageRegistry.addName(extractorMachine, "Extractor");
		MinecraftForge.setBlockHarvestLevel(extractorMachine, "pickaxe", 0);
		GameRegistry.registerBlock(extractorMachine, ExtractorItemBlock.class);

		LanguageRegistry.addName(laserBlock, "Beam");
		MinecraftForge.setBlockHarvestLevel(laserBlock, "pickaxe", 0);
		GameRegistry.registerBlock(laserBlock, "Beam");
		

		LanguageRegistry.addName(harmingLaserBlock, "Harming Beam");
		MinecraftForge.setBlockHarvestLevel(harmingLaserBlock, "pickaxe", 0);
		GameRegistry.registerBlock(harmingLaserBlock, "Hariming Beam");


		LanguageRegistry.addName(laserEmitter, "Laser Emitter");
		MinecraftForge.setBlockHarvestLevel(laserEmitter, "pickaxe", 0);
		GameRegistry.registerBlock(laserEmitter, LaserItemBlock.class);

		LanguageRegistry.addName(focalPoint, "Focal Point");
		MinecraftForge.setBlockHarvestLevel(focalPoint, "pickaxe", 0);
		GameRegistry.registerBlock(focalPoint, "Focal point");

		LanguageRegistry.addName(fillerMachine, "Filler");
		MinecraftForge.setBlockHarvestLevel(fillerMachine, "pickaxe", 0);
		GameRegistry.registerBlock(fillerMachine, FillerItemBlock.class);

		LanguageRegistry.addName(singlePoint, "Single Point Generator");
		MinecraftForge.setBlockHarvestLevel(singlePoint, "pickaxe", 0);
		GameRegistry.registerBlock(singlePoint, SinglePointItemBlock.class);

		LanguageRegistry.instance().addStringLocalization(
				"itemGroup.tabComplexMachines", "Complex Machines");
		

		LanguageRegistry.addName(feller, "Feller");

		LanguageRegistry.addName(cubeFormer, "Cube Former");

		LanguageRegistry.addName(clusterMiner, "Cluster Miner");

		
		ItemStack steelPlate = new ItemStack(14239, 1, 3);
		ItemStack stone = new ItemStack(1, 1, 0);
		ItemStack basicCircuit = new ItemStack(14229, 1, 0);
		ItemStack result = new ItemStack(Config.blockStartingID + 1, 1, 0);
		ItemStack eliteCircuit = new ItemStack(14229, 1, 2);
		ItemStack diamondPickaxe = new ItemStack(278, 1, 0);
		ItemStack extractor = new ItemStack(Config.blockStartingID + 2, 1, 0);
		ItemStack waterBucket = new ItemStack(326, 1, 0);
		ItemStack advancedCircuit = new ItemStack(14229, 1, 1);
		ItemStack singlePoint = new ItemStack(Config.blockStartingID + 4, 1, 0);
		ItemStack oceanGenerator = new ItemStack(Config.blockStartingID + 5, 1, 0);
		ItemStack replacerMachine = new ItemStack(Config.blockStartingID + 6, 1, 0);
		ItemStack grinder = new ItemStack(Config.blockStartingID + 7, 1, 0);
		ItemStack emitter = new ItemStack(Config.blockStartingID + 9, 1, 0);
		ItemStack motorCrafting = new ItemStack(14240,1,0);
		ItemStack glowstone = new ItemStack(Block.blocksList[89]);
		ItemStack diamond = new ItemStack(Item.diamond);
		ItemStack diamondBlock = new ItemStack(57,1,0);
		ItemStack goldBlock = new ItemStack(41,1,0);
		ItemStack ironBlock = new ItemStack(42,1,0);
		ItemStack ironIngot = new ItemStack(Item.ingotIron);
		ItemStack fellerCrafting=new ItemStack(feller);
		ItemStack axe=new ItemStack(Item.axeDiamond);
		ItemStack clusterMinerCrafting=new ItemStack(clusterMiner);
		ItemStack airshipMotorCrafting=new ItemStack(motor);

		ItemStack airshipMotorCraftingOutput=new ItemStack(motor,64);
		ItemStack airshipControllerCrafting=new ItemStack(controller);
		ItemStack cubeFormerCrafting=new ItemStack(cubeFormer);
		
		if(Config.vanillaRecipies){
			GameRegistry.addRecipe(emitter, "xyx", "yzy", "xyx", 'x', diamond, 'y',
					glowstone, 'z', diamondBlock);
			
			GameRegistry.addRecipe(grinder, "xxx", "xyx", "xxx", 'x', ironIngot,
					'y', diamondBlock);
			
			GameRegistry.addRecipe(replacerMachine, "xxx", "yzy", "xxx", 'x',
					ironIngot, 'y', result, 'z', diamond);
			
			GameRegistry.addRecipe(singlePoint, "xyx", "yxy", "xyx", 'x',
					diamond, 'y', diamondBlock);
			
			GameRegistry.addRecipe(extractor, "xyx", "xzx", "xxx", 'x', ironIngot,
					'y', diamondPickaxe, 'z', diamondBlock);


			GameRegistry.addRecipe(oceanGenerator, "xyx", "zyz", "xyx", 'x',
					waterBucket, 'y', diamondBlock, 'z', ironIngot);

			GameRegistry.addRecipe(result, "xyx", "yzy", "xyx", 'x', stone, 'y',
					diamond, 'z', ironIngot);
			

			GameRegistry.addRecipe(clusterMinerCrafting, "xxx", "xyx", "xxx", 'x', diamond, 'y', diamondPickaxe);
			GameRegistry.addRecipe(fellerCrafting, "xxx", "xyx", "xxx", 'x', diamond, 'y', axe);
		}
		
		
		
		GameRegistry.addRecipe(airshipControllerCrafting, "xxx", "xyx", "xxx", 'x', steelPlate, 'y', airshipMotorCrafting);
		GameRegistry.addRecipe(airshipMotorCraftingOutput, "xyx", "yzy", "xyx", 'x', basicCircuit, 'y', steelPlate, 'z', motorCrafting);

		GameRegistry.addRecipe(clusterMinerCrafting, "xxx", "xyx", "xxx", 'x', basicCircuit, 'y', diamondPickaxe);
		GameRegistry.addRecipe(fellerCrafting, "xxx", "xyx", "xxx", 'x', basicCircuit, 'y', axe);

		GameRegistry.addRecipe(cubeFormerCrafting, " x ", "xyx", " x ", 'x', steelPlate, 'y',
				advancedCircuit);
		GameRegistry.addRecipe(emitter, "xyx", "yzy", "xyx", 'x', diamond, 'y',
				glowstone, 'z', eliteCircuit);
		GameRegistry.addRecipe(grinder, "xxx", "xyx", "xxx", 'x', steelPlate,
				'y', eliteCircuit);
		GameRegistry.addRecipe(replacerMachine, "xxx", "yzy", "xxx", 'x',
				steelPlate, 'y', result, 'z', basicCircuit);
		GameRegistry.addRecipe(singlePoint, "xyx", "yxy", "xyx", 'x',
				basicCircuit, 'y', eliteCircuit);
		GameRegistry.addRecipe(extractor, "xyx", "xzx", "xxx", 'x', steelPlate,
				'y', diamondPickaxe, 'z', eliteCircuit);

		GameRegistry.addRecipe(oceanGenerator, "xyx", "zyz", "xyx", 'x',
				waterBucket, 'y', eliteCircuit, 'z', steelPlate);

		GameRegistry.addRecipe(result, "xyx", "yzy", "xyx", 'x', stone, 'y',
				basicCircuit, 'z', steelPlate);
		
		
		

		GameRegistry.registerWorldGenerator(new ComplexMachinesWorldGen());

		GameRegistry
				.registerTileEntity(LaserBeamTileEntity.class, "Laser Beam");
		
		GameRegistry
				.registerTileEntity(HarmingLaserBeamTileEntity.class, "Harming Laser Beam");

		GameRegistry
				.registerTileEntity(LaserEmitterTileEntity.class, "Emitter");
		GameRegistry.registerTileEntity(GrinderTileEntity.class, "Grinder");
		GameRegistry.registerTileEntity(OceanGeneratorTileEntity.class,
				"Ocean Generator");
		GameRegistry.registerTileEntity(SinglePointTileEntity.class,
				"Single Point generator");
		GameRegistry.registerTileEntity(FocalPointControledTileEntity.class,
				"Focal Point");
		GameRegistry.registerTileEntity(FillerMachineTileEntity.class,
				"Filler Machine");

		GameRegistry.registerTileEntity(SuctionLaserBeamTileEntity.class,
				"Suction Laser");
		
		GameRegistry.registerTileEntity(ChargingLaserBeamTileEntity.class,
				"Charging Laser");

		GameRegistry.registerTileEntity(DebuffLaserBeamTileEntity.class,
				"Debugg Laser");
		GameRegistry.registerTileEntity(ElectricLaserBeamTileEntity.class,
				"Electric Laser");
		GameRegistry.registerTileEntity(ExtractorMachineTileEntity.class,
				"Extractor Machine");
		
		GameRegistry.registerTileEntity(ReplacerMachineTileEntity.class,
				"Replacer");

		GameRegistry.registerTileEntity(TripwireLaserBeamTileEntity.class,
				"Tripbeam");
		

		GameRegistry.registerTileEntity(ControllerTileEntity.class,
				"Airship");
		
		GameRegistry.registerTileEntity(FluxTileEntity.class,
				"Flux");
		

		GameRegistry.registerTileEntity(MotorTileEntity.class,
				"Airship motor");
		


	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
