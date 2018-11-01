package arrowstorm66.tartheus.entity.model;

import arrowstorm66.tartheus.entity.EntityScorpion;
import arrowstorm66.tartheus.entity.EntitySolifugae;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelSolifugae - Either Mojang or a mod author Created using Tabula 7.0.0
 */
public class ModelSolifugae extends ModelBase {
	public ModelRenderer Carapace;
	public ModelRenderer Rleg1;
	public ModelRenderer Rleg3;
	public ModelRenderer Lleg1;
	public ModelRenderer Lleg2;
	public ModelRenderer Lleg3;
	public ModelRenderer Rleg2;
	public ModelRenderer Abdomen;
	public ModelRenderer Head;
	public ModelRenderer Lleg4;
	public ModelRenderer Rleg4;
	public ModelRenderer LTusk;
	public ModelRenderer RTusk;
	public ModelRenderer LJaw;
	public ModelRenderer RJaw;
	public ModelRenderer LEye;
	public ModelRenderer REye;
	public ModelRenderer LJaw2;
	public ModelRenderer LTopFang;
	public ModelRenderer LBotFang;
	public ModelRenderer RJaw2;
	public ModelRenderer RTopFang;
	public ModelRenderer RBotFang;

	public ModelSolifugae() {
		this.textureWidth = 120;
		this.textureHeight = 60;
		this.RBotFang = new ModelRenderer(this, 41, 35);
		this.RBotFang.setRotationPoint(0.4F, 2.0F, -3.0F);
		this.RBotFang.addBox(-1.0F, -0.5F, -4.5F, 2, 1, 5, 0.0F);
		this.setRotateAngle(RBotFang, -0.17453292519943295F, 0.0F, 0.0F);
		this.Lleg1 = new ModelRenderer(this, 59, 45);
		this.Lleg1.setRotationPoint(4.5F, 16.0F, 1.0F);
		this.Lleg1.addBox(-1.5F, -1.0F, -1.0F, 20, 2, 2, 0.0F);
		this.setRotateAngle(Lleg1, 0.0F, -0.7853981633974483F, 0.5235987755982988F);
		this.RTopFang = new ModelRenderer(this, 41, 28);
		this.RTopFang.setRotationPoint(0.4F, -1.0F, -3.0F);
		this.RTopFang.addBox(-1.0F, -0.5F, -4.5F, 2, 1, 5, 0.0F);
		this.setRotateAngle(RTopFang, 0.17453292519943295F, 0.0F, 0.0F);
		this.LTopFang = new ModelRenderer(this, 41, 43);
		this.LTopFang.setRotationPoint(-0.2F, -1.0F, -3.0F);
		this.LTopFang.addBox(-1.0F, -0.5F, -4.5F, 2, 1, 5, 0.0F);
		this.setRotateAngle(LTopFang, 0.17453292519943295F, 0.0F, 0.0F);
		this.LEye = new ModelRenderer(this, 0, 0);
		this.LEye.setRotationPoint(0.7F, -4.7F, -8.7F);
		this.LEye.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.REye = new ModelRenderer(this, 0, 2);
		this.REye.setRotationPoint(-0.7F, -4.7F, -8.7F);
		this.REye.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.Rleg1 = new ModelRenderer(this, 59, 40);
		this.Rleg1.setRotationPoint(-4.5F, 16.0F, 1.0F);
		this.Rleg1.addBox(-18.5F, -1.0F, -1.0F, 20, 2, 2, 0.0F);
		this.setRotateAngle(Rleg1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
		this.Lleg4 = new ModelRenderer(this, 59, 45);
		this.Lleg4.setRotationPoint(4.0F, -0.4F, -3.0F);
		this.Lleg4.addBox(-0.5F, -1.0F, -1.0F, 19, 2, 2, 0.0F);
		this.setRotateAngle(Lleg4, 0.0F, 0.6108652381980153F, 0.4363323129985824F);
		this.LJaw = new ModelRenderer(this, 20, 31);
		this.LJaw.setRotationPoint(2.5F, -1.5F, -9.0F);
		this.LJaw.addBox(-2.0F, -2.5F, -4.5F, 4, 6, 5, 0.0F);
		this.setRotateAngle(LJaw, 0.09599310885968812F, 0.05235987755982988F, 0.0F);
		this.Carapace = new ModelRenderer(this, 2, 0);
		this.Carapace.setRotationPoint(0.0F, 15.5F, 0.0F);
		this.Carapace.addBox(-4.5F, -2.5F, -4.0F, 9, 4, 7, 0.0F);
		this.Rleg2 = new ModelRenderer(this, 59, 40);
		this.Rleg2.setRotationPoint(-4.5F, 16.0F, -1.5F);
		this.Rleg2.addBox(-18.5F, -1.0F, -1.0F, 20, 2, 2, 0.0F);
		this.setRotateAngle(Rleg2, 0.0F, 0.3141592653589793F, -0.40142572795869574F);
		this.LTusk = new ModelRenderer(this, 60, 34);
		this.LTusk.setRotationPoint(3.5F, -0.4F, -5.0F);
		this.LTusk.addBox(-1.0F, -1.0F, -1.0F, 25, 2, 2, 0.0F);
		this.setRotateAngle(LTusk, 0.0F, 1.1344640137963142F, 0.2617993877991494F);
		this.RJaw2 = new ModelRenderer(this, 4, 43);
		this.RJaw2.setRotationPoint(0.5F, 0.0F, -4.5F);
		this.RJaw2.addBox(-1.5F, -2.0F, -3.0F, 3, 5, 3, 0.0F);
		this.Lleg2 = new ModelRenderer(this, 59, 45);
		this.Lleg2.setRotationPoint(4.5F, 16.0F, -1.5F);
		this.Lleg2.addBox(-1.5F, -1.0F, -1.0F, 20, 2, 2, 0.0F);
		this.setRotateAngle(Lleg2, 0.0F, -0.3141592653589793F, 0.40142572795869574F);
		this.Head = new ModelRenderer(this, 0, 14);
		this.Head.setRotationPoint(0.0F, 0.5F, -3.0F);
		this.Head.addBox(-5.0F, -5.0F, -9.0F, 10, 8, 8, 0.0F);
		this.Rleg3 = new ModelRenderer(this, 59, 40);
		this.Rleg3.setRotationPoint(-4.5F, 16.0F, -3.0F);
		this.Rleg3.addBox(-18.5F, -1.0F, -1.0F, 20, 2, 2, 0.0F);
		this.setRotateAngle(Rleg3, 0.0F, -0.3141592653589793F, -0.40142572795869574F);
		this.RTusk = new ModelRenderer(this, 60, 29);
		this.RTusk.setRotationPoint(-3.5F, -0.4F, -5.0F);
		this.RTusk.addBox(-24.0F, -1.0F, -1.0F, 25, 2, 2, 0.0F);
		this.setRotateAngle(RTusk, 0.0F, -1.1344640137963142F, -0.2617993877991494F);
		this.RJaw = new ModelRenderer(this, 0, 31);
		this.RJaw.setRotationPoint(-2.5F, -1.5F, -9.0F);
		this.RJaw.addBox(-2.0F, -2.5F, -4.5F, 4, 6, 5, 0.0F);
		this.setRotateAngle(RJaw, 0.09599310885968812F, -0.05235987755982988F, 0.0F);
		this.Lleg3 = new ModelRenderer(this, 59, 45);
		this.Lleg3.setRotationPoint(4.5F, 16.0F, -3.0F);
		this.Lleg3.addBox(-1.5F, -1.0F, -1.0F, 20, 2, 2, 0.0F);
		this.setRotateAngle(Lleg3, 0.0F, 0.3141592653589793F, 0.40142572795869574F);
		this.Rleg4 = new ModelRenderer(this, 59, 40);
		this.Rleg4.setRotationPoint(-4.0F, -0.4F, -3.0F);
		this.Rleg4.addBox(-18.5F, -1.0F, -1.0F, 19, 2, 2, 0.0F);
		this.setRotateAngle(Rleg4, 0.0F, -0.6108652381980153F, -0.4363323129985824F);
		this.Abdomen = new ModelRenderer(this, 39, 0);
		this.Abdomen.setRotationPoint(0.0F, 0.4F, 3.5F);
		this.Abdomen.addBox(-6.0F, -5.0F, -0.3F, 12, 8, 19, 0.0F);
		this.setRotateAngle(Abdomen, 0.091106186954104F, 0.0F, 0.0F);
		this.LJaw2 = new ModelRenderer(this, 23, 43);
		this.LJaw2.setRotationPoint(-0.5F, 0.0F, -4.5F);
		this.LJaw2.addBox(-1.5F, -2.0F, -3.0F, 3, 5, 3, 0.0F);
		this.LBotFang = new ModelRenderer(this, 41, 50);
		this.LBotFang.setRotationPoint(-0.2F, 2.0F, -3.0F);
		this.LBotFang.addBox(-1.0F, -0.5F, -4.5F, 2, 1, 5, 0.0F);
		this.setRotateAngle(LBotFang, -0.17453292519943295F, 0.0F, 0.0F);
		this.RJaw2.addChild(this.RBotFang);
		this.RJaw2.addChild(this.RTopFang);
		this.LJaw2.addChild(this.LTopFang);
		this.Head.addChild(this.LEye);
		this.Head.addChild(this.REye);
		this.Head.addChild(this.Lleg4);
		this.Head.addChild(this.LJaw);
		this.Head.addChild(this.LTusk);
		this.RJaw.addChild(this.RJaw2);
		this.Carapace.addChild(this.Head);
		this.Head.addChild(this.RTusk);
		this.Head.addChild(this.RJaw);
		this.Head.addChild(this.Rleg4);
		this.Carapace.addChild(this.Abdomen);
		this.LJaw.addChild(this.LJaw2);
		this.LJaw2.addChild(this.LBotFang);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.Lleg1.render(f5);
		this.Rleg1.render(f5);
		this.Carapace.render(f5);
		this.Rleg2.render(f5);
		this.Lleg2.render(f5);
		this.Rleg3.render(f5);
		this.Lleg3.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		EntitySolifugae solifugae = (EntitySolifugae) entityIn;
		if (solifugae.isBesideClimbableBlock()) {
			final float armSwing = ageInTicks * 0.4f;
			Head.rotateAngleY = netHeadYaw * 0.017453292F;
			Head.rotateAngleX = headPitch * 0.017453292F;
			this.Rleg1.rotateAngleZ = -((float) Math.PI / 4F);
			this.Lleg1.rotateAngleZ = ((float) Math.PI / 4F);
			this.Rleg2.rotateAngleZ = -0.58119464F;
			this.Lleg2.rotateAngleZ = 0.58119464F;
			this.Rleg3.rotateAngleZ = -0.58119464F;
			this.Lleg3.rotateAngleZ = 0.58119464F;
			this.Rleg1.rotateAngleY = ((float) Math.PI / 4F);
			this.Lleg1.rotateAngleY = -((float) Math.PI / 4F);
			this.Rleg2.rotateAngleY = 0.3926991F;
			this.Lleg2.rotateAngleY = -0.3926991F;
			this.Rleg3.rotateAngleY = -0.3926991F;
			this.Lleg3.rotateAngleY = 0.3926991F;
			float f3 = -(MathHelper.cos(armSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * 0.7f;
			float f4 = -(MathHelper.cos(armSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * 0.7f;
			float f5 = -(MathHelper.cos(armSwing * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * 0.7f;
			float f7 = Math.abs(MathHelper.sin(armSwing * 0.6662F + 0.0F) * 0.4F) * 0.7f;
			float f8 = Math.abs(MathHelper.sin(armSwing * 0.6662F + (float) Math.PI) * 0.4F) * 0.7f;
			float f9 = Math.abs(MathHelper.sin(armSwing * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * 0.7f;
			this.Rleg1.rotateAngleY += f3;
			this.Lleg1.rotateAngleY += -f3;
			this.Rleg2.rotateAngleY += f4;
			this.Lleg2.rotateAngleY += -f4;
			this.Rleg3.rotateAngleY += f5;
			this.Lleg3.rotateAngleY += -f5;
			this.Rleg1.rotateAngleZ += f7;
			this.Lleg1.rotateAngleZ += -f7;
			this.Rleg2.rotateAngleZ += f8;
			this.Lleg2.rotateAngleZ += -f8;
			this.Rleg3.rotateAngleZ += f9;
			this.Lleg3.rotateAngleZ += -f9;
		} else {
			Head.rotateAngleY = netHeadYaw * 0.017453292F;
			Head.rotateAngleX = headPitch * 0.017453292F;
			this.Rleg1.rotateAngleZ = -((float) Math.PI / 4F);
			this.Lleg1.rotateAngleZ = ((float) Math.PI / 4F);
			this.Rleg2.rotateAngleZ = -0.58119464F;
			this.Lleg2.rotateAngleZ = 0.58119464F;
			this.Rleg3.rotateAngleZ = -0.58119464F;
			this.Lleg3.rotateAngleZ = 0.58119464F;
			this.Rleg1.rotateAngleY = ((float) Math.PI / 4F);
			this.Lleg1.rotateAngleY = -((float) Math.PI / 4F);
			this.Rleg2.rotateAngleY = 0.3926991F;
			this.Lleg2.rotateAngleY = -0.3926991F;
			this.Rleg3.rotateAngleY = -0.3926991F;
			this.Lleg3.rotateAngleY = 0.3926991F;
			float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
			float f4 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * limbSwingAmount;
			float f5 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
			float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
			float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * limbSwingAmount;
			float f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
			this.Rleg1.rotateAngleY += f3;
			this.Lleg1.rotateAngleY += -f3;
			this.Rleg2.rotateAngleY += f4;
			this.Lleg2.rotateAngleY += -f4;
			this.Rleg3.rotateAngleY += f5;
			this.Lleg3.rotateAngleY += -f5;
			this.Rleg1.rotateAngleZ += f7;
			this.Lleg1.rotateAngleZ += -f7;
			this.Rleg2.rotateAngleZ += f8;
			this.Lleg2.rotateAngleZ += -f8;
			this.Rleg3.rotateAngleZ += f9;
			this.Lleg3.rotateAngleZ += -f9;
		}
	}
}
