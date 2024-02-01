package plantmegapack.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPTab;

public class PMPBlockBambooSlab extends BlockSlab {

    private PMPBlockBambooBlock blockParent;

    public PMPBlockBambooSlab(boolean doubleSlab, String blockName, PMPBlockBambooBlock blockParent) {
        super(doubleSlab, Material.wood);
        this.blockParent = blockParent;
        this.setBlockName(blockName);
        if (!doubleSlab) {
            this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
        }

        this.useNeighborBrightness = true;
        this.setLightOpacity(0);
        this.setHardness(1.2F);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {}

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData) {
        return side != 0 && side != 1 ? this.blockParent.getSideIcon(side) : this.blockParent.getTopIcon(side);
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        if (this.field_150004_a) {
            return super.shouldSideBeRendered(world, x, y, z, side);
        } else if (side != 1 && side != 0 && !super.shouldSideBeRendered(world, x, y, z, side)) {
            return false;
        } else {
            int i1 = x + Facing.offsetsXForSide[Facing.oppositeSide[side]];
            int j1 = y + Facing.offsetsYForSide[Facing.oppositeSide[side]];
            int k1 = z + Facing.offsetsZForSide[Facing.oppositeSide[side]];
            boolean flag = (world.getBlockMetadata(i1, j1, k1) & 8) != 0;
            return flag
                ? (side == 0 ? true
                    : (side == 1 && super.shouldSideBeRendered(world, x, y, z, side) ? true
                        : !func_150003_a(world.getBlock(x, y, z)) || (world.getBlockMetadata(x, y, z) & 8) == 0))
                : (side == 1 ? true
                    : (side == 0 && super.shouldSideBeRendered(world, x, y, z, side) ? true
                        : !func_150003_a(world.getBlock(x, y, z)) || (world.getBlockMetadata(x, y, z) & 8) != 0));
        }
    }

    public String func_150002_b(int metaData) {
        return super.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    private static boolean func_150003_a(Block block) {
        return PlantMegaPack.blocks.getBambooSlab(
            block.getUnlocalizedName()
                .substring(5))
            != null;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(this);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(this.getSingleSlabBlock());
    }

    protected ItemStack createStackedBlock(int damage) {
        return new ItemStack(this.getSingleSlabBlock(), 2, damage & 7);
    }

    private PMPBlockBambooSlab getSingleSlabBlock() {
        String name = this.blockParent.getUnlocalizedName()
            .substring(5);
        return PlantMegaPack.blocks.getBambooSlab(name + "Slab");
    }
}
