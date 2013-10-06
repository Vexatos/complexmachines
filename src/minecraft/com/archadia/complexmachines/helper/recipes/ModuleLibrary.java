package com.archadia.complexmachines.helper.recipes;

import java.util.HashMap;

/**
 * @author Archadia
 *
 */
public class ModuleLibrary {

	public void initDesc() {
		moduleDesc[0] = "";
		moduleDesc[1] = "";
		moduleDesc[2] = "";
	}
	
	private static String[] moduleDesc = new String[2];
	
	public enum ModuleTypes {
		SPEEDMODULE(moduleDesc[0]),
		UPGRADEMODULE(moduleDesc[1]),
		OUTPUTMODULE(moduleDesc[2]);
		
		String[] descriptions;
		
		ModuleTypes(String desc) {
			for(int i = 0; i < moduleDesc.length; i++) {
				descriptions[i] = desc;
			}
		}
	}
}
