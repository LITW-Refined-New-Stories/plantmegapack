package plantmegapack.common;

public enum PMPFoodType {

    aquatic("aquatic"),
    basic("basic"),
    crop("crop"),
    drink("drink");

    public final String unlocalizedName;

    private PMPFoodType(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }
}
