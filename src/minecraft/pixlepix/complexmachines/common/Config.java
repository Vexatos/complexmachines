package pixlepix.complexmachines.common;

import net.minecraftforge.common.Property;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config{
	// Base Config
	public static boolean vanillaRecipies = false;
	// ID Config
	public static int blockStartingID = 2770;
	public static int itemStartingID = 11670;
	// General Block/Item Config
	public static int spawnProtectionRadius = 0;
	public static int singlePointRadius = 5000;
	public static Configuration config;
	// Generator Outputs (in Joules/Tick)
	public static double oceanGeneratorOutput = 0.5;
	public static double singlePointGeneratorOutput = 500000;
	public static double grinderOutput = 5000;
	public static double focalPointOutput = 3000;

	public static void configure(FMLPreInitializationEvent event){
		config = new Configuration(
			event.getSuggestedConfigurationFile());
		config.load();

		vanillaRecipies = getGeneralConfig("Vanilla (Easy) Recipies Enabled", vanillaRecipies).getBoolean(true);

		blockStartingID = getGeneralConfig("BlockStartingID", blockStartingID).getInt();
		itemStartingID = getGeneralConfig("ItemStartingID", itemStartingID).getInt();

		spawnProtectionRadius = getGeneralConfig("Spawn Protection Radius", spawnProtectionRadius).getInt();
		singlePointRadius = getGeneralConfig("Single Point Generator Radius", singlePointRadius).getInt();

		oceanGeneratorOutput = getGeneralConfig("Ocean Generator Output", oceanGeneratorOutput).getDouble(oceanGeneratorOutput);
		singlePointGeneratorOutput = getGeneralConfig("Single Point Generator Output", singlePointGeneratorOutput).getDouble(singlePointGeneratorOutput);
		grinderOutput = getGeneralConfig("Grinder Output", grinderOutput).getDouble(grinderOutput);
		focalPointOutput = getGeneralConfig("Focal Point Output", focalPointOutput).getDouble(focalPointOutput);

		config.save();
	}

	private static Property getGeneralConfig(String configName, int defaultValue){
		return config.get(Configuration.CATEGORY_GENERAL, configName, defaultValue);
	}

	private static Property getGeneralConfig(String configName, double defaultValue){
		return config.get(Configuration.CATEGORY_GENERAL, configName, defaultValue);
	}

	private static Property getGeneralConfig(String configName, boolean defaultValue){
		return config.get(Configuration.CATEGORY_GENERAL, configName, defaultValue);
	}
}
