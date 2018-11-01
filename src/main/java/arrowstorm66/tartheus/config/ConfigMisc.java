package arrowstorm66.tartheus.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigMisc extends Configuration {
	private static final String CATEGORY_MISC = "miscellaneous features";

	public static boolean isBloodenabled;

	public ConfigMisc(File file) {
		super(file);
		reload();
	}

	public void reload() {
		this.load();
		isBloodenabled = getBoolean("Enable Blood", CATEGORY_MISC, true, "For a bloody good time.");
		this.save();
	}

	private void setCategoryLanguageKey(String category) {
		this.setCategoryLanguageKey(category, "tartheus.config." + category + ".name");
	}
}