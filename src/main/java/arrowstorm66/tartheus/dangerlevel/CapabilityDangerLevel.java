package arrowstorm66.tartheus.dangerlevel;

import javax.annotation.Nullable;

import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.base.entity.EntityHostile;
import arrowstorm66.tartheus.util.CapabilityUtils;
import arrowstorm66.tartheus.util.SimpleCapabilityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class CapabilityDangerLevel 
{
	@CapabilityInject(IDangerLevel.class)
	public static final Capability<IDangerLevel> DANGER_LEVEL = null;
	public static final EnumFacing DEFAULT_FACING = null;
	public static final ResourceLocation ID = new ResourceLocation(Tartheus.MODID, "DangerLevel");
	
	public static void register() 
	{
		CapabilityManager.INSTANCE.register(IDangerLevel.class, new Capability.IStorage<IDangerLevel>() 
		{
			@Override
			public NBTBase writeNBT(Capability<IDangerLevel> capability, IDangerLevel instance, EnumFacing side) 
			{
				NBTTagCompound nbt = new NBTTagCompound();				
				nbt.setInteger("DangerLevel", instance.getDangerLevel());
				return nbt;
			}

			@Override
			public void readNBT(Capability<IDangerLevel> capability, IDangerLevel instance, EnumFacing side, NBTBase nbt) 
			{
				NBTTagCompound compound = (NBTTagCompound) nbt;		
				instance.setDangerLevel(compound.getInteger("DangerLevel"));
			}
		}, () -> new DangerLevelData(null));

		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
	
	@Nullable
	public static IDangerLevel getDanger(EntityLivingBase entity) 
	{
		return CapabilityUtils.getCapability(entity, DANGER_LEVEL, DEFAULT_FACING);
	}
	
	public static ICapabilityProvider createProvider(IDangerLevel mana) 
	{
		return new SimpleCapabilityProvider<>(DANGER_LEVEL, DEFAULT_FACING, mana);
	}
	
	public static class EventHandler 
	{
		@SubscribeEvent
		public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) 
		{
			if (event.getObject() instanceof EntityHostile) 
			{
				final DangerLevelData stats = new DangerLevelData((EntityHostile) event.getObject());
				
				event.addCapability(ID, createProvider(stats));
			}
		}
	}
}