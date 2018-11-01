package arrowstorm66.tartheus.proxy;

import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.MForgeEvents;
import arrowstorm66.tartheus.MItems;
import arrowstorm66.tartheus.MSounds;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.blocks.TartheusColorizerFoilage;
import arrowstorm66.tartheus.config.ConfigDimension;
import arrowstorm66.tartheus.gui.ScreenEffects;
import arrowstorm66.tartheus.particles.StitcherParticleDanger;
import arrowstorm66.tartheus.particles.StitcherParticleImmunity;
import arrowstorm66.tartheus.particles.StitcherParticleProtection;
import arrowstorm66.tartheus.particles.StitcherParticleSpark;
import arrowstorm66.tartheus.util.CustomMusicTicker;
import arrowstorm66.tartheus.world.TartheusWorldProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends ServerProxy {
	private final Minecraft mc = Minecraft.getMinecraft();
	public static TartheusColorizerFoilage grassColorizer = new TartheusColorizerFoilage();

	public RayTraceResult getMouseOver(double d0) {
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.getRenderViewEntity() != null) {
			if (mc.world != null) {
				float tickPart = MForgeEvents.getPartialTick();
				RayTraceResult objectMouseOver = mc.getRenderViewEntity().rayTrace(d0, tickPart);
				double d1 = d0;
				Vec3d vec3 = mc.getRenderViewEntity().getPositionEyes(tickPart);

				if (objectMouseOver != null) {
					d1 = objectMouseOver.hitVec.distanceTo(vec3);
				}

				Vec3d vec31 = mc.getRenderViewEntity().getLook(tickPart);
				Vec3d vec32 = vec3.addVector(vec31.x * d0, vec31.y * d0, vec31.z * d0);
				Vec3d vec33 = null;
				Entity pointedEntity = null;
				List<Entity> list = mc.world.getEntitiesInAABBexcluding(
						mc.getRenderViewEntity(), mc.getRenderViewEntity().getEntityBoundingBox()
								.expand(vec31.x * d0, vec31.y * d0, vec31.z * d0).grow(1.0D, 1.0D, 1.0D),
						Predicates.and(new Predicate<Entity>() {
							@Override
							public boolean apply(@Nullable Entity input) {
								return input != null && input.canBeCollidedWith();
							}
						}, EntitySelectors.NOT_SPECTATING));
				double d2 = d1;

				for (Entity entity : list) {
					double f2 = entity.getCollisionBorderSize();
					AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow(f2);
					RayTraceResult movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);

					if (axisalignedbb.contains(vec3)) {
						if (d2 >= 0.0D) {
							pointedEntity = entity;
							vec33 = movingobjectposition == null ? vec3 : movingobjectposition.hitVec;
							d2 = 0.0D;
						}
					} else if (movingobjectposition != null) {
						double d3 = vec3.distanceTo(movingobjectposition.hitVec);

						if (d3 < d2 || d2 == 0.0D) {
							if (entity.getLowestRidingEntity() == mc.getRenderViewEntity().getLowestRidingEntity()
									&& !mc.getRenderViewEntity().canRiderInteract()) {
								if (d2 == 0.0D) {
									pointedEntity = entity;
									vec33 = movingobjectposition.hitVec;
								}
							} else {
								pointedEntity = entity;
								vec33 = movingobjectposition.hitVec;
								d2 = d3;
							}
						}
					}
				}

				if (pointedEntity != null && (d2 < d1 || objectMouseOver == null)) {
					objectMouseOver = new RayTraceResult(pointedEntity, vec33);
				}

				return objectMouseOver;
			}
		}
		return null;
	}

	@Override
	public void preInit(final FMLPreInitializationEvent event) {
		super.preInit(event);
		MinecraftForge.EVENT_BUS.register(new StitcherParticleDanger());
		MinecraftForge.EVENT_BUS.register(new StitcherParticleSpark());
		MinecraftForge.EVENT_BUS.register(new StitcherParticleImmunity());
		MinecraftForge.EVENT_BUS.register(new StitcherParticleProtection());
		((IReloadableResourceManager) mc.getResourceManager()).registerReloadListener(grassColorizer);
	}

	@Override
	public void init(final FMLInitializationEvent event) {
		super.init(event);

		final BlockColors blockColors = mc.getBlockColors();
		final ItemColors itemColors = mc.getItemColors();

		blockColors.registerBlockColorHandler(new IBlockColor() {
			@Override
			public int colorMultiplier(@Nonnull IBlockState state, IBlockAccess access, BlockPos pos, int tintIndex) {
				{
					if (pos == null) {
						return TartheusColorizerFoilage.getGrassColorStatic();
					}

					return TartheusColorizerFoilage.getGrassColorForPos(access, pos);
				}
			}
		}, MBlocks.TARTHEUS_GRASS, MBlocks.TARTHEUS_TALLGRASS, MBlocks.TARTHEUS_SHORTGRASS);
		itemColors.registerItemColorHandler(new IItemColor() {
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				return TartheusColorizerFoilage.getGrassColorStatic();
			}
		}, MBlocks.TARTHEUS_GRASS, MBlocks.TARTHEUS_TALLGRASS, MBlocks.TARTHEUS_SHORTGRASS);

		blockColors.registerBlockColorHandler(new IBlockColor() {
			@Override
			public int colorMultiplier(@Nonnull IBlockState state, IBlockAccess access, BlockPos pos, int tintIndex) {
				if (pos == null) {
					return TartheusColorizerFoilage.getLeavesColorStatic();
				}

				return TartheusColorizerFoilage.getLeavesColorForPos(access, pos);
			}
		}, MBlocks.ALDER_LEAVES, MBlocks.ALDER_SAPLING, MBlocks.BARRENWOOD_LEAVES, MBlocks.BARRENWOOD_SAPLING);
		itemColors.registerItemColorHandler(new IItemColor() {
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				return TartheusColorizerFoilage.getLeavesColorStatic();
			}
		}, MBlocks.ALDER_LEAVES, MBlocks.BARRENWOOD_LEAVES);
	}

	@Override
	public void postInit(final FMLPostInitializationEvent event) {
		super.postInit(event);
		this.overrideMusic();
		CustomMusicTicker
				.register((CustomMusicTicker.MusicEntry) new CustomMusicTicker.MusicEntry(MSounds.MUSIC_ENCLOSE_DAY,
						12000, 24000) {
					public boolean shouldSelect(final Minecraft mc) {
						return mc.world.provider instanceof TartheusWorldProvider && !MForgeEvents.isNighttime()
								&& ConfigDimension.isMusicenabled == true;
					}
				});
		CustomMusicTicker
				.register((CustomMusicTicker.MusicEntry) new CustomMusicTicker.MusicEntry(MSounds.MUSIC_ENCLOSE_NIGHT,
						12000, 24000) {
					public boolean shouldSelect(final Minecraft mc) {
						return mc.world.provider instanceof TartheusWorldProvider && MForgeEvents.isNighttime()
								&& ConfigDimension.isMusicenabled == true;
					}
				});
	}

	private void overrideMusic() {
		try {
			Tartheus.LOGGER.info("Trying to register Tartheus MusicTicker..");
			final Class minecraft = this.mc.getClass();
			Field musicTicker = null;
			try {
				musicTicker = minecraft.getDeclaredField("mcMusicTicker");
			} catch (NoSuchFieldException nsfe) {
				try {
					musicTicker = minecraft.getDeclaredField("field_147126_aw");
				} catch (NoSuchFieldException nsfe2) {
					throw new Exception("Failed to locate Minecraft.mcMusicTicker. Maybe the obfuscated name changed?");
				}
			}
			musicTicker.setAccessible(true);
			musicTicker.set(this.mc, new CustomMusicTicker(this.mc));
			Tartheus.LOGGER.info("Success! Minecraft.mcMusicTicker = " + this.mc.getMusicTicker());
		} catch (Throwable t) {
			Tartheus.LOGGER.warn("Failure. Reason: " + t.getLocalizedMessage());
			t.printStackTrace();
			Tartheus.LOGGER.info("Tartheus music will not be available.");
		}
	}

	@Override
	protected void registerEventListeners() {
		super.registerEventListeners();
		MinecraftForge.EVENT_BUS.register((Object) this);
	}

	@Override
	public void mouseClick() {
		final Minecraft mc = Minecraft.getMinecraft();
		if (mc.objectMouseOver != null && !mc.player.isRowingBoat()) {
			switch (mc.objectMouseOver.typeOfHit) {
			case ENTITY: {
				mc.playerController.attackEntity((EntityPlayer) mc.player, mc.objectMouseOver.entityHit);
				break;
			}
			case BLOCK:
			case MISS: {
				mc.player.resetCooldown();
				ForgeHooks.onEmptyLeftClick((EntityPlayer) mc.player);
				break;
			}
			}
			mc.player.swingArm(EnumHand.MAIN_HAND);
		}
	}

	@Override
	public void displayMobScreen(final int ticks, final String screen) {
		ScreenEffects.showTicks = ticks;
		ScreenEffects.image = screen;
	}
}