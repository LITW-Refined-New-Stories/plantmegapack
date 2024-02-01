package plantmegapack.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockBambooStairs;
import plantmegapack.block.PMPBlockPlant;
import plantmegapack.common.PMPDecorator;
import plantmegapack.common.PMPDecoratorArea;
import plantmegapack.common.PMPDecoratorType;
import plantmegapack.common.PMPPlantCategory;
import plantmegapack.common.PMPPlantGrowthType;
import plantmegapack.common.PMPPlantRenderType;
import plantmegapack.common.PMPPlantSoilType;
import plantmegapack.data.PMPDataPlantSpawnParams;

public abstract class PMPGenBase {

    public static boolean spawnPlantCluster(World world, Random random, PMPSpawnLocation locationData,
        PMPDataPlantSpawnParams params) {
        if (world != null && random != null && locationData != null && params != null) {
            if (!PlantMegaPack.settingsGeneral.contentAquatic
                && locationData.decorator.decoratorType == PMPDecoratorType.WATER) {
                return false;
            } else {
                int posX = locationData.x;
                int posY = locationData.y;
                int posZ = locationData.z;
                PMPBlockPlant plant = PlantMegaPack.blocks.getPlantBlock(params.plantName);
                if (plant == null) {
                    return false;
                } else if (locationData.decoratorArea == PMPDecoratorArea.EPIPHYTE) {
                    return PMPGenEpiphyte.generate(world, random, locationData, params, plant);
                } else if (locationData.decoratorArea == PMPDecoratorArea.VINE) {
                    return PMPGenVine.generate(world, random, locationData, params, plant);
                } else if (plant.plantData.attributes.growthType == PMPPlantGrowthType.SEAWEED) {
                    return PMPGenAquatic.spawnRandomSeaweedCluster(
                        world,
                        random,
                        locationData,
                        plant,
                        params.clusterSize,
                        params.clusterAmount);
                } else if (locationData.decoratorArea != PMPDecoratorArea.WATER_SUBMERGED
                    && locationData.decoratorArea != PMPDecoratorArea.WATER_IMMERSED) {
                        for (int index = 0; index < params.clusterAmount; ++index) {
                            locationData.x = posX + random.nextInt(params.clusterSize)
                                - random.nextInt(params.clusterSize);
                            locationData.y = posY;
                            locationData.z = posZ + random.nextInt(params.clusterSize)
                                - random.nextInt(params.clusterSize);
                            locationData.getBlockInfo(world);
                            boolean spawnPlant = false;
                            if (locationData.blockBelow.getClass() == PMPBlockPlant.class
                                || locationData.blockSpawn.getClass() == PMPBlockPlant.class
                                || locationData.blockAbove.getClass() == PMPBlockPlant.class) {
                                break;
                            }

                            if (plant.canPlaceBlockAt(world, locationData.x, locationData.y, locationData.z)) {
                                spawnPlant = true;
                            } else {
                                int count;
                                if (canReplaceBlockWithPlant(locationData.blockBelow, true)) {
                                    count = 0;

                                    while (count < params.elevationVariance
                                        && canReplaceBlockWithPlant(locationData.blockBelow, true)) {
                                        --locationData.y;
                                        locationData.getBlockInfo(world);
                                        ++count;
                                        if (plant
                                            .canPlaceBlockAt(world, locationData.x, locationData.y, locationData.z)) {
                                            spawnPlant = true;
                                            break;
                                        }
                                    }
                                } else if (!canReplaceBlockWithPlant(locationData.blockSpawn, true)) {
                                    count = 0;

                                    while (count < params.elevationVariance
                                        && !canReplaceBlockWithPlant(locationData.blockAbove, true)) {
                                        ++locationData.y;
                                        locationData.getBlockInfo(world);
                                        ++count;
                                        if (plant
                                            .canPlaceBlockAt(world, locationData.x, locationData.y, locationData.z)) {
                                            spawnPlant = true;
                                            break;
                                        }
                                    }
                                }
                            }

                            if (spawnPlant) {
                                spawnPlant(world, random, locationData, params, plant);
                            }
                        }

                        return true;
                    } else {
                        return PMPGenAquatic.spawnWaterPlantCluster(world, random, locationData, plant, params);
                    }
            }
        } else {
            return false;
        }
    }

