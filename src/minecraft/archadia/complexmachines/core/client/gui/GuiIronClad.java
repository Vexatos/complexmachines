package archadia.complexmachines.core.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import archadia.complexmachines.core.common.container.ContainerGrinder;
import archadia.complexmachines.core.common.container.ContainerIronClad;
import archadia.complexmachines.core.common.tileentity.TileEntityGrinder;
import archadia.complexmachines.core.common.tileentity.TileEntityIronClad;
import archadia.complexmachines.helper.ArchHelper;

/**
 * @author Archadia
 *
 */
public class GuiIronClad extends GuiContainer {
	
    private static TileEntityIronClad tileINV = new TileEntityIronClad();
    private ArchHelper helper = new ArchHelper();

    public GuiIronClad(InventoryPlayer par1InventoryPlayer, TileEntityIronClad tile) {
        super(new ContainerIronClad(par1InventoryPlayer, tile));
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String s = this.tileINV.isInvNameLocalized() ? this.tileINV.getInvName() : I18n.func_135053_a(this.tileINV.getInvName());
        this.fontRenderer.drawString(s, this.xSize / 2 - 81, 72, 4210752);
    }
 
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.func_110434_K().func_110577_a(new ResourceLocation("complexmachines","textures/gui/ironclad.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        i1 = TileEntityGrinder.instance().getProcessProgressScaled(24);
        this.drawTexturedModalRect(k + 112, l + 48, 176, 0, i1 + 1, 6);
    }
}