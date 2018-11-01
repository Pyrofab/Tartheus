package arrowstorm66.tartheus;

import net.minecraft.block.SoundType;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MMaterialSounds extends SoundType {
	public MMaterialSounds(float volumeIn, float pitchIn, SoundEvent breakSoundIn, SoundEvent stepSoundIn,
			SoundEvent placeSoundIn, SoundEvent hitSoundIn, SoundEvent fallSoundIn) {
		super(volumeIn, pitchIn, breakSoundIn, stepSoundIn, placeSoundIn, hitSoundIn, fallSoundIn);
	}

	public static final MMaterialSounds TILE = new MMaterialSounds(1.0F, 1.0F, SoundEvents.BLOCK_GLASS_BREAK,
			SoundEvents.BLOCK_GLASS_STEP, SoundEvents.BLOCK_GLASS_PLACE, SoundEvents.BLOCK_GLASS_HIT,
			SoundEvents.BLOCK_GLASS_FALL);
}