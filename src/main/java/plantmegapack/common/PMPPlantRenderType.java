package plantmegapack.common;

import cpw.mods.fml.common.registry.LanguageRegistry;

public enum PMPPlantRenderType {

    CROP("crop"),
    EPIPHYTE_HORIZONTAL("epiphyteHorizontal"),
    EPIPHYTE_VERTICAL("epiphyteVertical"),
    FLAT("flat"),
    FLOATING_FLAT("floatingFlat"),
    FLOATING_FLOWER("floatingFlower"),
    FLOATING_PLANT("floatingPlant"),
    GROUNDCOVER("groundcover"),
    IMMERSED("immersed"),
    NORMAL("normal"),
    STALK("stalk"),
    STAR("star"),
    VINE_FLOWER("vineFlower"),
    VINE_NORMAL("vineNormal"),
    VINE_RANDOM("vineRandom"),
    VINE_VANILLA("vineVanilla"),
    WATER("water"),
    WATER_FLAT("waterFlat");

    public final String ID;

    private PMPPlantRenderType(String ID) {
        this.ID = ID;
    }

    public String getLocalizedName() {
        return LanguageRegistry.instance()
            .getStringLocalization("plantRenderType." + this.ID);
    }
}
