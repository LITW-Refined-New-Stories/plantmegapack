package plantmegapack.bin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;

import plantmegapack.PlantMegaPack;

public class PMPSettings {

    protected File optionsFile;
    public boolean contentAquatic;
    public boolean contentChestSeeds;
    public boolean contentCraftingBambooBlocks;
    public boolean contentCrops;
    public boolean contentWallBracketExtra;
    public boolean plantBambooFallsWhenBroken;
    public boolean plantEffectPoison;
    public boolean plantEffectThorns;
    public boolean plantSolidShelfEpiphytes;
    public boolean tooltipAttributes;
    public boolean tooltipCategory;
    public boolean tooltipGrowthStages;
    public boolean tooltipLatinName;
    public boolean worldgenCoralReefs;
    public int worldgenEndRate;
    public int worldgenEndPasses;
    public int worldgenNetherRate;
    public int worldgenNetherPasses;
    public int worldgenOverworldRate;
    public int worldgenOverworldPasses;
    public int worldgenSkyRate;
    public int worldgenSkyPasses;
    public boolean worldgenSpawnAtFullGrowth;
    public boolean worldgenVillageCrops;
    public boolean worldgenVillagePlants;
    public int powderConditionerRadius;
    public int powderConditionerStrength;
    public int powderDefoliantRadius;
    public int powderDefoliantStrength;
    public int powderFertilizerRadius;
    public int powderFertilizerStrength;
    public int salveFireResistDuration;
    public int salveHealthHeartsHealed;
    public int salveNightVisionDuration;
    public int salveStrengthDuration;
    public int salveSwiftnessDuration;
    public int salveWaterBreathingDuration;

    public PMPSettings(String configDirPath) {
        this.optionsFile = new File(configDirPath + "settings.cfg");
        this.resetContentDefaults();
        this.resetGeneralDefaults();
        this.resetItemPowderDefaults();
        this.resetItemSalveDefaults();
        this.resetPlantDefaults();
        this.resetWorldgenDefaults();
        this.checkConfigDirs(configDirPath);
        this.loadOptions();
    }

