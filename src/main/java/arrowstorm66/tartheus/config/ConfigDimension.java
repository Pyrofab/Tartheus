package arrowstorm66.tartheus.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigDimension extends Configuration {
	public static final String CATEGORY_ID = "ids";
	private static final String CATEGORY_EXTRA = "extra";

	public static int TartheusID;
	public static boolean isMusicenabled;

	public ConfigDimension(File file) {
		super(file);
		reload();
	}

	public void reload() {
		this.load();
		TartheusID = getInt("tartheus", CATEGORY_ID, 65, Integer.MIN_VALUE, Integer.MAX_VALUE, "ID for Tartheus.");
		isMusicenabled = getBoolean("Enable Dimension Music", CATEGORY_EXTRA, true,
				"Disable if you want to hear vanilla music.");
		this.save();
	}

	private void setCategoryLanguageKey(String category) {
		this.setCategoryLanguageKey(category, "tartheus.config." + category + ".name");
	}
}