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
import plantmegapack.common.PMPCoralFragment;
import plantmegapack.common.PMPTab;

public class PMPItemCoralFragment extends Item {

    private PMPCoralFragment coralFragment;

    public PMPItemCoralFragment(PMPCoralFragment coralFragment) {
        this.coralFragment = coralFragment;
        this.setUnlocalizedName(this.coralFragment.unlocalizedName);
        this.setTextureName("plantmegapack:" + this.coralFragment.unlocalizedName);
        GameRegistry.registerItem(this, this.coralFragment.unlocalizedName);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
        if (PlantMegaPack.settingsGeneral.tooltipAttributes) {
            list.add("");
            this.addPlantDyeColor(itemStack, list);
            list.add(
                "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                    + ": §9"
                    + StatCollector.translateToLocal("text.crafting.arrow")
                    + "§r");
            list.add(
                "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                    + ": §9"
                    + StatCollector.translateToLocal("plantPowder.coral")
                    + "§r");
            list.add(
                "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                    + ": §9"
                    + StatCollector.translateToLocal("item.salveWaterBreathing.name")
                    + "§r");
        }

    }

    @SideOnly(Side.CLIENT)
    private void addPlantDyeColor(ItemStack itemStack, List list) {
        int color = this.coralFragment.color.dyeColor;
        String dyeColorID = "";
        String formatColor = "";
        switch (color) {
            case 1:
                dyeColorID = "red";
                formatColor = "4";
                break;
            case 2:
                dyeColorID = "green";
                formatColor = "2";
            case 3:
            case 4:
            default:
                break;
            case 5:
                dyeColorID = "purple";
                formatColor = "5";
                break;
            case 6:
                dyeColorID = "cyan";
                formatColor = "3";
                break;
            case 7:
                dyeColorID = "lightGray";
                formatColor = "7";
                break;
            case 8:
                dyeColorID = "gray";
                formatColor = "8";
                break;
            case 9:
                dyeColorID = "pink";
                formatColor = "c";
                break;
            case 10:
                dyeColorID = "lime";
                formatColor = "a";
                break;
            case 11:
                dyeColorID = "yellow";
                formatColor = "e";
                break;
            case 12:
                dyeColorID = "lightBlue";
                formatColor = "b";
                break;
            case 13:
                dyeColorID = "magenta";
                formatColor = "d";
                break;
            case 14:
                dyeColorID = "orange";
                formatColor = "6";
        }

        if (dyeColorID.length() > 0) {
            list.add(
                "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                    + ": §"
                    + formatColor
                    + StatCollector.translateToLocal("text.dye." + dyeColorID)
                    + " §9"
                    + StatCollector.translateToLocal("text.crafting.dye")
                    + "§r");
        }

    }

    @SideOnly(Side.CLIENT)
    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {
        super.getIsRepairable(stack1, stack2);
        return false;
    }
}
