// Date: 05/05/2013 17:25:52
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package pixlepix.complexmachines.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SinglePointGeneratorModel extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Body;
    ModelRenderer LegFrontRight;
    ModelRenderer LegFrontLeft;
    ModelRenderer LegBackRight;
    ModelRenderer LegBackLeft;
    ModelRenderer CubeMiddle;
    ModelRenderer CubeWidth;
    ModelRenderer CubeDepth;
    ModelRenderer CubeHeight;
    ModelRenderer OutputPanel;
    ModelRenderer BaseRim;
  
  public SinglePointGeneratorModel()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(0F, 0F, 0F, 14, 2, 14);
      Base.setRotationPoint(-7F, 22F, -7F);
      Base.setTextureSize(128, 128);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 57, 0);
      Body.addBox(0F, 0F, 0F, 9, 11, 9);
      Body.setRotationPoint(-4.5F, 11F, -4.5F);
      Body.setTextureSize(128, 128);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      LegFrontRight = new ModelRenderer(this, 25, 32);
      LegFrontRight.addBox(0F, 0F, 0F, 3, 7, 3);
      LegFrontRight.setRotationPoint(1F, 4F, -4F);
      LegFrontRight.setTextureSize(128, 128);
      LegFrontRight.mirror = true;
      setRotation(LegFrontRight, 0F, 0F, 0F);
      LegFrontLeft = new ModelRenderer(this, 25, 32);
      LegFrontLeft.addBox(0F, 0F, 0F, 3, 7, 3);
      LegFrontLeft.setRotationPoint(-4F, 4F, -4F);
      LegFrontLeft.setTextureSize(128, 128);
      LegFrontLeft.mirror = true;
      setRotation(LegFrontLeft, 0F, 0F, 0F);
      LegBackRight = new ModelRenderer(this, 25, 32);
      LegBackRight.addBox(0F, 0F, 0F, 3, 7, 3);
      LegBackRight.setRotationPoint(1F, 4F, 1F);
      LegBackRight.setTextureSize(128, 128);
      LegBackRight.mirror = true;
      setRotation(LegBackRight, 0F, 0F, 0F);
      LegBackLeft = new ModelRenderer(this, 25, 32);
      LegBackLeft.addBox(0F, 0F, 0F, 3, 7, 3);
      LegBackLeft.setRotationPoint(-4F, 4F, 1F);
      LegBackLeft.setTextureSize(128, 128);
      LegBackLeft.mirror = true;
      setRotation(LegBackLeft, 0F, 0F, 0F);
      CubeMiddle = new ModelRenderer(this, 49, 21);
      CubeMiddle.addBox(0F, 0F, 0F, 5, 5, 5);
      CubeMiddle.setRotationPoint(-2.5F, -3F, -2.5F);
      CubeMiddle.setTextureSize(128, 128);
      CubeMiddle.mirror = true;
      setRotation(CubeMiddle, 0F, 0F, 0F);
      CubeWidth = new ModelRenderer(this, 49, 32);
      CubeWidth.addBox(0F, 0F, 0F, 6, 4, 4);
      CubeWidth.setRotationPoint(-3F, -2.5F, -2F);
      CubeWidth.setTextureSize(128, 128);
      CubeWidth.mirror = true;
      setRotation(CubeWidth, 0F, 0F, 0F);
      CubeDepth = new ModelRenderer(this, 70, 32);
      CubeDepth.addBox(0F, 0F, 0F, 4, 4, 6);
      CubeDepth.setRotationPoint(-2F, -2.5F, -3F);
      CubeDepth.setTextureSize(128, 128);
      CubeDepth.mirror = true;
      setRotation(CubeDepth, 0F, 0F, 0F);
      CubeHeight = new ModelRenderer(this, 70, 21);
      CubeHeight.addBox(0F, 0F, 0F, 4, 6, 4);
      CubeHeight.setRotationPoint(-2F, -3.5F, -2F);
      CubeHeight.setTextureSize(128, 128);
      CubeHeight.mirror = true;
      setRotation(CubeHeight, 0F, 0F, 0F);
      OutputPanel = new ModelRenderer(this, 0, 32);
      OutputPanel.addBox(0F, 0F, 0F, 8, 11, 4);
      OutputPanel.setRotationPoint(-4F, 13F, -8F);
      OutputPanel.setTextureSize(128, 128);
      OutputPanel.mirror = true;
      setRotation(OutputPanel, 0F, 0F, 0F);
      BaseRim = new ModelRenderer(this, 0, 17);
      BaseRim.addBox(0F, 0F, 0F, 12, 2, 12);
      BaseRim.setRotationPoint(-6F, 21F, -6F);
      BaseRim.setTextureSize(128, 128);
      BaseRim.mirror = true;
      setRotation(BaseRim, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Base.render(f5);
    Body.render(f5);
    LegFrontRight.render(f5);
    LegFrontLeft.render(f5);
    LegBackRight.render(f5);
    LegBackLeft.render(f5);
    CubeMiddle.render(f5);
    CubeWidth.render(f5);
    CubeDepth.render(f5);
    CubeHeight.render(f5);
    OutputPanel.render(f5);
    BaseRim.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
