package plantmegapack.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import plantmegapack.block.PMPBlockPlant;
import plantmegapack.data.PMPDataPlantSpawnParams;

public abstract class PMPGenVine extends PMPGenBase {

    public static boolean generate(World world, Random random, PMPSpawnLocation locationData,
        PMPDataPlantSpawnParams params, PMPBlockPlant plant) {
        int posX = locationData.x;
        int posZ = locationData.z;
        spawnVine(world, random, locationData, getMetaDataPosition(locationData.spawnDirection), plant);

        for (int index = 0; index < params.clusterAmount; ++index) {
            locationData.x = posX + random.nextInt(params.clusterSize) - random.nextInt(params.clusterSize);
            locationData.z = posZ + random.nextInt(params.clusterSize) - random.nextInt(params.clusterSize);
            Block block = world.getBlock(locationData.x, locationData.y, locationData.z);
            if (block.getMaterial() == Material.leaves) {
                locationData.spawnDirection = PMPGenBase
                    .isAdjacentToAirOrVineBlock(world, locationData.x, locationData.y, locationData.z);
                if (locationData.spawnDirection > 0) {
                    locationData.moveSpawnLocationToDirection(locationData.spawnDirection, 1);
                    locationData.getBlockInfo(world);
                    if (world.isAirBlock(locationData.x, locationData.y, locationData.z)) {
                        spawnVine(world, random, locationData, getMetaDataPosition(locationData.spawnDirection), plant);
                    }
                }
            }
        }

        return true;
    }

    private static int getMetaDataPosition(int spawnDirection) {
        byte metaData = 0;
        if (spawnDirection == 2) {
            metaData = 1;
        } else if (spawnDirection == 3) {
            metaData = 4;
        } else if (spawnDirection == 4) {
            metaData = 8;
        } else if (spawnDirection == 5) {
            metaData = 2;
        }

        return metaData != 0 ? metaData : spawnDirection;
    }

    private static boolean spawnVine(World world, Random random, PMPSpawnLocation locationData, int metaData,
        PMPBlockPlant plant) {
        int height = 0;
        int y;
        Block block;

        do {
            ++height;
            y = locationData.y - height;
            block = world.getBlock(locationData.x, y, locationData.z);
        } while (y > 1 && block.getMaterial() == Material.air);

        if (y > 1) {
            for (int actualHeight = locationData.y - random.nextInt(Math.max(1, height)); actualHeight
                <= locationData.y; ++actualHeight) {
                world.setBlock(locationData.x, actualHeight, locationData.z, plant, metaData, 3);
            }
        }

        return true;
    }
}
