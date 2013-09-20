package archadia.complexmachines.common.helper;

import net.minecraft.block.Block;

/**
 * @author Archadia
 *
 */
public class ArchHelper {
	
	/**
	 * Input unlocalized name, and it turns it into a name the loader can use.
	 * @param unloc
	 * @return
	 */
	public static String getUsableNames(String unloc) {
		if(unloc.contains("tile.")) {
			String replaced = unloc.replace("tile.", "");
			return replaced;
		}
		if(unloc.contains("item.")) {
			String replaced = unloc.replace("item.", "");
			return replaced;
		}
		return null;
	}
}
