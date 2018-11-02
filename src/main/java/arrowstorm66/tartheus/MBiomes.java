package arrowstorm66.tartheus;

import arrowstorm66.tartheus.world.biomes.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(Tartheus.MODID)
public class MBiomes {

	@ObjectHolder("t_savanna")
	public static final Biome SAVANNA = null;
	@ObjectHolder("t_savanna_plateau")
	public static final Biome SAVANNA_PLATEAU = null;

	@ObjectHolder("t_shrubland")
	public static final Biome SHRUBLAND = null;
	@ObjectHolder("t_shrubland_hills")
	public static final Biome SHRUBLAND_HILLS = null;
	@ObjectHolder("t_desert_shrubland")
	public static final Biome DESERT_SHRUBLAND = null;

	@ObjectHolder("t_desert")
	public static final Biome DESERT = null;
	@ObjectHolder("t_desert_hills")
	public static final Biome DESERT_HILLS = null;

	@ObjectHolder("t_badlands")
	public static final Biome BADLANDS = null;
	@ObjectHolder("t_badlands_plateau")
	public static final Biome BADLANDS_PLATEAU = null;
	@ObjectHolder("t_badlands_spires")
	public static final Biome BADLANDS_SPIRES = null;

	@ObjectHolder("t_boneyard")
	public static final Biome BONEYARD = null;
	@ObjectHolder("t_boneyard_hills")
	public static final Biome BONEYARD_HILLS = null;

	@ObjectHolder("t_volcano")
	public static final Biome VOLCANO = null;
	@ObjectHolder("t_volcano_plateau")
	public static final Biome VOLCANO_PLATEAU = null;

	@ObjectHolder("t_river")
	public static final Biome RIVER = null;
	@ObjectHolder("t_lake")
	public static final Biome LAKE = null;

	@Mod.EventBusSubscriber(modid = Tartheus.MODID)
	public static class BiomeRegistrationHandler {

		@SubscribeEvent
		public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
			final BiomeRegistry biomes = new BiomeRegistry(event.getRegistry());

			// Savanna
			biomes.register(
					new BiomeSavannah(new Biome.BiomeProperties("Savanna").setBaseHeight(0.125F)
							.setHeightVariation(0.05F).setTemperature(1.7F).setRainfall(0.1F))
									.setRegistryName(Tartheus.MODID, "t_savanna"),
					BiomeDictionary.Type.HOT, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.SAVANNA,
					BiomeDictionary.Type.PLAINS);
			biomes.register(
					new BiomeSavannah(new Biome.BiomeProperties("Savanna Plateau").setBaseHeight(1.5F)
							.setHeightVariation(0.025F).setTemperature(1.6F).setRainfall(0.05F))
									.setRegistryName(Tartheus.MODID, "t_savanna_plateau"),
					BiomeDictionary.Type.HOT, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.SAVANNA,
					BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS);

			// Shrubland
			biomes.register(
					new BiomeShrubland(new Biome.BiomeProperties("Shrubland").setHeightVariation(0.05F)
							.setBaseHeight(0.18F).setTemperature(1.2f).setRainfall(0.2f))
									.setRegistryName(Tartheus.MODID, "t_shrubland"),
					BiomeDictionary.Type.DENSE, BiomeDictionary.Type.PLAINS);
			biomes.register(
					new BiomeShrubland(new Biome.BiomeProperties("Shrubland Hills").setBaseHeight(0.45F)
							.setHeightVariation(0.20F).setTemperature(1.2F).setRainfall(0.2F))
									.setRegistryName(Tartheus.MODID, "t_shrubland_hills"),
					BiomeDictionary.Type.DENSE, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS);
			biomes.register(
					new BiomeDesert(true,
							new Biome.BiomeProperties("Desert Shrubland").setHeightVariation(0.05F).setBaseHeight(0.18F)
									.setTemperature(1.8f).setRainfall(0.0f)).setRegistryName(Tartheus.MODID,
											"t_desert_shrubland"),
					BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.WASTELAND,
					BiomeDictionary.Type.HOT, BiomeDictionary.Type.RARE);

			// Desert
			biomes.register(
					new BiomeDesert(false,
							new Biome.BiomeProperties("Desert").setHeightVariation(0.05F).setBaseHeight(0.125F)
									.setTemperature(2.0f).setRainfall(0.0f).setRainDisabled())
											.setRegistryName(Tartheus.MODID, "t_desert"),
					BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.HOT,
					BiomeDictionary.Type.DRY);
			biomes.register(
					new BiomeDesert(false,
							new Biome.BiomeProperties("Desert Hills").setHeightVariation(0.20F).setBaseHeight(0.45F)
									.setTemperature(2.0f).setRainfall(0.0f).setRainDisabled())
											.setRegistryName(Tartheus.MODID, "t_desert_hills"),
					BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.HOT,
					BiomeDictionary.Type.DRY, BiomeDictionary.Type.HILLS);

