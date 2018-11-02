package arrowstorm66.tartheus.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigClient extends Configuration {

    public boolean useShaders;

    public ConfigClient(File file) {
        super(file);
        reload();
    }

    public void reload() {
        this.load();
        useShaders = getBoolean("Enable Shaders", CATEGORY_CLIENT, true, "For a fancy spooky world.");
        this.save();
    }

    private void setCategoryLanguageKey(String category) {
        this.setCategoryLanguageKey(category, "tartheus.config." + category + ".name");
    }
}