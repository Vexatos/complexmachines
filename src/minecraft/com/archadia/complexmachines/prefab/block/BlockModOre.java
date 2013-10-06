package com.archadia.complexmachines.prefab.block;

import basicmachinery.api.BlockBase;

import com.archadia.complexmachines.core.common.ComplexMachines;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author Archadia
 *
 */
public class BlockModOre extends BlockBase {

	public BlockModOre(int id, Material material, String name) {
		super(id, material, name, ComplexMachines.tabComplexMachines);
		setUnlocalizedName(name);
	}
}
