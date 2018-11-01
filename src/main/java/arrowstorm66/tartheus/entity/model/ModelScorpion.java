package arrowstorm66.tartheus.entity.model;

import arrowstorm66.tartheus.entity.EntityScorpion;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.util.math.MathHelper;

/**
 * ModelScorpion - Either Mojang or a mod author Created using Tabula 7.0.0
 */
public class ModelScorpion extends ModelBase {
	public ModelRenderer Carapace;
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
	public ModelRenderer Rear;
	public ModelRenderer Tailseg1;
	public ModelRenderer Tailseg2;
	public ModelRenderer Tailseg3;
	public ModelRenderer Stinger1;
	public ModelRenderer Stinger2;
	public ModelRenderer Larm1;
	public ModelRenderer Rarm1;
	public ModelRenderer Larm2;
	public ModelRenderer REye;
	public ModelRenderer LEye;
	public ModelRenderer RFrontEye;
	public ModelRenderer LFrontEye;
	public ModelRenderer LPincer;
	public ModelRenderer Lclawtop;
	public ModelRenderer Lclawbot;
	public ModelRenderer Rarm2;
	public ModelRenderer RPincer;
	public ModelRenderer Rclawtop;
	public ModelRenderer Rclawbot;

	public ModelScorpion() {
		this.textureWidth = 95;
		this.textureHeight = 48;
		this.LEye = new ModelRenderer(this, 0, 0);
		this.LEye.setRotationPoint(1.0F, -3.7F, -3.0F);
		this.LEye.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.RFrontEye = new ModelRenderer(this, 0, 2);
		this.RFrontEye.setRotationPoint(-3.0F, -3.7F, -7.7F);
		this.RFrontEye.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.REye = new ModelRenderer(this, 0, 2);
		this.REye.setRotationPoint(-1.0F, -3.7F, -3.0F);
		this.REye.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.LFrontEye = new ModelRenderer(this, 0, 0);
		this.LFrontEye.setRotationPoint(3.0F, -3.7F, -7.7F);
		this.LFrontEye.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.Lleg2 = new ModelRenderer(this, 59, 43);
		this.Lleg2.setRotationPoint(4.5F, 16.5F, 0.3F);
		this.Lleg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.setRotateAngle(Lleg2, 0.0F, -0.39269908169872414F, 0.5235987755982988F);
		this.Rleg3 = new ModelRenderer(this, 59, 38);
		this.Rleg3.setRotationPoint(-4.5F, 16.5F, -1.7F);
		this.Rleg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.setRotateAngle(Rleg3, 0.0F, -0.39269908169872414F, -0.5235987755982988F);
		this.LPincer = new ModelRenderer(this, 33, 16);
		this.LPincer.setRotationPoint(5.0F, 0.0F, -1.1F);
		this.LPincer.addBox(0.0F, -1.5F, -1.5F, 6, 4, 4, 0.0F);
		this.setRotateAngle(LPincer, 0.0F, 0.7155849933176751F, 0.0F);
		this.Rclawtop = new ModelRenderer(this, 1, 28);
		this.Rclawtop.setRotationPoint(-5.8F, 0.0F, 0.5F);
		this.Rclawtop.addBox(-5.0F, -1.5F, -1.0F, 5, 1, 2, 0.0F);
		this.setRotateAngle(Rclawtop, 0.0F, 0.0F, -0.18203784098300857F);
		this.Rear = new ModelRenderer(this, 66, 14);
		this.Rear.setRotationPoint(0.0F, -2.0F, 6.6F);
		this.Rear.addBox(-3.5F, -2.0F, -1.0F, 7, 5, 6, 0.0F);
		this.setRotateAngle(Rear, 0.27314402793711257F, 0.0F, 0.0F);
		this.Lclawtop = new ModelRenderer(this, 16, 28);
		this.Lclawtop.setRotationPoint(5.8F, 0.0F, 0.5F);
		this.Lclawtop.addBox(0.0F, -1.5F, -1.0F, 5, 1, 2, 0.0F);
		this.setRotateAngle(Lclawtop, 0.0F, 0.0F, 0.18203784098300857F);
		this.Rleg4 = new ModelRenderer(this, 59, 38);
		this.Rleg4.setRotationPoint(-4.5F, 16.5F, -2.5F);
		this.Rleg4.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.setRotateAngle(Rleg4, 0.0F, -0.7853981633974483F, -0.6632251157578453F);
		this.Head = new ModelRenderer(this, 0, 0);
		this.Head.setRotationPoint(0.0F, 0.5F, -3.0F);
		this.Head.addBox(-4.0F, -4.0F, -8.0F, 8, 6, 7, 0.0F);
		this.setRotateAngle(Head, -0.091106186954104F, 0.0F, 0.0F);
		this.Tailseg1 = new ModelRenderer(this, 0, 33);
		this.Tailseg1.setRotationPoint(0.0F, -0.7F, 5.1F);
		this.Tailseg1.addBox(-3.0F, -6.9F, -1.5F, 6, 9, 4, 0.0F);
		this.setRotateAngle(Tailseg1, -0.8651597102135892F, 0.0F, 0.0F);
		this.Stinger1 = new ModelRenderer(this, 54, 14);
		this.Stinger1.setRotationPoint(0.5F, -7.8F, -0.5F);
		this.Stinger1.addBox(-1.5F, -5.5F, -1.6F, 2, 6, 3, 0.0F);
		this.setRotateAngle(Stinger1, 1.9123572614101867F, 0.0F, 0.0F);
		this.Stinger2 = new ModelRenderer(this, 57, 24);
		this.Stinger2.setRotationPoint(-0.5F, -4.5F, -1.0F);
		this.Stinger2.addBox(-0.5F, -5.7F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(Stinger2, -0.40980330836826856F, 0.0F, 0.0F);
		this.RPincer = new ModelRenderer(this, 33, 24);
		this.RPincer.setRotationPoint(-5.0F, 0.0F, -1.1F);
		this.RPincer.addBox(-6.0F, -1.5F, -1.5F, 6, 4, 4, 0.0F);
		this.setRotateAngle(RPincer, 0.0F, -0.7285004297824331F, 0.0F);
		this.Rarm2 = new ModelRenderer(this, 8, 21);
		this.Rarm2.setRotationPoint(-5.5F, 0.0F, 0.0F);
		this.Rarm2.addBox(-6.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
		this.setRotateAngle(Rarm2, 0.0F, -1.8500490071139892F, 0.0F);
		this.Tailseg3 = new ModelRenderer(this, 40, 34);
		this.Tailseg3.setRotationPoint(0.0F, -8.2F, 0.8F);
		this.Tailseg3.addBox(-2.0F, -8.1F, -2.0F, 4, 9, 3, 0.0F);
		this.setRotateAngle(Tailseg3, 0.5009094953223726F, 0.0F, 0.0F);
		this.Rclawbot = new ModelRenderer(this, 1, 28);
		this.Rclawbot.setRotationPoint(-5.8F, 2.7F, 0.5F);
		this.Rclawbot.addBox(-5.0F, -1.5F, -1.0F, 5, 1, 2, 0.0F);
		this.setRotateAngle(Rclawbot, 0.0F, 0.0F, 0.04363323129985824F);
		this.Lleg1 = new ModelRenderer(this, 59, 43);
		this.Lleg1.setRotationPoint(4.5F, 16.5F, 1.5F);
		this.Lleg1.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.setRotateAngle(Lleg1, 0.0F, -0.7853981633974483F, 0.6632251157578453F);
		this.Rleg1 = new ModelRenderer(this, 59, 38);
		this.Rleg1.setRotationPoint(-4.5F, 16.5F, 1.5F);
		this.Rleg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.setRotateAngle(Rleg1, 0.0F, 0.7853981633974483F, -0.6632251157578453F);
		this.Abdomen = new ModelRenderer(this, 64, 0);
		this.Abdomen.setRotationPoint(0.0F, 0.4F, 3.5F);
		this.Abdomen.addBox(-4.0F, -4.0F, -0.3F, 8, 6, 7, 0.0F);
		this.setRotateAngle(Abdomen, 0.36425021489121656F, 0.0F, 0.0F);
		this.Lclawbot = new ModelRenderer(this, 16, 28);
		this.Lclawbot.setRotationPoint(5.8F, 2.7F, 0.5F);
		this.Lclawbot.addBox(0.0F, -1.5F, -1.0F, 5, 1, 2, 0.0F);
		this.setRotateAngle(Lclawbot, 0.0F, 0.0F, -0.04363323129985824F);
		this.Carapace = new ModelRenderer(this, 31, 0);
		this.Carapace.setRotationPoint(0.0F, 15.7F, 0.0F);
		this.Carapace.addBox(-4.5F, -3.5F, -4.0F, 9, 6, 7, 0.0F);
		this.setRotateAngle(Carapace, 0.091106186954104F, 0.0F, 0.0F);
		this.Larm1 = new ModelRenderer(this, 8, 21);
		this.Larm1.setRotationPoint(3.5F, -0.8F, -6.5F);
		this.Larm1.addBox(0.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
		this.setRotateAngle(Larm1, 0.0F, -0.5585053606381855F, 0.0F);
		this.Lleg4 = new ModelRenderer(this, 59, 43);
		this.Lleg4.setRotationPoint(4.5F, 16.5F, -2.5F);
		this.Lleg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.setRotateAngle(Lleg4, 0.0F, 0.7853981633974483F, 0.6632251157578453F);
		this.Larm2 = new ModelRenderer(this, 8, 21);
		this.Larm2.setRotationPoint(5.5F, 0.0F, 0.0F);
		this.Larm2.addBox(0.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
		this.setRotateAngle(Larm2, 0.0F, 1.8668041679331349F, 0.0F);
		this.Lleg3 = new ModelRenderer(this, 59, 43);
		this.Lleg3.setRotationPoint(4.5F, 16.5F, -1.3F);
		this.Lleg3.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.setRotateAngle(Lleg3, 0.0F, 0.39269908169872414F, 0.5235987755982988F);
		this.Tailseg2 = new ModelRenderer(this, 21, 33);
		this.Tailseg2.setRotationPoint(0.0F, -5.6F, 0.1F);
		this.Tailseg2.addBox(-2.5F, -8.0F, -1.5F, 5, 9, 4, 0.0F);
		this.setRotateAngle(Tailseg2, 1.0173524209874947F, 0.0F, 0.0F);
		this.Rarm1 = new ModelRenderer(this, 8, 21);
		this.Rarm1.setRotationPoint(-3.5F, -0.8F, -6.5F);
		this.Rarm1.addBox(-6.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
		this.setRotateAngle(Rarm1, 0.0F, 0.5585053606381855F, 0.0F);
		this.Rleg2 = new ModelRenderer(this, 59, 38);
		this.Rleg2.setRotationPoint(-4.5F, 16.5F, 0.3F);
		this.Rleg2.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
		this.setRotateAngle(Rleg2, 0.0F, 0.39269908169872414F, -0.5235987755982988F);
		this.Head.addChild(this.LFrontEye);
		this.Head.addChild(this.REye);
		this.Head.addChild(this.LEye);
		this.Head.addChild(this.RFrontEye);
		this.Larm2.addChild(this.LPincer);
		this.RPincer.addChild(this.Rclawtop);
		this.Stinger1.addChild(this.Stinger2);
		this.Abdomen.addChild(this.Rear);
		this.LPincer.addChild(this.Lclawtop);
		this.Carapace.addChild(this.Head);
		this.Rear.addChild(this.Tailseg1);
		this.Tailseg3.addChild(this.Stinger1);
		this.Rarm2.addChild(this.RPincer);
		this.Rarm1.addChild(this.Rarm2);
		this.Tailseg2.addChild(this.Tailseg3);
		this.RPincer.addChild(this.Rclawbot);
		this.Carapace.addChild(this.Abdomen);
		this.LPincer.addChild(this.Lclawbot);
		this.Head.addChild(this.Larm1);
		this.Larm1.addChild(this.Larm2);
		this.Tailseg1.addChild(this.Tailseg2);
		this.Head.addChild(this.Rarm1);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.Lleg2.render(f5);
		this.Rleg3.render(f5);
		this.Rleg4.render(f5);
		this.Lleg1.render(f5);
		this.Rleg1.render(f5);
		this.Carapace.render(f5);
		this.Lleg4.render(f5);
		this.Lleg3.render(f5);
		this.Rleg2.render(f5);
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
		EntityScorpion scorpion = (EntityScorpion) entityIn;
		if (scorpion.isBesideClimbableBlock()) {
			final float armSwing = ageInTicks * 0.4f;
			Head.rotateAngleY = netHeadYaw * 0.017453292F;
			Head.rotateAngleX = headPitch * 0.017453292F - 0.085f;
			this.Rleg1.rotateAngleZ = -((float) Math.PI / 4F);
			this.Lleg1.rotateAngleZ = ((float) Math.PI / 4F);
			this.Rleg2.rotateAngleZ = -0.58119464F;
			this.Lleg2.rotateAngleZ = 0.58119464F;
			this.Rleg3.rotateAngleZ = -0.58119464F;
			this.Lleg3.rotateAngleZ = 0.58119464F;
			this.Rleg4.rotateAngleZ = -((float) Math.PI / 4F);
			this.Lleg4.rotateAngleZ = ((float) Math.PI / 4F);
			this.Rleg1.rotateAngleY = ((float) Math.PI / 4F);
			this.Lleg1.rotateAngleY = -((float) Math.PI / 4F);
			this.Rleg2.rotateAngleY = 0.3926991F;
			this.Lleg2.rotateAngleY = -0.3926991F;
			this.Rleg3.rotateAngleY = -0.3926991F;
			this.Lleg3.rotateAngleY = 0.3926991F;
			this.Rleg4.rotateAngleY = -((float) Math.PI / 4F);
			this.Lleg4.rotateAngleY = ((float) Math.PI / 4F);
			float f3 = -(MathHelper.cos(armSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * 0.7f;
			float f4 = -(MathHelper.cos(armSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * 0.7f;
			float f5 = -(MathHelper.cos(armSwing * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * 0.7f;
			float f6 = -(MathHelper.cos(armSwing * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F) * 0.7f;
			float f7 = Math.abs(MathHelper.sin(armSwing * 0.6662F + 0.0F) * 0.4F) * 0.7f;
			float f8 = Math.abs(MathHelper.sin(armSwing * 0.6662F + (float) Math.PI) * 0.4F) * 0.7f;
			float f9 = Math.abs(MathHelper.sin(armSwing * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * 0.7f;
			float f10 = Math.abs(MathHelper.sin(armSwing * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F) * 0.7f;
			this.Rleg1.rotateAngleY += f3;
			this.Lleg1.rotateAngleY += -f3;
			this.Rleg2.rotateAngleY += f4;
			this.Lleg2.rotateAngleY += -f4;
			this.Rleg3.rotateAngleY += f5;
			this.Lleg3.rotateAngleY += -f5;
			this.Rleg4.rotateAngleY += f6;
			this.Lleg4.rotateAngleY += -f6;
			this.Rleg1.rotateAngleZ += f7;
			this.Lleg1.rotateAngleZ += -f7;
			this.Rleg2.rotateAngleZ += f8;
			this.Lleg2.rotateAngleZ += -f8;
			this.Rleg3.rotateAngleZ += f9;
			this.Lleg3.rotateAngleZ += -f9;
			this.Rleg4.rotateAngleZ += f10;
			this.Lleg4.rotateAngleZ += -f10;
		} else {
			Head.rotateAngleY = netHeadYaw * 0.017453292F;
			Head.rotateAngleX = headPitch * 0.017453292F - 0.085f;
			this.Rleg1.rotateAngleZ = -((float) Math.PI / 4F);
			this.Lleg1.rotateAngleZ = ((float) Math.PI / 4F);
			this.Rleg2.rotateAngleZ = -0.58119464F;
			this.Lleg2.rotateAngleZ = 0.58119464F;
			this.Rleg3.rotateAngleZ = -0.58119464F;
			this.Lleg3.rotateAngleZ = 0.58119464F;
			this.Rleg4.rotateAngleZ = -((float) Math.PI / 4F);
			this.Lleg4.rotateAngleZ = ((float) Math.PI / 4F);
			this.Rleg1.rotateAngleY = ((float) Math.PI / 4F);
			this.Lleg1.rotateAngleY = -((float) Math.PI / 4F);
			this.Rleg2.rotateAngleY = 0.3926991F;
			this.Lleg2.rotateAngleY = -0.3926991F;
			this.Rleg3.rotateAngleY = -0.3926991F;
			this.Lleg3.rotateAngleY = 0.3926991F;
			this.Rleg4.rotateAngleY = -((float) Math.PI / 4F);
			this.Lleg4.rotateAngleY = ((float) Math.PI / 4F);
			float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
			float f4 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * limbSwingAmount;
			float f5 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
			float f6 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F)
					* limbSwingAmount;
			float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
			float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * limbSwingAmount;
			float f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
			float f10 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F)
					* limbSwingAmount;
			this.Rleg1.rotateAngleY += f3;
			this.Lleg1.rotateAngleY += -f3;
			this.Rleg2.rotateAngleY += f4;
			this.Lleg2.rotateAngleY += -f4;
			this.Rleg3.rotateAngleY += f5;
			this.Lleg3.rotateAngleY += -f5;
			this.Rleg4.rotateAngleY += f6;
			this.Lleg4.rotateAngleY += -f6;
			this.Rleg1.rotateAngleZ += f7;
			this.Lleg1.rotateAngleZ += -f7;
			this.Rleg2.rotateAngleZ += f8;
			this.Lleg2.rotateAngleZ += -f8;
			this.Rleg3.rotateAngleZ += f9;
			this.Lleg3.rotateAngleZ += -f9;
			this.Rleg4.rotateAngleZ += f10;
			this.Lleg4.rotateAngleZ += -f10;
		}
	}

	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTickTime) {
		EntityScorpion entity = (EntityScorpion) entitylivingbaseIn;
		int i = entity.getAttackTimer();

		if (i > 0) {
			this.Rarm1.rotateAngleY = 0.55F * -this.triangleWave((float) i - partialTickTime, 15.0F);
			this.Larm1.rotateAngleY = -0.55F * -this.triangleWave((float) i - partialTickTime, 15.0F);
			this.Rear.rotateAngleX = 1.0F * -this.triangleWaveTail((float) i - partialTickTime, 10.0F);
			this.Stinger1.rotateAngleX = 0.87F;
		} else {
			this.Rarm1.rotateAngleY = 0.5585053606381855F;
			this.Larm1.rotateAngleY = -0.5585053606381855F;
			this.Rear.rotateAngleX = 0.27314402793711257F;
			this.Stinger1.rotateAngleX = 1.9123572614101867F;
		}
	}

	private float triangleWave(float time, float speed) {
		return (Math.abs(time % speed - speed * 0.5F) - speed * 0.73F) / (speed * 0.73F);
	}

	private float triangleWaveTail(float time, float speed) {
		return (Math.abs(time % speed - speed * 0.5F) - speed * 0.25F) / (speed * 0.25F);
	}
}