package archadia.complexmachines.common.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import archadia.complexmachines.common.container.ContainerWireMill;
import archadia.complexmachines.common.helper.ArchHelper;
import archadia.complexmachines.common.tileentity.TileEntityWireMill;

/**
 * @author Archadia
 *
 */
public class GuiWireMill extends GuiContainer {
	
    private TileEntityWireMill tileINV;
    private ArchHelper helper = new ArchHelper();

    public GuiWireMill(InventoryPlayer par1InventoryPlayer, TileEntityWireMill tile) {
        super(new ContainerWireMill(par1InventoryPlayer, tile));
        this.tileINV = tile;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String s = this.tileINV.isInvNameLocalized() ? this.tileINV.getInvName() : I18n.func_135053_a(this.tileINV.getInvName());
        this.fontRenderer.drawString(s, this.xSize / 2 + 44, 7, 4210752);
        this.fontRenderer.drawString(""+tileINV.processTime, this.xSize / 2 + 65, 72, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.func_110434_K().func_110577_a(new ResourceLocation("complexmachines","textures/gui/wiremill.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        i1 = tileINV.getProcessProgressScaled(24);
        this.drawTexturedModalRect(k + 77, l + 43, 176, 0, i1, 16);
    }
}