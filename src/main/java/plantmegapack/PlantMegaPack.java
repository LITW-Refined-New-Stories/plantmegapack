package plantmegapack;

import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import plantmegapack.bin.PMPBlocks;
import plantmegapack.bin.PMPCreativeTabs;
import plantmegapack.bin.PMPEventHandlers;
import plantmegapack.bin.PMPIntegration;
import plantmegapack.bin.PMPItems;
import plantmegapack.bin.PMPRecipes;
import plantmegapack.bin.PMPRenderers;
import plantmegapack.bin.PMPSettings;
import plantmegapack.bin.PMPSoilBlocks;
import plantmegapack.common.PMPModInfo;
import plantmegapack.worldgen.PMPWorldGenerator;

@Mod(
    modid = PlantMegaPack.MOD_ID,
    name = PlantMegaPack.MOD_NAME,
    version = Tags.VERSION,
    canBeDeactivated = false,
    guiFactory = "plantmegapack.gui.PMPGuiConfig")
public class PlantMegaPack {

    public static final String MOD_ID = "plantmegapack";
    public static final String MOD_NAME = "Plant Mega Pack";
    public static final String MOD_VERSION = Tags.VERSION;
    @Instance(MOD_ID)
    public static PlantMegaPack instance;
    private static final Logger logger = LogManager.getLogger("PlantMegaPack");
    public static final boolean debugMode = false;
    public static String configPathRoot;
    public static PMPSettings settingsGeneral;
    public static PMPSoilBlocks soilBlocks;
    public static PMPCreativeTabs creativeTabs;
    public static PMPBlocks blocks;
    public static PMPItems items;
    public static PMPRecipes recipes;
    public static PMPWorldGenerator worldGenerator;
    public static PMPRenderers renderers;
    public static PMPEventHandlers eventHandlers;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configPathRoot = new String(
            event.getModConfigurationDirectory()
                .getPath() + "/"
                + MOD_ID
                + "/");
        settingsGeneral = new PMPSettings(configPathRoot);
        soilBlocks = new PMPSoilBlocks(configPathRoot);
        creativeTabs = new PMPCreativeTabs();
        blocks = new PMPBlocks();
        items = new PMPItems();
        recipes = new PMPRecipes();
        renderers = new PMPRenderers();
        worldGenerator = new PMPWorldGenerator();
        eventHandlers = new PMPEventHandlers();
        FMLCommonHandler.instance()
            .bus()
            .register(eventHandlers);
        MinecraftForge.EVENT_BUS.register(eventHandlers);
        GameRegistry.registerWorldGenerator(worldGenerator, 1023);
        PMPModInfo.outputModStatisticsToConsole();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        PMPIntegration.initModIntegration();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {}

    public void logOutput(String line) {
        logger.info(line);
    }
}
