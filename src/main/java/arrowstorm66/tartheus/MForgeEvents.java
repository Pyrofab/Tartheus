package arrowstorm66.tartheus;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import arrowstorm66.tartheus.MItems;
import arrowstorm66.tartheus.base.BasicBlock;
import arrowstorm66.tartheus.base.BasicItemTool;
import arrowstorm66.tartheus.base.entity.EntityTartheus;
import arrowstorm66.tartheus.base.gear.BasicItemMeleeWeapon;
import arrowstorm66.tartheus.base.gear.BasicItemShield;
import arrowstorm66.tartheus.base.gear.ICriticalStrike;
import arrowstorm66.tartheus.base.gear.IReach;
import arrowstorm66.tartheus.config.ConfigDimension;
import arrowstorm66.tartheus.config.ConfigEntity;
import arrowstorm66.tartheus.config.ConfigMisc;
import arrowstorm66.tartheus.entity.EntityScorpion;
import arrowstorm66.tartheus.entity.EntitySolifugae;
import arrowstorm66.tartheus.entity.EntityVinegaroon;
import arrowstorm66.tartheus.gui.GuiHandler;
import arrowstorm66.tartheus.packets.PacketWeaponOverhaul;
import arrowstorm66.tartheus.packets.PacketWeaponReach;
import arrowstorm66.tartheus.particles.ParticleImmunity;
import arrowstorm66.tartheus.particles.ParticleProtection;
import arrowstorm66.tartheus.particles.ParticleSpawner;
import arrowstorm66.tartheus.proxy.ClientProxy;
import arrowstorm66.tartheus.util.TartheusCaveAmbience;
import arrowstorm66.tartheus.util.ToggleAnimation;
import arrowstorm66.tartheus.world.TartheusWorldProvider;
import arrowstorm66.tartheus.world.biomes.BiomeDesert;
import arrowstorm66.tartheus.world.biomes.BiomeShrubland;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIZombieAttack;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent.OverlayType;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.Type;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MForgeEvents {

	private static final MForgeEvents INSTANCE = new MForgeEvents();
	private static Minecraft mc = FMLClientHandler.instance().getClient();
	private static Random rand = new Random();
	private static boolean nighttime = false;
	private static float partialTick;
	private static long lastAmbienceTime;
	private static final ISound CAVE_IDLE_SOUND = (ISound) new TartheusCaveAmbience();
	public static final ToggleAnimation CAVE_ANIMATION = new ToggleAnimation(20);
	
	public static boolean isNighttime() {
		return nighttime;
	}

	@SubscribeEvent
	public void onNighttime(TickEvent.WorldTickEvent evt) {
		if (evt.world.provider instanceof TartheusWorldProvider) {
			if ((evt.world.getWorldTime() % 48000L > 47000L)
					|| (evt.world.getWorldTime() % 48000L < 28000L) && (nighttime)) {
				nighttime = false;
			}
			if ((!evt.world.isRemote) && (evt.phase == TickEvent.Phase.END)
					&& (evt.world.getWorldTime() % 48000L == 28000L)) {
				nighttime = true;
			}
			if ((!evt.world.isRemote) && (evt.phase == TickEvent.Phase.END)
					&& (evt.world.getWorldTime() % 48000L == 47000L) && (nighttime)) {
				nighttime = false;
			}
		}
	}

	// TNT Protection
	@SubscribeEvent
	public void protectTartheusFromTNT(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityTNTPrimed
				&& event.getWorld().provider.getDimension() == ConfigDimension.TartheusID) {
			if (!event.getWorld().isRemote) {
				double d1 = (double) event.getEntity().posX;
				double d2 = (double) (event.getEntity().posY + event.getEntity().height + 0.1);
				double d3 = (double) event.getEntity().posZ;
				ParticleProtection newEffect = new ParticleProtection(event.getWorld(), d1, d2, d3, 35);
				Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
			}
			event.getEntity().setDead();
		}
	}

	// Tool Protection
	@SubscribeEvent
	public void protectTartheusFromTool(BlockEvent.BreakEvent event) {
		if ((isPlayerHarvestingWithVanillaTool(event.getState(), event.getWorld(), event.getPos(), event.getPlayer()))
				|| (isPlayerHarvestingWithCorrectTool(event.getState(), event.getWorld(), event.getPos(),
						event.getPlayer()))) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void slowToolinTartheus(net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed event) {
		if ((isPlayerHarvestingWithVanillaTool(event.getState(), event.getEntityPlayer().getEntityWorld(),
				event.getPos(), event.getEntityPlayer()))
				|| (isPlayerHarvestingWithCorrectTool(event.getState(), event.getEntityPlayer().getEntityWorld(),
						event.getPos(), event.getEntityPlayer()))) {
			if (!event.getEntityPlayer().getEntityWorld().isRemote && rand.nextInt(10) == 0) {
				ParticleImmunity newEffect = new ParticleImmunity(event.getEntityPlayer().getEntityWorld(),
						event.getPos().getX() + 0.5, event.getPos().getY() + 1.1, event.getPos().getZ() + 0.5, 35);
				Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
			}
			event.setCanceled(true);
		}
	}

	private boolean isPlayerHarvestingWithVanillaTool(IBlockState state, IBlockAccess blockAccess, BlockPos pos,
			EntityPlayer player) {
		Item tool = player.getHeldItemMainhand().getItem();
		return player != null && tool != null && !player.capabilities.isCreativeMode
				&& state.getBlock() instanceof BasicBlock && tool instanceof ItemTool;
	}

	private boolean isPlayerHarvestingWithCorrectTool(IBlockState state, IBlockAccess blockAccess, BlockPos pos,
			EntityPlayer player) {
		String tool = state.getBlock().getHarvestTool(state);
		return player != null && !player.capabilities.isCreativeMode && state.getBlock() instanceof BasicBlock
				&& !(player.getHeldItemMainhand() != null && tool != null
						&& (player.getHeldItemMainhand().getItem().getHarvestLevel(player.getHeldItemMainhand(), tool,
								player, state) >= state.getBlock().getHarvestLevel(state))
						|| state.getBlock().getHarvestLevel(state) == 0);
	}

	@SubscribeEvent
	public void onAmbienceTick(final TickEvent.ClientTickEvent event) {
		if (!MForgeEvents.mc.isGamePaused()) {
			final EntityPlayer player = (EntityPlayer) MForgeEvents.mc.player;
			if (player == null) {
				return;
			}
			if (event.phase == TickEvent.Phase.END) {
				if (player.world.provider.getDimensionType() == MDimensions.TARTHEUS) {
					playAmbientSounds(player);
				}
			}
		}
	}

	private static void playAmbientSounds(final EntityPlayer player) {
		BlockPos blockpos = new BlockPos(player.getPosition().getX(), 0, player.getPosition().getZ());
		final Biome biome = player.world.getBiome(blockpos.add(16, 0, 16));
		final Random rand = player.world.rand;
		final long worldTime = player.world.getTotalWorldTime();
		if (worldTime - MForgeEvents.lastAmbienceTime > 120L && rand.nextInt(500) == 0) {
			ResourceLocation ambientSound = null;

			if (player.posY > 88.0 && player.world.canSeeSky(player.getPosition())) {
				if (!MForgeEvents.isNighttime()) {
					if (biome == MBiomes.SHRUBLAND || biome == MBiomes.SHRUBLAND_HILLS || biome == MBiomes.SAVANNA
							|| biome == MBiomes.SAVANNA_PLATEAU) {
						ambientSound = MSounds.BIRDS_AMBIENT.getSoundName();
					}
				} else if (MForgeEvents.isNighttime()) {
					ambientSound = MSounds.NIGHT_AMBIENT.getSoundName();
				}
			} else if (rand.nextInt(100) == 0) {
				ambientSound = MSounds.CAVE_SOUND.getSoundName();
			}

			if (ambientSound != null) {
				final float volume = rand.nextFloat() * 0.4f + 0.8f;
				final float pitch = rand.nextFloat() * 0.6f + 0.7f;
				final float x = (float) (player.posX + rand.nextFloat() - 0.5);
				final float y = (float) (player.posY + rand.nextFloat() - 0.5);
				final float z = (float) (player.posZ + rand.nextFloat() - 0.5);
				final ISound sound = (ISound) new PositionedSoundRecord(ambientSound, SoundCategory.AMBIENT, volume,
						pitch, false, 0, ISound.AttenuationType.NONE, x, y, z);
				MForgeEvents.mc.getSoundHandler().playSound(sound);
				MForgeEvents.lastAmbienceTime = worldTime;
			}
		}
	}

	@SubscribeEvent
	public void playIdleSounds(final TickEvent.ClientTickEvent event) {
		if (!MForgeEvents.mc.isGamePaused()) {
			final EntityPlayer player = (EntityPlayer) MForgeEvents.mc.player;
			if (player == null || event.phase == TickEvent.Phase.START) {
				return;
			}
			if (player.world.provider.getDimensionType() == MDimensions.TARTHEUS) {
				CAVE_ANIMATION.set(player.posY < 88.0 && !player.world.canSeeSky(player.getPosition()));
				CAVE_ANIMATION.update();
				continueIdleSound(CAVE_IDLE_SOUND);
			}
		}
	}

	private static void continueIdleSound(final ISound sound) {
		final SoundHandler soundHandler = MForgeEvents.mc.getSoundHandler();
		if (!soundHandler.isSoundPlaying(sound)) {
			soundHandler.stopSound(sound);
			try {
				soundHandler.playSound(sound);
			} catch (IllegalArgumentException ex) {
			}
		}
	}

	@SubscribeEvent
	public void burningTime(final FurnaceFuelBurnTimeEvent event) {
		final int i = this.getBurnTime(event.getItemStack());
		if (i > 0) {
			event.setBurnTime(i);
		}
	}

	private int getBurnTime(final ItemStack fuel) {
		final Item item = fuel.getItem();
		if (item == Item.getItemFromBlock(MBlocks.ASH)) {
			return 100;
		}
		if (item == MItems.ASHES) {
			return 70;
		}
		if (item == MItems.TARTHEUS_STICK) {
			return 100;
		}
		if ((item == Item.getItemFromBlock(MBlocks.ALDER_SAPLING))
				|| (item == Item.getItemFromBlock(MBlocks.BARRENWOOD_SAPLING))) {
			return 100;
		}
		return -1;
	}

	@SubscribeEvent
	public void onPlayerEveryLogin(PlayerEvent.PlayerLoggedInEvent event) {

		event.player.sendMessage(
				new TextComponentString(TextFormatting.GOLD + event.player.getDisplayName().getFormattedText()
						+ TextFormatting.YELLOW + ", Thank you for downloading Tartheus!"));

		ClickEvent newsLink = new ClickEvent(ClickEvent.Action.OPEN_URL,
				"https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/2930737-tartheus");
		Style clickableNewsLink = new Style().setClickEvent(newsLink);
		TextComponentString newsLinkChatComponent = new TextComponentString(
				TextFormatting.GREEN + "If you want to leave me a comment, " + TextFormatting.DARK_GREEN
						+ "[CLICK HERE!]\n" + TextFormatting.GREEN + "to visit our thread on Minecraftforum.net");
		newsLinkChatComponent.setStyle(clickableNewsLink);
		event.player.sendMessage(newsLinkChatComponent);

		ClickEvent discordLink = new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/xRdGTTH");
		Style clickableDiscordLink = new Style().setClickEvent(discordLink);
		TextComponentString discordLinkChatComponent = new TextComponentString(
				TextFormatting.AQUA + "Want to discuss the mod with others? " + TextFormatting.DARK_AQUA
						+ "[CLICK HERE!]\n" + TextFormatting.AQUA + "to join our Discord.");
		discordLinkChatComponent.setStyle(clickableDiscordLink);
		event.player.sendMessage(discordLinkChatComponent);
	}

	@SubscribeEvent
	public void onBloodSpawn(final LivingHurtEvent event) {
		if (ConfigMisc.isBloodenabled == true) {
			if (event.getEntity() instanceof EntityLiving && event.getSource().damageType != "inFire"
					&& event.getSource().damageType != "onFire" && event.getSource().damageType != "lightningBolt"
					&& event.getSource().damageType != "lava" && event.getSource().damageType != "hotFloor"
					&& event.getSource().damageType != "cactus" && event.getSource().damageType != "cramming"
					&& event.getSource().damageType != "drown" && event.getSource().damageType != "starve"
					&& event.getSource().damageType != "outOfWorld" && event.getSource().damageType != "magic"
					&& event.getSource().damageType != "wither" && event.getSource().damageType != "dragonBreath"
					&& event.getSource().damageType != "fireworks" && !(event.getEntity() instanceof EntitySkeleton)
					&& !(event.getEntity() instanceof EntitySlime) && !(event.getEntity() instanceof EntityBlaze)
					&& !(event.getEntity() instanceof EntityCreeper) && !(event.getEntity() instanceof EntityVex)
					&& !(event.getEntity() instanceof EntityWitherSkeleton)
					&& !(event.getEntity() instanceof EntityWither) && !(event.getEntity() instanceof EntitySpider)
					&& !(event.getEntity() instanceof EntityCaveSpider) && !(event.getEntity() instanceof EntityDragon)
					&& !(event.getEntity() instanceof EntityEndermite) && !(event.getEntity() instanceof EntityShulker)
					&& !(event.getEntity() instanceof EntityEnderman) && !(event.getEntity() instanceof EntitySnowman)
					&& !(event.getEntity() instanceof EntityIronGolem) && !(event.getEntity() instanceof EntityStray)
					&& !(event.getEntity() instanceof EntityHusk) && !(event.getEntity() instanceof EntitySquid)
					&& !(event.getEntity() instanceof EntityScorpion)
					&& !(event.getEntity() instanceof EntityVinegaroon)
					&& !(event.getEntity() instanceof EntitySolifugae)) {
				ParticleSpawner.spawnBloodGush(event.getEntity().world, event.getEntity().posX,
						event.getEntity().posY + (event.getEntity().height / 2), event.getEntity().posZ, 1,
						(int) (3 + (event.getAmount() / 3)), 133, 0, 0);
			}
			if ((event.getEntity() instanceof EntityScorpion) || (event.getEntity() instanceof EntityVinegaroon)
					|| (event.getEntity() instanceof EntitySolifugae) || (event.getEntity() instanceof EntitySpider)
					|| (event.getEntity() instanceof EntityCaveSpider) || (event.getEntity() instanceof EntitySquid)) {
				ParticleSpawner.spawnSpiderBloodGush(event.getEntity().world, event.getEntity().posX,
						event.getEntity().posY + (event.getEntity().height / 2), event.getEntity().posZ, 1,
						(int) (3 + (event.getAmount() / 3)), 0, 0, 133);
			}
			if ((event.getEntity() instanceof EntityDragon) || (event.getEntity() instanceof EntityEndermite)
					|| (event.getEntity() instanceof EntityEnderman) || (event.getEntity() instanceof EntityShulker)) {
				ParticleSpawner.spawnEnderBloodGush(event.getEntity().world, event.getEntity().posX,
						event.getEntity().posY + (event.getEntity().height / 2), event.getEntity().posZ, 1,
						(int) (3 + (event.getAmount() / 3)), 133, 0, 133);
			}
			if (event.getEntity() instanceof EntityCreeper) {
				ParticleSpawner.spawnGunpowderGush(event.getEntity().world, event.getEntity().posX,
						event.getEntity().posY + (event.getEntity().height / 2), event.getEntity().posZ, 1,
						(int) (3 + (event.getAmount() / 3)), 65, 65, 65);
			}
			if (event.getEntity() instanceof EntitySkeleton || event.getEntity() instanceof EntitySkeletonHorse
					|| event.getEntity() instanceof EntityStray) {
				ParticleSpawner.spawnPartSkeleton(event.getEntity().world, event.getEntity().posX,
						event.getEntity().posY + (event.getEntity().height / 2), event.getEntity().posZ, 1,
						(int) (3 + (event.getAmount() / 3)), 255, 255, 255);
			}
			if (event.getEntity() instanceof EntityWitherSkeleton || event.getEntity() instanceof EntityWither) {
				ParticleSpawner.spawnPartSkeleton(event.getEntity().world, event.getEntity().posX,
						event.getEntity().posY + (event.getEntity().height / 2), event.getEntity().posZ, 1,
						(int) (3 + (event.getAmount() / 3)), 5, 5, 5);
			}
			if (event.getEntity() instanceof EntitySnowman) {
				ParticleSpawner.spawnSnowGush(event.getEntity().world, event.getEntity().posX,
						event.getEntity().posY + (event.getEntity().height / 2), event.getEntity().posZ, 1,
						(int) (3 + (event.getAmount() / 3)), 155, 155, 255);
			}
		}
	}

	@SubscribeEvent
	public void attackEntityWithReach(AttackEntityEvent event) {
		double reachMod = maxReachDistance(event.getEntityPlayer());
		if (reachMod < event.getEntityPlayer().getDistance(event.getTarget())) {
			event.setCanceled(true);
		}
	}

	public double maxReachDistance(EntityPlayer player) {
		if (player != null) {
			ItemStack itemstack = player.getHeldItemMainhand();
			IReach ieri;
			if (itemstack != null) {
				if (itemstack.getItem() instanceof IReach) {
					ieri = (IReach) itemstack.getItem();
				} else {
					ieri = null;
				}

				if (ieri != null) {
					float reachMod = ieri.getReach();
					ItemStack itemStack = player.getHeldItemMainhand();
					if (!itemStack.isEmpty()) {
						reachMod -= -1.0;
					}
					return reachMod + 4.5;
				}
			}
		}
		return 4.5;
	}

	@SubscribeEvent
	public void attackingTickWithReach(TickEvent.ClientTickEvent event) {
		if (mc.player != null && mc.world != null && mc.currentScreen == null) {
			EntityPlayer player = mc.player;
			if (event.phase == TickEvent.Phase.START) {
				if (player.swingProgressInt == 1 && !player.isSpectator()) {
					double extendedReach = maxReachDistance(player);
					if (extendedReach > 4.5) {
						RayTraceResult mouseOver = Tartheus.proxy.getMouseOver(extendedReach);
						if (mouseOver != null && mouseOver.typeOfHit == RayTraceResult.Type.ENTITY) {
							Entity target = mouseOver.entityHit;
							if (target instanceof EntityLivingBase && target != player
									&& player.getDistance(target) > mc.playerController.getBlockReachDistance()) {
								if (target.hurtResistantTime != ((EntityLivingBase) target).maxHurtResistantTime) {
									player.attackTargetEntityWithCurrentItem(target);
									Tartheus.network.sendToServer(new PacketWeaponReach(target.getEntityId()));
								}
							}
						}
					}
				}
			}
		}
	}

	public static float getPartialTick() {
		return INSTANCE.partialTick;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onTartheusWeaponUse(final MouseEvent event) {
		if (event.getButton() == 0 && event.isButtonstate()) {
			final Minecraft mc = Minecraft.getMinecraft();
			final EntityPlayer thePlayer = (EntityPlayer) mc.player;
			final RayTraceResult mov = Tartheus.proxy.getMouseOver(4.5f);
			if (mov != null && mov.entityHit != null && mov.entityHit != thePlayer
					&& thePlayer.getHeldItemMainhand().getItem() instanceof BasicItemMeleeWeapon) {
				thePlayer.attackTargetEntityWithCurrentItem(mov.entityHit);
				Tartheus.network.sendToServer((IMessage) new PacketWeaponOverhaul(mov.entityHit.getEntityId()));
			}
		}
	}

	public static void attackWithTartheusWeapon(final EntityPlayer player, final Entity targetEntity) {
		if (!net.minecraftforge.common.ForgeHooks.onPlayerAttackTarget(player, targetEntity))
			return;
		if (targetEntity.canBeAttackedWithItem()) {
			if (!targetEntity.hitByEntity(player)) {
				float f = (float) player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
				float f1;

				if (targetEntity instanceof EntityLivingBase) {
					f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(),
							((EntityLivingBase) targetEntity).getCreatureAttribute());
				} else {
					f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(),
							EnumCreatureAttribute.UNDEFINED);
				}

				float f2 = player.getCooledAttackStrength(0.5F);
				f = f * (0.1F + f2 * f2 * 0.9F);
				// Vanilla--- f = f * (0.2F + f2 * f2 * 0.8F);
				f1 = f1 * f2;
				player.resetCooldown();

				if (f > 0.0F || f1 > 0.0F) {
					boolean flag = f2 > 0.9F;
					boolean flag1 = false;
					int i = 0;
					i = i + EnchantmentHelper.getKnockbackModifier(player);

					if (player.isSprinting() && flag) {
						player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ,
								SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, player.getSoundCategory(), 1.0F, 1.0F);
						++i;
						flag1 = true;
					}

					ItemStack itemstack2 = player.getHeldItemMainhand();
					ICriticalStrike ieri;
					if (itemstack2 != null) {
						if (itemstack2.getItem() instanceof ICriticalStrike) {
							ieri = (ICriticalStrike) itemstack2.getItem();
						} else {
							ieri = null;
						}

						if (ieri != null) {
							float critChance = ieri.getCriticalStrikeChance();

							boolean flag2 = (player.getRNG().nextFloat() < critChance);
							flag2 = flag2 && !player.isSprinting();

							net.minecraftforge.event.entity.player.CriticalHitEvent hitResult = net.minecraftforge.common.ForgeHooks
									.getCriticalHit(player, targetEntity, flag2, flag2 ? 2.0F : 1.0F);
							flag2 = hitResult != null;
							if (flag2) {
								f *= hitResult.getDamageModifier();
							}

							f = f + f1;
							boolean flag3 = false;
							double d0 = (double) (player.distanceWalkedModified - player.prevDistanceWalkedModified);

							if (flag && !flag2 && !flag1 && player.onGround && d0 < (double) player.getAIMoveSpeed()) {
								ItemStack itemstack = player.getHeldItem(EnumHand.MAIN_HAND);

								if (itemstack.getItem() instanceof BasicItemMeleeWeapon) {
									flag3 = true;
								}
							}

							float f4 = 0.0F;
							boolean flag4 = false;
							int j = EnchantmentHelper.getFireAspectModifier(player);

							if (targetEntity instanceof EntityLivingBase) {
								f4 = ((EntityLivingBase) targetEntity).getHealth();

								if (j > 0 && !targetEntity.isBurning()) {
									flag4 = true;
									targetEntity.setFire(1);
								}
							}

							double d1 = targetEntity.motionX;
							double d2 = targetEntity.motionY;
							double d3 = targetEntity.motionZ;
							boolean flag5 = targetEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), f);

							if (flag5) {
								if (i > 0) {
									if (targetEntity instanceof EntityLivingBase) {
										((EntityLivingBase) targetEntity).knockBack(player, (float) i * 0.5F,
												(double) MathHelper.sin(player.rotationYaw * 0.017453292F),
												(double) (-MathHelper.cos(player.rotationYaw * 0.017453292F)));
									} else {
										targetEntity.addVelocity(
												(double) (-MathHelper.sin(player.rotationYaw * 0.017453292F) * (float) i
														* 0.5F),
												0.1D, (double) (MathHelper.cos(player.rotationYaw * 0.017453292F)
														* (float) i * 0.5F));
									}

									player.motionX *= 0.6D;
									player.motionZ *= 0.6D;
									player.setSprinting(false);
								}

								if (flag3) {
									float f3 = 1.0F + EnchantmentHelper.getSweepingDamageRatio(player) * f;

									for (EntityLivingBase entitylivingbase : player.world.getEntitiesWithinAABB(
											EntityLivingBase.class,
											targetEntity.getEntityBoundingBox().grow(1.0D, 0.25D, 1.0D))) {
										if (entitylivingbase != player && entitylivingbase != targetEntity
												&& !player.isOnSameTeam(entitylivingbase)
												&& player.getDistanceSq(entitylivingbase) < 9.0D) {
											entitylivingbase.knockBack(player, 0.4F,
													(double) MathHelper.sin(player.rotationYaw * 0.017453292F),
													(double) (-MathHelper.cos(player.rotationYaw * 0.017453292F)));
											entitylivingbase.attackEntityFrom(DamageSource.causePlayerDamage(player),
													f3);
										}
									}

									player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ,
											SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F,
											1.0F);
									player.spawnSweepParticles();
								}

								if (targetEntity instanceof EntityPlayerMP && targetEntity.velocityChanged) {
									((EntityPlayerMP) targetEntity).connection
											.sendPacket(new SPacketEntityVelocity(targetEntity));
									targetEntity.velocityChanged = false;
									targetEntity.motionX = d1;
									targetEntity.motionY = d2;
									targetEntity.motionZ = d3;
								}

								if (flag2) {
									player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ,
											SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, player.getSoundCategory(), 1.0F,
											1.0F);
									player.onCriticalHit(targetEntity);
								}

								if (!flag2 && !flag3) {
									if (flag) {
										player.world.playSound((EntityPlayer) null, player.posX, player.posY,
												player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG,
												player.getSoundCategory(), 1.0F, 1.0F);
									} else {
										player.world.playSound((EntityPlayer) null, player.posX, player.posY,
												player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_WEAK,
												player.getSoundCategory(), 1.0F, 1.0F);
									}
								}

								if (f1 > 0.0F) {
									player.onEnchantmentCritical(targetEntity);
								}

								player.setLastAttackedEntity(targetEntity);

								if (targetEntity instanceof EntityLivingBase) {
									EnchantmentHelper.applyThornEnchantments((EntityLivingBase) targetEntity, player);
								}

								EnchantmentHelper.applyArthropodEnchantments(player, targetEntity);
								ItemStack itemstack1 = player.getHeldItemMainhand();
								Entity entity = targetEntity;

								if (targetEntity instanceof MultiPartEntityPart) {
									IEntityMultiPart ientitymultipart = ((MultiPartEntityPart) targetEntity).parent;

									if (ientitymultipart instanceof EntityLivingBase) {
										entity = (EntityLivingBase) ientitymultipart;
									}
								}

								if (!itemstack1.isEmpty() && entity instanceof EntityLivingBase) {
									ItemStack beforeHitCopy = itemstack1.copy();
									itemstack1.hitEntity((EntityLivingBase) entity, player);

									if (itemstack1.isEmpty()) {
										net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player,
												beforeHitCopy, EnumHand.MAIN_HAND);
										player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
									}
								}

								if (targetEntity instanceof EntityLivingBase) {
									float f5 = f4 - ((EntityLivingBase) targetEntity).getHealth();
									player.addStat(StatList.DAMAGE_DEALT, Math.round(f5 * 10.0F));

									if (j > 0) {
										targetEntity.setFire(j * 4);
									}

									if (player.world instanceof WorldServer && f5 > 2.0F) {
										int k = (int) ((double) f5 * 0.5D);
										((WorldServer) player.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR,
												targetEntity.posX,
												targetEntity.posY + (double) (targetEntity.height * 0.5F),
												targetEntity.posZ, k, 0.1D, 0.0D, 0.1D, 0.2D);
									}
								}

								player.addExhaustion(0.1F);
							} else {
								player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ,
										SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, player.getSoundCategory(), 1.0F,
										1.0F);

								if (flag4) {
									targetEntity.extinguish();
								}
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void takeShieldHit(LivingAttackEvent e) {
		float damage = e.getAmount();
		ItemStack activeItemStack;
		EntityPlayer player;

		if (!(e.getEntityLiving() instanceof EntityPlayer)) {
			return;
		}
		player = (EntityPlayer) e.getEntityLiving();
		if (player.getActiveItemStack() == null) {
			return;
		}
		activeItemStack = player.getActiveItemStack();

		if (damage > 0.0F && activeItemStack != null && activeItemStack.getItem() instanceof BasicItemShield) {

			BasicItemShield shield = (BasicItemShield) activeItemStack.getItem();

			if (shield != null) {

				activeItemStack.damageItem(1, player);
				player.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 0.8F, 0.8F + player.world.rand.nextFloat() * 0.4F);
				player.getCooldownTracker().setCooldown(shield, shield.recoveryRate);

			}
		}
	}
}