package plantmegapack.common;

import cpw.mods.fml.common.registry.LanguageRegistry;

public enum PMPDecoratorType {

    BEACH("beach"),
    END("end"),
    FEATURES("features"),
    LAND("land"),
    NETHER("nether"),
    WATER("water");

    public final String ID;

    private PMPDecoratorType(String ID) {
        this.ID = ID;
    }

    public String getLocalizedName() {
        return LanguageRegistry.instance()
            .getStringLocalization("decorator.type." + this.ID);
    }
}
