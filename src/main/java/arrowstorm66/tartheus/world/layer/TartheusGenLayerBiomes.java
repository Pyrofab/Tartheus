package arrowstorm66.tartheus.world.layer;

import net.minecraft.world.*;
import net.minecraft.world.gen.layer.*;
import net.minecraft.world.biome.*;
import arrowstorm66.tartheus.MBiomes;
import net.minecraft.init.*;

public class TartheusGenLayerBiomes extends GenLayer {
	protected Object[] commonBiomes;
	protected Object[] variantBiomes;
	protected Object[] rareBiomes;

	public TartheusGenLayerBiomes(final long par1, final GenLayer par3GenLayer) {
		super(par1);
		this.commonBiomes = new Biome[] { MBiomes.SHRUBLAND, MBiomes.SHRUBLAND_HILLS, MBiomes.DESERT,
				MBiomes.DESERT_HILLS, MBiomes.SAVANNA, MBiomes.SAVANNA_PLATEAU, MBiomes.BADLANDS,
				MBiomes.BADLANDS_PLATEAU, MBiomes.BONEYARD, MBiomes.BONEYARD_HILLS, MBiomes.VOLCANO, MBiomes.VOLCANO_PLATEAU };
		this.variantBiomes = new Biome[] { MBiomes.BADLANDS_SPIRES, MBiomes.DESERT_SHRUBLAND };
		this.rareBiomes = new Biome[] { MBiomes.LAKE };
		this.parent = par3GenLayer;
	}

	public TartheusGenLayerBiomes(final long par1) {
		super(par1);
		this.commonBiomes = new Biome[] { MBiomes.SHRUBLAND, MBiomes.SHRUBLAND_HILLS, MBiomes.DESERT,
				MBiomes.DESERT_HILLS, MBiomes.SAVANNA, MBiomes.SAVANNA_PLATEAU, MBiomes.BADLANDS,
				MBiomes.BADLANDS_PLATEAU, MBiomes.BONEYARD, MBiomes.BONEYARD_HILLS, MBiomes.VOLCANO, MBiomes.VOLCANO_PLATEAU };
		this.variantBiomes = new Biome[] { MBiomes.BADLANDS_SPIRES, MBiomes.DESERT_SHRUBLAND };
		this.rareBiomes = new Biome[] { MBiomes.LAKE };
	}

	public int[] getInts(final int x, final int z, final int width, final int depth) {
		final int[] dest = IntCache.getIntCache(width * depth);
		for (int dz = 0; dz < depth; ++dz) {
			for (int dx = 0; dx < width; ++dx) {
				this.initChunkSeed((long) (dx + x), (long) (dz + z));
				if (this.nextInt(20) == 0) {
					dest[dx + dz * width] = Biome
							.getIdForBiome((Biome) this.rareBiomes[this.nextInt(this.rareBiomes.length)]);
				} else if (this.nextInt(10) == 0) {
					dest[dx + dz * width] = Biome
							.getIdForBiome((Biome) this.variantBiomes[this.nextInt(this.variantBiomes.length)]);
				} else {
					dest[dx + dz * width] = Biome
							.getIdForBiome((Biome) this.commonBiomes[this.nextInt(this.commonBiomes.length)]);
				}
			}
		}
		return dest;
	}
}
