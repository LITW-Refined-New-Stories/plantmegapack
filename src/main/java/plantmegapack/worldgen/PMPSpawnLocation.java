package plantmegapack.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.TempCategory;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

import cpw.mods.fml.common.Loader;
import plantmegapack.common.PMPDecorator;
import plantmegapack.common.PMPDecoratorArea;
import plantmegapack.common.PMPDecoratorFeature;

public class PMPSpawnLocation {

    public BiomeGenBase biome;
    public PMPDecorator decorator;
    public PMPDecoratorArea decoratorArea;
    public PMPDecoratorFeature decoratorFeature;
    public Block blockAbove;
    public Block blockBelow;
    public Block blockSpawn;
    public int spawnDirection;
    public int x;
    public int y;
    public int z;
    public int waterBlockDepth;

    private void reset() {
        this.biome = null;
        this.decorator = null;
        this.decoratorArea = null;
        this.decoratorFeature = null;
        this.blockAbove = null;
        this.blockBelow = null;
        this.blockSpawn = null;
        this.spawnDirection = -1;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.waterBlockDepth = 0;
    }

    public boolean getSpawnLocationData(World world, Random random, int x, int y, int z) {
        this.reset();
        this.x = x;
        this.y = y;
        this.z = z;
        this.decoratorArea = null;
        this.getBlockInfo(world);
        if (!this.getLocationInfo(world)) {
            return false;
        } else if (PMPGenBase.isLeafBlock(world, this.x, this.y - 1, this.z)
            && this.adjustLocationForLeaf(world, random)) {
                return this.y >= 63;
            } else if (this.checkForWaterPlant(world)) {
                return true;
            } else if (PMPGenBase.isAdjacentToBlockMaterial(world, this.x, this.y - 1, this.z, Material.water) > 0) {
                this.decoratorArea = PMPDecoratorArea.WATER_EDGE;
                return true;
            } else if (this.blockSpawn == Blocks.wheat) {
                this.decorator = PMPDecorator.FEATURES;
                this.decoratorFeature = PMPDecoratorFeature.VILLAGE_CROP;
                return true;
            } else if (this.checkForVillagePlant(world)) {
                this.decorator = PMPDecorator.FEATURES;
                this.decoratorFeature = PMPDecoratorFeature.VILLAGE_PLANT;
                return true;
            } else if (PMPGenBase.isLargeMushroomBlock(world, this.x, this.y - 1, this.z)) {
                this.y = PMPGenBase.adjustHeightForLargeMushroom(world, random, this.x, this.y - 1, this.z);
                this.decoratorArea = PMPDecoratorArea.SHADE;
                this.getBlockInfo(world);
                return true;
            } else {
                this.decoratorArea = PMPDecoratorArea.PRIMARY;
                return true;
            }
    }

