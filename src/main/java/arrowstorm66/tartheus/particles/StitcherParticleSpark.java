package arrowstorm66.tartheus.particles;

import arrowstorm66.tartheus.Tartheus;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class StitcherParticleSpark
{

  @SubscribeEvent
  public void stitcherEventPre(TextureStitchEvent.Pre event) {
	for (final ResourceLocation loc : ParticleSpark.TEXTURES) {
		event.getMap().registerSprite(loc);
	}
  }
}
