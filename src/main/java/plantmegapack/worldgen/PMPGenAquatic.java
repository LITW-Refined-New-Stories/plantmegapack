package plantmegapack.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockPlant;
import plantmegapack.common.PMPDecoratorArea;
import plantmegapack.common.PMPPlantSoilType;
import plantmegapack.data.PMPDataPlantSpawnParams;

public abstract class PMPGenAquatic extends PMPGenBase {

    public static boolean generate(World world, Random random, PMPSpawnLocation locationData,
        PMPDataPlantSpawnParams params, PMPBlockPlant plant) {
        return true;
    }

    public static boolean spawnRandomSeaweedCluster(World world, Random random, PMPSpawnLocation locationData,
        PMPBlockPlant plant, int clusterSize, int clusterAmount) {
        int height = 0;

        for (int count = 0; count < clusterAmount; ++count) {
            int ix;
            if (random.nextInt(100) < 50) {
                ix = locationData.x + random.nextInt(clusterSize);
            } else {
                ix = locationData.x - random.nextInt(clusterSize);
            }

            int iz;
            if (random.nextInt(100) < 50) {
                iz = locationData.z + random.nextInt(clusterSize);
            } else {
                iz = locationData.z - random.nextInt(clusterSize);
            }

            int iy = world.getHeightValue(ix, iz) - 1;
            int bottom = iy;
            Block block = world.getBlock(ix, iy, iz);
            world.getBlock(ix, iy - 1, iz);
            if (block.getMaterial() == Material.water) {
                Block blockBelow;
                do {
                    --bottom;
                    blockBelow = world.getBlock(ix, bottom - 1, iz);
                } while (!PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.AQUATIC, blockBelow)
                    && bottom > 1);

                height = iy - bottom;
                height = random.nextInt(height);
                if (world.getBlock(ix, bottom, iz)
                    .getMaterial() == Material.water) {
                    for (int pos = bottom; pos <= bottom + height; ++pos) {
                        world.setBlock(ix, pos, iz, plant, 0, 3);
                    }
                }
            }
        }

        return true;
    }

    public static void spawnCoralReef(World world, Random random, PMPSpawnLocation locationData) {
        int clusterSize = 10;
        int variance = 4;

        for (int passes = 0; passes < 60; ++passes) {
            int x;
            if (random.nextInt(100) < 50) {
                x = locationData.x + random.nextInt(clusterSize);
            } else {
                x = locationData.x + random.nextInt(clusterSize) - random.nextInt(variance);
            }

            int z;
            if (random.nextInt(100) < 50) {
                z = locationData.z + random.nextInt(clusterSize);
            } else {
                z = locationData.z + random.nextInt(clusterSize) - random.nextInt(variance);
            }

            int y = world.getHeightValue(x, z) - 1;
            Block block = world.getBlock(x, y, z);
            if (block.getMaterial() == Material.water) {
                do {
                    --y;
                    block = world.getBlock(x, y, z);
                } while (!PlantMegaPack.soilBlocks
                    .canPlantOnThisBlock(PMPPlantSoilType.AQUATIC, world.getBlock(x, y - 1, z)) && y > 1);

                if (PMPGenBase.canReplaceBlockWithPlant(block, false)) {
                    world.setBlock(x, y, z, PlantMegaPack.blocks.getRandomCoralReefPlant(random), 0, 3);
                }
            }
        }

    }

    public static boolean spawnWaterPlantCluster(World world, Random random, PMPSpawnLocation locationData,
        PMPBlockPlant plant, PMPDataPlantSpawnParams params) {
        int x = locationData.x;
        int z = locationData.z;

        for (int passes = 0; passes < params.clusterAmount; ++passes) {
            if (random.nextInt(100) < 50) {
                locationData.x = x + random.nextInt(params.clusterSize);
            } else {
                locationData.x = x + random.nextInt(params.clusterSize) - random.nextInt(params.clusterSize);
            }

            if (random.nextInt(100) < 50) {
                locationData.z = z + random.nextInt(params.clusterSize);
            } else {
                locationData.z = z + random.nextInt(params.clusterSize) - random.nextInt(params.clusterSize);
            }

            locationData.y = world.getHeightValue(locationData.x, locationData.z);
            if (locationData.checkForWaterPlant(world)
                && (locationData.decoratorArea == PMPDecoratorArea.WATER_SUBMERGED
                    || locationData.decoratorArea == PMPDecoratorArea.WATER_IMMERSED)
                && plant.canPlaceBlockAt(world, locationData.x, locationData.y, locationData.z)) {
                world.setBlock(locationData.x, locationData.y, locationData.z, plant, 0, 3);
            }
        }

        return true;
    }
}
