package arrowstorm66.tartheus.packets;

import arrowstorm66.tartheus.MForgeEvents;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketWeaponOverhaul implements IMessage
{
    private int entityId;
    
    public PacketWeaponOverhaul() {
    }
    
    public PacketWeaponOverhaul(final int parEntityId) {
        this.entityId = parEntityId;
    }
    
    public void fromBytes(final ByteBuf buf) {
        this.entityId = ByteBufUtils.readVarInt(buf, 4);
    }
    
    public void toBytes(final ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, this.entityId, 4);
    }
    
    public static class Handler implements IMessageHandler<PacketWeaponOverhaul, IMessage>
    {
        public IMessage onMessage(final PacketWeaponOverhaul message, final MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> Handler.this.handle(message, ctx));
            return null;
        }
        
        private void handle(final PacketWeaponOverhaul message, final MessageContext ctx) {
            final EntityPlayerMP thePlayer = ctx.getServerHandler().player;
            final Entity theEntity = thePlayer.world.getEntityByID(message.entityId);
            if (thePlayer != null && theEntity != null) {
                if (thePlayer.interactionManager.getGameType() == GameType.SPECTATOR) {
                    thePlayer.setSpectatingEntity(theEntity);
                }
                else {
                    MForgeEvents.attackWithTartheusWeapon(thePlayer, theEntity);
                }
            }
        }
    }
}