package plantmegapack.common;

import net.minecraft.util.StatCollector;

public enum PMPSalve {

    salveFireResist("salveFireResist"),
    salveHealth("salveHealth"),
    salveNightVision("salveNightVision"),
    salveStrength("salveStrength"),
    salveSwiftness("salveSwiftness"),
    salveWaterBreathing("salveWaterBreathing");

    public final String unlocalizedName;

    private PMPSalve(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal("item." + this.unlocalizedName + ".name");
    }
}
