package plantmegapack.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockPlant;
import plantmegapack.common.PMPFlowerColor;
import plantmegapack.common.PMPPlantCategory;

public class PMPItemPlant extends ItemBlock {

    private PMPBlockPlant blockPlant = null;

    public PMPItemPlant(Block blockPlant) {
        super(blockPlant);
        this.setUnlocalizedName(
            blockPlant.getUnlocalizedName()
                .substring(5));
        this.setFull3D();
        if (blockPlant instanceof PMPBlockPlant) {
            this.blockPlant = (PMPBlockPlant) blockPlant;
            if (this.blockPlant.hasFlowerColors()) {
                this.setMaxDamage(0);
                this.setHasSubtypes(true);
            }
        }

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        if (this.blockPlant == null) {
            return Blocks.deadbush.getIcon(0, 0);
        } else {
            return this.blockPlant.hasFlowerColors() ? this.blockPlant.getIcon(0, damage)
                : this.blockPlant.getIcon(0, this.blockPlant.getInventoryIconIndex());
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack itemStack) {
        return this.getIconFromDamage(itemStack.getItemDamage());
    }

    public int getMetadata(int damageValue) {
        return damageValue;
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (this.blockPlant != null && (this.blockPlant.plantData.attributes.category == PMPPlantCategory.FLOATING
            || this.blockPlant.plantData.attributes.category == PMPPlantCategory.CROP_AQUATIC
            || this.blockPlant.plantData.attributes.category == PMPPlantCategory.IMMERSED)) {
            MovingObjectPosition movingobjectposition = this
                .getMovingObjectPositionFromPlayer(world, entityPlayer, true);
            if (movingobjectposition == null) {
                return itemStack;
            } else {
                if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
                    int i = movingobjectposition.blockX;
                    int j = movingobjectposition.blockY;
                    int k = movingobjectposition.blockZ;
                    if (!world.canMineBlock(entityPlayer, i, j, k)) {
                        return itemStack;
                    }

                    if (!entityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, itemStack)) {
                        return itemStack;
                    }

                    if (world.getBlock(i, j, k)
                        .getMaterial() == Material.water && world.getBlockMetadata(i, j, k) == 0
                        && world.isAirBlock(i, j + 1, k)) {
                        if (this.blockPlant.plantData.attributes.category == PMPPlantCategory.CROP_AQUATIC
                            || this.blockPlant.plantData.attributes.category == PMPPlantCategory.IMMERSED) {
                            Block block = world.getBlock(i, j - 1, k);
                            if (!PlantMegaPack.soilBlocks
                                .canPlantOnThisBlock(this.blockPlant.plantData.attributes.soilType, block)) {
                                return itemStack;
                            }
                        }

                        world.setBlock(i, j + 1, k, this.blockPlant, itemStack.getItemDamage(), 3);
                        if (!entityPlayer.capabilities.isCreativeMode) {
                            --itemStack.stackSize;
                        }
                    }
                }

                return itemStack;
            }
        } else {
            return super.onItemRightClick(itemStack, world, entityPlayer);
        }
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        if (this.blockPlant.hasFlowerColors()) {
            if (itemStack.getItemDamage() >= this.blockPlant.getSubBlocksCount()) {
                itemStack.setItemDamage(0);
            }

            return super.getUnlocalizedName(itemStack)
                + ((PMPFlowerColor) this.blockPlant.colorMap.get(itemStack.getItemDamage())).ID;
        } else {
            return super.getUnlocalizedName(itemStack);
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
        int lines = 0;
        if (this.blockPlant != null) {
            if (PlantMegaPack.settingsGeneral.tooltipLatinName
                && this.blockPlant.plantData.attributes.category != PMPPlantCategory.GROUNDCOVER) {
                list.add(new String("§7§o" + this.blockPlant.plantData.getLatinName() + "§r"));
            }

            if (PlantMegaPack.settingsGeneral.tooltipCategory) {
                list.add(
                    "§o§" + this.blockPlant.plantData.attributes.category.colorCode
                        + StatCollector
                            .translateToLocal("plantCategory." + this.blockPlant.plantData.attributes.category.ID)
                        + "§r");
            }

            if (PlantMegaPack.settingsGeneral.tooltipAttributes) {
                lines = list.size();
                list.add("");
                if (this.blockPlant.plantData.attributes.category != PMPPlantCategory.CORAL
                    && this.blockPlant.plantData.attributes.dyeColor >= 0) {
                    this.addPlantDyeColor(itemStack, list);
                }

                if (this.blockPlant.plantData.attributes.hangingPlant.length() > 0) {
                    list.add(
                        "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                            + ": §9"
                            + StatCollector.translateToLocal("text.crafting.hangingPlant")
                            + "§r");
                }

                this.addCategorySpecificCrafting(list);
                this.addPlantSpecificCrafting(list);
                if (this.blockPlant.plantData.attributes.foodPlant) {
                    list.add(
                        "§8" + StatCollector.translateToLocal("text.tooltipResource")
                            + ": §a"
                            + StatCollector.translateToLocal("text.tooltipFood")
                            + "§r");
                }

                if (this.blockPlant.plantData.attributes.damagePoison) {
                    list.add(
                        "§8" + StatCollector.translateToLocal("text.tooltipWarning")
                            + ": §c"
                            + StatCollector.translateToLocal("text.tooltipPoison")
                            + "§r");
                }

                if (this.blockPlant.plantData.attributes.damageThorns) {
                    list.add(
                        "§8" + StatCollector.translateToLocal("text.tooltipWarning")
                            + ": §e"
                            + StatCollector.translateToLocal("text.tooltipThorns")
                            + "§r");
                }
            }

            if (PlantMegaPack.settingsGeneral.tooltipGrowthStages
                && this.blockPlant.plantData.attributes.growthStages > 1) {
                if (this.blockPlant.plantData.attributes.category == PMPPlantCategory.BAMBOO) {
                    list.add(
                        "§8" + StatCollector.translateToLocal("text.tooltipGrowthHeight")
                            + ": §2"
                            + this.blockPlant.plantData.attributes.growthMax
                            + "§r");
                } else {
                    list.add(
                        "§8" + StatCollector.translateToLocal("text.tooltipGrowthStages")
                            + ": §2"
                            + this.blockPlant.plantData.attributes.growthStages
                            + "§r");
                }
            }

            if (list.size() == lines + 1) {
                list.remove(list.size() - 1);
            }

        }
    }

