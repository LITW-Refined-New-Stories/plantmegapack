package plantmegapack.common;

import cpw.mods.fml.common.registry.LanguageRegistry;

public enum PMPDecoratorArea {

    EPIPHYTE("epiphyte"),
    PRIMARY("primary"),
    SHADE("shade"),
    WATER_EDGE("waterEdge"),
    WATER_FLOATING("waterFloating"),
    WATER_IMMERSED("waterImmersed"),
    WATER_SUBMERGED("waterSubmerged"),
    TREE("tree"),
    VINE("vine");

    public final String ID;

    private PMPDecoratorArea(String ID) {
        this.ID = ID;
    }

    public String getLocalizedName() {
        return LanguageRegistry.instance()
            .getStringLocalization("decorator.area." + this.ID);
    }
}
