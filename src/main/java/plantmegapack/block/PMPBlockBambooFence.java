package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPTab;

public class PMPBlockBambooFence extends BlockFence {

    private PMPBlockBambooBlock blockParent;

    public PMPBlockBambooFence(String blockName, PMPBlockBambooBlock blockParent) {
        super(blockName, Material.wood);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
        this.blockParent = blockParent;
        this.setHardness(1.2F);
        GameRegistry.registerBlock(this, blockName);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {}

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData) {
        return side != 0 && side != 1 ? this.blockParent.getSideIcon(side) : this.blockParent.getTopIcon(side);
    }

    public String getUnlocalizedName() {
        String name = this.blockParent.getUnlocalizedName();
        return name.substring(0, name.length() - 5) + "Fence";
    }

    public boolean canConnectFenceTo(IBlockAccess p_149826_1_, int p_149826_2_, int p_149826_3_, int p_149826_4_) {
        Block block = p_149826_1_.getBlock(p_149826_2_, p_149826_3_, p_149826_4_);
        return block != this && block != Blocks.fence_gate && block.getClass() != PMPBlockBambooGate.class
            ? (block.getMaterial()
                .isOpaque() && block.renderAsNormalBlock() ? block.getMaterial() != Material.gourd : false)
            : true;
    }

    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        return true;
    }
}
