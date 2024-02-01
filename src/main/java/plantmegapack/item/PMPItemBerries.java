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

public class PMPItemBerries extends ItemFood {

    public PMPItemBerries(String unlocalizedName, int foodValue, float saturation) {
        super(foodValue, saturation, false);
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName("plantmegapack:" + unlocalizedName);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.food));
        GameRegistry.registerItem(this, unlocalizedName);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
        if (PlantMegaPack.settingsGeneral.tooltipAttributes) {
            list.add("");
            list.add(
                "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                    + ": §9"
                    + StatCollector.translateToLocal("item.foodBerrybowl.name")
                    + "§r");
            list.add(
                "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                    + ": §9"
                    + StatCollector.translateToLocal("item.powderBerry.name")
                    + "§r");
            String unlocalizedName = this.getUnlocalizedName()
                .substring(5);
            if (unlocalizedName.matches("berriesBeauty")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodBeautyberryBlazer.name")
                        + "§r");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodBeautyberryTurnover.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesBlack")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodBlackberryTumbler.name")
                        + "§r");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodBlackberryDanish.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesBlue")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodBlueberrySlushie.name")
                        + "§r");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodBlueberryMuffin.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesElder")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodElderberrySpritzer.name")
                        + "§r");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodElderberrySorbet.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesGoose")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodGooseberryShake.name")
                        + "§r");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodGooseberryCobbler.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesHarlequinMistletoe")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodHarlequinFizz.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesHuckle")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodHuckleberryBubbler.name")
                        + "§r");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodHuckleberryTart.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesOrange")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodOrangeberryWhip.name")
                        + "§r");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodOrangeberrySquare.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesSnow")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodSnowberryCooler.name")
                        + "§r");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodSnowberryCustard.name")
                        + "§r");
            } else if (unlocalizedName.matches("berriesStraw")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodStrawberrySmoothie.name")
                        + "§r");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.foodStrawberryDelight.name")
                        + "§r");
            }
        }

    }
}
