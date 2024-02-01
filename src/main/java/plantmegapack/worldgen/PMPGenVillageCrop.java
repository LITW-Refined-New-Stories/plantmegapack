package plantmegapack.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockPlant;
import plantmegapack.common.PMPPlantCategory;

public abstract class PMPGenVillageCrop extends PMPGenBase {

    public static boolean generate(World world, Random random, PMPSpawnLocation locationData) {
        if (!PlantMegaPack.settingsGeneral.worldgenVillageCrops) {
            return false;
        } else {
            spawnCropRow(world, random, locationData);
            return true;
        }
    }

    private static void spawnCropRow(World world, Random random, PMPSpawnLocation locationData) {
        ArrayList<PMPBlockPlant> cropsList = PlantMegaPack.blocks.getPlantBlockList(PMPPlantCategory.CROP_LAND);
        if (cropsList != null && cropsList.size() != 0) {
            int index = random.nextInt(cropsList.size());
            PMPBlockPlant blockCrop = (PMPBlockPlant) cropsList.get(index);
            if (blockCrop != null) {
                world.setBlock(locationData.x, locationData.y, locationData.z, blockCrop, 0, 3);
                int direction = getRowDirection(world, locationData);
                if (direction != 0) {
                    int x;
                    int z;
                    int maxScan;
                    if (direction == 1) {
                        x = locationData.x;
                        z = locationData.z - 1;

                        for (maxScan = 0; world.getBlock(x, locationData.y, z) == Blocks.wheat
                            && maxScan < 10; ++maxScan) {
                            world.setBlock(x, locationData.y, z, blockCrop, 0, 3);
                            --z;
                        }

                        z = locationData.z + 1;

                        for (maxScan = 0; world.getBlock(x, locationData.y, z) == Blocks.wheat
                            && maxScan < 10; ++maxScan) {
                            world.setBlock(x, locationData.y, z, blockCrop, 0, 3);
                            ++z;
                        }
                    } else if (direction == 2) {
                        x = locationData.x - 1;
                        z = locationData.z;

                        for (maxScan = 0; world.getBlock(x, locationData.y, z) == Blocks.wheat
                            && maxScan < 10; ++maxScan) {
                            world.setBlock(x, locationData.y, z, blockCrop, 0, 3);
                            --x;
                        }

                        x = locationData.x + 1;
                        z = locationData.z;

                        for (maxScan = 0; world.getBlock(x, locationData.y, z) == Blocks.wheat
                            && maxScan < 10; ++maxScan) {
                            world.setBlock(x, locationData.y, z, blockCrop, 0, 3);
                            ++x;
                        }
                    }

                }
            }
        }
    }

    private static int getRowDirection(World world, PMPSpawnLocation locationData) {
        int north = 0;
        int south = 0;
        int west = 0;
        int east = 0;
        if (world.getBlock(locationData.x, locationData.y, locationData.z - 1) == Blocks.wheat) {
            ++north;
            if (world.getBlock(locationData.x, locationData.y, locationData.z - 2) == Blocks.wheat) {
                ++north;
            }
        }

        if (world.getBlock(locationData.x, locationData.y, locationData.z + 1) == Blocks.wheat) {
            ++south;
            if (world.getBlock(locationData.x, locationData.y, locationData.z + 2) == Blocks.wheat) {
                ++south;
            }
        }

        if (world.getBlock(locationData.x - 1, locationData.y, locationData.z) == Blocks.wheat) {
            ++west;
            if (world.getBlock(locationData.x - 2, locationData.y, locationData.z) == Blocks.wheat) {
                ++west;
            }
        }

        if (world.getBlock(locationData.x + 1, locationData.y, locationData.z) == Blocks.wheat) {
            ++east;
            if (world.getBlock(locationData.x + 2, locationData.y, locationData.z) == Blocks.wheat) {
                ++east;
            }
        }

        if (north <= 1 && south <= 1) {
            return west <= 1 && east <= 1 ? 0 : 2;
        } else {
            return 1;
        }
    }
}
