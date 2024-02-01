package plantmegapack.bin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockClay;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

import cpw.mods.fml.client.FMLClientHandler;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPPlantSoilType;

public class PMPSoilBlocks {

    private File optionsFile;
    private ArrayList<String> listAquatic = new ArrayList();
    private ArrayList<String> listClay = new ArrayList();
    private ArrayList<String> listCrop = new ArrayList();
    private ArrayList<String> listDirt = new ArrayList();
    private ArrayList<String> listGravel = new ArrayList();
    private ArrayList<String> listNether = new ArrayList();
    private ArrayList<String> listSand = new ArrayList();
    private ArrayList<String> listStone = new ArrayList();
    private ArrayList<String> listWood = new ArrayList();

    public PMPSoilBlocks(String configPath) {
        this.optionsFile = new File(configPath + "soilblocks.cfg");
        if (!this.loadConfiguration()) {
            this.resetDefaults();
        }

    }

    public void addSoilBlock(PMPPlantSoilType soilType, String blockName) {
        switch (soilType) {
            case AQUATIC:
                this.listAquatic.add(blockName);
                this.sortList(PMPPlantSoilType.AQUATIC);
                break;
            case CLAY:
                this.listClay.add(blockName);
                this.sortList(PMPPlantSoilType.CLAY);
                break;
            case CROP:
                this.listCrop.add(blockName);
                this.sortList(PMPPlantSoilType.CROP);
                break;
            case DIRT:
                this.listDirt.add(blockName);
                this.sortList(PMPPlantSoilType.DIRT);
                break;
            case GRAVEL:
                this.listGravel.add(blockName);
                this.sortList(PMPPlantSoilType.GRAVEL);
                break;
            case NETHER:
                this.listNether.add(blockName);
                this.sortList(PMPPlantSoilType.NETHER);
                break;
            case SAND:
                this.listSand.add(blockName);
                this.sortList(PMPPlantSoilType.SAND);
                break;
            case STONE:
                this.listStone.add(blockName);
                this.sortList(PMPPlantSoilType.STONE);
                break;
            case WOOD:
                this.listWood.add(blockName);
                this.sortList(PMPPlantSoilType.WOOD);
        }

    }

    public ArrayList<String> getSoilBlocksList(PMPPlantSoilType soilType) {
        switch (soilType) {
            case AQUATIC:
                return this.listAquatic;
            case CLAY:
                return this.listClay;
            case CROP:
                return this.listCrop;
            case DIRT:
                return this.listDirt;
            case GRAVEL:
                return this.listGravel;
            case NETHER:
                return this.listNether;
            case SAND:
                return this.listSand;
            case STONE:
                return this.listStone;
            case WOOD:
                return this.listWood;
            default:
                return null;
        }
    }

    public void resetDefaults() {
        this.resetList(PMPPlantSoilType.AQUATIC);
        this.resetList(PMPPlantSoilType.CLAY);
        this.resetList(PMPPlantSoilType.CROP);
        this.resetList(PMPPlantSoilType.DIRT);
        this.resetList(PMPPlantSoilType.GRAVEL);
        this.resetList(PMPPlantSoilType.NETHER);
        this.resetList(PMPPlantSoilType.SAND);
        this.resetList(PMPPlantSoilType.STONE);
        this.resetList(PMPPlantSoilType.WOOD);
    }

    public void resetList(PMPPlantSoilType soilType) {
        ArrayList<String> list = this.getSoilBlocksList(soilType);
        if (list != null) {
            list.clear();
            switch (soilType) {
                case AQUATIC:
                case CLAY:
                case CROP:
                case DIRT:
                case GRAVEL:
                case NETHER:
                case SAND:
                case STONE:
                case WOOD:
            }
        }

    }

