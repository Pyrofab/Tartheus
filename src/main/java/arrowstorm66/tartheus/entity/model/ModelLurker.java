package arrowstorm66.tartheus.entity.model;

import arrowstorm66.tartheus.entity.EntityLurker;
import arrowstorm66.tartheus.entity.EntityScorpion;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.math.MathHelper;

/**
 * ModelLurker - Arrowstorm66 Created using Tabula 7.0.0
 */
public class ModelLurker extends ModelBase {
	public ModelRenderer Torso;
	public ModelRenderer Chest;
	public ModelRenderer Shoulders;
	public ModelRenderer RightLeg;
	public ModelRenderer LeftLeg;
	public ModelRenderer RightForeleg;
	public ModelRenderer LeftForeleg;
	public ModelRenderer RightArm;
	public ModelRenderer LeftArm;
	public ModelRenderer Jaw;
	public ModelRenderer RightForearm;
	public ModelRenderer LeftForearm;
	public ModelRenderer Head;

	public ModelLurker() {
		this.textureWidth = 68;
		this.textureHeight = 64;
		this.LeftForeleg = new ModelRenderer(this, 53, 16);
		this.LeftForeleg.setRotationPoint(0.0F, 12.0F, -0.1F);
		this.LeftForeleg.addBox(-1.0F, 0.0F, -1.5F, 2, 14, 3, 0.0F);
		this.setRotateAngle(LeftForeleg, 0.10471975511965977F, 0.0F, 0.06981317007977318F);
		this.LeftForearm = new ModelRenderer(this, 58, 50);
		this.LeftForearm.setRotationPoint(0.0F, 11.0F, 0.0F);
		this.LeftForearm.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
		this.setRotateAngle(LeftForearm, -0.4363323129985824F, -0.0F, 0.0F);
		this.Head = new ModelRenderer(this, 0, 0);
		this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Head.addBox(-4.5F, -12.0F, -4.5F, 9, 10, 9, 0.0F);
		this.LeftLeg = new ModelRenderer(this, 53, 0);
		this.LeftLeg.mirror = true;
		this.LeftLeg.setRotationPoint(2.0F, 4.8F, 0.0F);
		this.LeftLeg.addBox(-1.0F, 0.0F, -1.5F, 2, 12, 3, 0.0F);
		this.setRotateAngle(LeftLeg, -0.04363323129985824F, 0.0F, -0.091106186954104F);
		this.Jaw = new ModelRenderer(this, 0, 20);
		this.Jaw.setRotationPoint(0.0F, -1.5F, 0.0F);
		this.Jaw.addBox(-4.0F, -8.5F, -4.0F, 8, 9, 8, 0.0F);
		this.RightLeg = new ModelRenderer(this, 40, 0);
		this.RightLeg.setRotationPoint(-2.0F, 4.8F, 0.0F);
		this.RightLeg.addBox(-1.0F, 0.0F, -1.5F, 2, 12, 3, 0.0F);
		this.setRotateAngle(RightLeg, -0.04363323129985824F, 0.0F, 0.091106186954104F);
		this.RightForeleg = new ModelRenderer(this, 40, 16);
		this.RightForeleg.setRotationPoint(0.0F, 12.0F, -0.1F);
		this.RightForeleg.addBox(-1.0F, 0.0F, -1.5F, 2, 14, 3, 0.0F);
		this.setRotateAngle(RightForeleg, 0.10471975511965977F, 0.0F, -0.06981317007977318F);
		this.RightForearm = new ModelRenderer(this, 48, 50);
		this.RightForearm.setRotationPoint(0.0F, 11.0F, 0.0F);
		this.RightForearm.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
		this.setRotateAngle(RightForearm, -0.4363323129985824F, 0.0F, 0.0F);
		this.Chest = new ModelRenderer(this, 0, 52);
		this.Chest.setRotationPoint(0.0F, 7.6F, 0.0F);
		this.Chest.addBox(-3.5F, 0.0F, -1.5F, 7, 5, 3, 0.0F);
		this.RightArm = new ModelRenderer(this, 48, 34);
		this.RightArm.setRotationPoint(-5.5F, 1.0F, 0.0F);
		this.RightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 12, 2, 0.0F);
		this.setRotateAngle(RightArm, 0.091106186954104F, 0.0F, 0.19198621771937624F);
		this.LeftArm = new ModelRenderer(this, 58, 34);
		this.LeftArm.mirror = true;
		this.LeftArm.setRotationPoint(5.5F, 1.0F, 0.0F);
		this.LeftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 12, 2, 0.0F);
		this.setRotateAngle(LeftArm, 0.091106186954104F, 0.0F, -0.19198621771937624F);
		this.Torso = new ModelRenderer(this, 13, 38);
		this.Torso.setRotationPoint(0.0F, -14.0F, 0.0F);
		this.Torso.addBox(-4.0F, 0.0F, -2.0F, 8, 8, 4, 0.0F);
		this.Shoulders = new ModelRenderer(this, 17, 57);
		this.Shoulders.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.Shoulders.addBox(-5.5F, -1.0F, -2.0F, 11, 3, 4, 0.0F);
		this.LeftLeg.addChild(this.LeftForeleg);
		this.LeftArm.addChild(this.LeftForearm);
		this.Jaw.addChild(this.Head);
		this.Chest.addChild(this.LeftLeg);
		this.Shoulders.addChild(this.Jaw);
		this.Chest.addChild(this.RightLeg);
		this.RightLeg.addChild(this.RightForeleg);
		this.RightArm.addChild(this.RightForearm);
		this.Torso.addChild(this.Chest);
		this.Shoulders.addChild(this.RightArm);
		this.Shoulders.addChild(this.LeftArm);
		this.Torso.addChild(this.Shoulders);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.Torso.render(f5);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		if (((EntityLurker) entityIn).isHostile()) {
			this.Head.offsetY = -0.32f;
		} else {
			this.Head.offsetY = 0f;
		}
		this.Jaw.rotateAngleY = netHeadYaw / 57.295776F;
		this.Jaw.rotateAngleX = headPitch / 57.295776F;
		this.RightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount
				* 0.5F / 1.75F + 0.2f;
		this.LeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / 1.75F + 0.2f;
		this.RightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 1.75F - 0.06f;
		this.LeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount
				/ 1.75F - 0.06f;
	}
}
