package plantmegapack.common;

import cpw.mods.fml.common.registry.LanguageRegistry;

public enum PMPPlantBreakSound {

    GRASS("grass"),
    WOOD("wood"),
    GRAVEL("gravel");

    public final String ID;

    private PMPPlantBreakSound(String ID) {
        this.ID = ID;
    }

    public String getLocalizedName() {
        return LanguageRegistry.instance()
            .getStringLocalization("plantBreakSound." + this.ID);
    }
}
