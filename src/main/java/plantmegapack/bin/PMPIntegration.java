package plantmegapack.bin;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPPlant;

public abstract class PMPIntegration {

    public static void initModIntegration() {
        String modID = "butterflymania";
        if (Loader.isModLoaded(modID)) {
            initButterflyMania(modID, true);
        }

    }

    private static void initButterflyMania(String modID, boolean useItemStack) {
        String msg = "registerButterflyPlant";
        PMPPlant[] var3;
        int var4;
        int var5;
        PMPPlant plant;
        if (useItemStack) {
            var3 = PMPPlant.values();
            var4 = var3.length;

            for (var5 = 0; var5 < var4; ++var5) {
                plant = var3[var5];
                if (plant.insectPlant > 0) {
                    FMLInterModComms.sendMessage(
                        modID,
                        msg,
                        new ItemStack(PlantMegaPack.blocks.getPlantBlock(plant.ID), 1, plant.insectPlant));
                }
            }
        } else {
            var3 = PMPPlant.values();
            var4 = var3.length;

            for (var5 = 0; var5 < var4; ++var5) {
                plant = var3[var5];
                if (plant.insectPlant > 0) {
                    FMLInterModComms.sendMessage(modID, msg, plant.ID);
                }
            }
        }

    }
}
