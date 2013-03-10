package alpha.fod.skyr.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelJellyfish extends ModelBase
{
  //fields
    ModelRenderer MainTenticle;
    ModelRenderer JellyHead;
    ModelRenderer JellyHeadInside;
    ModelRenderer Tenticel1;
    ModelRenderer Tenticle2;
    ModelRenderer Tenticle3;
    ModelRenderer Tenticle4;
    ModelRenderer Tenticle5;
    ModelRenderer Tenticle6;
    ModelRenderer Tenticle7;
    ModelRenderer Tenticle8;
    ModelRenderer Flap1;
    ModelRenderer Flap2;
    ModelRenderer Flap3;
    ModelRenderer Flap4;
    ModelRenderer Head1;
    ModelRenderer Head2;
  
  public ModelJellyfish()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      MainTenticle = new ModelRenderer(this, 6, 44);
      MainTenticle.addBox(0F, 0F, 0F, 1, 10, 1);
      MainTenticle.setRotationPoint(0F, 0F, 0F);
      MainTenticle.setTextureSize(64, 64);
      MainTenticle.mirror = true;
      setRotation(MainTenticle, 0F, 0F, 0F);
      JellyHead = new ModelRenderer(this, 17, 31);
      JellyHead.addBox(0F, 0F, 0F, 7, 6, 7);
      JellyHead.setRotationPoint(-3F, -5F, -3F);
      JellyHead.setTextureSize(64, 64);
      JellyHead.mirror = true;
      setRotation(JellyHead, 0F, 0F, 0F);
      JellyHeadInside = new ModelRenderer(this, 48, 4);
      JellyHeadInside.addBox(0F, 0F, 0F, 3, 2, 3);
      JellyHeadInside.setRotationPoint(-1F, -3F, -1F);
      JellyHeadInside.setTextureSize(64, 64);
      JellyHeadInside.mirror = true;
      setRotation(JellyHeadInside, 0F, 0F, 0F);
      Tenticel1 = new ModelRenderer(this, 0, 0);
      Tenticel1.addBox(0F, 0F, 0F, 1, 6, 1);
      Tenticel1.setRotationPoint(0F, 0F, -3F);
      Tenticel1.setTextureSize(64, 64);
      Tenticel1.mirror = true;
      setRotation(Tenticel1, 0F, 0F, 0.2974289F);
      Tenticle2 = new ModelRenderer(this, 0, 0);
      Tenticle2.addBox(0F, 0F, 0F, 1, 6, 1);
      Tenticle2.setRotationPoint(0F, 0F, 3F);
      Tenticle2.setTextureSize(64, 64);
      Tenticle2.mirror = true;
      setRotation(Tenticle2, 0F, 0F, -0.306455F);
      Tenticle3 = new ModelRenderer(this, 0, 0);
      Tenticle3.addBox(0F, 0F, 0F, 1, 6, 1);
      Tenticle3.setRotationPoint(3F, 0F, 0F);
      Tenticle3.setTextureSize(64, 64);
      Tenticle3.mirror = true;
      setRotation(Tenticle3, 0F, 0F, 0.247341F);
      Tenticle4 = new ModelRenderer(this, 0, 0);
      Tenticle4.addBox(0F, 0F, 0F, 1, 6, 1);
      Tenticle4.setRotationPoint(-3F, 0F, 0F);
      Tenticle4.setTextureSize(64, 64);
      Tenticle4.mirror = true;
      setRotation(Tenticle4, 0F, 0F, -0.2473481F);
      Tenticle5 = new ModelRenderer(this, 0, 0);
      Tenticle5.addBox(0F, 0F, 0F, 1, 6, 1);
      Tenticle5.setRotationPoint(2F, 0F, -3F);
      Tenticle5.setTextureSize(64, 64);
      Tenticle5.mirror = true;
      setRotation(Tenticle5, 0F, 0F, -0.306455F);
      Tenticle6 = new ModelRenderer(this, 0, 0);
      Tenticle6.addBox(0F, 0F, 0F, 1, 6, 1);
      Tenticle6.setRotationPoint(-3F, 0F, -3F);
      Tenticle6.setTextureSize(64, 64);
      Tenticle6.mirror = true;
      setRotation(Tenticle6, 0.2443461F, 0F, 0F);
      Tenticle7 = new ModelRenderer(this, 0, 0);
      Tenticle7.addBox(0F, 0F, 0F, 1, 6, 1);
      Tenticle7.setRotationPoint(-3F, 0F, 3F);
      Tenticle7.setTextureSize(64, 64);
      Tenticle7.mirror = true;
      setRotation(Tenticle7, -0.2268928F, 0F, -0.0621089F);
      Tenticle8 = new ModelRenderer(this, 0, 0);
      Tenticle8.addBox(0F, 0F, 0F, 1, 6, 1);
      Tenticle8.setRotationPoint(3F, 0F, 3F);
      Tenticle8.setTextureSize(64, 64);
      Tenticle8.mirror = true;
      setRotation(Tenticle8, -0.1919862F, 0F, 0.0077042F);
      Flap1 = new ModelRenderer(this, 0, 20);
      Flap1.addBox(0F, 0F, 0F, 7, 1, 5);
      Flap1.setRotationPoint(-3F, -4.3F, -2.2F);
      Flap1.setTextureSize(64, 64);
      Flap1.mirror = true;
      setRotation(Flap1, -2.530727F, 0F, 0F);
      Flap2 = new ModelRenderer(this, 0, 20);
      Flap2.addBox(0F, 0F, 0F, 7, 1, 5);
      Flap2.setRotationPoint(-3F, -5F, 4F);
      Flap2.setTextureSize(64, 64);
      Flap2.mirror = true;
      setRotation(Flap2, -0.4363323F, 0F, 0F);
      Flap3 = new ModelRenderer(this, 35, 20);
      Flap3.addBox(0F, 0F, 0F, 5, 1, 7);
      Flap3.setRotationPoint(4F, -5F, -3F);
      Flap3.setTextureSize(64, 64);
      Flap3.mirror = true;
      setRotation(Flap3, 0F, 0F, 0.4363323F);
      Flap4 = new ModelRenderer(this, 35, 20);
      Flap4.addBox(0F, 0F, 0F, 5, 1, 7);
      Flap4.setRotationPoint(-2.2F, -4.3F, -3F);
      Flap4.setTextureSize(64, 64);
      Flap4.mirror = true;
      setRotation(Flap4, 0F, 0F, 2.530727F);
      Head1 = new ModelRenderer(this, 20, 0);
      Head1.addBox(0F, 0F, 0F, 5, 1, 5);
      Head1.setRotationPoint(-2F, -6F, -2F);
      Head1.setTextureSize(64, 64);
      Head1.mirror = true;
      setRotation(Head1, 0F, 0F, 0F);
      Head2 = new ModelRenderer(this, 20, 10);
      Head2.addBox(0F, 0F, 0F, 3, 1, 3);
      Head2.setRotationPoint(-1F, -7F, -1F);
      Head2.setTextureSize(64, 64);
      Head2.mirror = true;
      setRotation(Head2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    MainTenticle.render(f5);
    JellyHead.render(f5);
    JellyHeadInside.render(f5);
    Tenticel1.render(f5);
    Tenticle2.render(f5);
    Tenticle3.render(f5);
    Tenticle4.render(f5);
    Tenticle5.render(f5);
    Tenticle6.render(f5);
    Tenticle7.render(f5);
    Tenticle8.render(f5);
    Flap1.render(f5);
    Flap2.render(f5);
    Flap3.render(f5);
    Flap4.render(f5);
    Head1.render(f5);
    Head2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
    Tenticel1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.2F * f1;
    Tenticle2.rotateAngleX = MathHelper.cos(f * 0.65432662F) * 0.2F * f1;
    Tenticle3.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
    Tenticle4.rotateAngleX = MathHelper.cos(f * 0.865432F) * 0.4F * f1;
    Tenticle5.rotateAngleX = MathHelper.cos(f * 0.654662F) * 0.4F * f1;
    Tenticle6.rotateAngleX = MathHelper.cos(f * 0.66775462F) * 0.4F * f1;
    Tenticle7.rotateAngleX = MathHelper.cos(f * 0.668762F) * 0.4F * f1;
    Tenticle8.rotateAngleX = MathHelper.cos(f * 0.66455462F) * 0.4F * f1;
    MainTenticle.rotateAngleX = MathHelper.cos(f * 0.664576555462F) * 0.4F * f1;



  }

}
