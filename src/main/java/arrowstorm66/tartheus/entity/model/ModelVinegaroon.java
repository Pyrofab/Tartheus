package arrowstorm66.tartheus.entity.model;

import arrowstorm66.tartheus.entity.EntityScorpion;
import arrowstorm66.tartheus.entity.EntitySolifugae;
import arrowstorm66.tartheus.entity.EntityVinegaroon;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

/**
 * ModelVinegaroon - Either Mojang or a mod author Created using Tabula 7.0.0
 */
public class ModelVinegaroon extends ModelBase {
	public ModelRenderer Carapace;
    public ModelRenderer RFrontEye;
    public ModelRenderer LFrontEye;
    public ModelRenderer REye;
    public ModelRenderer LEye;
	public ModelRenderer Rleg1;
	public ModelRenderer Rleg3;
	public ModelRenderer Rleg4;
	public ModelRenderer Lleg1;
	public ModelRenderer Lleg2;
	public ModelRenderer Lleg3;
	public ModelRenderer Lleg4;
	public ModelRenderer Rleg2;
	public ModelRenderer Abdomen;
	public ModelRenderer Head;
	public ModelRenderer Tailnub;
	public ModelRenderer Tail;
	public ModelRenderer Tail_1;
	public ModelRenderer Larm1;
	public ModelRenderer Rarm1;
	public ModelRenderer Larm2;
	public ModelRenderer Larm3;
	public ModelRenderer Lclaw;
	public ModelRenderer Rarm2;
	public ModelRenderer Rarm3;
	public ModelRenderer Rclaw;
	public ModelRenderer Rleg4extend;
	public ModelRenderer Lleg4extend;

