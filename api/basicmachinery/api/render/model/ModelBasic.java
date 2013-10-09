package basicmachinery.api.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * @author Archadia
 *
 */
public abstract class ModelBasic extends ModelBase {

	@Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
      super.render(entity, f, f1, f2, f3, f4, 0.0625F);
      setRotationAngles(f, f1, f2, f3, f4, 0.0625F, entity);
    }	
    
    public abstract void render(float f);
    
    protected void setRotation(ModelRenderer model, float x, float y, float z)
    {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
    }
}
