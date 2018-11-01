package arrowstorm66.tartheus.particles;

import arrowstorm66.tartheus.Tartheus;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class StitcherParticleDanger
{

  @SubscribeEvent
  public void stitcherEventPre(TextureStitchEvent.Pre event) {
    ResourceLocation danger = new ResourceLocation(Tartheus.MODID, "particle/danger");
    event.getMap().registerSprite(danger);
  }
}