    @SideOnly(Side.CLIENT)
    private void addPlantDyeColor(ItemStack itemStack, List list) {
        int color = this.blockPlant.plantData.attributes.dyeColor;
        String dyeColorID = "";
        String formatColor = "";
        if (this.blockPlant.hasFlowerColors()) {
            color = ((PMPFlowerColor) this.blockPlant.colorMap.get(itemStack.getItemDamage())).dyeColor;
        }

        switch (color) {
            case 1:
                dyeColorID = "red";
                formatColor = "4";
                break;
            case 2:
                dyeColorID = "green";
                formatColor = "2";
            case 3:
            case 4:
            default:
                break;
            case 5:
                dyeColorID = "purple";
                formatColor = "5";
                break;
            case 6:
                dyeColorID = "cyan";
                formatColor = "3";
                break;
            case 7:
                dyeColorID = "lightGray";
                formatColor = "7";
                break;
            case 8:
                dyeColorID = "gray";
                formatColor = "8";
                break;
            case 9:
                dyeColorID = "pink";
                formatColor = "c";
                break;
            case 10:
                dyeColorID = "lime";
                formatColor = "a";
                break;
            case 11:
                dyeColorID = "yellow";
                formatColor = "e";
                break;
            case 12:
                dyeColorID = "lightBlue";
                formatColor = "b";
                break;
            case 13:
                dyeColorID = "magenta";
                formatColor = "d";
                break;
            case 14:
                dyeColorID = "orange";
                formatColor = "6";
        }

        if (dyeColorID.length() > 0) {
            list.add(
                "§8" + StatCollector.translateToLocal("text.tooltipCrafting")
                    + ": §"
                    + formatColor
                    + StatCollector.translateToLocal("text.dye." + dyeColorID)
                    + " §9"
                    + StatCollector.translateToLocal("text.crafting.dye")
                    + "§r");
        }

    }

