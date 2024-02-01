package plantmegapack.bin;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPCoralFragment;
import plantmegapack.common.PMPCropSeed;
import plantmegapack.common.PMPFood;
import plantmegapack.common.PMPFoodType;
import plantmegapack.common.PMPModInfo;
import plantmegapack.common.PMPPlantItem;
import plantmegapack.common.PMPPlantPowder;
import plantmegapack.common.PMPSalve;
import plantmegapack.item.PMPItemCoralFragment;
import plantmegapack.item.PMPItemCropSeed;
import plantmegapack.item.PMPItemFood;
import plantmegapack.item.PMPItemPlantItem;
import plantmegapack.item.PMPItemPowder;
import plantmegapack.item.PMPItemSalve;

public class PMPItems {

    private Map<String, PMPItemCropSeed> cropSeeds = new HashMap();
    private Map<String, PMPItemFood> foodItems = new HashMap();
    private Map<String, PMPItemCoralFragment> coralFragments = new HashMap();
    private Map<String, PMPItemPowder> powders = new HashMap();
    private Map<String, PMPItemSalve> salves = new HashMap();
    private Map<String, PMPItemPlantItem> plantItems = new HashMap();

    public PMPItems() {
        int var2;
        int var3;
        if (PlantMegaPack.settingsGeneral.contentCrops) {
            PMPCropSeed[] var1 = PMPCropSeed.values();
            var2 = var1.length;

            for (var3 = 0; var3 < var2; ++var3) {
                PMPCropSeed seeds = var1[var3];
                this.cropSeeds.put(
                    seeds.unlocalizedName,
                    new PMPItemCropSeed(PlantMegaPack.blocks.getPlantBlock(seeds.parentBlock), seeds.unlocalizedName));
                PMPModInfo.addToRegisteredItems(1);
            }
        }

        PMPFood[] var5 = PMPFood.values();
        var2 = var5.length;

        for (var3 = 0; var3 < var2; ++var3) {
            PMPFood food = var5[var3];
            if ((food.foodType != PMPFoodType.aquatic || PlantMegaPack.settingsGeneral.contentAquatic)
                && (food.foodType != PMPFoodType.crop || PlantMegaPack.settingsGeneral.contentCrops)) {
                this.foodItems.put(food.unlocalizedName, new PMPItemFood(food));
                PMPModInfo.addToRegisteredItems(1);
            }
        }

        if (PlantMegaPack.settingsGeneral.contentAquatic) {
            PMPCoralFragment[] var6 = PMPCoralFragment.values();
            var2 = var6.length;

            for (var3 = 0; var3 < var2; ++var3) {
                PMPCoralFragment coralFragment = var6[var3];
                this.coralFragments.put(coralFragment.unlocalizedName, new PMPItemCoralFragment(coralFragment));
                PMPModInfo.addToRegisteredItems(1);
            }
        }

        PMPPlantPowder[] var7 = PMPPlantPowder.values();
        var2 = var7.length;

        for (var3 = 0; var3 < var2; ++var3) {
            PMPPlantPowder powder = var7[var3];
            this.powders.put(powder.unlocalizedName, new PMPItemPowder(powder));
            PMPModInfo.addToRegisteredItems(1);
        }

        PMPSalve[] var8 = PMPSalve.values();
        var2 = var8.length;

        for (var3 = 0; var3 < var2; ++var3) {
            PMPSalve salve = var8[var3];
            this.salves.put(salve.unlocalizedName, new PMPItemSalve(salve));
            PMPModInfo.addToRegisteredItems(1);
        }

        PMPPlantItem[] var9 = PMPPlantItem.values();
        var2 = var9.length;

        for (var3 = 0; var3 < var2; ++var3) {
            PMPPlantItem plantItem = var9[var3];
            this.plantItems.put(plantItem.unlocalizedName, new PMPItemPlantItem(plantItem));
            PMPModInfo.addToRegisteredItems(1);
        }

        PMPModInfo.addToRegisteredChestItems(this.addItemsToSpawnedChests());
    }

    public PMPItemCoralFragment getCoralFragment(String unlocalizedName) {
        return this.coralFragments == null ? null : (PMPItemCoralFragment) this.coralFragments.get(unlocalizedName);
    }

    public PMPItemCropSeed getCropSeed(String unlocalizedName) {
        return this.cropSeeds == null ? null : (PMPItemCropSeed) this.cropSeeds.get(unlocalizedName);
    }

