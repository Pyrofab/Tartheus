package arrowstorm66.tartheus;

import java.io.File;

import arrowstorm66.tartheus.config.ConfigDimension;
import arrowstorm66.tartheus.config.ConfigEntity;
import arrowstorm66.tartheus.config.ConfigMisc;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class MConfig {

	public static ConfigEntity entity;
	public static ConfigDimension dimension;
	public static ConfigMisc miscellaneous;

	public static void preInitConfigs(FMLPreInitializationEvent event) {
		String configFolder = event.getModConfigurationDirectory().getAbsolutePath() + "\\tartheus\\";
		entity = new ConfigEntity(new File(configFolder + "entity.cfg"));
		dimension = new ConfigDimension(new File(configFolder + "dimension.cfg"));
		miscellaneous = new ConfigMisc(new File(configFolder + "miscellaneous.cfg"));
	}

}
