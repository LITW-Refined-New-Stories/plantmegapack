package plantmegapack.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.util.ForgeDirection;

import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockHangingPlant;
import plantmegapack.block.PMPBlockPlant;
import plantmegapack.block.PMPBlockWallBracket;

public abstract class PMPGenVillagePlant extends PMPGenBase {

    public static boolean generate(World world, Random random, PMPSpawnLocation locationData) {
        if (!PlantMegaPack.settingsGeneral.worldgenVillagePlants) {
            return false;
        } else {
            if (locationData.spawnDirection == 2) {
                locationData.spawnDirection = 3;
            } else if (locationData.spawnDirection == 3) {
                locationData.spawnDirection = 2;
            } else if (locationData.spawnDirection == 4) {
                locationData.spawnDirection = 5;
            } else {
                if (locationData.spawnDirection != 5) {
                    return false;
                }

                locationData.spawnDirection = 4;
            }

            if (locationData.spawnDirection != 2 && locationData.spawnDirection != 3) {
                scanNorthSouth(world, random, locationData);
            } else {
                scanWestEast(world, random, locationData);
            }

            return true;
        }
    }

    private static void scanWestEast(World world, Random random, PMPSpawnLocation locationData) {
        int ix = locationData.x;
        int iy = locationData.y;
        int iz = locationData.z;
        boolean cornerDetected = false;

        Block block;
        int maxScan;
        for (maxScan = 0; !cornerDetected && maxScan < 16; ++maxScan) {
            if (locationData.spawnDirection == 2) {
                block = world.getBlock(ix - 1, iy, iz + 1);
                if (block.getMaterial() == Material.air || block.getClass() == PMPBlockWallBracket.class) {
                    cornerDetected = true;
                    if (!spawnHangingPlant(world, random, ix, iy, iz, locationData.spawnDirection)) {
                        spawnGroundPlant(world, random, locationData);
                    }
                }
            }

            if (locationData.spawnDirection == 3) {
                block = world.getBlock(ix - 1, iy, iz - 1);
                if (block.getMaterial() == Material.air || block.getClass() == PMPBlockWallBracket.class) {
                    cornerDetected = true;
                    if (!spawnHangingPlant(world, random, ix, iy, iz, locationData.spawnDirection)) {
                        spawnGroundPlant(world, random, locationData);
                    }
                }
            }

            --ix;
        }

        ix = locationData.x;
        iy = locationData.y;
        iz = locationData.z;
        cornerDetected = false;

        for (maxScan = 0; !cornerDetected && maxScan < 16; ++maxScan) {
            if (locationData.spawnDirection == 2) {
                block = world.getBlock(ix + 1, iy, iz + 1);
                if (block.getMaterial() == Material.air || block.getClass() == PMPBlockWallBracket.class) {
                    cornerDetected = true;
                    if (!spawnHangingPlant(world, random, ix, iy, iz, locationData.spawnDirection)) {
                        spawnGroundPlant(world, random, locationData);
                    }
                }
            }

            if (locationData.spawnDirection == 3) {
                block = world.getBlock(ix + 1, iy, iz - 1);
                if (block.getMaterial() == Material.air || block.getClass() == PMPBlockWallBracket.class) {
                    cornerDetected = true;
                    if (!spawnHangingPlant(world, random, ix, iy, iz, locationData.spawnDirection)) {
                        spawnGroundPlant(world, random, locationData);
                    }
                }
            }

            ++ix;
        }

    }

