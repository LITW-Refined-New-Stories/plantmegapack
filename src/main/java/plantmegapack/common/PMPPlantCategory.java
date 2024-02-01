package plantmegapack.common;

import cpw.mods.fml.common.registry.LanguageRegistry;

public enum PMPPlantCategory {

    BAMBOO("bamboo", 1, "2", PMPTab.plants, PMPPlantSoilType.DIRT),
    BEACH("beach", 1, "2", PMPTab.plants, PMPPlantSoilType.BEACH),
    BERRYBUSH("berryBush", 1, "6", PMPTab.crops, PMPPlantSoilType.DIRT),
    CACTUS("cactus", 1, "2", PMPTab.plants, PMPPlantSoilType.SAND),
    CAVE("cave", 0, "7", PMPTab.plants, PMPPlantSoilType.ALL),
    CORAL("coral", 1, "b", PMPTab.aquatic, PMPPlantSoilType.AQUATIC),
    CROP_AQUATIC("cropAquatic", 1, "6", PMPTab.crops, PMPPlantSoilType.AQUATIC),
    CROP_LAND("cropLand", 1, "6", PMPTab.crops, PMPPlantSoilType.CROP),
    DESERT("desert", 1, "2", PMPTab.plants, PMPPlantSoilType.SAND),
    END("end", 0, "e", PMPTab.end, PMPPlantSoilType.STONE),
    EPIPHYTE("epiphyte", 1, "2", PMPTab.plants, PMPPlantSoilType.WOOD),
    FANTASY("fantasy", 0, "3", PMPTab.fantasy, PMPPlantSoilType.DIRT),
    FERN("fern", 1, "2", PMPTab.plants, PMPPlantSoilType.DIRT),
    FLOATING("floating", 1, "b", PMPTab.aquatic, PMPPlantSoilType.WATER),
    FLOWER_MULTI("flowerMulti", 1, "c", PMPTab.flowers, PMPPlantSoilType.DIRT),
    FLOWER_SINGLE("flowerSingle", 1, "c", PMPTab.flowers, PMPPlantSoilType.DIRT),
    FOREST("forest", 1, "2", PMPTab.plants, PMPPlantSoilType.DIRT),
    FRESHWATER("freshwater", 1, "b", PMPTab.aquatic, PMPPlantSoilType.AQUATIC),
    FROZEN("frozen", 0, "2", PMPTab.plants, PMPPlantSoilType.DIRT),
    FUNGUS("fungus", 1, "2", PMPTab.plants, PMPPlantSoilType.DIRT),
    GRASS("grass", 1, "2", PMPTab.plants, PMPPlantSoilType.DIRT),
    GROUNDCOVER("groundcover", 1, "2", PMPTab.plants, PMPPlantSoilType.ALL),
    IMMERSED("immersed", 1, "b", PMPTab.aquatic, PMPPlantSoilType.AQUATIC),
    JUNGLE("jungle", 1, "2", PMPTab.plants, PMPPlantSoilType.DIRT),
    MESA("mesa", 0, "2", PMPTab.plants, PMPPlantSoilType.CLAY),
    MOUNTAIN("mountain", 1, "2", PMPTab.plants, PMPPlantSoilType.DIRT),
    NETHER("nether", 0, "4", PMPTab.nether, PMPPlantSoilType.NETHER),
    PLAINS("plains", 1, "2", PMPTab.plants, PMPPlantSoilType.DIRT),
    SALTWATER("saltwater", 1, "b", PMPTab.aquatic, PMPPlantSoilType.AQUATIC),
    SAVANNA("savanna", 1, "2", PMPTab.plants, PMPPlantSoilType.DIRT),
    SHRUB("shrub", 1, "2", PMPTab.plants, PMPPlantSoilType.DIRT),
    VINE("vine", 1, "2", PMPTab.plants, PMPPlantSoilType.ALL),
    WETLANDS("wetlands", 1, "2", PMPTab.plants, PMPPlantSoilType.DIRT);

    public final String ID;
    public final int addToGame;
    public final String colorCode;
    public final PMPTab creativeTab;
    public final PMPPlantSoilType soilType;

    private PMPPlantCategory(String ID, int addToGame, String colorCode, PMPTab creativeTab,
        PMPPlantSoilType soilType) {
        this.ID = ID;
        this.addToGame = addToGame;
        this.colorCode = colorCode;
        this.creativeTab = creativeTab;
        this.soilType = soilType;
    }

    public String getLocalizedName() {
        return LanguageRegistry.instance()
            .getStringLocalization("plantCategory." + this.ID);
    }
}
