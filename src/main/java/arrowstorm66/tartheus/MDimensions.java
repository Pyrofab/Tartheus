package arrowstorm66.tartheus;

import arrowstorm66.tartheus.config.ConfigDimension;
import arrowstorm66.tartheus.world.TartheusWorldProvider;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Config;

public class MDimensions {

	public static final int tartheusDimensionID = ConfigDimension.TartheusID;
	public static final DimensionType TARTHEUS = DimensionType.register("tartheus", "_tartheus", tartheusDimensionID,
			TartheusWorldProvider.class, false);

	public static void registerDimension() {
		DimensionManager.registerDimension(tartheusDimensionID, TARTHEUS);
	}

	public static void init() {
		registerDimension();
	}
}
