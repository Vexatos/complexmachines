package com.archadia.complexmachines.helper.visual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

/**
 * @author Archadia
 *
 */
public class UtilRender {

	public static TextureManager renderEngine() {
	    return Minecraft.getMinecraft().renderEngine;
	}
	
	public static void bindResTexture(ResourceLocation loc) {
		renderEngine().func_110577_a(loc);
	}
	
	public void registerIcon(IconRegister ir) {
		
	}
}
