package plantmegapack.bin;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPFood;
import plantmegapack.common.PMPModInfo;
import plantmegapack.item.PMPItemFood;

public class PMPRecipes {

    public PMPRecipes() {
        int recipes = CraftingManager.getInstance()
            .getRecipeList()
            .size();
        this.initAquaticRecipes();
        this.initBambooRecipes();
        this.initBerryBowls();
        this.initCoralFragments();
        this.initCropRecipes();
        this.initDesserts();
        this.initDrinks();
        this.initFermentedSpiderEye();
        this.initFoodStirFry();
        this.initFoodWraps();
        this.initMushroomSoup();
        this.initPlantPowders();
        this.initPreparedFoods();
        this.initSandwiches();
        this.initSalves();
        this.initStuffedPeppers();
        this.initWallBrackets();
        this.initVanillaHangingPlants();
        this.initPlantSpecialRecipes();
        this.initFoodSpecialRecipes();
        PMPModInfo.addToRegisteredRecipes(
            CraftingManager.getInstance()
                .getRecipeList()
                .size() - recipes);
    }

    private void initAquaticRecipes() {
        if (PlantMegaPack.settingsGeneral.contentAquatic) {
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodMozuku"), 1, 0),
                        new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("saltwaterMozuku"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodMozukuSoup"), 1, 0),
                        new Object[] { new ItemStack(Items.bowl, 1, 0),
                            PlantMegaPack.items.getFoodItem("foodMozukuSeaweed"),
                            PlantMegaPack.items.getFoodItem("foodMozukuSeaweed") }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodWaterChestnut"), 1, 0),
                        new Object[] {
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("floatingWaterChestnut"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodLettuce"), 1, 0),
                        new Object[] {
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("floatingWaterLettuce"), 1, 0) }));
        }

    }

