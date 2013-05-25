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
import pixlepix.complexmachines.common.tileentity.ExtractorMachineTileEntity;
import pixlepix.complexmachines.common.tileentity.MotorTileEntity;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class GuiAirship extends GuiContainer
{
    private MotorTileEntity tileEntity;
    
    private int containerWidth;

    private int containerHeight;

	private int direction;
    
    public GuiAirship(InventoryPlayer par1InventoryPlayer, MotorTileEntity tileEntity)
    {
    	super(new ContainerAirship(par1InventoryPlayer, tileEntity));
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
    	buttonList.add(new GuiButton(1, cornerX+20, cornerY+ySize/4, 20, 20, "+"));
        buttonList.add(new GuiButton(2, cornerX+40, cornerY+ySize/4, 20, 20, "-"));
        buttonList.add(new GuiButton(3, cornerX+60, cornerY+ySize/4, 20, 20, "N"));
        buttonList.add(new GuiButton(4, cornerX+80, cornerY+ySize/4, 20, 20, "E"));
        buttonList.add(new GuiButton(5, cornerX+100, cornerY+ySize/4, 20, 20, "S"));
        buttonList.add(new GuiButton(6, cornerX+120, cornerY+ySize/4, 20, 20, "W"));
        buttonList.add(new GuiButton(0, cornerX+140, cornerY+ySize/4, 20, 20, "O"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(GuiAirship.getTexture());
        this.containerWidth = (this.width - this.xSize) / 2;
        this.containerHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
        
        
        
        
    }
    
    void updateServer (String name)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try
        {
            outputStream.writeByte(1);
            outputStream.writeInt(tileEntity.worldObj.getWorldInfo().getDimension());
            outputStream.writeInt(tileEntity.xCoord);
            outputStream.writeInt(tileEntity.yCoord);
            outputStream.writeInt(tileEntity.zCoord);
            outputStream.writeInt(tileEntity.direction);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "Complex Machines";
        packet.data = bos.toByteArray();
        packet.length = bos.size();

        PacketDispatcher.sendPacketToServer(packet);
    }
    
    @Override
    protected void actionPerformed (GuiButton button)
    {
    	
        tileEntity.direction = button.id;
        updateServer("");
       
    }
    
    public static String getTexture()
    {
    	return "/mods/ComplexMachines/textures/gui/Airship.png";
    }
}