package plantmegapack.common;

import net.minecraft.util.StatCollector;

public enum PMPTab {

    plants("plants", 1, "shrubBoxwood"),
    flowers("flowers", 1, "flowerPurpleConeflower"),
    aquatic("aquatic", 1, "floatingWaterLettuce"),
    crops("crops", 1, "cropTomato"),
    food("food", 1, "food"),
    trees("trees", 0, ""),
    build("build", 1, "wallBracketWoodOak"),
    end("end", 0, ""),
    fantasy("fantasy", 0, ""),
    nether("nether", 0, "");

    public final String unlocalizedName;
    public final int addToGame;
    public final String iconBlockID;

    private PMPTab(String unlocalizedName, int addToGame, String iconBlockID) {
        this.unlocalizedName = unlocalizedName;
        this.addToGame = addToGame;
        this.iconBlockID = iconBlockID;
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal("creativeTab." + this.unlocalizedName);
    }
}
