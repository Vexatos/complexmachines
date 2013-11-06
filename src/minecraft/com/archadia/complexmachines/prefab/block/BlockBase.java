package com.archadia.complexmachines.prefab.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

/**
 * @author Archadia
 *
 */
public class BlockBase extends Block {

    protected Icon[] icons = new Icon[0];
	protected String iconPath;
	
	public BlockBase(int id, Material material, String name) {
		super(id, material);
		setUnlocalizedName(name);
		iconPath = name;
	}
	
	@Override
	public void registerIcons(IconRegister ir) {
		this.blockIcon = ir.registerIcon("complexmachines:" + iconPath);
	}
	
	 protected void setIconMax(int max) {
		 icons = new Icon[max];
	 }
}
