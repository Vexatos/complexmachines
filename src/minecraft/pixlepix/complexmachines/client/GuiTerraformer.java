package pixlepix.complexmachines.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;

import org.lwjgl.opengl.GL11;

import pixlepix.complexmachines.common.container.ContainerAirship;
import pixlepix.complexmachines.common.container.ContainerExtractor;
import pixlepix.complexmachines.common.container.ContainerTerraformer;
import pixlepix.complexmachines.common.tileentity.ExtractorMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.MotorTileEntity;
import pixlepix.complexmachines.common.tileentity.TerraformerTileEntity;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class GuiTerraformer extends GuiContainer
{
    private TerraformerTileEntity tileEntity;
    
    private int containerWidth;

    private int containerHeight;

	private int direction;
    
    public GuiTerraformer(InventoryPlayer par1InventoryPlayer, TerraformerTileEntity tileEntity)
    {
    	super(new ContainerTerraformer(par1InventoryPlayer, tileEntity));
        this.tileEntity = tileEntity;
    }
    
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of
     * the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String displayText = "";
        
        
        
    }
    
    /**
     * Draw the background layer for the GuiContainer (everything behind the
     * items)
     */
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {

        int cornerX = (this.width - this.xSize) / 2;
        int cornerY = (this.height - this.ySize) / 2;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(GuiTerraformer.getTexture());
        this.containerWidth = (this.width - this.xSize) / 2;
        this.containerHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
        
        
        this.fontRenderer.drawString("x+", 25, 4, 4210752);
        this.fontRenderer.drawString("x-", 25, 22, 4210752);
        this.fontRenderer.drawString("z+", 25, 40, 4210752);
        this.fontRenderer.drawString("z-", 25, 58, 4210752);

        this.fontRenderer.drawString("y+", 65, 4, 4210752);

        this.fontRenderer.drawString("y-", 65, 58, 4210752);
        
        
        
    }
    
   
    
   
    
    public static String getTexture()
    {
    	return "/mods/ComplexMachines/textures/gui/Terraformer.png";
    }
}