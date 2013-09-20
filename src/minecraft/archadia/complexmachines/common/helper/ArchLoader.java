package archadia.complexmachines.common.helper;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * @author Archadia
 *
 */
public class ArchLoader {

	private static ArrayList<Block> blockLibrary = new ArrayList<Block>();
	public static ArrayList<Item> ingotLibrary = new ArrayList<Item>();
		
	public ArchLoader() {
		super();
	}
	
	public void loadLangauges(String path, String[] supported) {
		for(String language : supported) {
			LanguageRegistry.instance().loadLocalization(path + language + ".properties", language, false);
		}
	}
	
	/**
	 * Loads blocks.
	 */
	public void loadBlocks() {
		for(Block block : blockLibrary) {
			GameRegistry.registerBlock(block, getCodeName(block));
		}
	}
		
	/**
	 * Adds a certain block to the Archadia Block Dictionary.
	 * @param block
	 */
	public void addBlock(Block block) {
		blockLibrary.add(block);
	}
	
	/**
	 * Returns the Code Name of a block, as in "excavator".
	 * @param block
	 * @return code name
	 */
	public String getCodeName(Block block) {
		String name = ArchHelper.getUsableNames(block.getUnlocalizedName());
		return name;
	}
}