    public static boolean spawnPlant(World world, Random random, PMPSpawnLocation locationData,
        PMPDataPlantSpawnParams params, PMPBlockPlant plant) {
        if (locationData != null && params != null
            && plant != null
            && plant.canPlaceBlockAt(world, locationData.x, locationData.y, locationData.z)) {
            int metaData = 0;
            Block block = world.getBlock(locationData.x, locationData.y, locationData.z);
            if (block == Blocks.wheat) {
                return PMPGenVillageCrop.generate(world, random, locationData);
            } else if (plant.plantData.attributes.category == PMPPlantCategory.BAMBOO) {
                return spawnRandomBambooPlantCluster(
                    world,
                    random,
                    locationData.x,
                    locationData.y,
                    locationData.z,
                    plant,
                    params.clusterSize,
                    params.clusterAmount,
                    params.overwriteVanilla);
            } else {
                if (plant.plantData.attributes.renderType != PMPPlantRenderType.FLOATING_FLOWER
                    && plant.plantData.attributes.category != PMPPlantCategory.FLOWER_MULTI) {
                    if (plant.plantData.attributes.renderType == PMPPlantRenderType.FLAT) {
                        metaData = random.nextInt(8);
                        world.setBlock(locationData.x, locationData.y, locationData.z, plant, metaData, 3);
                        return true;
                    } else {
                        metaData = (PlantMegaPack.settingsGeneral.worldgenSpawnAtFullGrowth
                            ? plant.plantData.attributes.growthStages - 1
                            : random.nextInt(plant.plantData.attributes.growthStages)) - 1;
                        if (plant.plantData.attributes.growthType == PMPPlantGrowthType.DOUBLE) {
                            if (metaData > 1 && world.getBlock(locationData.x, locationData.y + 1, locationData.z)
                                .getMaterial() != Material.air) {
                                metaData = 0;
                            }

                            if (!plant.isImmersedPlant()) {
                                switch (metaData) {
                                    case 0:
                                        world.setBlock(locationData.x, locationData.y, locationData.z, plant, 0, 3);
                                        break;
                                    case 1:
                                        world.setBlock(locationData.x, locationData.y, locationData.z, plant, 1, 3);
                                        break;
                                    case 2:
                                        world.setBlock(locationData.x, locationData.y, locationData.z, plant, 2, 3);
                                        world.setBlock(locationData.x, locationData.y + 1, locationData.z, plant, 3, 3);
                                        break;
                                    case 3:
                                        world.setBlock(locationData.x, locationData.y, locationData.z, plant, 4, 3);
                                        world.setBlock(locationData.x, locationData.y + 1, locationData.z, plant, 5, 3);
                                        break;
                                    case 4:
                                        world.setBlock(locationData.x, locationData.y, locationData.z, plant, 6, 3);
                                        world.setBlock(locationData.x, locationData.y + 1, locationData.z, plant, 7, 3);
                                        break;
                                    default:
                                        return false;
                                }
                            }
                        } else {
                            if (metaData < 0 || metaData > 15) {
                                metaData = 0;
                            }

                            world.setBlock(locationData.x, locationData.y, locationData.z, plant, metaData, 3);
                        }

                        return true;
                    }
                } else {
                    metaData = random.nextInt(plant.getSubBlocksCount());
                    world.setBlock(locationData.x, locationData.y, locationData.z, plant, metaData, 3);
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public static int isStairBlock(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        if (block == null) {
            return 0;
        } else {
            if (block == Blocks.acacia_stairs || block == Blocks.birch_stairs
                || block == Blocks.dark_oak_stairs
                || block == Blocks.jungle_stairs
                || block == Blocks.oak_stairs
                || block == Blocks.spruce_stairs
                || block == Blocks.sandstone_stairs
                || block == Blocks.stone_stairs
                || block.getClass() == BlockStairs.class
                || block.getClass() == PMPBlockBambooStairs.class) {
                switch (world.getBlockMetadata(x, y, z)) {
                    case 0:
                        return 5;
                    case 1:
                        return 4;
                    case 2:
                        return 3;
                    case 3:
                        return 2;
                }
            }

            return 0;
        }
    }

    public static boolean isAdjacentToBlock(World world, int x, int y, int z, Block block) {
        return world.getBlock(x - 1, y, z) == block || world.getBlock(x - 1, y, z - 1) == block
            || world.getBlock(x - 1, y, z + 1) == block
            || world.getBlock(x, y, z - 1) == block
            || world.getBlock(x, y, z + 1) == block
            || world.getBlock(x + 1, y, z) == block
            || world.getBlock(x + 1, y, z - 1) == block
            || world.getBlock(x + 1, y, z + 1) == block;
    }

    public static boolean isAdjacentToBlockFull(World world, int x, int y, int z, Block block) {
        return world.getBlock(x - 2, y, z - 2) == block || world.getBlock(x - 1, y, z - 2) == block
            || world.getBlock(x, y, z - 2) == block
            || world.getBlock(x + 1, y, z - 2) == block
            || world.getBlock(x + 2, y, z - 2) == block
            || world.getBlock(x - 2, y, z - 1) == block
            || world.getBlock(x - 2, y, z) == block
            || world.getBlock(x - 2, y, z + 1) == block
            || world.getBlock(x - 2, y, z + 2) == block
            || world.getBlock(x - 1, y, z + 2) == block
            || world.getBlock(x, y, z + 2) == block
            || world.getBlock(x + 1, y, z + 2) == block
            || world.getBlock(x + 2, y, z + 2) == block
            || world.getBlock(x + 2, y, z + 2) == block
            || world.getBlock(x + 2, y, z + 1) == block
            || world.getBlock(x + 2, y, z) == block
            || world.getBlock(x + 2, y, z - 1) == block;
    }

    public static int isAdjacentToBlockMaterial(World world, int x, int y, int z, Material material) {
        Block block = world.getBlock(x, y, z + 1);
        if (block.getMaterial() == material) {
            return 3;
        } else {
            block = world.getBlock(x + 1, y, z + 1);
            if (block.getMaterial() == material) {
                return 9;
            } else {
                block = world.getBlock(x + 1, y, z);
                if (block.getMaterial() == material) {
                    return 5;
                } else {
                    block = world.getBlock(x + 1, y, z - 1);
                    if (block.getMaterial() == material) {
                        return 7;
                    } else {
                        block = world.getBlock(x, y, z - 1);
                        if (block.getMaterial() == material) {
                            return 2;
                        } else {
                            block = world.getBlock(x - 1, y, z - 1);
                            if (block.getMaterial() == material) {
                                return 6;
                            } else {
                                block = world.getBlock(x - 1, y, z);
                                if (block.getMaterial() == material) {
                                    return 4;
                                } else {
                                    block = world.getBlock(x - 1, y, z + 1);
                                    return block.getMaterial() == material ? 8 : 0;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static int isAdjacentToLogBlock(World world, int x, int y, int z, boolean checkDiagonals) {
        if (isLogBlock(world, x, y, z + 1)) {
            return 3;
        } else if (isLogBlock(world, x + 1, y, z)) {
            return 5;
        } else if (isLogBlock(world, x, y, z - 1)) {
            return 2;
        } else if (isLogBlock(world, x - 1, y, z)) {
            return 4;
        } else {
            if (checkDiagonals) {
                if (isLogBlock(world, x + 1, y, z + 1)) {
                    return 9;
                }

                if (isLogBlock(world, x + 1, y, z - 1)) {
                    return 7;
                }

                if (isLogBlock(world, x - 1, y, z - 1)) {
                    return 6;
                }

                if (isLogBlock(world, x - 1, y, z + 1)) {
                    return 8;
                }
            }

            return 0;
        }
    }

    public static boolean isLogBlock(Block block) {
        return PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.WOOD, block);
    }

    public static boolean isLogBlock(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        return isLogBlock(block);
    }

    public static boolean isSurroundedByBlock(World world, int x, int y, int z, Block block) {
        return world.getBlock(x - 1, y, z) == block && world.getBlock(x - 1, y, z - 1) == block
            && world.getBlock(x - 1, y, z + 1) == block
            && world.getBlock(x, y, z - 1) == block
            && world.getBlock(x, y, z + 1) == block
            && world.getBlock(x + 1, y, z) == block
            && world.getBlock(x + 1, y, z - 1) == block
            && world.getBlock(x + 1, y, z + 1) == block;
    }

    public static boolean isVanillaPlant(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        if (block == null) {
            return false;
        } else {
            String name = block.getUnlocalizedName()
                .substring(5);
            return name.matches("tallgrass") || name.matches("deadbush")
                || name.matches("yellow_flower")
                || name.matches("red_flower")
                || name.matches("brown_mushroom")
                || name.matches("red_mushroom")
                || name.matches("cactus")
                || name.matches("reeds")
                || name.matches("double_plant");
        }
    }

    public static boolean isAdjacentToDifferentBiome(BiomeGenBase biome, World world, int x, int y, int z) {
        BiomeGenBase biomeCompare = world.getBiomeGenForCoordsBody(x - 1, z);
        if (biome != biomeCompare) {
            return true;
        } else {
            biomeCompare = world.getBiomeGenForCoordsBody(x - 1, z - 1);
            if (biome != biomeCompare) {
                return true;
            } else {
                biomeCompare = world.getBiomeGenForCoordsBody(x - 1, z);
                if (biome != biomeCompare) {
                    return true;
                } else {
                    biomeCompare = world.getBiomeGenForCoordsBody(x - 1, z + 1);
                    if (biome != biomeCompare) {
                        return true;
                    } else {
                        biomeCompare = world.getBiomeGenForCoordsBody(x, z - 1);
                        if (biome != biomeCompare) {
                            return true;
                        } else {
                            biomeCompare = world.getBiomeGenForCoordsBody(x, z + 1);
                            if (biome != biomeCompare) {
                                return true;
                            } else {
                                biomeCompare = world.getBiomeGenForCoordsBody(x + 1, z - 1);
                                if (biome != biomeCompare) {
                                    return true;
                                } else {
                                    biomeCompare = world.getBiomeGenForCoordsBody(x + 1, z);
                                    if (biome != biomeCompare) {
                                        return true;
                                    } else {
                                        biomeCompare = world.getBiomeGenForCoordsBody(x + 1, z + 1);
                                        return biome != biomeCompare;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static BiomeGenBase checkForNearDifferentBiome(BiomeGenBase biome, World world, int x, int y, int z) {
        BiomeGenBase biomeCompare = world.getBiomeGenForCoordsBody(x - 1, z - 2);
        if (biome != biomeCompare) {
            return biomeCompare;
        } else {
            biomeCompare = world.getBiomeGenForCoordsBody(x + 1, z - 2);
            if (biome != biomeCompare) {
                return biomeCompare;
            } else {
                biomeCompare = world.getBiomeGenForCoordsBody(x - 2, z - 1);
                if (biome != biomeCompare) {
                    return biomeCompare;
                } else {
                    biomeCompare = world.getBiomeGenForCoordsBody(x + 2, z - 1);
                    if (biome != biomeCompare) {
                        return biomeCompare;
                    } else {
                        biomeCompare = world.getBiomeGenForCoordsBody(x - 2, z + 1);
                        if (biome != biomeCompare) {
                            return biomeCompare;
                        } else {
                            biomeCompare = world.getBiomeGenForCoordsBody(x + 2, z + 1);
                            if (biome != biomeCompare) {
                                return biomeCompare;
                            } else {
                                biomeCompare = world.getBiomeGenForCoordsBody(x - 1, z + 2);
                                if (biome != biomeCompare) {
                                    return biomeCompare;
                                } else {
                                    biomeCompare = world.getBiomeGenForCoordsBody(x + 1, z + 2);
                                    return biome != biomeCompare ? biomeCompare : null;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static int isAdjacentToAirBlock(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z - 1);
        if (block.getMaterial() == Material.air) {
            return 2;
        } else {
            block = world.getBlock(x - 1, y, z);
            if (block.getMaterial() == Material.air) {
                return 4;
            } else {
                block = world.getBlock(x + 1, y, z);
                if (block.getMaterial() == Material.air) {
                    return 5;
                } else {
                    block = world.getBlock(x, y, z + 1);
                    return block.getMaterial() == Material.air ? 3 : 0;
                }
            }
        }
    }

    public static int isAdjacentToAirOrLeafBlock(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z - 1);
        if (block.getMaterial() != Material.air && block.getMaterial() != Material.leaves) {
            block = world.getBlock(x - 1, y, z);
            if (block.getMaterial() != Material.air && block.getMaterial() != Material.leaves) {
                block = world.getBlock(x + 1, y, z);
                if (block.getMaterial() != Material.air && block.getMaterial() != Material.leaves) {
                    block = world.getBlock(x, y, z + 1);
                    return block.getMaterial() != Material.air && block.getMaterial() != Material.leaves ? 0 : 3;
                } else {
                    return 5;
                }
            } else {
                return 4;
            }
        } else {
            return 2;
        }
    }

    public static int isAdjacentToPlantSpawnBlock(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z - 1);
        if (block.getMaterial() != Material.air && block.getMaterial() != Material.leaves
            && block.getMaterial() != Material.plants) {
            block = world.getBlock(x - 1, y, z);
            if (block.getMaterial() != Material.air && block.getMaterial() != Material.leaves
                && block.getMaterial() != Material.plants) {
                block = world.getBlock(x + 1, y, z);
                if (block.getMaterial() != Material.air && block.getMaterial() != Material.leaves
                    && block.getMaterial() != Material.plants) {
                    block = world.getBlock(x, y, z + 1);
                    return block.getMaterial() != Material.air && block.getMaterial() != Material.leaves
                        && block.getMaterial() != Material.plants ? 0 : 3;
                } else {
                    return 5;
                }
            } else {
                return 4;
            }
        } else {
            return 2;
        }
    }

    public static int isAdjacentToAirOrVineBlock(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z - 1);
        if (block.getMaterial() != Material.air && block.getMaterial() != Material.vine) {
            block = world.getBlock(x - 1, y, z);
            if (block.getMaterial() != Material.air && block.getMaterial() != Material.vine) {
                block = world.getBlock(x + 1, y, z);
                if (block.getMaterial() != Material.air && block.getMaterial() != Material.vine) {
                    block = world.getBlock(x, y, z + 1);
                    return block.getMaterial() != Material.air && block.getMaterial() != Material.vine ? 0 : 3;
                } else {
                    return 5;
                }
            } else {
                return 4;
            }
        } else {
            return 2;
        }
    }

    public static void surroundPlantWithGrass(World world, int x, int y, int z) {
        int ix = x - 1;
        int iz = z - 1;
        if (Blocks.tallgrass.canPlaceBlockAt(world, ix, y, iz)) {
            world.setBlock(ix, y, iz, Blocks.tallgrass, 1, 2);
        }

        iz = z - 1;
        if (Blocks.tallgrass.canPlaceBlockAt(world, x, y, iz)) {
            world.setBlock(x, y, iz, Blocks.tallgrass, 1, 2);
        }

        ix = x + 1;
        iz = z - 1;
        if (Blocks.tallgrass.canPlaceBlockAt(world, ix, y, iz)) {
            world.setBlock(ix, y, iz, Blocks.tallgrass, 1, 2);
        }

        ix = x - 1;
        if (Blocks.tallgrass.canPlaceBlockAt(world, ix, y, z)) {
            world.setBlock(ix, y, z, Blocks.tallgrass, 1, 2);
        }

        ix = x + 1;
        if (Blocks.tallgrass.canPlaceBlockAt(world, ix, y, z)) {
            world.setBlock(ix, y, z, Blocks.tallgrass, 1, 2);
        }

        ix = x - 1;
        iz = z + 1;
        if (Blocks.tallgrass.canPlaceBlockAt(world, ix, y, iz)) {
            world.setBlock(ix, y, iz, Blocks.tallgrass, 1, 2);
        }

        iz = z + 1;
        if (Blocks.tallgrass.canPlaceBlockAt(world, x, y, iz)) {
            world.setBlock(x, y, iz, Blocks.tallgrass, 1, 2);
        }

        ix = x + 1;
        iz = z + 1;
        if (Blocks.tallgrass.canPlaceBlockAt(world, ix, y, iz)) {
            world.setBlock(ix, y, iz, Blocks.tallgrass, 1, 2);
        }

    }

    public static boolean checkAndAdjustSpawnForBlock(World world, PMPSpawnLocation locationData, Block block,
        int heightModifier) {
        int ix = locationData.x - 1;
        int iy = locationData.y + heightModifier;
        int iz = locationData.z - 1;
        Block blockPotential = world.getBlock(ix, iy, iz);
        boolean blockFound = false;
        blockFound = block == blockPotential;
        if (!blockFound) {
            ix = locationData.x;
            iz = locationData.z - 1;
            blockPotential = world.getBlock(ix, iy, iz);
            blockFound = block == blockPotential;
        }

        if (!blockFound) {
            ix = locationData.x + 1;
            iz = locationData.z - 1;
            blockPotential = world.getBlock(ix, iy, iz);
            blockFound = block == blockPotential;
        }

        if (!blockFound) {
            ix = locationData.x - 1;
            iz = locationData.z;
            blockPotential = world.getBlock(ix, iy, iz);
            blockFound = block == blockPotential;
        }

        if (!blockFound) {
            ix = locationData.x + 1;
            iz = locationData.z;
            blockPotential = world.getBlock(ix, iy, iz);
            blockFound = block == blockPotential;
        }

        if (!blockFound) {
            ix = locationData.x - 1;
            iz = locationData.z + 1;
            blockPotential = world.getBlock(ix, iy, iz);
            blockFound = block == blockPotential;
        }

        if (!blockFound) {
            ix = locationData.x;
            iz = locationData.z + 1;
            blockPotential = world.getBlock(ix, iy, iz);
            blockFound = block == blockPotential;
        }

        if (!blockFound) {
            ix = locationData.x + 1;
            iz = locationData.z + 1;
            blockPotential = world.getBlock(ix, iy, iz);
            blockFound = block == blockPotential;
        }

        if (blockFound) {
            locationData.x = ix;
            locationData.y = iy;
            locationData.z = iz;
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGroundBlock(World world, int x, int y, int z) {
        return isGroundBlock(world.getBlock(x, y, z));
    }

    public static boolean isGroundBlock(Block block) {
        if (block == null) {
            return false;
        } else {
            return block.getMaterial() == Material.clay || block.getMaterial() == Material.grass
                || block.getMaterial() == Material.ground
                || block.getMaterial() == Material.rock
                || block.getMaterial() == Material.sand
                || block == Blocks.gravel
                || block == Blocks.stone;
        }
    }

    public static boolean isLargeMushroomBlock(World world, int x, int y, int z) {
        return isLargeMushroomBlock(world.getBlock(x, y, z));
    }

    public static boolean isLargeMushroomBlock(Block block) {
        return block == Blocks.brown_mushroom_block || block == Blocks.red_mushroom_block
            || block.getUnlocalizedName()
                .substring(5)
                .matches("mushroom");
    }

    public static boolean isLeafBlock(Block block) {
        return block.getMaterial() == Material.leaves || block == Blocks.leaves || block == Blocks.leaves2;
    }

    public static boolean isLeafBlock(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        return isLeafBlock(block);
    }

    private static boolean spawnBamboo(World world, Random random, int x, int y, int z, Block block) {
        PMPBlockPlant blockBamboo = (PMPBlockPlant) block;
        if (blockBamboo == null) {
            return false;
        } else {
            int spawnHeight = random.nextInt(blockBamboo.plantData.attributes.growthMax);

            for (int count = 0; count < spawnHeight; ++count) {
                if (canReplaceBlockWithPlant(world, x, y + count, z, true)
                    && canReplaceBlockWithPlant(world, x, y + count + 1, z, true)) {
                    if (blockBamboo.plantData.attributes.renderSubtype == 2) {
                        if (count == 0) {
                            world.setBlock(x, y + count, z, blockBamboo, 0, 2);
                        } else {
                            world.setBlock(x, y + count, z, blockBamboo, 1 + random.nextInt(3), 2);
                        }
                    } else {
                        world.setBlock(x, y + count, z, blockBamboo, 0, 2);
                    }
                } else {
                    spawnHeight = count;
                }
            }

            world.setBlock(x, y + spawnHeight, z, blockBamboo, 4, 2);
            blockBamboo.updateBambooFromTop(world, x, y + spawnHeight, z);
            return true;
        }
    }

    public static boolean spawnRandomBambooPlantCluster(World world, Random random, int x, int y, int z, Block block,
        int range, int maxPlants, boolean overWriteVanilla) {
        for (int count = 0; count < maxPlants; ++count) {
            int ix = x + random.nextInt(range) - random.nextInt(range);
            int iz = z + random.nextInt(range) - random.nextInt(range);
            int iy = world.getHeightValue(ix, iz);
            if (isLeafBlock(world, ix, iy - 1, iz)) {
                iy = adjustHeightForLeaf(world, random, ix, iy, iz);
            }

            Block blockSoil = world.getBlock(ix, iy - 1, iz);
            if (canReplaceBlockWithPlant(world, ix, iy, iz, true)
                && canReplaceBlockWithPlant(world, ix, iy + 1, iz, true)
                && isGroundBlock(blockSoil)) {
                spawnBamboo(world, random, ix, iy, iz, block);
            }
        }

        return true;
    }

    public static int adjustHeightForLeaf(World world, Random random, int x, int y, int z) {
        boolean canContinueDown = true;

        do {
            --y;
            Block block = world.getBlock(x, y - 1, z);
            if (isGroundBlock(world, x, y - 1, z) || block != null && block.getMaterial() == Material.water) {
                canContinueDown = false;
            }
        } while (canContinueDown && y > 0);

        return y;
    }

    public static int adjustHeightForLargeMushroom(World world, Random random, int x, int y, int z) {
        boolean canContinueDown = true;

        do {
            --y;
            if (isGroundBlock(world, x, y - 1, z)) {
                canContinueDown = false;
            }
        } while (canContinueDown && y > 0);

        return y;
    }

    public static boolean canReplaceBlockWithPlant(Block block, boolean includeLeaves) {
        if (block == null) {
            return false;
        } else if (includeLeaves && isLeafBlock(block)) {
            return true;
        } else {
            return block.getMaterial() == Material.air || block.getMaterial() == Material.plants
                || block.getMaterial() == Material.snow
                || block.getMaterial() == Material.vine
                || block == Blocks.tallgrass
                || block == Blocks.double_plant
                || block == Blocks.red_flower
                || block == Blocks.yellow_flower
                || block == Blocks.deadbush;
        }
    }

    public static boolean canReplaceBlockWithPlant(World world, int x, int y, int z, boolean includeLeaves) {
        Block block = world.getBlock(x, y, z);
        return canReplaceBlockWithPlant(block, includeLeaves);
    }

    protected static void spawnWool(World world, PMPSpawnLocation locationData) {
        int color = 15;
        if (locationData.decorator == PMPDecorator.FEATURES) {
            switch (locationData.decoratorFeature) {
                case VILLAGE_CROP:
                    color = 12;
                    break;
                case VILLAGE_PLANT:
                    color = 8;
            }
        } else {
            switch (locationData.decoratorArea) {
                case EPIPHYTE:
                    color = 2;
                    break;
                case PRIMARY:
                    color = 0;
                    break;
                case SHADE:
                    color = 12;
                    break;
                case WATER_EDGE:
                    color = 3;
                    break;
                case WATER_FLOATING:
                    color = 14;
                    break;
                case WATER_IMMERSED:
                    color = 1;
                    break;
                case WATER_SUBMERGED:
                    color = 4;
                    break;
                case TREE:
                    color = 9;
                    break;
                case VINE:
                    color = 5;
            }
        }

        world.setBlock(locationData.x, locationData.y, locationData.z, Blocks.wool, color, 3);
    }

    public static int getOppositeDirection(int direction) {
        if (direction >= 2 && direction <= 9) {
            switch (direction) {
                case 2:
                    return 3;
                case 3:
                    return 2;
                case 4:
                    return 5;
                case 5:
                    return 4;
                case 6:
                    return 9;
                case 7:
                    return 8;
                case 8:
                    return 7;
                case 9:
                    return 6;
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }
}
