package plantmegapack.common;

import cpw.mods.fml.common.registry.LanguageRegistry;

public enum PMPPlantBamboo {

    ASPER("bambooAsper"),
    FARGESIA_ROBUSTA("bambooFargesiaRobusta"),
    GIANT_TIMBER("bambooGiantTimber"),
    GOLDEN("bambooGolden"),
    MOSO("bambooMoso"),
    SHORT_TASSLED("bambooShortTassled"),
    TIMOR_BLACK("bambooTimorBlack"),
    TROPICAL_BLUE("bambooTropicalBlue"),
    WET_FOREST("bambooWetForest");

    public final String ID;

    private PMPPlantBamboo(String ID) {
        this.ID = ID;
    }

    public String getScientificName() {
        return LanguageRegistry.instance()
            .getStringLocalization("tile." + this.ID + ".sname");
    }

    public String getLocalizedName() {
        return LanguageRegistry.instance()
            .getStringLocalization("tile." + this.ID + ".name");
    }
}
