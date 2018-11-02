package arrowstorm66.tartheus.world.layer;

import arrowstorm66.tartheus.MBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import java.util.Random;

public class TartheusGenLayerRiver extends GenLayer {

	Random rand = new Random();

	public TartheusGenLayerRiver(long l, GenLayer genlayer) {
		super(l);
		super.parent = genlayer;
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {

		int nx = x - 1;
		int nz = z - 1;
		int nwidth = width + 2;
		int ndepth = depth + 2;
		int input[] = parent.getInts(nx, nz, nwidth, ndepth);
		int output[] = IntCache.getIntCache(width * depth);

		int border = Biome.getIdForBiome(MBiomes.RIVER);

		for (int dz = 0; dz < depth; dz++) {
			for (int dx = 0; dx < width; dx++) {

				int left = input[dx + 0 + (dz + 1) * nwidth];
				int right = input[dx + 2 + (dz + 1) * nwidth];
				int down = input[dx + 1 + (dz + 0) * nwidth];
				int up = input[dx + 1 + (dz + 2) * nwidth];
				int mid = input[dx + 1 + (dz + 1) * nwidth];

				int ur = input[dx + 0 + (dz + 0) * nwidth];
				int ul = input[dx + 2 + (dz + 0) * nwidth];
				int dr = input[dx + 0 + (dz + 2) * nwidth];
				int dl = input[dx + 2 + (dz + 2) * nwidth];

				if (createRiver(mid, left, down, right, up)) {
					output[dx + dz * width] = border;
				} else if (createRiver(mid, ur, ul, dr, dl)) {
					output[dx + dz * width] = border;
				} else {
					output[dx + dz * width] = -1;
				}
			}
		}

		return output;
	}

	boolean createRiver(int mid, int left, int down, int right, int up) {
		if (placeRiverBetween(mid, left)) {
			return true;
		} else if (placeRiverBetween(mid, right)) {
			return true;
		} else if (placeRiverBetween(mid, down)) {
			return true;
		} else return placeRiverBetween(mid, up);
	}

	boolean placeRiverBetween(int id1, int id2) {

		if (id1 == id2) {
			return false;
		}
		if (id1 == -id2) {
			return false;
		}

		Biome biome1 = Biome.getBiomeForId(id1);
		Biome biome2 = Biome.getBiomeForId(id2);

		// Badlands
		if (biome1 == MBiomes.BADLANDS && (biome2 == MBiomes.BADLANDS_PLATEAU || biome2 == MBiomes.BADLANDS_SPIRES
				|| biome2 == MBiomes.DESERT || biome2 == MBiomes.DESERT_HILLS || biome2 == MBiomes.DESERT_SHRUBLAND
				|| biome2 == MBiomes.RIVER || biome2 == MBiomes.LAKE)) {
			return false;
		}

		else if (biome1 == MBiomes.BADLANDS_PLATEAU && (biome2 == MBiomes.BADLANDS || biome2 == MBiomes.BADLANDS_SPIRES
				|| biome2 == MBiomes.DESERT || biome2 == MBiomes.DESERT_HILLS || biome2 == MBiomes.DESERT_SHRUBLAND
				|| biome2 == MBiomes.RIVER || biome2 == MBiomes.LAKE)) {
			return false;
		}

		// Desert
		else if (biome1 == MBiomes.DESERT && (biome2 == MBiomes.DESERT_HILLS || biome2 == MBiomes.DESERT_SHRUBLAND
				|| biome2 == MBiomes.BADLANDS || biome2 == MBiomes.BADLANDS_PLATEAU || biome2 == MBiomes.BADLANDS_SPIRES
				|| biome2 == MBiomes.RIVER || biome2 == MBiomes.LAKE)) {
			return false;
		}

		else if (biome1 == MBiomes.DESERT_HILLS && (biome2 == MBiomes.DESERT || biome2 == MBiomes.DESERT_SHRUBLAND
				|| biome2 == MBiomes.BADLANDS || biome2 == MBiomes.BADLANDS_PLATEAU || biome2 == MBiomes.BADLANDS_SPIRES
				|| biome2 == MBiomes.RIVER || biome2 == MBiomes.LAKE)) {
			return false;
		}

		else if (biome1 == MBiomes.BADLANDS_SPIRES && (biome2 == MBiomes.BADLANDS_PLATEAU || biome2 == MBiomes.BADLANDS
				|| biome2 == MBiomes.DESERT || biome2 == MBiomes.DESERT_HILLS || biome2 == MBiomes.DESERT_SHRUBLAND
				|| biome2 == MBiomes.RIVER || biome2 == MBiomes.LAKE)) {
			return false;
		}

		// Shrublands

		else if (biome1 == MBiomes.DESERT_SHRUBLAND && (biome2 == MBiomes.BADLANDS_PLATEAU
				|| biome2 == MBiomes.BADLANDS_SPIRES || biome2 == MBiomes.BADLANDS || biome2 == MBiomes.DESERT
				|| biome2 == MBiomes.DESERT_HILLS || biome2 == MBiomes.RIVER || biome2 == MBiomes.LAKE)) {
			return false;
		}

		else if (biome1 == MBiomes.SHRUBLAND && (biome2 == MBiomes.SHRUBLAND_HILLS || biome2 == MBiomes.RIVER
				|| biome2 == MBiomes.SAVANNA || biome2 == MBiomes.SAVANNA_PLATEAU || biome2 == MBiomes.LAKE)) {
			return false;
		}

		else if (biome1 == MBiomes.SHRUBLAND_HILLS
				&& (biome2 == MBiomes.DESERT_SHRUBLAND || biome2 == MBiomes.SHRUBLAND_HILLS || biome2 == MBiomes.RIVER
						|| biome2 == MBiomes.SAVANNA || biome2 == MBiomes.SAVANNA_PLATEAU || biome2 == MBiomes.LAKE)) {
			return false;
		}

		// Savanna
		else if (biome1 == MBiomes.SAVANNA && (biome2 == MBiomes.SHRUBLAND || biome2 == MBiomes.SHRUBLAND_HILLS
				|| biome2 == MBiomes.SAVANNA_PLATEAU || biome2 == MBiomes.RIVER || biome2 == MBiomes.LAKE)) {
			return false;
		}

		else if (biome1 == MBiomes.SAVANNA_PLATEAU && (biome2 == MBiomes.SHRUBLAND || biome2 == MBiomes.SHRUBLAND_HILLS
				|| biome2 == MBiomes.SAVANNA || biome2 == MBiomes.RIVER || biome2 == MBiomes.LAKE)) {
			return false;
		}

		// Boneyard
		else if (biome1 == MBiomes.BONEYARD && (biome2 == MBiomes.BONEYARD_HILLS || biome2 == MBiomes.VOLCANO
				|| biome2 == MBiomes.VOLCANO_PLATEAU || biome2 == MBiomes.RIVER || biome2 == MBiomes.LAKE)) {
			return false;
		}

		else if (biome1 == MBiomes.BONEYARD_HILLS && (biome2 == MBiomes.BONEYARD || biome2 == MBiomes.VOLCANO
				|| biome2 == MBiomes.VOLCANO_PLATEAU || biome2 == MBiomes.RIVER || biome2 == MBiomes.LAKE)) {
			return false;
		}

		// Volcano
		else if (biome1 == MBiomes.VOLCANO && (biome2 == MBiomes.BONEYARD || biome2 == MBiomes.BONEYARD_HILLS
				|| biome2 == MBiomes.VOLCANO_PLATEAU || biome2 == MBiomes.RIVER || biome2 == MBiomes.LAKE)) {
			return false;
		}

		else if (biome1 == MBiomes.VOLCANO_PLATEAU && (biome2 == MBiomes.BONEYARD || biome2 == MBiomes.BONEYARD_HILLS
				|| biome2 == MBiomes.VOLCANO || biome2 == MBiomes.RIVER || biome2 == MBiomes.LAKE)) {
			return false;
		}

		// Rare
		else return biome1 != MBiomes.LAKE || (biome2 != MBiomes.DESERT && biome2 != MBiomes.DESERT_HILLS
                    && biome2 != MBiomes.SHRUBLAND_HILLS && biome2 != MBiomes.SHRUBLAND
                    && biome2 != MBiomes.DESERT_SHRUBLAND && biome2 != MBiomes.BADLANDS
                    && biome2 != MBiomes.BADLANDS_PLATEAU && biome2 != MBiomes.BADLANDS_SPIRES && biome2 != MBiomes.BONEYARD
                    && biome2 != MBiomes.BONEYARD_HILLS && biome2 != MBiomes.VOLCANO && biome2 != MBiomes.VOLCANO_PLATEAU
                    && biome2 != MBiomes.SAVANNA && biome2 != MBiomes.SAVANNA_PLATEAU && biome2 != MBiomes.RIVER
                    && biome2 != MBiomes.LAKE);

    }
}