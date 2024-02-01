package plantmegapack.common;

import cpw.mods.fml.common.registry.LanguageRegistry;

public enum PMPDecoratorFeature {

    VILLAGE_CROP("villageCrop"),
    VILLAGE_PLANT("villagePlant");

    public final String ID;

    private PMPDecoratorFeature(String ID) {
        this.ID = ID;
    }

    public String getLocalizedName() {
        return LanguageRegistry.instance()
            .getStringLocalization("decorator.feature." + this.ID);
    }
}
