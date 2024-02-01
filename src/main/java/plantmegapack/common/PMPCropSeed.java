package plantmegapack.common;

import net.minecraft.util.StatCollector;

public enum PMPCropSeed {

    seedBeet("seedBeet", "cropBeet"),
    seedBellPepperOrange("seedBellPepperOrange", "cropBellPepperOrange"),
    seedBellPepperRed("seedBellPepperRed", "cropBellPepperRed"),
    seedBellPepperYellow("seedBellPepperYellow", "cropBellPepperYellow"),
    seedBroccoli("seedBroccoli", "cropBroccoli"),
    seedCassava("seedCassava", "cropCassava"),
    seedCelery("seedCelery", "cropCelery"),
    seedCorn("seedCorn", "cropCorn"),
    seedCucumber("seedCucumber", "cropCucumber"),
    seedEggplant("seedEggplant", "cropEggplant"),
    seedGreenBean("seedGreenBean", "cropGreenBeans"),
    seedLeek("seedLeek", "cropLeek"),
    seedLettuce("seedLettuce", "cropLettuce"),
    seedOnion("seedOnion", "cropOnion"),
    seedSorrel("seedSorrel", "cropSorrel"),
    seedSpinach("seedSpinach", "cropSpinach"),
    seedTomato("seedTomato", "cropTomato");

    public final String unlocalizedName;
    public final String parentBlock;

    private PMPCropSeed(String unlocalizedName, String parentBlock) {
        this.unlocalizedName = unlocalizedName;
        this.parentBlock = parentBlock;
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal("item." + this.unlocalizedName + ".name");
    }
}
