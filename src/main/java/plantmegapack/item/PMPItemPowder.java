package plantmegapack.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockPlant;
import plantmegapack.common.PMPPlantCategory;
import plantmegapack.common.PMPPlantGrowthType;
import plantmegapack.common.PMPPlantPowder;
import plantmegapack.common.PMPPlantRenderType;
import plantmegapack.common.PMPPlantSoilType;
import plantmegapack.common.PMPTab;

public class PMPItemPowder extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon powderIcon;
    private PMPPlantPowder powder;

    public PMPItemPowder(PMPPlantPowder powder) {
        this.powder = powder;
        this.setUnlocalizedName(this.powder.unlocalizedName);
        GameRegistry.registerItem(this, this.powder.unlocalizedName);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
        if (this.powder.unlocalizedName.matches("powderAloe")) {
            this.setPotionEffect(PotionHelper.speckledMelonEffect);
        } else if (this.powder.unlocalizedName.matches("powderCoral")) {
            this.setPotionEffect(PotionHelper.field_151423_m);
        } else if (this.powder.unlocalizedName.matches("powderCactus")) {
            this.setPotionEffect(PotionHelper.blazePowderEffect);
        }

    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.powderIcon = ir.registerIcon(
            "plantmegapack:" + this.getUnlocalizedName()
                .substring(5));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return this.powderIcon;
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side,
        float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (world.getBlock(x, y + 1, z)
            .getMaterial() == Material.water) {
            return false;
        } else {
            Block block = world.getBlock(x, y, z);
            String unlocalizedName = this.getUnlocalizedName()
                .substring(5);
            if (unlocalizedName.matches("powderConditioner")) {
                if (block.getMaterial() == Material.plants || block instanceof BlockBush) {
                    return false;
                }

                if (side == 1) {
                    this.applyConditioner(world, x, y, z);
                }
            } else if (unlocalizedName.matches("powderDefoliant")) {
                if (block.getMaterial() != Material.plants && !(block instanceof BlockBush)) {
                    if (side == 1) {
                        this.applyDefoliant(world, x, y, z);
                    }
                } else {
                    this.applyDefoliantToPlant(world, x, y, z);
                }
            } else if (unlocalizedName.matches("powderFertilizer")) {
                if (block.getMaterial() != Material.plants && !(block instanceof BlockBush)
                    && !(block instanceof BlockCactus)
                    && !(block instanceof BlockReed)) {
                    if (side == 1) {
                        this.applyFertilizer(world, x, y, z);
                    }
                } else {
                    this.applyFertilizerToPlant(world, x, y, z);
                }
            }

            --itemStack.stackSize;
            return true;
        }
    }

    private void applyConditioner(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        if (PlantMegaPack.soilBlocks.isGrassBlock(block) || block instanceof BlockDirt || block == Blocks.farmland) {
            int radius = PlantMegaPack.settingsGeneral.powderConditionerRadius;
            int lowX = x - radius;
            int lowZ = z - radius;
            int highX = x + radius;
            int highZ = z + radius;

            for (int ix = lowX; ix <= highX; ++ix) {
                for (int iz = lowZ; iz <= highZ; ++iz) {
                    if ((ix != lowX && ix != highX || iz != lowZ && iz != highZ)
                        && world.rand.nextInt(100) < PlantMegaPack.settingsGeneral.powderConditionerStrength) {
                        block = world.getBlock(ix, y, iz);
                        if (PlantMegaPack.soilBlocks.isGrassBlock(block) || block instanceof BlockDirt
                            || block == Blocks.farmland) {
                            world.setBlock(ix, y, iz, Blocks.farmland, 0, 3);
                            this.spawnFertilizerParticles(world, ix, y, iz, block, true);
                        }
                    }
                }
            }

        }
    }

    private void applyFertilizer(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        if (block instanceof BlockDirt || PlantMegaPack.soilBlocks.isGrassBlock(block)
            || block instanceof BlockMycelium
            || block instanceof BlockFarmland
            || block == Blocks.cobblestone) {
            int radius = PlantMegaPack.settingsGeneral.powderFertilizerRadius;
            int lowX = x - radius;
            int lowZ = z - radius;
            int highX = x + radius;
            int highZ = z + radius;

            for (int ix = lowX; ix <= highX; ++ix) {
                for (int iz = lowZ; iz <= highZ; ++iz) {
                    if ((ix != lowX && ix != highX || iz != lowZ && iz != highZ)
                        && world.rand.nextInt(100) < PlantMegaPack.settingsGeneral.powderFertilizerStrength) {
                        block = world.getBlock(ix, y, iz);
                        if (block instanceof BlockDirt) {
                            world.setBlock(ix, y, iz, Blocks.grass, 0, 3);
                            this.spawnFertilizerParticles(world, ix, y, iz, block, true);
                        } else if (block instanceof BlockMycelium) {
                            world.setBlock(ix, y, iz, Blocks.grass, 0, 3);
                            this.spawnFertilizerParticles(world, ix, y, iz, block, true);
                        } else if (block == Blocks.cobblestone) {
                            world.setBlock(ix, y, iz, Blocks.mossy_cobblestone, 0, 3);
                            this.spawnFertilizerParticles(world, ix, y, iz, block, true);
                        }
                    }
                }
            }

        }
    }

    private void applyFertilizerToPlant(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        int metaData = world.getBlockMetadata(x, y, z);
        if (block instanceof PMPBlockPlant) {
            PMPBlockPlant plantBlock = (PMPBlockPlant) block;
            if ((plantBlock.plantData.attributes.growthType != PMPPlantGrowthType.DOUBLE
                || metaData != 3 && metaData != 5 && metaData != 7) && !plantBlock.isImmersedPlant()) {
                if (plantBlock.plantData.attributes.category != PMPPlantCategory.BAMBOO
                    && plantBlock.plantData.attributes.category != PMPPlantCategory.CACTUS
                    && plantBlock.plantData.attributes.category != PMPPlantCategory.VINE
                    && plantBlock.plantData.attributes.soilType != PMPPlantSoilType.AQUATIC
                    && plantBlock.plantData.attributes.category != PMPPlantCategory.EPIPHYTE) {
                    if (plantBlock.hasFlowerColors()) {
                        this.spawnPlantRadius(world, x, y, z, plantBlock, metaData, false);
                    } else {
                        this.spawnPlantRadius(world, x, y, z, plantBlock, 0, false);
                    }
                }
            } else {
                this.spawnPlantRadius(world, x, y - (plantBlock.isImmersedPlant() ? 0 : 1), z, plantBlock, 0, false);
            }
        } else if (block instanceof BlockSapling) {
            ((BlockSapling) block).func_149878_d(world, x, x, z, world.rand);
        } else if (block instanceof BlockBush || block instanceof BlockFlower || block instanceof BlockDoublePlant) {
            if (metaData == 8) {
                this.spawnPlantRadius(world, x, y - 1, z, block, world.getBlockMetadata(x, y - 1, z), false);
            } else {
                this.spawnPlantRadius(world, x, y, z, block, metaData, false);
            }
        }

    }

    private void spawnPlantRadius(World world, int x, int y, int z, Block blockPlant, int metaData,
        boolean overrideSoilCheck) {
        int radius = PlantMegaPack.settingsGeneral.powderFertilizerRadius;
        int iy = y;
        int lowX = x - radius;
        int lowZ = z - radius;
        int highX = x + radius;
        int highZ = z + radius;

        for (int ix = lowX; ix <= highX; ++ix) {
            for (int iz = lowZ; iz <= highZ; ++iz) {
                while (world.isAirBlock(ix, iy - 1, iz)) {
                    --iy;
                }

                if ((ix != lowX && ix != highX || iz != lowZ && iz != highZ)
                    && world.rand.nextInt(100) < PlantMegaPack.settingsGeneral.powderFertilizerStrength
                    && world.isAirBlock(ix, iy, iz)
                    && (blockPlant.canBlockStay(world, ix, iy, iz) || overrideSoilCheck)) {
                    if (blockPlant instanceof PMPBlockPlant) {
                        if (((PMPBlockPlant) blockPlant).hasFlowerColors()) {
                            world.setBlock(ix, iy, iz, blockPlant, metaData, 3);
                            this.spawnFertilizerParticles(world, ix, iy, iz, blockPlant, false);
                        } else if (((PMPBlockPlant) blockPlant).plantData.attributes.renderType
                            == PMPPlantRenderType.FLAT) {
                                world.setBlock(ix, iy, iz, blockPlant, world.rand.nextInt(8), 3);
                                this.spawnFertilizerParticles(world, ix, iy, iz, blockPlant, false);
                            } else {
                                world.setBlock(ix, iy, iz, blockPlant, 0, 3);
                                this.spawnFertilizerParticles(world, ix, iy, iz, blockPlant, false);
                            }
                    } else {
                        world.setBlock(ix, iy, iz, blockPlant, metaData, 3);
                        this.spawnFertilizerParticles(world, ix, iy, iz, blockPlant, false);
                        if (blockPlant == Blocks.double_plant) {
                            world.setBlock(ix, iy + 1, iz, blockPlant, 8, 2);
                            this.spawnFertilizerParticles(world, ix, iy + 1, iz, blockPlant, false);
                        }
                    }
                }

                iy = y;
            }
        }

    }

    private void applyDefoliant(World world, int x, int y, int z) {
        world.getBlock(x, y, z);
        int radius = PlantMegaPack.settingsGeneral.powderDefoliantRadius;
        int lowX = x - radius;
        int lowZ = z - radius;
        int highX = x + radius;
        int highZ = z + radius;

        for (int ix = lowX; ix <= highX; ++ix) {
            for (int iz = lowZ; iz <= highZ; ++iz) {
                if ((ix != lowX && ix != highX || iz != lowZ && iz != highZ)
                    && world.rand.nextInt(100) < PlantMegaPack.settingsGeneral.powderDefoliantStrength) {
                    Block block = world.getBlock(ix, y, iz);
                    if (PlantMegaPack.soilBlocks.isGrassBlock(block)) {
                        world.setBlock(ix, y, iz, Blocks.dirt, 0, 3);
                        this.spawnDefoliantParticles(world, x, y, z, true);
                        block = world.getBlock(ix, y + 1, iz);
                        if (block.getMaterial() == Material.plants || block instanceof BlockBush) {
                            this.applyDefoliantToPlant(world, ix, y + 1, iz);
                        }
                    } else if (block == Blocks.farmland) {
                        world.setBlock(ix, y, iz, Blocks.dirt, 1, 3);
                        this.spawnDefoliantParticles(world, x, y, z, true);
                        block = world.getBlock(ix, y + 1, iz);
                        if (block.getMaterial() == Material.plants || block instanceof BlockBush) {
                            this.applyDefoliantToPlant(world, ix, y + 1, iz);
                        }
                    } else if (block == Blocks.sand && world.getBlockMetadata(ix, y, iz) == 0) {
                        world.setBlock(ix, y, iz, Blocks.sand, 1, 3);
                        this.spawnDefoliantParticles(world, x, y, z, true);
                        block = world.getBlock(ix, y + 1, iz);
                        if (block.getMaterial() == Material.plants || block instanceof BlockBush) {
                            this.applyDefoliantToPlant(world, ix, y + 1, iz);
                        }
                    } else if (block == Blocks.mossy_cobblestone) {
                        world.setBlock(ix, y, iz, Blocks.cobblestone, 0, 3);
                        this.spawnDefoliantParticles(world, x, y, z, true);
                    }
                }
            }
        }

    }

    private void applyDefoliantToPlant(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        if (block instanceof PMPBlockPlant) {
            if (((PMPBlockPlant) block).isTopPlantBlock(world, x, y, z)) {
                world.setBlock(x, y, z, Blocks.air, 0, 3);
                this.spawnDefoliantParticles(world, x, y, z, false);
                block = world.getBlock(x, y - 1, z);
                if (block instanceof PMPBlockPlant) {
                    world.setBlock(x, y - 1, z, Blocks.air, 0, 3);
                    this.spawnDefoliantParticles(world, x, y - 1, z, false);
                }
            } else {
                world.setBlock(x, y, z, Blocks.air, 0, 3);
                this.spawnDefoliantParticles(world, x, y, z, false);
            }
        } else if (block instanceof BlockSapling || block.getMaterial() == Material.plants
            || block == Blocks.tallgrass
            || block instanceof BlockFlower) {
                world.setBlock(x, y, z, Blocks.air, 0, 3);
                this.spawnDefoliantParticles(world, x, y, z, false);
            }

    }

    private void spawnFertilizerParticles(World world, int x, int y, int z, Block block, boolean soilAdjust) {
        double d0 = itemRand.nextGaussian() * 0.02;
        double d1 = itemRand.nextGaussian() * 0.02;
        double d2 = itemRand.nextGaussian() * 0.02;
        double heightAdjustment = (double) (soilAdjust ? 1.0F : 0.05F);
        world.spawnParticle(
            "happyVillager",
            (double) ((float) x + itemRand.nextFloat()),
            (double) y + heightAdjustment + (double) itemRand.nextFloat() * 1.0,
            (double) ((float) z + itemRand.nextFloat()),
            d0,
            d1,
            d2);
    }

    private void spawnDefoliantParticles(World world, int x, int y, int z, boolean soilAdjust) {
        double d0 = itemRand.nextGaussian() * 0.02;
        double d1 = itemRand.nextGaussian() * 0.02;
        double d2 = itemRand.nextGaussian() * 0.02;
        double heightAdjustment = (double) (soilAdjust ? 1.0F : 0.05F);
        world.spawnParticle(
            "largesmoke",
            (double) ((float) x + itemRand.nextFloat()),
            (double) y + heightAdjustment + (double) itemRand.nextFloat() * 1.0,
            (double) ((float) z + itemRand.nextFloat()),
            d0,
            d1,
            d2);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
        if (PlantMegaPack.settingsGeneral.tooltipAttributes) {
            String unlocalizedName = this.getUnlocalizedName()
                .substring(5);
            if (unlocalizedName.matches("powderAloe")) {
                list.add("");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipBrewing")
                        + ": §9"
                        + StatCollector.translateToLocal("text.crafting.potionHealing")
                        + "§r");
            } else if (unlocalizedName.matches("powderBerry")) {
                list.add("");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.powderFertilizer.name")
                        + "§r");
            } else if (unlocalizedName.matches("powderCactus")) {
                list.add("");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipBrewing")
                        + ": §9"
                        + StatCollector.translateToLocal("text.crafting.potionStrength")
                        + "§r");
            } else if (unlocalizedName.matches("powderCoral")) {
                list.add("");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipBrewing")
                        + ": §9"
                        + StatCollector.translateToLocal("text.crafting.potionWaterBreathing")
                        + "§r");
            } else if (unlocalizedName.matches("powderFern")) {
                list.add("");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.powderFertilizer.name")
                        + "§r");
            } else if (unlocalizedName.matches("powderLeaf")) {
                list.add("");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.powderFertilizer.name")
                        + "§r");
            } else if (unlocalizedName.matches("powderMoss")) {
                list.add("");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.powderConditioner.name")
                        + "§r");
            } else if (unlocalizedName.matches("powderMushroom")) {
                list.add("");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.powderFertilizer.name")
                        + "§r");
            } else if (unlocalizedName.matches("powderMushroomPoison")) {
                list.add("");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                        + ": §9"
                        + StatCollector.translateToLocal("item.powderDefoliant.name")
                        + "§r");
            } else if (unlocalizedName.matches("powderConditioner")) {
                list.add("");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipRadius")
                        + ": §9"
                        + String.format("%d", PlantMegaPack.settingsGeneral.powderConditionerRadius)
                        + "§r");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipStrength")
                        + ": §9"
                        + String.format("%d", PlantMegaPack.settingsGeneral.powderConditionerStrength)
                        + "%§r");
            } else if (unlocalizedName.matches("powderDefoliant")) {
                list.add("");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipRadius")
                        + ": §9"
                        + String.format("%d", PlantMegaPack.settingsGeneral.powderDefoliantRadius)
                        + "§r");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipStrength")
                        + ": §9"
                        + String.format("%d", PlantMegaPack.settingsGeneral.powderDefoliantStrength)
                        + "%§r");
            } else if (unlocalizedName.matches("powderFertilizer")) {
                list.add("");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipRadius")
                        + ": §9"
                        + String.format("%d", PlantMegaPack.settingsGeneral.powderFertilizerRadius)
                        + "§r");
                list.add(
                    "§8" + StatCollector.translateToLocal("text.tooltipStrength")
                        + ": §9"
                        + String.format("%d", PlantMegaPack.settingsGeneral.powderFertilizerStrength)
                        + "%§r");
            }
        }

    }
}
