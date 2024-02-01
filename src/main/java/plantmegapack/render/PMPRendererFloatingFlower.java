package plantmegapack.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import plantmegapack.bin.PMPRenderers;
import plantmegapack.block.PMPBlockPlant;
import plantmegapack.common.PMPPlantRenderType;

public class PMPRendererFloatingFlower implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return PMPRenderers.renderFloatingFlowerID;
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        if (!(block instanceof PMPBlockPlant)) {
            return false;
        } else {
            Tessellator tessellator = Tessellator.instance;
            this.renderLeaf(world, tessellator, (PMPBlockPlant) block, x, y, z);
            this.renderFlower(world, tessellator, (PMPBlockPlant) block, x, y, z);
            return true;
        }
    }

    private boolean renderLeaf(IBlockAccess world, Tessellator tessellator, PMPBlockPlant block, int x, int y, int z) {
        IIcon iicon = block.getIcon(1, 0);
        float f = 0.015625F;
        double d0 = (double) iicon.getMinU();
        double d1 = (double) iicon.getMinV();
        double d2 = (double) iicon.getMaxU();
        double d3 = (double) iicon.getMaxV();
        long l = (long) (x * 3129871) ^ (long) z * 116129781L ^ (long) y;
        l = l * l * 42317861L + l * 11L;
        int i1 = (int) (l >> 16 & 3L);
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        float f1 = (float) x + 0.5F;
        float f2 = (float) z + 0.5F;
        float f3 = (float) (i1 & 1) * 0.5F * (float) (1 - i1 / 2 % 2 * 2);
        float f4 = (float) (i1 + 1 & 1) * 0.5F * (float) (1 - (i1 + 1) / 2 % 2 * 2);
        tessellator.setColorOpaque_I(block.getBlockColor());
        tessellator.addVertexWithUV((double) (f1 + f3 - f4), (double) ((float) y + f), (double) (f2 + f3 + f4), d0, d1);
        tessellator.addVertexWithUV((double) (f1 + f3 + f4), (double) ((float) y + f), (double) (f2 - f3 + f4), d2, d1);
        tessellator.addVertexWithUV((double) (f1 - f3 + f4), (double) ((float) y + f), (double) (f2 - f3 - f4), d2, d3);
        tessellator.addVertexWithUV((double) (f1 - f3 - f4), (double) ((float) y + f), (double) (f2 + f3 - f4), d0, d3);
        tessellator.setColorOpaque_I((block.getBlockColor() & 16711422) >> 1);
        tessellator.addVertexWithUV((double) (f1 - f3 - f4), (double) ((float) y + f), (double) (f2 + f3 - f4), d0, d3);
        tessellator.addVertexWithUV((double) (f1 - f3 + f4), (double) ((float) y + f), (double) (f2 - f3 - f4), d2, d3);
        tessellator.addVertexWithUV((double) (f1 + f3 + f4), (double) ((float) y + f), (double) (f2 - f3 + f4), d2, d1);
        tessellator.addVertexWithUV((double) (f1 + f3 - f4), (double) ((float) y + f), (double) (f2 + f3 + f4), d0, d1);
        return true;
    }

    private void renderFlower(IBlockAccess world, Tessellator tessellator, PMPBlockPlant block, int x, int y, int z) {
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        int l = block.colorMultiplier(world, x, y, z);
        float f = (float) (l >> 16 & 255) / 255.0F;
        float f1 = (float) (l >> 8 & 255) / 255.0F;
        float f2 = (float) (l & 255) / 255.0F;
        if (EntityRenderer.anaglyphEnable) {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }

        tessellator.setColorOpaque_F(f, f1, f2);
        double d1 = (double) x;
        double d2 = (double) y;
        double d0 = (double) z;
        long i1 = (long) (x * 3129871) ^ (long) z * 116129781L ^ (long) y;
        i1 = i1 * i1 * 42317861L + i1 * 11L;
        d1 += ((double) ((float) (i1 >> 16 & 15L) / 15.0F) - 0.5) * 0.3;
        d0 += ((double) ((float) (i1 >> 24 & 15L) / 15.0F) - 0.5) * 0.3;
        IIcon iicon = null;
        if (block.hasFlowerColors()) {
            iicon = block.getIcon(0, world.getBlockMetadata(x, y, z));
        } else if (block.plantData.attributes.renderType == PMPPlantRenderType.FLOATING_PLANT) {
            iicon = block.getIcon(0, 1);
        }

        if (iicon != null) {
            this.drawCrossedSquares(iicon, d1, d2, d0, 1.0F);
        }

    }

    private void drawCrossedSquares(IIcon p_147765_1_, double p_147765_2_, double p_147765_4_, double p_147765_6_,
        float p_147765_8_) {
        Tessellator tessellator = Tessellator.instance;
        double d3 = (double) p_147765_1_.getMinU();
        double d4 = (double) p_147765_1_.getMinV();
        double d5 = (double) p_147765_1_.getMaxU();
        double d6 = (double) p_147765_1_.getMaxV();
        double d7 = 0.45 * (double) p_147765_8_;
        double d8 = p_147765_2_ + 0.5 - d7;
        double d9 = p_147765_2_ + 0.5 + d7;
        double d10 = p_147765_6_ + 0.5 - d7;
        double d11 = p_147765_6_ + 0.5 + d7;
        tessellator.addVertexWithUV(d8, p_147765_4_ + (double) p_147765_8_, d10, d3, d4);
        tessellator.addVertexWithUV(d8, p_147765_4_ + 0.0, d10, d3, d6);
        tessellator.addVertexWithUV(d9, p_147765_4_ + 0.0, d11, d5, d6);
        tessellator.addVertexWithUV(d9, p_147765_4_ + (double) p_147765_8_, d11, d5, d4);
        tessellator.addVertexWithUV(d9, p_147765_4_ + (double) p_147765_8_, d11, d3, d4);
        tessellator.addVertexWithUV(d9, p_147765_4_ + 0.0, d11, d3, d6);
        tessellator.addVertexWithUV(d8, p_147765_4_ + 0.0, d10, d5, d6);
        tessellator.addVertexWithUV(d8, p_147765_4_ + (double) p_147765_8_, d10, d5, d4);
        tessellator.addVertexWithUV(d8, p_147765_4_ + (double) p_147765_8_, d11, d3, d4);
        tessellator.addVertexWithUV(d8, p_147765_4_ + 0.0, d11, d3, d6);
        tessellator.addVertexWithUV(d9, p_147765_4_ + 0.0, d10, d5, d6);
        tessellator.addVertexWithUV(d9, p_147765_4_ + (double) p_147765_8_, d10, d5, d4);
        tessellator.addVertexWithUV(d9, p_147765_4_ + (double) p_147765_8_, d10, d3, d4);
        tessellator.addVertexWithUV(d9, p_147765_4_ + 0.0, d10, d3, d6);
        tessellator.addVertexWithUV(d8, p_147765_4_ + 0.0, d11, d5, d6);
        tessellator.addVertexWithUV(d8, p_147765_4_ + (double) p_147765_8_, d11, d5, d4);
    }
}
