package archadia.complexmachines.core.common;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import universalelectricity.prefab.network.PacketManager;
import archadia.complexmachines.core.common.block.BlockAlloyFabricator;
import archadia.complexmachines.core.common.block.BlockCookieMaker;
import archadia.complexmachines.core.common.block.BlockExtractor;
import archadia.complexmachines.core.common.block.BlockGrinder;
import archadia.complexmachines.core.common.block.BlockWireMill;
import archadia.complexmachines.core.common.gen.OreGenerator;
import archadia.complexmachines.core.common.item.ItemAlloy;
import archadia.complexmachines.core.common.item.ItemIngot;
import archadia.complexmachines.core.common.item.ItemWire;
import archadia.complexmachines.core.common.proxy.CommonProxy;
import archadia.complexmachines.core.common.tileentity.TileEntityAlloyFabricator;
import archadia.complexmachines.core.common.tileentity.TileEntityCookieMaker;
import archadia.complexmachines.core.common.tileentity.TileEntityExtractor;
import archadia.complexmachines.core.common.tileentity.TileEntityGrinder;
import archadia.complexmachines.core.common.tileentity.TileEntityWireMill;
import archadia.complexmachines.helper.ArchHelper;
import archadia.complexmachines.helper.ArchLoader;
import archadia.complexmachines.helper.recipes.WiremillRecipes;
import archadia.complexmachines.prefab.block.BlockModOre;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Archadia
 * Originally by Pixlepix, renamed to Modech, now owned by Archadia(Me)
 */
@Mod(modid = ComplexMachines.MOD_ID, name = ComplexMachines.NAME, version = ComplexMachines.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = {ComplexMachines.CHANNEL}, packetHandler = PacketManager.class)
public class ComplexMachines {

	public static final String[] languages = new String[] {"en_US", "de_DE"};
	
	public static final String CHANNEL = "ComplexMachines";
	public static final String MOD_ID = "complexmachines";
	public static final String NAME = "Complex Machines";
	public static final String VERSION = "0.3.3";
	
	@Instance("complexmachines")
	public static ComplexMachines instance;
	
	@SidedProxy(clientSide = "archadia.complexmachines.core.common.proxy.ClientProxy", serverSide = "archadia.complexmachines.core.common.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static ComplexMachinesTab tabComplexMachines = new ComplexMachinesTab();
		
	public static ArchLoader loader = new ArchLoader();
	public static final Configuration config = new Configuration(new File(Loader.instance().getConfigDir() + "/Modech.cfg"));
	
	public static boolean oldExtractorMode = false;
	
	public static Block wireMill;
	public static Block alloyFabricator;
	public static Block oreCopper;
	public static Block oreTin;
	public static Block grinder;
	public static Block extractor;
	public static Block cookieMaker;
	
	public static Item ingotCopper;
	public static Item ingotTin;
	public static Item wiring1;
	public static Item wiring2;
	public static Item wiring3;
	public static Item C194;
	
	public int BLOCK_ID = 2389;
	public int ITEM_ID = 8932;
	
	public static boolean debug = true;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		oreCopper = new BlockModOre(2389, Material.rock, "oreCopper").setHardness(2F);
		oreTin = new BlockModOre(2390, Material.rock, "oreTin").setHardness(2F);
		wireMill = new BlockWireMill(2391, Material.iron, "wireMill").setHardness(2F);
		alloyFabricator = new BlockAlloyFabricator(2392, Material.iron, "alloyFabricator").setHardness(2F);;
		grinder = new BlockGrinder(2393, Material.iron, "grinder").setHardness(2F);
		extractor = new BlockExtractor(2394, Material.iron, "extractor").setHardness(2F);
		cookieMaker = new BlockCookieMaker(2395, Material.iron, "cookieMaker").setHardness(2F);

		ingotTin = new ItemIngot(8930, "ingotTin");
		ingotCopper = new ItemIngot(8931, "ingotCopper");
		C194 = new ItemAlloy(8932, "C194");
		wiring1 = new ItemWire(8933, "c194-wiring");
		wiring2 = new ItemWire(8934, "tin-wiring");
		wiring3 = new ItemWire(8935, "gold-wiring");
		
		config.load();
		
		oldExtractorMode = config.get(config.CATEGORY_GENERAL, "Old Extractor Mode", false).getBoolean(false);
		
		config.save();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		ArchHelper.println(""+oldExtractorMode);
		loader.addBlock(wireMill);
		loader.addBlock(oreTin);
		loader.addBlock(alloyFabricator);
		loader.addBlock(grinder);
		loader.addBlock(oreCopper);
		loader.addBlock(extractor);
		loader.addBlock(cookieMaker);
		loader.loadLanguages("/assets/complexmachines/lang/", languages);
		
		loader.loadBlocks();
		
		GameRegistry.registerTileEntity(TileEntityAlloyFabricator.class, "tileEntityAlloyFabricator");
		GameRegistry.registerTileEntity(TileEntityGrinder.class, "tileEntityGrinder");
		GameRegistry.registerTileEntity(TileEntityWireMill.class, "tileEntityWireMill");
		GameRegistry.registerTileEntity(TileEntityExtractor.class, "tileEntityExtractor");
		GameRegistry.registerTileEntity(TileEntityCookieMaker.class, "tileEntityCookieMaker");
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		
		OreDictionary.registerOre("ingotCopper", ingotCopper);
		OreDictionary.registerOre("oreCopper", oreCopper);
		OreDictionary.registerOre("ingotTin", ingotTin);
		OreDictionary.registerOre("oreTin", oreTin);
		
		WiremillRecipes.recipes().addWireMillRecipes(Item.ingotGold.itemID, new ItemStack(wiring3));
		WiremillRecipes.recipes().addWireMillRecipes(ingotTin.itemID, new ItemStack(wiring2));
		WiremillRecipes.recipes().addWireMillRecipes(C194.itemID, new ItemStack(wiring1));
	
		TileEntityExtractor.instance().addExtractorValidOre(oreTin.blockID);
		TileEntityExtractor.instance().addExtractorValidOre(oreCopper.blockID);
		
		GameRegistry.registerWorldGenerator(new OreGenerator());
	}
}
