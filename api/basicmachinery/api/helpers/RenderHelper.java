package basicmachinery.api.helpers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import basicmachinery.api.render.HandRenderer;
import basicmachinery.api.render.model.ModelBasic;
import cpw.mods.fml.client.registry.ClientRegistry;

/**
 * @author Archadia
 *
 */
public class RenderHelper extends TileEntitySpecialRenderer  {
	
	public static void bindTileEntity(Class<?extends TileEntity> tile, ModelBasic model, String domain, String path) {
		ClientRegistry.bindTileEntitySpecialRenderer(tile, new RenderHelper(model, domain, path));
	}
	
	public static void registerItemRenderer(int itemid, TileEntity tile) {
		MinecraftForgeClient.registerItemRenderer(itemid, new HandRenderer(tile));
	}
	
	private ModelBasic model;
	private String domain;
	private String path;
	
	public RenderHelper(ModelBasic model, String domain, String path) {
		this.model = model;
		this.domain = domain;
		this.path = path;
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
		//func_110628_a(new ResourceLocation(this.domain, this.path));
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		
		this.model.render(0.0625F);
		GL11.glPopMatrix();
	}
}
