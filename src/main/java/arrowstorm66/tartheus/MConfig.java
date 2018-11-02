package arrowstorm66.tartheus;

import arrowstorm66.tartheus.config.ConfigClient;
import arrowstorm66.tartheus.config.ConfigDimension;
import arrowstorm66.tartheus.config.ConfigEntity;
import arrowstorm66.tartheus.config.ConfigMisc;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class MConfig {

	public static ConfigEntity entity;
	public static ConfigDimension dimension;
	public static ConfigMisc miscellaneous;
	public static ConfigClient client;

	public static void preInitConfigs(FMLPreInitializationEvent event) {
		File configFolder = new File(event.getModConfigurationDirectory().getAbsolutePath(), "tartheus");
		entity = new ConfigEntity(new File(configFolder, "entity.cfg"));
		dimension = new ConfigDimension(new File(configFolder, "dimension.cfg"));
		miscellaneous = new ConfigMisc(new File(configFolder, "miscellaneous.cfg"));
		client = new ConfigClient(new File(configFolder, "client.cfg"));
	}

}