	public ModelVinegaroon() {
		this.textureWidth = 95;
		this.textureHeight = 58;
		this.LEye = new ModelRenderer(this, 0, 2);
        this.LEye.setRotationPoint(3.7F, -3.7F, -4.2F);
        this.LEye.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.LFrontEye = new ModelRenderer(this, 0, 0);
        this.LFrontEye.setRotationPoint(1.0F, -3.7F, -7.7F);
        this.LFrontEye.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.REye = new ModelRenderer(this, 0, 2);
        this.REye.setRotationPoint(-3.7F, -3.7F, -4.2F);
        this.REye.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.RFrontEye = new ModelRenderer(this, 0, 2);
        this.RFrontEye.setRotationPoint(-1.0F, -3.7F, -7.7F);
        this.RFrontEye.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.Rleg2 = new ModelRenderer(this, 59, 38);
		this.Rleg2.setRotationPoint(-4.5F, 16.5F, 0.3F);
		this.Rleg2.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.setRotateAngle(Rleg2, 0.0F, 0.3141592653589793F, -0.5235987755982988F);
		this.Abdomen = new ModelRenderer(this, 0, 14);
		this.Abdomen.setRotationPoint(0.0F, 0.4F, 3.5F);
		this.Abdomen.addBox(-5.0F, -4.0F, -0.3F, 10, 6, 18, 0.0F);
		this.setRotateAngle(Abdomen, 0.091106186954104F, 0.0F, 0.0F);
		this.Tail_1 = new ModelRenderer(this, 64, 10);
		this.Tail_1.setRotationPoint(0.0F, -0.1F, 8.8F);
		this.Tail_1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 9, 0.0F);
		this.setRotateAngle(Tail_1, 0.136659280431156F, 0.0F, 0.0F);
		this.Tailnub = new ModelRenderer(this, 79, 7);
		this.Tailnub.setRotationPoint(0.0F, -1.0F, 17.5F);
		this.Tailnub.addBox(-1.5F, -1.5F, -0.3F, 3, 3, 3, 0.0F);
		this.setRotateAngle(Tailnub, 0.091106186954104F, 0.0F, 0.0F);
		this.Rarm3 = new ModelRenderer(this, 68, 48);
		this.Rarm3.setRotationPoint(-6.5F, 0.0F, 0.8F);
		this.Rarm3.addBox(-5.5F, -2.0F, -1.0F, 5, 4, 2, 0.0F);
		this.setRotateAngle(Rarm3, 0.0F, -1.0471975511965976F, 0.0F);
		this.Rleg3 = new ModelRenderer(this, 59, 38);
		this.Rleg3.setRotationPoint(-4.5F, 16.5F, -1.7F);
		this.Rleg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.setRotateAngle(Rleg3, 0.0F, -0.39269908169872414F, -0.5235987755982988F);
		this.Head = new ModelRenderer(this, 0, 0);
		this.Head.setRotationPoint(0.0F, 0.5F, -3.0F);
		this.Head.addBox(-4.0F, -4.0F, -8.0F, 8, 6, 7, 0.0F);
		this.Rleg4extend = new ModelRenderer(this, 61, 34);
		this.Rleg4extend.setRotationPoint(-14.0F, -0.5F, -0.5F);
		this.Rleg4extend.addBox(-14.5F, -0.5F, -0.5F, 15, 1, 1, 0.0F);
		this.setRotateAngle(Rleg4extend, 0.5009094953223726F, -0.36302848441482055F, -0.22689280275926282F);
		this.Rarm1 = new ModelRenderer(this, 26, 39);
		this.Rarm1.setRotationPoint(-3.0F, -1.0F, -7.0F);
		this.Rarm1.addBox(-6.0F, -1.5F, -1.5F, 6, 3, 3, 0.0F);
		this.setRotateAngle(Rarm1, 0.0F, -0.6981317007977318F, 0.0F);
		this.Lleg3 = new ModelRenderer(this, 59, 43);
		this.Lleg3.setRotationPoint(4.5F, 16.5F, -1.3F);
		this.Lleg3.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.setRotateAngle(Lleg3, 0.0F, 0.39269908169872414F, 0.5235987755982988F);
		this.Rleg4 = new ModelRenderer(this, 61, 34);
		this.Rleg4.setRotationPoint(-4.5F, 16.5F, -2.5F);
		this.Rleg4.addBox(-14.0F, -1.0F, -1.0F, 15, 1, 1, 0.0F);
		this.setRotateAngle(Rleg4, 0.0F, -0.9637708129512687F, -0.4886921905584123F);
		this.Lleg4 = new ModelRenderer(this, 61, 34);
		this.Lleg4.setRotationPoint(4.5F, 16.5F, -2.5F);
		this.Lleg4.addBox(-1.0F, -1.0F, -1.0F, 15, 1, 1, 0.0F);
		this.setRotateAngle(Lleg4, 0.0F, 0.9637708129512687F, 0.4886921905584123F);
		this.Lleg2 = new ModelRenderer(this, 59, 43);
		this.Lleg2.setRotationPoint(4.5F, 16.5F, 0.3F);
		this.Lleg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.setRotateAngle(Lleg2, 0.0F, -0.3141592653589793F, 0.5235987755982988F);
		this.Larm2 = new ModelRenderer(this, 0, 46);
		this.Larm2.setRotationPoint(5.0F, 0.0F, 1.5F);
		this.Larm2.addBox(0.5F, -2.0F, -2.0F, 7, 4, 3, 0.0F);
		this.setRotateAngle(Larm2, 0.0F, 1.0471975511965976F, 0.0F);
		this.Rarm2 = new ModelRenderer(this, 27, 46);
		this.Rarm2.setRotationPoint(-5.0F, 0.0F, 1.5F);
		this.Rarm2.addBox(-7.5F, -2.0F, -2.0F, 7, 4, 3, 0.0F);
		this.setRotateAngle(Rarm2, 0.0F, -1.0471975511965976F, 0.0F);
		this.Carapace = new ModelRenderer(this, 31, 0);
		this.Carapace.setRotationPoint(0.0F, 15.7F, 0.0F);
		this.Carapace.addBox(-4.5F, -3.5F, -4.0F, 9, 6, 7, 0.0F);
		this.Rclaw = new ModelRenderer(this, 0, 53);
		this.Rclaw.setRotationPoint(-5.5F, 0.0F, 0.7F);
		this.Rclaw.addBox(-2.0F, -2.0F, -1.0F, 2, 4, 1, 0.0F);
		this.setRotateAngle(Rclaw, 0.0F, -0.6108652381980153F, 0.0F);
		this.Larm3 = new ModelRenderer(this, 52, 48);
		this.Larm3.setRotationPoint(6.5F, 0.0F, 0.8F);
		this.Larm3.addBox(0.5F, -2.0F, -1.0F, 5, 4, 2, 0.0F);
		this.setRotateAngle(Larm3, 0.0F, 1.0471975511965976F, 0.0F);
		this.Tail = new ModelRenderer(this, 64, 10);
		this.Tail.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.Tail.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 9, 0.0F);
		this.setRotateAngle(Tail, 0.136659280431156F, 0.0F, 0.0F);
		this.Lclaw = new ModelRenderer(this, 8, 53);
		this.Lclaw.setRotationPoint(5.5F, 0.0F, 0.7F);
		this.Lclaw.addBox(0.0F, -2.0F, -1.0F, 2, 4, 1, 0.0F);
		this.setRotateAngle(Lclaw, 0.0F, 0.6108652381980153F, 0.0F);
		this.Lleg4extend = new ModelRenderer(this, 61, 34);
		this.Lleg4extend.setRotationPoint(14.0F, -0.5F, -0.5F);
		this.Lleg4extend.addBox(-0.5F, -0.5F, -0.5F, 15, 1, 1, 0.0F);
		this.setRotateAngle(Lleg4extend, 0.5009094953223726F, 0.36302848441482055F, 0.22689280275926282F);
		this.Rleg1 = new ModelRenderer(this, 59, 38);
		this.Rleg1.setRotationPoint(-4.5F, 16.5F, 1.5F);
		this.Rleg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.setRotateAngle(Rleg1, 0.0F, 0.7853981633974483F, -0.6632251157578453F);
		this.Lleg1 = new ModelRenderer(this, 59, 43);
		this.Lleg1.setRotationPoint(4.5F, 16.5F, 1.5F);
		this.Lleg1.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.setRotateAngle(Lleg1, 0.0F, -0.7853981633974483F, 0.6632251157578453F);
		this.Larm1 = new ModelRenderer(this, 0, 39);
		this.Larm1.setRotationPoint(3.0F, -1.0F, -7.0F);
		this.Larm1.addBox(0.0F, -1.5F, -1.5F, 6, 3, 3, 0.0F);
		this.setRotateAngle(Larm1, 0.0F, 0.6981317007977318F, 0.0F);
        this.Head.addChild(this.LFrontEye);
        this.Head.addChild(this.RFrontEye);
        this.Head.addChild(this.LEye);
        this.Head.addChild(this.REye);
		this.Carapace.addChild(this.Abdomen);
		this.Tail.addChild(this.Tail_1);
		this.Abdomen.addChild(this.Tailnub);
		this.Rarm2.addChild(this.Rarm3);
		this.Carapace.addChild(this.Head);
		this.Rleg4.addChild(this.Rleg4extend);
		this.Head.addChild(this.Rarm1);
		this.Larm1.addChild(this.Larm2);
		this.Rarm1.addChild(this.Rarm2);
		this.Rarm3.addChild(this.Rclaw);
		this.Larm2.addChild(this.Larm3);
		this.Tailnub.addChild(this.Tail);
		this.Larm3.addChild(this.Lclaw);
		this.Lleg4.addChild(this.Lleg4extend);
		this.Head.addChild(this.Larm1);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.Rleg2.render(f5);
		this.Rleg3.render(f5);
		this.Lleg3.render(f5);
		this.Rleg4.render(f5);
		this.Lleg4.render(f5);
		this.Lleg2.render(f5);
		this.Carapace.render(f5);
		this.Rleg1.render(f5);
		this.Lleg1.render(f5);
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
		EntityVinegaroon vinegaroon = (EntityVinegaroon) entityIn;
		if (vinegaroon.isBesideClimbableBlock()) {
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

	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTickTime) {
		EntityVinegaroon entity = (EntityVinegaroon) entitylivingbaseIn;

		if (!entity.getPassengers().isEmpty()) {
			this.Rarm1.rotateAngleY = -0.75F;
			this.Larm1.rotateAngleY = 0.75F;
		} else {
			this.Rarm1.rotateAngleY = -0.3F;
			this.Larm1.rotateAngleY = 0.3F;
		}
		
		int i2 = entity.getTailTimer();
		if (i2 > 40) {
			this.Abdomen.rotateAngleX = 0.25F;
			this.Tailnub.rotateAngleX = 1.25F;
			this.Tail.rotateAngleX = 0.45F;
			this.Tail_1.rotateAngleX = 0.35F;
		} else {
			this.Abdomen.rotateAngleX = 0.0F;
			this.Tailnub.rotateAngleX = 0.0F;
			this.Tail.rotateAngleX = 0.136659280431156F;
			this.Tail_1.rotateAngleX = 0.136659280431156F;
		}
	}
}
