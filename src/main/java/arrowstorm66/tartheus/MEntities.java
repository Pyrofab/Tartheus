package arrowstorm66.tartheus;

import java.util.Locale;

import arrowstorm66.tartheus.config.ConfigEntity;
import arrowstorm66.tartheus.entity.EntityLurker;
import arrowstorm66.tartheus.entity.EntityScorpion;
import arrowstorm66.tartheus.entity.EntitySolifugae;
import arrowstorm66.tartheus.entity.EntityVinegar;
import arrowstorm66.tartheus.entity.EntityVinegaroon;
import arrowstorm66.tartheus.entity.render.RenderLurker;
import arrowstorm66.tartheus.entity.render.RenderScorpion;
import arrowstorm66.tartheus.entity.render.RenderSolifugae;
import arrowstorm66.tartheus.entity.render.RenderVinegar;
import arrowstorm66.tartheus.entity.render.RenderVinegaroon;
import arrowstorm66.tartheus.util.BiomesProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MEntities {
	private static int EntityID = 0;

	public static void preInit() {
		EntityRegistry.registerModEntity(new ResourceLocation(Tartheus.MODID, "vinegar"), EntityVinegar.class,
				"vinegar", EntityID++, Tartheus.instance, 128, 8, true);
		EntityRegistry.registerModEntity(new ResourceLocation(Tartheus.MODID, "scorpion"), EntityScorpion.class,
				"scorpion", ++EntityID, Tartheus.instance, 64, 3, true, 0xC18743, 0x7F5E39);
		EntityRegistry.registerModEntity(new ResourceLocation(Tartheus.MODID, "vinegaroon"), EntityVinegaroon.class,
				"vinegaroon", ++EntityID, Tartheus.instance, 64, 3, true, 0x4C3722, 0xC99559);
		EntityRegistry.registerModEntity(new ResourceLocation(Tartheus.MODID, "solifugae"), EntitySolifugae.class,
				"solifugae", ++EntityID, Tartheus.instance, 64, 3, true, 0xD3C1B1, 0xAF7D4D);
		EntityRegistry.registerModEntity(new ResourceLocation(Tartheus.MODID, "lurker"), EntityLurker.class, "lurker",
				++EntityID, Tartheus.instance, 64, 3, true, 0x000000, 0x000000);
		Tartheus.LOGGER.info("MEntities: Entities Birthed!");
	}

	public static void Init() {
		LootTableList.register(EntityScorpion.LOOT);
		LootTableList.register(EntityVinegaroon.LOOT);
		LootTableList.register(EntitySolifugae.LOOT);
		Tartheus.LOGGER.info("MEntities: Entities Looted!");
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		RenderingRegistry.registerEntityRenderingHandler(EntityScorpion.class, RenderScorpion.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityVinegaroon.class, RenderVinegaroon.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntitySolifugae.class, RenderSolifugae.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityLurker.class, RenderLurker.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityVinegar.class, RenderVinegar.FACTORY);
	}
}