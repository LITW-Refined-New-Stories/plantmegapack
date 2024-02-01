package plantmegapack.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPDecorator;
import plantmegapack.common.PMPDecoratorArea;

public class PMPDataDecorator {

    private static final Logger logger = LogManager.getLogger();
    public final PMPDecorator decorator;
    public ArrayList<PMPDataPlantSpawnParams> plantsEpiphyte = new ArrayList();
    public ArrayList<PMPDataPlantSpawnParams> plantsPrimary = new ArrayList();
    public ArrayList<PMPDataPlantSpawnParams> plantsShade = new ArrayList();
    public ArrayList<PMPDataPlantSpawnParams> plantsTrees = new ArrayList();
    public ArrayList<PMPDataPlantSpawnParams> plantsVines = new ArrayList();
    public ArrayList<PMPDataPlantSpawnParams> plantsWaterEdge = new ArrayList();
    public ArrayList<PMPDataPlantSpawnParams> plantsWaterFloating = new ArrayList();
    public ArrayList<PMPDataPlantSpawnParams> plantsWaterImmersed = new ArrayList();
    public ArrayList<PMPDataPlantSpawnParams> plantsWaterSubmerged = new ArrayList();
    public int generationRate;

    public PMPDataDecorator(PMPDecorator decorator) {
        this.decorator = decorator;
        this.generationRate = 100;
        this.loadUserConfig();
    }

    public boolean loadUserConfig() {
        File optionsFile = new File(PlantMegaPack.configPathRoot + "/decorators/" + this.decorator.ID + ".cfg");
        if (!optionsFile.exists()) {
            this.resetDecoratorDefaults((PMPDecoratorArea) null);
            return this.saveUserConfig();
        } else {
            BufferedReader reader = null;

            try {
                reader = new BufferedReader(new FileReader(optionsFile));
                this.loadFileIntoDecorator(reader, true, (PMPDecoratorArea) null);
                reader.close();
                return true;
            } catch (FileNotFoundException var4) {} catch (IOException var5) {}

            logger.error(String.format("Unable to load user decorator config: %s", this.decorator.getLocalizedName()));
            return false;
        }
    }

