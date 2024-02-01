package plantmegapack.common;

import cpw.mods.fml.common.registry.LanguageRegistry;

public enum PMPFlowerColor {

    BURGUNDY("Burgundy", 1, "4", PMPHangingPlant.HANGING_FLOWER_BURGUNDY),
    RED("Red", 1, "c", PMPHangingPlant.HANGING_FLOWER_RED),
    CORAL("Coral", 14, "6", PMPHangingPlant.HANGING_FLOWER_CORAL),
    ORANGE("Orange", 14, "6", PMPHangingPlant.HANGING_FLOWER_ORANGE),
    YELLOW("Yellow", 11, "e", PMPHangingPlant.HANGING_FLOWER_YELLOW),
    GREEN("Green", 10, "9", PMPHangingPlant.HANGING_FLOWER_GREEN),
    CYAN("Cyan", 6, "3", PMPHangingPlant.HANGING_FLOWER_CYAN),
    BLUE("Blue", 12, "9", PMPHangingPlant.HANGING_FLOWER_BLUE),
    ROYAL("Royal", 5, "1", PMPHangingPlant.HANGING_FLOWER_ROYAL),
    PURPLE("Purple", 5, "5", PMPHangingPlant.HANGING_FLOWER_PURPLE),
    MAGENTA("Magenta", 13, "5", PMPHangingPlant.HANGING_FLOWER_MAGENTA),
    PINK("Pink", 9, "d", PMPHangingPlant.HANGING_FLOWER_PINK),
    WHITE("White", 7, "f", PMPHangingPlant.HANGING_FLOWER_WHITE);

    public final String ID;
    public final int dyeColor;
    public final String formatCode;
    public final PMPHangingPlant hangingPlant;

    private PMPFlowerColor(String ID, int dyeColor, String formatCode, PMPHangingPlant hangingPlant) {
        this.ID = ID;
        this.dyeColor = dyeColor;
        this.formatCode = formatCode;
        this.hangingPlant = hangingPlant;
    }

    public String getLocalizedName() {
        return LanguageRegistry.instance()
            .getStringLocalization("flowerColor." + this.ID);
    }
}