			// Badlands
			biomes.register(
					new BiomeBadlands(false,
							new Biome.BiomeProperties("Badlands").setTemperature(2.3f).setRainfall(0.0f)
									.setRainDisabled()).setRegistryName(Tartheus.MODID, "t_badlands"),
					BiomeDictionary.Type.MESA, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.HOT,
					BiomeDictionary.Type.DRY);
			biomes.register(
					new BiomeBadlands(false,
							new Biome.BiomeProperties("Badlands Plateau").setHeightVariation(0.025F).setBaseHeight(1.5F)
									.setTemperature(2.3f).setRainfall(0.0f).setRainDisabled())
											.setRegistryName(Tartheus.MODID, "t_badlands_plateau"),
					BiomeDictionary.Type.MESA, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.HOT,
					BiomeDictionary.Type.DRY);
			biomes.register(
					new BiomeBadlands(true,
							new Biome.BiomeProperties("Badlands Spires").setTemperature(2.3f).setRainfall(0.0f)
									.setRainDisabled()).setRegistryName(Tartheus.MODID, "t_badlands_spires"),
					BiomeDictionary.Type.MESA, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.HOT,
					BiomeDictionary.Type.DRY, BiomeDictionary.Type.RARE);

			// Boneyard
			biomes.register(
					new BiomeBoneyard(new Biome.BiomeProperties("Boneyard").setHeightVariation(0.04F)
							.setBaseHeight(0.7F).setTemperature(0.45f).setRainfall(0.3f))
									.setRegistryName(Tartheus.MODID, "t_boneyard"),
					BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.COLD);
			biomes.register(
					new BiomeBoneyard(new Biome.BiomeProperties("Boneyard Hills").setHeightVariation(0.22F)
							.setBaseHeight(0.8F).setTemperature(0.45f).setRainfall(0.3f))
									.setRegistryName(Tartheus.MODID, "t_boneyard_hills"),
					BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.COLD,
					BiomeDictionary.Type.HILLS);

			// Volcano
			biomes.register(
					new BiomeVolcano(new Biome.BiomeProperties("Volcano").setHeightVariation(0.04F)
							.setBaseHeight(0.7F).setTemperature(3.0f).setRainfall(0.0f)).setRegistryName(Tartheus.MODID,
									"t_volcano"),
					BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.DRY, BiomeDictionary.Type.MESA,
					BiomeDictionary.Type.HOT);
			biomes.register(
					new BiomeVolcano(new Biome.BiomeProperties("Volcano Plateau").setHeightVariation(0.08F)
							.setBaseHeight(1.5F).setTemperature(3.0f).setRainfall(0.0f)).setRegistryName(Tartheus.MODID,
									"t_volcano_plateau"),
					BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.DRY, BiomeDictionary.Type.MESA,
					BiomeDictionary.Type.HOT);

			// Water
			biomes.register(
					new BiomeRiver(new Biome.BiomeProperties("River").setHeightVariation(0.05F)
							.setBaseHeight(-1.0F).setTemperature(1.8f).setRainfall(0.1f))
									.setRegistryName(Tartheus.MODID, "t_river"),
					BiomeDictionary.Type.WATER, BiomeDictionary.Type.WET, BiomeDictionary.Type.OCEAN);
			biomes.register(
					new BiomeLake(new Biome.BiomeProperties("Lake").setHeightVariation(0.1F)
							.setBaseHeight(-1.0F).setTemperature(0.8f).setRainfall(0.7f))
									.setRegistryName(Tartheus.MODID, "t_lake"),
					BiomeDictionary.Type.WATER, BiomeDictionary.Type.WET, BiomeDictionary.Type.RARE,
					BiomeDictionary.Type.OCEAN);

			Tartheus.LOGGER.info("Custom Biomes Registered!");
		}

		private static class BiomeRegistry {
			private final IForgeRegistry<Biome> registry;

			BiomeRegistry(final IForgeRegistry<Biome> registry) {
				this.registry = registry;
			}

			public void register(final Biome biome, final BiomeDictionary.Type... biomeTypes) {
				this.registry.register(biome);
				BiomeDictionary.addTypes(biome, biomeTypes);
			}
		}
	}
}