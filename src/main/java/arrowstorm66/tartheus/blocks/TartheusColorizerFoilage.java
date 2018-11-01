package arrowstorm66.tartheus.blocks;

import java.io.IOException;

import javax.annotation.Nonnull;

import arrowstorm66.tartheus.Tartheus;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TartheusColorizerFoilage implements IResourceManagerReloadListener {
	private static int[] colorBufferGrassTartheus = new int[65536];
	private static int[] colorBufferLeavesTartheus = new int[65536];

	private static final ResourceLocation LOC_GRASS_TARTHEUS_PNG = new ResourceLocation(
			"tartheus:textures/colormap/tartheusgrasscolor.png");
	private static final ResourceLocation LOC_LEAVES_TARTHEUS_PNG = new ResourceLocation(
			"tartheus:textures/colormap/tartheusleavescolor.png");

	public static int getBlueGrassColor(double temperature, double humidity) {
		humidity *= temperature;
		int i = (int) ((1.0D - temperature) * 255.0D);
		int j = (int) ((1.0D - humidity) * 255.0D);
		return colorBufferGrassTartheus[j << 8 | i];
	}

	public static int getGrassColorForPos(IBlockAccess blockAccess, BlockPos pos) {
		int i = 0;
		int j = 0;
		int k = 0;

		for (BlockPos.MutableBlockPos mutableblockpos : BlockPos.getAllInBoxMutable(pos.add(-1, 0, -1),
				pos.add(1, 0, 1))) {
			Biome biome = blockAccess.getBiome(mutableblockpos);

			double temp;
			double rainfall;
			int grassColor;

			temp = MathHelper.clamp(biome.getTemperature(pos), 0.0F, 1.0F);
			rainfall = MathHelper.clamp(biome.getRainfall(), 0.0F, 1.0F);
			grassColor = TartheusColorizerFoilage.getBlueGrassColor(temp, rainfall);

			i += (grassColor & 16711680) >> 16;
			j += (grassColor & 65280) >> 8;
			k += grassColor & 255;
		}

		return (i / 9 & 255) << 16 | (j / 9 & 255) << 8 | k / 9 & 255;
	}

	public static int getGrassColorStatic() {
		return TartheusColorizerFoilage.getBlueGrassColor(0.5D, 0.5D);
	}
	
	public static int getLeavesColor(double temperature, double humidity) {
		humidity *= temperature;
		int i = (int) ((1.0D - temperature) * 255.0D);
		int j = (int) ((1.0D - humidity) * 255.0D);
		return colorBufferLeavesTartheus[j << 8 | i];
	}

	public static int getLeavesColorForPos(IBlockAccess blockAccess, BlockPos pos) {
		int i = 0;
		int j = 0;
		int k = 0;

		for (BlockPos.MutableBlockPos mutableblockpos : BlockPos.getAllInBoxMutable(pos.add(-1, 0, -1),
				pos.add(1, 0, 1))) {
			Biome biome = blockAccess.getBiome(mutableblockpos);

			double temp;
			double rainfall;
			int grassColor;

			temp = MathHelper.clamp(biome.getTemperature(pos), 0.0F, 1.0F);
			rainfall = MathHelper.clamp(biome.getRainfall(), 0.0F, 1.0F);
			grassColor = TartheusColorizerFoilage.getLeavesColor(temp, rainfall);

			i += (grassColor & 16711680) >> 16;
			j += (grassColor & 65280) >> 8;
			k += grassColor & 255;
		}

		return (i / 9 & 255) << 16 | (j / 9 & 255) << 8 | k / 9 & 255;
	}

	public static int getLeavesColorStatic() {
		return TartheusColorizerFoilage.getLeavesColor(0.5D, 0.5D);
	}

	@Override
	public void onResourceManagerReload(@Nonnull IResourceManager resourceManager) {
		try {
			colorBufferGrassTartheus = TextureUtil.readImageData(resourceManager, LOC_GRASS_TARTHEUS_PNG);
			colorBufferLeavesTartheus = TextureUtil.readImageData(resourceManager, LOC_LEAVES_TARTHEUS_PNG);
		} catch (IOException e) {
			Tartheus.LOGGER.error(e);
		}
	}
}