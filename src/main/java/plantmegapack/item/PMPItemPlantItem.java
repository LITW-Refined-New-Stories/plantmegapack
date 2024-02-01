package plantmegapack.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPPlantItem;
import plantmegapack.common.PMPTab;

public class PMPItemPlantItem extends Item {

    private PMPPlantItem plantItem;

    public PMPItemPlantItem(PMPPlantItem plantItem) {
        this.plantItem = plantItem;
        this.setUnlocalizedName(this.plantItem.unlocalizedName);
        this.setTextureName("plantmegapack:" + this.plantItem.unlocalizedName);
        GameRegistry.registerItem(this, this.plantItem.unlocalizedName);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
        int lines = 0;
        String textCrafting = StatCollector.translateToLocal("text.tooltipCrafting");
        if (PlantMegaPack.settingsGeneral.tooltipAttributes) {
            lines = list.size();
            list.add("");
            if (this.plantItem.unlocalizedName.matches("leafAloe")) {
                list.add(
                    "§8" + textCrafting
                        + ": §2"
                        + StatCollector.translateToLocal("text.dye.green")
                        + " §9"
                        + StatCollector.translateToLocal("text.crafting.dye")
                        + "§r");
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.salveHealth.name") + "§r");
            } else if (this.plantItem.unlocalizedName.matches("leafWildMint")) {
                list.add(
                    "§8" + textCrafting
                        + ": §2"
                        + StatCollector.translateToLocal("text.dye.green")
                        + " §9"
                        + StatCollector.translateToLocal("text.crafting.dye")
                        + "§r");
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.salveHealth.name") + "§r");
            } else if (this.plantItem.unlocalizedName.matches("flowerBlueStarItem")) {
                list.add(
                    "§8" + textCrafting
                        + ": §b"
                        + StatCollector.translateToLocal("text.dye.lightBlue")
                        + " §9"
                        + StatCollector.translateToLocal("text.crafting.dye")
                        + "§r");
                list.add(
                    "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.salveNightVision.name") + "§r");
            } else if (!this.plantItem.unlocalizedName.matches("flowerBroomSnakeweed")
                && !this.plantItem.unlocalizedName.matches("flowerYellowToadflaxItem")) {
                    if (this.plantItem.unlocalizedName.matches("flowerOcotillo")) {
                        list.add(
                            "§8" + textCrafting
                                + ": §6"
                                + StatCollector.translateToLocal("text.dye.orange")
                                + " §9"
                                + StatCollector.translateToLocal("text.crafting.dye")
                                + "§r");
                        list.add(
                            "§8" + textCrafting
                                + ": §9"
                                + StatCollector.translateToLocal("item.salveSwiftness.name")
                                + "§r");
                    } else if (this.plantItem.unlocalizedName.matches("flowerCattail")) {
                        list.add(
                            "§8" + textCrafting
                                + ": §9"
                                + StatCollector.translateToLocal("text.crafting.torch")
                                + "§r");
                    } else if (this.plantItem.unlocalizedName.matches("flowerTorchGinger")) {
                        list.add(
                            "§8" + textCrafting
                                + ": §c"
                                + StatCollector.translateToLocal("text.dye.red")
                                + " §9"
                                + StatCollector.translateToLocal("text.crafting.dye")
                                + "§r");
                        list.add(
                            "§8" + textCrafting
                                + ": §9"
                                + StatCollector.translateToLocal("item.salveHealth.name")
                                + "§r");
                    }
                } else {
                    list.add(
                        "§8" + textCrafting
                            + ": §e"
                            + StatCollector.translateToLocal("text.dye.yellow")
                            + " §9"
                            + StatCollector.translateToLocal("text.crafting.dye")
                            + "§r");
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.salveHealth.name") + "§r");
                }
        }

        if (list.size() == lines + 1) {
            list.remove(list.size() - 1);
        }

    }
}
