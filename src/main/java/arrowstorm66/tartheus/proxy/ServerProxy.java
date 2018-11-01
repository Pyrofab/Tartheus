package arrowstorm66.tartheus.proxy;

import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.packets.PacketWeaponOverhaul;
import arrowstorm66.tartheus.packets.PacketWeaponReach;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ServerProxy {

	public RayTraceResult getMouseOver(double v) {
		return null;
	}

	public EntityPlayerMP getPlayerEntityFromContext(MessageContext ctx) {
		return ctx.getServerHandler().player; //Getting the player on the server.
	}

	public int getUniqueTextureLoc() {
		return 0;
	}

	public void registerModelRenderers() {
	}

	public void mouseClick() {
	}

	protected void registerEventListeners() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

	public void init(FMLInitializationEvent event) {
	}

	public void preInit(FMLPreInitializationEvent e) {
		int id = 0;
		Tartheus.network.registerMessage(PacketWeaponReach.Handler.class, PacketWeaponReach.class, id++, Side.SERVER);
		Tartheus.network.registerMessage(PacketWeaponOverhaul.Handler.class, PacketWeaponOverhaul.class, id++, Side.SERVER);
	}

	public void displayMobScreen(int ticks, String screen) {
	}
}
