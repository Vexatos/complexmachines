package com.archadia.complexmachines.prefab.block;

import net.minecraft.block.material.Material;
import basicmachinery.api.block.BlockBase;

import com.archadia.complexmachines.core.common.ComplexMachines;

/**
 * @author Archadia
 *
 */
public class BlockModOre extends BlockBase {

	public BlockModOre(int id, Material material, String name) {
		super(id, material, name);
		setUnlocalizedName(name);
	}
}
