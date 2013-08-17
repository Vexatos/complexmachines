package pixlepix.complexmachines.client.render;

import org.lwjgl.opengl.GL11;

import pixlepix.complexmachines.client.model.ExtractorModel;
import pixlepix.complexmachines.client.model.FillerModel;
import pixlepix.complexmachines.client.model.FocalPointModel;
import pixlepix.complexmachines.client.model.LaserEmitterModel;
import pixlepix.complexmachines.client.model.OceanGeneratorModel;
import pixlepix.complexmachines.common.ComplexMachines;
import pixlepix.complexmachines.common.Config;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderHandler implements ISimpleBlockRenderingHandler {


	public ExtractorModel extractor=new ExtractorModel();
	public FillerModel filler=new FillerModel();
	public FocalPointModel focalPoint=new FocalPointModel();
	public LaserEmitterModel laser=new LaserEmitterModel();
	public OceanGeneratorModel oceanGenerator=new OceanGeneratorModel();
	static ResourceLocation location=new ResourceLocation("complexmachines","textures/models/Extractor.png");
    public static ResourceLocation getTexture()
    {
    	return location;
    } 
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
GL11.glPushMatrix();
        
	//WHat the hell kind of code is this
       /* if (block.blockID == Config.blockStartingID+2){
            GL11.glBindTexture(3553,FMLClientHandler.instance().getClient().renderEngine.getTexture("/assets/ComplexMachines/textures/models/Extractor.png"));
            GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.5F, .8F, 0.5F);
            GL11.glScalef(1F, -1F, -1F);
            this.extractor.render(null, 0, 0, 0, 0, 0, 0.0625F);
            GL11.glPopMatrix();
        }
*/
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
