package plantmegapack.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPFood;
import plantmegapack.common.PMPTab;

public class PMPItemFood extends ItemFood {

    private PMPFood food;

    public PMPItemFood(PMPFood food) {
        super(food.foodValue, food.saturation, false);
        this.food = food;
        this.setUnlocalizedName(this.food.unlocalizedName);
        this.setTextureName("plantmegapack:" + this.food.unlocalizedName);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.food));
        GameRegistry.registerItem(this, this.food.unlocalizedName);
        if (this.food.unlocalizedName.matches("foodBerrybowl") || this.food.unlocalizedName.matches("foodQuinoaCereal")
            || this.food.unlocalizedName.matches("foodElderberrySorbet")
            || this.food.unlocalizedName.matches("foodSnowberryCustard")
            || this.food.unlocalizedName.matches("foodMozukuSoup")
            || this.food.unlocalizedName.matches("foodQuinoaCereal")
            || this.food.unlocalizedName.matches("foodBroccoliSoup")
            || this.food.unlocalizedName.matches("foodBeetSoup")
            || this.food.unlocalizedName.matches("foodTomatoSoup")) {
            this.setMaxStackSize(1);
        }

    }

    @SideOnly(Side.CLIENT)
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
        super.onEaten(itemStack, world, player);
        return !this.food.unlocalizedName.matches("foodBerrybowl")
            && !this.food.unlocalizedName.matches("foodQuinoaCereal")
            && !this.food.unlocalizedName.matches("foodElderberrySorbet")
            && !this.food.unlocalizedName.matches("foodSnowberryCustard")
            && !this.food.unlocalizedName.matches("foodMozukuSoup")
            && !this.food.unlocalizedName.matches("foodQuinoaCereal")
            && !this.food.unlocalizedName.matches("foodBroccoliSoup")
            && !this.food.unlocalizedName.matches("foodBeetSoup")
            && !this.food.unlocalizedName.matches("foodTomatoSoup") ? itemStack : new ItemStack(Items.bowl);
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ,
        int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        Item item = itemStack.getItem();
        if (p_77648_7_ == 1 && item != null) {
            Block block = null;
            if (item == PlantMegaPack.items.getFoodItem("foodPeanuts")) {
                block = PlantMegaPack.blocks.getPlantBlock("cropPeanut");
            }

            if (block != null && player.canPlayerEdit(posX, posY + 1, posZ, p_77648_7_, itemStack)) {
                if (block.canPlaceBlockAt(world, posX, posY + 1, posZ) && world.isAirBlock(posX, posY + 1, posZ)) {
                    world.setBlock(posX, posY + 1, posZ, block);
                    --itemStack.stackSize;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
        int lines = 0;
        if (PlantMegaPack.settingsGeneral.tooltipAttributes) {
            lines = list.size();
            list.add("");
            String unlocalizedName = this.food.unlocalizedName;
            String textCrafting = StatCollector.translateToLocal("text.tooltipCrafting");
            if (unlocalizedName.matches("berriesBeauty")) {
                list.add(
                    "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodBerrybowl.name") + "§r");
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.powderBerry.name") + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodBeautyberryBlazer.name")
                        + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodBeautyberryTurnover.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesBlack")) {
                list.add(
                    "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodBerrybowl.name") + "§r");
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.powderBerry.name") + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodBlackberryTumbler.name")
                        + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodBlackberryDanish.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesBlue")) {
                list.add(
                    "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodBerrybowl.name") + "§r");
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.powderBerry.name") + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodBlueberrySlushie.name")
                        + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodBlueberryMuffin.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesElder")) {
                list.add(
                    "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodBerrybowl.name") + "§r");
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.powderBerry.name") + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodElderberrySpritzer.name")
                        + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodElderberrySorbet.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesGoose")) {
                list.add(
                    "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodBerrybowl.name") + "§r");
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.powderBerry.name") + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodGooseberryShake.name")
                        + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodGooseberryCobbler.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesHarlequinMistletoe")) {
                list.add(
                    "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodBerrybowl.name") + "§r");
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.powderBerry.name") + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodHarlequinFizz.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesHuckle")) {
                list.add(
                    "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodBerrybowl.name") + "§r");
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.powderBerry.name") + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodHuckleberryBubbler.name")
                        + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodHuckleberryTart.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesOrange")) {
                list.add(
                    "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodBerrybowl.name") + "§r");
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.powderBerry.name") + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodOrangeberryWhip.name")
                        + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodOrangeberrySquare.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesSnow")) {
                list.add(
                    "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodBerrybowl.name") + "§r");
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.powderBerry.name") + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodSnowberryCooler.name")
                        + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodSnowberryCustard.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesStraw")) {
                list.add(
                    "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodBerrybowl.name") + "§r");
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.powderBerry.name") + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodStrawberrySmoothie.name")
                        + "§r");
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodStrawberryDelight.name")
                        + "§r");
            } else if (unlocalizedName.matches("foodMozukuSeaweed")) {
                list.add(
                    "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodMozukuSoup.name") + "§r");
            } else if (!unlocalizedName.matches("foodWaterChestnut")) {
                if (unlocalizedName.matches("foodWrapSeaweed")) {
                    list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("text.crafting.salves") + "§r");
                } else if (unlocalizedName.matches("foodPricklyPearFruit")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodPricklyPearTwister.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodJelly.name") + "§r");
                } else if (unlocalizedName.matches("foodQuinoaSeeds")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodQuinoaCereal.name")
                            + "§r");
                } else if (unlocalizedName.matches("foodBeautyberry")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBeautyberryBlazer.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBeautyberryTurnover.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBerrybowl.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodJelly.name") + "§r");
                } else if (unlocalizedName.matches("foodBlackberry")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBerrybowl.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBlackberryTumbler.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBlackberryDanish.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodJelly.name") + "§r");
                } else if (unlocalizedName.matches("foodBlueberry")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBerrybowl.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBlueberrySlushie.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBlueberryMuffin.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodJelly.name") + "§r");
                } else if (unlocalizedName.matches("foodElderberry")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBerrybowl.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodElderberrySpritzer.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodElderberrySorbet.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodJelly.name") + "§r");
                } else if (unlocalizedName.matches("foodGooseberry")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBerrybowl.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodGooseberryShake.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodGooseberryCobbler.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodJelly.name") + "§r");
                } else if (unlocalizedName.matches("foodHarlequinmistletoeberry")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBerrybowl.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodHarlequinFizz.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodJelly.name") + "§r");
                } else if (unlocalizedName.matches("foodHuckleberry")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBerrybowl.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodHuckleberryBubbler.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodHuckleberryTart.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodJelly.name") + "§r");
                } else if (unlocalizedName.matches("foodOrangeberry")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBerrybowl.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodOrangeberryWhip.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodOrangeberrySquare.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodJelly.name") + "§r");
                } else if (unlocalizedName.matches("foodPorcelainberry")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBerrybowl.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodPorcelainberryMixer.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodPorcelainberryTart.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodJelly.name") + "§r");
                } else if (unlocalizedName.matches("foodSnowberry")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBerrybowl.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodSnowberryCooler.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodSnowberryCustard.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodJelly.name") + "§r");
                } else if (unlocalizedName.matches("foodStrawberry")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodBerrybowl.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodStrawberrySmoothie.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodStrawberryDelight.name")
                            + "§r");
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodJelly.name") + "§r");
                } else if (unlocalizedName.matches("foodJelly")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodSandwichPBJ.name")
                            + "§r");
                } else if (unlocalizedName.matches("foodPeanutButter")) {
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodSandwichPBJ.name")
                            + "§r");
                } else if (unlocalizedName.matches("foodBeet")) {
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.foodBeetSoup.name") + "§r");
                } else if (!unlocalizedName.matches("foodBellPepperOrange")
                    && !unlocalizedName.matches("foodBellPepperRed")
                    && !unlocalizedName.matches("foodBellPepperYellow")) {
                        if (unlocalizedName.matches("foodBroccoli")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodBroccoliSoup.name")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodStirFry.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodCassavaRoot")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodStirFry.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodCelery")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodCentellaLeaves")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodCookedRice")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodStirFry.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodCorn")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodCornFlour.name")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodStirFry.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodCornFlour")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodCornBread.name")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.wrapCorn.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodCornBread")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodBlueberryMuffin.name")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodCucumber")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodEggplant")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodStirFry.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodGrapesPurple")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodGrapeInfusion.name")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodGrapeTart.name")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodJelly.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodGreenBeans")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodStirFry.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodHops")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodHopTea.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodHyacinthBeans")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodStirFry.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodKiwi")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodJelly.name")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodKiwiPie.name")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodKiwiSplash.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodLaksaLeaves")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodLeek")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodStirFry.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodLettuce")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodOnion")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodStirFry.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodPeanuts")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodPeanutButter.name")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodCookiePeanutButter.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodRice")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodCookedRice.name")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.paper")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodSacredLotusRoot")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodStirFry.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodSorrel")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodSpinach")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodTomato")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodStirFry.name")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodTomatoSoup.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodTaroRoot")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodStirFry.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodWasabiStem")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodStirFry.name")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodWasabiQuencher.name")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodWatercress")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.sandwich")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodWildRice")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.foodCookedRice.name")
                                    + "§r");
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.paper")
                                    + "§r");
                        } else if (unlocalizedName.matches("foodWrapCorn")) {
                            list.add(
                                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                        }
                    } else {
                        list.add(
                            "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.wrap.name") + "§r");
                        list.add(
                            "§8" + textCrafting
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                        list.add(
                            "§8" + textCrafting
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodStirFry.name")
                                + "§r");
                        list.add(
                            "§8" + textCrafting
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.stuffedPepper")
                                + "§r");
                    }
            }
        }

        if (list.size() == lines + 1) {
            list.remove(list.size() - 1);
        }

    }
}