    private int loadFileIntoDecorator(BufferedReader reader, boolean loadAll, PMPDecoratorArea area) {
        int plantCount = 0;
        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
                try {
                    if (line.length() > 0 && !line.startsWith("#")) {
                        String[] astring = line.split(":");
                        if (astring[0].matches("config.generationRate")) {
                            this.generationRate = Integer.parseInt(astring[1]);
                        }

                        PMPDataPlantSpawnParams params;
                        if (astring[0].matches("plant.epiphyte") && (loadAll || area == PMPDecoratorArea.EPIPHYTE)) {
                            params = new PMPDataPlantSpawnParams(
                                astring[1],
                                Integer.parseInt(astring[2]),
                                Boolean.getBoolean(astring[3]),
                                Integer.parseInt(astring[4]),
                                Integer.parseInt(astring[5]),
                                Integer.parseInt(astring[6]));
                            this.plantsEpiphyte.add(params);
                            ++plantCount;
                        }

                        if (astring[0].matches("plant.primary") && (loadAll || area == PMPDecoratorArea.PRIMARY)) {
                            params = new PMPDataPlantSpawnParams(
                                astring[1],
                                Integer.parseInt(astring[2]),
                                Boolean.getBoolean(astring[3]),
                                Integer.parseInt(astring[4]),
                                Integer.parseInt(astring[5]),
                                Integer.parseInt(astring[6]));
                            this.plantsPrimary.add(params);
                            ++plantCount;
                        }

                        if (astring[0].matches("plant.shade") && (loadAll || area == PMPDecoratorArea.SHADE)) {
                            params = new PMPDataPlantSpawnParams(
                                astring[1],
                                Integer.parseInt(astring[2]),
                                Boolean.getBoolean(astring[3]),
                                Integer.parseInt(astring[4]),
                                Integer.parseInt(astring[5]),
                                Integer.parseInt(astring[6]));
                            this.plantsShade.add(params);
                            ++plantCount;
                        }

                        if (astring[0].matches("plant.tree") && (loadAll || area == PMPDecoratorArea.TREE)) {
                            params = new PMPDataPlantSpawnParams(
                                astring[1],
                                Integer.parseInt(astring[2]),
                                Boolean.getBoolean(astring[3]),
                                Integer.parseInt(astring[4]),
                                Integer.parseInt(astring[5]),
                                Integer.parseInt(astring[6]));
                            this.plantsTrees.add(params);
                            ++plantCount;
                        }

                        if (astring[0].matches("plant.vine") && (loadAll || area == PMPDecoratorArea.VINE)) {
                            params = new PMPDataPlantSpawnParams(
                                astring[1],
                                Integer.parseInt(astring[2]),
                                Boolean.getBoolean(astring[3]),
                                Integer.parseInt(astring[4]),
                                Integer.parseInt(astring[5]),
                                Integer.parseInt(astring[6]));
                            this.plantsVines.add(params);
                            ++plantCount;
                        }

                        if (astring[0].matches("plant.waterEdge") && (loadAll || area == PMPDecoratorArea.WATER_EDGE)) {
                            params = new PMPDataPlantSpawnParams(
                                astring[1],
                                Integer.parseInt(astring[2]),
                                Boolean.getBoolean(astring[3]),
                                Integer.parseInt(astring[4]),
                                Integer.parseInt(astring[5]),
                                Integer.parseInt(astring[6]));
                            this.plantsWaterEdge.add(params);
                            ++plantCount;
                        }

                        if (astring[0].matches("plant.waterFloating")
                            && (loadAll || area == PMPDecoratorArea.WATER_FLOATING)) {
                            params = new PMPDataPlantSpawnParams(
                                astring[1],
                                Integer.parseInt(astring[2]),
                                Boolean.getBoolean(astring[3]),
                                Integer.parseInt(astring[4]),
                                Integer.parseInt(astring[5]),
                                Integer.parseInt(astring[6]));
                            this.plantsWaterFloating.add(params);
                            ++plantCount;
                        }

                        if (astring[0].matches("plant.waterImmersed")
                            && (loadAll || area == PMPDecoratorArea.WATER_IMMERSED)) {
                            params = new PMPDataPlantSpawnParams(
                                astring[1],
                                Integer.parseInt(astring[2]),
                                Boolean.getBoolean(astring[3]),
                                Integer.parseInt(astring[4]),
                                Integer.parseInt(astring[5]),
                                Integer.parseInt(astring[6]));
                            this.plantsWaterImmersed.add(params);
                            ++plantCount;
                        }

                        if (astring[0].matches("plant.waterSubmerged")
                            && (loadAll || area == PMPDecoratorArea.WATER_SUBMERGED)) {
                            params = new PMPDataPlantSpawnParams(
                                astring[1],
                                Integer.parseInt(astring[2]),
                                Boolean.getBoolean(astring[3]),
                                Integer.parseInt(astring[4]),
                                Integer.parseInt(astring[5]),
                                Integer.parseInt(astring[6]));
                            this.plantsWaterSubmerged.add(params);
                            ++plantCount;
                        }
                    }
                } catch (Exception var8) {
                    logger.error(
                        String.format("Unable to load user decorator config: %s", this.decorator.getLocalizedName()));
                    return 0;
                }
            }

