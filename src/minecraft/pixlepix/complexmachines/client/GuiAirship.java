package pixlepix.complexmachines.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.network.packet.Packet250CustomPayload;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import pixlepix.complexmachines.common.tileentity.MotorTileEntity;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAirship extends GuiScreen
{
    GuiTextField text;
    String toolName;
    int guiType;
    int[] slotX, slotY, iconX, iconY;
    boolean active;
    String title, body = "";
	private int xSize;
	public MotorTileEntity tileEntity;
	private int ySize;
	public int direction;
    public GuiAirship(MotorTileEntity entity)
    {
    	this.tileEntity=entity;
        text = new GuiTextField(this.fontRenderer, this.xSize / 2 - 5, 8, 30, 12);
        this.text.setMaxStringLength(40);
        this.text.setEnableBackgroundDrawing(false);
        this.text.setVisible(true);
        this.text.setCanLoseFocus(false);
        this.text.setFocused(true);
        this.text.setTextColor(0xffffff);
        toolName = "";
        resetGui();
        Keyboard.enableRepeatEvents(true);
    }
    
    protected void mouseClicked (int mouseX, int mouseY, int mouseButton)
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (mouseButton == 0)
        {
        	
        }
    }

    void resetGui ()
    {
        this.text.setText("");
        guiType = 0;
        iconX = new int[] { 0, 1, 2 };
        iconY = new int[] { 13, 13, 13 };
    }

    @SuppressWarnings("unchecked")
	public void initGui ()
    {
        super.initGui();
        int cornerX = (this.width - this.xSize) / 2;
        int cornerY = (this.height - this.ySize) / 2;

        buttonList.add(new GuiButton(1, 100, 100, 20, 20, "+"));
        buttonList.add(new GuiButton(2, 125, 100, 20, 20, "-"));
        buttonList.add(new GuiButton(3, 150, 100, 20, 20, "N"));
        buttonList.add(new GuiButton(4, 175, 100, 20, 20, "E"));
        buttonList.add(new GuiButton(5, 200, 100, 20, 20, "S"));
        buttonList.add(new GuiButton(6, 225, 100, 20, 20, "W"));
        buttonList.add(new GuiButton(0, 250, 100, 20, 20, "O"));
        this.buttonList.clear();
}
    

    protected void actionPerformed (GuiButton button)
    {
        ((GuiButton) this.buttonList.get(guiType)).enabled = true;
        guiType = button.id;

        direction=button.id;
    }


    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
   

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer (float par1, int par2, int par3)
    {
        // Draw the background
        //int texID = this.mc.renderEngine.getTexture("/mods/tinker/textures/gui/toolstation.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/ComplexMachines/textures/gui/Airship.png");
        int cornerX = (this.width - this.xSize) / 2;
        int cornerY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(cornerX, cornerY, 0, 0, this.xSize, this.ySize);
        
        if (active)
        {
            this.drawTexturedModalRect(cornerX+62, cornerY, 0, this.ySize, 112, 22);
        }

        //texID = this.mc.renderEngine.getTexture("/mods/tinker/textures/gui/icons.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //this.mc.renderEngine.bindTexture(texID);

        this.mc.renderEngine.bindTexture("/mods/ComplexMachines/textures/gui/Airship.png");
        // Draw the slots

        

        // Draw description
        //texID = this.mc.renderEngine.getTexture("/mods/tinker/textures/gui/description.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //this.mc.renderEngine.bindTexture(texID);
        this.mc.renderEngine.bindTexture("/mods/tinker/textures/gui/description.png");
        cornerX = (this.width + this.xSize) / 2;
        cornerY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(cornerX, cornerY, 0, 0, 126, this.ySize + 30);
        
    }

    /*protected void keyTyped (char par1, int keyCode)
    {
        if (keyCode == 1 || (!active && keyCode == this.mc.gameSettings.keyBindInventory.keyCode))
        {
            logic.setToolname("");
            updateServer("");
            Keyboard.enableRepeatEvents(false);
            this.mc.thePlayer.closeScreen();
        }
        else if (active)
        {
            text.textboxKeyTyped(par1, keyCode);
            toolName = text.getText().trim();
            logic.setToolname(toolName);
            updateServer(toolName);
        }
    }
    */
    void updateServer (String name)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try
        {
            outputStream.writeByte(1);
            outputStream.writeInt(direction);
            outputStream.writeUTF(name);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "ComplexMachines";
        packet.data = bos.toByteArray();
        packet.length = bos.size();

        PacketDispatcher.sendPacketToServer(packet);
    }

    /*protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        text.mouseClicked(par1, par2, par3);
    }*/
}