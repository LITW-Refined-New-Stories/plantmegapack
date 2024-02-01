package plantmegapack.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPSalve;
import plantmegapack.common.PMPTab;

public class PMPItemSalve extends Item {

    private PMPSalve salve;

    public PMPItemSalve(PMPSalve salve) {
        this.salve = salve;
        this.setUnlocalizedName(this.salve.unlocalizedName);
        this.setTextureName("plantmegapack:" + this.salve.unlocalizedName);
        GameRegistry.registerItem(this, this.salve.unlocalizedName);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
    }

    @SideOnly(Side.CLIENT)
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            String unlocalizedName = this.getUnlocalizedName()
                .substring(5);
            if (unlocalizedName.matches("salveFireResist")) {
                player.addPotionEffect(
                    new PotionEffect(
                        Potion.fireResistance.id,
                        PlantMegaPack.settingsGeneral.salveFireResistDuration * 20,
                        1,
                        false));
                --itemStack.stackSize;
                world.playSoundAtEntity(player, "step.cloth", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            } else if (unlocalizedName.matches("salveHealth") && player.shouldHeal()) {
                player.addPotionEffect(new PotionEffect(Potion.heal.id, 1, 0, false));
                --itemStack.stackSize;
                world.playSoundAtEntity(player, "step.cloth", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            } else if (unlocalizedName.matches("salveNightVision")) {
                player.addPotionEffect(
                    new PotionEffect(
                        Potion.nightVision.id,
                        PlantMegaPack.settingsGeneral.salveNightVisionDuration * 20,
                        1,
                        false));
                --itemStack.stackSize;
                world.playSoundAtEntity(player, "step.cloth", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            } else if (unlocalizedName.matches("salveStrength")) {
                player.addPotionEffect(
                    new PotionEffect(
                        Potion.damageBoost.id,
                        PlantMegaPack.settingsGeneral.salveStrengthDuration * 20,
                        1,
                        false));
                --itemStack.stackSize;
                world.playSoundAtEntity(player, "step.cloth", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            } else if (unlocalizedName.matches("salveSwiftness")) {
                player.addPotionEffect(
                    new PotionEffect(
                        Potion.moveSpeed.id,
                        PlantMegaPack.settingsGeneral.salveSwiftnessDuration * 20,
                        1,
                        false));
                --itemStack.stackSize;
                world.playSoundAtEntity(player, "step.cloth", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            } else if (unlocalizedName.matches("salveWaterBreathing")) {
                player.addPotionEffect(
                    new PotionEffect(
                        Potion.waterBreathing.id,
                        PlantMegaPack.settingsGeneral.salveWaterBreathingDuration * 20,
                        1,
                        false));
                --itemStack.stackSize;
                world.playSoundAtEntity(player, "step.cloth", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            }
        }

        return itemStack;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
        int lines = 0;
        if (PlantMegaPack.settingsGeneral.tooltipAttributes) {
            lines = list.size();
            list.add("");
            String unlocalizedName = this.getUnlocalizedName()
                .substring(5);
            if (unlocalizedName.matches("salveFireResist")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipEffect")
                        + ": §9"
                        + StatCollector.translateToLocal("text.tooltipDuration")
                        + String.format(" %d ", PlantMegaPack.settingsGeneral.salveFireResistDuration)
                        + StatCollector.translateToLocal("text.tooltipSeconds")
                        + "§r");
            } else if (unlocalizedName.matches("salveHealth")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipEffect")
                        + ": §9"
                        + String.format("%d ", PlantMegaPack.settingsGeneral.salveHealthHeartsHealed)
                        + StatCollector.translateToLocal("text.tooltipHeartsHealed")
                        + "§r");
            } else if (unlocalizedName.matches("salveNightVision")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipEffect")
                        + ": §9"
                        + StatCollector.translateToLocal("text.tooltipDuration")
                        + String.format(" %d ", PlantMegaPack.settingsGeneral.salveNightVisionDuration)
                        + StatCollector.translateToLocal("text.tooltipSeconds")
                        + "§r");
            } else if (unlocalizedName.matches("salveStrength")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipEffect")
                        + ": §9"
                        + StatCollector.translateToLocal("text.tooltipDuration")
                        + String.format(" %d ", PlantMegaPack.settingsGeneral.salveStrengthDuration)
                        + StatCollector.translateToLocal("text.tooltipSeconds")
                        + "§r");
            } else if (unlocalizedName.matches("salveSwiftness")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipEffect")
                        + ": §9"
                        + StatCollector.translateToLocal("text.tooltipDuration")
                        + String.format(" %d ", PlantMegaPack.settingsGeneral.salveSwiftnessDuration)
                        + StatCollector.translateToLocal("text.tooltipSeconds")
                        + "§r");
            } else if (unlocalizedName.matches("salveWaterBreathing")) {
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipEffect")
                        + ": §9"
                        + StatCollector.translateToLocal("text.tooltipDuration")
                        + String.format(" %d ", PlantMegaPack.settingsGeneral.salveWaterBreathingDuration)
                        + StatCollector.translateToLocal("text.tooltipSeconds")
                        + "§r");
            }
        }

        if (list.size() == lines + 1) {
            list.remove(list.size() - 1);
        }

    }
}