            reader.close();
            return plantCount;
        } catch (Exception var9) {
            logger.error(String.format("Unable to load user decorator config: %s", this.decorator.getLocalizedName()));
            return 0;
        }
    }

    public boolean saveUserConfig() {
        if (this.decorator == PMPDecorator.FEATURES) {
            return false;
        } else {
            File optionsFile = new File(PlantMegaPack.configPathRoot + "/decorators/" + this.decorator.ID + ".cfg");
            int plantCount = 0;

            try {
                if (!optionsFile.exists()) {
                    optionsFile.createNewFile();
                    optionsFile.setWritable(true);
                }

                PrintWriter printwriter = new PrintWriter(new FileWriter(optionsFile));
                printwriter.println("config.generationRate:" + String.valueOf(this.generationRate));
                plantCount += this.savePlantList("epiphyte", this.plantsEpiphyte, printwriter);
                plantCount += this.savePlantList("primary", this.plantsPrimary, printwriter);
                plantCount += this.savePlantList("shade", this.plantsShade, printwriter);
                plantCount += this.savePlantList("tree", this.plantsTrees, printwriter);
                plantCount += this.savePlantList("vine", this.plantsVines, printwriter);
                plantCount += this.savePlantList("waterEdge", this.plantsWaterEdge, printwriter);
                plantCount += this.savePlantList("waterFloating", this.plantsWaterFloating, printwriter);
                plantCount += this.savePlantList("waterImmersed", this.plantsWaterImmersed, printwriter);
                plantCount += this.savePlantList("waterSubmerged", this.plantsWaterSubmerged, printwriter);
                printwriter.close();
            } catch (Exception var4) {
                logger.error(
                    String.format("Unable to save user decorator config: %s", this.decorator.getLocalizedName()));
                return false;
            }

            if (plantCount == 0) {}

            return true;
        }
    }

    public int resetDecoratorDefaults(PMPDecoratorArea area) {
        if (this.decorator == PMPDecorator.FEATURES) {
            return 0;
        } else {
            String filePath = new String(
                "defaults/" + this.decorator.decoratorSet.ID + "/" + this.decorator.ID + ".cfg");
            InputStream in = null;
            BufferedReader reader = null;
            int linesProcessed = 0;

            try {
                in = PlantMegaPack.instance.getClass()
                    .getResourceAsStream(filePath);
                if (in == null) {
                    return 0;
                } else {
                    reader = new BufferedReader(new InputStreamReader(in));
                    linesProcessed = this.loadFileIntoDecorator(reader, area == null, area);
                    reader.close();
                    in.close();
                    return linesProcessed;
                }
            } catch (IOException var7) {
                return 0;
            }
        }
    }

    public void resetDecoratorAll() {
        this.resetDecoratorArea(PMPDecoratorArea.EPIPHYTE);
        this.resetDecoratorArea(PMPDecoratorArea.PRIMARY);
        this.resetDecoratorArea(PMPDecoratorArea.SHADE);
        this.resetDecoratorArea(PMPDecoratorArea.TREE);
        this.resetDecoratorArea(PMPDecoratorArea.VINE);
        this.resetDecoratorArea(PMPDecoratorArea.WATER_EDGE);
        this.resetDecoratorArea(PMPDecoratorArea.WATER_FLOATING);
        this.resetDecoratorArea(PMPDecoratorArea.WATER_IMMERSED);
        this.resetDecoratorArea(PMPDecoratorArea.WATER_SUBMERGED);
    }

    public void resetDecoratorArea(PMPDecoratorArea area) {
        this.clearDecoratorAreaList(area);
        this.resetDecoratorDefaults(area);
    }

    public boolean copyDecorator(PMPDataDecorator sourceDecorator) {
        if (sourceDecorator == null) {
            return false;
        } else {
            this.generationRate = sourceDecorator.generationRate;
            this.clearDecoratorAreaList(PMPDecoratorArea.EPIPHYTE);
            int max = sourceDecorator.plantsEpiphyte.size();

            int index;
            for (index = 0; index < max; ++index) {
                this.plantsEpiphyte.add(sourceDecorator.plantsEpiphyte.get(index));
            }

            this.clearDecoratorAreaList(PMPDecoratorArea.PRIMARY);
            max = sourceDecorator.plantsPrimary.size();

            for (index = 0; index < max; ++index) {
                this.plantsPrimary.add(sourceDecorator.plantsPrimary.get(index));
            }

            this.clearDecoratorAreaList(PMPDecoratorArea.SHADE);
            max = sourceDecorator.plantsShade.size();

            for (index = 0; index < max; ++index) {
                this.plantsShade.add(sourceDecorator.plantsShade.get(index));
            }

            this.clearDecoratorAreaList(PMPDecoratorArea.TREE);
            max = sourceDecorator.plantsTrees.size();

            for (index = 0; index < max; ++index) {
                this.plantsTrees.add(sourceDecorator.plantsTrees.get(index));
            }

            this.clearDecoratorAreaList(PMPDecoratorArea.VINE);
            max = sourceDecorator.plantsVines.size();

            for (index = 0; index < max; ++index) {
                this.plantsVines.add(sourceDecorator.plantsVines.get(index));
            }

            this.clearDecoratorAreaList(PMPDecoratorArea.WATER_EDGE);
            max = sourceDecorator.plantsWaterEdge.size();

            for (index = 0; index < max; ++index) {
                this.plantsWaterEdge.add(sourceDecorator.plantsWaterEdge.get(index));
            }

            this.clearDecoratorAreaList(PMPDecoratorArea.WATER_FLOATING);
            max = sourceDecorator.plantsWaterFloating.size();

            for (index = 0; index < max; ++index) {
                this.plantsWaterFloating.add(sourceDecorator.plantsWaterFloating.get(index));
            }

            this.clearDecoratorAreaList(PMPDecoratorArea.WATER_IMMERSED);
            max = sourceDecorator.plantsWaterImmersed.size();

            for (index = 0; index < max; ++index) {
                this.plantsWaterImmersed.add(sourceDecorator.plantsWaterImmersed.get(index));
            }

            this.clearDecoratorAreaList(PMPDecoratorArea.WATER_SUBMERGED);
            max = sourceDecorator.plantsWaterSubmerged.size();

            for (index = 0; index < max; ++index) {
                this.plantsWaterSubmerged.add(sourceDecorator.plantsWaterSubmerged.get(index));
            }

            return true;
        }
    }

    public void clearAllDecoratorAreas() {
        this.plantsEpiphyte.clear();
        this.plantsPrimary.clear();
        this.plantsShade.clear();
        this.plantsTrees.clear();
        this.plantsVines.clear();
        this.plantsWaterEdge.clear();
        this.plantsWaterFloating.clear();
        this.plantsWaterImmersed.clear();
        this.plantsWaterSubmerged.clear();
    }

    private void clearDecoratorAreaList(PMPDecoratorArea area) {
        switch (area) {
            case EPIPHYTE:
                this.plantsEpiphyte.clear();
                break;
            case PRIMARY:
                this.plantsPrimary.clear();
                break;
            case SHADE:
                this.plantsShade.clear();
                break;
            case TREE:
                this.plantsTrees.clear();
                break;
            case VINE:
                this.plantsVines.clear();
                break;
            case WATER_EDGE:
                this.plantsWaterEdge.clear();
                break;
            case WATER_FLOATING:
                this.plantsWaterFloating.clear();
                break;
            case WATER_IMMERSED:
                this.plantsWaterImmersed.clear();
                break;
            case WATER_SUBMERGED:
                this.plantsWaterSubmerged.clear();
        }

    }

    private int savePlantList(String prefix, ArrayList<PMPDataPlantSpawnParams> array, PrintWriter printwriter) {
        if (prefix != null && prefix.length() != 0 && array.size() != 0 && printwriter != null) {
            for (int index = 0; index < array.size(); ++index) {
                printwriter.println(
                    "plant." + prefix
                        + ":"
                        + ((PMPDataPlantSpawnParams) array.get(index)).plantName
                        + ":"
                        + ((PMPDataPlantSpawnParams) array.get(index)).generationWeight
                        + ":"
                        + ((PMPDataPlantSpawnParams) array.get(index)).overwriteVanilla
                        + ":"
                        + ((PMPDataPlantSpawnParams) array.get(index)).elevationVariance
                        + ":"
                        + ((PMPDataPlantSpawnParams) array.get(index)).clusterAmount
                        + ":"
                        + ((PMPDataPlantSpawnParams) array.get(index)).clusterSize);
            }

            return array.size();
        } else {
            return 0;
        }
    }

    public ArrayList<PMPDataPlantSpawnParams> getPlantList(PMPDecoratorArea area) {
        if (area == null) {
            return null;
        } else {
            switch (area) {
                case EPIPHYTE:
                    return this.plantsEpiphyte;
                case PRIMARY:
                    return this.plantsPrimary;
                case SHADE:
                    return this.plantsShade;
                case TREE:
                    return this.plantsTrees;
                case VINE:
                    return this.plantsVines;
                case WATER_EDGE:
                    return this.plantsWaterEdge;
                case WATER_FLOATING:
                    return this.plantsWaterFloating;
                case WATER_IMMERSED:
                    return this.plantsWaterImmersed;
                case WATER_SUBMERGED:
                    return this.plantsWaterSubmerged;
                default:
                    return null;
            }
        }
    }

    public PMPDataPlantSpawnParams getPlantFromListByName(PMPDecoratorArea area, String plantName) {
        if (plantName != null && plantName.length() != 0) {
            ArrayList<PMPDataPlantSpawnParams> list = this.getPlantList(area);
            if (list == null) {
                return null;
            } else {
                for (int index = 0; index < list.size(); ++index) {
                    if (plantName.matches(((PMPDataPlantSpawnParams) list.get(index)).plantName)) {
                        return (PMPDataPlantSpawnParams) list.get(index);
                    }
                }

                return null;
            }
        } else {
            return null;
        }
    }

    public boolean addPlantToList(PMPDecoratorArea area, PMPDataPlantSpawnParams params) {
        if (area != null && params != null && this.getPlantFromListByName(area, params.plantName) == null) {
            switch (area) {
                case EPIPHYTE:
                    this.plantsEpiphyte.add(params);
                    break;
                case PRIMARY:
                    this.plantsPrimary.add(params);
                    break;
                case SHADE:
                    this.plantsShade.add(params);
                    break;
                case TREE:
                    this.plantsTrees.add(params);
                    break;
                case VINE:
                    this.plantsVines.add(params);
                    break;
                case WATER_EDGE:
                    this.plantsWaterEdge.add(params);
                    break;
                case WATER_FLOATING:
                    this.plantsWaterFloating.add(params);
                    break;
                case WATER_IMMERSED:
                    this.plantsWaterImmersed.add(params);
                    break;
                case WATER_SUBMERGED:
                    this.plantsWaterSubmerged.add(params);
                    break;
                default:
                    return false;
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean removePlantFromList(PMPDecoratorArea area, PMPDataPlantSpawnParams params) {
        if (area != null && params != null) {
            ArrayList<PMPDataPlantSpawnParams> list = this.getPlantList(area);
            if (list == null) {
                return false;
            } else {
                for (int index = 0; index < list.size(); ++index) {
                    if (params.plantName.matches(((PMPDataPlantSpawnParams) list.get(index)).plantName)) {
                        list.remove(index);
                        return true;
                    }
                }

                return false;
            }
        } else {
            return false;
        }
    }

    public PMPDataPlantSpawnParams getRandomPlant(Random random, PMPDecoratorArea area) {
        if (random != null && area != null) {
            switch (area) {
                case EPIPHYTE:
                    return this.getRandomPlantFromPlantList(random, this.plantsEpiphyte);
                case PRIMARY:
                    return this.getRandomPlantFromPlantList(random, this.plantsPrimary);
                case SHADE:
                    return this.getRandomPlantFromPlantList(random, this.plantsShade);
                case TREE:
                    return this.getRandomPlantFromPlantList(random, this.plantsTrees);
                case VINE:
                    return this.getRandomPlantFromPlantList(random, this.plantsVines);
                case WATER_EDGE:
                    return this.getRandomPlantFromPlantList(random, this.plantsWaterEdge);
                case WATER_FLOATING:
                    return this.getRandomPlantFromPlantList(random, this.plantsWaterFloating);
                case WATER_IMMERSED:
                    return this.getRandomPlantFromPlantList(random, this.plantsWaterImmersed);
                case WATER_SUBMERGED:
                    return this.getRandomPlantFromPlantList(random, this.plantsWaterSubmerged);
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    private PMPDataPlantSpawnParams getRandomPlantFromPlantList(Random random,
        ArrayList<PMPDataPlantSpawnParams> list) {
        if (list != null && !list.isEmpty()) {
            int totalWeight = 0;

            int index;
            for (index = 0; index < list.size(); ++index) {
                totalWeight += ((PMPDataPlantSpawnParams) list.get(index)).generationWeight;
            }

            int randomSelection = random.nextInt(totalWeight);
            int countWeight = 0;

            for (index = 0; index < list.size(); ++index) {
                countWeight += ((PMPDataPlantSpawnParams) list.get(index)).generationWeight;
                if (countWeight >= randomSelection) {
                    return (PMPDataPlantSpawnParams) list.get(index);
                }
            }

            return null;
        } else {
            return null;
        }
    }
}
