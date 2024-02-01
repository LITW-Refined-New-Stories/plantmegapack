package plantmegapack.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

import cpw.mods.fml.common.IWorldGenerator;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPDecorator;
import plantmegapack.common.PMPDecoratorArea;
import plantmegapack.common.PMPDecoratorFeature;
import plantmegapack.common.PMPDecoratorSet;
import plantmegapack.data.PMPDataDecorator;

public class PMPWorldGenerator implements IWorldGenerator {

    private static final int chunkSize = 16;
    private ArrayList<PMPDataDecorator> arrayDecorators = new ArrayList(PMPDecorator.values().length);
    private PMPSpawnLocation locationData = new PMPSpawnLocation();

    public PMPWorldGenerator() {
        this.loadDecoratorData();
    }

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
        IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            case -1:
                if (PlantMegaPack.settingsGeneral.worldgenNetherRate > 0) {
                    this.generateNether(world, random, chunkX * 16, chunkZ * 16);
                }
                break;
            case 0:
                if (PlantMegaPack.settingsGeneral.worldgenOverworldRate > 0) {
                    this.generateSurface(world, random, chunkX * 16, chunkZ * 16);
                }
                break;
            case 1:
                if (PlantMegaPack.settingsGeneral.worldgenEndRate > 0) {
                    this.generateEnd(world, random, chunkX * 16, chunkZ * 16);
                }
        }

    }

    private void generateNether(World world, Random random, int chunkX, int chunkZ) {}

    private void generateSurface(World world, Random random, int chunkX, int chunkZ) {
        BiomeGenBase biome = world.getBiomeGenForCoordsBody(chunkX, chunkZ);
        int x;
        int y;
        int z;
        if (PlantMegaPack.settingsGeneral.contentAquatic && biome.biomeName.matches("Deep Ocean")
            && PlantMegaPack.settingsGeneral.worldgenCoralReefs
            && random.nextInt(100) < 40) {
            x = chunkX + random.nextInt(16);
            z = chunkZ + random.nextInt(16);
            y = world.getHeightValue(x, z);
            this.locationData.getSpawnLocationData(world, random, x, y, z);
            PMPGenAquatic.spawnCoralReef(world, random, this.locationData);
        } else {
            for (int count = 0; count < PlantMegaPack.settingsGeneral.worldgenOverworldPasses; ++count) {
                if (random.nextInt(100) < PlantMegaPack.settingsGeneral.worldgenOverworldRate) {
                    x = chunkX + random.nextInt(16);
                    z = chunkZ + random.nextInt(16);
                    y = world.getHeightValue(x, z);
                    if (this.locationData.getSpawnLocationData(world, random, x, y, z)) {
                        if (this.locationData.decoratorFeature == PMPDecoratorFeature.VILLAGE_CROP) {
                            PMPGenVillageCrop.generate(world, random, this.locationData);
                        } else if (this.locationData.decoratorFeature == PMPDecoratorFeature.VILLAGE_PLANT) {
                            PMPGenVillagePlant.generate(world, random, this.locationData);
                        } else {
                            PMPDataDecorator decorator = this.getDecorator(this.locationData.decorator);
                            if (decorator != null && random.nextInt(100) < decorator.generationRate) {
                                PMPGenBase.spawnPlantCluster(
                                    world,
                                    random,
                                    this.locationData,
                                    decorator.getRandomPlant(random, this.locationData.decoratorArea));
                            }
                        }
                    }
                }
            }

        }
    }

    private void generateEnd(World world, Random random, int chunkX, int chunkZ) {}

    private void loadDecoratorData() {
        PMPDecorator[] var2 = PMPDecorator.values();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            PMPDecorator spawnBiome = var2[var4];
            PMPDataDecorator decorator = new PMPDataDecorator(spawnBiome);
            if (decorator != null) {
                this.arrayDecorators.add(decorator);
            }
        }

    }

    private PMPDataDecorator getDecorator(PMPDecorator decorator) {
        for (int index = 0; index < this.arrayDecorators.size(); ++index) {
            if (((PMPDataDecorator) this.arrayDecorators.get(index)).decorator == decorator) {
                return (PMPDataDecorator) this.arrayDecorators.get(index);
            }
        }

        return null;
    }

    public PMPDataDecorator getDecoratorByID(String ID) {
        for (int index = 0; index < this.arrayDecorators.size(); ++index) {
            if (((PMPDataDecorator) this.arrayDecorators.get(index)).decorator.ID.matches(ID)) {
                return (PMPDataDecorator) this.arrayDecorators.get(index);
            }
        }

        return null;
    }

    public void resetAllDecoratorsInSet(PMPDecoratorSet decoratorSet) {
        for (int index = 0; index < this.arrayDecorators.size(); ++index) {
            PMPDataDecorator decoratorData = (PMPDataDecorator) this.arrayDecorators.get(index);
            if (decoratorData != null && decoratorSet == decoratorData.decorator.decoratorSet) {
                decoratorData.clearAllDecoratorAreas();
                decoratorData.resetDecoratorDefaults((PMPDecoratorArea) null);
                decoratorData.saveUserConfig();
            }
        }

    }

    public int getDecoratorsCount() {
        return this.arrayDecorators.size();
    }
}
