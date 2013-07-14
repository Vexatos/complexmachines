package pixlepix.complexmachines.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import pixlepix.complexmachines.common.container.ContainerExtractor;
import pixlepix.complexmachines.common.container.ContainerGrinder;
import pixlepix.complexmachines.common.tileentity.ExtractorMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.GrinderTileEntity;

import universalelectricity.core.electricity.ElectricityDisplay;
import universalelectricity.core.electricity.ElectricityDisplay.ElectricUnit;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class GuiExtractor extends GuiContainer
{
    private ExtractorMachineTileEntity tileEntity;
    
    private int containerWidth;
    private int containerHeight;
    
    public GuiExtractor(InventoryPlayer par1InventoryPlayer, ExtractorMachineTileEntity tileEntity)
    {
        super(new ContainerExtractor(par1InventoryPlayer, tileEntity));
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
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.func_110577_a(getTexture());
        this.containerWidth = (this.width - this.xSize) / 2;
        this.containerHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
        
        
        
        
    }
    
    static ResourceLocation location=new ResourceLocation("complexmachines","/textures/gui/Extractor.png");
    public static ResourceLocation getTexture()
    {
    	return location;
    }
}