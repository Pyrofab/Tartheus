package arrowstorm66.tartheus.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigMisc extends Configuration {
	private static final String CATEGORY_MISC = "miscellaneous features";

	public static boolean isBloodEnabled;
	public static boolean isStatIndicatorEnabled;

	public ConfigMisc(File file) {
		super(file);
		reload();
	}

	public void reload() {
		this.load();
		isBloodEnabled = getBoolean("Enable Blood", CATEGORY_MISC, true, "For a bloody good time.");
		isStatIndicatorEnabled = getBoolean("Enable Stat Indicator", CATEGORY_MISC, true, "Disable if you already have another indicator.");
		this.save();
	}

	private void setCategoryLanguageKey(String category) {
		this.setCategoryLanguageKey(category, "tartheus.config." + category + ".name");
	}
}