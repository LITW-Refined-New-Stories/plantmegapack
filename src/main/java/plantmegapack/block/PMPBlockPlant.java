package plantmegapack.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.bin.PMPRenderers;
import plantmegapack.common.PMPFlowerColor;
import plantmegapack.common.PMPPlantCategory;
import plantmegapack.common.PMPPlantGrowthType;
import plantmegapack.common.PMPPlantRenderType;
import plantmegapack.common.PMPPlantSoilType;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.item.PMPItemPlant;
import plantmegapack.worldgen.PMPGenBase;

public class PMPBlockPlant extends BlockBush implements IPlantable, IShearable {

    public PMPDataPlant plantData = null;
    @SideOnly(Side.CLIENT)
    private IIcon[] textures;
    public ArrayList<PMPFlowerColor> colorMap = null;
    public static int renderPass;

    public PMPBlockPlant(PMPDataPlant plantData) {
        super(
            plantData.attributes.category != PMPPlantCategory.CORAL
                && plantData.attributes.category != PMPPlantCategory.FRESHWATER
                && plantData.attributes.category != PMPPlantCategory.SALTWATER ? Material.plants : Material.water);
        this.plantData = plantData;
        renderPass = 1;
        this.setBlockName(this.plantData.attributes.ID);
        this.setTickRandomly(true);
        switch (this.plantData.attributes.breakSound) {
            case GRASS:
                this.setStepSound(Block.soundTypeGrass);
                break;
            case GRAVEL:
                this.setStepSound(Block.soundTypeGravel);
                break;
            case WOOD:
                this.setStepSound(Block.soundTypeWood);
                break;
            default:
                this.setStepSound(Block.soundTypeGrass);
        }

        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(plantData.attributes.category.creativeTab));
        GameRegistry.registerBlock(this, PMPItemPlant.class, plantData.attributes.ID);
        OreDictionary.registerOre(plantData.attributes.ID, this);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        int growthStages = this.plantData.attributes.growthStages;
        int iconsToLoad = growthStages;
        String iconName = this.getUnlocalizedName()
            .substring(5);
        int count;
        if (this.plantData.attributes.category == PMPPlantCategory.BERRYBUSH) {
            this.textures = new IIcon[growthStages];
            this.textures[0] = ir.registerIcon("plantmegapack:berrybushBare");

            for (count = 1; count < growthStages; ++count) {
                this.textures[count] = ir.registerIcon("plantmegapack:" + this.plantData.attributes.ID + count);
            }
        } else if (this.plantData.attributes.category == PMPPlantCategory.EPIPHYTE) {
            if (this.plantData.attributes.renderSubtype == 2) {
                this.textures = new IIcon[3];

                for (count = 0; count < 3; ++count) {
                    this.textures[count] = ir.registerIcon("plantmegapack:" + this.plantData.attributes.ID + count);
                }
            } else if (this.plantData.attributes.growthStages > 1) {
                this.textures = new IIcon[growthStages];

                for (count = 0; count < iconsToLoad; ++count) {
                    this.textures[count] = ir.registerIcon("plantmegapack:" + this.plantData.attributes.ID + count);
                }
            } else {
                this.textures = new IIcon[1];
                this.textures[0] = ir.registerIcon("plantmegapack:" + this.plantData.attributes.ID);
            }
        } else if (this.plantData.attributes.category == PMPPlantCategory.FLOATING) {
            if (this.plantData.attributes.renderType == PMPPlantRenderType.FLOATING_FLAT) {
                this.textures = new IIcon[1];
                this.textures[0] = ir.registerIcon("plantmegapack:" + this.plantData.attributes.ID + "0");
            } else if (this.plantData.attributes.renderType == PMPPlantRenderType.FLOATING_FLOWER) {
                this.textures = new IIcon[this.colorMap.size() + 1];
                this.textures[this.colorMap.size()] = ir
                    .registerIcon("plantmegapack:" + this.plantData.attributes.ID + "0");

                for (count = 0; count < this.colorMap.size(); ++count) {
                    this.textures[count] = ir
                        .registerIcon("plantmegapack:" + iconName + ((PMPFlowerColor) this.colorMap.get(count)).ID);
                }
            } else if (this.plantData.attributes.renderType == PMPPlantRenderType.FLOATING_PLANT) {
                this.textures = new IIcon[2];
                this.textures[0] = ir.registerIcon("plantmegapack:" + this.plantData.attributes.ID + "0");
                this.textures[1] = ir.registerIcon("plantmegapack:" + this.plantData.attributes.ID + "1");
            }
        } else if (this.plantData.attributes.category == PMPPlantCategory.FLOWER_MULTI) {
            this.textures = new IIcon[this.colorMap.size()];

            for (count = 0; count < this.colorMap.size(); ++count) {
                this.textures[count] = ir
                    .registerIcon("plantmegapack:" + iconName + ((PMPFlowerColor) this.colorMap.get(count)).ID);
            }
        } else if (this.plantData.attributes.category == PMPPlantCategory.GROUNDCOVER) {
            this.textures = new IIcon[1];
            this.textures[0] = ir.registerIcon("plantmegapack:" + this.plantData.attributes.ID);
        } else if (this.isImmersedPlant()) {
            if (this.plantData.attributes.category == PMPPlantCategory.CROP_AQUATIC) {
                iconsToLoad = 10;
            } else {
                iconsToLoad = this.plantData.attributes.growthType == PMPPlantGrowthType.NORMAL ? 6 : 9;
            }

            this.textures = new IIcon[iconsToLoad];

            for (count = 0; count < iconsToLoad; ++count) {
                this.textures[count] = ir.registerIcon("plantmegapack:" + iconName + count);
            }
        } else if (this.plantData.attributes.category == PMPPlantCategory.VINE) {
            if (this.plantData.attributes.renderType != PMPPlantRenderType.VINE_FLOWER
                && this.plantData.attributes.renderType != PMPPlantRenderType.VINE_NORMAL) {
                if (this.plantData.attributes.renderType == PMPPlantRenderType.VINE_RANDOM) {
                    iconsToLoad = 5;
                    this.textures = new IIcon[iconsToLoad];

                    for (count = 0; count < iconsToLoad; ++count) {
                        this.textures[count] = ir.registerIcon("plantmegapack:" + this.plantData.attributes.ID + count);
                    }
                }
            } else {
                iconsToLoad = 3;
                this.textures = new IIcon[iconsToLoad];

                for (count = 0; count < iconsToLoad; ++count) {
                    this.textures[count] = ir.registerIcon("plantmegapack:" + this.plantData.attributes.ID + count);
                }
            }
        } else if (growthStages == 1) {
            this.textures = new IIcon[1];
            this.textures[0] = ir.registerIcon("plantmegapack:" + this.plantData.attributes.ID + "0");
        } else {
            if (this.plantData.attributes.growthType == PMPPlantGrowthType.DOUBLE) {
                iconsToLoad = growthStages * 2 - 2;
            }

            this.textures = new IIcon[iconsToLoad];

            for (count = 0; count < iconsToLoad; ++count) {
                this.textures[count] = ir.registerIcon("plantmegapack:" + this.plantData.attributes.ID + count);
            }
        }

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData) {
        if (this.plantData.attributes.category == PMPPlantCategory.EPIPHYTE) {
            return this.plantData.attributes.renderSubtype == 2 && metaData < this.textures.length
                ? this.textures[metaData]
                : this.textures[0];
        } else if (this.plantData.attributes.category == PMPPlantCategory.FLOATING) {
            if (side == 1) {
                return this.plantData.attributes.renderType == PMPPlantRenderType.FLOATING_FLOWER
                    ? this.textures[this.colorMap.size()]
                    : this.textures[metaData];
            } else {
                return metaData >= 0 && metaData <= this.textures.length - 1 ? this.textures[metaData]
                    : this.textures[0];
            }
        } else if (this.isImmersedPlant()) {
            return metaData < this.textures.length ? this.textures[metaData] : null;
        } else if (this.plantData.attributes.category == PMPPlantCategory.FLOWER_MULTI) {
            return metaData >= 0 && metaData <= this.textures.length - 1 ? this.textures[metaData] : this.textures[0];
        } else {
            if (this.plantData.attributes.category == PMPPlantCategory.VINE) {
                if (side >= 0) {
                    return this.textures[0];
                }

                if (side == -1) {
                    return metaData < this.textures.length ? this.textures[metaData] : this.textures[0];
                }
            }

            if (this.textures.length == 0) {
                return Blocks.deadbush.getIcon(side, metaData);
            } else {
                return metaData < this.textures.length ? this.textures[metaData] : this.textures[0];
            }
        }
    }

    private boolean canPlantGrowHere(World world, int x, int y, int z) {
        world.getBlock(x, y, z);
        int metaData = world.getBlockMetadata(x, y, z);
        Block block;
        if (this.plantData.attributes.category == PMPPlantCategory.EPIPHYTE) {
            block = world.getBlock(x, y, z + 1);
            if (PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.WOOD, block)) {
                return true;
            }

            block = world.getBlock(x, y, z - 1);
            if (PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.WOOD, block)) {
                return true;
            }

            block = world.getBlock(x + 1, y, z);
            if (PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.WOOD, block)) {
                return true;
            }

            block = world.getBlock(x - 1, y, z);
            if (PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.WOOD, block)) {
                return true;
            }
        } else if (this.plantData.attributes.category == PMPPlantCategory.FLOATING || this.isImmersedPlant()) {
            return this.canBlockSustainPlant(world, x, y - 1, z);
        }

        int i1 = metaData;
        if (this.plantData.attributes.category == PMPPlantCategory.VINE) {
            if (metaData > 0) {
                for (int j1 = 0; j1 <= 3; ++j1) {
                    int k1 = 1 << j1;
                    if ((metaData & k1) != 0
                        && !this
                            .canVineAttachHere(world.getBlock(x + Direction.offsetX[j1], y, z + Direction.offsetZ[j1]))
                        && (world.getBlock(x, y + 1, z) != this || (world.getBlockMetadata(x, y + 1, z) & k1) == 0)) {
                        i1 &= ~k1;
                    }
                }
            }

            if (i1 == 0 && !this.canVineAttachHere(world.getBlock(x, y + 1, z))) {
                return false;
            } else {
                if (i1 != metaData) {
                    world.setBlockMetadataWithNotify(x, y, z, i1, 3);
                }

                return true;
            }
        } else if (this.plantData.attributes.growthType == PMPPlantGrowthType.SEAWEED) {
            return this.canBlockSustainPlant(world, x, y - 1, z)
                && (world.getBlock(x, y + 1, z) == Blocks.water || world.getBlock(x, y + 1, z) == this);
        } else if (this.plantData.attributes.category != PMPPlantCategory.CORAL
            && this.plantData.attributes.category != PMPPlantCategory.FRESHWATER
            && this.plantData.attributes.category != PMPPlantCategory.SALTWATER) {
                if (this.plantData.attributes.growthType == PMPPlantGrowthType.DOUBLE
                    && (metaData == 3 || metaData == 5 || metaData == 7)) {
                    block = world.getBlock(x, y - 1, z);
                    return block == this;
                } else {
                    return world.getBlock(x, y - 1, z)
                        .canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
                }
            } else {
                block = world.getBlock(x, y + 1, z);
                return block.getMaterial() == Material.water;
            }
    }

    public boolean canBlockSustainPlant(IBlockAccess world, int x, int y, int z) {
        return this.canSustainPlant(world, x, y, z, ForgeDirection.UP, this);
    }

    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction,
        IPlantable plantable) {
        Block block = world.getBlock(x, y, z);
        int metaData = world.getBlockMetadata(x, y, z);
        if (this.plantData.attributes.category == PMPPlantCategory.FLOATING) {
            return block.getMaterial() == Material.water && metaData == 0;
        } else if (this.plantData.attributes.category == PMPPlantCategory.VINE) {
            return true;
        } else if (this.plantData.attributes.category == PMPPlantCategory.BEACH) {
            return PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.DIRT, block)
                || PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.SAND, block);
        } else if (this.plantData.attributes.category == PMPPlantCategory.GRASS) {
            return PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.DIRT, block)
                || PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.GRAVEL, block)
                || PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.SAND, block);
        } else if (this.plantData.attributes.category == PMPPlantCategory.GROUNDCOVER) {
            return PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.CROP, block)
                || PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.DIRT, block)
                || PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.GRAVEL, block)
                || PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.SAND, block)
                || PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.STONE, block);
        } else if (this.isImmersedPlant()) {
            if (world.getBlock(x, y, z)
                .getMaterial() == Material.water) {
                block = world.getBlock(x, y - 1, z);
                return PlantMegaPack.soilBlocks.canPlantOnThisBlock(this.plantData.attributes.soilType, block);
            } else {
                return false;
            }
        } else if (this.plantData.attributes.growthType == PMPPlantGrowthType.SEAWEED) {
            return PlantMegaPack.soilBlocks.canPlantOnThisBlock(this.plantData.attributes.soilType, block)
                || world.getBlock(x, y, z) == this;
        } else {
            switch (this.plantData.attributes.growthType) {
                case DOUBLE:
                    if (block == this) {
                        return metaData == 2 || metaData == 4 || metaData == 6;
                    }
                    break;
                case EPIPHYTE:
                    return true;
                case NORMAL:
                default:
                    break;
                case SEAWEED:
                    if (block == this) {
                        return true;
                    }
                    break;
                case STALK:
                    if (block == this) {
                        return true;
                    }
            }

            return PlantMegaPack.soilBlocks.canPlantOnThisBlock(this.plantData.attributes.soilType, block);
        }
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y - 1, z);
        if (this.plantData.attributes.category == PMPPlantCategory.BAMBOO
            && !PlantMegaPack.settingsGeneral.plantBambooFallsWhenBroken) {
            return true;
        } else if (this.plantData.attributes.category == PMPPlantCategory.EPIPHYTE) {
            int metaData = world.getBlockMetadata(x, y, z);
            switch (metaData) {
                case 2:
                    block = world.getBlock(x, y, z + 1);
                    break;
                case 3:
                    block = world.getBlock(x, y, z - 1);
                    break;
                case 4:
                    block = world.getBlock(x + 1, y, z);
                    break;
                case 5:
                    block = world.getBlock(x - 1, y, z);
                    break;
                default:
                    return false;
            }

            return block.getMaterial() == Material.wood;
        } else if (this.plantData.attributes.category != PMPPlantCategory.FLOATING) {
            if (this.plantData.attributes.category == PMPPlantCategory.VINE) {
                return true;
            } else {
                return this.plantData.attributes.growthType == PMPPlantGrowthType.SEAWEED
                    ? this.canPlantGrowHere(world, x, y, z)
                    : this.canPlaceBlockAt(world, x, y, z);
            }
        } else {
            return block.getMaterial() == Material.water && world.getBlockMetadata(x, y - 1, z) == 0;
        }
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        if (PlantMegaPack.settingsGeneral.contentAquatic
            && (this.plantData.attributes.category == PMPPlantCategory.CORAL
                || this.plantData.attributes.category == PMPPlantCategory.FRESHWATER
                || this.plantData.attributes.category == PMPPlantCategory.SALTWATER)) {
            return block.getMaterial() == Material.water && this.canPlantGrowHere(world, x, y, z);
        } else if (this.plantData.attributes.category == PMPPlantCategory.EPIPHYTE) {
            return PMPGenBase.isAdjacentToLogBlock(world, x, y, z, false) > 0;
        } else if (this.isImmersedPlant()) {
            return this.canPlantGrowHere(world, x, y, z);
        } else if (this.plantData.attributes.category == PMPPlantCategory.VINE) {
            return super.canPlaceBlockAt(world, x, y, z);
        } else {
            return PMPGenBase.canReplaceBlockWithPlant(world, x, y, z, true) ? this.canPlantGrowHere(world, x, y, z)
                : false;
        }
    }

    public int damageDropped(int metaData) {
        return metaData;
    }

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list,
        Entity entity) {
        if (this.plantData.attributes.category == PMPPlantCategory.FLOATING) {
            if (entity == null || !(entity instanceof EntityBoat)) {
                super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
            }
        } else {
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        }

    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        int metaData = world.getBlockMetadata(x, y, z);
        float pixel = 0.0625F;
        if (this.plantData.attributes.category == PMPPlantCategory.BAMBOO) {
            return AxisAlignedBB.getBoundingBox(
                (double) ((float) x + 0.2F),
                (double) y,
                (double) ((float) z + 0.2F),
                (double) ((float) x + 0.8F),
                (double) ((float) y + 1.0F),
                (double) ((float) z + 0.8F));
        } else {
            if (this.plantData.attributes.category == PMPPlantCategory.EPIPHYTE
                && PlantMegaPack.settingsGeneral.plantSolidShelfEpiphytes
                && this.plantData.attributes.renderType == PMPPlantRenderType.EPIPHYTE_HORIZONTAL) {
                switch (metaData) {
                    case 2:
                        return AxisAlignedBB.getBoundingBox(
                            (double) ((float) x + (0.5F - (float) this.plantData.attributes.hitX1 * pixel)),
                            (double) ((float) y + 0.312F),
                            (double) ((float) z + (1.0F - (float) this.plantData.attributes.hitY1 * pixel)),
                            (double) ((float) x + 0.5F + (float) this.plantData.attributes.hitX1 * pixel),
                            (double) ((float) y + 0.688F),
                            (double) ((float) z + 0.5F + (float) this.plantData.attributes.hitY1 * pixel));
                    case 3:
                        return AxisAlignedBB.getBoundingBox(
                            (double) ((float) x + (0.5F - (float) this.plantData.attributes.hitX1 * pixel)),
                            (double) ((float) y + 0.312F),
                            (double) ((float) z + 0.0F),
                            (double) ((float) x + 0.5F + (float) this.plantData.attributes.hitX1 * pixel),
                            (double) ((float) y + 0.688F),
                            (double) ((float) z + (float) this.plantData.attributes.hitY1 * pixel));
                    case 4:
                        return AxisAlignedBB.getBoundingBox(
                            (double) ((float) x + (1.0F - (float) this.plantData.attributes.hitY1 * pixel)),
                            (double) ((float) y + 0.312F),
                            (double) ((float) z + (0.5F - (float) this.plantData.attributes.hitX1 * pixel)),
                            (double) ((float) x + 1.0F),
                            (double) ((float) y + 0.688F),
                            (double) ((float) z + 0.5F + (float) this.plantData.attributes.hitX1 * pixel));
                    case 5:
                        return AxisAlignedBB.getBoundingBox(
                            (double) ((float) x + 0.0F),
                            (double) ((float) y + 0.312F),
                            (double) ((float) z + (0.5F - (float) this.plantData.attributes.hitX1 * pixel)),
                            (double) ((float) x + (float) this.plantData.attributes.hitY1 * pixel),
                            (double) ((float) y + 0.688F),
                            (double) ((float) z + 0.5F + (float) this.plantData.attributes.hitX1 * pixel));
                }
            }

            return null;
        }
    }

    public int getDamageValue(World world, int x, int y, int z) {
        return this.hasFlowerColors() ? world.getBlockMetadata(x, y, z) : 0;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metaData, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList();
        if (this.plantData.attributes.category == PMPPlantCategory.BAMBOO) {
            ret.add(new ItemStack(this, 1, 0));
            return ret;
        } else {
            if (this.plantData.attributes.category == PMPPlantCategory.BERRYBUSH) {
                ret.add(new ItemStack(this, this.plantData.dropAlways, 0));
                if (world.rand.nextInt(100) < this.plantData.dropRandomChance) {
                    ret.add(new ItemStack(this, this.plantData.dropRandomAmount, 0));
                }

                if (this.isFullyGrown(metaData)) {
                    ret.add(this.getBerryItemStack(metaData, 1));
                    if (world.rand.nextInt(100) < this.plantData.dropRandomChance) {
                        ret.add(this.getBerryItemStack(metaData, this.plantData.dropRandomAmount));
                    }
                }
            } else if (this.plantData.attributes.ID.matches("cactusPricklyPear")) {
                ret.add(new ItemStack(this, this.plantData.dropAlways, 0));
                if (this.isFullyGrown(metaData)) {
                    ret.add(new ItemStack(PlantMegaPack.items.getFoodItem("foodPricklyPearFruit"), 1, 0));
                    if (world.rand.nextInt(100) < this.plantData.dropRandomChance) {
                        ret.add(
                            new ItemStack(
                                PlantMegaPack.items.getFoodItem("foodPricklyPearFruit"),
                                this.plantData.dropRandomAmount,
                                0));
                    }
                }
            } else if (this.plantData.attributes.category == PMPPlantCategory.CROP_AQUATIC) {
                ret.add(new ItemStack(this, this.plantData.dropAlways, 0));
                if (this.isFullyGrown(metaData)) {
                    ItemStack tempItemStack = this.getFoodItemStack(this.plantData.attributes.ID, 1);
                    if (tempItemStack != null) {
                        ret.add(tempItemStack);
                    }

                    if (world.rand.nextInt(100) < this.plantData.dropRandomChance) {
                        ret.add(new ItemStack(this, Math.max(this.plantData.dropRandomAmount, 2), 0));
                    }

                    if (world.rand.nextInt(100) < this.plantData.dropRandomChance) {
                        tempItemStack = this
                            .getFoodItemStack(this.plantData.attributes.ID, this.plantData.dropRandomAmount);
                        if (tempItemStack != null) {
                            ret.add(tempItemStack);
                        }
                    }
                }
            } else if (this.plantData.attributes.category == PMPPlantCategory.CROP_LAND) {
                ret.add(this.getSeedItemStack(this.plantData.attributes.ID, Math.max(1, this.plantData.dropAlways)));
                if (this.isFullyGrown(metaData)) {
                    ret.add(this.getFoodItemStack(this.plantData.attributes.ID, 1));
                    if (world.rand.nextInt(100) < this.plantData.dropRandomChance) {
                        ret.add(this.getFoodItemStack(this.plantData.attributes.ID, this.plantData.dropRandomAmount));
                    }

                    if (world.rand.nextInt(100) < this.plantData.dropRandomChance) {
                        ret.add(this.getSeedItemStack(this.plantData.attributes.ID, this.plantData.dropRandomAmount));
                    }
                }
            } else if (this.plantData.attributes.ID.matches("epiphyteHarlequinMistletoe")) {
                ret.add(new ItemStack(this, this.plantData.dropAlways, 0));
                if (world.rand.nextInt(100) < this.plantData.dropRandomChance) {
                    ret.add(
                        new ItemStack(
                            PlantMegaPack.items.getFoodItem("berriesHarlequinMistletoe"),
                            this.plantData.dropRandomAmount,
                            0));
                }
            } else if (this.plantData.attributes.ID.matches("flowerQuinoa")) {
                ret.add(new ItemStack(this, this.plantData.dropAlways, 0));
                if (this.isFullyGrown(metaData)) {
                    ret.add(new ItemStack(PlantMegaPack.items.getFoodItem("foodQuinoaSeeds"), 1, 0));
                    if (world.rand.nextInt(100) < this.plantData.dropRandomChance) {
                        ret.add(
                            new ItemStack(
                                PlantMegaPack.items.getFoodItem("foodQuinoaSeeds"),
                                this.plantData.dropRandomAmount,
                                0));
                    }
                }
            } else if (this.plantData.attributes.ID.matches("oceanMozuku")) {
                ret.add(new ItemStack(this, this.plantData.dropAlways, 0));
                if (world.rand.nextInt(100) < this.plantData.dropRandomChance) {
                    ret.add(
                        new ItemStack(
                            PlantMegaPack.items.getFoodItem("foodMozukuSeaweed"),
                            this.plantData.dropRandomAmount,
                            0));
                }
            } else if (this.plantData.attributes.category != PMPPlantCategory.FLOATING
                && this.plantData.attributes.category != PMPPlantCategory.VINE) {
                    if (this.hasFlowerColors()) {
                        ret.add(new ItemStack(this, this.plantData.dropAlways, metaData));
                        if (world.rand.nextInt(100) < this.plantData.dropRandomChance) {
                            ret.add(new ItemStack(this, this.plantData.dropRandomAmount, metaData));
                        }
                    } else {
                        ret.add(new ItemStack(this, this.plantData.dropAlways, 0));
                        if (world.rand.nextInt(100) < this.plantData.dropRandomChance) {
                            ret.add(new ItemStack(this, this.plantData.dropRandomAmount, 0));
                        }
                    }
                }

            return ret;
        }
    }

    public String getLatinName() {
        return LanguageRegistry.instance()
            .getStringLocalization(this.getUnlocalizedName() + ".snam");
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        if (this.plantData.attributes.category == PMPPlantCategory.CROP_LAND) {
            return this.getSeedItemStack(this.plantData.attributes.ID, 1);
        } else {
            return this.hasFlowerColors() ? new ItemStack(this, 1, world.getBlockMetadata(x, y, z))
                : new ItemStack(this, 1, 0);
        }
    }

    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this;
    }

    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        Block blockBelow = world.getBlock(x, y - 1, z);
        return this.plantData.attributes.category == PMPPlantCategory.BAMBOO && block != blockBelow ? 4
            : world.getBlockMetadata(x, y, z);
    }

    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        switch (this.plantData.attributes.category) {
            case CAVE:
                return EnumPlantType.Cave;
            case CROP_AQUATIC:
            case IMMERSED:
                return EnumPlantType.Plains;
            case FRESHWATER:
            case SALTWATER:
                return EnumPlantType.Water;
            case CROP_LAND:
                return EnumPlantType.Crop;
            case CACTUS:
                return EnumPlantType.Desert;
            case NETHER:
                return EnumPlantType.Nether;
            default:
                return EnumPlantType.Plains;
        }
    }

    public int getRenderType() {
        return PMPRenderers.getRendererFromRenderType(this.plantData.attributes.renderType);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List subItems) {
        if (this.hasFlowerColors()) {
            for (int count = 0; count < this.colorMap.size(); ++count) {
                subItems.add(new ItemStack(this, 1, count));
            }
        } else {
            super.getSubBlocks(item, tab, subItems);
        }

    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
        return this.plantData.attributes.category == PMPPlantCategory.GRASS
            || this.plantData.attributes.category == PMPPlantCategory.GROUNDCOVER;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {
        int metaData = world.getBlockMetadata(x, y, z);
        if (this.plantData.attributes.category == PMPPlantCategory.BERRYBUSH && this.isFullyGrown(metaData)) {
            int plantDrops = this.plantData.dropAlways;
            int randomChance = world.rand.nextInt(100);
            if (randomChance < this.plantData.dropRandomChance * 100) {
                plantDrops += world.rand.nextInt(this.plantData.dropRandomAmount);
            }

            this.dropBlockAsItem(world, x, y, z, this.getBerryItemStack(metaData, plantDrops));
            world.setBlockMetadataWithNotify(x, y, z, 0, 3);
            return true;
        } else {
            return false;
        }
    }

    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData) {
        if (this.plantData.attributes.soilType == PMPPlantSoilType.AQUATIC) {
            if (this.isImmersedPlant()) {
                world.setBlock(x, y, z, Blocks.air);
            } else {
                world.setBlock(x, y, z, Blocks.water);
            }
        } else if (this.plantData.attributes.growthType == PMPPlantGrowthType.DOUBLE) {
            switch (metaData) {
                case 2:
                case 4:
                case 6:
                    if (world.getBlock(x, y + 1, z) == this) {
                        if (this.plantData.attributes.category != PMPPlantCategory.CORAL
                            && this.plantData.attributes.category != PMPPlantCategory.FRESHWATER
                            && this.plantData.attributes.category != PMPPlantCategory.SALTWATER) {
                            world.setBlockToAir(x, y + 1, z);
                        } else {
                            world.setBlock(x, y + 1, z, Blocks.water);
                        }
                    }
                    break;
                case 3:
                case 5:
                case 7:
                    if (world.getBlock(x, y - 1, z) == this) {
                        if (this.plantData.attributes.category != PMPPlantCategory.CORAL
                            && this.plantData.attributes.category != PMPPlantCategory.FRESHWATER
                            && this.plantData.attributes.category != PMPPlantCategory.SALTWATER) {
                            world.setBlockToAir(x, y - 1, z);
                        } else {
                            world.setBlock(x, y - 1, z, Blocks.water);
                        }
                    }
            }
        }

    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        if (entity != null) {
            if (this.plantData.attributes.renderType == PMPPlantRenderType.FLAT) {
                int direction = MathHelper.floor_double((double) (entity.rotationYaw * 8.0F / 360.0F) + 0.5) & 3;
                world.setBlockMetadataWithNotify(x, y, z, direction, 3);
            }
        }
    }

    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ,
        int metaData) {
        if (this.plantData.attributes.category == PMPPlantCategory.BAMBOO) {
            return 4;
        } else if (this.plantData.attributes.category != PMPPlantCategory.EPIPHYTE) {
            return this.plantData.attributes.category == PMPPlantCategory.VINE
                ? Blocks.vine.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, metaData)
                : metaData;
        } else {
            int j1 = world.getBlockMetadata(x, y, z);
            Block block = world.getBlock(x, y, z + 1);
            if ((j1 == 0 || side == 2) && PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.WOOD, block)) {
                j1 = 2;
            }

            block = world.getBlock(x, y, z - 1);
            if ((j1 == 0 || side == 3) && PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.WOOD, block)) {
                j1 = 3;
            }

            block = world.getBlock(x + 1, y, z);
            if ((j1 == 0 || side == 4) && PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.WOOD, block)) {
                j1 = 4;
            }

            block = world.getBlock(x - 1, y, z);
            if ((j1 == 0 || side == 5) && PlantMegaPack.soilBlocks.canPlantOnThisBlock(PMPPlantSoilType.WOOD, block)) {
                j1 = 5;
            }

            return j1;
        }
    }

    public void onBlockPreDestroy(World world, int x, int y, int z, int metaData) {
        if (this.plantData.attributes.category == PMPPlantCategory.BAMBOO) {
            Block block = world.getBlock(x, y, z);
            Block blockBelow = world.getBlock(x, y - 1, z);
            if (block == blockBelow) {
                world.setBlockMetadataWithNotify(x, y - 1, z, 4, 3);
                if (this.plantData.attributes.renderSubtype == 1) {
                    this.updateBambooFromTop(world, x, y - 1, z);
                }
            }
        }

    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (entity != null) {
            if (entity instanceof EntityPlayer || entity instanceof EntityPlayerMP) {
                if (this.plantData.attributes.damagePoison) {
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 5));
                }

                if (this.plantData.attributes.damageThorns) {
                    ((EntityLivingBase) entity).attackEntityFrom(DamageSource.cactus, 1.0F);
                }
            }

        }
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!world.isRemote) {
            if (this.plantData.attributes.category == PMPPlantCategory.VINE) {
                if (!this.func_150094_e(world, x, y, z)) {
                    this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
                    world.setBlockToAir(x, y, z);
                }
            } else if (world.getBlock(x, y, z) == this && !this.canBlockStay(world, x, y, z)) {
                if (this.hasFlowerColors()) {
                    this.dropBlockAsItem(
                        world,
                        x,
                        y,
                        z,
                        new ItemStack(Item.getItemFromBlock(this), 1, world.getBlockMetadata(x, y, z)));
                } else {
                    this.dropBlockAsItem(world, x, y, z, new ItemStack(Item.getItemFromBlock(this), 1, 0));
                }

                if (this.plantData.attributes.category != PMPPlantCategory.CORAL
                    && this.plantData.attributes.category != PMPPlantCategory.FRESHWATER
                    && this.plantData.attributes.category != PMPPlantCategory.SALTWATER) {
                    world.setBlockToAir(x, y, z);
                } else {
                    world.setBlock(x, y, z, Blocks.water);
                }
            }
        }

    }

    public void onPostBlockPlaced(World world, int x, int y, int z, int metaData) {
        if (this.plantData.attributes.category == PMPPlantCategory.BAMBOO) {
            Block block = world.getBlock(x, y - 1, z);
            if (block == this) {
                switch (this.plantData.attributes.renderSubtype) {
                    case 0:
                    case 1:
                        this.updateBambooFromTop(world, x, y, z);
                        break;
                    case 2:
                        world.setBlockMetadataWithNotify(x, y - 1, z, world.rand.nextInt(4), 3);
                }
            }
        }

    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        int metaData = world.getBlockMetadata(x, y, z);
        float pixel = 0.0625F;
        float bottom = this.isImmersedPlant() ? -1.0F : 0.0F;
        if (this.plantData.attributes.category == PMPPlantCategory.BAMBOO) {
            switch (metaData) {
                case 0:
                    this.setBlockBounds(0.26F, 0.0F, 0.26F, 0.74F, 1.0F, 0.74F);
                    return;
                case 1:
                    this.setBlockBounds(0.22F, 0.0F, 0.22F, 0.78F, 1.0F, 0.78F);
                    return;
                case 2:
                    this.setBlockBounds(0.18F, 0.0F, 0.18F, 0.82F, 1.0F, 0.82F);
                    return;
                case 3:
                    this.setBlockBounds(0.14F, 0.0F, 0.14F, 0.86F, 1.0F, 0.86F);
                    return;
                case 4:
                default:
                    this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
            }
        } else if (this.plantData.attributes.category == PMPPlantCategory.EPIPHYTE) {
            if (this.plantData.attributes.renderType == PMPPlantRenderType.EPIPHYTE_HORIZONTAL) {
                switch (metaData) {
                    case 2:
                        this.setBlockBounds(
                            0.5F - (float) this.plantData.attributes.hitX1 * pixel,
                            0.312F,
                            1.0F - (float) this.plantData.attributes.hitY1 * pixel,
                            0.5F + (float) this.plantData.attributes.hitX1 * pixel,
                            0.688F,
                            0.5F + (float) this.plantData.attributes.hitY1 * pixel);
                        break;
                    case 3:
                        this.setBlockBounds(
                            0.5F - (float) this.plantData.attributes.hitX1 * pixel,
                            0.312F,
                            0.0F,
                            0.5F + (float) this.plantData.attributes.hitX1 * pixel,
                            0.688F,
                            (float) this.plantData.attributes.hitY1 * pixel);
                        break;
                    case 4:
                        this.setBlockBounds(
                            1.0F - (float) this.plantData.attributes.hitY1 * pixel,
                            0.312F,
                            0.5F - (float) this.plantData.attributes.hitX1 * pixel,
                            1.0F,
                            0.688F,
                            0.5F + (float) this.plantData.attributes.hitX1 * pixel);
                        break;
                    case 5:
                        this.setBlockBounds(
                            0.0F,
                            0.312F,
                            0.5F - (float) this.plantData.attributes.hitX1 * pixel,
                            (float) this.plantData.attributes.hitY1 * pixel,
                            0.688F,
                            0.5F + (float) this.plantData.attributes.hitX1 * pixel);
                }
            } else if (this.plantData.attributes.renderType == PMPPlantRenderType.EPIPHYTE_VERTICAL) {
                switch (metaData) {
                    case 2:
                        this.setBlockBounds(0.125F, 0.125F, 0.125F, 0.875F, 0.875F, 1.0F);
                        break;
                    case 3:
                        this.setBlockBounds(0.125F, 0.125F, 0.0F, 0.875F, 0.875F, 0.875F);
                        break;
                    case 4:
                        this.setBlockBounds(0.125F, 0.125F, 0.125F, 1.0F, 0.875F, 0.875F);
                        break;
                    case 5:
                        this.setBlockBounds(0.0F, 0.125F, 0.125F, 0.875F, 0.875F, 0.875F);
                }
            } else {
                this.setBlockBounds(0.062F, 0.062F, 0.062F, 0.938F, 0.938F, 0.938F);
            }

        } else if (this.plantData.attributes.category != PMPPlantCategory.GROUNDCOVER
            && this.plantData.attributes.renderType != PMPPlantRenderType.FLOATING_FLAT) {
                if (this.plantData.attributes.growthType == PMPPlantGrowthType.DOUBLE) {
                    if (this.isImmersedPlant()) {
                        switch (metaData) {
                            case 0:
                                this.setBlockBounds(
                                    0.5F - (float) this.plantData.attributes.hitX1 * pixel,
                                    bottom,
                                    0.5F - (float) this.plantData.attributes.hitX1 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX1 * pixel,
                                    (float) this.plantData.attributes.hitY1 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX1 * pixel);
                                return;
                            case 1:
                                this.setBlockBounds(
                                    0.5F - (float) this.plantData.attributes.hitX2 * pixel,
                                    bottom,
                                    0.5F - (float) this.plantData.attributes.hitX2 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX2 * pixel,
                                    (float) this.plantData.attributes.hitY2 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX2 * pixel);
                                return;
                            case 2:
                                this.setBlockBounds(
                                    0.5F - (float) this.plantData.attributes.hitX3 * pixel,
                                    bottom,
                                    0.5F - (float) this.plantData.attributes.hitX3 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX3 * pixel,
                                    1.0F,
                                    0.5F + (float) this.plantData.attributes.hitX3 * pixel);
                                return;
                            case 3:
                                this.setBlockBounds(
                                    0.5F - (float) this.plantData.attributes.hitX4 * pixel,
                                    bottom,
                                    0.5F - (float) this.plantData.attributes.hitX4 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX4 * pixel,
                                    1.0F,
                                    0.5F + (float) this.plantData.attributes.hitX4 * pixel);
                                return;
                            case 4:
                                this.setBlockBounds(
                                    0.5F - (float) this.plantData.attributes.hitX5 * pixel,
                                    bottom,
                                    0.5F - (float) this.plantData.attributes.hitX5 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX5 * pixel,
                                    1.0F,
                                    0.5F + (float) this.plantData.attributes.hitX5 * pixel);
                                return;
                            default:
                                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                        }
                    } else {
                        switch (metaData) {
                            case 0:
                                this.setBlockBounds(
                                    0.5F - (float) this.plantData.attributes.hitX1 * pixel,
                                    0.0F,
                                    0.5F - (float) this.plantData.attributes.hitX1 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX1 * pixel,
                                    (float) this.plantData.attributes.hitY1 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX1 * pixel);
                                return;
                            case 1:
                                this.setBlockBounds(
                                    0.5F - (float) this.plantData.attributes.hitX2 * pixel,
                                    0.0F,
                                    0.5F - (float) this.plantData.attributes.hitX2 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX2 * pixel,
                                    (float) this.plantData.attributes.hitY2 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX2 * pixel);
                                return;
                            case 2:
                                this.setBlockBounds(
                                    0.5F - (float) this.plantData.attributes.hitX3 * pixel,
                                    0.0F,
                                    0.5F - (float) this.plantData.attributes.hitX3 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX3 * pixel,
                                    1.0F + (float) this.plantData.attributes.hitY3 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX3 * pixel);
                                return;
                            case 3:
                                this.setBlockBounds(
                                    0.5F - (float) this.plantData.attributes.hitX3 * pixel,
                                    -1.0F,
                                    0.5F - (float) this.plantData.attributes.hitX3 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX3 * pixel,
                                    (float) this.plantData.attributes.hitY3 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX3 * pixel);
                                return;
                            case 4:
                                this.setBlockBounds(
                                    0.5F - (float) this.plantData.attributes.hitX4 * pixel,
                                    0.0F,
                                    0.5F - (float) this.plantData.attributes.hitX4 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX4 * pixel,
                                    1.0F + (float) this.plantData.attributes.hitY4 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX4 * pixel);
                                return;
                            case 5:
                                this.setBlockBounds(
                                    0.5F - (float) this.plantData.attributes.hitX4 * pixel,
                                    -1.0F,
                                    0.5F - (float) this.plantData.attributes.hitX4 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX4 * pixel,
                                    (float) this.plantData.attributes.hitY4 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX4 * pixel);
                                return;
                            case 6:
                                this.setBlockBounds(
                                    0.5F - (float) this.plantData.attributes.hitX5 * pixel,
                                    0.0F,
                                    0.5F - (float) this.plantData.attributes.hitX5 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX5 * pixel,
                                    1.0F + (float) this.plantData.attributes.hitY5 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX5 * pixel);
                                return;
                            case 7:
                                this.setBlockBounds(
                                    0.5F - (float) this.plantData.attributes.hitX5 * pixel,
                                    -1.0F,
                                    0.5F - (float) this.plantData.attributes.hitX5 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX5 * pixel,
                                    (float) this.plantData.attributes.hitY5 * pixel,
                                    0.5F + (float) this.plantData.attributes.hitX5 * pixel);
                                return;
                            default:
                                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                        }
                    }
                } else if (this.plantData.attributes.category == PMPPlantCategory.VINE) {
                    int l = world.getBlockMetadata(x, y, z);
                    float f1 = 1.0F;
                    float f2 = 1.0F;
                    float f3 = 1.0F;
                    float f4 = 0.0F;
                    float f5 = 0.0F;
                    float f6 = 0.0F;
                    boolean flag = l > 0;
                    if ((l & 2) != 0) {
                        f4 = Math.max(f4, 0.0625F);
                        f1 = 0.0F;
                        f2 = 0.0F;
                        f5 = 1.0F;
                        f3 = 0.0F;
                        f6 = 1.0F;
                        flag = true;
                    }

                    if ((l & 8) != 0) {
                        f1 = Math.min(f1, 0.9375F);
                        f4 = 1.0F;
                        f2 = 0.0F;
                        f5 = 1.0F;
                        f3 = 0.0F;
                        f6 = 1.0F;
                        flag = true;
                    }

                    if ((l & 4) != 0) {
                        f6 = Math.max(f6, 0.0625F);
                        f3 = 0.0F;
                        f1 = 0.0F;
                        f4 = 1.0F;
                        f2 = 0.0F;
                        f5 = 1.0F;
                        flag = true;
                    }

                    if ((l & 1) != 0) {
                        f3 = Math.min(f3, 0.9375F);
                        f6 = 1.0F;
                        f1 = 0.0F;
                        f4 = 1.0F;
                        f2 = 0.0F;
                        f5 = 1.0F;
                        flag = true;
                    }

                    if (!flag && this.canVineAttachHere(world.getBlock(x, y + 1, z))) {
                        f2 = Math.min(f2, 0.9375F);
                        f5 = 1.0F;
                        f1 = 0.0F;
                        f4 = 1.0F;
                        f3 = 0.0F;
                        f6 = 1.0F;
                    }

                    this.setBlockBounds(f1, f2, f3, f4, f5, f6);
                } else {
                    if (this.plantData.attributes.growthStages > 1) {
                        if (metaData == 0) {
                            this.setBlockBounds(
                                0.5F - (float) this.plantData.attributes.hitX1 * pixel,
                                bottom,
                                0.5F - (float) this.plantData.attributes.hitX1 * pixel,
                                0.5F + (float) this.plantData.attributes.hitX1 * pixel,
                                (float) this.plantData.attributes.hitY1 * pixel,
                                0.5F + (float) this.plantData.attributes.hitX1 * pixel);
                            return;
                        }

                        if (metaData == 1) {
                            this.setBlockBounds(
                                0.5F - (float) this.plantData.attributes.hitX2 * pixel,
                                bottom,
                                0.5F - (float) this.plantData.attributes.hitX2 * pixel,
                                0.5F + (float) this.plantData.attributes.hitX2 * pixel,
                                (float) this.plantData.attributes.hitY2 * pixel,
                                0.5F + (float) this.plantData.attributes.hitX2 * pixel);
                            return;
                        }

                        if (metaData == 2) {
                            this.setBlockBounds(
                                0.5F - (float) this.plantData.attributes.hitX3 * pixel,
                                bottom,
                                0.5F - (float) this.plantData.attributes.hitX3 * pixel,
                                0.5F + (float) this.plantData.attributes.hitX3 * pixel,
                                (float) this.plantData.attributes.hitY3 * pixel,
                                0.5F + (float) this.plantData.attributes.hitX3 * pixel);
                            return;
                        }

                        if (metaData == 3) {
                            this.setBlockBounds(
                                0.5F - (float) this.plantData.attributes.hitX4 * pixel,
                                bottom,
                                0.5F - (float) this.plantData.attributes.hitX4 * pixel,
                                0.5F + (float) this.plantData.attributes.hitX4 * pixel,
                                (float) this.plantData.attributes.hitY4 * pixel,
                                0.5F + (float) this.plantData.attributes.hitX4 * pixel);
                            return;
                        }

                        if (metaData == 4) {
                            this.setBlockBounds(
                                0.5F - (float) this.plantData.attributes.hitX5 * pixel,
                                bottom,
                                0.5F - (float) this.plantData.attributes.hitX5 * pixel,
                                0.5F + (float) this.plantData.attributes.hitX5 * pixel,
                                (float) this.plantData.attributes.hitY5 * pixel,
                                0.5F + (float) this.plantData.attributes.hitX5 * pixel);
                            return;
                        }
                    }

                    this.setBlockBounds(
                        0.5F - (float) this.plantData.attributes.hitX1 * pixel,
                        bottom,
                        0.5F - (float) this.plantData.attributes.hitX1 * pixel,
                        0.5F + (float) this.plantData.attributes.hitX1 * pixel,
                        (float) this.plantData.attributes.hitY1 * pixel,
                        0.5F + (float) this.plantData.attributes.hitX1 * pixel);
                }
            } else {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.015625F, 1.0F);
            }
    }

    protected boolean canPlaceBlockOn(Block block) {
        if (block == null) {
            return false;
        } else if (this.plantData.attributes.category != PMPPlantCategory.FLOATING && !this.isImmersedPlant()) {
            return PlantMegaPack.soilBlocks.canPlantOnThisBlock(this.plantData.attributes.soilType, block);
        } else {
            return block.getMaterial() == Material.water;
        }
    }

    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
        if (this.plantData.attributes.category == PMPPlantCategory.VINE) {
            switch (side) {
                case 1:
                    return this.canVineAttachHere(world.getBlock(x, y + 1, z));
                case 2:
                    return this.canVineAttachHere(world.getBlock(x, y, z + 1));
                case 3:
                    return this.canVineAttachHere(world.getBlock(x, y, z - 1));
                case 4:
                    return this.canVineAttachHere(world.getBlock(x + 1, y, z));
                case 5:
                    return this.canVineAttachHere(world.getBlock(x - 1, y, z));
                default:
                    return false;
            }
        } else {
            return super.canPlaceBlockOnSide(world, x, y, z, side);
        }
    }

    private boolean canVineAttachHere(Block block) {
        return block.renderAsNormalBlock() && block.getMaterial()
            .blocksMovement();
    }

    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        return super.getBlockColor();
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int data) {
        return super.getRenderColor(data);
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        return super.colorMultiplier(world, x, y, z);
    }

    public void updateTick(World world, int x, int y, int z, Random random) {
        if (this.plantData.attributes.category != PMPPlantCategory.FLOATING
            && this.plantData.attributes.category != PMPPlantCategory.FLOWER_MULTI) {
            if (this.plantData.attributes.category == PMPPlantCategory.BAMBOO) {
                Block blockBelow = world.getBlock(x, y - 1, z);
                if (this != blockBelow) {
                    this.growStalkPlantFromBottom(world, x, y, z);
                }
            } else if (this.plantData.attributes.growthType == PMPPlantGrowthType.SEAWEED) {
                Block blockAbove = world.getBlock(x, y + 1, z);
                Block blockAbove2 = world.getBlock(x, y + 2, z);
                if (blockAbove.getMaterial() == Material.water && blockAbove2.getMaterial() == Material.water
                    && this.plantData.growthRate > 0
                    && random.nextInt(100) < this.plantData.growthRate) {
                    world.setBlock(x, y + 1, z, this, 0, 2);
                }
            } else if (this.plantData.attributes.category == PMPPlantCategory.VINE) {
                this.updateVineTick(world, x, y, z, random);
            } else if (world.getBlock(x, y, z) == this) {
                this.checkAndDropBlock(world, x, y, z);
            } else if (this.plantData.growthRate > 0 && random.nextInt(100) < this.plantData.growthRate) {
                this.growPlant(world, x, y, z);
            }

        }
    }

    private void updateVineTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote && world.rand.nextInt(4) == 0) {
            byte b0 = 4;
            int l = 5;
            boolean flag = false;

            int i1;
            int j1;
            int k1;
            label134: for (i1 = x - b0; i1 <= x + b0; ++i1) {
                for (j1 = z - b0; j1 <= z + b0; ++j1) {
                    for (k1 = y - 1; k1 <= y + 1; ++k1) {
                        if (world.getBlock(i1, k1, j1) == this) {
                            --l;
                            if (l <= 0) {
                                flag = true;
                                break label134;
                            }
                        }
                    }
                }
            }

            i1 = world.getBlockMetadata(x, y, z);
            j1 = world.rand.nextInt(6);
            k1 = Direction.facingToDirection[j1];
            int l1;
            if (j1 == 1 && y < 255 && world.isAirBlock(x, y + 1, z)) {
                if (flag) {
                    return;
                }

                int j2 = world.rand.nextInt(16) & i1;
                if (j2 > 0) {
                    for (l1 = 0; l1 <= 3; ++l1) {
                        if (!this.canVineAttachHere(
                            world.getBlock(x + Direction.offsetX[l1], y + 1, z + Direction.offsetZ[l1]))) {
                            j2 &= ~(1 << l1);
                        }
                    }

                    if (j2 > 0) {
                        world.setBlock(x, y + 1, z, this, j2, 2);
                    }
                }
            } else {
                Block block;
                int i2;
                if (j1 >= 2 && j1 <= 5 && (i1 & 1 << k1) == 0) {
                    if (flag) {
                        return;
                    }

                    block = world.getBlock(x + Direction.offsetX[k1], y, z + Direction.offsetZ[k1]);
                    if (block.getMaterial() == Material.air) {
                        l1 = k1 + 1 & 3;
                        i2 = k1 + 3 & 3;
                        if ((i1 & 1 << l1) != 0 && this.canVineAttachHere(
                            world.getBlock(
                                x + Direction.offsetX[k1] + Direction.offsetX[l1],
                                y,
                                z + Direction.offsetZ[k1] + Direction.offsetZ[l1]))) {
                            world.setBlock(x + Direction.offsetX[k1], y, z + Direction.offsetZ[k1], this, 1 << l1, 2);
                        } else if ((i1 & 1 << i2) != 0 && this.canVineAttachHere(
                            world.getBlock(
                                x + Direction.offsetX[k1] + Direction.offsetX[i2],
                                y,
                                z + Direction.offsetZ[k1] + Direction.offsetZ[i2]))) {
                                    world.setBlock(
                                        x + Direction.offsetX[k1],
                                        y,
                                        z + Direction.offsetZ[k1],
                                        this,
                                        1 << i2,
                                        2);
                                } else
                            if ((i1 & 1 << l1) != 0
                                && world.isAirBlock(
                                    x + Direction.offsetX[k1] + Direction.offsetX[l1],
                                    y,
                                    z + Direction.offsetZ[k1] + Direction.offsetZ[l1])
                                && this.canVineAttachHere(
                                    world.getBlock(x + Direction.offsetX[l1], y, z + Direction.offsetZ[l1]))) {
                                        world.setBlock(
                                            x + Direction.offsetX[k1] + Direction.offsetX[l1],
                                            y,
                                            z + Direction.offsetZ[k1] + Direction.offsetZ[l1],
                                            this,
                                            1 << (k1 + 2 & 3),
                                            2);
                                    } else
                                if ((i1 & 1 << i2) != 0
                                    && world.isAirBlock(
                                        x + Direction.offsetX[k1] + Direction.offsetX[i2],
                                        y,
                                        z + Direction.offsetZ[k1] + Direction.offsetZ[i2])
                                    && this.canVineAttachHere(
                                        world.getBlock(x + Direction.offsetX[i2], y, z + Direction.offsetZ[i2]))) {
                                            world.setBlock(
                                                x + Direction.offsetX[k1] + Direction.offsetX[i2],
                                                y,
                                                z + Direction.offsetZ[k1] + Direction.offsetZ[i2],
                                                this,
                                                1 << (k1 + 2 & 3),
                                                2);
                                        } else
                                    if (this.canVineAttachHere(
                                        world.getBlock(x + Direction.offsetX[k1], y + 1, z + Direction.offsetZ[k1]))) {
                                            world.setBlock(
                                                x + Direction.offsetX[k1],
                                                y,
                                                z + Direction.offsetZ[k1],
                                                this,
                                                0,
                                                2);
                                        }
                    } else if (block.getMaterial()
                        .isOpaque() && block.renderAsNormalBlock()) {
                            world.setBlockMetadataWithNotify(x, y, z, i1 | 1 << k1, 3);
                        }
                } else if (y > 1) {
                    block = world.getBlock(x, y - 1, z);
                    if (block.getMaterial() == Material.air) {
                        l1 = world.rand.nextInt(16) & i1;
                        if (l1 > 0) {
                            world.setBlock(x, y - 1, z, this, l1, 2);
                        }
                    } else if (block == this) {
                        l1 = world.rand.nextInt(16) & i1;
                        i2 = world.getBlockMetadata(x, y - 1, z);
                        if (i2 != (i2 | l1)) {
                            world.setBlockMetadataWithNotify(x, y - 1, z, i2 | l1, 3);
                        }
                    }
                }
            }
        }

    }

    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
        return this.plantData.attributes.category == PMPPlantCategory.VINE;
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return this.plantData.attributes.category == PMPPlantCategory.FLOATING
            || this.plantData.attributes.category == PMPPlantCategory.VINE;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList();
        if (ret == null) {
            return null;
        } else {
            if (this.hasFlowerColors()) {
                ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
            } else {
                ret.add(new ItemStack(this, 1));
            }

            return ret;
        }
    }

    public boolean growPlant(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        if (block != null && block instanceof PMPBlockPlant) {
            if (this.plantData.attributes.category != PMPPlantCategory.FLOATING
                && this.plantData.attributes.category != PMPPlantCategory.FLOWER_MULTI
                && this.plantData.attributes.category != PMPPlantCategory.EPIPHYTE) {
                int growthStage = world.getBlockMetadata(x, y, z);
                if (this.isFullyGrown(world.getBlockMetadata(x, y, z))) {
                    return false;
                } else {
                    if (this.plantData.attributes.growthType == PMPPlantGrowthType.DOUBLE && !this.isImmersedPlant()) {
                        switch (growthStage) {
                            case 0:
                                world.setBlockMetadataWithNotify(x, y, z, 1, 3);
                                return true;
                            case 1:
                                if (world.isAirBlock(x, y + 1, z) && this.plantData.attributes.growthStages > 2) {
                                    world.setBlockMetadataWithNotify(x, y, z, 2, 3);
                                    world.setBlock(x, y + 1, z, block, 3, 2);
                                    return true;
                                }
                                break;
                            case 2:
                                if (world.getBlock(x, y + 1, z) == block
                                    && this.plantData.attributes.growthStages > 3) {
                                    world.setBlockMetadataWithNotify(x, y, z, 4, 3);
                                    world.setBlockMetadataWithNotify(x, y + 1, z, 5, 3);
                                    return true;
                                }
                                break;
                            case 3:
                                if (world.getBlock(x, y - 1, z) == block
                                    && this.plantData.attributes.growthStages > 3) {
                                    world.setBlockMetadataWithNotify(x, y, z, 5, 3);
                                    world.setBlockMetadataWithNotify(x, y - 1, z, 4, 3);
                                    return true;
                                }
                                break;
                            case 4:
                                if (world.getBlock(x, y + 1, z) == block
                                    && this.plantData.attributes.growthStages > 4) {
                                    world.setBlockMetadataWithNotify(x, y, z, 6, 3);
                                    world.setBlockMetadataWithNotify(x, y + 1, z, 7, 3);
                                    return true;
                                }
                                break;
                            case 5:
                                if (world.getBlock(x, y - 1, z) == block
                                    && this.plantData.attributes.growthStages > 4) {
                                    world.setBlockMetadataWithNotify(x, y, z, 7, 3);
                                    world.setBlockMetadataWithNotify(x, y - 1, z, 6, 3);
                                    return true;
                                }
                                break;
                            default:
                                return false;
                        }
                    } else if (this.plantData.attributes.category == PMPPlantCategory.BAMBOO) {
                        return this.growStalkPlantFromBottom(world, x, y, z);
                    }

                    ++growthStage;
                    if (growthStage < this.plantData.attributes.growthStages) {
                        world.setBlockMetadataWithNotify(x, y, z, growthStage, 3);
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int getInventoryIconIndex() {
        return this.plantData.attributes.invIconIndex;
    }

    public boolean hasFlowerColors() {
        return this.plantData.attributes.renderType == PMPPlantRenderType.FLOATING_FLOWER
            || this.plantData.attributes.category == PMPPlantCategory.FLOWER_MULTI;
    }

    public boolean isFullyGrown(int metaData) {
        if (this.plantData.attributes.category != PMPPlantCategory.FLOATING
            && this.plantData.attributes.category != PMPPlantCategory.FLOWER_MULTI
            && this.plantData.attributes.category != PMPPlantCategory.GROUNDCOVER
            && this.plantData.attributes.category != PMPPlantCategory.VINE
            && this.plantData.attributes.category != PMPPlantCategory.EPIPHYTE) {
            if (this.plantData.attributes.growthType != PMPPlantGrowthType.SEAWEED
                && this.plantData.attributes.growthType != PMPPlantGrowthType.STALK) {
                if (this.plantData.attributes.growthType == PMPPlantGrowthType.DOUBLE && !this.isImmersedPlant()) {
                    if ((metaData == 4 || metaData == 5) && this.plantData.attributes.growthStages == 4) {
                        return true;
                    } else {
                        return (metaData == 6 || metaData == 7) && this.plantData.attributes.growthStages == 5;
                    }
                } else {
                    return metaData == this.plantData.attributes.growthStages - 1;
                }
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean isImmersedPlant() {
        return this.plantData.attributes.category == PMPPlantCategory.CROP_AQUATIC
            || this.plantData.attributes.category == PMPPlantCategory.IMMERSED;
    }

    public boolean isSubmergedPlant() {
        return this.plantData.attributes.category == PMPPlantCategory.CORAL
            || this.plantData.attributes.category == PMPPlantCategory.FRESHWATER
            || this.plantData.attributes.category == PMPPlantCategory.SALTWATER;
    }

    public boolean isTopPlantBlock(World world, int x, int y, int z) {
        if (this.plantData.attributes.growthType != PMPPlantGrowthType.SEAWEED
            && this.plantData.attributes.growthType != PMPPlantGrowthType.STALK
            && this.plantData.attributes.category != PMPPlantCategory.VINE) {
            int metaData = world.getBlockMetadata(x, y, z);
            if (this.plantData.attributes.growthType == PMPPlantGrowthType.DOUBLE && !this.isImmersedPlant()) {
                return metaData == 3 || metaData == 5 || metaData == 7;
            } else {
                return true;
            }
        } else {
            return this != world.getBlock(x, y + 1, z);
        }
    }

    private boolean growStalkPlantFromBottom(World world, int x, int y, int z) {
        int currentHeight = this.getPlantHeightFromBase(world, x, y, z);
        if (world.isAirBlock(x, y + currentHeight, z) && currentHeight < this.plantData.attributes.growthMax) {
            world.setBlock(x, y + currentHeight, z, this, 4, 2);
            this.updateBambooFromTop(world, x, y + currentHeight, z);
            return true;
        } else {
            return false;
        }
    }

    public void updateBambooFromTop(World world, int x, int y, int z) {
        int currentHeight = y;
        int count = this.getPlantHeightFromTop(world, x, y, z);

        for (int icon = 4; count > 0; world.setBlockMetadataWithNotify(x, currentHeight, z, icon, 3)) {
            --count;
            --currentHeight;
            if (icon > 0) {
                switch (this.plantData.attributes.renderSubtype) {
                    case 0:
                    default:
                        --icon;
                        break;
                    case 1:
                        if (count > 3) {
                            icon = 3;
                        } else {
                            --icon;
                        }
                        break;
                    case 2:
                        icon = 1 + world.rand.nextInt(3);
                }
            }
        }

    }

    public int getPlantHeightFromBase(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        Block blockAbove = world.getBlock(x, y + 1, z);

        int height;
        for (height = 1; block == blockAbove; blockAbove = world.getBlock(x, y + height, z)) {
            ++height;
        }

        return height;
    }

    public int getPlantHeightFromTop(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        Block blockBelow = world.getBlock(x, y - 1, z);

        int height;
        for (height = 1; block == blockBelow; blockBelow = world.getBlock(x, y - height, z)) {
            ++height;
        }

        return height;
    }

    public int getSubBlocksCount() {
        int count = 0;
        int index;
        if (this.plantData.attributes.category == PMPPlantCategory.FLOATING) {
            for (index = 0; index < this.plantData.attributes.plantData.length(); ++index) {
                if (this.plantData.attributes.plantData.charAt(index) == '1') {
                    ++count;
                }
            }

            if (count < 0 || count > 15) {
                count = 0;
            }
        } else if (this.plantData.attributes.category == PMPPlantCategory.FLOWER_MULTI) {
            for (index = 0; index < this.plantData.attributes.plantData.length(); ++index) {
                if (this.plantData.attributes.plantData.charAt(index) == '1') {
                    ++count;
                }
            }

            if (count < 0 || count > 15) {
                count = 0;
            }
        }

        return count;
    }

    private boolean func_150093_a(Block block) {
        return block.renderAsNormalBlock() && block.getMaterial()
            .blocksMovement();
    }

    private boolean func_150094_e(World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        int i1 = l;
        if (l > 0) {
            for (int j1 = 0; j1 <= 3; ++j1) {
                int k1 = 1 << j1;
                if ((l & k1) != 0
                    && !this.func_150093_a(world.getBlock(x + Direction.offsetX[j1], y, z + Direction.offsetZ[j1]))
                    && (world.getBlock(x, y + 1, z) != this || (world.getBlockMetadata(x, y + 1, z) & k1) == 0)) {
                    i1 &= ~k1;
                }
            }
        }

        if (i1 == 0 && !this.func_150093_a(world.getBlock(x, y + 1, z))) {
            return false;
        } else {
            if (i1 != l) {
                world.setBlockMetadataWithNotify(x, y, z, i1, 2);
            }

            return true;
        }
    }

    private ItemStack getBerryItemStack(int metaData, int quantity) {
        String blockName = this.getUnlocalizedName()
            .substring(5);
        ItemStack berries = null;
        if (this.isFullyGrown(metaData)) {
            if (blockName.matches("berrybushBeauty")) {
                berries = new ItemStack(PlantMegaPack.items.getFoodItem("berriesBeauty"), quantity, 0);
            } else if (blockName.matches("berrybushBlack")) {
                berries = new ItemStack(PlantMegaPack.items.getFoodItem("berriesBlack"), quantity, 0);
            } else if (blockName.matches("berrybushBlue")) {
                berries = new ItemStack(PlantMegaPack.items.getFoodItem("berriesBlue"), quantity, 0);
            } else if (blockName.matches("berrybushElder")) {
                berries = new ItemStack(PlantMegaPack.items.getFoodItem("berriesElder"), quantity, 0);
            } else if (blockName.matches("berrybushGoose")) {
                berries = new ItemStack(PlantMegaPack.items.getFoodItem("berriesGoose"), quantity, 0);
            } else if (blockName.matches("berrybushHuckle")) {
                berries = new ItemStack(PlantMegaPack.items.getFoodItem("berriesHuckle"), quantity, 0);
            } else if (blockName.matches("berrybushOrange")) {
                berries = new ItemStack(PlantMegaPack.items.getFoodItem("berriesOrange"), quantity, 0);
            } else if (blockName.matches("berrybushSnow")) {
                berries = new ItemStack(PlantMegaPack.items.getFoodItem("berriesSnow"), quantity, 0);
            } else if (blockName.matches("berrybushStraw")) {
                berries = new ItemStack(PlantMegaPack.items.getFoodItem("berriesStraw"), quantity, 0);
            }
        }

        return berries;
    }

    private ItemStack getFoodItemStack(String name, int quantity) {
        if (name.matches("cropBeet")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodBeet"), quantity, 0);
        } else if (name.matches("cropBellPepperOrange")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodBellPepperOrange"), quantity, 0);
        } else if (name.matches("cropBellPepperRed")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodBellPepperRed"), quantity, 0);
        } else if (name.matches("cropBellPepperYellow")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodBellPepperYellow"), quantity, 0);
        } else if (name.matches("cropBroccoli")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodBroccoli"), quantity, 0);
        } else if (name.matches("cropCassava")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodCassavaRoot"), quantity, 0);
        } else if (name.matches("cropCelery")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodCelery"), quantity, 0);
        } else if (name.matches("cropCorn")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodCorn"), quantity, 0);
        } else if (name.matches("cropCucumber")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodCucumber"), quantity, 0);
        } else if (name.matches("cropEggplant")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodEggplant"), quantity, 0);
        } else if (name.matches("cropGreenBeans")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodGreenBeans"), quantity, 0);
        } else if (name.matches("cropLeek")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodLeek"), quantity, 0);
        } else if (name.matches("cropLettuce")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodLettuce"), quantity, 0);
        } else if (name.matches("cropOnion")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodOnion"), quantity, 0);
        } else if (name.matches("cropPeanut")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodPeanuts"), quantity, 0);
        } else if (name.matches("cropRice")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodRice"), quantity, 0);
        } else if (name.matches("cropSorrel")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodSorrel"), quantity, 0);
        } else if (name.matches("cropSpinach")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodSpinach"), quantity, 0);
        } else if (name.matches("cropTomato")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodTomato"), quantity, 0);
        } else {
            return name.matches("cropWildRice")
                ? new ItemStack(PlantMegaPack.items.getFoodItem("foodWildRice"), quantity, 0)
                : null;
        }
    }

    private ItemStack getSeedItemStack(String name, int quantity) {
        if (name.matches("cropBeet")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedBeet"), quantity, 0);
        } else if (name.matches("cropBellPepperOrange")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedBellPepperOrange"), quantity, 0);
        } else if (name.matches("cropBellPepperRed")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedBellPepperRed"), quantity, 0);
        } else if (name.matches("cropBellPepperYellow")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedBellPepperYellow"), quantity, 0);
        } else if (name.matches("cropBroccoli")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedBroccoli"), quantity, 0);
        } else if (name.matches("cropCassava")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedCassava"), quantity, 0);
        } else if (name.matches("cropCelery")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedCelery"), quantity, 0);
        } else if (name.matches("cropCorn")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedCorn"), quantity, 0);
        } else if (name.matches("cropCucumber")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedCucumber"), quantity, 0);
        } else if (name.matches("cropEggplant")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedEggplant"), quantity, 0);
        } else if (name.matches("cropGreenBeans")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedGreenBean"), quantity, 0);
        } else if (name.matches("cropLeek")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedLeek"), quantity, 0);
        } else if (name.matches("cropLettuce")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedLettuce"), quantity, 0);
        } else if (name.matches("cropOnion")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedOnion"), quantity, 0);
        } else if (name.matches("cropPeanut")) {
            return new ItemStack(PlantMegaPack.items.getFoodItem("foodPeanuts"), quantity, 0);
        } else if (name.matches("cropSorrel")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedSorrel"), quantity, 0);
        } else if (name.matches("cropSpinach")) {
            return new ItemStack(PlantMegaPack.items.getCropSeed("seedSpinach"), quantity, 0);
        } else {
            return name.matches("cropTomato")
                ? new ItemStack(PlantMegaPack.items.getCropSeed("seedTomato"), quantity, 0)
                : null;
        }
    }
}
