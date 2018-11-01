package arrowstorm66.tartheus.util;

import net.minecraftforge.fml.relauncher.*;
import arrowstorm66.tartheus.MDimensions;
import arrowstorm66.tartheus.MForgeEvents;
import arrowstorm66.tartheus.MSounds;
import net.minecraft.client.*;
import net.minecraft.util.*;
import net.minecraft.client.audio.*;

@SideOnly(Side.CLIENT)
public class TartheusCaveAmbience extends PositionedSound implements ITickableSound {
	private static final Minecraft MC;

	public TartheusCaveAmbience() {
		super(MSounds.CAVE_IDLE, SoundCategory.AMBIENT);
		this.attenuationType = ISound.AttenuationType.NONE;
		this.repeat = true;
	}

	public boolean isDonePlaying() {
		return TartheusCaveAmbience.MC.player == null
				|| TartheusCaveAmbience.MC.player.world.provider.getDimensionType() != MDimensions.TARTHEUS;
	}

	public void update() {
		this.volume = Math.max(MForgeEvents.CAVE_ANIMATION.getScale(), 0.01f);
	}

	static {
		MC = Minecraft.getMinecraft();
	}
}
