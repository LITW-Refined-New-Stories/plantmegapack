package plantmegapack.common;

import cpw.mods.fml.common.registry.LanguageRegistry;

public enum PMPPlantGrowthType {

    DOUBLE("double"),
    EPIPHYTE("epiphyte"),
    NORMAL("normal"),
    SEAWEED("seaweed"),
    STALK("stalk");

    public String ID;

    private PMPPlantGrowthType(String ID) {
        this.ID = ID;
    }

    public String getLocalizedName() {
        return LanguageRegistry.instance()
            .getStringLocalization("plantGrowthType." + this.ID);
    }
}
