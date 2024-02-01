package plantmegapack.common;

import net.minecraft.util.StatCollector;

public enum PMPBamboo {

    bambooAsper("bambooAsper"),
    bambooFargesiaRobusta("bambooFargesiaRobusta"),
    bambooGiantTimber("bambooGiantTimber"),
    bambooGolden("bambooGolden"),
    bambooMoso("bambooMoso"),
    bambooShortTassled("bambooShortTassled"),
    bambooTimorBlack("bambooTimorBlack"),
    bambooTropicalBlue("bambooTropicalBlue"),
    bambooWetForest("bambooWetForest");

    public final String unlocalizedName;

    private PMPBamboo(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal("tile." + this.unlocalizedName + ".name");
    }
}
