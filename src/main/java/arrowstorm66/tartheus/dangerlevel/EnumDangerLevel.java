package arrowstorm66.tartheus.dangerlevel;

import java.util.Random;

import arrowstorm66.tartheus.util.RandomCollection;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

/**
 * 
 * @author TheXFactor117
 *
 */
public enum EnumDangerLevel 
{
	DEFAULT("default", 0xFFFFFF, TextFormatting.DARK_GRAY, 0),
	NAIVE("Naive", 0xFFFFFF, TextFormatting.WHITE, 0.8),
	LEARNING("Learning", 0x00AA00, TextFormatting.DARK_GREEN, 0.4),
	EXPERIENCED("Experienced", 0x55FFFF, TextFormatting.AQUA, 0.2),
	SKILLFUL("Skillful", 0x00AAAA, TextFormatting.DARK_AQUA, 0.1),
	ADEPT("Adept", 0xFFFF55, TextFormatting.YELLOW, 0.05),
	TYRANNICAL("Tyrannical", 0xFF5555, TextFormatting.RED, 0.025),
	OMNISCIENT("Omniscient", 0xFF55FF, TextFormatting.LIGHT_PURPLE, 0.012);
	
	private String name;
	private int hex;
	private String color;
	private double chance;
	
	private static final RandomCollection<EnumDangerLevel> DANGER_LEVELS = new RandomCollection<EnumDangerLevel>();
	
	EnumDangerLevel(String name, int hex, Object color, double chance)
	{
		this.name = name;
		this.hex = hex;
		this.color = color.toString();
		this.chance = chance;
	}
	
	/**
	 * Returns a randomized rarity.
	 * @param nbt
	 * @param blacksmithingRank
	 * @param rand
	 * @return
	 */
	public static EnumDangerLevel getRandomDangerLevel(NBTTagCompound nbt, Random rand)
	{	
		return DANGER_LEVELS.next(rand);
	}
	
	/**
	 * Return the current rarity in the given NBTTagCompound. Returns Default if it can't find it.
	 * @param nbt
	 * @return
	 */
	public static EnumDangerLevel getDangerLevel(NBTTagCompound nbt)
	{
		return nbt.hasKey("DANGERLEVEL") ? EnumDangerLevel.values()[nbt.getInteger("DANGERLEVEL")] : DEFAULT;
	}
	
	/**
	 * Sets the rarity specified to the given NBTTagCompound.
	 * @param nbt
	 * @param rarity
	 */
	public static void setDangerLevel(NBTTagCompound nbt, EnumDangerLevel rarity)
	{
		nbt.setInteger("DANGERLEVEL", rarity.ordinal());
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getHex()
	{
		return hex;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public double getChance()
	{
		return chance;
	}
	
	static
	{
		for (EnumDangerLevel dangerlevel : EnumDangerLevel.values())
		{
			if (dangerlevel.chance > 0.0D)
			{
				DANGER_LEVELS.add(dangerlevel.chance, dangerlevel);
			}
		}
	}
}
