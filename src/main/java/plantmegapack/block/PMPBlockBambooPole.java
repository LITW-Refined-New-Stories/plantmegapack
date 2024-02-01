package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.bin.PMPRenderers;
import plantmegapack.common.PMPTab;

public class PMPBlockBambooPole extends Block {

    @SideOnly(Side.CLIENT)
    protected IIcon[] textures;

    public PMPBlockBambooPole(String blockName) {
        super(Material.wood);
        this.setHardness(1.2F);
        this.setStepSound(soundTypeWood);
        this.setBlockName(blockName);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
        this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
        GameRegistry.registerBlock(this, blockName);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        String iconName = this.getUnlocalizedName()
            .substring(5);
        this.textures = new IIcon[2];

        for (int count = 0; count < 2; ++count) {
            this.textures[count] = ir.registerIcon("plantmegapack:" + iconName + count);
        }

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData) {
        return metaData >= 0 && metaData <= 1 ? this.textures[metaData] : this.textures[0];
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(
            (double) ((float) x + 0.375F),
            (double) y,
            (double) ((float) z + 0.375F),
            (double) ((float) x + 0.625F),
            (double) ((float) y + 1.0F),
            (double) ((float) z + 0.625F));
    }

    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }

    public int damageDropped(int metaData) {
        return metaData;
    }

    public int getRenderType() {
        return PMPRenderers.renderBambooPoleID;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        return true;
    }
}
