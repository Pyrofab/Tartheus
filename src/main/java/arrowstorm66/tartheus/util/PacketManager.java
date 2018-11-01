package arrowstorm66.tartheus.util;

import com.google.common.base.Predicates;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;

public class PacketManager {
	protected FMLEventChannel channel;
	protected String label;

	public static PacketManager create(FMLEventChannel channel, String label) {
		return new PacketManager(channel, label);
	}

	protected PacketManager(FMLEventChannel channel, String label) {
		this.channel = channel;
		this.label = label;
	}
	
	private byte[] generatePacket(byte id, byte[] data) {
		byte[] bytes = new byte[data.length + 1];
		bytes[0] = id;
		for (int i = 0; i < data.length; i++) {
			bytes[i + 1] = data[i];
		}
		return bytes;
	}

	public void sendPacketToServer(byte id, byte[] data) {
		FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(Unpooled.copiedBuffer(generatePacket(id, data))), label);
		packet.setTarget(Side.SERVER);
		channel.sendToServer(packet);
	}

	public void sendToClient(EntityPlayerMP player, byte id, byte[] data) {
		FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(Unpooled.copiedBuffer(generatePacket(id, data))), label);
		packet.setTarget(Side.CLIENT);
		channel.sendTo(packet, player);
	}

	public void sendPacketToAllClientsNear(int dimension, double ox, double oy, double oz, double radius, byte id, byte[] data) {
		FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(Unpooled.copiedBuffer(generatePacket(id, data))), label);
		packet.setTarget(Side.CLIENT);
		channel.sendToAllAround(packet, new TargetPoint(dimension, ox, oy, oz, radius));
	}

	public void sendToAllClients(byte packetID, byte[] data) {
		for (EntityPlayerMP player : FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers()) {
			sendToClient(player, packetID, data);
		}
	}
	
	public void sendToAllClientsInWorld(World world, byte packetID, byte[] data) {
		for (EntityPlayerMP player : world.getPlayers(EntityPlayerMP.class, Predicates.alwaysTrue()))
			sendToClient(player, packetID, data);
	}
}
