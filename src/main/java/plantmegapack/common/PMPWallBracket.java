package plantmegapack.common;

public enum PMPWallBracket {

    BAMBOO_ASPER("wallBracketBambooAsper", true, 0),
    BAMBOO_FARGESIAROBUSTA("wallBracketBambooFargesiaRobusta", true, 0),
    BAMBOO_GIANTTIMBER("wallBracketBambooGiantTimber", true, 0),
    BAMBOO_GOLDEN("wallBracketBambooGolden", true, 0),
    BAMBOO_MOSO("wallBracketBambooMoso", true, 0),
    BAMBOO_SHORTTASSLED("wallBracketBambooShortTassled", true, 0),
    BAMBOO_TIMORBLACK("wallBracketBambooTimorBlack", true, 0),
    BAMBOO_TROPICALBLUE("wallBracketBambooTropicalBlue", true, 0),
    BAMBOO_WETFOREST("wallBracketBambooWetForest", true, 0),
    STONE("wallBracketStone", true, 0),
    WOOD_ACACIA("wallBracketWoodAcacia", true, 0),
    WOOD_BIRCH("wallBracketWoodBirch", true, 0),
    WOOD_DARKOAK("wallBracketWoodDarkOak", true, 0),
    WOOD_JUNGLE("wallBracketWoodJungle", true, 0),
    WOOD_OAK("wallBracketWoodOak", true, 0),
    WOOD_SPRUCE("wallBracketWoodSpruce", true, 0),
    METAL_IRONCURL("wallBracketIronCurl", true, 1),
    METAL_GOLDCURL("wallBracketGoldCurl", true, 1),
    DIAMOND("wallBracketDiamond", false, 0),
    GOLD("wallBracketGold", false, 0),
    ICE("wallBracketIce", false, 0),
    IRON("wallBracketIron", false, 0),
    LAPIS("wallBracketLapis", false, 0),
    NETHERBRICK("wallBracketNetherbrick", false, 0),
    OBSIDIAN("wallBracketObsidian", false, 0),
    QUARTZ("wallBracketQuartz", false, 0),
    SANDSTONE("wallBracketSandstone", false, 0);

    public final String ID;
    public boolean alwaysCreate;
    public final int renderType;

    private PMPWallBracket(String ID, boolean alwaysCreate, int renderType) {
        this.ID = ID;
        this.alwaysCreate = alwaysCreate;
        this.renderType = renderType;
    }
}
