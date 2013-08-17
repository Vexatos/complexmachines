package pixlepix.complexmachines.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import pixlepix.complexmachines.common.container.ContainerTerraformer;
import pixlepix.complexmachines.common.tileentity.TerraformerTileEntity;
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

        this.fontRenderer.drawString("x+", 25, 4, 4210752);
        this.fontRenderer.drawString("x-", 25, 22, 4210752);
        this.fontRenderer.drawString("z+", 25, 40, 4210752);
        this.fontRenderer.drawString("z-", 25, 58, 4210752);

        this.fontRenderer.drawString("y+", 65, 4, 4210752);

        this.fontRenderer.drawString("y-", 65, 58, 4210752);

        this.fontRenderer.drawString(Math.floor(this.tileEntity.getNeededJoules()/1000)+"KJ", 65, 30, 4210752);
      
        
        
        
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
        this.mc.renderEngine.func_110577_a(getTexture());
        this.containerWidth = (this.width - this.xSize) / 2;
        this.containerHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
        
        
        
        
        
    }
    
   
    
   
    static ResourceLocation location=new ResourceLocation("complexmachines","textures/gui/Terraformer.png");
    public static ResourceLocation getTexture()
    {
    	return location;
    }
}