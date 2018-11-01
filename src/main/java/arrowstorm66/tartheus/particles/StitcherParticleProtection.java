package arrowstorm66.tartheus.particles;

import arrowstorm66.tartheus.Tartheus;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class StitcherParticleProtection
{

  @SubscribeEvent
  public void stitcherEventPre(TextureStitchEvent.Pre event) {
    ResourceLocation danger = new ResourceLocation(Tartheus.MODID, "particle/protection");
    event.getMap().registerSprite(danger);
  }
}
