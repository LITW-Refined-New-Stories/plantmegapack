package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.bin.PMPRenderers;
import plantmegapack.common.PMPTab;
import plantmegapack.item.PMPItemWallBracket;

public class PMPBlockWallBracket extends Block {

    @SideOnly(Side.CLIENT)
    protected IIcon modelTexture;
    private int renderType;

    public PMPBlockWallBracket(String blockName, int renderTP) {
        super(Material.wood);
        this.setHardness(0.2F);
        this.setBlockName(blockName);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
        this.renderType = renderTP >= 0 && renderTP <= 1 ? renderTP : 0;
        GameRegistry.registerBlock(this, PMPItemWallBracket.class, blockName);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        String name = this.getUnlocalizedName()
            .substring(5);
        this.blockIcon = icon.registerIcon("plantmegapack:" + name + "0");
        this.modelTexture = icon.registerIcon("plantmegapack:" + name + "1");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData) {
        return metaData == 1 ? this.modelTexture : this.blockIcon;
    }

    public int getRenderType() {
        if (this.renderType == 0) {
            return PMPRenderers.renderWallBracketID;
        } else {
            return this.renderType == 1 ? PMPRenderers.renderWallBracketMetalID : 1;
        }
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ,
        int metaData) {
        int j1 = metaData;
        if ((metaData == 0 || side == 2) && this.canAttachToBlock(world, x, y, z + 1)) {
            j1 = 2;
        }

        if ((j1 == 0 || side == 3) && this.canAttachToBlock(world, x, y, z - 1)) {
            j1 = 3;
        }

        if ((j1 == 0 || side == 4) && this.canAttachToBlock(world, x + 1, y, z)) {
            j1 = 4;
        }

        if ((j1 == 0 || side == 5) && this.canAttachToBlock(world, x - 1, y, z)) {
            j1 = 5;
        }

        return j1;
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        boolean result = true;
        int metaData = world.getBlockMetadata(x, y, z);
        switch (metaData) {
            case 2:
                result = this.canAttachToBlock(world, x, y, z + 1);
                break;
            case 3:
                result = this.canAttachToBlock(world, x, y, z - 1);
                break;
            case 4:
                result = this.canAttachToBlock(world, x + 1, y, z);
                break;
            case 5:
                result = this.canAttachToBlock(world, x - 1, y, z);
        }

        return result;
    }

    private boolean canAttachToBlock(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        return block.isBlockSolid(world, x, y, z, 0);
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return this.canAttachToBlock(world, x - 1, y, z) || this.canAttachToBlock(world, x + 1, y, z)
            || this.canAttachToBlock(world, x, y, z - 1)
            || this.canAttachToBlock(world, x, y, z + 1);
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!this.canBlockStay(world, x, y, z)) {
            this.dropBlockAsItem(world, x, y, z, 0, 0);
            world.setBlockToAir(x, y, z);
        }

    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        if (this.renderType == 1) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
            int metaData = world.getBlockMetadata(x, y, z);
            switch (metaData) {
                case 2:
                    this.setBlockBounds(0.25F, 0.0F, 0.43F, 0.75F, 0.5F, 1.0F);
                    break;
                case 3:
                    this.setBlockBounds(0.25F, 0.0F, 0.0F, 0.75F, 0.5F, 0.57F);
                    break;
                case 4:
                    this.setBlockBounds(0.43F, 0.0F, 0.25F, 1.0F, 0.5F, 0.75F);
                    break;
                case 5:
                    this.setBlockBounds(0.0F, 0.0F, 0.25F, 0.57F, 0.5F, 0.75F);
                    break;
                default:
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }

        }
    }
}
