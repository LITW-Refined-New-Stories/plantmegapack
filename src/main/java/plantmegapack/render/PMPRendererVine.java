package plantmegapack.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import plantmegapack.bin.PMPRenderers;
import plantmegapack.block.PMPBlockPlant;
import plantmegapack.common.PMPPlantRenderType;

public class PMPRendererVine implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return PMPRenderers.renderVineID;
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        if (!(block instanceof PMPBlockPlant)) {
            return false;
        } else {
            Tessellator tessellator = Tessellator.instance;
            return this.renderBlockVine(world, tessellator, (PMPBlockPlant) block, x, y, z);
        }
    }

    private boolean renderBlockVine(IBlockAccess world, Tessellator tessellator, PMPBlockPlant block, int x, int y,
        int z) {
        IIcon iicon = block.getIcon(-1, this.getVineIcon(world, block, x, y, z));
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        int l = block.colorMultiplier(world, x, y, z);
        float f = (float) (l >> 16 & 255) / 255.0F;
        float f1 = (float) (l >> 8 & 255) / 255.0F;
        float f2 = (float) (l & 255) / 255.0F;
        tessellator.setColorOpaque_F(f, f1, f2);
        double d3 = (double) iicon.getMinU();
        double d4 = (double) iicon.getMinV();
        double d0 = (double) iicon.getMaxU();
        double d1 = (double) iicon.getMaxV();
        double d2 = 0.05000000074505806;
        int i1 = world.getBlockMetadata(x, y, z);
        if ((i1 & 2) != 0) {
            tessellator.addVertexWithUV((double) x + d2, (double) (y + 1), (double) (z + 1), d3, d4);
            tessellator.addVertexWithUV((double) x + d2, (double) (y + 0), (double) (z + 1), d3, d1);
            tessellator.addVertexWithUV((double) x + d2, (double) (y + 0), (double) (z + 0), d0, d1);
            tessellator.addVertexWithUV((double) x + d2, (double) (y + 1), (double) (z + 0), d0, d4);
            tessellator.addVertexWithUV((double) x + d2, (double) (y + 1), (double) (z + 0), d0, d4);
            tessellator.addVertexWithUV((double) x + d2, (double) (y + 0), (double) (z + 0), d0, d1);
            tessellator.addVertexWithUV((double) x + d2, (double) (y + 0), (double) (z + 1), d3, d1);
            tessellator.addVertexWithUV((double) x + d2, (double) (y + 1), (double) (z + 1), d3, d4);
        }

        if ((i1 & 8) != 0) {
            tessellator.addVertexWithUV((double) (x + 1) - d2, (double) (y + 0), (double) (z + 1), d0, d1);
            tessellator.addVertexWithUV((double) (x + 1) - d2, (double) (y + 1), (double) (z + 1), d0, d4);
            tessellator.addVertexWithUV((double) (x + 1) - d2, (double) (y + 1), (double) (z + 0), d3, d4);
            tessellator.addVertexWithUV((double) (x + 1) - d2, (double) (y + 0), (double) (z + 0), d3, d1);
            tessellator.addVertexWithUV((double) (x + 1) - d2, (double) (y + 0), (double) (z + 0), d3, d1);
            tessellator.addVertexWithUV((double) (x + 1) - d2, (double) (y + 1), (double) (z + 0), d3, d4);
            tessellator.addVertexWithUV((double) (x + 1) - d2, (double) (y + 1), (double) (z + 1), d0, d4);
            tessellator.addVertexWithUV((double) (x + 1) - d2, (double) (y + 0), (double) (z + 1), d0, d1);
        }

        if ((i1 & 4) != 0) {
            tessellator.addVertexWithUV((double) (x + 1), (double) (y + 0), (double) z + d2, d0, d1);
            tessellator.addVertexWithUV((double) (x + 1), (double) (y + 1), (double) z + d2, d0, d4);
            tessellator.addVertexWithUV((double) (x + 0), (double) (y + 1), (double) z + d2, d3, d4);
            tessellator.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) z + d2, d3, d1);
            tessellator.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) z + d2, d3, d1);
            tessellator.addVertexWithUV((double) (x + 0), (double) (y + 1), (double) z + d2, d3, d4);
            tessellator.addVertexWithUV((double) (x + 1), (double) (y + 1), (double) z + d2, d0, d4);
            tessellator.addVertexWithUV((double) (x + 1), (double) (y + 0), (double) z + d2, d0, d1);
        }

        if ((i1 & 1) != 0) {
            tessellator.addVertexWithUV((double) (x + 1), (double) (y + 1), (double) (z + 1) - d2, d3, d4);
            tessellator.addVertexWithUV((double) (x + 1), (double) (y + 0), (double) (z + 1) - d2, d3, d1);
            tessellator.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) (z + 1) - d2, d0, d1);
            tessellator.addVertexWithUV((double) (x + 0), (double) (y + 1), (double) (z + 1) - d2, d0, d4);
            tessellator.addVertexWithUV((double) (x + 0), (double) (y + 1), (double) (z + 1) - d2, d0, d4);
            tessellator.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) (z + 1) - d2, d0, d1);
            tessellator.addVertexWithUV((double) (x + 1), (double) (y + 0), (double) (z + 1) - d2, d3, d1);
            tessellator.addVertexWithUV((double) (x + 1), (double) (y + 1), (double) (z + 1) - d2, d3, d4);
        }

        return true;
    }

    private int getVineIcon(IBlockAccess world, PMPBlockPlant block, int x, int y, int z) {
        if (block.plantData.attributes.renderType != PMPPlantRenderType.VINE_FLOWER
            && block.plantData.attributes.renderType != PMPPlantRenderType.VINE_NORMAL) {
            if (block.plantData.attributes.renderType == PMPPlantRenderType.VINE_RANDOM) {
                if (world.getBlock(x, y + 1, z) != block) {
                    return 0;
                } else if (world.getBlock(x, y - 1, z) != block) {
                    return 4;
                } else if (y % 7 == 0) {
                    return 1;
                } else if (y % 3 == 0) {
                    return 2;
                } else {
                    return y % 2 == 0 ? 3 : 1;
                }
            } else {
                return 0;
            }
        } else if (world.getBlock(x, y + 1, z) == block) {
            return world.getBlock(x, y - 1, z) == block ? 1 : 2;
        } else {
            return 0;
        }
    }
}
