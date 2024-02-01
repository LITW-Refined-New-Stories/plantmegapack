package plantmegapack.common;

import net.minecraft.util.StatCollector;

public enum PMPPlantItem {

    flowerBlueStar("flowerBlueStarItem", PMPPlantItemType.flower),
    flowerBroomSnakeweed("flowerBroomSnakeweed", PMPPlantItemType.flower),
    flowerCattail("flowerCattail", PMPPlantItemType.flower),
    flowerOcotillo("flowerOcotillo", PMPPlantItemType.flower),
    flowerTorchGinger("flowerTorchGinger", PMPPlantItemType.flower),
    flowerYellowToadflax("flowerYellowToadflaxItem", PMPPlantItemType.flower),
    leafAloe("leafAloe", PMPPlantItemType.flower),
    leafWildMint("leafWildMint", PMPPlantItemType.flower);

    public final String unlocalizedName;
    public final PMPPlantItemType itemType;

    private PMPPlantItem(String unlocalizedName, PMPPlantItemType itemType) {
        this.unlocalizedName = unlocalizedName;
        this.itemType = itemType;
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal("item." + this.unlocalizedName + ".name");
    }
}
