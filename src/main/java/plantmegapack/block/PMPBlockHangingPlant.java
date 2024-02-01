package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPTab;
import plantmegapack.item.PMPItemHangingPlant;

public class PMPBlockHangingPlant extends Block {

    @SideOnly(Side.CLIENT)
    protected IIcon[] textures;

    public PMPBlockHangingPlant(String blockName) {
        super(Material.plants);
        this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.9F, 0.9F);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
        this.setBlockName(blockName);
        this.setStepSound(Block.soundTypeGrass);
        GameRegistry.registerBlock(this, PMPItemHangingPlant.class, blockName);
        OreDictionary.registerOre(blockName, this);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        String iconName = this.getUnlocalizedName()
            .substring(5);
        this.textures = new IIcon[3];
        this.textures[0] = ir.registerIcon("plantmegapack:" + iconName + "0");
        this.textures[1] = ir.registerIcon("plantmegapack:" + iconName + "1");
        this.textures[2] = ir.registerIcon("plantmegapack:" + iconName + "2");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData) {
        return metaData < this.textures.length ? this.textures[metaData] : this.textures[0];
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        int metaData = world.getBlockMetadata(x, y, z);
        if (metaData == 0) {
            world.setBlock(x, y - 1, z, this, 1, 2);
        }

    }

    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData) {
        if (metaData == 0) {
            world.setBlockToAir(x, y - 1, z);
        } else {
            world.setBlockToAir(x, y + 1, z);
        }

    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return this.canBlockStay(world, x, y, z) && world.isAirBlock(x, y, z) && world.isAirBlock(x, y - 1, z);
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y + 1, z);
        if (world.getBlockMetadata(x, y, z) != 0) {
            return block == this;
        } else {
            return block.getClass() == PMPBlockWallBracket.class || block == Blocks.fence
                || block.getClass() == PMPBlockBambooFence.class
                || block.getClass() == PMPBlockBambooPole.class
                || block == Blocks.cobblestone_wall;
        }
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        int metaData = world.getBlockMetadata(x, y, z);
        if (!this.canBlockStay(world, x, y, z) && metaData == 0) {
            this.dropBlockAsItem(world, x, y, z, metaData, 0);
            world.setBlockToAir(x, y, z);
            world.setBlockToAir(x, y - 1, z);
        }

    }

    public int damageDropped(int damage) {
        return 0;
    }

    public int getRenderType() {
        return 1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        int metaData = world.getBlockMetadata(x, y, z);
        if (metaData == 0) {
            this.setBlockBounds(0.062F, -1.0F, 0.062F, 0.938F, 1.0F, 0.938F);
        } else {
            this.setBlockBounds(0.062F, 0.0F, 0.062F, 0.938F, 2.0F, 0.938F);
        }

    }
}
