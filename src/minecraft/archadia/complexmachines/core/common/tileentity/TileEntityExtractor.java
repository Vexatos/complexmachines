package archadia.complexmachines.core.common.tileentity;

import java.util.ArrayList;

import net.minecraft.block.Block;
import archadia.complexmachines.prefab.tileentity.TileEntityAdvancedMachine;

/**
 * @author Archadia
 *
 */
public class TileEntityExtractor extends TileEntityAdvancedMachine {
	
	private final static TileEntityExtractor tileEntityBase = new TileEntityExtractor();   
 	
	private static ArrayList<Integer> validOre = new ArrayList<Integer>();
	
	public void addExtractorValidOre(int blockID) {
		validOre.add(blockID);
	}
	
	public void addExtractorVanillaOre() {
		addExtractorValidOre(Block.oreCoal.blockID);
		addExtractorValidOre(Block.oreIron.blockID);
		addExtractorValidOre(Block.oreGold.blockID);
		addExtractorValidOre(Block.oreRedstone.blockID);
		addExtractorValidOre(Block.oreLapis.blockID);
	}
	
	public final static TileEntityExtractor instance() {
		return tileEntityBase;
	}
	 
	public TileEntityExtractor() {
		setInventorySize(0);
		setMaxTicks(200);
	}
	
	public void updateEntity() {
		
	}
	
	public void findOre() {
		
	}
	
	public boolean isOre(int id) {
		for(int oreID : validOre) {
			if(id == oreID) {
				return true;
			}
		}
		return false;
	}
	
	public String getInvName() {
		return "Extractor";
	}

}
