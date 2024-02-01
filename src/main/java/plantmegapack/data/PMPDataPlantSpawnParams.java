package plantmegapack.data;

import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockPlant;

public class PMPDataPlantSpawnParams {

    public String plantName;
    public int generationWeight;
    public boolean overwriteVanilla;
    public int elevationVariance;
    public int clusterAmount;
    public int clusterSize;

    public PMPDataPlantSpawnParams(String plantName) {
        this.plantName = plantName;
        this.resetDefaults();
    }

    public PMPDataPlantSpawnParams(String plantName, int generationWeight) {
        this.plantName = plantName;
        this.resetDefaults();
        this.generationWeight = generationWeight;
    }

    public PMPDataPlantSpawnParams(String plantName, int generationWeight, boolean overwriteVanilla,
        int elevationVariance, int clusterAmount, int clusterSize) {
        this.plantName = plantName;
        this.generationWeight = generationWeight;
        this.overwriteVanilla = overwriteVanilla;
        this.elevationVariance = elevationVariance;
        this.clusterAmount = clusterAmount;
        this.clusterSize = clusterSize;
    }

    private void resetDefaults() {
        this.generationWeight = 50;
        this.overwriteVanilla = true;
        this.elevationVariance = 3;
        PMPBlockPlant plant = PlantMegaPack.blocks.getPlantBlock(this.plantName);
        if (plant != null) {
            this.clusterAmount = plant.plantData.attributes.clusterAmount;
            this.clusterSize = plant.plantData.attributes.clusterSize;
        } else {
            this.clusterAmount = 3;
            this.clusterSize = 5;
        }

    }
}
