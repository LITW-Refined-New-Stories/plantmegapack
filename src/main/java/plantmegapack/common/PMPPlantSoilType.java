package plantmegapack.common;

import cpw.mods.fml.common.registry.LanguageRegistry;

public enum PMPPlantSoilType {

    ALL("all"),
    AQUATIC("aquatic"),
    BEACH("beach"),
    CLAY("clay"),
    CROP("crop"),
    DIRT("dirt"),
    GRAVEL("gravel"),
    NETHER("nether"),
    SAND("sand"),
    STONE("stone"),
    WATER("water"),
    WOOD("wood");

    public String ID;

    private PMPPlantSoilType(String ID) {
        this.ID = ID;
    }

    public String getLocalizedName() {
        return LanguageRegistry.instance()
            .getStringLocalization("plantSoilType." + this.ID);
    }
}