    public PMPItemCropSeed getCropSeedFromPlantBlock(String unlocalizedName) {
        if (this.cropSeeds == null) {
            return null;
        } else {
            PMPCropSeed[] var2 = PMPCropSeed.values();
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                PMPCropSeed seed = var2[var4];
                if (unlocalizedName.matches(seed.parentBlock)) {
                    return (PMPItemCropSeed) this.cropSeeds.get(seed.unlocalizedName);
                }
            }

            return null;
        }
    }

    public PMPItemFood getFoodItem(String unlocalizedName) {
        return this.foodItems == null ? null : (PMPItemFood) this.foodItems.get(unlocalizedName);
    }

    public PMPItemPowder getPlantPowder(String unlocalizedName) {
        return this.powders == null ? null : (PMPItemPowder) this.powders.get(unlocalizedName);
    }

    public PMPItemSalve getSalve(String unlocalizedName) {
        return this.salves == null ? null : (PMPItemSalve) this.salves.get(unlocalizedName);
    }

    public PMPItemPlantItem getPlantItem(String unlocalizedName) {
        return this.plantItems == null ? null : (PMPItemPlantItem) this.plantItems.get(unlocalizedName);
    }

    private int addItemsToSpawnedChests() {
        int chestItems = 0;
        ChestGenHooks.getInfo("pyramidDesertyChest")
            .addItem(new WeightedRandomChestContent(new ItemStack((Item) this.plantItems.get("leafAloe")), 1, 1, 2));
        ChestGenHooks.getInfo("pyramidJungleChest")
            .addItem(new WeightedRandomChestContent(new ItemStack((Item) this.plantItems.get("leafAloe")), 1, 1, 2));
        ChestGenHooks.getInfo("villageBlacksmith")
            .addItem(new WeightedRandomChestContent(new ItemStack((Item) this.plantItems.get("leafAloe")), 1, 1, 2));
        chestItems += 3;
        ChestGenHooks.getInfo("pyramidDesertyChest")
            .addItem(
                new WeightedRandomChestContent(
                    new ItemStack((Item) this.foodItems.get("berriesHarlequinMistletoe")),
                    1,
                    1,
                    2));
        ChestGenHooks.getInfo("pyramidDesertyChest")
            .addItem(
                new WeightedRandomChestContent(
                    new ItemStack((Item) this.foodItems.get("foodPricklyPearFruit")),
                    1,
                    1,
                    2));
        chestItems += 2;
        if (PlantMegaPack.settingsGeneral.contentCrops && PlantMegaPack.settingsGeneral.contentChestSeeds) {
            ChestGenHooks.getInfo("dungeonChest")
                .addItem(new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedBeet")), 1, 2, 2));
            ChestGenHooks.getInfo("dungeonChest")
                .addItem(
                    new WeightedRandomChestContent(
                        new ItemStack((Item) this.cropSeeds.get("seedBellPepperYellow")),
                        1,
                        2,
                        2));
            ChestGenHooks.getInfo("dungeonChest")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedCelery")), 1, 2, 2));
            ChestGenHooks.getInfo("dungeonChest")
                .addItem(new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedCorn")), 1, 2, 2));
            ChestGenHooks.getInfo("dungeonChest")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedCucumber")), 1, 2, 2));
            ChestGenHooks.getInfo("dungeonChest")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedLettuce")), 1, 2, 2));
            ChestGenHooks.getInfo("dungeonChest")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedOnion")), 1, 2, 2));
            ChestGenHooks.getInfo("dungeonChest")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedSpinach")), 1, 2, 2));
            ChestGenHooks.getInfo("dungeonChest")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedTomato")), 1, 2, 2));
            chestItems += 9;
            ChestGenHooks.getInfo("mineshaftCorridor")
                .addItem(new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedBeet")), 1, 2, 2));
            ChestGenHooks.getInfo("mineshaftCorridor")
                .addItem(
                    new WeightedRandomChestContent(
                        new ItemStack((Item) this.cropSeeds.get("seedBellPepperYellow")),
                        1,
                        2,
                        2));
            ChestGenHooks.getInfo("mineshaftCorridor")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedCelery")), 1, 2, 2));
            ChestGenHooks.getInfo("mineshaftCorridor")
                .addItem(new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedCorn")), 1, 2, 2));
            ChestGenHooks.getInfo("mineshaftCorridor")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedCucumber")), 1, 2, 2));
            ChestGenHooks.getInfo("mineshaftCorridor")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedLettuce")), 1, 2, 2));
            ChestGenHooks.getInfo("mineshaftCorridor")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedOnion")), 1, 2, 2));
            ChestGenHooks.getInfo("mineshaftCorridor")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedSpinach")), 1, 2, 2));
            ChestGenHooks.getInfo("mineshaftCorridor")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedTomato")), 1, 2, 2));
            chestItems += 9;
            ChestGenHooks.getInfo("villageBlacksmith")
                .addItem(new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedBeet")), 1, 2, 3));
            ChestGenHooks.getInfo("villageBlacksmith")
                .addItem(
                    new WeightedRandomChestContent(
                        new ItemStack((Item) this.cropSeeds.get("seedBellPepperYellow")),
                        1,
                        2,
                        3));
            ChestGenHooks.getInfo("villageBlacksmith")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedCelery")), 1, 2, 3));
            ChestGenHooks.getInfo("villageBlacksmith")
                .addItem(new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedCorn")), 1, 2, 3));
            ChestGenHooks.getInfo("villageBlacksmith")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedCucumber")), 1, 2, 3));
            ChestGenHooks.getInfo("villageBlacksmith")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedLettuce")), 1, 2, 3));
            ChestGenHooks.getInfo("villageBlacksmith")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedOnion")), 1, 2, 3));
            ChestGenHooks.getInfo("villageBlacksmith")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedSpinach")), 1, 2, 3));
            ChestGenHooks.getInfo("villageBlacksmith")
                .addItem(
                    new WeightedRandomChestContent(new ItemStack((Item) this.cropSeeds.get("seedTomato")), 1, 2, 3));
            chestItems += 9;
        }

        return chestItems;
    }
}
