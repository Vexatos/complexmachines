package com.archadia.complexmachines.core.client.icon;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.archadia.complexmachines.helper.visual.UtilRender;

/**
 * @author Archadia
 *
 */
public class IconWidgets {
	
	  public static final IconWidgets TEST = new IconWidgets(0, 0);

	  private static final int TEXTURE_SIZE = 256;
	  private static final double PIXEL_SIZE = 1d / TEXTURE_SIZE;

	  public final double minU;
	  public final double maxU;
	  public final double minV;
	  public final double maxV;

	  public static final ResourceLocation TEXTURE = new ResourceLocation("complexmachines:textures/gui/widgets.png");

	  public IconWidgets(int x, int y) {
	    this(x, y, 16, 16);
	  }

	  public IconWidgets(int x, int y, int width, int height) {
	    this((float) (PIXEL_SIZE * x), (float) (PIXEL_SIZE * (x + width)), (float) (PIXEL_SIZE * y), (float) (PIXEL_SIZE * (y + height)));
	  }

	  public IconWidgets(double minU, double maxU, double minV, double maxV) {
	    this.minU = minU;
	    this.maxU = maxU;
	    this.minV = minV;
	    this.maxV = maxV;
	  }

	  public void renderIcon(double x, double y) {
	    renderIcon(x, y, 16, 16, 0, false);
	  }

	  public void renderIcon(double x, double y, double width, double height, double zLevel, boolean doDraw) {

	    Tessellator tessellator = Tessellator.instance;
	    if(doDraw) {
	      UtilRender.bindResTexture(TEXTURE);
	      GL11.glColor3f(1, 1, 1);
	      tessellator.startDrawingQuads();
	    }
	    tessellator.addVertexWithUV(x, y + height, zLevel, minU, maxV);
	    tessellator.addVertexWithUV(x + width, y + height, zLevel, maxU, maxV);
	    tessellator.addVertexWithUV(x + width, y + 0, zLevel, maxU, minV);
	    tessellator.addVertexWithUV(x, y + 0, zLevel, minU, minV);
	    if(doDraw) {
	      tessellator.draw();
	    }
	  }

}