    private void initBambooRecipes() {
        if (PlantMegaPack.settingsGeneral.contentCraftingBambooBlocks) {
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooAsper"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooAsperSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getBambooPole("bambooAsperPole"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(
                            PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooFargesiaRobusta"),
                            1,
                            0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooFargesiaRobustaSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getBambooPole("bambooFargesiaRobustaPole"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooGiantTimber"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooGiantTimberSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getBambooPole("bambooGiantTimberPole"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooGolden"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooGoldenSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getBambooPole("bambooGoldenPole"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooMoso"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooMosoSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getBambooPole("bambooMosoPole"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooShortTassled"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooShortTassledSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getBambooPole("bambooShortTassledPole"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooTimorBlack"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooTimorBlackSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getBambooPole("bambooTimorBlackPole"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooTropicalBlue"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooTropicalBlueSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getBambooPole("bambooTropicalBluePole"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooWetForest"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooWetForestSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getBambooPole("bambooWetForestPole"), 1, 0) }));
        }

    }

    private void initBerryBowls() {
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBeauty"),
                        PlantMegaPack.items.getFoodItem("berriesBeauty") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBeauty"),
                        PlantMegaPack.items.getFoodItem("berriesBlack") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBeauty"),
                        PlantMegaPack.items.getFoodItem("berriesBlue") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBeauty"),
                        PlantMegaPack.items.getFoodItem("berriesElder") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBeauty"),
                        PlantMegaPack.items.getFoodItem("berriesGoose") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBeauty"),
                        PlantMegaPack.items.getFoodItem("berriesHarlequinMistletoe") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBeauty"),
                        PlantMegaPack.items.getFoodItem("berriesHuckle") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBeauty"),
                        PlantMegaPack.items.getFoodItem("berriesOrange") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBeauty"),
                        PlantMegaPack.items.getFoodItem("berriesSnow") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBeauty"),
                        PlantMegaPack.items.getFoodItem("berriesStraw") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlack"),
                        PlantMegaPack.items.getFoodItem("berriesBlack") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlack"),
                        PlantMegaPack.items.getFoodItem("berriesBlue") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlack"),
                        PlantMegaPack.items.getFoodItem("berriesElder") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlack"),
                        PlantMegaPack.items.getFoodItem("berriesGoose") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlack"),
                        PlantMegaPack.items.getFoodItem("berriesHarlequinMistletoe") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlack"),
                        PlantMegaPack.items.getFoodItem("berriesHuckle") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlack"),
                        PlantMegaPack.items.getFoodItem("berriesOrange") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlack"),
                        PlantMegaPack.items.getFoodItem("berriesSnow") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlack"),
                        PlantMegaPack.items.getFoodItem("berriesStraw") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlue"),
                        PlantMegaPack.items.getFoodItem("berriesBlue") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlue"),
                        PlantMegaPack.items.getFoodItem("berriesElder") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlue"),
                        PlantMegaPack.items.getFoodItem("berriesGoose") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlue"),
                        PlantMegaPack.items.getFoodItem("berriesHarlequinMistletoe") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlue"),
                        PlantMegaPack.items.getFoodItem("berriesHuckle") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlue"),
                        PlantMegaPack.items.getFoodItem("berriesOrange") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlue"),
                        PlantMegaPack.items.getFoodItem("berriesSnow") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesBlue"),
                        PlantMegaPack.items.getFoodItem("berriesStraw") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesElder"),
                        PlantMegaPack.items.getFoodItem("berriesElder") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesElder"),
                        PlantMegaPack.items.getFoodItem("berriesGoose") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesElder"),
                        PlantMegaPack.items.getFoodItem("berriesHarlequinMistletoe") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesElder"),
                        PlantMegaPack.items.getFoodItem("berriesHuckle") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesElder"),
                        PlantMegaPack.items.getFoodItem("berriesOrange") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesElder"),
                        PlantMegaPack.items.getFoodItem("berriesSnow") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesElder"),
                        PlantMegaPack.items.getFoodItem("berriesStraw") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesGoose"),
                        PlantMegaPack.items.getFoodItem("berriesGoose") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesGoose"),
                        PlantMegaPack.items.getFoodItem("berriesHarlequinMistletoe") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesGoose"),
                        PlantMegaPack.items.getFoodItem("berriesHuckle") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesGoose"),
                        PlantMegaPack.items.getFoodItem("berriesOrange") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesGoose"),
                        PlantMegaPack.items.getFoodItem("berriesSnow") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesGoose"),
                        PlantMegaPack.items.getFoodItem("berriesStraw") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.items.getFoodItem("berriesHarlequinMistletoe"),
                        PlantMegaPack.items.getFoodItem("berriesHarlequinMistletoe") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.items.getFoodItem("berriesHarlequinMistletoe"),
                        PlantMegaPack.items.getFoodItem("berriesHuckle") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.items.getFoodItem("berriesHarlequinMistletoe"),
                        PlantMegaPack.items.getFoodItem("berriesOrange") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.items.getFoodItem("berriesHarlequinMistletoe"),
                        PlantMegaPack.items.getFoodItem("berriesSnow") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.items.getFoodItem("berriesHarlequinMistletoe"),
                        PlantMegaPack.items.getFoodItem("berriesStraw") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesHuckle"),
                        PlantMegaPack.items.getFoodItem("berriesHuckle") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesHuckle"),
                        PlantMegaPack.items.getFoodItem("berriesOrange") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesHuckle"),
                        PlantMegaPack.items.getFoodItem("berriesSnow") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesHuckle"),
                        PlantMegaPack.items.getFoodItem("berriesStraw") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesOrange"),
                        PlantMegaPack.items.getFoodItem("berriesOrange") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesOrange"),
                        PlantMegaPack.items.getFoodItem("berriesSnow") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesOrange"),
                        PlantMegaPack.items.getFoodItem("berriesStraw") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesSnow"),
                        PlantMegaPack.items.getFoodItem("berriesSnow") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesSnow"),
                        PlantMegaPack.items.getFoodItem("berriesStraw") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBerrybowl"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("berriesStraw"),
                        PlantMegaPack.items.getFoodItem("berriesStraw") }));
    }

    private void initCoralFragments() {
        if (PlantMegaPack.settingsGeneral.contentAquatic) {
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentBlueStaghorn"), 2, 0),
                        new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("coralBlueStaghorn"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentElegance"), 2, 0),
                        new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("coralElegance"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentFeatheredHydroid"), 2, 0),
                        new Object[] {
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("coralFeatheredHydroid"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentPillar"), 2, 0),
                        new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("coralPillar"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentPulsingXenia"), 2, 0),
                        new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("waterPulsingXenia"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentPurpleSeaWhip"), 2, 0),
                        new Object[] {
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("waterPurpleSeaWhip"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentRedSeaFan"), 2, 0),
                        new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("waterRedSeaFan"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentSunPolyps"), 2, 0),
                        new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("coralSunPolyps"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentYellowBamboo"), 2, 0),
                        new Object[] {
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("waterBambooCoralYEL"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.dye, 1, 12),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentBlueStaghorn"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.dye, 1, 14),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentElegance"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.dye, 1, 2),
                        new Object[] { new ItemStack(
                            PlantMegaPack.items.getCoralFragment("coralFragmentFeatheredHydroid"),
                            1,
                            0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.dye, 1, 1),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentPillar"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.dye, 1, 7),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentPulsingXenia"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.dye, 1, 5),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentPurpleSeaWhip"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.dye, 1, 1),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentRedSeaFan"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.dye, 1, 14),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentSunPolyps"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.dye, 1, 11),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentYellowBamboo"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.arrow, 1, 0),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentBlueStaghorn"), 1, 0),
                            new ItemStack(Items.stick, 1, 0), new ItemStack(Items.feather, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.arrow, 1, 0),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentElegance"), 1, 0),
                            new ItemStack(Items.stick, 1, 0), new ItemStack(Items.feather, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.arrow, 1, 0),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentFeatheredHydroid"), 1, 0),
                            new ItemStack(Items.stick, 1, 0), new ItemStack(Items.feather, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.arrow, 1, 0),
                        new Object[] { new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentPillar"), 1, 0),
                            new ItemStack(Items.stick, 1, 0), new ItemStack(Items.feather, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.arrow, 1, 0),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentPulsingXenia"), 1, 0),
                            new ItemStack(Items.stick, 1, 0), new ItemStack(Items.feather, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.arrow, 1, 0),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentPurpleSeaWhip"), 1, 0),
                            new ItemStack(Items.stick, 1, 0), new ItemStack(Items.feather, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.arrow, 1, 0),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentRedSeaFan"), 1, 0),
                            new ItemStack(Items.stick, 1, 0), new ItemStack(Items.feather, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.arrow, 1, 0),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentSunPolyps"), 1, 0),
                            new ItemStack(Items.stick, 1, 0), new ItemStack(Items.feather, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.arrow, 1, 0),
                        new Object[] {
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentYellowBamboo"), 1, 0),
                            new ItemStack(Items.stick, 1, 0), new ItemStack(Items.feather, 1, 0) }));
        }

    }

    private void initCropRecipes() {
        if (PlantMegaPack.settingsGeneral.contentCrops) {
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodCornFlour"), 1, 0),
                        new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem("foodCorn")) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodCornBread"), 1, 0),
                        new Object[] { "   ", "   ", "xxx", 'x', PlantMegaPack.items.getFoodItem("foodCornFlour") }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodBeetSoup"), 1, 0),
                        new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("foodBeet"),
                            PlantMegaPack.items.getFoodItem("foodBeet") }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodBroccoliSoup"), 1, 0),
                        new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("foodBroccoli"),
                            PlantMegaPack.items.getFoodItem("foodBroccoli") }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodTomatoSoup"), 1, 0),
                        new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("foodTomato"),
                            PlantMegaPack.items.getFoodItem("foodTomato") }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodCookedRice"), 1, 0),
                        new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodCookedRice"), 1, 0),
                        new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodCookedRice"), 1, 0),
                        new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodCookedRice"), 1, 0),
                        new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.paper, 3, 0),
                        new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(Items.paper, 3, 0),
                        new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), 1, 0) }));
        }

    }

    private void initDesserts() {
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBeautyberryTurnover"), 2, 0),
                    new Object[] { "   ", "wxw", "yzy", 'w', PlantMegaPack.items.getFoodItem("berriesBeauty"), 'x',
                        new ItemStack(Items.sugar), 'y', new ItemStack(Items.wheat), 'z', new ItemStack(Items.egg) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBlackberryDanish"), 2, 0),
                    new Object[] { "   ", "wxw", "yzy", 'w', PlantMegaPack.items.getFoodItem("berriesBlack"), 'x',
                        new ItemStack(Items.sugar), 'y', new ItemStack(Items.wheat), 'z', new ItemStack(Items.egg) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBlueberryMuffin"), 6, 0),
                    new Object[] { PlantMegaPack.items.getFoodItem("berriesBlue"),
                        PlantMegaPack.items.getFoodItem("berriesBlue"), new ItemStack(Items.bread) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodBlueberryMuffin"), 6, 0),
                    new Object[] { PlantMegaPack.items.getFoodItem("berriesBlue"),
                        PlantMegaPack.items.getFoodItem("berriesBlue"),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodCornBread")) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodElderberrySorbet"), 1, 0),
                    new Object[] { " w ", "xyx", " z ", 'w', new ItemStack(Items.sugar), 'x',
                        PlantMegaPack.items.getFoodItem("berriesElder"), 'y', new ItemStack(Items.milk_bucket), 'z',
                        new ItemStack(Items.bowl) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodGooseberryCobbler"), 2, 0),
                    new Object[] { "   ", "wxw", "yzy", 'w', PlantMegaPack.items.getFoodItem("berriesGoose"), 'x',
                        new ItemStack(Items.sugar), 'y', new ItemStack(Items.wheat), 'z', new ItemStack(Items.egg) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodHuckleberryTart"), 2, 0),
                    new Object[] { "   ", "wxw", "yzy", 'w', PlantMegaPack.items.getFoodItem("berriesHuckle"), 'x',
                        new ItemStack(Items.sugar), 'y', new ItemStack(Items.wheat), 'z', new ItemStack(Items.egg) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodOrangeberrySquare"), 2, 0),
                    new Object[] { "   ", "wxw", "yzy", 'w', PlantMegaPack.items.getFoodItem("berriesOrange"), 'x',
                        new ItemStack(Items.sugar), 'y', new ItemStack(Items.wheat), 'z', new ItemStack(Items.egg) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodSnowberryCustard"), 1, 0),
                    new Object[] { " w ", "xyx", " z ", 'w', new ItemStack(Items.sugar), 'x',
                        PlantMegaPack.items.getFoodItem("berriesSnow"), 'y', new ItemStack(Items.milk_bucket), 'z',
                        new ItemStack(Items.bowl) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodStrawberryDelight"), 2, 0),
                    new Object[] { "   ", "wxw", "yzy", 'w', PlantMegaPack.items.getFoodItem("berriesStraw"), 'x',
                        new ItemStack(Items.sugar), 'y', new ItemStack(Items.wheat), 'z', new ItemStack(Items.egg) }));
    }

    private void initDrinks() {
        this.initFruitDrink("foodBeautyberryBlazer", "berriesBeauty");
        this.initFruitDrink("foodBlackberryTumbler", "berriesBlack");
        this.initFruitDrink("foodBlueberrySlushie", "berriesBlue");
        this.initFruitDrink("foodElderberrySpritzer", "berriesElder");
        this.initFruitDrink("foodGooseberryShake", "berriesGoose");
        this.initFruitDrink("foodHarlequinFizz", "berriesHarlequinMistletoe");
        this.initFruitDrink("foodHuckleberryBubbler", "berriesHuckle");
        this.initFruitDrink("foodOrangeberryWhip", "berriesOrange");
        this.initFruitDrink("foodPricklyPearTwister", "foodPricklyPearFruit");
        this.initFruitDrink("foodSnowberryCooler", "berriesSnow");
        this.initFruitDrink("foodStrawberrySmoothie", "berriesStraw");
        if (PlantMegaPack.settingsGeneral.contentCrops) {
            this.initFruitDrink("foodWasabiQuencher", "foodWasabiStem");
        }

    }

    private void initFruitDrink(String drink, String ingredient) {
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(drink), 1, 0),
                    new Object[] { "www", "xyx", " z ", 'w', PlantMegaPack.items.getFoodItem(ingredient), 'x',
                        Items.sugar, 'y', Items.milk_bucket, 'z', Items.glass_bottle }));
    }

    private void initFermentedSpiderEye() {
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.fermented_spider_eye, 1, 0),
                    new Object[] { new ItemStack(Items.sugar, 1, 0), Items.spider_eye,
                        PlantMegaPack.blocks.getPlantBlock("epiphyteArtistsConk") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.fermented_spider_eye, 1, 0),
                    new Object[] { new ItemStack(Items.sugar, 1, 0), Items.spider_eye,
                        PlantMegaPack.blocks.getPlantBlock("epiphyteSulphurShelf") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.fermented_spider_eye, 1, 0),
                    new Object[] { new ItemStack(Items.sugar, 1, 0), Items.spider_eye,
                        PlantMegaPack.blocks.getPlantBlock("epiphyteTurkeyTail") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.fermented_spider_eye, 1, 0),
                    new Object[] { new ItemStack(Items.sugar, 1, 0), Items.spider_eye,
                        PlantMegaPack.blocks.getPlantBlock("fungusBlackPowderpuff") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.fermented_spider_eye, 1, 0),
                    new Object[] { new ItemStack(Items.sugar, 1, 0), Items.spider_eye,
                        PlantMegaPack.blocks.getPlantBlock("fungusChanterelle") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.fermented_spider_eye, 1, 0),
                    new Object[] { new ItemStack(Items.sugar, 1, 0), Items.spider_eye,
                        PlantMegaPack.blocks.getPlantBlock("fungusDeathCap") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.fermented_spider_eye, 1, 0),
                    new Object[] { new ItemStack(Items.sugar, 1, 0), Items.spider_eye,
                        PlantMegaPack.blocks.getPlantBlock("fungusGiantClub") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.fermented_spider_eye, 1, 0),
                    new Object[] { new ItemStack(Items.sugar, 1, 0), Items.spider_eye,
                        PlantMegaPack.blocks.getPlantBlock("fungusParasol") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.fermented_spider_eye, 1, 0),
                    new Object[] { new ItemStack(Items.sugar, 1, 0), Items.spider_eye,
                        PlantMegaPack.blocks.getPlantBlock("fungusStinkhorn") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.fermented_spider_eye, 1, 0),
                    new Object[] { new ItemStack(Items.sugar, 1, 0), Items.spider_eye,
                        PlantMegaPack.blocks.getPlantBlock("fungusWeepingMilkCap") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.fermented_spider_eye, 1, 0),
                    new Object[] { new ItemStack(Items.sugar, 1, 0), Items.spider_eye,
                        PlantMegaPack.blocks.getPlantBlock("fungusWoodBlewit") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.fermented_spider_eye, 1, 0),
                    new Object[] { new ItemStack(Items.sugar, 1, 0), Items.spider_eye,
                        PlantMegaPack.blocks.getPlantBlock("fungusWoollyGomphus") }));
    }

    private void initFoodStirFry() {
        if (PlantMegaPack.settingsGeneral.contentCrops) {
            this.createFoodStirFryRecipes(Items.cooked_beef);
            this.createFoodStirFryRecipes(Items.cooked_chicken);
            this.createFoodStirFryRecipes(Items.cooked_fished);
            this.createFoodStirFryRecipes(Items.cooked_porkchop);
        }
    }

    private void createFoodStirFryRecipes(Item meat) {
        PMPItemFood stirFry = PlantMegaPack.items.getFoodItem("foodStirFry");
        PMPItemFood cookedRice = PlantMegaPack.items.getFoodItem("foodCookedRice");
        PMPFood[] var4 = PMPFood.values();
        int var5 = var4.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            PMPFood veg1 = var4[var6];
            if (veg1.stirFry) {
                PMPFood[] var8 = PMPFood.values();
                int var9 = var8.length;

                for (int var10 = 0; var10 < var9; ++var10) {
                    PMPFood veg2 = var8[var10];
                    if (veg2.stirFry) {
                        CraftingManager.getInstance()
                            .getRecipeList()
                            .add(
                                new ShapedOreRecipe(
                                    new ItemStack(stirFry, 1, 0),
                                    new Object[] { " v ", "wxy", " z ", 'v', new ItemStack(meat, 1, 0), 'w',
                                        new ItemStack(PlantMegaPack.items.getFoodItem(veg1.unlocalizedName), 1, 0), 'x',
                                        new ItemStack(cookedRice, 1, 0), 'y',
                                        new ItemStack(PlantMegaPack.items.getFoodItem(veg2.unlocalizedName), 1, 0), 'z',
                                        new ItemStack(Items.bowl, 1, 0) }));
                    }
                }
            }
        }

    }

    private void initFoodWraps() {
        if (PlantMegaPack.settingsGeneral.contentCrops) {
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("wrapCorn"), 3, 0),
                        new Object[] { "   ", "x x", " x ", 'x',
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodCornFlour"), 1, 0) }));
            this.createFoodWrapRecipes("wrapCorn", "wrapCornBeef", Items.cooked_beef);
            this.createFoodWrapRecipes("wrapCorn", "wrapCornChicken", Items.cooked_chicken);
            this.createFoodWrapRecipes("wrapCorn", "wrapCornFish", Items.cooked_fished);
            this.createFoodWrapRecipes("wrapCorn", "wrapCornPork", Items.cooked_porkchop);
            this.createFoodWrapRecipes("wrapCorn", "wrapCornRice", PlantMegaPack.items.getFoodItem("foodCookedRice"));
            if (PlantMegaPack.settingsGeneral.contentAquatic) {
                CraftingManager.getInstance()
                    .getRecipeList()
                    .add(
                        new ShapedOreRecipe(
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 3, 0),
                            new Object[] { "   ", "x x", " x ", 'x',
                                new ItemStack(PlantMegaPack.blocks.getPlantBlock("waterKelpGiantGRN"), 1, 0) }));
                CraftingManager.getInstance()
                    .getRecipeList()
                    .add(
                        new ShapedOreRecipe(
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 3, 0),
                            new Object[] { "   ", "x x", " x ", 'x',
                                new ItemStack(PlantMegaPack.blocks.getPlantBlock("waterKelpGiantYEL"), 1, 0) }));
                this.createFoodWrapRecipes("wrapSeaweed", "wrapBeef", Items.cooked_beef);
                this.createFoodWrapRecipes("wrapSeaweed", "wrapChicken", Items.cooked_chicken);
                this.createFoodWrapRecipes("wrapSeaweed", "wrapFish", Items.cooked_fished);
                this.createFoodWrapRecipes("wrapSeaweed", "wrapPork", Items.cooked_porkchop);
                this.createFoodWrapRecipes(
                    "wrapSeaweed",
                    "wrapRice",
                    PlantMegaPack.items.getFoodItem("foodCookedRice"));
            }

        }
    }

    private void createFoodWrapRecipes(String wrapName, String wrapCraftedName, Item meat) {
        this.createFoodWraps(wrapName, wrapCraftedName, meat, "foodBellPepperOrange");
        this.createFoodWraps(wrapName, wrapCraftedName, meat, "foodBellPepperRed");
        this.createFoodWraps(wrapName, wrapCraftedName, meat, "foodBellPepperYellow");
        this.createFoodWraps(wrapName, wrapCraftedName, meat, "foodCassavaRoot");
        this.createFoodWraps(wrapName, wrapCraftedName, meat, "foodEggplant");
        this.createFoodWraps(wrapName, wrapCraftedName, meat, "foodLeek");
        this.createFoodWraps(wrapName, wrapCraftedName, meat, "foodOnion");
        this.createFoodWraps(wrapName, wrapCraftedName, meat, "foodTomato");
    }

    private void createFoodWraps(String wrapName, String wrapCraftedName, Item meat, String veg) {
        PMPItemFood vegetable = PlantMegaPack.items.getFoodItem(veg);
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(wrapCraftedName), 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem(wrapName), 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodCelery"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(wrapCraftedName), 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem(wrapName), 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodLettuce"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(wrapCraftedName), 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem(wrapName), 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodCucumber"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(wrapCraftedName), 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem(wrapName), 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodSpinach"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(wrapCraftedName), 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem(wrapName), 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodCentellaLeaves"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(wrapCraftedName), 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem(wrapName), 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodLaksaLeaves"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(wrapCraftedName), 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem(wrapName), 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodSorrel"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(wrapCraftedName), 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem(wrapName), 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodWatercress"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
    }

    private void initMushroomSoup() {
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), Blocks.brown_mushroom,
                        PlantMegaPack.blocks.getPlantBlock("fungusChanterelle") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), Blocks.brown_mushroom,
                        PlantMegaPack.blocks.getPlantBlock("fungusGiantClub") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), Blocks.brown_mushroom,
                        PlantMegaPack.blocks.getPlantBlock("fungusParasol") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), Blocks.brown_mushroom,
                        PlantMegaPack.blocks.getPlantBlock("fungusStinkhorn") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), Blocks.brown_mushroom,
                        PlantMegaPack.blocks.getPlantBlock("fungusWeepingMilkCap") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), Blocks.brown_mushroom,
                        PlantMegaPack.blocks.getPlantBlock("fungusWoodBlewit") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), Blocks.red_mushroom,
                        PlantMegaPack.blocks.getPlantBlock("fungusChanterelle") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), Blocks.red_mushroom,
                        PlantMegaPack.blocks.getPlantBlock("fungusGiantClub") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), Blocks.red_mushroom,
                        PlantMegaPack.blocks.getPlantBlock("fungusParasol") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), Blocks.red_mushroom,
                        PlantMegaPack.blocks.getPlantBlock("fungusStinkhorn") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), Blocks.red_mushroom,
                        PlantMegaPack.blocks.getPlantBlock("fungusWeepingMilkCap") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), Blocks.red_mushroom,
                        PlantMegaPack.blocks.getPlantBlock("fungusWoodBlewit") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusChanterelle"),
                        PlantMegaPack.blocks.getPlantBlock("fungusChanterelle") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusChanterelle"),
                        PlantMegaPack.blocks.getPlantBlock("fungusGiantClub") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusChanterelle"),
                        PlantMegaPack.blocks.getPlantBlock("fungusParasol") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusChanterelle"),
                        PlantMegaPack.blocks.getPlantBlock("fungusStinkhorn") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusChanterelle"),
                        PlantMegaPack.blocks.getPlantBlock("fungusWeepingMilkCap") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusChanterelle"),
                        PlantMegaPack.blocks.getPlantBlock("fungusWoodBlewit") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusGiantClub"),
                        PlantMegaPack.blocks.getPlantBlock("fungusGiantClub") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusGiantClub"),
                        PlantMegaPack.blocks.getPlantBlock("fungusParasol") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusGiantClub"),
                        PlantMegaPack.blocks.getPlantBlock("fungusStinkhorn") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusGiantClub"),
                        PlantMegaPack.blocks.getPlantBlock("fungusWeepingMilkCap") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusGiantClub"),
                        PlantMegaPack.blocks.getPlantBlock("fungusWoodBlewit") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.blocks.getPlantBlock("fungusParasol"),
                        PlantMegaPack.blocks.getPlantBlock("fungusParasol") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.blocks.getPlantBlock("fungusParasol"),
                        PlantMegaPack.blocks.getPlantBlock("fungusStinkhorn") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.blocks.getPlantBlock("fungusParasol"),
                        PlantMegaPack.blocks.getPlantBlock("fungusWeepingMilkCap") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.blocks.getPlantBlock("fungusParasol"),
                        PlantMegaPack.blocks.getPlantBlock("fungusWoodBlewit") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusStinkhorn"),
                        PlantMegaPack.blocks.getPlantBlock("fungusStinkhorn") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusStinkhorn"),
                        PlantMegaPack.blocks.getPlantBlock("fungusWeepingMilkCap") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusStinkhorn"),
                        PlantMegaPack.blocks.getPlantBlock("fungusWoodBlewit") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusWeepingMilkCap"),
                        PlantMegaPack.blocks.getPlantBlock("fungusWeepingMilkCap") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusWeepingMilkCap"),
                        PlantMegaPack.blocks.getPlantBlock("fungusWoodBlewit") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.mushroom_stew, 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0),
                        PlantMegaPack.blocks.getPlantBlock("fungusWoodBlewit"),
                        PlantMegaPack.blocks.getPlantBlock("fungusWoodBlewit") }));
    }

    private void initPlantPowders() {
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderAloe"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("flowerCandelabraAloe"),
                        'y', Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderBerry"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.items.getFoodItem("berriesBeauty"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderBerry"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.items.getFoodItem("berriesBlack"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderBerry"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.items.getFoodItem("berriesBlue"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderBerry"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.items.getFoodItem("berriesElder"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderBerry"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.items.getFoodItem("berriesGoose"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderBerry"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        PlantMegaPack.items.getFoodItem("berriesHarlequinMistletoe"), 'y', Items.string, 'z',
                        Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderBerry"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.items.getFoodItem("berriesHuckle"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderBerry"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.items.getFoodItem("berriesOrange"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderBerry"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.items.getFoodItem("berriesSnow"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderBerry"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.items.getFoodItem("berriesStraw"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderCactus"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        PlantMegaPack.blocks.getPlantBlock("cactusArmatocereusMatucanensis"), 'y', Items.string, 'z',
                        Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderCactus"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("cactusBaseballBat"),
                        'y', Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderCactus"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        PlantMegaPack.blocks.getPlantBlock("cactusEchinocereusMetornii"), 'y', Items.string, 'z',
                        Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderCactus"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("cactusGoldenCereus"),
                        'y', Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderCactus"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("cactusGoldenSaguaro"),
                        'y', Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderCactus"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        PlantMegaPack.blocks.getPlantBlock("cactusMatucanaAureiflora"), 'y', Items.string, 'z',
                        Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderCactus"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("cactusPricklyPear"),
                        'y', Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderCactus"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("cactusSnowPole"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderCactus"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("cactusToothpick"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderCactus"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        PlantMegaPack.blocks.getPlantBlock("epiphyteClimbingCactus"), 'y', Items.string, 'z',
                        Items.leather }));
        if (PlantMegaPack.settingsGeneral.contentAquatic) {
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderCoral"), 1, 0),
                        new Object[] { "xyx", "xzx", "xxx", 'x',
                            PlantMegaPack.items.getCoralFragment("coralFragmentBlueStaghorn"), 'y', Items.string, 'z',
                            Items.leather }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderCoral"), 1, 0),
                        new Object[] { "xyx", "xzx", "xxx", 'x',
                            PlantMegaPack.items.getCoralFragment("coralFragmentElegance"), 'y', Items.string, 'z',
                            Items.leather }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderCoral"), 1, 0),
                        new Object[] { "xyx", "xzx", "xxx", 'x',
                            PlantMegaPack.items.getCoralFragment("coralFragmentFeatheredHydroid"), 'y', Items.string,
                            'z', Items.leather }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderCoral"), 1, 0),
                        new Object[] { "xyx", "xzx", "xxx", 'x',
                            PlantMegaPack.items.getCoralFragment("coralFragmentPillar"), 'y', Items.string, 'z',
                            Items.leather }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderCoral"), 1, 0),
                        new Object[] { "xyx", "xzx", "xxx", 'x',
                            PlantMegaPack.items.getCoralFragment("coralFragmentPulsingXenia"), 'y', Items.string, 'z',
                            Items.leather }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderCoral"), 1, 0),
                        new Object[] { "xyx", "xzx", "xxx", 'x',
                            PlantMegaPack.items.getCoralFragment("coralFragmentPurpleSeaWhip"), 'y', Items.string, 'z',
                            Items.leather }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderCoral"), 1, 0),
                        new Object[] { "xyx", "xzx", "xxx", 'x',
                            PlantMegaPack.items.getCoralFragment("coralFragmentRedSeaFan"), 'y', Items.string, 'z',
                            Items.leather }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderCoral"), 1, 0),
                        new Object[] { "xyx", "xzx", "xxx", 'x',
                            PlantMegaPack.items.getCoralFragment("coralFragmentSunPolyps"), 'y', Items.string, 'z',
                            Items.leather }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderCoral"), 1, 0),
                        new Object[] { "xyx", "xzx", "xxx", 'x',
                            PlantMegaPack.items.getCoralFragment("coralFragmentYellowBamboo"), 'y', Items.string, 'z',
                            Items.leather }));
        }

        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderFern"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("fernCretanBrake"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderFern"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("fernDwarfPalmetto"),
                        'y', Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderFern"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("fernHayScented"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderFern"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("fernKangaroo"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderFern"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        PlantMegaPack.blocks.getPlantBlock("fernMaidenhairSpleenwort"), 'y', Items.string, 'z',
                        Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderFern"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("fernOstrich"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderFern"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("fernScalyTree"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderFern"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("fernSword"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderFern"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("fernWoodsia"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderLeaf"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("groundcoverLeavesBRN"),
                        'y', Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderLeaf"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("groundcoverLeavesGRN"),
                        'y', Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderMoss"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x', PlantMegaPack.blocks.getPlantBlock("groundcoverMoss"), 'y',
                        Items.string, 'z', Items.leather }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderMushroom"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("epiphyteArtistsConk"), 1, 0), 'y',
                        new ItemStack(Items.string, 1, 0), 'z', new ItemStack(Items.leather, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderMushroom"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("epiphyteSulphurShelf"), 1, 0), 'y',
                        new ItemStack(Items.string, 1, 0), 'z', new ItemStack(Items.leather, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderMushroom"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("epiphyteTurkeyTail"), 1, 0), 'y',
                        new ItemStack(Items.string, 1, 0), 'z', new ItemStack(Items.leather, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderMushroom"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("fungusBlackPowderpuff"), 1, 0), 'y',
                        new ItemStack(Items.string, 1, 0), 'z', new ItemStack(Items.leather, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderMushroom"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("fungusChanterelle"), 1, 0), 'y',
                        new ItemStack(Items.string, 1, 0), 'z', new ItemStack(Items.leather, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderMushroomPoison"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("fungusDeathCap"), 1, 0), 'y',
                        new ItemStack(Items.string, 1, 0), 'z', new ItemStack(Items.leather, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderMushroom"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("fungusGiantClub"), 1, 0), 'y',
                        new ItemStack(Items.string, 1, 0), 'z', new ItemStack(Items.leather, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderMushroom"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("fungusParasol"), 1, 0), 'y',
                        new ItemStack(Items.string, 1, 0), 'z', new ItemStack(Items.leather, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderMushroom"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("fungusStinkhorn"), 1, 0), 'y',
                        new ItemStack(Items.string, 1, 0), 'z', new ItemStack(Items.leather, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderMushroom"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("fungusWeepingMilkCap"), 1, 0), 'y',
                        new ItemStack(Items.string, 1, 0), 'z', new ItemStack(Items.leather, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderMushroom"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("fungusWoodBlewit"), 1, 0), 'y',
                        new ItemStack(Items.string, 1, 0), 'z', new ItemStack(Items.leather, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderMushroomPoison"), 1, 0),
                    new Object[] { "xyx", "xzx", "xxx", 'x',
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("fungusWoollyGomphus"), 1, 0), 'y',
                        new ItemStack(Items.string, 1, 0), 'z', new ItemStack(Items.leather, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderConditioner"), 1, 0),
                    new Object[] { "xxx", "xyx", "xxx", 'x', new ItemStack(Items.dye, 1, 15), 'y',
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderMoss"), 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderDefoliant"), 1, 0),
                    new Object[] { new ItemStack(Items.dye, 1, 0),
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderMushroomPoison"), 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderFertilizer"), 1, 0),
                    new Object[] { "xxx", "xyx", "xxx", 'x', new ItemStack(Items.dye, 1, 15), 'y',
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderBerry"), 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderFertilizer"), 1, 0),
                    new Object[] { "xxx", "xyx", "xxx", 'x', new ItemStack(Items.dye, 1, 15), 'y',
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderFern"), 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderFertilizer"), 1, 0),
                    new Object[] { "xxx", "xyx", "xxx", 'x', new ItemStack(Items.dye, 1, 15), 'y',
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderLeaf"), 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantPowder("powderFertilizer"), 1, 0),
                    new Object[] { "xxx", "xyx", "xxx", 'x', new ItemStack(Items.dye, 1, 15), 'y',
                        new ItemStack(PlantMegaPack.items.getPlantPowder("powderMushroom"), 1, 0) }));
    }

    private void initPreparedFoods() {
        PMPItemFood jelly = PlantMegaPack.items.getFoodItem("foodJelly");
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(jelly, 1, 0),
                    new Object[] { "xxx", " y ", " z ", 'x', PlantMegaPack.items.getFoodItem("berriesBeauty"), 'y',
                        Items.sugar, 'z', Items.glass_bottle }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(jelly, 1, 0),
                    new Object[] { "xxx", " y ", " z ", 'x', PlantMegaPack.items.getFoodItem("berriesBlack"), 'y',
                        Items.sugar, 'z', Items.glass_bottle }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(jelly, 1, 0),
                    new Object[] { "xxx", " y ", " z ", 'x', PlantMegaPack.items.getFoodItem("berriesBlue"), 'y',
                        Items.sugar, 'z', Items.glass_bottle }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(jelly, 1, 0),
                    new Object[] { "xxx", " y ", " z ", 'x', PlantMegaPack.items.getFoodItem("berriesElder"), 'y',
                        Items.sugar, 'z', Items.glass_bottle }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(jelly, 1, 0),
                    new Object[] { "xxx", " y ", " z ", 'x', PlantMegaPack.items.getFoodItem("berriesGoose"), 'y',
                        Items.sugar, 'z', Items.glass_bottle }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(jelly, 1, 0),
                    new Object[] { "xxx", " y ", " z ", 'x',
                        PlantMegaPack.items.getFoodItem("berriesHarlequinMistletoe"), 'y', Items.sugar, 'z',
                        Items.glass_bottle }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(jelly, 1, 0),
                    new Object[] { "xxx", " y ", " z ", 'x', PlantMegaPack.items.getFoodItem("berriesHuckle"), 'y',
                        Items.sugar, 'z', Items.glass_bottle }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(jelly, 1, 0),
                    new Object[] { "xxx", " y ", " z ", 'x', PlantMegaPack.items.getFoodItem("berriesOrange"), 'y',
                        Items.sugar, 'z', Items.glass_bottle }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(jelly, 1, 0),
                    new Object[] { "xxx", " y ", " z ", 'x', PlantMegaPack.items.getFoodItem("berriesSnow"), 'y',
                        Items.sugar, 'z', Items.glass_bottle }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(jelly, 1, 0),
                    new Object[] { "xxx", " y ", " z ", 'x', PlantMegaPack.items.getFoodItem("berriesStraw"), 'y',
                        Items.sugar, 'z', Items.glass_bottle }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(jelly, 1, 0),
                    new Object[] { "xxx", " y ", " z ", 'x', PlantMegaPack.items.getFoodItem("foodPricklyPearFruit"),
                        'y', Items.sugar, 'z', Items.glass_bottle }));
        if (PlantMegaPack.settingsGeneral.contentCrops) {
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodPeanutButter"), 1, 0),
                        new Object[] { "xxx", " y ", " z ", 'x', PlantMegaPack.items.getFoodItem("foodPeanuts"), 'y',
                            Items.sugar, 'z', Items.glass_bottle }));
        }

    }

    private void initSandwiches() {
        if (PlantMegaPack.settingsGeneral.contentCrops) {
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodSandwichPBJ"), 6, 0),
                        new Object[] { new ItemStack(Items.bread, 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodPeanutButter"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodJelly"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapelessOreRecipe(
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodSandwichPBJ"), 6, 0),
                        new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem("foodCornBread"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodPeanutButter"), 1, 0),
                            new ItemStack(PlantMegaPack.items.getFoodItem("foodJelly"), 1, 0) }));
            this.createSandwichRecipes(Items.bread, "foodSandwichBeef", Items.cooked_beef);
            this.createSandwichRecipes(Items.bread, "foodSandwichChicken", Items.cooked_chicken);
            this.createSandwichRecipes(Items.bread, "foodSandwichFish", Items.cooked_fished);
            this.createSandwichRecipes(Items.bread, "foodSandwichPork", Items.cooked_porkchop);
            this.createSandwichRecipes(
                PlantMegaPack.items.getFoodItem("foodCornBread"),
                "foodSandwichBeef",
                Items.cooked_beef);
            this.createSandwichRecipes(
                PlantMegaPack.items.getFoodItem("foodCornBread"),
                "foodSandwichChicken",
                Items.cooked_chicken);
            this.createSandwichRecipes(
                PlantMegaPack.items.getFoodItem("foodCornBread"),
                "foodSandwichFish",
                Items.cooked_fished);
            this.createSandwichRecipes(
                PlantMegaPack.items.getFoodItem("foodCornBread"),
                "foodSandwichPork",
                Items.cooked_porkchop);
        }
    }

    private void createSandwichRecipes(Item bread, String sandwichCraftedName, Item meat) {
        this.createSandwiches(bread, sandwichCraftedName, meat, "foodBellPepperOrange");
        this.createSandwiches(bread, sandwichCraftedName, meat, "foodBellPepperRed");
        this.createSandwiches(bread, sandwichCraftedName, meat, "foodBellPepperYellow");
        this.createSandwiches(bread, sandwichCraftedName, meat, "foodCassavaRoot");
        this.createSandwiches(bread, sandwichCraftedName, meat, "foodEggplant");
        this.createSandwiches(bread, sandwichCraftedName, meat, "foodLeek");
        this.createSandwiches(bread, sandwichCraftedName, meat, "foodOnion");
        this.createSandwiches(bread, sandwichCraftedName, meat, "foodTomato");
    }

    private void createSandwiches(Item bread, String sandwichCraftedName, Item meat, String veg) {
        PMPItemFood vegetable = PlantMegaPack.items.getFoodItem(veg);
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(sandwichCraftedName), 4, 0),
                    new Object[] { new ItemStack(bread, 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodCelery"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(sandwichCraftedName), 4, 0),
                    new Object[] { new ItemStack(bread, 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodCucumber"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(sandwichCraftedName), 4, 0),
                    new Object[] { new ItemStack(bread, 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodLettuce"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(sandwichCraftedName), 4, 0),
                    new Object[] { new ItemStack(bread, 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodCucumber"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(sandwichCraftedName), 4, 0),
                    new Object[] { new ItemStack(bread, 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodSpinach"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(sandwichCraftedName), 4, 0),
                    new Object[] { new ItemStack(bread, 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodCentellaLeaves"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(sandwichCraftedName), 4, 0),
                    new Object[] { new ItemStack(bread, 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodLaksaLeaves"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(sandwichCraftedName), 4, 0),
                    new Object[] { new ItemStack(bread, 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodSorrel"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem(sandwichCraftedName), 4, 0),
                    new Object[] { new ItemStack(bread, 1, 0),
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodWatercress"), 1, 0),
                        new ItemStack(vegetable, 1, 0), new ItemStack(meat, 1, 0) }));
    }

    private void initSalves() {
        if (PlantMegaPack.settingsGeneral.contentAquatic) {
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveWaterBreathing"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentBlueStaghorn"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveWaterBreathing"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentElegance"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveWaterBreathing"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentFeatheredHydroid"), 1, 0),
                            'y', new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveWaterBreathing"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentPillar"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveWaterBreathing"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentPulsingXenia"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveWaterBreathing"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentPurpleSeaWhip"), 1, 0),
                            'y', new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveWaterBreathing"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentRedSeaFan"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveWaterBreathing"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentSunPolyps"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveWaterBreathing"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getCoralFragment("coralFragmentYellowBamboo"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveNightVision"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getPlantItem("flowerBlueStarItem"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveHealth"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getPlantItem("flowerBroomSnakeweed"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveSwiftness"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getPlantItem("flowerOcotillo"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveHealth"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getPlantItem("flowerTorchGinger"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveHealth"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getPlantItem("flowerYellowToadflaxItem"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveHealth"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getPlantItem("leafAloe"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveHealth"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.items.getPlantItem("leafWildMint"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveFireResist"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("forestPinesap"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveNightVision"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("flowerBlueStar"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveHealth"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("desertBroomSnakeweed"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveSwiftness"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("desertOcotillo"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveHealth"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("jungleTorchGinger"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveHealth"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("flowerYellowToadflax"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveHealth"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("flowerCandelabraAloe"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveHealth"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("forestWildMint"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.items.getSalve("salveStrength"), 1, 0),
                        new Object[] { "   ", "xyx", " z ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("wetlandsWaterHorsetail"), 1, 0), 'y',
                            new ItemStack(Items.sugar, 1, 0), 'z',
                            new ItemStack(PlantMegaPack.items.getFoodItem("wrapSeaweed"), 1, 0) }));
        }

    }

    private void initStuffedPeppers() {
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodStuffedPepperOrange"), 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem("foodBellPepperOrange"), 1, 0),
                        new ItemStack(Items.baked_potato, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodStuffedPepperRed"), 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem("foodStuffedPepperRed"), 1, 0),
                        new ItemStack(Items.baked_potato, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodStuffedPepperYellow"), 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.items.getFoodItem("foodStuffedPepperYellow"), 1, 0),
                        new ItemStack(Items.baked_potato, 1, 0) }));
    }

    private void initWallBrackets() {
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketWoodAcacia"), 1, 0),
                    new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.wooden_slab, 1, 4), 'y',
                        new ItemStack(Items.stick, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketWoodBirch"), 1, 0),
                    new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.wooden_slab, 1, 2), 'y',
                        new ItemStack(Items.stick, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketWoodDarkOak"), 1, 0),
                    new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.wooden_slab, 1, 5), 'y',
                        new ItemStack(Items.stick, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketWoodJungle"), 1, 0),
                    new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.wooden_slab, 1, 3), 'y',
                        new ItemStack(Items.stick, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketWoodOak"), 1, 0),
                    new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.wooden_slab, 1, 0), 'y',
                        new ItemStack(Items.stick, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketWoodSpruce"), 1, 0),
                    new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.wooden_slab, 1, 1), 'y',
                        new ItemStack(Items.stick, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketStone"), 1, 0),
                    new Object[] { "xx ", " x ", "   ", 'x', new ItemStack(Blocks.stone, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketIronCurl"), 1, 0),
                    new Object[] { "xx ", " x ", "   ", 'x', new ItemStack(Items.iron_ingot, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketGoldCurl"), 1, 0),
                    new Object[] { "xx ", " x ", "   ", 'x', new ItemStack(Items.gold_ingot, 1, 0) }));
        if (PlantMegaPack.settingsGeneral.contentCraftingBambooBlocks) {
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooAsper"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooAsperSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("bambooAsper"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(
                            PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooFargesiaRobusta"),
                            1,
                            0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooFargesiaRobustaSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("bambooFargesiaRobusta"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooGiantTimber"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooGiantTimberSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("bambooGiantTimber"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooGolden"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooGoldenSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("bambooGolden"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooMoso"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooMosoSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("bambooMoso"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooShortTassled"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooShortTassledSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("bambooShortTassled"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooTimorBlack"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooTimorBlackSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("bambooTimorBlack"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooTropicalBlue"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooTropicalBlueSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("bambooTropicalBlue"), 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketBambooWetForest"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x',
                            new ItemStack(PlantMegaPack.blocks.getBambooSlab("bambooWetForestSlab"), 1, 0), 'y',
                            new ItemStack(PlantMegaPack.blocks.getPlantBlock("bambooWetForest"), 1, 0) }));
        }

        if (PlantMegaPack.settingsGeneral.contentWallBracketExtra) {
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketDiamond"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.diamond_block, 1, 0), 'y',
                            new ItemStack(Blocks.diamond_block, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketGold"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.gold_block, 1, 0), 'y',
                            new ItemStack(Blocks.gold_block, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketIce"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.ice, 1, 0), 'y',
                            new ItemStack(Blocks.ice, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketIron"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.iron_block, 1, 0), 'y',
                            new ItemStack(Blocks.iron_block, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketLapis"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.lapis_block, 1, 0), 'y',
                            new ItemStack(Blocks.lapis_block, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketNetherbrick"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.nether_brick, 1, 0), 'y',
                            new ItemStack(Blocks.nether_brick, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketObsidian"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.obsidian, 1, 0), 'y',
                            new ItemStack(Items.blaze_rod, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketQuartz"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.quartz_block, 1, 0), 'y',
                            new ItemStack(Blocks.quartz_block, 1, 0) }));
            CraftingManager.getInstance()
                .getRecipeList()
                .add(
                    new ShapedOreRecipe(
                        new ItemStack(PlantMegaPack.blocks.getWallBracketBlock("wallBracketSandstone"), 1, 0),
                        new Object[] { "xy ", " y ", "   ", 'x', new ItemStack(Blocks.sandstone, 1, 0), 'y',
                            new ItemStack(Blocks.sandstone, 1, 0) }));
        }

    }

    private void initVanillaHangingPlants() {
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getHangingPlantBlock("hangingFlowersPUR"), 1, 0),
                    new Object[] { " x ", " y ", " z ", 'x', new ItemStack(Items.iron_ingot, 1, 0), 'y',
                        new ItemStack(Blocks.double_plant, 1, 1), 'z', new ItemStack(Items.bowl, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getHangingPlantBlock("hangingFlowersRED"), 1, 0),
                    new Object[] { " x ", " y ", " z ", 'x', new ItemStack(Items.iron_ingot, 1, 0), 'y',
                        new ItemStack(Blocks.double_plant, 1, 4), 'z', new ItemStack(Items.bowl, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getHangingPlantBlock("hangingFlowersPNK"), 1, 0),
                    new Object[] { " x ", " y ", " z ", 'x', new ItemStack(Items.iron_ingot, 1, 0), 'y',
                        new ItemStack(Blocks.double_plant, 1, 5), 'z', new ItemStack(Items.bowl, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getHangingPlantBlock("hangingFlowersRED"), 1, 0),
                    new Object[] { " x ", " y ", " z ", 'x', new ItemStack(Items.iron_ingot, 1, 0), 'y',
                        new ItemStack(Blocks.red_flower, 1, 0), 'z', new ItemStack(Items.bowl, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getHangingPlantBlock("hangingFlowersBLU"), 1, 0),
                    new Object[] { " x ", " y ", " z ", 'x', new ItemStack(Items.iron_ingot, 1, 0), 'y',
                        new ItemStack(Blocks.red_flower, 1, 1), 'z', new ItemStack(Items.bowl, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getHangingPlantBlock("hangingFlowersPUR"), 1, 0),
                    new Object[] { " x ", " y ", " z ", 'x', new ItemStack(Items.iron_ingot, 1, 0), 'y',
                        new ItemStack(Blocks.red_flower, 1, 2), 'z', new ItemStack(Items.bowl, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getHangingPlantBlock("hangingFlowersWHT"), 1, 0),
                    new Object[] { " x ", " y ", " z ", 'x', new ItemStack(Items.iron_ingot, 1, 0), 'y',
                        new ItemStack(Blocks.red_flower, 1, 3), 'z', new ItemStack(Items.bowl, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getHangingPlantBlock("hangingFlowersRED"), 1, 0),
                    new Object[] { " x ", " y ", " z ", 'x', new ItemStack(Items.iron_ingot, 1, 0), 'y',
                        new ItemStack(Blocks.red_flower, 1, 4), 'z', new ItemStack(Items.bowl, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getHangingPlantBlock("hangingFlowersORA"), 1, 0),
                    new Object[] { " x ", " y ", " z ", 'x', new ItemStack(Items.iron_ingot, 1, 0), 'y',
                        new ItemStack(Blocks.red_flower, 1, 5), 'z', new ItemStack(Items.bowl, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getHangingPlantBlock("hangingFlowersWHT"), 1, 0),
                    new Object[] { " x ", " y ", " z ", 'x', new ItemStack(Items.iron_ingot, 1, 0), 'y',
                        new ItemStack(Blocks.red_flower, 1, 6), 'z', new ItemStack(Items.bowl, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getHangingPlantBlock("hangingFlowersPNK"), 1, 0),
                    new Object[] { " x ", " y ", " z ", 'x', new ItemStack(Items.iron_ingot, 1, 0), 'y',
                        new ItemStack(Blocks.red_flower, 1, 7), 'z', new ItemStack(Items.bowl, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getHangingPlantBlock("hangingFlowersWHT"), 1, 0),
                    new Object[] { " x ", " y ", " z ", 'x', new ItemStack(Items.iron_ingot, 1, 0), 'y',
                        new ItemStack(Blocks.red_flower, 1, 8), 'z', new ItemStack(Items.bowl, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.blocks.getHangingPlantBlock("hangingFlowersYEL"), 1, 0),
                    new Object[] { " x ", " y ", " z ", 'x', new ItemStack(Items.iron_ingot, 1, 0), 'y',
                        new ItemStack(Blocks.yellow_flower, 1, 0), 'z', new ItemStack(Items.bowl, 1, 0) }));
    }

    private void initPlantSpecialRecipes() {
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantItem("leafAloe"), 2),
                    new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("flowerCandelabraAloe"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.dye, 1, 2),
                    new Object[] { new ItemStack(PlantMegaPack.items.getPlantItem("leafAloe"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantItem("flowerBlueStarItem"), 2),
                    new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("flowerBlueStar"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.dye, 1, 12),
                    new Object[] { new ItemStack(PlantMegaPack.items.getPlantItem("flowerBlueStarItem"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantItem("flowerBroomSnakeweed"), 2),
                    new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("desertBroomSnakeweed"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.dye, 1, 11),
                    new Object[] { new ItemStack(PlantMegaPack.items.getPlantItem("flowerBroomSnakeweed"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantItem("flowerOcotillo"), 2),
                    new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("desertOcotillo"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.dye, 1, 14),
                    new Object[] { new ItemStack(PlantMegaPack.items.getPlantItem("flowerOcotillo"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Blocks.torch, 1),
                    new Object[] { new ItemStack(Items.stick, 1),
                        new ItemStack(PlantMegaPack.items.getPlantItem("flowerCattail"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantItem("flowerCattail"), 2),
                    new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("wetlandsCattails"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantItem("flowerTorchGinger"), 2),
                    new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("jungleTorchGinger"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.dye, 1, 1),
                    new Object[] { new ItemStack(PlantMegaPack.items.getPlantItem("flowerTorchGinger"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantItem("leafWildMint"), 2),
                    new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("forestWildMint"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.dye, 1, 2),
                    new Object[] { new ItemStack(PlantMegaPack.items.getPlantItem("leafWildMint"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getPlantItem("flowerYellowToadflaxItem"), 2),
                    new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("flowerYellowToadflax"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.dye, 1, 11),
                    new Object[] { new ItemStack(PlantMegaPack.items.getPlantItem("flowerYellowToadflaxItem"), 1) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Blocks.mossy_cobblestone, 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("groundcoverMoss"), 1, 0),
                        Blocks.cobblestone }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Blocks.cobblestone_wall, 1, 1),
                    new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("groundcoverMoss"), 1, 0),
                        new ItemStack(Blocks.cobblestone_wall, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Blocks.stonebrick, 1, 1),
                    new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("groundcoverMoss"), 1, 0),
                        new ItemStack(Blocks.stonebrick, 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.stick, 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("groundcoverTwig"), 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.string, 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("vineSpanishMoss"), 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(Items.gunpowder, 1, 0),
                    new Object[] { new ItemStack(PlantMegaPack.blocks.getPlantBlock("forestWolfsFootClubmoss"), 1, 0),
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("forestWolfsFootClubmoss"), 1, 0),
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("forestWolfsFootClubmoss"), 1, 0),
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("forestWolfsFootClubmoss"), 1, 0) }));
    }

    private void initFoodSpecialRecipes() {
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodQuinoaCereal"), 1, 0),
                    new Object[] { new ItemStack(Items.bowl, 1, 0), PlantMegaPack.items.getFoodItem("foodQuinoaSeeds"),
                        PlantMegaPack.items.getFoodItem("foodQuinoaSeeds"),
                        PlantMegaPack.items.getFoodItem("foodQuinoaSeeds") }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapelessOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodHarlequinmistletoeberry"), 2, 0),
                    new Object[] {
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock("epiphyteHarlequinMistletoe"), 1, 0) }));
        CraftingManager.getInstance()
            .getRecipeList()
            .add(
                new ShapedOreRecipe(
                    new ItemStack(PlantMegaPack.items.getFoodItem("foodCookiePeanutButter"), 8, 0),
                    new Object[] { "   ", "xyx", "   ", 'x', new ItemStack(Items.wheat, 1, 0), 'y',
                        new ItemStack(PlantMegaPack.items.getFoodItem("foodPeanuts"), 1, 0) }));
    }
}