    private void checkConfigDirs(String configDirPath) {
        File pmpConfigDir = new File(configDirPath);
        if (!pmpConfigDir.exists()) {
            try {
                pmpConfigDir.mkdir();
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }

        pmpConfigDir = new File(configDirPath + "/decorators");
        if (!pmpConfigDir.exists()) {
            try {
                pmpConfigDir.mkdir();
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        pmpConfigDir = new File(configDirPath + "/plants");
        if (!pmpConfigDir.exists()) {
            try {
                pmpConfigDir.mkdir();
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

    }

    public void resetContentDefaults() {
        this.contentAquatic = true;
        this.contentChestSeeds = true;
        this.contentCraftingBambooBlocks = true;
        this.contentCrops = true;
        this.contentWallBracketExtra = false;
    }

    public void resetGeneralDefaults() {
        this.tooltipAttributes = true;
        this.tooltipCategory = true;
        this.tooltipGrowthStages = true;
        this.tooltipLatinName = true;
        this.powderDefoliantRadius = 3;
        this.powderDefoliantStrength = 70;
        this.powderFertilizerRadius = 3;
        this.powderFertilizerStrength = 70;
    }

    public void resetItemPowderDefaults() {
        this.powderConditionerRadius = 2;
        this.powderConditionerStrength = 90;
        this.powderDefoliantRadius = 2;
        this.powderDefoliantStrength = 90;
        this.powderFertilizerRadius = 2;
        this.powderFertilizerStrength = 90;
    }

    public void resetItemSalveDefaults() {
        this.salveFireResistDuration = 30;
        this.salveHealthHeartsHealed = 4;
        this.salveNightVisionDuration = 30;
        this.salveStrengthDuration = 30;
        this.salveSwiftnessDuration = 30;
        this.salveWaterBreathingDuration = 30;
    }

    public void resetPlantDefaults() {
        this.plantBambooFallsWhenBroken = true;
        this.plantEffectPoison = false;
        this.plantEffectThorns = true;
        this.plantSolidShelfEpiphytes = true;
    }

    public void resetWorldgenDefaults() {
        this.worldgenCoralReefs = true;
        this.worldgenSpawnAtFullGrowth = false;
        this.worldgenVillageCrops = true;
        this.worldgenVillagePlants = true;
        this.resetWorldgenEnd();
        this.resetWorldgenNether();
        this.resetWorldgenOverworld();
        this.resetWorldgenSky();
    }

    public void resetWorldgenEnd() {
        this.worldgenEndRate = 50;
        this.worldgenEndPasses = 4;
    }

    public void resetWorldgenNether() {
        this.worldgenNetherRate = 50;
        this.worldgenNetherPasses = 4;
    }

    public void resetWorldgenOverworld() {
        this.worldgenOverworldRate = 90;
        this.worldgenOverworldPasses = 8;
    }

    public void resetWorldgenSky() {
        this.worldgenSkyRate = 50;
        this.worldgenSkyPasses = 4;
    }

    public void loadOptions() {
        try {
            if (!this.optionsFile.exists()) {
                this.saveOptions();
                return;
            }

            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.optionsFile));
            String line = "";

            while ((line = bufferedreader.readLine()) != null) {
                try {
                    String[] optionLine = line.split(":");
                    if (optionLine[0].equals("contentAquatic")) {
                        this.contentAquatic = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("contentChestSeeds")) {
                        this.contentChestSeeds = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("contentCraftingBambooBlocks")) {
                        this.contentCraftingBambooBlocks = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("contentCrops")) {
                        this.contentCrops = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("contentWallBracketExtra")) {
                        this.contentWallBracketExtra = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("plantBambooFallsWhenBroken")) {
                        this.plantBambooFallsWhenBroken = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("plantEffectPoison")) {
                        this.plantEffectPoison = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("plantEffectThorns")) {
                        this.plantEffectThorns = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("plantSolidShelfEpiphyte")) {
                        this.plantSolidShelfEpiphytes = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("tooltipAttributes")) {
                        this.tooltipAttributes = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("tooltipCategory")) {
                        this.tooltipCategory = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("tooltipGrowthStages")) {
                        this.tooltipGrowthStages = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("tooltipLatinName")) {
                        this.tooltipLatinName = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("worldgenCoralReefs")) {
                        this.worldgenCoralReefs = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("worldgenEndRate")) {
                        this.worldgenEndRate = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("worldgenEndPasses")) {
                        this.worldgenEndPasses = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("worldgenNetherRate")) {
                        this.worldgenNetherRate = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("worldgenNetherPasses")) {
                        this.worldgenNetherPasses = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("worldgenOverworldRate")) {
                        this.worldgenOverworldRate = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("worldgenOverworldPasses")) {
                        this.worldgenOverworldPasses = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("worldgenRateSky")) {
                        this.worldgenSkyRate = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("worldgenSkyPasses")) {
                        this.worldgenSkyPasses = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("worldgenSpawnAtFullGrowth")) {
                        this.worldgenSpawnAtFullGrowth = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("worldgenVillageCrops")) {
                        this.worldgenVillageCrops = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("worldgenVillagePlants")) {
                        this.worldgenVillagePlants = optionLine[1].equals("true");
                    }

                    if (optionLine[0].equals("powderConditionerRadius")) {
                        this.powderConditionerRadius = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("powderConditionerStrength")) {
                        this.powderConditionerStrength = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("powderDefoliantRadius")) {
                        this.powderDefoliantRadius = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("powderDefoliantStrength")) {
                        this.powderDefoliantStrength = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("powderFertilizerRadius")) {
                        this.powderFertilizerRadius = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("powderFertilizerStrength")) {
                        this.powderFertilizerStrength = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("salveFireResistDuration")) {
                        this.salveFireResistDuration = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("salveHealthDuration")) {
                        this.salveHealthHeartsHealed = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("salveNightVisionDuration")) {
                        this.salveNightVisionDuration = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("salveStrengthDuration")) {
                        this.salveStrengthDuration = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("salveSwiftnessDuration")) {
                        this.salveSwiftnessDuration = Integer.parseInt(optionLine[1]);
                    }

                    if (optionLine[0].equals("salveWaterBreathingDuration")) {
                        this.salveWaterBreathingDuration = Integer.parseInt(optionLine[1]);
                    }
                } catch (Exception var4) {
                    PlantMegaPack.instance.logOutput("Skipping bad option: " + line);
                }
            }

            bufferedreader.close();
        } catch (Exception var5) {
            PlantMegaPack.instance.logOutput("Failed to load options");
            return;
        }

        PlantMegaPack.instance.logOutput("Config file loaded: settings.cfg");
    }

    public void saveOptions() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.optionsFile));
            printwriter.println("contentAquatic:" + this.contentAquatic);
            printwriter.println("contentChestSeeds:" + this.contentChestSeeds);
            printwriter.println("contentCraftingBambooBlocks:" + this.contentCraftingBambooBlocks);
            printwriter.println("contentCrops:" + this.contentCrops);
            printwriter.println("contentWallBracketExtra:" + this.contentWallBracketExtra);
            printwriter.println("plantBambooFallsWhenBroken:" + this.plantBambooFallsWhenBroken);
            printwriter.println("plantEffectPoison:" + this.plantEffectPoison);
            printwriter.println("plantEffectThorns:" + this.plantEffectThorns);
            printwriter.println("plantSolidShelfEpiphyte:" + this.plantSolidShelfEpiphytes);
            printwriter.println("tooltipAttributes:" + this.tooltipAttributes);
            printwriter.println("tooltipCategory:" + this.tooltipCategory);
            printwriter.println("tooltipGrowthStages:" + this.tooltipGrowthStages);
            printwriter.println("tooltipLatinName:" + this.tooltipLatinName);
            printwriter.println("worldgenCoralReefs:" + this.worldgenCoralReefs);
            printwriter.println("worldgenEndRate:" + this.worldgenEndRate);
            printwriter.println("worldgenEndPasses:" + this.worldgenEndPasses);
            printwriter.println("worldgenNetherRate:" + this.worldgenNetherRate);
            printwriter.println("worldgenNetherPasses:" + this.worldgenNetherPasses);
            printwriter.println("worldgenOverworldRate:" + this.worldgenOverworldRate);
            printwriter.println("worldgenOverworldPasses:" + this.worldgenOverworldPasses);
            printwriter.println("worldgenSkyRate:" + this.worldgenSkyRate);
            printwriter.println("worldgenSkyPasses:" + this.worldgenSkyPasses);
            printwriter.println("worldgenSpawnAtFullGrowth:" + this.worldgenSpawnAtFullGrowth);
            printwriter.println("worldgenVillageCrops:" + this.worldgenVillageCrops);
            printwriter.println("worldgenVillagePlants:" + this.worldgenVillagePlants);
            printwriter.println("powderConditionerRadius:" + this.powderConditionerRadius);
            printwriter.println("powderConditionerStrength:" + this.powderConditionerStrength);
            printwriter.println("powderDefoliantRadius:" + this.powderDefoliantRadius);
            printwriter.println("powderDefoliantStrength:" + this.powderDefoliantStrength);
            printwriter.println("powderFertilizerRadius:" + this.powderFertilizerRadius);
            printwriter.println("powderFertilizerStrength:" + this.powderFertilizerStrength);
            printwriter.println("salveFireResistDuration:" + this.salveFireResistDuration);
            printwriter.println("salveHealthHeartsHealed:" + this.salveHealthHeartsHealed);
            printwriter.println("salveNightVisionDuration:" + this.salveNightVisionDuration);
            printwriter.println("salveStrengthDuration:" + this.salveStrengthDuration);
            printwriter.println("salveSwiftnessDuration:" + this.salveSwiftnessDuration);
            printwriter.println("salveWaterBreathingDuration:" + this.salveWaterBreathingDuration);
            printwriter.close();
        } catch (Exception var2) {
            PlantMegaPack.instance.logOutput("Failed to save options");
            return;
        }

