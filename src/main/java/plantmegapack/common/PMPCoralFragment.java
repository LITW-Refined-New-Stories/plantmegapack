package plantmegapack.common;

import net.minecraft.util.StatCollector;

public enum PMPCoralFragment {

    coralFragmentBlueStaghorn("coralFragmentBlueStaghorn", PMPFlowerColor.BLUE),
    coralFragmentElegance("coralFragmentElegance", PMPFlowerColor.ORANGE),
    coralFragmentFeatheredHydroid("coralFragmentFeatheredHydroid", PMPFlowerColor.GREEN),
    coralFragmentPillar("coralFragmentPillar", PMPFlowerColor.ORANGE),
    coralFragmentPulsingXenia("coralFragmentPulsingXenia", PMPFlowerColor.WHITE),
    coralFragmentPurpleSeaWhip("coralFragmentPurpleSeaWhip", PMPFlowerColor.PURPLE),
    coralFragmentRedSeaFan("coralFragmentRedSeaFan", PMPFlowerColor.RED),
    coralFragmentSunPolyps("coralFragmentSunPolyps", PMPFlowerColor.ORANGE),
    coralFragmentYellowBamboo("coralFragmentYellowBamboo", PMPFlowerColor.YELLOW);

    public final String unlocalizedName;
    public final PMPFlowerColor color;

    private PMPCoralFragment(String unlocalizedName, PMPFlowerColor color) {
        this.unlocalizedName = unlocalizedName;
        this.color = color;
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal("item." + this.unlocalizedName + ".name");
    }
}
