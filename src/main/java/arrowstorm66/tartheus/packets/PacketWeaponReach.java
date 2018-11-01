package arrowstorm66.tartheus.packets;

import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.base.gear.IReach;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketWeaponReach implements IMessage {
	private int entityId;

	public PacketWeaponReach() {
		// need this constructor
	}

	public PacketWeaponReach(int parEntityId) {
		entityId = parEntityId;
		// DEBUG
		// logger.info("Constructor");
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		entityId = ByteBufUtils.readVarInt(buf, 4);
		// DEBUG
		// logger.info("fromBytes");
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarInt(buf, entityId, 4);
		// DEBUG
		// logger.info("toBytes encoded");
	}

	public static class Handler implements IMessageHandler<PacketWeaponReach, IMessage> {
		@Override
		public IMessage onMessage(final PacketWeaponReach message, MessageContext ctx) {
			// DEBUG
			// logger.info("Message received");
			// Know it will be on the server so make it thread-safe
			final EntityPlayerMP thePlayer = Tartheus.proxy.getPlayerEntityFromContext(ctx);
			thePlayer.getServer().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					Entity theEntity = thePlayer.world.getEntityByID(message.entityId);
					if (theEntity instanceof EntityLivingBase && theEntity != thePlayer) {
						if (thePlayer.getDistance(theEntity) > 4.5) {
							if (theEntity.hurtResistantTime != ((EntityLivingBase) theEntity).maxHurtResistantTime) {
								thePlayer.attackTargetEntityWithCurrentItem(theEntity);
							}
						}
					}
				}
			});
			return null;
		}
	}
}