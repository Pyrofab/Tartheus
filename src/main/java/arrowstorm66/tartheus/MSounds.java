package arrowstorm66.tartheus;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MSounds {

	public static final SoundEvent LURKER_ROAR;
	public static final SoundEvent LURKER_BREATH;

	public static final SoundEvent SCORPION_IDLE;
	public static final SoundEvent SCORPION_HURT;
	public static final SoundEvent SCORPION_DEATH;

	public static final SoundEvent GUNSHOT;

	public static final SoundEvent MUSIC_ENCLOSE_DAY;
	public static final SoundEvent MUSIC_ENCLOSE_NIGHT;

	public static final SoundEvent CAVE_IDLE;
	public static final SoundEvent CAVE_SOUND;
	public static final SoundEvent BIRDS_AMBIENT;
	public static final SoundEvent NIGHT_AMBIENT;

	@SubscribeEvent
	public static void registerSounds(final RegistryEvent.Register<SoundEvent> evt) {

		evt.getRegistry().register(MSounds.LURKER_ROAR);
		evt.getRegistry().register(MSounds.LURKER_BREATH);

		evt.getRegistry().register(MSounds.SCORPION_IDLE);
		evt.getRegistry().register(MSounds.SCORPION_HURT);
		evt.getRegistry().register(MSounds.SCORPION_DEATH);

		evt.getRegistry().register(MSounds.GUNSHOT);

		evt.getRegistry().register(MSounds.MUSIC_ENCLOSE_DAY);
		evt.getRegistry().register(MSounds.MUSIC_ENCLOSE_NIGHT);

		evt.getRegistry().register(MSounds.CAVE_IDLE);
		evt.getRegistry().register(MSounds.CAVE_SOUND);
		evt.getRegistry().register(MSounds.BIRDS_AMBIENT);
		evt.getRegistry().register(MSounds.NIGHT_AMBIENT);
	}

	private static SoundEvent createEvent(final String soundName) {
		final ResourceLocation soundID = new ResourceLocation(Tartheus.MODID, soundName);
		return new SoundEvent(soundID).setRegistryName(soundID);
	}

	static {

		LURKER_ROAR = createEvent("lurker_roar");
		LURKER_BREATH = createEvent("lurker_breath");

		SCORPION_IDLE = createEvent("scorpion_idle");
		SCORPION_HURT = createEvent("scorpion_hurt");
		SCORPION_DEATH = createEvent("scorpion_death");

		GUNSHOT = createEvent("gunshot");

		MUSIC_ENCLOSE_DAY = createEvent("music_enclose_day");
		MUSIC_ENCLOSE_NIGHT = createEvent("music_enclose_night");

		CAVE_IDLE = createEvent("cave_idle");
		CAVE_SOUND = createEvent("cave_sound");
		BIRDS_AMBIENT = createEvent("birds_ambient");
		NIGHT_AMBIENT = createEvent("night_ambient");
	}
}
