package pixlepix.complexmachines.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import pixlepix.complexmachines.client.GuiHandler;
import pixlepix.complexmachines.common.crops.BasicSeeds;
import pixlepix.complexmachines.common.item.ClusterMinerItem;
import pixlepix.complexmachines.common.item.CubeFormerItem;
import pixlepix.complexmachines.common.item.FellerItem;
import pixlepix.complexmachines.common.item.GeneticRandomizer;
import pixlepix.complexmachines.common.item.LinkerItem;
import pixlepix.complexmachines.common.item.RangeExtender;
import pixlepix.complexmachines.common.item.RemoteItem;
import pixlepix.complexmachines.common.mob.GeneticMob;
import universalelectricity.prefab.flag.FlagRegistry;
import universalelectricity.prefab.flag.ModFlag;
import basiccomponents.common.BasicComponents;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "ComplexMachines", name = "Complex Machines", version = "0.3.3")
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = { "Complex Machines" }, packetHandler = pixlepix.complexmachines.common.PacketHandler.class)
public class ComplexMachines {
	private GuiHandler guiHandler = new GuiHandler();

	
	public static BetterLoader loader;
	
	public static ComplexMachinesTab creativeTab = new ComplexMachinesTab();
	
	public static Item feller;

	public static Item remote;
	public static Item clusterMiner;
	public static Item cubeFormer;
	public static Item randomizer;


	public static Item rangeExtender;

	public static Item linker;

	public static Item breederSeed;

	public static Item cultivatorSeed;
	
	public static ModFlag flag;

	public static boolean worldGen;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void loadBlocks(){
		EntityRegistry.registerModEntity(GeneticMob.class, "GeneticMob", 1, this, 50, 1, true);
		registerEntityEgg(GeneticMob.class, 0xffffff, 0x000000);
	


	



	
	
	feller=new FellerItem(Config.itemStartingID+1);

	remote=new RemoteItem(Config.itemStartingID+6);

	clusterMiner=new ClusterMinerItem(Config.itemStartingID+2);

	cubeFormer=new CubeFormerItem(Config.itemStartingID+3);

	randomizer=new GeneticRandomizer(Config.itemStartingID+5);

	rangeExtender=new RangeExtender(Config.itemStartingID+4);

	linker=new LinkerItem(Config.itemStartingID+7);

	breederSeed=new BasicSeeds(Config.itemStartingID+8, Config.blockStartingID+31,60,"ComplexMachines:BreederSeeds","BreederSeed");
	cultivatorSeed=new BasicSeeds(Config.itemStartingID+9, Config.blockStartingID+32,2,"ComplexMachines:CultivatorSeeds","CultivatorSeed");

	
	
	BasicComponents.requestAll();
	

	
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
		loader=new BetterLoader();
		loader.loadBlocks();
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

		/*
		for(ForgeDirection dir:ForgeDirection.VALID_DIRECTIONS){
			System.out.println(dir.toString()+dir.ordinal());
		}
		*/
		
		TickRegistry.registerTickHandler(new AirshipBlockRegistry(), Side.SERVER);

		TickRegistry.registerTickHandler(new AirshipBlockRegistry(), Side.CLIENT);
		FlagRegistry.registerFlag("ComplexMachines");
		proxy.registerRenderers();
		
		loader.mainload();
		proxy.init();
		LanguageRegistry.instance().addStringLocalization("entity.ComplexMachines.GeneticMob.name", "Mutant");
		
		NetworkRegistry networkRegistry = NetworkRegistry.instance();
		networkRegistry.registerGuiHandler(this, guiHandler);
		

		

		LanguageRegistry.instance().addStringLocalization(
				"itemGroup.tabComplexMachines", "Complex Machines");
		

		LanguageRegistry.addName(feller, "Feller");

		LanguageRegistry.addName(remote, "Remote Interloper");

		LanguageRegistry.addName(cubeFormer, "Cube Former");

		LanguageRegistry.addName(randomizer, "GeneticRandomizer");
		
		
		LanguageRegistry.addName(rangeExtender, "Motor range extender upgrade");

		LanguageRegistry.addName(clusterMiner, "Cluster Miner");
		LanguageRegistry.addName(linker, "Link former");
		

		LanguageRegistry.addName(breederSeed, "Breeder Seed");

		LanguageRegistry.addName(cultivatorSeed, "Cultivator Seed");

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(remote),true,new Object[]{"xyx", "xyx", 'x', "ingotCopper", 'y', "circuitBasic"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(linker),true,new Object[]{" y ", "yxy", " x ", 'x', "circuitBasic", 'y', new ItemStack(Item.arrow)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(rangeExtender),true,new Object[]{" x ", " x ", " y ", 'x', "ingotSteel", 'y', "plateCopper"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(cubeFormer),true,new Object[]{"xy", "yx", 'x', "plateSteel", 'y', "circuitBasic"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(feller),true,new Object[]{"xyx", " x ", " x ", 'x', new ItemStack(Item.stick), 'y', "circuitBasic"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(clusterMiner),true,new Object[]{"xyx", " x ", " x ", 'x', new ItemStack(Item.ingotIron), 'y', "circuitBasic"}));
		
		
		MinecraftForge.addGrassSeed(new ItemStack(breederSeed), 10);
		

		GameRegistry.registerWorldGenerator(new ComplexMachinesWorldGen());

		
		


	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
	
	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) 
	 {
	  int id = Config.itemStartingID+7;
	  EntityList.IDtoClassMapping.put(id, entity);
	  EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
	 }
}