    private static void scanNorthSouth(World world, Random random, PMPSpawnLocation locationData) {
        int ix = locationData.x;
        int iy = locationData.y;
        int iz = locationData.z;
        boolean cornerDetected = false;

        Block block;
        int maxScan;
        for (maxScan = 0; !cornerDetected && maxScan < 16; ++maxScan) {
            if (locationData.spawnDirection == 4) {
                block = world.getBlock(ix + 1, iy, iz - 1);
                if (block.getMaterial() == Material.air || block.getClass() == PMPBlockWallBracket.class) {
                    cornerDetected = true;
                    if (!spawnHangingPlant(world, random, ix, iy, iz, locationData.spawnDirection)) {
                        spawnGroundPlant(world, random, locationData);
                    }
                }
            }

            if (locationData.spawnDirection == 5) {
                block = world.getBlock(ix - 1, iy, iz - 1);
                if (block.getMaterial() == Material.air || block.getClass() == PMPBlockWallBracket.class) {
                    cornerDetected = true;
                    if (!spawnHangingPlant(world, random, ix, iy, iz, locationData.spawnDirection)) {
                        spawnGroundPlant(world, random, locationData);
                    }
                }
            }

            --iz;
        }

        ix = locationData.x;
        iy = locationData.y;
        iz = locationData.z;
        cornerDetected = false;

        for (maxScan = 0; !cornerDetected && maxScan < 16; ++maxScan) {
            if (locationData.spawnDirection == 4) {
                block = world.getBlock(ix + 1, iy, iz + 1);
                if (block.getMaterial() == Material.air || block.getClass() == PMPBlockWallBracket.class) {
                    cornerDetected = true;
                    if (!spawnHangingPlant(world, random, ix, iy, iz, locationData.spawnDirection)) {
                        spawnGroundPlant(world, random, locationData);
                    }
                }
            }

            if (locationData.spawnDirection == 5) {
                block = world.getBlock(ix - 1, iy, iz + 1);
                if (block.getMaterial() == Material.air || block.getClass() == PMPBlockWallBracket.class) {
                    cornerDetected = true;
                    if (!spawnHangingPlant(world, random, ix, iy, iz, locationData.spawnDirection)) {
                        spawnGroundPlant(world, random, locationData);
                    }
                }
            }

            ++iz;
        }

    }

    private static boolean spawnHangingPlant(World world, Random random, int x, int y, int z, int direction) {
        Block block = world.getBlock(x, y, z);
        if (block.getMaterial() != Material.air) {
            return false;
        } else {
            block = world.getBlock(x, y - 1, z);
            if (block.getMaterial() != Material.air) {
                return false;
            } else {
                block = world.getBlock(x, y - 2, z);
                if (block.getMaterial() != Material.air) {
                    return false;
                } else {
                    int distribution = random.nextInt(100);
                    world.setBlock(
                        x,
                        y,
                        z,
                        PlantMegaPack.blocks.getWallBracketBlock("wallBracketWoodOak"),
                        direction,
                        3);
                    if (distribution < 5) {
                        world.setBlock(x, y - 1, z, getRandomHangingBamboo(random), 0, 3);
                    } else if (distribution < 15) {
                        world.setBlock(x, y - 1, z, getRandomHangingPlant(random), 0, 3);
                    } else if (distribution < 35) {
                        world.setBlock(x, y - 1, z, getRandomHangingFoliage(random), 0, 3);
                    } else {
                        world.setBlock(x, y - 1, z, getRandomHangingFlower(random), 0, 3);
                    }

                    return true;
                }
            }
        }
    }

    private static void spawnGroundPlant(World world, Random random, PMPSpawnLocation locationData) {
        int ix = locationData.x;
        int iy = locationData.y;
        int iz = locationData.z;
        boolean soilBlockFound = false;

        Block block;
        for (int maxScan = 0; !soilBlockFound && maxScan < 10; ++maxScan) {
            block = world.getBlock(ix, iy - 1, iz);
            if (block.getMaterial() != Material.air && block.getMaterial() != Material.plants) {
                soilBlockFound = true;
            } else {
                --iy;
            }
        }

        block = world.getBlock(ix, iy, iz);
        if (block.getClass() != PMPBlockHangingPlant.class && block
            .canSustainPlant(world, ix, iy, iz, ForgeDirection.UP, PlantMegaPack.blocks.getPlantBlock("flowerRose"))) {}

        spawnGardenPlant(world, random, ix, iy, iz, locationData.biome);
    }

