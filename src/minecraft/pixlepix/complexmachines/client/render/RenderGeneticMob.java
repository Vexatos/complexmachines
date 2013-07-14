package pixlepix.complexmachines.client.render;

import pixlepix.complexmachines.common.mob.GeneticMob;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderGeneticMob extends RenderLiving {

	
	
	 protected ModelZombie model;
	 
	 public RenderGeneticMob (ModelZombie modelZombie, float f)
	 {
	  super(modelZombie, f);
	  model = ((ModelZombie)mainModel);
	 }
	 
	 public void renderGeneticMob(GeneticMob entity, double par2, double par4, double par6, float par8, float par9)
	    {
	        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
	    }
	 
	 public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	    {
	        renderGeneticMob((GeneticMob)par1EntityLiving, par2, par4, par6, par8, par9);
	    }
	 
	 public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	    {
	        renderGeneticMob((GeneticMob)par1Entity, par2, par4, par6, par8, par9);
	    }

	 static ResourceLocation location=new ResourceLocation("complexmachines","/assets/ComplexMachines/textures/mobs/creeper.png");
	    public static ResourceLocation getTexture()
	    {
	    	return location;
	    } 
	 
	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		// TODO Auto-generated method stub
		return getTexture();
	}
	}


