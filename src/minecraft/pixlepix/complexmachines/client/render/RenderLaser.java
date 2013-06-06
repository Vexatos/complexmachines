
    	package pixlepix.complexmachines.client.render;

    	import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ForgeDirection;

    	import org.lwjgl.opengl.GL11;

    	import pixlepix.complexmachines.client.model.ExtractorModel;
import pixlepix.complexmachines.client.model.LaserModel;
import pixlepix.complexmachines.common.Config;

    	import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

    	@SideOnly(Side.CLIENT)
    	public class RenderLaser extends TileEntitySpecialRenderer
    	{
    	    private LaserModel model;
    	    
    	    public RenderLaser()
    	    {
    	        this.model = new LaserModel();
    	    }
    	    
    	   
    	    @Override
    	    public void renderTileEntityAt(TileEntity var1, double var2, double var3, double var4, float var5)
    	    {
    	    	

String laserTexture="/mods/ComplexMachines/textures/blocks/LaserBeam.png";
    	int laserId=(var1.worldObj.getBlockId(var1.xCoord, var1.yCoord, var1.zCoord))-Config.blockStartingID;
    	
    	switch(laserId){
    		case 20: 
    			laserTexture="/mods/ComplexMachines/textures/blocks/GlassLaser.png";
    			break;
    		case 19: 
    			laserTexture="/mods/ComplexMachines/textures/blocks/StoneBrick.png";

    			break;
    		case 17: 
    			laserTexture="/mods/ComplexMachines/textures/blocks/SuctionLaser.png";

    			break;
    		case 14: 
    			laserTexture="/mods/ComplexMachines/textures/blocks/RedstoneLaser.png";

    			break;

    		case 13: 
    			laserTexture="/mods/ComplexMachines/textures/blocks/DebuffLaser.png";

    			break;

    		case 21: 
    			laserTexture="/mods/ComplexMachines/textures/blocks/ChargingBeam.png";

    			break;
    		case 16: 
    			laserTexture="/mods/ComplexMachines/textures/blocks/ElectricLaser.png";

    			break;
    		case 12: 
    			laserTexture="/mods/ComplexMachines/textures/blocks/GlassLaser.png";

    			break;

    		case 11: 
    			laserTexture="/mods/ComplexMachines/textures/blocks/HarmingLaser.png";

    			break;
    			
    	}
    	    	

        this.bindTextureByName(laserTexture);
    	        GL11.glPushMatrix();
    	        GL11.glTranslatef((float) var2 + 0.5F, (float) var3 + 1.5F, (float) var4 + 0.5F);
    	       

    	        GL11.glRotatef(270, 0.0F, 1.0F, 0.0F);
    	        
    	        GL11.glScalef(1.0F, -1F, -1F);
    	        ForgeDirection direction=null;
    	        int meta=var1.worldObj.getBlockMetadata(var1.xCoord, var1.yCoord, var1.zCoord);
    	        if(meta<ForgeDirection.VALID_DIRECTIONS.length){
    	        	direction=ForgeDirection.VALID_DIRECTIONS[var1.worldObj.getBlockMetadata(var1.xCoord, var1.yCoord, var1.zCoord)];
    	        }
    	        
    	        if(direction!=null){
	    	        if(meta==4){
	    	        	GL11.glRotatef(90,0F, 1F, 0F);

	    	        	GL11.glTranslated(-0.5, 0.5, 0.5);
	    	        }
	    	        if(meta==5){
	    	        	GL11.glRotatef(270,0F,1F,0F);

	    	        	GL11.glTranslated(0, -1, -1);
	    	        }
	    	        if(direction.offsetZ==1){
	
	    	        	GL11.glTranslated(-0.5, 0.5, 0.5);
	    	        }
	    	        if(direction.offsetZ==-1){
	
	    	        	GL11.glTranslated(-20.5, 0.5, 0.5);
	
	    	        }
    	        }
    	        //GL11.glTranslated();
    	        //GL11.glScaled(20,1,20);
    	        this.model.render();
    	        GL11.glPopMatrix();
    	        
    	    }
    	    
    	}