package plantmegapack.common;

import net.minecraft.util.StatCollector;

public enum PMPPlantPowder {

    powderAloe("powderAloe"),
    powderBerry("powderBerry"),
    powderCactus("powderCactus"),
    powderCoral("powderCoral"),
    powderFern("powderFern"),
    powderLeaf("powderLeaf"),
    powderMoss("powderMoss"),
    powderMushroom("powderMushroom"),
    powderMushroomPoison("powderMushroomPoison"),
    powderConditioner("powderConditioner"),
    powderDefoliant("powderDefoliant"),
    powderFertilizer("powderFertilizer");

    public final String unlocalizedName;

    private PMPPlantPowder(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }

    public String getLocalizedName() {
        return StatCollector.translateToLocal("item." + this.unlocalizedName + ".name");
    }
}
