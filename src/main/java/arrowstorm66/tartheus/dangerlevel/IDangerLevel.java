package arrowstorm66.tartheus.dangerlevel;

import java.security.InvalidParameterException;
import java.util.Random;
import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public interface IDangerLevel

{
	int getDangerLevel();

	void setDangerLevel(int level);
}
