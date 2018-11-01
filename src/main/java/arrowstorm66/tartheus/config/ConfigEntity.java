package arrowstorm66.tartheus.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigEntity extends Configuration {
	private static final String CATEGORY_INSECTS = "insects";
	private static final String CATEGORY_EXTRA = "extra";

	public static boolean isScorpionEnabled;
	public static boolean isVinegaroonEnabled;
	public static boolean isSolifugaeEnabled;
	public static boolean isLurkerEnabled;
	
	public static boolean areDangerLevelsenabled;

	public ConfigEntity(File file) {
		super(file);
		reload();
	}

	public void reload() {
		this.load();
		isScorpionEnabled = getBoolean("Enable Scorpion", CATEGORY_INSECTS, true, "The basic insects of the desert and badlands.");
		isVinegaroonEnabled = getBoolean("Enable Vinegaroon", CATEGORY_INSECTS, true,
				"Heavily-armored creatures that shoot vinegar and grip the player when close.");
		isSolifugaeEnabled = getBoolean("Enable Solifugae", CATEGORY_INSECTS, true,
				"A speedy desert insect that thankfully has low health.");
		isLurkerEnabled = getBoolean("Enable Lurker", CATEGORY_INSECTS, true,
				". . .");
		
		areDangerLevelsenabled = getBoolean("Enable Danger Levels", CATEGORY_EXTRA, true,
				"Danger Levels make Nature's Revenge mobs more intelligent.");
		this.save();
	}

	private void setCategoryLanguageKey(String category) {
		this.setCategoryLanguageKey(category, "tartheus.config." + category + ".name");
	}
}