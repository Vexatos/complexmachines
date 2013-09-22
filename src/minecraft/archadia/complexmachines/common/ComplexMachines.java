package archadia.complexmachines.common;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import universalelectricity.prefab.network.PacketManager;
import archadia.complexmachines.common.block.BlockAlloyFabricator;
import archadia.complexmachines.common.block.BlockModOre;
import archadia.complexmachines.common.block.BlockWireMill;
import archadia.complexmachines.common.helper.ArchLoader;
import archadia.complexmachines.common.helper.recipes.AlloyRecipes;
import archadia.complexmachines.common.helper.recipes.WiremillRecipes;
import archadia.complexmachines.common.item.ItemAlloy;
import archadia.complexmachines.common.item.ItemBase;
import archadia.complexmachines.common.item.ItemIngot;
import archadia.complexmachines.common.proxy.CommonProxy;
import archadia.complexmachines.common.tileentity.TileEntityAlloyFabricator;
import archadia.complexmachines.common.tileentity.TileEntityWireMill;
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
@Mod(modid = "complexmachines", name = "ComplexMachines", version = "0.3.3")
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = {"ComplexMachines"}, packetHandler = PacketManager.class)
public class ComplexMachines {

	public static final String[] languages = new String[] {"en_US", "de_DE"};
	
	@Instance("complexmachines")
	public static ComplexMachines instance;
	
	@SidedProxy(clientSide = "archadia.complexmachines.common.proxy.ClientProxy", serverSide = "archadia.complexmachines.common.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static ComplexMachinesTab tabComplexMachines = new ComplexMachinesTab();
		
	public static ArchLoader loader = new ArchLoader();
	public static final Configuration config = new Configuration(new File(Loader.instance().getConfigDir() + "Modech.cfg"));
	
	public static Block wireMill;
	public static Block alloyFabricator;
	public static Block oreCopper;
	
	public static Item ingotCopper;
	public static Item wiring;
	public static Item C194;
	
	public int BLOCK_ID = 2389;
	public int ITEM_ID = 8932;
	
	public static boolean debug = true;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		wireMill = new BlockWireMill(2389, Material.iron, "wireMill").setHardness(2F);
		alloyFabricator = new BlockAlloyFabricator(2390, Material.iron, "alloyFabricator").setHardness(2F);;
		oreCopper = new BlockModOre(2391, Material.rock, "oreCopper").setHardness(2F);
		
		ingotCopper = new ItemIngot(8931, "ingotCopper");
		C194 = new ItemAlloy(8932, "C194");
		wiring = new ItemBase(8933, "wiring");
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		loader.addBlock(wireMill);
		loader.addBlock(alloyFabricator);
		loader.addBlock(oreCopper);
		loader.loadLangauges("/assets/complexmachines/lang/", languages);
		
		loader.loadBlocks();
		
		GameRegistry.registerTileEntity(TileEntityAlloyFabricator.class, "tileEntityAlloyFabricator");
		GameRegistry.registerTileEntity(TileEntityWireMill.class, "tileEntityWireMill");
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		
		OreDictionary.registerOre("ingotCopper", ingotCopper);
		OreDictionary.registerOre("oreCopper", oreCopper);

		WiremillRecipes.recipes().addWireMillRecipes(C194.itemID, new ItemStack(wiring));
		AlloyRecipes.alloy().addAlloyRecipe(ingotCopper.itemID, Item.ingotIron.itemID, new ItemStack(C194));
	}
}
