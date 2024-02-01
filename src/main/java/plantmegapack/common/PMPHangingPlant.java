package plantmegapack.common;

public enum PMPHangingPlant {

    HANGING_FLOWER_BURGUNDY("hangingFlowersMIX", 1),
    HANGING_FLOWER_RED("hangingFlowersRED", 1),
    HANGING_FLOWER_CORAL("hangingFlowersCOR", 1),
    HANGING_FLOWER_ORANGE("hangingFlowersORA", 1),
    HANGING_FLOWER_YELLOW("hangingFlowersYEL", 1),
    HANGING_FLOWER_GREEN("hangingFlowersGRN", 1),
    HANGING_FLOWER_CYAN("hangingFlowersCYA", 1),
    HANGING_FLOWER_BLUE("hangingFlowersBLU", 1),
    HANGING_FLOWER_ROYAL("hangingFlowersROY", 1),
    HANGING_FLOWER_PURPLE("hangingFlowersPUR", 1),
    HANGING_FLOWER_MAGENTA("hangingFlowersMAG", 1),
    HANGING_FLOWER_PINK("hangingFlowersPNK", 1),
    HANGING_FLOWER_WHITE("hangingFlowersWHT", 1),
    HANGING_BLUE_WHEATGRASS("hangingBlueWheatgrass", 1),
    HANGING_BRITTLEBUSH("hangingBrittlebush", 1),
    HANGING_KRISPLANT("hangingKrisPlant", 1),
    HANGING_LAVENDER("hangingLavender", 1),
    HANGING_PRICKLYPEAR("hangingPricklyPear", 1),
    HANGING_SCALYTREE("hangingScalyTree", 1),
    HANGING_PHILODENDRON("hangingPhilodendron", 1),
    HANGING_WILDFIRE("hangingWildfire", 1),
    HANGING_WOLFWILLOW("hangingWolfWillow", 1),
    HANGING_FOLIAGE_GRN_SM("hangingFoliageSMGRN", 1),
    HANGING_FOLIAGE_GRN_MED("hangingFoliageMDGRN", 1),
    HANGING_FOLIAGE_GRN_LG("hangingFoliageLGGRN", 1),
    HANGING_FOLIAGE_YEL_LG("hangingFoliageLGYEL", 1),
    HANGING_FOLIAGE_RED_LG("hangingFoliageLGRED", 1),
    HANGING_BAMBOO_ASPER("hangingBambooAsper", 1),
    HANGING_BAMBOO_FARGESIAROBUSTA("hangingBambooFargesiaRobusta", 1),
    HANGING_BAMBOO_GIANTTIMBER("hangingBambooGiantTimber", 1),
    HANGING_BAMBOO_GOLDEN("hangingBambooGolden", 1),
    HANGING_BAMBOO_MOSO("hangingBambooMoso", 1),
    HANGING_BAMBOO_SHORTTASSLED("hangingBambooShortTassled", 1),
    HANGING_BAMBOO_TIMORBLACK("hangingBambooTimorBlack", 1),
    HANGING_BAMBOO_TROPICALBLUE("hangingBambooTropicalBlue", 1),
    HANGING_BAMBOO_WETFOREST("hangingBambooWetForest", 1);

    public final String ID;
    public final int addToGame;

    private PMPHangingPlant(String ID, int addToGame) {
        this.ID = ID;
        this.addToGame = addToGame;
    }
}
