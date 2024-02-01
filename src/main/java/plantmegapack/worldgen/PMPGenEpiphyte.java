package plantmegapack.worldgen;

import java.util.Random;

import net.minecraft.world.World;

import plantmegapack.block.PMPBlockPlant;
import plantmegapack.data.PMPDataPlantSpawnParams;

public abstract class PMPGenEpiphyte extends PMPGenBase {

    public static boolean generate(World world, Random random, PMPSpawnLocation locationData,
        PMPDataPlantSpawnParams params, PMPBlockPlant plant) {
        locationData.spawnDirection = PMPGenBase
            .isAdjacentToLogBlock(world, locationData.x, locationData.y, locationData.z, false);
        world.setBlock(
            locationData.x,
            locationData.y,
            locationData.z,
            plant,
            PMPGenBase.getOppositeDirection(locationData.spawnDirection),
            3);
        return true;
    }
}