        PlantMegaPack.instance.logOutput("Config file saved: settings.cfg");
    }

    public void setOptionValue(PMPOptions option, int value) {
        if (option == PMPSettings.PMPOptions.CONTENT_AQUATIC) {
            this.contentAquatic = !this.contentAquatic;
        }

        if (option == PMPSettings.PMPOptions.CONTENT_CHEST_SEEDS) {
            this.contentChestSeeds = !this.contentChestSeeds;
        }

        if (option == PMPSettings.PMPOptions.CONTENT_CRAFTING_BAMBOOBLOCKS) {
            this.contentCraftingBambooBlocks = !this.contentCraftingBambooBlocks;
        }

        if (option == PMPSettings.PMPOptions.CONTENT_CROPS) {
            this.contentCrops = !this.contentCrops;
        }

        if (option == PMPSettings.PMPOptions.CONTENT_WALLBRACKETEXTRA) {
            this.contentWallBracketExtra = !this.contentWallBracketExtra;
        }

        if (option == PMPSettings.PMPOptions.PLANT_BAMBOOFALLSWHENBROKEN) {
            this.plantBambooFallsWhenBroken = !this.plantBambooFallsWhenBroken;
        }

        if (option == PMPSettings.PMPOptions.PLANT_EFFECT_POISON) {
            this.plantEffectPoison = !this.plantEffectPoison;
        }

        if (option == PMPSettings.PMPOptions.PLANT_EFFECT_THORNS) {
            this.plantEffectThorns = !this.plantEffectThorns;
        }

        if (option == PMPSettings.PMPOptions.PLANT_SOLIDSHELFEPIPHYTES) {
            this.plantSolidShelfEpiphytes = !this.plantSolidShelfEpiphytes;
        }

        if (option == PMPSettings.PMPOptions.TOOLTIP_ATTRIBUTES) {
            this.tooltipAttributes = !this.tooltipAttributes;
        }

        if (option == PMPSettings.PMPOptions.TOOLTIP_CATEGORY) {
            this.tooltipCategory = !this.tooltipCategory;
        }

        if (option == PMPSettings.PMPOptions.TOOLTIP_GROWTH_STAGES) {
            this.tooltipGrowthStages = !this.tooltipGrowthStages;
        }

        if (option == PMPSettings.PMPOptions.TOOLTIP_LATIN_NAME) {
            this.tooltipLatinName = !this.tooltipLatinName;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_CORALREEFS) {
            this.worldgenCoralReefs = !this.worldgenCoralReefs;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_END_RATE) {
            this.worldgenEndRate = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_END_PASSES) {
            this.worldgenEndPasses = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_NETHER_RATE) {
            this.worldgenNetherRate = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_NETHER_PASSES) {
            this.worldgenNetherPasses = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_OVERWORLD_RATE) {
            this.worldgenOverworldRate = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_OVERWORLD_PASSES) {
            this.worldgenOverworldPasses = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_SKY_RATE) {
            this.worldgenSkyRate = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_SKY_PASSES) {
            this.worldgenSkyPasses = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_SPAWNATFULLGROWTH) {
            this.worldgenSpawnAtFullGrowth = !this.worldgenSpawnAtFullGrowth;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_VILLAGE_CROPS) {
            this.worldgenVillageCrops = !this.worldgenVillageCrops;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_VILLAGE_PLANTS) {
            this.worldgenVillagePlants = !this.worldgenVillagePlants;
        }

        if (option == PMPSettings.PMPOptions.POWDER_CONDITIONER_RADIUS) {
            this.powderConditionerRadius = value;
        }

        if (option == PMPSettings.PMPOptions.POWDER_CONDITIONER_STRENGTH) {
            this.powderConditionerStrength = value;
        }

        if (option == PMPSettings.PMPOptions.POWDER_DEFOLIANT_RADIUS) {
            this.powderDefoliantRadius = value;
        }

        if (option == PMPSettings.PMPOptions.POWDER_DEFOLIANT_STRENGTH) {
            this.powderDefoliantStrength = value;
        }

        if (option == PMPSettings.PMPOptions.POWDER_FERTILIZER_RADIUS) {
            this.powderFertilizerRadius = value;
        }

        if (option == PMPSettings.PMPOptions.POWDER_FERTILIZER_STRENGTH) {
            this.powderFertilizerStrength = value;
        }

        if (option == PMPSettings.PMPOptions.SALVE_FIRERESIST_DURATION) {
            this.salveFireResistDuration = value;
        }

        if (option == PMPSettings.PMPOptions.SALVE_HEALTH_HEARTSHEALED) {
            this.salveHealthHeartsHealed = value;
        }

        if (option == PMPSettings.PMPOptions.SALVE_NIGHVISION_DURATION) {
            this.salveNightVisionDuration = value;
        }

        if (option == PMPSettings.PMPOptions.SALVE_STRENGTH_DURATION) {
            this.salveStrengthDuration = value;
        }

        if (option == PMPSettings.PMPOptions.SALVE_SWIFTNESS_DURATION) {
            this.salveSwiftnessDuration = value;
        }

        if (option == PMPSettings.PMPOptions.SALVE_WATERBREATHING_DURATION) {
            this.salveWaterBreathingDuration = value;
        }

    }

    public void setOptionIntValue(PMPOptions option, int value) {
        if (option == PMPSettings.PMPOptions.WORLDGEN_END_RATE) {
            this.worldgenEndRate = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_END_PASSES) {
            this.worldgenEndPasses = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_NETHER_RATE) {
            this.worldgenNetherRate = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_NETHER_PASSES) {
            this.worldgenNetherPasses = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_OVERWORLD_RATE) {
            this.worldgenOverworldRate = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_OVERWORLD_PASSES) {
            this.worldgenOverworldPasses = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_SKY_RATE) {
            this.worldgenSkyRate = value;
        }

        if (option == PMPSettings.PMPOptions.WORLDGEN_SKY_PASSES) {
            this.worldgenSkyPasses = value;
        }

        if (option == PMPSettings.PMPOptions.POWDER_CONDITIONER_RADIUS) {
            this.powderConditionerRadius = value;
        }

        if (option == PMPSettings.PMPOptions.POWDER_CONDITIONER_STRENGTH) {
            this.powderConditionerStrength = value;
        }

        if (option == PMPSettings.PMPOptions.POWDER_DEFOLIANT_RADIUS) {
            this.powderDefoliantRadius = value;
        }

        if (option == PMPSettings.PMPOptions.POWDER_DEFOLIANT_STRENGTH) {
            this.powderDefoliantStrength = value;
        }

        if (option == PMPSettings.PMPOptions.POWDER_FERTILIZER_RADIUS) {
            this.powderFertilizerRadius = value;
        }

        if (option == PMPSettings.PMPOptions.POWDER_FERTILIZER_STRENGTH) {
            this.powderFertilizerStrength = value;
        }

        if (option == PMPSettings.PMPOptions.SALVE_FIRERESIST_DURATION) {
            this.salveFireResistDuration = value;
        }

        if (option == PMPSettings.PMPOptions.SALVE_HEALTH_HEARTSHEALED) {
            this.salveHealthHeartsHealed = value;
        }

        if (option == PMPSettings.PMPOptions.SALVE_NIGHVISION_DURATION) {
            this.salveNightVisionDuration = value;
        }

        if (option == PMPSettings.PMPOptions.SALVE_STRENGTH_DURATION) {
            this.salveStrengthDuration = value;
        }

        if (option == PMPSettings.PMPOptions.SALVE_SWIFTNESS_DURATION) {
            this.salveSwiftnessDuration = value;
        }

        if (option == PMPSettings.PMPOptions.SALVE_WATERBREATHING_DURATION) {
            this.salveWaterBreathingDuration = value;
        }

    }

    public float getOptionFloatValue(PMPOptions option) {
        switch (option) {
            case WORLDGEN_END_RATE:
                return (float) this.worldgenEndRate;
            case WORLDGEN_END_PASSES:
                return (float) this.worldgenEndPasses;
            case WORLDGEN_NETHER_RATE:
                return (float) this.worldgenNetherRate;
            case WORLDGEN_NETHER_PASSES:
                return (float) this.worldgenNetherPasses;
            case WORLDGEN_OVERWORLD_RATE:
                return (float) this.worldgenOverworldRate;
            case WORLDGEN_OVERWORLD_PASSES:
                return (float) this.worldgenOverworldPasses;
            case WORLDGEN_SKY_RATE:
                return (float) this.worldgenSkyRate;
            case WORLDGEN_SKY_PASSES:
                return (float) this.worldgenSkyPasses;
            case WORLDGEN_SPAWNATFULLGROWTH:
            case WORLDGEN_VILLAGE_CROPS:
            case WORLDGEN_VILLAGE_PLANTS:
            default:
                return 0.0F;
            case POWDER_CONDITIONER_RADIUS:
                return (float) this.powderConditionerRadius;
            case POWDER_CONDITIONER_STRENGTH:
                return (float) this.powderConditionerStrength;
            case POWDER_DEFOLIANT_RADIUS:
                return (float) this.powderDefoliantRadius;
            case POWDER_DEFOLIANT_STRENGTH:
                return (float) this.powderDefoliantStrength;
            case POWDER_FERTILIZER_RADIUS:
                return (float) this.powderFertilizerRadius;
            case POWDER_FERTILIZER_STRENGTH:
                return (float) this.powderFertilizerStrength;
            case SALVE_FIRERESIST_DURATION:
                return (float) this.salveFireResistDuration;
            case SALVE_HEALTH_HEARTSHEALED:
                return (float) this.salveHealthHeartsHealed;
            case SALVE_NIGHVISION_DURATION:
                return (float) this.salveNightVisionDuration;
            case SALVE_STRENGTH_DURATION:
                return (float) this.salveStrengthDuration;
            case SALVE_SWIFTNESS_DURATION:
                return (float) this.salveSwiftnessDuration;
            case SALVE_WATERBREATHING_DURATION:
                return (float) this.salveWaterBreathingDuration;
        }
    }

    public boolean getOptionOrdinalValue(PMPOptions option) {
        switch (option) {
            case CONTENT_AQUATIC:
                return this.contentAquatic;
            case CONTENT_CRAFTING_BAMBOOBLOCKS:
                return this.contentCraftingBambooBlocks;
            case CONTENT_CROPS:
                return this.contentCrops;
            case PLANT_BAMBOOFALLSWHENBROKEN:
                return this.plantBambooFallsWhenBroken;
            case PLANT_EFFECT_POISON:
                return this.plantEffectPoison;
            case PLANT_EFFECT_THORNS:
                return this.plantEffectThorns;
            case PLANT_SOLIDSHELFEPIPHYTES:
                return this.plantSolidShelfEpiphytes;
            case TOOLTIP_ATTRIBUTES:
                return this.tooltipAttributes;
            case TOOLTIP_CATEGORY:
                return this.tooltipCategory;
            case TOOLTIP_GROWTH_STAGES:
                return this.tooltipGrowthStages;
            case TOOLTIP_LATIN_NAME:
                return this.tooltipLatinName;
            case WORLDGEN_CORALREEFS:
                return this.worldgenCoralReefs;
            case WORLDGEN_END_RATE:
            case WORLDGEN_END_PASSES:
            case WORLDGEN_NETHER_RATE:
            case WORLDGEN_NETHER_PASSES:
            case WORLDGEN_OVERWORLD_RATE:
            case WORLDGEN_OVERWORLD_PASSES:
            case WORLDGEN_SKY_RATE:
            case WORLDGEN_SKY_PASSES:
            case POWDER_CONDITIONER_RADIUS:
            case POWDER_CONDITIONER_STRENGTH:
            case POWDER_DEFOLIANT_RADIUS:
            case POWDER_DEFOLIANT_STRENGTH:
            case POWDER_FERTILIZER_RADIUS:
            case POWDER_FERTILIZER_STRENGTH:
            case SALVE_FIRERESIST_DURATION:
            case SALVE_HEALTH_HEARTSHEALED:
            case SALVE_NIGHVISION_DURATION:
            case SALVE_STRENGTH_DURATION:
            case SALVE_SWIFTNESS_DURATION:
            case SALVE_WATERBREATHING_DURATION:
            default:
                return false;
            case WORLDGEN_SPAWNATFULLGROWTH:
                return this.worldgenSpawnAtFullGrowth;
            case WORLDGEN_VILLAGE_CROPS:
                return this.worldgenVillageCrops;
            case WORLDGEN_VILLAGE_PLANTS:
                return this.worldgenVillagePlants;
            case CONTENT_WALLBRACKETEXTRA:
                return this.contentWallBracketExtra;
            case CONTENT_CHEST_SEEDS:
                return this.contentChestSeeds;
        }
    }

    public String getKeyBinding(PMPOptions option) {
        String s = I18n.format(option.getEnumString(), new Object[0]) + ": ";
        if (option.getEnumBoolean()) {
            boolean flag = this.getOptionOrdinalValue(option);
            return flag ? s + I18n.format("options.on", new Object[0]) : s + I18n.format("options.off", new Object[0]);
        } else {
            return s;
        }
    }

    static final class SwitchOptions {

        static final int[] optionIds = new int[PMPSettings.PMPOptions.values().length];

        static {
            try {
                optionIds[PMPSettings.PMPOptions.CONTENT_AQUATIC.ordinal()] = 1;
            } catch (NoSuchFieldError var37) {}

            try {
                optionIds[PMPSettings.PMPOptions.CONTENT_CRAFTING_BAMBOOBLOCKS.ordinal()] = 2;
            } catch (NoSuchFieldError var36) {}

            try {
                optionIds[PMPSettings.PMPOptions.CONTENT_CROPS.ordinal()] = 3;
            } catch (NoSuchFieldError var35) {}

            try {
                optionIds[PMPSettings.PMPOptions.PLANT_BAMBOOFALLSWHENBROKEN.ordinal()] = 4;
            } catch (NoSuchFieldError var34) {}

            try {
                optionIds[PMPSettings.PMPOptions.PLANT_EFFECT_POISON.ordinal()] = 5;
            } catch (NoSuchFieldError var33) {}

            try {
                optionIds[PMPSettings.PMPOptions.PLANT_EFFECT_THORNS.ordinal()] = 6;
            } catch (NoSuchFieldError var32) {}

            try {
                optionIds[PMPSettings.PMPOptions.PLANT_SOLIDSHELFEPIPHYTES.ordinal()] = 7;
            } catch (NoSuchFieldError var31) {}

            try {
                optionIds[PMPSettings.PMPOptions.TOOLTIP_ATTRIBUTES.ordinal()] = 8;
            } catch (NoSuchFieldError var30) {}

            try {
                optionIds[PMPSettings.PMPOptions.TOOLTIP_CATEGORY.ordinal()] = 9;
            } catch (NoSuchFieldError var29) {}

            try {
                optionIds[PMPSettings.PMPOptions.TOOLTIP_GROWTH_STAGES.ordinal()] = 10;
            } catch (NoSuchFieldError var28) {}

            try {
                optionIds[PMPSettings.PMPOptions.TOOLTIP_LATIN_NAME.ordinal()] = 11;
            } catch (NoSuchFieldError var27) {}

            try {
                optionIds[PMPSettings.PMPOptions.WORLDGEN_CORALREEFS.ordinal()] = 12;
            } catch (NoSuchFieldError var26) {}

            try {
                optionIds[PMPSettings.PMPOptions.WORLDGEN_END_RATE.ordinal()] = 13;
            } catch (NoSuchFieldError var25) {}

            try {
                optionIds[PMPSettings.PMPOptions.WORLDGEN_END_PASSES.ordinal()] = 14;
            } catch (NoSuchFieldError var24) {}

            try {
                optionIds[PMPSettings.PMPOptions.WORLDGEN_NETHER_RATE.ordinal()] = 15;
            } catch (NoSuchFieldError var23) {}

            try {
                optionIds[PMPSettings.PMPOptions.WORLDGEN_NETHER_PASSES.ordinal()] = 16;
            } catch (NoSuchFieldError var22) {}

            try {
                optionIds[PMPSettings.PMPOptions.WORLDGEN_OVERWORLD_RATE.ordinal()] = 17;
            } catch (NoSuchFieldError var21) {}

            try {
                optionIds[PMPSettings.PMPOptions.WORLDGEN_OVERWORLD_PASSES.ordinal()] = 18;
            } catch (NoSuchFieldError var20) {}

            try {
                optionIds[PMPSettings.PMPOptions.WORLDGEN_SKY_RATE.ordinal()] = 19;
            } catch (NoSuchFieldError var19) {}

            try {
                optionIds[PMPSettings.PMPOptions.WORLDGEN_SKY_PASSES.ordinal()] = 20;
            } catch (NoSuchFieldError var18) {}

            try {
                optionIds[PMPSettings.PMPOptions.WORLDGEN_SPAWNATFULLGROWTH.ordinal()] = 21;
            } catch (NoSuchFieldError var17) {}

            try {
                optionIds[PMPSettings.PMPOptions.WORLDGEN_VILLAGE_CROPS.ordinal()] = 22;
            } catch (NoSuchFieldError var16) {}

            try {
                optionIds[PMPSettings.PMPOptions.WORLDGEN_VILLAGE_PLANTS.ordinal()] = 23;
            } catch (NoSuchFieldError var15) {}

            try {
                optionIds[PMPSettings.PMPOptions.POWDER_CONDITIONER_RADIUS.ordinal()] = 24;
            } catch (NoSuchFieldError var14) {}

            try {
                optionIds[PMPSettings.PMPOptions.POWDER_CONDITIONER_STRENGTH.ordinal()] = 25;
            } catch (NoSuchFieldError var13) {}

            try {
                optionIds[PMPSettings.PMPOptions.POWDER_DEFOLIANT_RADIUS.ordinal()] = 26;
            } catch (NoSuchFieldError var12) {}

            try {
                optionIds[PMPSettings.PMPOptions.POWDER_DEFOLIANT_STRENGTH.ordinal()] = 27;
            } catch (NoSuchFieldError var11) {}

            try {
                optionIds[PMPSettings.PMPOptions.POWDER_FERTILIZER_RADIUS.ordinal()] = 28;
            } catch (NoSuchFieldError var10) {}

            try {
                optionIds[PMPSettings.PMPOptions.POWDER_FERTILIZER_STRENGTH.ordinal()] = 29;
            } catch (NoSuchFieldError var9) {}

            try {
                optionIds[PMPSettings.PMPOptions.SALVE_FIRERESIST_DURATION.ordinal()] = 30;
            } catch (NoSuchFieldError var8) {}

            try {
                optionIds[PMPSettings.PMPOptions.SALVE_HEALTH_HEARTSHEALED.ordinal()] = 31;
            } catch (NoSuchFieldError var7) {}

            try {
                optionIds[PMPSettings.PMPOptions.SALVE_NIGHVISION_DURATION.ordinal()] = 32;
            } catch (NoSuchFieldError var6) {}

            try {
                optionIds[PMPSettings.PMPOptions.SALVE_STRENGTH_DURATION.ordinal()] = 33;
            } catch (NoSuchFieldError var5) {}

            try {
                optionIds[PMPSettings.PMPOptions.SALVE_SWIFTNESS_DURATION.ordinal()] = 34;
            } catch (NoSuchFieldError var4) {}

            try {
                optionIds[PMPSettings.PMPOptions.SALVE_WATERBREATHING_DURATION.ordinal()] = 35;
            } catch (NoSuchFieldError var3) {}

            try {
                optionIds[PMPSettings.PMPOptions.CONTENT_WALLBRACKETEXTRA.ordinal()] = 36;
            } catch (NoSuchFieldError var2) {}

            try {
                optionIds[PMPSettings.PMPOptions.CONTENT_CHEST_SEEDS.ordinal()] = 37;
            } catch (NoSuchFieldError var1) {}

        }
    }

    public static enum PMPOptions {

        CONTENT_AQUATIC("option.contentAquatic", false, true),
        CONTENT_CRAFTING_BAMBOOBLOCKS("option.contentCraftingBambooBlocks", false, true),
        CONTENT_CROPS("option.contentCrops", false, true),
        PLANT_BAMBOOFALLSWHENBROKEN("option.plantBambooFallsWhenBroken", false, true),
        PLANT_EFFECT_POISON("option.plantEffectPoison", false, true),
        PLANT_EFFECT_THORNS("option.plantEffectThorns", false, true),
        PLANT_SOLIDSHELFEPIPHYTES("option.plantSolidShelfEpiphytes", false, true),
        TOOLTIP_ATTRIBUTES("option.tooltipAttributes", false, true),
        TOOLTIP_CATEGORY("option.tooltipCategory", false, true),
        TOOLTIP_GROWTH_STAGES("option.tooltipGrowthStages", false, true),
        TOOLTIP_LATIN_NAME("option.tooltipLatinName", false, true),
        WORLDGEN_CORALREEFS("option.worldgenCoralReefs", false, true),
        WORLDGEN_END_RATE("option.worldgenEndRate", true, false, 1.0F, 100.0F, 1.0F),
        WORLDGEN_END_PASSES("option.worldgenEndPasses", true, false, 1.0F, 24.0F, 1.0F),
        WORLDGEN_NETHER_RATE("option.worldgenNetherRate", true, false, 1.0F, 100.0F, 1.0F),
        WORLDGEN_NETHER_PASSES("option.worldgenNetherPasses", true, false, 1.0F, 24.0F, 1.0F),
        WORLDGEN_OVERWORLD_RATE("option.worldgenOverworldRate", true, false, 1.0F, 100.0F, 1.0F),
        WORLDGEN_OVERWORLD_PASSES("option.worldgenOverworldPasses", true, false, 1.0F, 24.0F, 1.0F),
        WORLDGEN_SKY_RATE("option.worldgenSkyRate", true, false, 1.0F, 100.0F, 1.0F),
        WORLDGEN_SKY_PASSES("option.worldgenSkyPasses", true, false, 1.0F, 24.0F, 1.0F),
        WORLDGEN_SPAWNATFULLGROWTH("option.worldgenSpawnAtFullGrowth", false, true),
        WORLDGEN_VILLAGE_CROPS("option.worldgenVillageCrops", false, true),
        WORLDGEN_VILLAGE_PLANTS("option.worldgenVillagePlants", false, true),
        POWDER_CONDITIONER_RADIUS("option.powderConditionerRadius", true, false, 1.0F, 4.0F, 1.0F),
        POWDER_CONDITIONER_STRENGTH("option.powderConditionerStrength", true, false, 1.0F, 100.0F, 1.0F),
        POWDER_DEFOLIANT_RADIUS("option.powderDefoliantRadius", true, false, 1.0F, 4.0F, 1.0F),
        POWDER_DEFOLIANT_STRENGTH("option.powderDefoliantStrength", true, false, 1.0F, 100.0F, 1.0F),
        POWDER_FERTILIZER_RADIUS("option.powderFertilizerRadius", true, false, 1.0F, 4.0F, 1.0F),
        POWDER_FERTILIZER_STRENGTH("option.powderFertilizerStrength", true, false, 1.0F, 100.0F, 1.0F),
        SALVE_FIRERESIST_DURATION("option.salveFireResistDuration", true, false, 10.0F, 60.0F, 1.0F),
        SALVE_HEALTH_HEARTSHEALED("salveHealthHeartsHealed", true, false, 2.0F, 8.0F, 1.0F),
        SALVE_NIGHVISION_DURATION("salveNightVisionDuration", true, false, 10.0F, 60.0F, 1.0F),
        SALVE_STRENGTH_DURATION("salveStrengthDuration", true, false, 10.0F, 60.0F, 1.0F),
        SALVE_SWIFTNESS_DURATION("salveSwiftnessDuration", true, false, 10.0F, 60.0F, 1.0F),
        SALVE_WATERBREATHING_DURATION("salveWaterBreathingDuration", true, false, 10.0F, 60.0F, 1.0F),
        CONTENT_WALLBRACKETEXTRA("option.contentWallBracketExtra", false, true),
        CONTENT_CHEST_SEEDS("option.contentChestSeeds", false, true);

        private final boolean enumFloat;
        private final boolean enumBoolean;
        private final String enumString;
        private final float valueStep;
        private float valueMin;
        private float valueMax;

        public static PMPOptions getEnumOptions(int ordinal) {
            PMPOptions[] aoptions = values();
            int j = aoptions.length;

            for (int k = 0; k < j; ++k) {
                PMPOptions options = aoptions[k];
                if (options.returnEnumOrdinal() == ordinal) {
                    return options;
                }
            }

            return null;
        }

        private PMPOptions(String stringValue, boolean floatValue, boolean booleanValue, float valueMin, float valueMax,
            float valueStep, Object object) {
            this(stringValue, floatValue, booleanValue, valueMin, valueMax, valueStep);
        }

        private PMPOptions(String stringValue, boolean floatValue, boolean booleanValue) {
            this(stringValue, floatValue, booleanValue, 0.0F, 1.0F, 0.0F);
        }

        private PMPOptions(String stringValue, boolean floatValue, boolean booleanValue, float valueMin, float valueMax,
            float valueStep) {
            this.enumString = stringValue;
            this.enumFloat = floatValue;
            this.enumBoolean = booleanValue;
            this.valueMin = valueMin;
            this.valueMax = valueMax;
            this.valueStep = valueStep;
        }

        public boolean getEnumFloat() {
            return this.enumFloat;
        }

        public boolean getEnumBoolean() {
            return this.enumBoolean;
        }

        public int returnEnumOrdinal() {
            return this.ordinal();
        }

        public String getEnumString() {
            return this.enumString;
        }

        public float getValueMax() {
            return this.valueMax;
        }

        public void setValueMax(float valueMax) {
            this.valueMax = valueMax;
        }

        public float normalizeValue(float value) {
            return MathHelper.clamp_float(
                (this.snapToStepClamp(value) - this.valueMin) / (this.valueMax - this.valueMin),
                0.0F,
                1.0F);
        }

        public float denormalizeValue(float value) {
            return this.snapToStepClamp(
                this.valueMin + (this.valueMax - this.valueMin) * MathHelper.clamp_float(value, 0.0F, 1.0F));
        }

        public float snapToStepClamp(float value) {
            value = this.snapToStep(value);
            return MathHelper.clamp_float(value, this.valueMin, this.valueMax);
        }

        protected float snapToStep(float value) {
            if (this.valueStep > 0.0F) {
                value = this.valueStep * (float) Math.round(value / this.valueStep);
            }

            return value;
        }
    }
}