    @SideOnly(Side.CLIENT)
    private void addCategorySpecificCrafting(List list) {
        String textCrafting = StatCollector.translateToLocal("text.tooltipCrafting");
        if (this.blockPlant.plantData.attributes.category == PMPPlantCategory.BAMBOO) {
            list.add(
                "§8" + StatCollector.translateToLocal("text.tooltipResource")
                    + ": §9"
                    + StatCollector.translateToLocal("text.crafting.buildingBlocks")
                    + "§r");
        } else if (this.blockPlant.plantData.attributes.category == PMPPlantCategory.CACTUS) {
            list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("plantPowder.cactus") + "§r");
        } else if (this.blockPlant.plantData.attributes.category == PMPPlantCategory.CORAL) {
            list.add(
                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("text.crafting.coralFragment") + "§r");
        } else if (this.blockPlant.plantData.attributes.category == PMPPlantCategory.EPIPHYTE) {
            if (this.blockPlant.plantData.attributes.ID.matches("epiphyteArtistsConk")
                || this.blockPlant.plantData.attributes.ID.matches("epiphyteSulphurShelf")
                || this.blockPlant.plantData.attributes.ID.matches("epiphyteTurkeyTail")) {
                list.add(
                    "§8" + textCrafting
                        + ": §9"
                        + StatCollector.translateToLocal("text.crafting.fermentedSpiderEye")
                        + "§r");
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("plantPowder.mushroom") + "§r");
            }
        } else if (this.blockPlant.plantData.attributes.category == PMPPlantCategory.FERN) {
            list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("plantPowder.fern") + "§r");
        } else if (this.blockPlant.plantData.attributes.category == PMPPlantCategory.FUNGUS) {
            list.add(
                "§8" + textCrafting
                    + ": §9"
                    + StatCollector.translateToLocal("text.crafting.fermentedSpiderEye")
                    + "§r");
            if (!this.blockPlant.plantData.attributes.ID.matches("fungusDeathCap")
                && !this.blockPlant.plantData.attributes.ID.matches("fungusWoollyGomphus")) {
                list.add(
                    "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("text.crafting.mushroomStew") + "§r");
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("plantPowder.mushroom") + "§r");
            } else {
                list.add(
                    "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("plantPowder.mushroomPoison") + "§r");
            }
        }

    }

    @SideOnly(Side.CLIENT)
    private void addPlantSpecificCrafting(List list) {
        String textCrafting = StatCollector.translateToLocal("text.tooltipCrafting");
        if (this.blockPlant.plantData.attributes.ID.matches("flowerBlueStar")) {
            list.add(
                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.flowerBlueStarItem.name") + "§r");
        } else if (this.blockPlant.plantData.attributes.ID.matches("desertBroomSnakeweed")) {
            list.add(
                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.flowerBroomSnakeweed.name") + "§r");
        } else if (this.blockPlant.plantData.attributes.ID.matches("desertOcotillo")) {
            list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.flowerOcotillo.name") + "§r");
        } else if (this.blockPlant.plantData.attributes.ID.matches("flowerYellowToadflax")) {
            list.add(
                "§8" + textCrafting
                    + ": §9"
                    + StatCollector.translateToLocal("item.flowerYellowToadflaxItem.name")
                    + "§r");
        } else if (this.blockPlant.plantData.attributes.ID.matches("forestWildMint")) {
            list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.leafWildMint.name") + "§r");
        } else if (this.blockPlant.plantData.attributes.ID.matches("jungleTorchGinger")) {
            list.add(
                "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.flowerTorchGinger.name") + "§r");
        } else if (this.blockPlant.plantData.attributes.ID.matches("flowerCandelabraAloe")) {
            list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.leafAloe.name") + "§r");
        } else if (this.blockPlant.plantData.attributes.ID.matches("forestPinesap")) {
            list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("item.salveFireResist.name") + "§r");
        } else if (this.blockPlant.plantData.attributes.ID.matches("forestWolfsFootClubmoss")) {
            list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("text.crafting.gunpowder") + "§r");
        } else if (!this.blockPlant.plantData.attributes.ID.matches("groundcoverLeavesBRN")
            && !this.blockPlant.plantData.attributes.ID.matches("groundcoverLeavesGRN")) {
                if (this.blockPlant.plantData.attributes.ID.matches("groundcoverMoss")) {
                    list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("plantPowder.moss") + "§r");
                    list.add(
                        "§8" + textCrafting
                            + ": §9"
                            + StatCollector.translateToLocal("text.crafting.mossyCobblestone")
                            + "§r");
                } else if (this.blockPlant.plantData.attributes.ID.matches("groundcoverTwig")) {
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("text.crafting.stick") + "§r");
                } else if (this.blockPlant.plantData.attributes.ID.matches("vineSpanishMoss")) {
                    list.add(
                        "§8" + textCrafting + ": §9" + StatCollector.translateToLocal("text.crafting.string") + "§r");
                } else if (!this.blockPlant.plantData.attributes.ID.matches("waterKelpGiantGRN")
                    && !this.blockPlant.plantData.attributes.ID.matches("waterKelpGiantYEL")) {
                        if (this.blockPlant.plantData.attributes.ID.matches("wetlandsCattails")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("text.crafting.cattailSpike")
                                    + "§r");
                        } else if (this.blockPlant.plantData.attributes.ID.matches("wetlandsWaterHorsetail")) {
                            list.add(
                                "§8" + textCrafting
                                    + ": §9"
                                    + StatCollector.translateToLocal("item.salveStrength.name")
                                    + "§r");
                        }
                    } else {
                        list.add(
                            "§8" + textCrafting
                                + ": §9"
                                + StatCollector.translateToLocal("item.wrapSeaweed.name")
                                + "§r");
                    }
            } else {
                list.add("§8" + textCrafting + ": §9" + StatCollector.translateToLocal("plantPowder.leaf") + "§r");
            }

    }
}
