package plantmegapack.common;

import cpw.mods.fml.common.Loader;
import plantmegapack.PlantMegaPack;

public abstract class PMPModInfo {

    public static final String LOGGER_NAME = "PlantMegaPack";
    private static int statRegisteredBlocks = 0;
    private static int statRegisteredItems = 0;
    private static int statRegisteredPlants = 0;
    private static int statRegisteredRecipes = 0;
    private static int statRegisteredChestItems = 0;
    private static Boolean[] modInstalled = new Boolean[PMPDecoratorSet.values().length];

    public static void addToRegisteredBlocks(int quantity) {
        if (quantity > 0) {
            statRegisteredBlocks += quantity;
        }

    }

    public static void addToRegisteredItems(int quantity) {
        if (quantity > 0) {
            statRegisteredItems += quantity;
        }

    }

    public static void addToRegisteredPlants(int quantity) {
        if (quantity > 0) {
            statRegisteredPlants += quantity;
        }

    }

    public static void addToRegisteredRecipes(int quantity) {
        if (quantity > 0) {
            statRegisteredRecipes += quantity;
        }

    }

    public static void addToRegisteredChestItems(int quantity) {
        if (quantity > 0) {
            statRegisteredChestItems += quantity;
        }

    }

    public static int getRegisteredBlocks() {
        return statRegisteredBlocks;
    }

    public static int getRegisteredCategories() {
        int categoryCount = 0;
        PMPPlantCategory[] var1 = PMPPlantCategory.values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            PMPPlantCategory category = var1[var3];
            if (category.addToGame > 0) {
                ++categoryCount;
            }
        }

        return categoryCount;
    }

    public static int getRegisteredItems() {
        return statRegisteredItems;
    }

    public static int getRegisteredPlants() {
        return statRegisteredPlants;
    }

    public static int getRegisteredRecipes() {
        return statRegisteredRecipes;
    }

    public static int getRegisteredChestItems() {
        return statRegisteredChestItems;
    }

    public static void initModsInstalled() {
        modInstalled[0] = Loader.isModLoaded("BiomesOPlenty");
        modInstalled[1] = Loader.isModLoaded("enhancedbiomes");
        modInstalled[2] = Loader.isModLoaded("ExtrabiomesXL");
        modInstalled[3] = Loader.isModLoaded("Highlands");
        modInstalled[4] = true;
    }

    public static boolean isModInstalled(PMPDecoratorSet decoratorSet) {
        return modInstalled[decoratorSet.ordinal()];
    }

    public static void outputModStatisticsToConsole() {
        PlantMegaPack.instance.logOutput(String.format("Building blocks    : %d", getRegisteredBlocks()));
        PlantMegaPack.instance.logOutput(String.format("Plant categories   : %d", getRegisteredCategories()));
        PlantMegaPack.instance.logOutput(String.format("Plant blocks       : %d", getRegisteredPlants()));
        PlantMegaPack.instance.logOutput(String.format("Items              : %d", getRegisteredItems()));
        PlantMegaPack.instance.logOutput(String.format("Recipes            : %d", getRegisteredRecipes()));
        PlantMegaPack.instance.logOutput(String.format("Chest items        : %d", getRegisteredChestItems()));
        PlantMegaPack.instance
            .logOutput(String.format("Worldgen decorators: %d", PlantMegaPack.worldGenerator.getDecoratorsCount()));
    }
}
