package com.archadia.complexmachines.api;

import java.util.ArrayList;

import com.archadia.complexmachines.core.common.tileentity.TileEntityExtractor;

/**
 * @author Archadia
 *
 */
public class ExtractorHelper {

	private static ExtractorHelper base = new ExtractorHelper();
	
	public ArrayList<Integer> validOre = new ArrayList<Integer>();
	
	public static ExtractorHelper instance() {
		return base;
	}
	
	public void addExtractorValidOre(int blockID) {
		validOre.add(blockID);
	}		
	
	public ArrayList<Integer> getValidOres() {
		return validOre;
	}
}
