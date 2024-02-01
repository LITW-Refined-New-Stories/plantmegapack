package plantmegapack.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPTab;

public class PMPItemCropFood extends ItemFood {

    public PMPItemCropFood(String unlocalizedName, int healAmount, float saturationModifier,
        boolean isWolfsFavoriteMeat) {
        super(healAmount, saturationModifier, isWolfsFavoriteMeat);
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName("plantmegapack:" + unlocalizedName);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.food));
        GameRegistry.registerItem(this, unlocalizedName);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
        int lines = 0;
        if (PlantMegaPack.settingsGeneral.tooltipAttributes) {
            lines = list.size();
            list.add("");
            String unlocalizedName = this.getUnlocalizedName()
                .substring(5);
            if (unlocalizedName.matches("foodBeet")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodBeetSoup.name")
                        + "§r");
            } else if (!unlocalizedName.matches("foodBellPepperOrange") && !unlocalizedName.matches("foodBellPepperRed")
                && !unlocalizedName.matches("foodBellPepperYellow")) {
                    if (unlocalizedName.matches("foodBroccoli")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodBroccoliSoup.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodStirFry.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodCassavaRoot")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodStirFry.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodCelery")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                    } else if (unlocalizedName.matches("foodCentellaLeaves")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                    } else if (unlocalizedName.matches("foodCookedRice")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodStirFry.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodCorn")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodCornFlour.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodStirFry.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodCornFlour")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodCornBread.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrapCorn.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodCornBread")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodBlueberryMuffin.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                    } else if (unlocalizedName.matches("foodCucumber")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                    } else if (unlocalizedName.matches("foodEggplant")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodStirFry.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodGrapesPurple")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodGrapeInfusion.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodGrapeTart.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodJelly.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodGreenBeans")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodStirFry.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodHops")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodHopTea.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodHyacinthBeans")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodStirFry.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodKiwi")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodJelly.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodKiwiPie.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodKiwiSplash.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodLaksaLeaves")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                    } else if (unlocalizedName.matches("foodLeek")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodStirFry.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodLettuce")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                    } else if (unlocalizedName.matches("foodOnion")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodStirFry.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodPeanuts")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodPeanutButter.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodCookiePeanutButter.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodRice")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodCookedRice.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.paper")
                                + "§r");
                    } else if (unlocalizedName.matches("foodSacredLotusRoot")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodStirFry.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodSorrel")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                    } else if (unlocalizedName.matches("foodSpinach")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                    } else if (unlocalizedName.matches("foodTomato")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodStirFry.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodTomatoSoup.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodTaroRoot")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodStirFry.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodWasabiStem")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodStirFry.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWasabiQuencher.name")
                                + "§r");
                    } else if (unlocalizedName.matches("foodWatercress")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.sandwich")
                                + "§r");
                    } else if (unlocalizedName.matches("foodWildRice")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodCookedRice.name")
                                + "§r");
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.paper")
                                + "§r");
                    } else if (unlocalizedName.matches("foodWrapCorn")) {
                        list.add(
                            "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                                + ": §9"
                                + StatCollector.translateToLocal("item.foodWrap.name")
                                + "§r");
                    }
                } else {
                    list.add(
                        "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodWrap.name")
                            + "§r");
                    list.add(
                        "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                            + ": §9"
                            + StatCollector.translateToLocal("text.crafting.sandwich")
                            + "§r");
                    list.add(
                        "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                            + ": §9"
                            + StatCollector.translateToLocal("item.foodStirFry.name")
                            + "§r");
                    list.add(
                        "§8" + StatCollector.translateToLocal("text.tooltip.crafting")
                            + ": §9"
                            + StatCollector.translateToLocal("text.crafting.stuffedPepper")
                            + "§r");
                }
        }

        if (list.size() == lines + 1) {
            list.remove(list.size() - 1);
        }

    }
}
