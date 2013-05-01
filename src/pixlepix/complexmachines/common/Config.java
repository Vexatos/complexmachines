package pixlepix.complexmachines.common;

import net.minecraft.common.Property;
import net.minecraft.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config{
	// Base Config
	public static boolean worldGen = false;
	public static boolean vanillaRecipies = false;
	// ID Config
	public static int blockStartingID = 2770;
	public static int itemStartingID = 11670;
	// General Block/Item Config
	public static int spawnProtectionRadius = 0;
	public static int singlePointRadius = 5000;
	public static Configuration config;
	// Generator Outputs (in Joules/Tick)
	public static float oceanGeneratorOutput = 0.5;
	public static float grinderOutput = 5000;

	public static void configure(FMLPreInitializationEvent event){
		config = new Configuration(
			event.getSuggestedConfigurationFile());
		config.load();

		worldGen = getGeneralConfig("Focal Point Generation", worldGen).getBoolean(true);
		vanillaRecipes = getGeneralConfig("Vanilla (Easy) Recipies Enabled", vanillaRecipes).getBoolean(true);

		blockStartingID = getGeneralConfig("BlockStartingID", blockStartingID).getInt();
		itemStartingID = getGeneralConfig("ItemStartingID", itemStartingID).getInt();

		spawnProtectionRadius = getGeneralConfig("Spawn Protection Radius", spawnProtectionRadius).getInt();
		singlePointRadius = getGeneralConfig("Single Point Generator Radius", singlePointRadius).getInt();

		oceanGeneratorOutput = getGeneralConfig("Ocean Generator Output", oceanGeneratorOutput).getFloat();
		grinderOutput = getGeneralConfig("Grinder Output", grinderOutput).getFloat();

		config.save();
	}

	private static Property getGeneralConfig(String configName, int defaultValue){
		return config.get(Configuration.CATEGORY_GENERAL, configName, defaultValue);
	}

	private static Property getGeneralConfig(String configName, float defaultValue){
		return config.get(Configuration.CATEGORY_GENERAL, configName, defaultValue);
	}

	private static Property getGeneralConfig(String configName, boolean defaultValue){
		return config.get(Configuration.CATEGORY_GENERAL, configName, defaultValue);
	}
}
