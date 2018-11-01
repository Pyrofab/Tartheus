package arrowstorm66.tartheus.world;

import net.minecraft.world.biome.*;
import net.minecraft.world.*;
import net.minecraft.world.gen.layer.*;
import net.minecraft.util.*;
import net.minecraft.crash.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraftforge.event.terraingen.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;

import com.google.common.collect.*;

import arrowstorm66.tartheus.MBiomes;
import arrowstorm66.tartheus.world.layer.TartheusGenLayer;

public class TartheusBiomeGenerator extends BiomeProvider {
	public static List<Biome> allowedBiomes;
	private GenLayer genBiomes;
	private GenLayer biomeIndexLayer;
	private BiomeCache biomeCache;
	private List biomesToSpawnIn;
	private World world;

	protected TartheusBiomeGenerator() {
		this.biomeCache = new BiomeCache((BiomeProvider) this);
		(this.biomesToSpawnIn = new ArrayList()).addAll(TartheusBiomeGenerator.allowedBiomes);
	}

	public TartheusBiomeGenerator(final long par1, final WorldType par3WorldType, final World world) {
		this();
		GenLayer[] agenlayer = TartheusGenLayer.initializeAllBiomeGenerators(par1, par3WorldType);
		agenlayer = this.getModdedBiomeGenerators(par3WorldType, par1, agenlayer);
		this.genBiomes = agenlayer[0];
		this.biomeIndexLayer = agenlayer[1];
		this.world = world;
	}

	public TartheusBiomeGenerator(final World par1World) {
		this(par1World.getSeed(), par1World.getWorldInfo().getTerrainType(), par1World);
	}

	public Biome[] getBiomesForGeneration(Biome[] par1ArrayOfBiomeGenBase, final int par2, final int par3,
			final int par4, final int par5) {
		IntCache.resetIntCache();
		if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5) {
			par1ArrayOfBiomeGenBase = new Biome[par4 * par5];
		}
		final int[] aint = this.genBiomes.getInts(par2, par3, par4, par5);
		try {
			for (int i1 = 0; i1 < par4 * par5; ++i1) {
				par1ArrayOfBiomeGenBase[i1] = Biome.getBiome(aint[i1]);
			}
			return par1ArrayOfBiomeGenBase;
		} catch (Throwable throwable) {
			final CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
			final CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
			crashreportcategory.addCrashSection("biomes[] size", (Object) par1ArrayOfBiomeGenBase.length);
			crashreportcategory.addCrashSection("x", (Object) par2);
			crashreportcategory.addCrashSection("z", (Object) par3);
			crashreportcategory.addCrashSection("w", (Object) par4);
			crashreportcategory.addCrashSection("h", (Object) par5);
			throw new ReportedException(crashreport);
		}
	}

	public Biome[] getBiomes(Biome[] par1ArrayOfBiomeGenBase, final int par2, final int par3, final int par4,
			final int par5, final boolean par6) {
		IntCache.resetIntCache();
		if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5) {
			par1ArrayOfBiomeGenBase = new Biome[par4 * par5];
		}
		if (par6 && par4 == 16 && par5 == 16 && (par2 & 0xF) == 0x0 && (par3 & 0xF) == 0x0) {
			final Biome[] abiomegenbase1 = this.biomeCache.getCachedBiomes(par2, par3);
			System.arraycopy(abiomegenbase1, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
			return par1ArrayOfBiomeGenBase;
		}
		final int[] aint = this.biomeIndexLayer.getInts(par2, par3, par4, par5);
		for (int i1 = 0; i1 < par4 * par5; ++i1) {
			par1ArrayOfBiomeGenBase[i1] = Biome.getBiome(aint[i1]);
		}
		return par1ArrayOfBiomeGenBase;
	}

	public boolean areBiomesViable(final int par1, final int par2, final int par3, final List par4List) {
		IntCache.resetIntCache();
		final int l = par1 - par3 >> 2;
		final int i1 = par2 - par3 >> 2;
		final int j1 = par1 + par3 >> 2;
		final int k1 = par2 + par3 >> 2;
		final int l2 = j1 - l + 1;
		final int i2 = k1 - i1 + 1;
		final int[] aint = this.genBiomes.getInts(l, i1, l2, i2);
		try {
			for (int j2 = 0; j2 < l2 * i2; ++j2) {
				final Biome biomegenbase = Biome.getBiome(aint[j2]);
				if (!par4List.contains(biomegenbase)) {
					return false;
				}
			}
			return true;
		} catch (Throwable throwable) {
			final CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
			final CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
			crashreportcategory.addCrashSection("Layer", (Object) this.genBiomes.toString());
			crashreportcategory.addCrashSection("x", (Object) par1);
			crashreportcategory.addCrashSection("z", (Object) par2);
			crashreportcategory.addCrashSection("radius", (Object) par3);
			crashreportcategory.addCrashSection("allowed", (Object) par4List);
			throw new ReportedException(crashreport);
		}
	}

	public BlockPos findBiomePosition(final int x, final int z, final int range, final List<Biome> biomes,
			final Random random) {
		IntCache.resetIntCache();
		final int l = x - range >> 2;
		final int i1 = z - range >> 2;
		final int j1 = x + range >> 2;
		final int k1 = z + range >> 2;
		final int l2 = j1 - l + 1;
		final int i2 = k1 - i1 + 1;
		final int[] aint = this.genBiomes.getInts(l, i1, l2, i2);
		BlockPos pos = null;
		int j2 = 0;
		for (int k2 = 0; k2 < l2 * i2; ++k2) {
			final int l3 = l + k2 % l2 << 2;
			final int i3 = i1 + k2 / l2 << 2;
			final Biome biomegenbase = Biome.getBiome(aint[k2]);
			if (biomes.contains(biomegenbase)) {
				if (pos == null || random.nextInt(j2 + 1) == 0) {
					pos = new BlockPos(l3, 0, i3);
					++j2;
				}
			}
		}
		return pos;
	}

	public void cleanupCache() {
		super.cleanupCache();
	}

	public GenLayer[] getModdedBiomeGenerators(final WorldType worldType, final long seed, final GenLayer[] original) {
		final WorldTypeEvent.InitBiomeGens event = new WorldTypeEvent.InitBiomeGens(worldType, seed, original);
		MinecraftForge.TERRAIN_GEN_BUS.post((Event) event);
		return event.getNewBiomeGens();
	}

	static {
		TartheusBiomeGenerator.allowedBiomes = (List<Biome>) Lists.newArrayList(
				(Biome[]) new Biome[] { MBiomes.SHRUBLAND, MBiomes.SHRUBLAND_HILLS, MBiomes.DESERT_SHRUBLAND,
						MBiomes.RIVER, MBiomes.DESERT, MBiomes.DESERT_HILLS, MBiomes.SAVANNA, MBiomes.SAVANNA_PLATEAU,
						MBiomes.BADLANDS, MBiomes.BADLANDS_PLATEAU, MBiomes.BADLANDS_SPIRES, MBiomes.BONEYARD,
						MBiomes.BONEYARD_HILLS, MBiomes.VOLCANO, MBiomes.VOLCANO_PLATEAU, MBiomes.LAKE });
	}
}