package pixlepix.complexmachines.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeDirection;
import pixlepix.complexmachines.client.GuiHandler;
import pixlepix.complexmachines.common.item.ClusterMinerItem;
import pixlepix.complexmachines.common.item.CubeFormerItem;
import pixlepix.complexmachines.common.item.FellerItem;
import pixlepix.complexmachines.common.item.GeneticRandomizer;
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