    public boolean getLocationInfo(World world) {
        this.biome = world.getBiomeGenForCoordsBody(this.x, this.z);
        boolean biomeEdge = PMPGenBase.isAdjacentToDifferentBiome(this.biome, world, this.x, this.y, this.z);
        BiomeGenBase.TempCategory tempCategory = this.biome.getTempCategory();
        this.decorator = null;
        if (this.biome.biomeName.matches("Cold Beach")) {
            this.decorator = PMPDecorator.BEACH_COLD;
        } else if (this.biome.biomeName.matches("Beach")) {
            this.decorator = biomeEdge ? PMPDecorator.BEACH_OCEAN_EDGE : PMPDecorator.BEACH_OCEAN;
        } else if (this.biome.biomeName.matches("Stone Beach")) {
            this.decorator = PMPDecorator.BEACH_STONE;
        } else if (this.biome.biomeName.matches("Birch Forest")) {
            this.decorator = biomeEdge ? PMPDecorator.BIRCH_FOREST_EDGE : PMPDecorator.BIRCH_FOREST;
        } else if (this.biome.biomeName.matches("Birch Forest Hills")) {
            this.decorator = biomeEdge ? PMPDecorator.BIRCH_FOREST_HILLS_EDGE : PMPDecorator.BIRCH_FOREST_HILLS;
        } else if (this.biome.biomeName.matches("Birch Forest M")) {
            this.decorator = biomeEdge ? PMPDecorator.BIRCH_FOREST_M_EDGE : PMPDecorator.BIRCH_FOREST_M;
        } else if (this.biome.biomeName.matches("Birch Forest Hills M")) {
            this.decorator = biomeEdge ? PMPDecorator.BIRCH_FOREST_HILLS_M_EDGE : PMPDecorator.BIRCH_FOREST_HILLS_M;
        } else if (this.biome.biomeName.matches("Cold Taiga")) {
            this.decorator = biomeEdge ? PMPDecorator.COLD_TAIGA_EDGE : PMPDecorator.COLD_TAIGA;
        } else if (this.biome.biomeName.matches("Cold Taiga Hills")) {
            this.decorator = biomeEdge ? PMPDecorator.COLD_TAIGA_HILLS_EDGE : PMPDecorator.COLD_TAIGA_HILLS;
        } else if (this.biome.biomeName.contains("Cold Taiga M")) {
            this.decorator = biomeEdge ? PMPDecorator.COLD_TAIGA_M_EDGE : PMPDecorator.COLD_TAIGA_M;
        } else if (this.biome.biomeName.matches("Desert")) {
            this.decorator = biomeEdge ? PMPDecorator.DESERT_EDGE : PMPDecorator.DESERT;
        } else if (this.biome.biomeName.matches("DesertHills")) {
            this.decorator = biomeEdge ? PMPDecorator.DESERT_HILLS_EDGE : PMPDecorator.DESERT_HILLS;
        } else if (this.biome.biomeName.matches("Desert M")) {
            this.decorator = biomeEdge ? PMPDecorator.DESERT_M_EDGE : PMPDecorator.DESERT_M;
        } else if (this.biome.biomeName.matches("Extreme Hills")) {
            this.decorator = biomeEdge ? PMPDecorator.EXTREME_HILLS_EDGE : PMPDecorator.EXTREME_HILLS;
        } else if (this.biome.biomeName.matches("Extreme Hills Edge")) {
            this.decorator = PMPDecorator.EXTREME_HILLS_EDGE;
        } else if (this.biome.biomeName.matches("Extreme Hills+")) {
            this.decorator = biomeEdge ? PMPDecorator.EXTREME_HILLS_PLUS_EDGE : PMPDecorator.EXTREME_HILLS_PLUS;
        } else if (this.biome.biomeName.matches("Extreme Hills M")) {
            this.decorator = biomeEdge ? PMPDecorator.EXTREME_HILLS_M_EDGE : PMPDecorator.EXTREME_HILLS_M;
        } else if (this.biome.biomeName.matches("Extreme Hills+ M")) {
            this.decorator = biomeEdge ? PMPDecorator.EXTREME_HILLS_PLUS_M_EDGE : PMPDecorator.EXTREME_HILLS_PLUS_M;
        } else if (this.biome.biomeName.matches("Flower Forest")) {
            this.decorator = PMPDecorator.FLOWER_FOREST;
        } else if (this.biome.biomeName.matches("Forest")) {
            this.decorator = biomeEdge ? PMPDecorator.FOREST_EDGE : PMPDecorator.FOREST;
        } else if (this.biome.biomeName.matches("ForestHills")) {
            this.decorator = biomeEdge ? PMPDecorator.FOREST_HILLS_EDGE : PMPDecorator.FOREST_HILLS;
        } else if (this.biome.biomeName.matches("Ice Mountains")) {
            this.decorator = biomeEdge ? PMPDecorator.ICE_MOUNTAINS_EDGE : PMPDecorator.ICE_MOUNTAINS;
        } else if (this.biome.biomeName.matches("Ice Plains")) {
            this.decorator = biomeEdge ? PMPDecorator.ICE_PLAINS_EDGE : PMPDecorator.ICE_PLAINS;
        } else if (this.biome.biomeName.matches("Ice Plains Spikes")) {
            this.decorator = biomeEdge ? PMPDecorator.ICE_PLAINS_SPIKES_EDGE : PMPDecorator.ICE_PLAINS_SPIKES;
        } else if (this.biome.biomeName.matches("Jungle")) {
            this.decorator = biomeEdge ? PMPDecorator.JUNGLE_EDGE : PMPDecorator.JUNGLE;
        } else if (this.biome.biomeName.matches("JungleEdge")) {
            this.decorator = PMPDecorator.JUNGLE_EDGE;
        } else if (this.biome.biomeName.matches("JungleHills")) {
            this.decorator = biomeEdge ? PMPDecorator.JUNGLE_HILLS_EDGE : PMPDecorator.JUNGLE_HILLS;
        } else if (this.biome.biomeName.matches("Jungle M")) {
            this.decorator = PMPDecorator.JUNGLE_M;
        } else if (!this.biome.biomeName.matches("JungleEdge M") && !this.biome.biomeName.matches("Jungle Edge M")) {
            if (this.biome.biomeName.matches("Mega Spruce Taiga")) {
                this.decorator = biomeEdge ? PMPDecorator.MEGA_SPRUCE_TAIGA_EDGE : PMPDecorator.MEGA_SPRUCE_TAIGA;
            } else if (this.biome.biomeName.matches("Mega Taiga")) {
                this.decorator = biomeEdge ? PMPDecorator.MEGA_TAIGA_EDGE : PMPDecorator.MEGA_TAIGA;
            } else if (this.biome.biomeName.matches("Mega Taiga Hills")) {
                this.decorator = biomeEdge ? PMPDecorator.MEGA_TAIGA_HILLS_EDGE : PMPDecorator.MEGA_TAIGA_HILLS;
            } else if (this.biome.biomeName.matches("Mesa")) {
                this.decorator = biomeEdge ? PMPDecorator.MESA_EDGE : PMPDecorator.MESA;
            } else if (this.biome.biomeName.matches("Mesa (Bryce)")) {
                this.decorator = PMPDecorator.MESA_BRYCE;
            } else if (this.biome.biomeName.matches("Mesa Plateau")) {
                this.decorator = PMPDecorator.MESA_PLATEAU;
            } else if (this.biome.biomeName.matches("Mesa Plateau F")) {
                this.decorator = PMPDecorator.MESA_PLATEAU_F;
            } else if (this.biome.biomeName.matches("MushroomIsland")) {
                this.decorator = PMPDecorator.MUSHROOM;
            } else if (this.biome.biomeName.matches("MushroomIslandShore")) {
                this.decorator = PMPDecorator.MUSHROOM_SHORE;
            } else if (this.biome.biomeName.matches("Ocean")) {
                this.decorator = PMPDecorator.OCEAN;
            } else if (this.biome.biomeName.matches("Deep Ocean")) {
                this.decorator = PMPDecorator.OCEAN_DEEP;
            } else if (this.biome.biomeName.matches("Plains")) {
                this.decorator = biomeEdge ? PMPDecorator.PLAINS_EDGE : PMPDecorator.PLAINS;
            } else if (this.biome.biomeName.matches("Sunflower Plains")) {
                this.decorator = biomeEdge ? PMPDecorator.PLAINS_SUNFLOWER_EDGE : PMPDecorator.PLAINS_SUNFLOWER;
            } else if (this.biome.biomeName.matches("River")) {
                if (tempCategory == TempCategory.COLD) {
                    this.decorator = PMPDecorator.RIVER_COLD;
                } else if (tempCategory == TempCategory.WARM) {
                    this.decorator = PMPDecorator.RIVER_WARM;
                } else {
                    this.decorator = PMPDecorator.RIVER_MEDIUM;
                }
            } else if (this.biome.biomeName.matches("Roofed Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.ROOFED_FOREST_EDGE : PMPDecorator.ROOFED_FOREST;
            } else if (this.biome.biomeName.matches("Roofed Forest M")) {
                this.decorator = biomeEdge ? PMPDecorator.ROOFED_FOREST_M_EDGE : PMPDecorator.ROOFED_FOREST_M;
            } else if (this.biome.biomeName.matches("Savanna")) {
                this.decorator = biomeEdge ? PMPDecorator.SAVANNA_EDGE : PMPDecorator.SAVANNA;
            } else if (this.biome.biomeName.matches("Savanna M")) {
                this.decorator = biomeEdge ? PMPDecorator.SAVANNA_M_EDGE : PMPDecorator.SAVANNA_M;
            } else if (this.biome.biomeName.matches("Savanna Plateau")) {
                this.decorator = PMPDecorator.SAVANNA_PLATEAU;
            } else if (this.biome.biomeName.matches("Sky")) {
                this.decorator = PMPDecorator.SKY;
            } else if (this.biome.biomeName.matches("Swampland")) {
                this.decorator = biomeEdge ? PMPDecorator.SWAMP_EDGE : PMPDecorator.SWAMP;
            } else if (this.biome.biomeName.matches("Swampland M")) {
                this.decorator = biomeEdge ? PMPDecorator.SWAMP_M_EDGE : PMPDecorator.SWAMP_M;
            } else if (this.biome.biomeName.matches("Taiga")) {
                this.decorator = biomeEdge ? PMPDecorator.TAIGA_EDGE : PMPDecorator.TAIGA;
            } else if (this.biome.biomeName.matches("TaigaHills")) {
                this.decorator = biomeEdge ? PMPDecorator.TAIGA_HILLS_EDGE : PMPDecorator.TAIGA_HILLS;
            } else if (this.biome.biomeName.matches("Taiga M")) {
                this.decorator = biomeEdge ? PMPDecorator.TAIGA_M_EDGE : PMPDecorator.TAIGA_M;
            }
        } else {
            this.decorator = PMPDecorator.JUNGLE_M_EDGE;
        }

        if (Loader.isModLoaded("ExtrabiomesXL")) {
            if (this.biome.biomeName.matches("Alpine")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_ALPINE_EDGE : PMPDecorator.EBXL_ALPINE;
            } else if (this.biome.biomeName.matches("Autumn Woods")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_AUTUMNWOODS_EDGE : PMPDecorator.EBXL_AUTUMNWOODS;
            } else if (this.biome.biomeName.matches("Extreme Jungle")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_EXTREMEJUNGLE_EDGE : PMPDecorator.EBXL_EXTREMEJUNGLE;
            } else if (this.biome.biomeName.matches("Forested Hills")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_FORESTEDHILLS_EDGE : PMPDecorator.EBXL_FORESTEDHILLS;
            } else if (this.biome.biomeName.matches("Forested Island")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_FORESTEDISLAND_EDGE : PMPDecorator.EBXL_FORESTEDISLAND;
            } else if (this.biome.biomeName.matches("Glacier")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_GLACIER_EDGE : PMPDecorator.EBXL_GLACIER;
            } else if (this.biome.biomeName.matches("Green Hills")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_GREENHILLS_EDGE : PMPDecorator.EBXL_GREENHILLS;
            } else if (this.biome.biomeName.matches("Green Swamplands")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_GREENSWAMPLANDS_EDGE : PMPDecorator.EBXL_GREENSWAMPLANDS;
            } else if (this.biome.biomeName.matches("Ice Wasteland")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_ICEWASTELAND_EDGE : PMPDecorator.EBXL_ICEWASTELAND;
            } else if (this.biome.biomeName.matches("Marsh")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_MARSH_EDGE : PMPDecorator.EBXL_MARSH;
            } else if (this.biome.biomeName.matches("Meadow")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_MEADOW_EDGE : PMPDecorator.EBXL_MEADOW;
            } else if (this.biome.biomeName.matches("Mini Jungle")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_MINIJUNGLE_EDGE : PMPDecorator.EBXL_MINIJUNGLE;
            } else if (this.biome.biomeName.matches("Mountainous Desert")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_MOUNTAINOUSDESERT_EDGE
                    : PMPDecorator.EBXL_MOUNTAINOUSDESERT;
            } else if (this.biome.biomeName.matches("Red Rock Mountains")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_REDROCKMOUNTAINS_EDGE
                    : PMPDecorator.EBXL_REDROCKMOUNTAINS;
            } else if (this.biome.biomeName.matches("Mountain Taiga")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_MOUNTAINTAIGA_EDGE : PMPDecorator.EBXL_MOUNTAINTAIGA;
            } else if (this.biome.biomeName.matches("Pine Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_PINEFOREST_EDGE : PMPDecorator.EBXL_PINEFOREST;
            } else if (this.biome.biomeName.matches("Rainforest")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_RAINFOREST_EDGE : PMPDecorator.EBXL_RAINFOREST;
            } else if (this.biome.biomeName.matches("Redwood Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_REDWOODFOREST_EDGE : PMPDecorator.EBXL_REDWOODFOREST;
            } else if (this.biome.biomeName.matches("Lush Redwoods")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_LUSHREDWOODS_EDGE : PMPDecorator.EBXL_LUSHREDWOODS;
            } else if (this.biome.biomeName.matches("Shrubland")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_SHRUBLAND_EDGE : PMPDecorator.EBXL_SHRUBLAND;
            } else if (this.biome.biomeName.matches("Snow Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_SNOWFOREST_EDGE : PMPDecorator.EBXL_SNOWFOREST;
            } else if (this.biome.biomeName.matches("Temperate Rainforest")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_TEMPERATERAINFOREST_EDGE
                    : PMPDecorator.EBXL_TEMPERATERAINFOREST;
            } else if (this.biome.biomeName.matches("Tundra")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_TUNDRA_EDGE : PMPDecorator.EBXL_TUNDRA;
            } else if (this.biome.biomeName.matches("Woodlands")) {
                this.decorator = biomeEdge ? PMPDecorator.EBXL_WOODLANDS_EDGE : PMPDecorator.EBXL_WOODLANDS;
            }
        }

        if (Loader.isModLoaded("Highlands")) {
            if (this.biome.biomeName.matches("Highlands_Alps")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_ALPS_EDGE : PMPDecorator.HL_ALPS;
            } else if (this.biome.biomeName.matches("Highlands_Autumn Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_AUTUMNFOREST_EDGE : PMPDecorator.HL_AUTUMNFOREST;
            } else if (this.biome.biomeName.matches("Highlands_Badlands")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_BADLANDS_EDGE : PMPDecorator.HL_BADLANDS;
            } else if (this.biome.biomeName.matches("Highlands_Bald Hill")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_BALDHILL_EDGE : PMPDecorator.HL_BALDHILL;
            } else if (this.biome.biomeName.matches("Highlands_Birch Hills")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_BIRCHHILLS_EDGE : PMPDecorator.HL_BIRCHHILLS;
            } else if (this.biome.biomeName.matches("Highlands_Bog")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_BOG_EDGE : PMPDecorator.HL_BOG;
            } else if (this.biome.biomeName.matches("Highlands_Canyon")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_CANYON_EDGE : PMPDecorator.HL_CANYON;
            } else if (this.biome.biomeName.matches("Highlands_Cliffs")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_CLIFFS_EDGE : PMPDecorator.HL_CLIFFS;
            } else if (this.biome.biomeName.matches("Highlands_Desert Island")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_DESERTISLAND_EDGE : PMPDecorator.HL_DESERTISLAND;
            } else if (this.biome.biomeName.matches("Highlands_Desert Mountains")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_DESERTMOUNTAINS_EDGE : PMPDecorator.HL_DESERTMOUNTAINS;
            } else if (this.biome.biomeName.matches("Highlands_Dunes")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_DUNES_EDGE : PMPDecorator.HL_DUNES;
            } else if (this.biome.biomeName.matches("Highlands_Estuary")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_ESTUARY_EDGE : PMPDecorator.HL_ESTUARY;
            } else if (this.biome.biomeName.matches("Highlands_Flying Mountains")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_FLYINGMOUNTAINS_EDGE : PMPDecorator.HL_FLYINGMOUNTAINS;
            } else if (this.biome.biomeName.matches("Highlands_Forest Island")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_FORESTISLAND_EDGE : PMPDecorator.HL_FORESTISLAND;
            } else if (this.biome.biomeName.matches("Highlands_Glacier")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_GLACIER_EDGE : PMPDecorator.HL_GLACIER;
            } else if (this.biome.biomeName.matches("Highlands_Highlands")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_HIGHLANDS_EDGE : PMPDecorator.HL_HIGHLANDS;
            } else if (this.biome.biomeName.matches("Highlands_Jungle Island")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_JUNGLEISLAND_EDGE : PMPDecorator.HL_JUNGLEISLAND;
            } else if (this.biome.biomeName.matches("Highlands_Lake")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_LAKE_EDGE : PMPDecorator.HL_LAKE;
            } else if (this.biome.biomeName.matches("Highlands_Lowlands")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_LOWLANDS_EDGE : PMPDecorator.HL_LOWLANDS;
            } else if (this.biome.biomeName.matches("Highlands_Meadow")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_MEADOW_EDGE : PMPDecorator.HL_MEADOW;
            } else if (this.biome.biomeName.matches("Highlands_Mesa")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_MESA_EDGE : PMPDecorator.HL_MESA;
            } else if (this.biome.biomeName.matches("Highlands_Oasis")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_OASIS_EDGE : PMPDecorator.HL_OASIS;
            } else if (this.biome.biomeName.matches("Highlands_Outback")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_OUTBACK_EDGE : PMPDecorator.HL_OUTBACK;
            } else if (this.biome.biomeName.matches("Highlands_Pinelands")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_PINELANDS_EDGE : PMPDecorator.HL_PINELANDS;
            } else if (this.biome.biomeName.matches("Highlands_Rainforest")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_RAINFOREST_EDGE : PMPDecorator.HL_RAINFOREST;
            } else if (this.biome.biomeName.matches("Highlands_Redwood Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_REDWOODFOREST_EDGE : PMPDecorator.HL_REDWOODFOREST;
            } else if (this.biome.biomeName.matches("Highlands_Rock Island")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_ROCKISLAND_EDGE : PMPDecorator.HL_ROCKISLAND;
            } else if (this.biome.biomeName.matches("Highlands_Rock Mountains")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_ROCKMOUNTAINS_EDGE : PMPDecorator.HL_ROCKMOUNTAINS;
            } else if (this.biome.biomeName.matches("Highlands_Sahel")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_SAHEL_EDGE : PMPDecorator.HL_SAHEL;
            } else if (this.biome.biomeName.matches("Highlands_Savannah")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_SAVANNAH_EDGE : PMPDecorator.HL_SAVANNAH;
            } else if (this.biome.biomeName.matches("Highlands_Shrubland")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_SHRUBLAND_EDGE : PMPDecorator.HL_SHRUBLAND;
            } else if (this.biome.biomeName.matches("Highlands_Snow Island")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_SNOWISLAND_EDGE : PMPDecorator.HL_SNOWISLAND;
            } else if (this.biome.biomeName.matches("Highlands_Snow Mountains")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_SNOWMOUNTAINS_EDGE : PMPDecorator.HL_SNOWMOUNTAINS;
            } else if (this.biome.biomeName.matches("Highlands_Steppe")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_STEPPE_EDGE : PMPDecorator.HL_STEPPE;
            } else if (this.biome.biomeName.matches("Highlands_Tall Pine Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_TALLPINEFOREST_EDGE : PMPDecorator.HL_TALLPINEFOREST;
            } else if (this.biome.biomeName.matches("Highlands_Tropical Islands")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_TROPICALISLANDS_EDGE : PMPDecorator.HL_TROPICALISLANDS;
            } else if (this.biome.biomeName.matches("Highlands_Tropics")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_TROPICS_EDGE : PMPDecorator.HL_TROPICS;
            } else if (this.biome.biomeName.matches("Highlands_Tundra")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_TUNDRA_EDGE : PMPDecorator.HL_TUNDRA;
            } else if (this.biome.biomeName.matches("Highlands_Valley")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_VALLEY_EDGE : PMPDecorator.HL_VALLEY;
            } else if (this.biome.biomeName.matches("Highlands_Volcano Island")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_VOLCANOISLAND_EDGE : PMPDecorator.HL_VOLCANOISLAND;
            } else if (this.biome.biomeName.matches("Highlands_Windy Island")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_WINDYISLAND_EDGE : PMPDecorator.HL_WINDYISLAND;
            } else if (this.biome.biomeName.matches("Highlands_Woodland Mountains")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_WOODLANDMOUNTAINS_EDGE : PMPDecorator.HL_WOODLANDMOUNTAINS;
            } else if (this.biome.biomeName.matches("Highlands_Woodlands")) {
                this.decorator = biomeEdge ? PMPDecorator.HL_WOODLANDS_EDGE : PMPDecorator.HL_WOODLANDS;
            }
        }

        if (Loader.isModLoaded("BiomesOPlenty")) {
            if (this.biome.biomeName.matches("Alps")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_ALPS_EDGE : PMPDecorator.BOP_ALPS;
            } else if (this.biome.biomeName.matches("Alps Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_ALPSFOREST_EDGE : PMPDecorator.BOP_ALPSFOREST;
            } else if (this.biome.biomeName.matches("Arctic")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_ARCTIC_EDGE : PMPDecorator.BOP_ARCTIC;
            } else if (this.biome.biomeName.matches("Bamboo Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_BAMBOOFOREST_EDGE : PMPDecorator.BOP_BAMBOOFOREST;
            } else if (this.biome.biomeName.matches("Bayou")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_BAYOU_EDGE : PMPDecorator.BOP_BAYOU;
            } else if (this.biome.biomeName.matches("Bog")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_BOG_EDGE : PMPDecorator.BOP_BOG;
            } else if (this.biome.biomeName.matches("Boreal Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_BOREALFOREST_EDGE : PMPDecorator.BOP_BOREALFOREST;
            } else if (this.biome.biomeName.matches("Brushland")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_BRUSHLAND_EDGE : PMPDecorator.BOP_BRUSHLAND;
            } else if (this.biome.biomeName.matches("Canyon")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_CANYON_EDGE : PMPDecorator.BOP_CANYON;
            } else if (this.biome.biomeName.matches("Canyon Ravine")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_CANYONRAVINE_EDGE : PMPDecorator.BOP_CANYONRAVINE;
            } else if (this.biome.biomeName.matches("Chaparral")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_CHAPARRAL_EDGE : PMPDecorator.BOP_CHAPARRAL;
            } else if (this.biome.biomeName.matches("Cherry Blossom Grove")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_CHERRYBLOSSOMGROVE_EDGE
                    : PMPDecorator.BOP_CHERRYBLOSSOMGROVE;
            } else if (this.biome.biomeName.matches("Coniferous Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_CONIFEROUSFOREST_EDGE : PMPDecorator.BOP_CONIFEROUSFOREST;
            } else if (this.biome.biomeName.matches("Crag")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_CRAG_EDGE : PMPDecorator.BOP_CRAG;
            } else if (this.biome.biomeName.matches("Dead Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_DEADFOREST_EDGE : PMPDecorator.BOP_DEADFOREST;
            } else if (this.biome.biomeName.matches("Dead Swamp")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_DEADSWAMP_EDGE : PMPDecorator.BOP_DEADSWAMP;
            } else if (this.biome.biomeName.matches("Deciduous Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_DECIDUOUSFOREST_EDGE : PMPDecorator.BOP_DECIDUOUSFOREST;
            } else if (this.biome.biomeName.matches("Fen")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_FEN_EDGE : PMPDecorator.BOP_FEN;
            } else if (this.biome.biomeName.matches("Flower Field")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_FLOWERFIELD_EDGE : PMPDecorator.BOP_FLOWERFIELD;
            } else if (this.biome.biomeName.matches("Frost Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_FROSTFOREST_EDGE : PMPDecorator.BOP_FROSTFOREST;
            } else if (this.biome.biomeName.matches("Fungi Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_FUNGIFOREST_EDGE : PMPDecorator.BOP_FUNGIFOREST;
            } else if (this.biome.biomeName.matches("Garden")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_GARDEN_EDGE : PMPDecorator.BOP_GARDEN;
            } else if (this.biome.biomeName.matches("Glacier")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_GLACIER_EDGE : PMPDecorator.BOP_GLACIER;
            } else if (this.biome.biomeName.matches("Grassland")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_GRASSLAND_EDGE : PMPDecorator.BOP_GRASSLAND;
            } else if (this.biome.biomeName.matches("Grove")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_GROVE_EDGE : PMPDecorator.BOP_GROVE;
            } else if (this.biome.biomeName.matches("Heathland")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_HEATHLAND_EDGE : PMPDecorator.BOP_HEATHLAND;
            } else if (this.biome.biomeName.matches("Highland")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_HIGHLAND_EDGE : PMPDecorator.BOP_HIGHLAND;
            } else if (this.biome.biomeName.matches("Jade Cliffs")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_JADECLIFFS_EDGE : PMPDecorator.BOP_JADECLIFFS;
            } else if (this.biome.biomeName.matches("Lavender Fields")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_LAVENDERFIELDS_EDGE : PMPDecorator.BOP_LAVENDERFIELDS;
            } else if (this.biome.biomeName.matches("Lush Desert")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_LUSHDESERT_EDGE : PMPDecorator.BOP_LUSHDESERT;
            } else if (this.biome.biomeName.matches("Lush Swamp")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_LUSHSWAMP_EDGE : PMPDecorator.BOP_LUSHSWAMP;
            } else if (this.biome.biomeName.matches("Mangrove")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_MANGROVE_EDGE : PMPDecorator.BOP_MANGROVE;
            } else if (this.biome.biomeName.matches("Maple Woods")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_MAPLEWOODS_EDGE : PMPDecorator.BOP_MAPLEWOODS;
            } else if (this.biome.biomeName.matches("Marsh")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_MARSH_EDGE : PMPDecorator.BOP_MARSH;
            } else if (this.biome.biomeName.matches("Meadow")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_MEADOW_EDGE : PMPDecorator.BOP_MEADOW;
            } else if (this.biome.biomeName.matches("Meadow Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_MEADOWFOREST_EDGE : PMPDecorator.BOP_MEADOWFOREST;
            } else if (this.biome.biomeName.matches("Moor")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_MOOR_EDGE : PMPDecorator.BOP_MOOR;
            } else if (this.biome.biomeName.matches("Mountain")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_MOUNTAIN_EDGE : PMPDecorator.BOP_MOUNTAIN;
            } else if (this.biome.biomeName.matches("Mystic Grove")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_MYSTICGROVE_EDGE : PMPDecorator.BOP_MYSTICGROVE;
            } else if (this.biome.biomeName.matches("Oasis")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_OASIS_EDGE : PMPDecorator.BOP_OASIS;
            } else if (this.biome.biomeName.matches("Ominous Woods")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_OMINOUSWOODS_EDGE : PMPDecorator.BOP_OMINOUSWOODS;
            } else if (this.biome.biomeName.matches("Orchard")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_ORCHARD_EDGE : PMPDecorator.BOP_ORCHARD;
            } else if (this.biome.biomeName.matches("Origin Valley")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_ORIGINVALLEY_EDGE : PMPDecorator.BOP_ORIGINVALLEY;
            } else if (this.biome.biomeName.matches("Outback")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_OUTBACK_EDGE : PMPDecorator.BOP_OUTBACK;
            } else if (this.biome.biomeName.matches("Prairie")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_PRAIRIE_EDGE : PMPDecorator.BOP_PRAIRIE;
            } else if (this.biome.biomeName.matches("Quagmire")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_QUAGMIRE_EDGE : PMPDecorator.BOP_QUAGMIRE;
            } else if (this.biome.biomeName.matches("Rainforest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_RAINFOREST_EDGE : PMPDecorator.BOP_RAINFOREST;
            } else if (this.biome.biomeName.matches("Redwood Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_REDWOODFOREST_EDGE : PMPDecorator.BOP_REDWOODFOREST;
            } else if (this.biome.biomeName.matches("Sacred Springs")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_SACREDSPRINGS_EDGE : PMPDecorator.BOP_SACREDSPRINGS;
            } else if (this.biome.biomeName.matches("Scrubland")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_SCRUBLAND_EDGE : PMPDecorator.BOP_SCRUBLAND;
            } else if (this.biome.biomeName.matches("Seasonal Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_SEASONALFOREST_EDGE : PMPDecorator.BOP_SEASONALFOREST;
            } else if (this.biome.biomeName.matches("Shield")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_SHIELD_EDGE : PMPDecorator.BOP_SHIELD;
            } else if (this.biome.biomeName.matches("Shrubland")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_SHRUBLAND_EDGE : PMPDecorator.BOP_SHRUBLAND;
            } else if (this.biome.biomeName.matches("Silkglades")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_SILKGLADES_EDGE : PMPDecorator.BOP_SILKGLADES;
            } else if (this.biome.biomeName.matches("Sludgepit")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_SLUDGEPIT_EDGE : PMPDecorator.BOP_SLUDGEPIT;
            } else if (this.biome.biomeName.matches("Snowy Coniferous Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_SNOWYCONIFEROUSFOREST_EDGE
                    : PMPDecorator.BOP_SNOWYCONIFEROUSFOREST;
            } else if (this.biome.biomeName.matches("Spruce Woods")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_SPRUCEWOODS_EDGE : PMPDecorator.BOP_SPRUCEWOODS;
            } else if (this.biome.biomeName.matches("Steppe")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_STEPPE_EDGE : PMPDecorator.BOP_STEPPE;
            } else if (this.biome.biomeName.matches("Temperate Rainforest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_TEMPERATERAINFOREST_EDGE
                    : PMPDecorator.BOP_TEMPERATERAINFOREST;
            } else if (this.biome.biomeName.matches("Thicket")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_THICKET_EDGE : PMPDecorator.BOP_THICKET;
            } else if (this.biome.biomeName.matches("Tropical Rainforest")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_TROPICALRAINFOREST_EDGE
                    : PMPDecorator.BOP_TROPICALRAINFOREST;
            } else if (this.biome.biomeName.matches("Tropics")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_TROPICS_EDGE : PMPDecorator.BOP_TROPICS;
            } else if (this.biome.biomeName.matches("Tundra")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_TUNDRA_EDGE : PMPDecorator.BOP_TUNDRA;
            } else if (this.biome.biomeName.matches("Volcano")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_VOLCANO_EDGE : PMPDecorator.BOP_VOLCANO;
            } else if (this.biome.biomeName.matches("Wasteland")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_WASTELAND_EDGE : PMPDecorator.BOP_WASTELAND;
            } else if (this.biome.biomeName.matches("Wetland")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_WETLAND_EDGE : PMPDecorator.BOP_WETLAND;
            } else if (this.biome.biomeName.matches("Woodland")) {
                this.decorator = biomeEdge ? PMPDecorator.BOP_WOODLAND_EDGE : PMPDecorator.BOP_WOODLAND;
            }
        }

        if (Loader.isModLoaded("enhancedbiomes")) {
            if (this.biome.biomeName.matches("Alpine Mountains")) {
                this.decorator = PMPDecorator.EB_ALPINEMOUNTAINS;
            } else if (this.biome.biomeName.matches("Alpine Mountains Edge")) {
                this.decorator = PMPDecorator.EB_ALPINEMOUNTAINSEDGE;
            } else if (this.biome.biomeName.matches("Alpine Mountains M")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_ALPINEMOUNTAINSM_EDGE : PMPDecorator.EB_ALPINEMOUNTAINSM;
            } else if (this.biome.biomeName.matches("Alpine Tundra")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_ALPINETUNDRA_EDGE : PMPDecorator.EB_ALPINETUNDRA;
            } else if (this.biome.biomeName.matches("Aspen Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_ASPENFOREST_EDGE : PMPDecorator.EB_ASPENFOREST;
            } else if (this.biome.biomeName.matches("Aspen Hills")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_ASPENHILLS_EDGE : PMPDecorator.EB_ASPENHILLS;
            } else if (this.biome.biomeName.matches("Badlands")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_BADLANDS_EDGE : PMPDecorator.EB_BADLANDS;
            } else if (this.biome.biomeName.matches("Basin")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_BASIN_EDGE : PMPDecorator.EB_BASIN;
            } else if (this.biome.biomeName.matches("Blossom Hills")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_BLOSSOMHILLS_EDGE : PMPDecorator.EB_BLOSSOMHILLS;
            } else if (this.biome.biomeName.matches("Blossom Woods")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_BLOSSOMWOODS_EDGE : PMPDecorator.EB_BLOSSOMWOODS;
            } else if (this.biome.biomeName.matches("Boreal Archipelago")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_BOREALARCHIPELAGO_EDGE : PMPDecorator.EB_BOREALARCHIPELAGO;
            } else if (this.biome.biomeName.matches("Boreal Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_BOREALFOREST_EDGE : PMPDecorator.EB_BOREALFOREST;
            } else if (this.biome.biomeName.matches("Boreal Plateau")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_BOREALPLATEAU_EDGE : PMPDecorator.EB_BOREALPLATEAU;
            } else if (this.biome.biomeName.matches("Boreal Plateau M")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_BOREALPLATEAUM_EDGE : PMPDecorator.EB_BOREALPLATEAUM;
            } else if (this.biome.biomeName.matches("Carr")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_CARR_EDGE : PMPDecorator.EB_CARR;
            } else if (this.biome.biomeName.matches("Clay Hills")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_CLAYHILLS_EDGE : PMPDecorator.EB_CLAYHILLS;
            } else if (this.biome.biomeName.matches("Clearing")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_CLEARING_EDGE : PMPDecorator.EB_CLEARING;
            } else if (this.biome.biomeName.matches("Cold Boreal Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_COLDBOREALFOREST_EDGE : PMPDecorator.EB_COLDBOREALFOREST;
            } else if (this.biome.biomeName.matches("Cold Cypress Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_COLDCYPRESSFOREST_EDGE : PMPDecorator.EB_COLDCYPRESSFOREST;
            } else if (this.biome.biomeName.matches("Cold Fir Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_COLDFIRFOREST_EDGE : PMPDecorator.EB_COLDFIRFOREST;
            } else if (this.biome.biomeName.matches("Cold Pine Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_COLDPINEFOREST_EDGE : PMPDecorator.EB_COLDPINEFOREST;
            } else if (this.biome.biomeName.matches("Creek Bed")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_CREEKBED_EDGE : PMPDecorator.EB_CREEKBED;
            } else if (this.biome.biomeName.matches("Cypress Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_CYPRESSFOREST_EDGE : PMPDecorator.EB_CYPRESSFOREST;
            } else if (this.biome.biomeName.matches("Desert Archipelago")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_DESERTARCHIPELAGO_EDGE : PMPDecorator.EB_DESERTARCHIPELAGO;
            } else if (this.biome.biomeName.matches("Ephemeral Lake")) {
                this.decorator = PMPDecorator.EB_EPHEMERALLAKE;
            } else if (this.biome.biomeName.matches("Ephemeral Lake Edge")) {
                this.decorator = PMPDecorator.EB_EPHEMERALLAKEEDGE;
            } else if (this.biome.biomeName.matches("Fens")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_FENS_EDGE : PMPDecorator.EB_FENS;
            } else if (this.biome.biomeName.matches("Fir Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_FIRFOREST_EDGE : PMPDecorator.EB_FIRFOREST;
            } else if (this.biome.biomeName.matches("Flowery Archipelago")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_FLOWERYARCHIPELAGO_EDGE
                    : PMPDecorator.EB_FLOWERYARCHIPELAGO;
            } else if (this.biome.biomeName.matches("Forested Archipelago")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_FORESTEDARCHIPELAGO_EDGE
                    : PMPDecorator.EB_FORESTEDARCHIPELAGO;
            } else if (this.biome.biomeName.matches("Forested Mountains")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_FORESTEDMOUNTAINS_EDGE : PMPDecorator.EB_FORESTEDMOUNTAINS;
            } else if (this.biome.biomeName.matches("Forested Valley")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_FORESTEDVALLEY_EDGE : PMPDecorator.EB_FORESTEDVALLEY;
            } else if (this.biome.biomeName.matches("Frozen Archipelago")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_FROZENARCHIPELAGO_EDGE : PMPDecorator.EB_FROZENARCHIPELAGO;
            } else if (this.biome.biomeName.matches("Glacier")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_GLACIER_EDGE : PMPDecorator.EB_GLACIER;
            } else if (this.biome.biomeName.matches("Grassy Archipelago")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_GRASSYARCHIPELAGO_EDGE : PMPDecorator.EB_GRASSYARCHIPELAGO;
            } else if (this.biome.biomeName.matches("Ice Sheet")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_ICESHEET_EDGE : PMPDecorator.EB_ICESHEET;
            } else if (this.biome.biomeName.matches("Kakadu")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_KAKADU_EDGE : PMPDecorator.EB_KAKADU;
            } else if (this.biome.biomeName.matches("Lake")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_LAKE_EDGE : PMPDecorator.EB_LAKE;
            } else if (this.biome.biomeName.matches("Low Hills")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_LOWHILLS_EDGE : PMPDecorator.EB_LOWHILLS;
            } else if (this.biome.biomeName.matches("Mangroves")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_MANGROVES_EDGE : PMPDecorator.EB_MANGROVES;
            } else if (this.biome.biomeName.matches("Marsh")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_MARSH_EDGE : PMPDecorator.EB_MARSH;
            } else if (this.biome.biomeName.matches("Meadow")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_MEADOW_EDGE : PMPDecorator.EB_MEADOW;
            } else if (this.biome.biomeName.matches("Meadow M")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_MEADOWM_EDGE : PMPDecorator.EB_MEADOWM;
            } else if (this.biome.biomeName.matches("Mountainous Archipelago")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_MOUNTAINOUSARCHIPELAGO_EDGE
                    : PMPDecorator.EB_MOUNTAINOUSARCHIPELAGO;
            } else if (this.biome.biomeName.matches("Mountains")) {
                this.decorator = PMPDecorator.EB_MOUNTAINS;
            } else if (this.biome.biomeName.matches("Mountains Edge")) {
                this.decorator = PMPDecorator.EB_MOUNTAINSEDGE;
            } else if (this.biome.biomeName.matches("Oak Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_OAKFOREST_EDGE : PMPDecorator.EB_OAKFOREST;
            } else if (this.biome.biomeName.matches("Oasis")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_OASIS_EDGE : PMPDecorator.EB_OASIS;
            } else if (this.biome.biomeName.matches("Pine Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_PINEFOREST_EDGE : PMPDecorator.EB_PINEFOREST;
            } else if (this.biome.biomeName.matches("Pine Forest Archipelago")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_PINEFORESTARCHIPELAGO_EDGE
                    : PMPDecorator.EB_PINEFORESTARCHIPELAGO;
            } else if (this.biome.biomeName.matches("Plateau")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_PLATEAU_EDGE : PMPDecorator.EB_PLATEAU;
            } else if (this.biome.biomeName.matches("Polar Desert")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_POLARDESERT_EDGE : PMPDecorator.EB_POLARDESERT;
            } else if (this.biome.biomeName.matches("Prairie")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_PRAIRIE_EDGE : PMPDecorator.EB_PRAIRIE;
            } else if (this.biome.biomeName.matches("Rainforest")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_RAINFOREST_EDGE : PMPDecorator.EB_RAINFOREST;
            } else if (this.biome.biomeName.matches("Rainforest Valley")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_RAINFORESTVALLEY_EDGE : PMPDecorator.EB_RAINFORESTVALLEY;
            } else if (this.biome.biomeName.matches("Red Desert")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_REDDESERT_EDGE : PMPDecorator.EB_REDDESERT;
            } else if (this.biome.biomeName.matches("Riparian Zone")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_RIPARIANZONE_EDGE : PMPDecorator.EB_RIPARIANZONE;
            } else if (this.biome.biomeName.matches("Rocky Desert")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_ROCKYDESERT_EDGE : PMPDecorator.EB_ROCKYDESERT;
            } else if (this.biome.biomeName.matches("Rocky Hills")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_ROCKYHILLS_EDGE : PMPDecorator.EB_ROCKYHILLS;
            } else if (this.biome.biomeName.matches("Roofed Shrublands")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_ROOFEDSHRUBLANDS_EDGE : PMPDecorator.EB_ROOFEDSHRUBLANDS;
            } else if (this.biome.biomeName.matches("Sahara")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SAHARA_EDGE : PMPDecorator.EB_SAHARA;
            } else if (this.biome.biomeName.matches("Sandstone Canyon")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SANDSTONECANYON_EDGE : PMPDecorator.EB_SANDSTONECANYON;
            } else if (this.biome.biomeName.matches("Sandstone Canyons")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SANDSTONECANYONS_EDGE : PMPDecorator.EB_SANDSTONECANYONS;
            } else if (this.biome.biomeName.matches("Sandstone Ranges")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SANDSTONERANGES_EDGE : PMPDecorator.EB_SANDSTONERANGES;
            } else if (this.biome.biomeName.matches("Sandstone Ranges M")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SANDSTONERANGESM_EDGE : PMPDecorator.EB_SANDSTONERANGESM;
            } else if (this.biome.biomeName.matches("Scree")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SCREE_EDGE : PMPDecorator.EB_SCREE;
            } else if (this.biome.biomeName.matches("Scrub")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SCRUB_EDGE : PMPDecorator.EB_SCRUB;
            } else if (this.biome.biomeName.matches("Shield")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SHIELD_EDGE : PMPDecorator.EB_SHIELD;
            } else if (this.biome.biomeName.matches("Shrublands")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SHRUBLANDS_EDGE : PMPDecorator.EB_SHRUBLANDS;
            } else if (this.biome.biomeName.matches("Silver Pine Forest")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SILVERPINEFOREST_EDGE : PMPDecorator.EB_SILVERPINEFOREST;
            } else if (this.biome.biomeName.matches("Silver Pine Hills")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SILVERPINEHILLS_EDGE : PMPDecorator.EB_SILVERPINEHILLS;
            } else if (this.biome.biomeName.matches("Snowy Desert")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SNOWYDESERT_EDGE : PMPDecorator.EB_SNOWYDESERT;
            } else if (this.biome.biomeName.matches("Snowy Plateau")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SNOWYPLATEAU_EDGE : PMPDecorator.EB_SNOWYPLATEAU;
            } else if (this.biome.biomeName.matches("Snowy Ranges")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SNOWYRANGES_EDGE : PMPDecorator.EB_SNOWYRANGES;
            } else if (this.biome.biomeName.matches("Snowy Wastelands")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_SNOWYWASTELANDS_EDGE : PMPDecorator.EB_SNOWYWASTELANDS;
            } else if (this.biome.biomeName.matches("Steppe")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_STEPPE_EDGE : PMPDecorator.EB_STEPPE;
            } else if (this.biome.biomeName.matches("Stone Canyon")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_STONECANYON_EDGE : PMPDecorator.EB_STONECANYON;
            } else if (this.biome.biomeName.matches("Stone Canyons")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_STONECANYONS_EDGE : PMPDecorator.EB_STONECANYONS;
            } else if (this.biome.biomeName.matches("Tropical Archipelago")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_TROPICALARCHIPELAGO_EDGE
                    : PMPDecorator.EB_TROPICALARCHIPELAGO;
            } else if (this.biome.biomeName.matches("Tundra")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_TUNDRA_EDGE : PMPDecorator.EB_TUNDRA;
            } else if (this.biome.biomeName.matches("Volcano")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_VOLCANO_EDGE : PMPDecorator.EB_VOLCANO;
            } else if (this.biome.biomeName.matches("Volcano M")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_VOLCANOM_EDGE : PMPDecorator.EB_VOLCANOM;
            } else if (this.biome.biomeName.matches("Wastelands")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_WASTELANDS_EDGE : PMPDecorator.EB_WASTELANDS;
            } else if (this.biome.biomeName.matches("Woodland Field")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_WOODLANDFIELD_EDGE : PMPDecorator.EB_WOODLANDFIELD;
            } else if (this.biome.biomeName.matches("Woodland Hills")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_WOODLANDHILLS_EDGE : PMPDecorator.EB_WOODLANDHILLS;
            } else if (this.biome.biomeName.matches("Woodland Lake")) {
                this.decorator = PMPDecorator.EB_WOODLANDLAKE;
            } else if (this.biome.biomeName.matches("Woodland Lake Edge")) {
                this.decorator = PMPDecorator.EB_WOODLANDLAKEEDGE;
            } else if (this.biome.biomeName.matches("Woodlands")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_WOODLANDS_EDGE : PMPDecorator.EB_WOODLANDS;
            } else if (this.biome.biomeName.matches("Xeric Savannah")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_XERICSAVANNAH_EDGE : PMPDecorator.EB_XERICSAVANNAH;
            } else if (this.biome.biomeName.matches("Xeric Shrubland")) {
                this.decorator = biomeEdge ? PMPDecorator.EB_XERICSHRUBLAND_EDGE : PMPDecorator.EB_XERICSHRUBLAND;
            }
        }

        if (Loader.isModLoaded("RWG")) {
            if (this.biome.biomeName.matches("rwg_hotDesert")) {
                this.decorator = PMPDecorator.RWG_DESERT_HOT;
            } else if (this.biome.biomeName.matches("rwg_snowDesert")) {
                this.decorator = PMPDecorator.RWG_DESERT_SNOW;
            } else if (this.biome.biomeName.matches("rwg_coldForest")) {
                this.decorator = PMPDecorator.RWG_FOREST_COLD;
            } else if (this.biome.biomeName.matches("rwg_hotForest")) {
                this.decorator = PMPDecorator.RWG_FOREST_HOT;
            } else if (this.biome.biomeName.matches("rwg_snowForest")) {
                this.decorator = PMPDecorator.RWG_FOREST_SNOW;
            } else if (this.biome.biomeName.matches("rwg_temperateForest")) {
                this.decorator = PMPDecorator.RWG_FOREST_TEMPERATE;
            } else if (this.biome.biomeName.matches("rwg_jungle")) {
                this.decorator = PMPDecorator.RWG_JUNGLE;
            } else if (this.biome.biomeName.matches("rwg_oasis")) {
                this.decorator = PMPDecorator.RWG_OASIS;
            } else if (this.biome.biomeName.matches("rwg_oceanCold")) {
                this.decorator = PMPDecorator.RWG_OCEAN_COLD;
            } else if (this.biome.biomeName.matches("rwg_oceanHot")) {
                this.decorator = PMPDecorator.RWG_OCEAN_HOT;
            } else if (this.biome.biomeName.matches("rwg_oceanIce")) {
                this.decorator = PMPDecorator.RWG_OCEAN_ICE;
            } else if (this.biome.biomeName.matches("rwg_oceanOasis")) {
                this.decorator = PMPDecorator.RWG_OCEAN_OASIS;
            } else if (this.biome.biomeName.matches("rwg_oceanTemperate")) {
                this.decorator = PMPDecorator.RWG_OCEAN_TEMPERATE;
            } else if (this.biome.biomeName.matches("rwg_oceanWet")) {
                this.decorator = PMPDecorator.RWG_OCEAN_WET;
            } else if (this.biome.biomeName.matches("rwg_plains")) {
                this.decorator = PMPDecorator.RWG_PLAINS;
            } else if (this.biome.biomeName.matches("rwg_coldPlains")) {
                this.decorator = PMPDecorator.RWG_PLAINS_COLD;
            } else if (this.biome.biomeName.matches("rwg_hotPlains")) {
                this.decorator = PMPDecorator.RWG_PLAINS_HOT;
            } else if (this.biome.biomeName.matches("rwg_redwood")) {
                this.decorator = PMPDecorator.RWG_REDWOOD;
            } else if (this.biome.biomeName.matches("rwg_riverCold")) {
                this.decorator = PMPDecorator.RWG_RIVER_COLD;
            } else if (this.biome.biomeName.matches("rwg_riverHot")) {
                this.decorator = PMPDecorator.RWG_RIVER_HOT;
            } else if (this.biome.biomeName.matches("rwg_riverIce")) {
                this.decorator = PMPDecorator.RWG_RIVER_ICE;
            } else if (this.biome.biomeName.matches("rwg_riverOasis")) {
                this.decorator = PMPDecorator.RWG_RIVER_OASIS;
            } else if (this.biome.biomeName.matches("rwg_riverTemperate")) {
                this.decorator = PMPDecorator.RWG_RIVER_TEMPERATE;
            } else if (this.biome.biomeName.matches("rwg_riverWet")) {
                this.decorator = PMPDecorator.RWG_RIVER_WET;
            } else if (this.biome.biomeName.matches("rwg_tropical")) {
                this.decorator = PMPDecorator.RWG_TROPICAL;
            }
        }

        if (this.decorator == null) {
            this.setDefaultDecorator(biomeEdge, tempCategory);
        }

        return true;
    }

    private void setDefaultDecorator(boolean biomeEdge, BiomeGenBase.TempCategory tempCategory) {
        if (BiomeDictionary.isBiomeOfType(this.biome, Type.BEACH)) {
            if (tempCategory == TempCategory.COLD) {
                this.decorator = PMPDecorator.BEACH_COLD;
            } else {
                this.decorator = PMPDecorator.BEACH_OCEAN;
            }
        } else if (BiomeDictionary.isBiomeOfType(this.biome, Type.SANDY)) {
            this.decorator = biomeEdge ? PMPDecorator.DESERT_EDGE : PMPDecorator.DESERT;
        } else if (BiomeDictionary.isBiomeOfType(this.biome, Type.END)) {
            this.decorator = PMPDecorator.END;
        } else if (BiomeDictionary.isBiomeOfType(this.biome, Type.FOREST)) {
            if (tempCategory == TempCategory.COLD) {
                this.decorator = biomeEdge ? PMPDecorator.TAIGA_EDGE : PMPDecorator.TAIGA;
            } else if (tempCategory == TempCategory.WARM) {
                this.decorator = biomeEdge ? PMPDecorator.JUNGLE_EDGE : PMPDecorator.JUNGLE;
            } else {
                this.decorator = biomeEdge ? PMPDecorator.FOREST_EDGE : PMPDecorator.FOREST;
            }
        } else if (BiomeDictionary.isBiomeOfType(this.biome, Type.COLD)) {
            this.decorator = biomeEdge ? PMPDecorator.ICE_PLAINS_EDGE : PMPDecorator.ICE_PLAINS;
        } else if (BiomeDictionary.isBiomeOfType(this.biome, Type.HILLS)) {
            this.decorator = PMPDecorator.PLAINS;
        } else if (BiomeDictionary.isBiomeOfType(this.biome, Type.JUNGLE)) {
            this.decorator = biomeEdge ? PMPDecorator.JUNGLE_EDGE : PMPDecorator.JUNGLE;
        } else if (BiomeDictionary.isBiomeOfType(this.biome, Type.MAGICAL)) {
            this.decorator = biomeEdge ? PMPDecorator.MAGICAL_EDGE : PMPDecorator.MAGICAL;
        } else if (BiomeDictionary.isBiomeOfType(this.biome, Type.MOUNTAIN)) {
            this.decorator = biomeEdge ? PMPDecorator.EXTREME_HILLS_EDGE : PMPDecorator.EXTREME_HILLS;
        } else if (BiomeDictionary.isBiomeOfType(this.biome, Type.MUSHROOM)) {
            this.decorator = PMPDecorator.MUSHROOM;
        } else if (BiomeDictionary.isBiomeOfType(this.biome, Type.NETHER)) {
            this.decorator = PMPDecorator.NETHER;
        } else if (BiomeDictionary.isBiomeOfType(this.biome, Type.PLAINS)) {
            this.decorator = biomeEdge ? PMPDecorator.PLAINS_EDGE : PMPDecorator.PLAINS;
        } else if (BiomeDictionary.isBiomeOfType(this.biome, Type.SWAMP)) {
            this.decorator = biomeEdge ? PMPDecorator.SWAMP_EDGE : PMPDecorator.SWAMP;
        } else if (BiomeDictionary.isBiomeOfType(this.biome, Type.WASTELAND)) {
            this.decorator = biomeEdge ? PMPDecorator.WASTELAND_EDGE : PMPDecorator.WASTELAND;
        } else if (BiomeDictionary.isBiomeOfType(this.biome, Type.WATER)) {
            this.decorator = PMPDecorator.OCEAN;
        } else {
            this.decorator = PMPDecorator.PLAINS;
        }

    }

    public void getBlockInfo(World world) {
        this.blockAbove = world.getBlock(this.x, this.y + 1, this.z);
        this.blockBelow = world.getBlock(this.x, this.y - 1, this.z);
        this.blockSpawn = world.getBlock(this.x, this.y, this.z);
    }

    private boolean checkForVillagePlant(World world) {
        int ix = this.x;
        int iy = this.y - 1;
        int iz = this.z;
        Block block = world.getBlock(ix, iy, iz);
        int direction;
        if (block == Blocks.stone_slab) {
            --iy;
            direction = PMPGenBase.isAdjacentToAirBlock(world, ix, iy, iz);
            if (direction > 0) {
                switch (direction) {
                    case 2:
                        ++iz;
                        break;
                    case 3:
                        --iz;
                        break;
                    case 4:
                        ++ix;
                        break;
                    case 5:
                        --ix;
                }

                this.x = ix;
                this.y = iy;
                this.z = iz;
                this.getLocationInfo(world);
                this.spawnDirection = direction;
                return true;
            }
        }

        direction = PMPGenBase.isStairBlock(world, ix, iy, iz);
        if (direction == 0) {
            return false;
        } else {
            do {
                --iy;
                block = world.getBlock(ix, iy, iz);
                if (block.getMaterial() == Material.air) {
                    this.x = ix;
                    this.y = iy;
                    this.z = iz;
                    this.getLocationInfo(world);
                    this.spawnDirection = direction;
                    return true;
                }

                switch (direction) {
                    case 2:
                        ++iz;
                        break;
                    case 3:
                        --iz;
                        break;
                    case 4:
                        ++ix;
                        break;
                    case 5:
                        --ix;
                }
            } while (PMPGenBase.isStairBlock(world, ix, iy, iz) > 0);

            return false;
        }
    }

    private boolean adjustLocationForLeaf(World world, Random random) {
        int direction = 0;
        int heightTop = 0;
        int heightBottom = 0;
        int logBlockDir = 0;
        this.decoratorArea = null;
        boolean canContinue = true;

        int airBlockDir;
        do {
            --this.y;
            this.getBlockInfo(world);
            if (PMPGenBase.isLeafBlock(this.blockSpawn) && this.blockBelow.getMaterial() == Material.air
                && world.getBlock(this.x, this.y - 2, this.z)
                    .getMaterial() == Material.air) {
                airBlockDir = PMPGenBase.isAdjacentToAirOrVineBlock(world, this.x, this.y, this.z);
                if (airBlockDir > 0) {
                    this.moveSpawnLocationToDirection(airBlockDir, 1);
                    this.spawnDirection = airBlockDir;
                    this.decoratorArea = PMPDecoratorArea.VINE;
                    this.getBlockInfo(world);
                    return true;
                }
            }

            if (!PMPGenBase.canReplaceBlockWithPlant(world, this.x, this.y, this.z, false)) {
                direction = 0;
                heightTop = 0;
                heightBottom = 0;
                this.decoratorArea = null;
                logBlockDir = 0;
            } else {
                if (direction == 0) {
                    logBlockDir = this.isAdjacentToLogBlock(world, random, true);
                    if (logBlockDir > 5) {
                        logBlockDir = this.adjustLocationForDiagonalHostTree(random, logBlockDir);
                        this.getBlockInfo(world);
                    }
                } else {
                    logBlockDir = this.isAdjacentToLogBlock(world, random, false);
                }

                if (logBlockDir > 0) {
                    if (direction == 0) {
                        direction = logBlockDir;
                        heightTop = this.y;
                        heightBottom = this.y;
                        this.decoratorArea = PMPDecoratorArea.EPIPHYTE;
                    } else {
                        if (logBlockDir != direction) {
                            ++this.y;
                            heightBottom = this.y;
                            this.getBlockInfo(world);
                            break;
                        }

                        heightBottom = this.y;
                    }
                }
            }

            canContinue = this.y > 1 && !PMPGenBase.isGroundBlock(this.blockBelow)
                && this.blockBelow.getMaterial() != Material.water
                && this.blockBelow.getMaterial() != Material.lava
                && this.blockBelow.getMaterial() != Material.fire;
        } while (canContinue);

        if (PMPGenBase.isLogBlock(this.blockSpawn)) {
            airBlockDir = PMPGenBase.isAdjacentToPlantSpawnBlock(world, this.x, this.y, this.z);
            if (airBlockDir > 0) {
                this.moveSpawnLocationToDirection(airBlockDir, 1);
                this.getBlockInfo(world);
                direction = 0;
                heightTop = 0;
                heightBottom = 0;
                this.decoratorArea = null;
            }
        }

        if (this.decoratorArea == PMPDecoratorArea.EPIPHYTE && heightBottom > 1 && heightTop > 1) {
            this.spawnDirection = PMPGenBase.getOppositeDirection(direction);
            this.y = this.obtainEpiphyteHeight(world, random, heightBottom, heightTop);
            this.getBlockInfo(world);
            return true;
        } else {
            if (PMPGenBase.canReplaceBlockWithPlant(world, this.x, this.y, this.z, true)) {
                if (this.blockBelow.getMaterial() == Material.water) {
                    this.decoratorArea = PMPDecoratorArea.WATER_FLOATING;
                    return true;
                }

                if (PMPGenBase.isGroundBlock(this.blockBelow)) {
                    if (PMPGenBase.isAdjacentToBlockMaterial(world, this.x, this.y - 1, this.z, Material.water) > 0) {
                        this.decoratorArea = PMPDecoratorArea.WATER_EDGE;
                    } else {
                        this.decoratorArea = PMPDecoratorArea.SHADE;
                    }

                    return true;
                }
            }

            return false;
        }
    }

    public boolean moveSpawnLocationToDirection(int direction, int distance) {
        if (direction >= 2 && direction <= 9 && distance >= 1 && distance <= 32767) {
            for (int count = 0; count < distance; ++count) {
                switch (direction) {
                    case 2:
                        --this.z;
                        break;
                    case 3:
                        ++this.z;
                        break;
                    case 4:
                        --this.x;
                        break;
                    case 5:
                        ++this.x;
                        break;
                    case 6:
                        --this.x;
                        --this.z;
                        break;
                    case 7:
                        ++this.x;
                        --this.z;
                        break;
                    case 8:
                        --this.x;
                        ++this.z;
                        break;
                    case 9:
                        ++this.x;
                        ++this.z;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private int obtainEpiphyteHeight(World world, Random random, int minHeight, int maxHeight) {
        return minHeight >= maxHeight ? minHeight : minHeight + random.nextInt(maxHeight - minHeight + 1);
    }

    private int isAdjacentToLogBlock(World world, Random random, boolean includeDiagonals) {
        if (PMPGenBase.isLogBlock(world, this.x, this.y, this.z - 1)) {
            return 2;
        } else if (PMPGenBase.isLogBlock(world, this.x - 1, this.y, this.z)) {
            return 4;
        } else if (PMPGenBase.isLogBlock(world, this.x + 1, this.y, this.z)) {
            return 5;
        } else if (PMPGenBase.isLogBlock(world, this.x, this.y, this.z + 1)) {
            return 3;
        } else {
            if (includeDiagonals) {
                if (PMPGenBase.isLogBlock(world, this.x - 1, this.y, this.z - 1)) {
                    return 6;
                }

                if (PMPGenBase.isLogBlock(world, this.x + 1, this.y, this.z - 1)) {
                    return 7;
                }

                if (PMPGenBase.isLogBlock(world, this.x - 1, this.y, this.z + 1)) {
                    return 8;
                }

                if (PMPGenBase.isLogBlock(world, this.x + 1, this.y, this.z + 1)) {
                    return 9;
                }
            }

            return 0;
        }
    }

    private int adjustLocationForDiagonalHostTree(Random random, int direction) {
        if (direction >= 6 && direction <= 9) {
            int distribution = random.nextInt(100);
            int newDirection = 0;
            switch (direction) {
                case 6:
                    if (distribution < 50) {
                        --this.x;
                        newDirection = 2;
                    } else {
                        --this.z;
                        newDirection = 4;
                    }
                    break;
                case 7:
                    if (distribution < 50) {
                        ++this.x;
                        newDirection = 2;
                    } else {
                        --this.z;
                        newDirection = 5;
                    }
                    break;
                case 8:
                    if (distribution < 50) {
                        ++this.z;
                        newDirection = 4;
                    } else {
                        --this.x;
                        newDirection = 3;
                    }
                    break;
                case 9:
                    if (distribution < 50) {
                        ++this.z;
                        newDirection = 5;
                    } else {
                        ++this.x;
                        newDirection = 3;
                    }
            }

            return newDirection;
        } else {
            return 0;
        }
    }

    public boolean checkForWaterPlant(World world) {
        Block block = world.getBlock(this.x, this.y - 1, this.z);
        if (block.getMaterial() == Material.water && world.getBlockMetadata(this.x, this.y - 1, this.z) <= 0) {
            block = world.getBlock(this.x, this.y - 2, this.z);
            if (block.getMaterial() != Material.water) {
                if (PMPGenBase.isSurroundedByBlock(world, this.x, this.y - 1, this.z, Blocks.water)) {
                    this.decoratorArea = PMPDecoratorArea.WATER_FLOATING;
                } else {
                    this.decoratorArea = PMPDecoratorArea.WATER_IMMERSED;
                    this.getBlockInfo(world);
                }

                return true;
            } else {
                this.adjustLocationForWaterBlock(world);
                this.getBlockInfo(world);
                this.decoratorArea = PMPDecoratorArea.WATER_SUBMERGED;
                return true;
            }
        } else {
            return false;
        }
    }

    public void adjustLocationForWaterBlock(World world) {
        int startHeight;
        for (startHeight = this.y; this.blockBelow.getMaterial()
            == Material.water; this.blockBelow = world.getBlock(this.x, this.y - 1, this.z)) {
            --this.y;
        }

        this.getBlockInfo(world);
        this.waterBlockDepth = startHeight - this.y;
    }
}
