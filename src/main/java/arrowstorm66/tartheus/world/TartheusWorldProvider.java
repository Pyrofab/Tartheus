package arrowstorm66.tartheus.world;

import java.util.Random;

import javax.annotation.Nullable;

import arrowstorm66.tartheus.MBiomes;
import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.MDimensions;
import arrowstorm66.tartheus.config.ConfigDimension;
import arrowstorm66.tartheus.particles.ParticleImmunity;
import arrowstorm66.tartheus.particles.ParticleProtection;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldProvider.WorldSleepResult;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.end.DragonFightManager;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TartheusWorldProvider extends WorldProvider {

	@Override
	public DimensionType getDimensionType() {
		return MDimensions.TARTHEUS;
	}

	@Override
	public void init() {
		this.hasSkyLight = true;
		this.biomeProvider = new TartheusBiomeGenerator(this.world);
		this.setDimension(MDimensions.tartheusDimensionID);
	}

	@Override
	public net.minecraft.world.gen.IChunkGenerator createChunkGenerator() {
		return (net.minecraft.world.gen.IChunkGenerator) new TartheusChunkGenerator(this.world, this.world.getSeed(),
				this.world.getWorldInfo().isMapFeaturesEnabled());
	}

	@Override
	public boolean canRespawnHere() {
		return true;
	}

	@Override
	public String getSaveFolder() {
		return "Tartheus";
	}

	@SideOnly(Side.CLIENT)
	public float getCloudHeight() {
		return 200;
	}

	@Override
	public double getHorizon() {
		return world.getSeaLevel();
	}

	@Override
	public int getAverageGroundLevel() {
		return world.getSeaLevel() + 1;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		int i = (int) (worldTime % 48000L);
		float f = ((float) i + partialTicks) / 48000.0F - 0.25F;

		if (f < 0.0F) {
			++f;
		}

		if (f > 1.0F) {
			--f;
		}

		float f1 = 1.0F - (float) ((Math.cos((double) f * Math.PI) + 1.0D) / 2.0D);
		f = f + (f1 - f) / 3.0F;
		return f;
	}

	@Override
	public int getMoonPhase(long worldTime) {
		return (int) (worldTime / 48000L % 8L + 8L) % 8;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getSkyColor(net.minecraft.entity.Entity cameraEntity, float partialTicks) {
		float f = this.world.getCelestialAngle(partialTicks);
		float f1 = MathHelper.cos(f * ((float) Math.PI * 2F)) * 2.0F + 0.5F;
		f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
		int i = MathHelper.floor(cameraEntity.posX);
		int j = MathHelper.floor(cameraEntity.posY);
		int k = MathHelper.floor(cameraEntity.posZ);
		BlockPos blockpos = new BlockPos(i, j, k);
		int l = net.minecraftforge.client.ForgeHooksClient.getSkyBlendColour(this.world, blockpos);
		float f3 = (float) (l >> 16 & 155) / 255.0F;
		float f4 = (float) (l >> 8 & 155) / 255.0F;
		float f5 = (float) (l & 255) / 255.0F;
		f3 = f3 * f1;
		f4 = f4 * f1;
		f5 = f5 * f1;
		float f6 = this.world.getRainStrength(partialTicks);

		if (f6 > 0.0F) {
			float f7 = (f3 * 0.3F + f4 * 0.59F + f5 * 0.11F) * 0.6F;
			float f8 = 1.0F - f6 * 0.75F;
			f3 = f3 * f8 + f7 * (1.0F - f8);
			f4 = f4 * f8 + f7 * (1.0F - f8);
			f5 = f5 * f8 + f7 * (1.0F - f8);
		}

		float f10 = this.world.getThunderStrength(partialTicks);

		if (f10 > 0.0F) {
			float f11 = (f3 * 0.3F + f4 * 0.59F + f5 * 0.11F) * 0.2F;
			float f9 = 1.0F - f10 * 0.75F;
			f3 = f3 * f9 + f11 * (1.0F - f9);
			f4 = f4 * f9 + f11 * (1.0F - f9);
			f5 = f5 * f9 + f11 * (1.0F - f9);
		}

		if (this.world.getLastLightningBolt() > 0) {
			float f12 = (float) this.world.getLastLightningBolt() - partialTicks;

			if (f12 > 1.0F) {
				f12 = 1.0F;
			}

			f12 = f12 * 0.45F;
			f3 = f3 * (1.0F - f12) + 0.8F * f12;
			f4 = f4 * (1.0F - f12) + 0.8F * f12;
			f5 = f5 * (1.0F - f12) + 1.0F * f12;
		}
		return new Vec3d((double) f3, (double) f4, (double) f5);
	}

	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
		float f = MathHelper.cos(p_76562_1_ * ((float) Math.PI * 2F)) * 2.0F + 0.5F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		float f1 = 0.50F;
		float f2 = 0.65F;
		float f3 = 1.0F;
		f1 = f1 * (f * 0.94F + 0.06F);
		f2 = f2 * (f * 0.94F + 0.06F);
		f3 = f3 * (f * 0.91F + 0.09F);
		return new Vec3d((double) f1, (double) f2, (double) f3);
	}

	// Bed Protection
	@Override
	public WorldSleepResult canSleepAt(net.minecraft.entity.player.EntityPlayer player, BlockPos pos) {
		if (!this.world.isRemote) {
			double d1 = (double) pos.getX() + 0.5;
			double d2 = (double) pos.getY() + 0.7;
			double d3 = (double) pos.getZ() + 0.5;
			ParticleProtection newEffect = new ParticleProtection(world, d1, d2, d3, 35);
			Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
		}
		return WorldSleepResult.DENY;
	}
}