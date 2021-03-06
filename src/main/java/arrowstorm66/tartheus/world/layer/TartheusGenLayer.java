package arrowstorm66.tartheus.world.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class TartheusGenLayer extends GenLayer
{
    public static GenLayer[] initializeAllBiomeGenerators(final long par0, final WorldType par2WorldType) {
        GenLayer biomes = new TartheusGenLayerBiomes(1L);
		
        biomes = new GenLayerZoom(1000L, biomes);
        biomes = new GenLayerZoom(1001L, biomes);
        biomes = new TartheusGenLayerStabilizer(700L, biomes);
        biomes = new GenLayerZoom(1002L, biomes);
        biomes = new GenLayerZoom(1003L, biomes);
        biomes = new GenLayerZoom(1004L, biomes);
        biomes = new GenLayerZoom(1005L, biomes);
        
        GenLayer borderLayer = new TartheusGenLayerRiver(1L, biomes);
		borderLayer = new GenLayerSmooth(7000L, borderLayer);
		biomes = new TartheusGenLayerRiverMix(100L, biomes, borderLayer);
		
        biomes = new GenLayerZoom(1006L, biomes);
		
        final GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);
        biomes.initWorldGenSeed(par0);
        genlayervoronoizoom.initWorldGenSeed(par0);
        return new GenLayer[] { biomes, genlayervoronoizoom, biomes };
    }
    
    public TartheusGenLayer(final long par1) {
        super(par1);
    }
    
    public int[] getInts(final int x, final int z, final int width, final int depth) {
        return new int[0];
    }
}