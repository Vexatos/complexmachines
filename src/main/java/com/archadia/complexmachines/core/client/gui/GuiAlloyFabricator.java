package com.archadia.complexmachines.core.client.gui;

import com.archadia.complexmachines.core.common.container.ContainerAlloyFabricator;
import com.archadia.complexmachines.core.common.tileentity.TileEntityAlloyFabricator;
import com.archadia.complexmachines.helper.ArchHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * @author Archadia
 */
public class GuiAlloyFabricator extends GuiContainer {

  private TileEntityAlloyFabricator tileINV;
  private ArchHelper helper = new ArchHelper();

  public GuiAlloyFabricator(InventoryPlayer par1InventoryPlayer, TileEntityAlloyFabricator tile) {
    super(new ContainerAlloyFabricator(par1InventoryPlayer, tile));
  }

  protected void drawGuiContainerForegroundLayer(int par1, int par2) {

  }

  protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    this.mc.getTextureManager().bindTexture(new ResourceLocation("complexmachines", "textures/gui/alloyfab.png"));
    int k = (this.width - this.xSize) / 2;
    int l = (this.height - this.ySize) / 2;
    this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
  }
}
