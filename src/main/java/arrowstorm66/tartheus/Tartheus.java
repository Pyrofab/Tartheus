package arrowstorm66.tartheus;

import arrowstorm66.tartheus.blocks.tile.TileEntityFlameJet;
import arrowstorm66.tartheus.blocks.tile.TileEntityPoppingJet;
import arrowstorm66.tartheus.blocks.tile.TileEntityScorpionSpawner;
import arrowstorm66.tartheus.blocks.tile.TileEntityStoneFurnace;
import arrowstorm66.tartheus.dangerlevel.CapabilityDangerLevel;
import arrowstorm66.tartheus.gui.CreatureInfo;
import arrowstorm66.tartheus.gui.GuiHandler;
import arrowstorm66.tartheus.gui.ScreenEffects;
import arrowstorm66.tartheus.proxy.ServerProxy;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Hello World!
@Mod(modid = Tartheus.MODID, name = Tartheus.MODNAME, version = Tartheus.VERSION, guiFactory = Tartheus.CONFIG, updateJSON = Tartheus.UPDATE)
public class Tartheus {

	public static final String MODID = "tartheus";
	public static final String MODNAME = "Tartheus";
	public static final String VERSION = "0.1.3";
	public static final String CONFIG = "arrowstorm66.tartheus.config.ForgeConfigFactory";
	public static final String UPDATE = "https://raw.githubusercontent.com/Arrowstorm606/Tartheus/master/src/main/resources/update_changelog.json";
	public static final Logger LOGGER = LogManager.getLogger(" </> " + Tartheus.MODID + " </> ");
	public Class targetclass;

	@Instance(MODID)
	public static Tartheus instance = new Tartheus();
	public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Tartheus.MODID);

	@SidedProxy(clientSide = "arrowstorm66.tartheus.proxy.ClientProxy", serverSide = "arrowstorm66.tartheus.proxy.ServerProxy")
	public static ServerProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		GameRegistry.registerTileEntity(TileEntityScorpionSpawner.class, new ResourceLocation("tartheus:adjustable_mobspawner"));
		GameRegistry.registerTileEntity(TileEntityStoneFurnace.class, new ResourceLocation("tartheus:stone_furnace"));
		GameRegistry.registerTileEntity(TileEntityPoppingJet.class, new ResourceLocation("tartheus:popping_jet"));
		GameRegistry.registerTileEntity(TileEntityFlameJet.class, new ResourceLocation("tartheus:flame_jet"));
		MConfig.preInitConfigs(e);
		MEntities.preInit();
		MEntities.initModels();
		MinecraftForge.EVENT_BUS.register(new CreatureInfo());
		MinecraftForge.EVENT_BUS.register(new MForgeEvents());
		CapabilityDangerLevel.register();
		proxy.preInit(e);
		Tartheus.LOGGER.info("Preinitialization Done");
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		MArmorMaterials.initRepairMaterials();
		MEntities.Init();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		MinecraftForge.EVENT_BUS.register(new ScreenEffects());
		proxy.init(e);
		Tartheus.LOGGER.info("Initialization Done");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		MDimensions.init();
		proxy.postInit(e);
		Tartheus.LOGGER.info("Postinitialization Done");
	}
}