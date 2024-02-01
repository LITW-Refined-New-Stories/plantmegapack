package plantmegapack.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import cpw.mods.fml.common.registry.LanguageRegistry;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPPlant;

public class PMPDataPlant {

    public PMPPlant attributes;
    public int dropAlways;
    public int dropRandomAmount;
    public int dropRandomChance;
    public int growthRate;

    public PMPDataPlant(PMPPlant attrib) {
        this.attributes = attrib;
        this.resetPlantDataDefaults();
    }

    public String getLatinName() {
        return LanguageRegistry.instance()
            .getStringLocalization("tile." + this.attributes.ID + ".snam");
    }

    public void resetPlantDataDefaults() {
        this.dropAlways = this.attributes.dropAlways;
        this.dropRandomAmount = this.attributes.dropRandomAmount;
        this.dropRandomChance = this.attributes.dropRandomChance;
        this.growthRate = this.attributes.growthRate;
    }

    public void loadUserConfig() {
        File optionsFile = new File(PlantMegaPack.configPathRoot + "/plants/" + this.attributes.ID + ".cfg");
        if (!optionsFile.exists()) {
            this.saveUserConfig();
        } else {
            try {
                BufferedReader bufferedreader = new BufferedReader(new FileReader(optionsFile));
                String line = "";

                while ((line = bufferedreader.readLine()) != null) {
                    try {
                        if (line.length() > 0 && !line.startsWith("#")) {
                            String[] astring = line.split(":");
                            if (astring[0].equals("dropAlways")) {
                                this.dropAlways = Integer.parseInt(astring[1]);
                            }

                            if (astring[0].equals("dropRandomAmount")) {
                                this.dropRandomAmount = Integer.parseInt(astring[1]);
                            }

                            if (astring[0].equals("dropRandomChance")) {
                                this.dropRandomChance = Integer.parseInt(astring[1]);
                            }

                            if (astring[0].equals("growthRate")) {
                                this.growthRate = Integer.parseInt(astring[1]);
                            }
                        }
                    } catch (Exception var5) {}
                }

                bufferedreader.close();
            } catch (Exception var6) {}

        }
    }

    public void saveUserConfig() {
        File optionsFile = new File(PlantMegaPack.configPathRoot + "/plants/" + this.attributes.ID + ".cfg");

        try {
            if (!optionsFile.exists()) {
                optionsFile.createNewFile();
                optionsFile.setWritable(true);
            }

            PrintWriter printwriter = new PrintWriter(new FileWriter(optionsFile));
            printwriter.println("dropAlways:" + String.format("%d", this.dropAlways));
            printwriter.println("dropRandomAmount:" + String.format("%d", this.dropRandomAmount));
            printwriter.println("dropRandomChance:" + String.format("%d", this.dropRandomChance));
            printwriter.println("growthRate:" + String.format("%d", this.growthRate));
            printwriter.close();
        } catch (Exception var3) {}

    }
}