    private static void spawnGardenPlant(World world, Random random, int x, int y, int z, BiomeGenBase biome) {
        PMPBlockPlant gardenPlant = PlantMegaPack.blocks.getPlantBlock("flowerRose");
        Block soilBlock = Blocks.grass;
        int metaData = random.nextInt(gardenPlant.getSubBlocksCount());
        if (BiomeDictionary.isBiomeOfType(biome, Type.FOREST)) {
            gardenPlant = PlantMegaPack.blocks.getPlantBlock("flowerTulip");
            metaData = random.nextInt(gardenPlant.getSubBlocksCount());
        } else if (BiomeDictionary.isBiomeOfType(biome, Type.SANDY)) {
            soilBlock = Blocks.sand;
            gardenPlant = PlantMegaPack.blocks.getPlantBlock("desertKangarooPaw");
            metaData = 0;
        }

        world.setBlock(x, y - 1, z, (Block) soilBlock, metaData, 3);
        world.setBlock(x, y, z, gardenPlant, metaData, 3);
    }

    private static PMPBlockHangingPlant getRandomHangingBamboo(Random random) {
        String name = "";
        switch (random.nextInt(8)) {
            case 0:
                name = "hangingBambooAsper";
                break;
            case 1:
                name = "hangingBambooFargesiaRobusta";
                break;
            case 2:
                name = "hangingBambooGiantTimber";
                break;
            case 3:
                name = "hangingBambooGolden";
                break;
            case 4:
                name = "hangingBambooMoso";
                break;
            case 5:
                name = "hangingBambooShortTassled";
                break;
            case 6:
                name = "hangingBambooTimorBlack";
                break;
            case 7:
                name = "hangingBambooTropicalBlue";
                break;
            case 8:
                name = "hangingBambooWetForest";
        }

        return PlantMegaPack.blocks.getHangingPlantBlock(name);
    }

    private static PMPBlockHangingPlant getRandomHangingFlower(Random random) {
        String name = "";
        switch (random.nextInt(13)) {
            case 0:
                name = "hangingFlowersMIX";
                break;
            case 1:
                name = "hangingFlowersRED";
                break;
            case 2:
                name = "hangingFlowersCOR";
                break;
            case 3:
                name = "hangingFlowersORA";
                break;
            case 4:
                name = "hangingFlowersYEL";
                break;
            case 5:
                name = "hangingFlowersGRN";
                break;
            case 6:
                name = "hangingFlowersCYA";
                break;
            case 7:
                name = "hangingFlowersBLU";
                break;
            case 8:
                name = "hangingFlowersROY";
                break;
            case 9:
                name = "hangingFlowersPUR";
                break;
            case 10:
                name = "hangingFlowersMAG";
                break;
            case 11:
                name = "hangingFlowersPNK";
                break;
            case 12:
                name = "hangingFlowersWHT";
        }

        return PlantMegaPack.blocks.getHangingPlantBlock(name);
    }

    private static PMPBlockHangingPlant getRandomHangingFoliage(Random random) {
        String name = "";
        switch (random.nextInt(5)) {
            case 0:
                name = "hangingFoliageSMGRN";
                break;
            case 1:
                name = "hangingFoliageMDGRN";
                break;
            case 2:
                name = "hangingFoliageLGGRN";
                break;
            case 3:
                name = "hangingFoliageLGYEL";
                break;
            case 4:
                name = "hangingFoliageLGRED";
        }

        return PlantMegaPack.blocks.getHangingPlantBlock(name);
    }

    private static PMPBlockHangingPlant getRandomHangingPlant(Random random) {
        String name = "";
        switch (random.nextInt(9)) {
            case 0:
                name = "hangingBlueWheatgrass";
                break;
            case 1:
                name = "hangingBrittlebush";
                break;
            case 2:
                name = "hangingKrisPlant";
                break;
            case 3:
                name = "hangingLavender";
                break;
            case 4:
                name = "hangingPricklyPear";
                break;
            case 5:
                name = "hangingScalyTree";
                break;
            case 6:
                name = "hangingPhilodendron";
                break;
            case 7:
                name = "hangingWildfire";
                break;
            case 8:
                name = "hangingWolfWillow";
        }

        return PlantMegaPack.blocks.getHangingPlantBlock(name);
    }
}
