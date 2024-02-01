package plantmegapack.common;

import cpw.mods.fml.common.registry.LanguageRegistry;

public enum PMPDecoratorSet {

    BOP("bop"),
    EB("eb"),
    EBXL("ebxl"),
    HIGHLANDS("hl"),
    RWG("rwg"),
    VC("vc"),
    VANILLA("vanilla");

    public final String ID;

    private PMPDecoratorSet(String ID) {
        this.ID = ID;
    }

    public String getLocalizedName() {
        return LanguageRegistry.instance()
            .getStringLocalization("decorator.set." + this.ID);
    }
}