    private void sortList(PMPPlantSoilType soilType) {
        switch (soilType) {
            case AQUATIC:
                Collections.sort(this.listAquatic);
                break;
            case CLAY:
                Collections.sort(this.listClay);
                break;
            case CROP:
                Collections.sort(this.listCrop);
                break;
            case DIRT:
                Collections.sort(this.listDirt);
                break;
            case GRAVEL:
                Collections.sort(this.listGravel);
                break;
            case NETHER:
                Collections.sort(this.listNether);
                break;
            case SAND:
                Collections.sort(this.listSand);
                break;
            case STONE:
                Collections.sort(this.listStone);
                break;
            case WOOD:
                Collections.sort(this.listWood);
        }

    }

    public boolean loadConfiguration() {
        try {
            if (!this.optionsFile.exists()) {
                this.resetDefaults();
                return this.saveFile();
            }

            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.optionsFile));
            String line = "";

            while (true) {
                if ((line = bufferedreader.readLine()) == null) {
                    bufferedreader.close();
                    break;
                }

                try {
                    if (line.length() > 0 && !line.startsWith("#")) {
                        String[] astring = line.split(":");
                        if (astring[0].equals("aquatic")) {
                            this.listAquatic.add(astring[1]);
                        } else if (astring[0].equals("clay")) {
                            this.listClay.add(astring[1]);
                        } else if (astring[0].equals("crop")) {
                            this.listCrop.add(astring[1]);
                        } else if (astring[0].equals("dirt")) {
                            this.listDirt.add(astring[1]);
                        } else if (astring[0].equals("gravel")) {
                            this.listGravel.add(astring[1]);
                        } else if (astring[0].equals("nether")) {
                            this.listNether.add(astring[1]);
                        } else if (astring[0].equals("sand")) {
                            this.listSand.add(astring[1]);
                        } else if (astring[0].equals("stone")) {
                            this.listStone.add(astring[1]);
                        } else if (astring[0].equals("wood")) {
                            this.listWood.add(astring[1]);
                        }
                    }
                } catch (Exception var4) {
                    PlantMegaPack.instance.logOutput("Skipping bad entry");
                }
            }
        } catch (Exception var5) {
            PlantMegaPack.instance.logOutput("Failed to load config file: soilblocks.cfg");
            return false;
        }

        PlantMegaPack.instance.logOutput("Config file loaded: soilblocks.cfg");
        return true;
    }

    public boolean saveConfiguration() {
        return FMLClientHandler.instance()
            .isLoading() ? false : this.saveFile();
    }

    private boolean saveFile() {
        try {
            this.sortList(PMPPlantSoilType.AQUATIC);
            this.sortList(PMPPlantSoilType.CLAY);
            this.sortList(PMPPlantSoilType.CROP);
            this.sortList(PMPPlantSoilType.DIRT);
            this.sortList(PMPPlantSoilType.GRAVEL);
            this.sortList(PMPPlantSoilType.NETHER);
            this.sortList(PMPPlantSoilType.SAND);
            this.sortList(PMPPlantSoilType.STONE);
            this.sortList(PMPPlantSoilType.WOOD);
            if (!this.optionsFile.exists()) {
                this.optionsFile.createNewFile();
                this.optionsFile.setWritable(true);
                PlantMegaPack.instance.logOutput("Create new config file: soilblocks.cfg");
            }

            PrintWriter printwriter = new PrintWriter(new FileWriter(this.optionsFile));
            Iterator var2 = this.listAquatic.iterator();

            String name;
            while (var2.hasNext()) {
                name = (String) var2.next();
                printwriter.println("aquatic:" + name);
            }

            var2 = this.listClay.iterator();

            while (var2.hasNext()) {
                name = (String) var2.next();
                printwriter.println("clay:" + name);
            }

            var2 = this.listCrop.iterator();

            while (var2.hasNext()) {
                name = (String) var2.next();
                printwriter.println("crop:" + name);
            }

            var2 = this.listDirt.iterator();

            while (var2.hasNext()) {
                name = (String) var2.next();
                printwriter.println("dirt:" + name);
            }

            var2 = this.listGravel.iterator();

            while (var2.hasNext()) {
                name = (String) var2.next();
                printwriter.println("gravel:" + name);
            }

            var2 = this.listNether.iterator();

            while (var2.hasNext()) {
                name = (String) var2.next();
                printwriter.println("nether:" + name);
            }

            var2 = this.listSand.iterator();

            while (var2.hasNext()) {
                name = (String) var2.next();
                printwriter.println("sand:" + name);
            }

            var2 = this.listStone.iterator();

            while (true) {
                if (!var2.hasNext()) {
                    var2 = this.listWood.iterator();

                    while (var2.hasNext()) {
                        name = (String) var2.next();
                        printwriter.println("wood:" + name);
                    }

                    printwriter.close();
                    break;
                }

                name = (String) var2.next();
                printwriter.println("stone:" + name);
            }
        } catch (Exception var4) {
            PlantMegaPack.instance.logOutput("Failed to save config file: soilblocks.cfg");
            return false;
        }

        PlantMegaPack.instance.logOutput("Config file saved: soilblocks.cfg");
        return true;
    }

    public boolean canPlantOnThisBlock(PMPPlantSoilType soilType, Block block) {
        switch (soilType) {
            case AQUATIC:
                if (block == Blocks.clay || block instanceof BlockClay
                    || block.getMaterial() == Material.clay
                    || block == Blocks.dirt
                    || block instanceof BlockDirt
                    || block == Blocks.gravel
                    || block instanceof BlockGravel
                    || block == Blocks.sand
                    || block instanceof BlockSand
                    || block.getMaterial() == Material.sand) {
                    return true;
                }
                break;
            case CLAY:
                if (block == Blocks.clay || block instanceof BlockClay
                    || block.getMaterial() == Material.clay
                    || this.isJaquadroBlock(block)) {
                    return true;
                }
                break;
            case CROP:
                return block == Blocks.farmland || block instanceof BlockFarmland || this.isJaquadroBlock(block);
            case DIRT:
                if (block == Blocks.dirt || block instanceof BlockDirt
                    || block == Blocks.farmland
                    || block instanceof BlockFarmland
                    || this.isGrassBlock(block)
                    || block == Blocks.mycelium
                    || block instanceof BlockMycelium
                    || this.isJaquadroBlock(block)) {
                    return true;
                }
                break;
            case GRAVEL:
                if (block == Blocks.gravel || block instanceof BlockGravel || this.isJaquadroBlock(block)) {
                    return true;
                }
                break;
            case NETHER:
                if (block == Blocks.netherrack || block instanceof BlockNetherrack
                    || block == Blocks.soul_sand
                    || block instanceof BlockSoulSand) {
                    return true;
                }
                break;
            case SAND:
                if (block == Blocks.sand || block instanceof BlockSand
                    || block.getMaterial() == Material.sand
                    || this.isJaquadroBlock(block)) {
                    return true;
                }
                break;
            case STONE:
                if (block == Blocks.end_stone || block == Blocks.mossy_cobblestone
                    || block == Blocks.stone
                    || block instanceof BlockStone
                    || block.getMaterial() == Material.rock
                    || this.isJaquadroBlock(block)) {
                    return true;
                }
                break;
            case WOOD:
                return block == Blocks.log || block == Blocks.log2
                    || block instanceof BlockLog
                    || block.getMaterial() == Material.wood;
            case BEACH:
                if (block == Blocks.dirt || block instanceof BlockDirt
                    || block == Blocks.gravel
                    || block instanceof BlockGravel
                    || block == Blocks.sand
                    || block instanceof BlockSand
                    || block.getMaterial() == Material.sand) {
                    return true;
                }
        }

        return block.getMaterial() == Material.ground;
    }

    public boolean isGrassBlock(Block block) {
        return block == Blocks.grass || block instanceof BlockGrass || block.getMaterial() == Material.grass;
    }

    private boolean isJaquadroBlock(Block block) {
        String blockName = block.getUnlocalizedName()
            .substring(5);
        return blockName.matches("garden_soil") || blockName.matches("garden_farmland")
            || blockName.matches("largePot")
            || blockName.matches("largePotColored");
    }
}
