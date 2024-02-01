package plantmegapack.common;

import cpw.mods.fml.common.registry.LanguageRegistry;

public enum PMPPlantEffectType {

    NONE("none"),
    DARK_PARTICLES("darkParticles");

    public String ID;

    private PMPPlantEffectType(String ID) {
        this.ID = ID;
    }

    public String getLocalizedName() {
        return LanguageRegistry.instance()
            .getStringLocalization("plantEffectType." + this.ID);
    }
}
