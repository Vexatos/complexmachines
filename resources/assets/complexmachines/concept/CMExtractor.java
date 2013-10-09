package net.minecraft.src;

public class ModelCMExtractor extends ModelBase
{
    ModelRenderer Drum;
    ModelRenderer BackTop;
    ModelRenderer Axil;
    ModelRenderer FrontTop;
    ModelRenderer Cover;
    ModelRenderer Shell1;
    ModelRenderer Shell2;
    ModelRenderer Shell3;
    ModelRenderer Shell4;
    ModelRenderer Shell5;
    ModelRenderer Shell6;
    ModelRenderer Shell7;
    ModelRenderer BackPistonAxil;
    ModelRenderer FrontPistonAxil;
    ModelRenderer PistonBaseFrnt;
    ModelRenderer PistonBaseBack;
    ModelRenderer Rod1;
    ModelRenderer Rod2;
    ModelRenderer Rod3;
    ModelRenderer Rod4;
    ModelRenderer RodBase;
    ModelRenderer PistonBackHead;
    ModelRenderer PistonFrontHead;
    ModelRenderer CoverSheilding;
  
  public ModelCMExtractor()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      Drum = new ModelRenderer(this, 49, 0);
      Drum.addBox(0F, 0F, 0F, 6, 15, 6);
      Drum.setRotationPoint(-3F, 9F, -3F);
      Drum.setTextureSize(128, 128);
      Drum.mirror = true;
      setRotation(Drum, 0F, 0F, 0F);
      BackTop = new ModelRenderer(this, 0, 39);
      BackTop.addBox(0F, 0F, 0F, 2, 1, 2);
      BackTop.setRotationPoint(-1F, 10.5F, 5F);
      BackTop.setTextureSize(128, 128);
      BackTop.mirror = true;
      setRotation(BackTop, 0F, 0F, 0F);
      Axil = new ModelRenderer(this, 49, 22);
      Axil.addBox(0F, 0F, 0F, 2, 2, 10);
      Axil.setRotationPoint(-1F, 13F, -5F);
      Axil.setTextureSize(128, 128);
      Axil.mirror = true;
      setRotation(Axil, 0F, 0F, 0F);
      FrontTop = new ModelRenderer(this, 0, 39);
      FrontTop.addBox(0F, 0F, 0F, 2, 1, 2);
      FrontTop.setRotationPoint(-1F, 10.5F, -7F);
      FrontTop.setTextureSize(128, 128);
      FrontTop.mirror = true;
      setRotation(FrontTop, 0F, 0F, 0F);
      Cover = new ModelRenderer(this, 74, 0);
      Cover.addBox(0F, 0F, 0F, 14, 2, 8);
      Cover.setRotationPoint(-7F, 8.5F, -4F);
      Cover.setTextureSize(128, 128);
      Cover.mirror = true;
      setRotation(Cover, 0F, 0F, 0F);
      Shell1 = new ModelRenderer(this, 0, 17);
      Shell1.addBox(0F, 0F, 0F, 8, 1, 8);
      Shell1.setRotationPoint(-4F, 23F, -4F);
      Shell1.setTextureSize(128, 128);
      Shell1.mirror = true;
      setRotation(Shell1, 0F, 0F, 0F);
      Shell2 = new ModelRenderer(this, 0, 17);
      Shell2.addBox(0F, 0F, 0F, 8, 1, 8);
      Shell2.setRotationPoint(-4F, 21.5F, -4F);
      Shell2.setTextureSize(128, 128);
      Shell2.mirror = true;
      setRotation(Shell2, 0F, 0F, 0F);
      Shell3 = new ModelRenderer(this, 0, 17);
      Shell3.addBox(0F, 0F, 0F, 8, 1, 8);
      Shell3.setRotationPoint(-4F, 20.03333F, -4F);
      Shell3.setTextureSize(128, 128);
      Shell3.mirror = true;
      setRotation(Shell3, 0F, 0F, 0F);
      Shell4 = new ModelRenderer(this, 0, 17);
      Shell4.addBox(0F, 0F, 0F, 8, 1, 8);
      Shell4.setRotationPoint(-4F, 18.5F, -4F);
      Shell4.setTextureSize(128, 128);
      Shell4.mirror = true;
      setRotation(Shell4, 0F, 0F, 0F);
      Shell5 = new ModelRenderer(this, 0, 17);
      Shell5.addBox(0F, 0F, 0F, 8, 1, 8);
      Shell5.setRotationPoint(-4F, 17F, -4F);
      Shell5.setTextureSize(128, 128);
      Shell5.mirror = true;
      setRotation(Shell5, 0F, 0F, 0F);
      Shell6 = new ModelRenderer(this, 0, 17);
      Shell6.addBox(0F, 0F, 0F, 8, 1, 8);
      Shell6.setRotationPoint(-4F, 15.5F, -4F);
      Shell6.setTextureSize(128, 128);
      Shell6.mirror = true;
      setRotation(Shell6, 0F, 0F, 0F);
      Shell7 = new ModelRenderer(this, 0, 17);
      Shell7.addBox(0F, 0F, 0F, 8, 1, 8);
      Shell7.setRotationPoint(-4F, 11.5F, -4F);
      Shell7.setTextureSize(128, 128);
      Shell7.mirror = true;
      setRotation(Shell7, 0F, 0F, 0F);
      BackPistonAxil = new ModelRenderer(this, 0, 27);
      BackPistonAxil.addBox(0F, 0F, 0F, 2, 4, 1);
      BackPistonAxil.setRotationPoint(-1F, 19F, 6F);
      BackPistonAxil.setTextureSize(128, 128);
      BackPistonAxil.mirror = true;
      setRotation(BackPistonAxil, 0F, 0F, 0F);
      FrontPistonAxil = new ModelRenderer(this, 0, 27);
      FrontPistonAxil.addBox(0F, 0F, 0F, 2, 4, 1);
      FrontPistonAxil.setRotationPoint(-1F, 19F, -7F);
      FrontPistonAxil.setTextureSize(128, 128);
      FrontPistonAxil.mirror = true;
      setRotation(FrontPistonAxil, 0F, 0F, 0F);
      PistonBaseFrnt = new ModelRenderer(this, 7, 27);
      PistonBaseFrnt.addBox(0F, 0F, 0F, 3, 9, 3);
      PistonBaseFrnt.setRotationPoint(-1.5F, 11F, -7.5F);
      PistonBaseFrnt.setTextureSize(128, 128);
      PistonBaseFrnt.mirror = true;
      setRotation(PistonBaseFrnt, 0F, 0F, 0F);
      PistonBaseBack = new ModelRenderer(this, 7, 27);
      PistonBaseBack.addBox(0F, 0F, 0F, 3, 9, 3);
      PistonBaseBack.setRotationPoint(-1.5F, 11F, 4.5F);
      PistonBaseBack.setTextureSize(128, 128);
      PistonBaseBack.mirror = true;
      setRotation(PistonBaseBack, 0F, 0F, 0F);
      Rod1 = new ModelRenderer(this, 20, 27);
      Rod1.addBox(0F, 0F, 0F, 1, 12, 1);
      Rod1.setRotationPoint(-6F, 10F, 2.5F);
      Rod1.setTextureSize(128, 128);
      Rod1.mirror = true;
      setRotation(Rod1, 0F, 0F, 0F);
      Rod2 = new ModelRenderer(this, 20, 27);
      Rod2.addBox(0F, 0F, 0F, 1, 12, 1);
      Rod2.setRotationPoint(-6F, 10F, 0.5F);
      Rod2.setTextureSize(128, 128);
      Rod2.mirror = true;
      setRotation(Rod2, 0F, 0F, 0F);
      Rod3 = new ModelRenderer(this, 20, 27);
      Rod3.addBox(0F, 0F, 0F, 1, 12, 1);
      Rod3.setRotationPoint(-6F, 10F, -1.5F);
      Rod3.setTextureSize(128, 128);
      Rod3.mirror = true;
      setRotation(Rod3, 0F, 0F, 0F);
      Rod4 = new ModelRenderer(this, 20, 27);
      Rod4.addBox(0F, 0F, 0F, 1, 12, 1);
      Rod4.setRotationPoint(-6F, 10F, -3.5F);
      Rod4.setTextureSize(128, 128);
      Rod4.mirror = true;
      setRotation(Rod4, 0F, 0F, 0F);
      RodBase = new ModelRenderer(this, 25, 27);
      RodBase.addBox(0F, 0F, 0F, 3, 3, 8);
      RodBase.setRotationPoint(-7.5F, 21F, -4F);
      RodBase.setTextureSize(128, 128);
      RodBase.mirror = true;
      setRotation(RodBase, 0F, 0F, 0F);
      PistonBackHead = new ModelRenderer(this, 0, 43);
      PistonBackHead.addBox(0F, 0F, 0F, 6, 2, 3);
      PistonBackHead.setRotationPoint(-3F, 22F, 5F);
      PistonBackHead.setTextureSize(128, 128);
      PistonBackHead.mirror = true;
      setRotation(PistonBackHead, 0F, 0F, 0F);
      PistonFrontHead = new ModelRenderer(this, 0, 43);
      PistonFrontHead.addBox(0F, 0F, 0F, 6, 2, 3);
      PistonFrontHead.setRotationPoint(-3F, 22F, -8F);
      PistonFrontHead.setTextureSize(128, 128);
      PistonFrontHead.mirror = true;
      setRotation(PistonFrontHead, 0F, 0F, 0F);
      CoverSheilding = new ModelRenderer(this, 74, 11);
      CoverSheilding.addBox(0F, 0F, 0F, 13, 1, 7);
      CoverSheilding.setRotationPoint(-6.5F, 8F, -3.5F);
      CoverSheilding.setTextureSize(128, 128);
      CoverSheilding.mirror = true;
      setRotation(CoverSheilding, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Drum.render(f5);
    BackTop.render(f5);
    Axil.render(f5);
    FrontTop.render(f5);
    Cover.render(f5);
    Shell1.render(f5);
    Shell2.render(f5);
    Shell3.render(f5);
    Shell4.render(f5);
    Shell5.render(f5);
    Shell6.render(f5);
    Shell7.render(f5);
    BackPistonAxil.render(f5);
    FrontPistonAxil.render(f5);
    PistonBaseFrnt.render(f5);
    PistonBaseBack.render(f5);
    Rod1.render(f5);
    Rod2.render(f5);
    Rod3.render(f5);
    Rod4.render(f5);
    RodBase.render(f5);
    PistonBackHead.render(f5);
    PistonFrontHead.render(f5);
    CoverSheilding.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5);
  }

}
