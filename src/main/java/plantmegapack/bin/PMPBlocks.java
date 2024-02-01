package plantmegapack.bin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import cpw.mods.fml.common.registry.GameRegistry;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockBambooBlock;
import plantmegapack.block.PMPBlockBambooDoor;
import plantmegapack.block.PMPBlockBambooFence;
import plantmegapack.block.PMPBlockBambooGate;
import plantmegapack.block.PMPBlockBambooLadder;
import plantmegapack.block.PMPBlockBambooPole;
import plantmegapack.block.PMPBlockBambooSlab;
import plantmegapack.block.PMPBlockBambooStairs;
import plantmegapack.block.PMPBlockHangingPlant;
import plantmegapack.block.PMPBlockPlant;
import plantmegapack.block.PMPBlockWallBracket;
import plantmegapack.common.PMPFlowerColor;
import plantmegapack.common.PMPHangingPlant;
import plantmegapack.common.PMPModInfo;
import plantmegapack.common.PMPPlant;
import plantmegapack.common.PMPPlantCategory;
import plantmegapack.common.PMPWallBracket;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.item.PMPItemBambooSlab;

public class PMPBlocks {

    private ArrayList<PMPBlockHangingPlant> hangingPlants = new ArrayList(PMPHangingPlant.values().length);
    private Map<PMPPlantCategory, ArrayList<PMPBlockPlant>> plantBlocks = new HashMap();
    private ArrayList<PMPBlockBambooPole> bambooPoles = new ArrayList();
    private ArrayList<PMPBlockBambooBlock> bambooBlocks = new ArrayList();
    private ArrayList<PMPBlockBambooSlab> bambooSlabs = new ArrayList();
    private ArrayList<PMPBlockBambooSlab> bambooSlabsDouble = new ArrayList();
    private ArrayList<PMPBlockBambooStairs> bambooStairs = new ArrayList();
    private ArrayList<PMPBlockBambooDoor> bambooDoors = new ArrayList();
    private ArrayList<PMPBlockBambooFence> bambooFences = new ArrayList();
    private ArrayList<PMPBlockBambooGate> bambooGates = new ArrayList();
    private ArrayList<PMPBlockBambooLadder> bambooLadders = new ArrayList();
    private ArrayList<PMPBlockWallBracket> wallBrackets = new ArrayList(PMPWallBracket.values().length);

    public PMPBlocks() {
        this.createHangingPlants();
        this.createPlantBlockArrays();
        this.createPlantBlocks();
        if (PlantMegaPack.settingsGeneral.contentCraftingBambooBlocks) {
            this.createBambooBlocks();
        }

        this.createWallBrackets();
    }

