package com.archadia.complexmachines.core.common;

import com.archadia.complexmachines.core.common.block.*;
import com.archadia.complexmachines.core.common.gen.OreGenerator;
import com.archadia.complexmachines.core.common.item.ItemFoodBase;
import com.archadia.complexmachines.core.common.item.ItemIngot;
import com.archadia.complexmachines.core.common.proxy.CommonProxy;
import com.archadia.complexmachines.core.common.tileentity.*;
import com.archadia.complexmachines.helper.ArchLoader;
import com.archadia.complexmachines.helper.recipes.MachineRecipes;
import com.archadia.complexmachines.prefab.block.BlockModOre;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

import java.io.File;

/**
 * @author Archadia
 *         Originally by Pixlepix, renamed to Modech, now owned by Archadia(Me)
 */
@Mod(modid = ComplexMachines.MOD_ID, name = ComplexMachines.NAME, version = ComplexMachines.VERSION)
public class ComplexMachines {

  public static final String[] languages = new String[] { "en_US", "de_DE", "ru_RU", "pt_BR" };

  public static final String MOD_ID = "complexmachines";
  public static final String NAME = "Complex Machines";
  public static final String VERSION = "0.1.0";

  @Instance(ComplexMachines.MOD_ID)
  public static ComplexMachines instance;

  @SidedProxy(clientSide = "com.archadia.complexmachines.core.common.proxy.ClientProxy", serverSide = "com.archadia.complexmachines.core.common.proxy.CommonProxy")
  public static CommonProxy proxy;

  public static ArchLoader loader = new ArchLoader();
  public static final Configuration config = new Configuration(new File(Loader.instance().getConfigDir() + "/ComplexMachines.cfg"));

  public static boolean oldExtractorMode = false;

  public static ComplexTab tab = new ComplexTab();

  public static Block wireMill;
  public static Block alloyFabricator;
  public static Block oreCopper;
  public static Block oreTin;
  public static Block grinder;
  public static Block extractor;
  public static Block cookieMaker;
  public static Block ironClad;

  public static Item ingotCopper;
  public static Item ingotTin;
  public static Item wiring1;
  public static Item wiring2;
  public static Item wiring3;
  public static Item C194;
  public static Item russianCandy;
  public static Item polishCandy;

  public int BLOCK_ID = 2389;
  public int ITEM_ID = 8932;

  public static boolean debug = true;
  public static int extractorPickDegradeRate = 10;

  @EventHandler
  public void PreInit(FMLPreInitializationEvent event) {
    oreCopper = new BlockModOre(Material.rock, "oreCopper").setHardness(2F);
    oreTin = new BlockModOre(Material.rock, "oreTin").setHardness(2F);
    wireMill = new BlockWireMill(Material.iron, "wireMill").setHardness(2F);
    alloyFabricator = new BlockAlloyFabricator(Material.iron, "alloyFabricator").setHardness(2F);
    ;
    grinder = new BlockGrinder(Material.iron, "grinder").setHardness(2F);
    extractor = new BlockExtractor(Material.iron, "extractor").setHardness(2F);
    cookieMaker = new BlockCookieMaker(Material.iron, "cookieMaker").setHardness(2F);
    ironClad = new BlockIronClad(Material.iron, "ironClad").setHardness(2F);

    ingotTin = new ItemIngot("ingotTin");
    ingotCopper = new ItemIngot("ingotCopper");
    C194 = new ItemIngot("C194");
    wiring1 = new ItemIngot("c194-wiring");
    wiring2 = new ItemIngot("tin-wiring");
    wiring3 = new ItemIngot("gold-wiring");
    russianCandy = new ItemFoodBase(5, 5, false, "ruCandy");
    polishCandy = new ItemFoodBase(5, 5, false, "poCandy");

    config.load();

    oldExtractorMode = config.get(Configuration.CATEGORY_GENERAL, "Old Extractor Mode", false).getBoolean(false);
    extractorPickDegradeRate = config.get(Configuration.CATEGORY_GENERAL, "Extractor Pickaxe Degrading Rate", 6).getInt();

    config.save();
  }

  @EventHandler
  public void Init(FMLInitializationEvent event) {
    loader.addBlock(wireMill);
    loader.addBlock(oreTin);
    loader.addBlock(alloyFabricator);
    loader.addBlock(grinder);

    loader.addBlock(oreCopper);
    loader.addBlock(extractor);
    loader.addBlock(cookieMaker);
    loader.addBlock(ironClad);
    //loader.loadLanguages("/assets/complexmachines/lang/", languages);

    loader.loadBlocks();

    GameRegistry.registerTileEntity(TileEntityAlloyFabricator.class, "tileEntityAlloyFabricator");
    GameRegistry.registerTileEntity(TileEntityGrinder.class, "tileEntityGrinder");
    GameRegistry.registerTileEntity(TileEntityWireMill.class, "tileEntityWireMill");
    GameRegistry.registerTileEntity(TileEntityExtractor.class, "tileEntityExtractor");
    GameRegistry.registerTileEntity(TileEntityCookieMaker.class, "tileEntityCookieMaker");
    GameRegistry.registerTileEntity(TileEntityIronClad.class, "tileEntityIron");
    NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

    OreDictionary.registerOre("ingotCopper", ingotCopper);
    OreDictionary.registerOre("oreCopper", oreCopper);
    OreDictionary.registerOre("ingotTin", ingotTin);
    OreDictionary.registerOre("oreTin", oreTin);

    GameRegistry.addSmelting(oreCopper, new ItemStack(ingotCopper), 5);
    GameRegistry.addSmelting(oreTin, new ItemStack(ingotTin), 5);

    MachineRecipes.instance().addWireMillRecipes(new ItemStack(Items.gold_ingot), new ItemStack(wiring3));
    MachineRecipes.instance().addWireMillRecipes(new ItemStack(ingotTin), new ItemStack(wiring2));
    MachineRecipes.instance().addWireMillRecipes(new ItemStack(C194), new ItemStack(wiring1));

    GameRegistry.registerWorldGenerator(new OreGenerator(), 1);
  }
}