    private void createHangingPlants() {
        PMPHangingPlant[] var2 = PMPHangingPlant.values();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            PMPHangingPlant plantList = var2[var4];
            if (plantList.addToGame > 0) {
                PMPBlockHangingPlant block = new PMPBlockHangingPlant(plantList.ID);
                if (block != null) {
                    this.hangingPlants.add(block);
                    PMPModInfo.addToRegisteredBlocks(1);
                }
            }
        }

    }

    private void createPlantBlocks() {
        PMPPlant[] var1 = PMPPlant.values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            PMPPlant plants = var1[var3];
            if (plants.addToGame > 0 && this.isPlantCategoryCreated(plants.category)) {
                PMPModInfo.addToRegisteredPlants(this.createPlantBlock(plants));
            }
        }

    }

    private void createBambooBlocks() {
        int index;
        String blockName;
        for (index = 0; index < this.getPlantBlockList(PMPPlantCategory.BAMBOO)
            .size(); ++index) {
            blockName = ((PMPBlockPlant) this.getPlantBlockList(PMPPlantCategory.BAMBOO)
                .get(index)).getUnlocalizedName()
                    .substring(5);
            PMPBlockBambooPole blockPole = new PMPBlockBambooPole(blockName + "Pole");
            if (blockPole != null) {
                this.bambooPoles.add(blockPole);
                OreDictionary.registerOre("poleB" + blockName.substring(1), blockPole);
                CraftingManager.getInstance()
                    .getRecipeList()
                    .add(
                        new ShapelessOreRecipe(
                            new ItemStack(Item.getItemFromBlock(blockPole), 1, 0),
                            new Object[] {
                                new ItemStack(Item.getItemFromBlock(this.getPlantBlock(blockName)), 1, 0) }));
                PMPModInfo.addToRegisteredBlocks(1);
                PMPModInfo.addToRegisteredRecipes(1);
            }
        }

        for (index = 0; index < this.getPlantBlockList(PMPPlantCategory.BAMBOO)
            .size(); ++index) {
            blockName = ((PMPBlockPlant) this.getPlantBlockList(PMPPlantCategory.BAMBOO)
                .get(index)).getUnlocalizedName()
                    .substring(5);
            PMPBlockBambooBlock block = new PMPBlockBambooBlock(
                blockName + "Block",
                (PMPBlockPlant) this.getPlantBlockList(PMPPlantCategory.BAMBOO)
                    .get(index));
            if (block != null) {
                this.bambooBlocks.add(block);
                OreDictionary.registerOre("blockB" + blockName.substring(1), block);
                CraftingManager.getInstance()
                    .getRecipeList()
                    .add(
                        new ShapedOreRecipe(
                            new ItemStack(Item.getItemFromBlock(block), 2, 0),
                            new Object[] { "xxx", "xxx", "xxx", 'x',
                                new ItemStack(Item.getItemFromBlock((Block) this.bambooPoles.get(index)), 1, 0) }));
                PMPModInfo.addToRegisteredBlocks(1);
                PMPModInfo.addToRegisteredRecipes(1);
            }
        }

        for (index = 0; index < this.getPlantBlockList(PMPPlantCategory.BAMBOO)
            .size(); ++index) {
            blockName = ((PMPBlockPlant) this.getPlantBlockList(PMPPlantCategory.BAMBOO)
                .get(index)).getUnlocalizedName()
                    .substring(5);
            PMPBlockBambooStairs blockStairs = new PMPBlockBambooStairs(
                blockName + "Stairs",
                (PMPBlockBambooBlock) this.bambooBlocks.get(index));
            if (blockStairs != null) {
                this.bambooStairs.add(blockStairs);
                OreDictionary.registerOre("stairsB" + blockName.substring(1), blockStairs);
                CraftingManager.getInstance()
                    .getRecipeList()
                    .add(
                        new ShapedOreRecipe(
                            new ItemStack(Item.getItemFromBlock(blockStairs), 4, 0),
                            new Object[] { "  x", " xx", "xxx", 'x',
                                new ItemStack(Item.getItemFromBlock((Block) this.bambooBlocks.get(index)), 1, 0) }));
                PMPModInfo.addToRegisteredBlocks(1);
                PMPModInfo.addToRegisteredRecipes(1);
            }
        }

        for (index = 0; index < this.getPlantBlockList(PMPPlantCategory.BAMBOO)
            .size(); ++index) {
            blockName = ((PMPBlockPlant) this.getPlantBlockList(PMPPlantCategory.BAMBOO)
                .get(index)).getUnlocalizedName()
                    .substring(5);
            PMPBlockBambooSlab blockSlab = new PMPBlockBambooSlab(
                false,
                blockName + "Slab",
                (PMPBlockBambooBlock) this.bambooBlocks.get(index));
            PMPBlockBambooSlab blockSlabDouble = new PMPBlockBambooSlab(
                true,
                blockName + "SlabDouble",
                (PMPBlockBambooBlock) this.bambooBlocks.get(index));
            if (blockSlab != null && blockSlabDouble != null) {
                this.bambooSlabs.add(blockSlab);
                this.bambooSlabsDouble.add(blockSlabDouble);
                GameRegistry.registerBlock(
                    blockSlab,
                    PMPItemBambooSlab.class,
                    blockName + "Slab",
                    new Object[] { blockSlab, blockSlabDouble });
                GameRegistry.registerBlock(
                    blockSlabDouble,
                    PMPItemBambooSlab.class,
                    blockName + "SlabDouble",
                    new Object[] { blockSlab, blockSlabDouble });
                OreDictionary.registerOre("blockB" + blockName.substring(1) + "Slab", blockSlab);
                CraftingManager.getInstance()
                    .getRecipeList()
                    .add(
                        new ShapedOreRecipe(
                            new ItemStack(Item.getItemFromBlock(blockSlab), 6, 0),
                            new Object[] { "   ", "xxx", "   ", 'x',
                                new ItemStack(Item.getItemFromBlock((Block) this.bambooBlocks.get(index)), 1, 0) }));
                PMPModInfo.addToRegisteredBlocks(1);
                PMPModInfo.addToRegisteredRecipes(1);
            }
        }

        for (index = 0; index < this.getPlantBlockList(PMPPlantCategory.BAMBOO)
            .size(); ++index) {
            blockName = ((PMPBlockPlant) this.getPlantBlockList(PMPPlantCategory.BAMBOO)
                .get(index)).getUnlocalizedName()
                    .substring(5);
            PMPBlockBambooDoor blockDoor = new PMPBlockBambooDoor(blockName + "Door");
            if (blockDoor != null) {
                this.bambooDoors.add(blockDoor);
                OreDictionary.registerOre("doorB" + blockName.substring(1), blockDoor);
                CraftingManager.getInstance()
                    .getRecipeList()
                    .add(
                        new ShapedOreRecipe(
                            new ItemStack(Item.getItemFromBlock(blockDoor), 3, 0),
                            new Object[] { "xx ", "xx ", "xx ", 'x',
                                new ItemStack(Item.getItemFromBlock((Block) this.bambooBlocks.get(index)), 1, 0) }));
                PMPModInfo.addToRegisteredBlocks(1);
                PMPModInfo.addToRegisteredRecipes(1);
            }
        }

        for (index = 0; index < this.getPlantBlockList(PMPPlantCategory.BAMBOO)
            .size(); ++index) {
            blockName = ((PMPBlockPlant) this.getPlantBlockList(PMPPlantCategory.BAMBOO)
                .get(index)).getUnlocalizedName()
                    .substring(5);
            PMPBlockBambooFence blockFence = new PMPBlockBambooFence(
                blockName + "Fence",
                (PMPBlockBambooBlock) this.bambooBlocks.get(index));
            if (blockFence != null) {
                this.bambooFences.add(blockFence);
                OreDictionary.registerOre("fenceB" + blockName.substring(1), blockFence);
                CraftingManager.getInstance()
                    .getRecipeList()
                    .add(
                        new ShapedOreRecipe(
                            new ItemStack(Item.getItemFromBlock(blockFence), 3, 0),
                            new Object[] { "   ", "xyx", "xyx", 'x',
                                new ItemStack(Item.getItemFromBlock((Block) this.bambooBlocks.get(index)), 1, 0), 'y',
                                new ItemStack(Item.getItemFromBlock((Block) this.bambooPoles.get(index)), 1, 0) }));
                PMPModInfo.addToRegisteredBlocks(1);
                PMPModInfo.addToRegisteredRecipes(1);
            }
        }

        for (index = 0; index < this.getPlantBlockList(PMPPlantCategory.BAMBOO)
            .size(); ++index) {
            blockName = ((PMPBlockPlant) this.getPlantBlockList(PMPPlantCategory.BAMBOO)
                .get(index)).getUnlocalizedName()
                    .substring(5);
            PMPBlockBambooGate blockGate = new PMPBlockBambooGate(
                blockName + "Gate",
                (PMPBlockBambooBlock) this.bambooBlocks.get(index));
            if (blockGate != null) {
                this.bambooGates.add(blockGate);
                OreDictionary.registerOre("gateB" + blockName.substring(1), blockGate);
                CraftingManager.getInstance()
                    .getRecipeList()
                    .add(
                        new ShapedOreRecipe(
                            new ItemStack(Item.getItemFromBlock(blockGate), 1, 0),
                            new Object[] { "   ", "xyx", "xyx", 'x',
                                new ItemStack(Item.getItemFromBlock((Block) this.bambooPoles.get(index)), 1, 0), 'y',
                                new ItemStack(Item.getItemFromBlock((Block) this.bambooBlocks.get(index)), 1, 0) }));
                PMPModInfo.addToRegisteredBlocks(1);
                PMPModInfo.addToRegisteredRecipes(1);
            }
        }

        for (index = 0; index < this.getPlantBlockList(PMPPlantCategory.BAMBOO)
            .size(); ++index) {
            blockName = ((PMPBlockPlant) this.getPlantBlockList(PMPPlantCategory.BAMBOO)
                .get(index)).getUnlocalizedName()
                    .substring(5);
            PMPBlockBambooLadder blockLadder = new PMPBlockBambooLadder(
                blockName + "Ladder",
                (PMPBlockBambooBlock) this.bambooBlocks.get(index));
            if (blockLadder != null) {
                this.bambooLadders.add(blockLadder);
                OreDictionary.registerOre("ladderB" + blockName.substring(1), blockLadder);
                CraftingManager.getInstance()
                    .getRecipeList()
                    .add(
                        new ShapedOreRecipe(
                            new ItemStack(Item.getItemFromBlock(blockLadder), 3, 0),
                            new Object[] { "x x", "xxx", "x x", 'x',
                                new ItemStack(Item.getItemFromBlock((Block) this.bambooPoles.get(index)), 1, 0) }));
                PMPModInfo.addToRegisteredBlocks(1);
                PMPModInfo.addToRegisteredRecipes(1);
            }
        }

    }

    private void createWallBrackets() {
        PMPWallBracket[] var2 = PMPWallBracket.values();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            PMPWallBracket bracketList = var2[var4];
            if ((!bracketList.ID.startsWith("wallBracketBamboo")
                || PlantMegaPack.settingsGeneral.contentCraftingBambooBlocks)
                && (bracketList.alwaysCreate || PlantMegaPack.settingsGeneral.contentWallBracketExtra)) {
                PMPBlockWallBracket block = new PMPBlockWallBracket(bracketList.ID, bracketList.renderType);
                if (block != null) {
                    this.wallBrackets.add(block);
                    OreDictionary.registerOre(bracketList.ID, block);
                    PMPModInfo.addToRegisteredBlocks(1);
                    PMPModInfo.addToRegisteredRecipes(1);
                }
            }
        }

    }

    private void createPlantBlockArrays() {
        PMPPlantCategory[] var2 = PMPPlantCategory.values();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            PMPPlantCategory category = var2[var4];
            if (category.addToGame > 0) {
                ArrayList<PMPBlockPlant> list = new ArrayList();
                if (list != null) {
                    this.plantBlocks.put(category, list);
                }
            }
        }

    }

    private int createPlantBlock(PMPPlant plant) {
        int blocksAdded = 0;
        PMPDataPlant plantData = new PMPDataPlant(plant);
        if (plantData != null) {
            plantData.loadUserConfig();
            PMPBlockPlant plantBlock = new PMPBlockPlant(plantData);
            if (plantBlock != null
                && (!this.isAquaticPlant(plantData.attributes.category) || PlantMegaPack.settingsGeneral.contentAquatic)
                && (plantData.attributes.category != PMPPlantCategory.CROP_AQUATIC
                    && plantData.attributes.category != PMPPlantCategory.CROP_LAND
                    || PlantMegaPack.settingsGeneral.contentCrops)) {
                this.getPlantBlockList(plant.category)
                    .add(plantBlock);
                int index;
                PMPFlowerColor[] var6;
                int var7;
                int var8;
                PMPFlowerColor color;
                if (plantData.attributes.category == PMPPlantCategory.FLOATING && plantBlock.hasFlowerColors()) {
                    plantBlock.colorMap = new ArrayList();
                    if (plantBlock.colorMap != null) {
                        index = 0;
                        var6 = PMPFlowerColor.values();
                        var7 = var6.length;

                        for (var8 = 0; var8 < var7; ++var8) {
                            color = var6[var8];
                            if (plantData.attributes.plantData.charAt(color.ordinal()) == '1') {
                                plantBlock.colorMap.add(color);
                                CraftingManager.getInstance()
                                    .getRecipeList()
                                    .add(
                                        new ShapelessOreRecipe(
                                            new ItemStack(Items.dye, 1, color.dyeColor),
                                            new Object[] { new ItemStack(plantBlock, 1, index) }));
                                ++index;
                                PMPModInfo.addToRegisteredRecipes(1);
                            }
                        }

                        blocksAdded += plantBlock.colorMap.size();
                    }
                } else if (plantData.attributes.category == PMPPlantCategory.FLOWER_MULTI) {
                    plantBlock.colorMap = new ArrayList();
                    if (plantBlock.colorMap != null) {
                        index = 0;
                        var6 = PMPFlowerColor.values();
                        var7 = var6.length;

                        for (var8 = 0; var8 < var7; ++var8) {
                            color = var6[var8];
                            if (plantData.attributes.plantData.charAt(color.ordinal()) == '1') {
                                plantBlock.colorMap.add(color);
                                CraftingManager.getInstance()
                                    .getRecipeList()
                                    .add(
                                        new ShapelessOreRecipe(
                                            new ItemStack(Items.dye, 1, color.dyeColor),
                                            new Object[] { new ItemStack(plantBlock, 1, index) }));
                                CraftingManager.getInstance()
                                    .getRecipeList()
                                    .add(
                                        new ShapedOreRecipe(
                                            new ItemStack(this.getHangingPlantBlock(color.hangingPlant.ID), 1, 0),
                                            new Object[] { " x ", " y ", " z ", 'x',
                                                new ItemStack(Items.iron_ingot, 1, 0), 'y',
                                                new ItemStack(plantBlock, 1, index), 'z',
                                                new ItemStack(Items.bowl, 1, 0) }));
                                ++index;
                                PMPModInfo.addToRegisteredRecipes(2);
                            }
                        }

                        blocksAdded += plantBlock.colorMap.size();
                    }
                } else {
                    if (plantData.attributes.category != PMPPlantCategory.CORAL
                        && plantBlock.plantData.attributes.dyeColor >= 0) {
                        CraftingManager.getInstance()
                            .getRecipeList()
                            .add(
                                new ShapelessOreRecipe(
                                    new ItemStack(Items.dye, 1, plantBlock.plantData.attributes.dyeColor),
                                    new Object[] { new ItemStack(plantBlock, 1) }));
                        PMPModInfo.addToRegisteredRecipes(1);
                    }

                    if (plantBlock.plantData.attributes.hangingPlant.length() > 0) {
                        CraftingManager.getInstance()
                            .getRecipeList()
                            .add(
                                new ShapedOreRecipe(
                                    new ItemStack(
                                        Item.getItemFromBlock(
                                            this.getHangingPlantBlock(plantBlock.plantData.attributes.hangingPlant)),
                                        1,
                                        0),
                                    new Object[] { " x ", " y ", " z ", 'x', new ItemStack(Items.iron_ingot, 1, 0), 'y',
                                        new ItemStack(plantBlock, 1, 0), 'z', new ItemStack(Items.bowl, 1, 0) }));
                        PMPModInfo.addToRegisteredRecipes(1);
                    }

                    ++blocksAdded;
                }
            }
        }

        return blocksAdded;
    }

    public ArrayList<PMPBlockPlant> getPlantBlockList(PMPPlantCategory category) {
        return (ArrayList) this.plantBlocks.get(category);
    }

    public PMPBlockHangingPlant getHangingPlantBlock(String name) {
        for (int plantIndex = 0; plantIndex < this.hangingPlants.size(); ++plantIndex) {
            if (name.matches(
                ((PMPBlockHangingPlant) this.hangingPlants.get(plantIndex)).getUnlocalizedName()
                    .substring(5))) {
                return (PMPBlockHangingPlant) this.hangingPlants.get(plantIndex);
            }
        }

        return null;
    }

    public PMPBlockPlant getPlantBlock(String name) {
        PMPPlantCategory[] var2 = PMPPlantCategory.values();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            PMPPlantCategory category = var2[var4];
            if (category.addToGame > 0) {
                for (int plantIndex = 0; plantIndex
                    < ((ArrayList) this.plantBlocks.get(category)).size(); ++plantIndex) {
                    if (((PMPBlockPlant) ((ArrayList) this.plantBlocks.get(category))
                        .get(plantIndex)).plantData.attributes.ID.matches(name)) {
                        return (PMPBlockPlant) ((ArrayList) this.plantBlocks.get(category)).get(plantIndex);
                    }
                }
            }
        }

        return null;
    }

    public PMPBlockBambooBlock getBambooBlock(String name) {
        for (int index = 0; index < this.bambooBlocks.size(); ++index) {
            if (((PMPBlockBambooBlock) this.bambooBlocks.get(index)).getUnlocalizedName()
                .substring(5)
                .matches(name)) {
                return (PMPBlockBambooBlock) this.bambooBlocks.get(index);
            }
        }

        return null;
    }

    public PMPBlockBambooPole getBambooPole(String name) {
        for (int index = 0; index < this.bambooPoles.size(); ++index) {
            if (((PMPBlockBambooPole) this.bambooPoles.get(index)).getUnlocalizedName()
                .substring(5)
                .matches(name)) {
                return (PMPBlockBambooPole) this.bambooPoles.get(index);
            }
        }

        return null;
    }

    public PMPBlockBambooSlab getBambooSlab(String name) {
        for (int index = 0; index < this.bambooSlabs.size(); ++index) {
            if (((PMPBlockBambooSlab) this.bambooSlabs.get(index)).getUnlocalizedName()
                .substring(5)
                .matches(name)) {
                return (PMPBlockBambooSlab) this.bambooSlabs.get(index);
            }
        }

        return null;
    }

    public PMPBlockBambooSlab getBambooSlabDouble(String name) {
        for (int index = 0; index < this.bambooSlabsDouble.size(); ++index) {
            if (((PMPBlockBambooSlab) this.bambooSlabsDouble.get(index)).getUnlocalizedName()
                .substring(5)
                .matches(name)) {
                return (PMPBlockBambooSlab) this.bambooSlabsDouble.get(index);
            }
        }

        return null;
    }

    public PMPBlockBambooDoor getBambooDoor(String name) {
        for (int index = 0; index < this.bambooDoors.size(); ++index) {
            if (((PMPBlockBambooDoor) this.bambooDoors.get(index)).getUnlocalizedName()
                .substring(5)
                .matches(name)) {
                return (PMPBlockBambooDoor) this.bambooDoors.get(index);
            }
        }

        return null;
    }

    public PMPBlockBambooStairs getBambooStairs(String unlocalizedName) {
        for (int index = 0; index < this.bambooStairs.size(); ++index) {
            if (((PMPBlockBambooStairs) this.bambooStairs.get(index)).getUnlocalizedName()
                .substring(5)
                .matches(unlocalizedName)) {
                return (PMPBlockBambooStairs) this.bambooStairs.get(index);
            }
        }

        return null;
    }

    public PMPBlockBambooFence getBambooFence(String unlocalizedName) {
        for (int index = 0; index < this.bambooFences.size(); ++index) {
            if (((PMPBlockBambooFence) this.bambooFences.get(index)).getUnlocalizedName()
                .substring(5)
                .matches(unlocalizedName)) {
                return (PMPBlockBambooFence) this.bambooFences.get(index);
            }
        }

        return null;
    }

    public PMPBlockBambooGate getBambooGate(String unlocalizedName) {
        for (int index = 0; index < this.bambooGates.size(); ++index) {
            if (((PMPBlockBambooGate) this.bambooGates.get(index)).getUnlocalizedName()
                .substring(5)
                .matches(unlocalizedName)) {
                return (PMPBlockBambooGate) this.bambooGates.get(index);
            }
        }

        return null;
    }

    public PMPBlockBambooLadder getBambooLadder(String unlocalizedName) {
        for (int index = 0; index < this.bambooLadders.size(); ++index) {
            if (((PMPBlockBambooLadder) this.bambooLadders.get(index)).getUnlocalizedName()
                .substring(5)
                .matches(unlocalizedName)) {
                return (PMPBlockBambooLadder) this.bambooLadders.get(index);
            }
        }

        return null;
    }

    public PMPBlockWallBracket getWallBracketBlock(String name) {
        for (int index = 0; index < this.wallBrackets.size(); ++index) {
            if (((PMPBlockWallBracket) this.wallBrackets.get(index)).getUnlocalizedName()
                .substring(5)
                .matches(name)) {
                return (PMPBlockWallBracket) this.wallBrackets.get(index);
            }
        }

        return null;
    }

    public boolean isAquaticPlant(PMPPlantCategory category) {
        return category == PMPPlantCategory.CORAL || category == PMPPlantCategory.FLOATING
            || category == PMPPlantCategory.FRESHWATER
            || category == PMPPlantCategory.SALTWATER
            || category == PMPPlantCategory.CROP_AQUATIC
            || category == PMPPlantCategory.IMMERSED;
    }

    private boolean isPlantCategoryCreated(PMPPlantCategory category) {
        return this.plantBlocks.get(category) != null;
    }

    public void addPlantNamesToArray(PMPPlantCategory category, ArrayList<String> array) {
        for (int index = 0; index < ((ArrayList) this.plantBlocks.get(category)).size(); ++index) {
            array.add(
                ((PMPBlockPlant) ((ArrayList) this.plantBlocks.get(category)).get(index)).getUnlocalizedName()
                    .substring(5));
        }

    }

    public PMPBlockPlant getRandomCoralReefPlant(Random random) {
        ArrayList plantList;
        if (random.nextInt(100) < 70) {
            plantList = this.getPlantBlockList(PMPPlantCategory.CORAL);
        } else {
            plantList = this.getPlantBlockList(PMPPlantCategory.SALTWATER);
        }

        return plantList == null ? null : (PMPBlockPlant) plantList.get(random.nextInt(plantList.size()));
    }
}
