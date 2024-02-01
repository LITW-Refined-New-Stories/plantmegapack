package plantmegapack.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import plantmegapack.bin.PMPRenderers;
import plantmegapack.block.PMPBlockBambooPole;

public class PMPRendererBambooPole implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return PMPRenderers.renderBambooPoleID;
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        if (!(block instanceof PMPBlockBambooPole)) {
            return false;
        } else {
            Tessellator tessellator = Tessellator.instance;
            IIcon icon = block.getIcon(0, 1);
            double iconMinU = (double) icon.getMinU();
            double iconMinV = (double) icon.getMinV();
            double iconMaxV = (double) icon.getMaxV();
            float cf = 1.0F;
            int colorMultiplier = block.colorMultiplier(renderer.blockAccess, x, y, z);
            float colorRED = (float) (colorMultiplier >> 16 & 255) / 255.0F;
            float colorGRN = (float) (colorMultiplier >> 8 & 255) / 255.0F;
            float colorBLU = (float) (colorMultiplier & 255) / 255.0F;
            if (EntityRenderer.anaglyphEnable) {
                colorRED = (colorRED * 30.0F + colorGRN * 59.0F + colorBLU * 11.0F) / 100.0F;
                colorGRN = (colorRED * 30.0F + colorGRN * 70.0F) / 100.0F;
                colorBLU = (colorRED * 30.0F + colorBLU * 70.0F) / 100.0F;
            }

            tessellator.setNormal(0.0F, 0.0F, 0.0F);
            tessellator.setColorOpaque_F(cf * colorRED, cf * colorGRN, cf * colorBLU);
            tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
            tessellator.addTranslation((float) x, (float) y, (float) z);
            double d7 = 0.378;
            double d8 = 0.622;
            tessellator.addVertexWithUV(d8, 0.998, d7, iconMinU, iconMinV);
            tessellator.addVertexWithUV(d8, 0.002, d7, iconMinU, iconMaxV);
            tessellator.addVertexWithUV(d7, 0.002, d7, (double) icon.getInterpolatedU(4.0), iconMaxV);
            tessellator.addVertexWithUV(d7, 0.998, d7, (double) icon.getInterpolatedU(4.0), iconMinV);
            tessellator.addVertexWithUV(d7, 0.998, d8, iconMinU, iconMinV);
            tessellator.addVertexWithUV(d7, 0.002, d8, iconMinU, iconMaxV);
            tessellator.addVertexWithUV(d8, 0.002, d8, (double) icon.getInterpolatedU(4.0), iconMaxV);
            tessellator.addVertexWithUV(d8, 0.998, d8, (double) icon.getInterpolatedU(4.0), iconMinV);
            tessellator.addVertexWithUV(d7, 0.998, d7, iconMinU, iconMinV);
            tessellator.addVertexWithUV(d7, 0.002, d7, iconMinU, iconMaxV);
            tessellator.addVertexWithUV(d7, 0.002, d8, (double) icon.getInterpolatedU(4.0), iconMaxV);
            tessellator.addVertexWithUV(d7, 0.998, d8, (double) icon.getInterpolatedU(4.0), iconMinV);
            tessellator.addVertexWithUV(d8, 0.998, d8, iconMinU, iconMinV);
            tessellator.addVertexWithUV(d8, 0.002, d8, iconMinU, iconMaxV);
            tessellator.addVertexWithUV(d8, 0.002, d7, (double) icon.getInterpolatedU(4.0), iconMaxV);
            tessellator.addVertexWithUV(d8, 0.998, d7, (double) icon.getInterpolatedU(4.0), iconMinV);
            tessellator.addVertexWithUV(d8, 0.998, d7, (double) icon.getInterpolatedU(8.0), iconMinV);
            tessellator.addVertexWithUV(d7, 0.998, d7, (double) icon.getInterpolatedU(4.0), iconMinV);
            tessellator.addVertexWithUV(
                d7,
                0.998,
                d8,
                (double) icon.getInterpolatedU(4.0),
                (double) icon.getInterpolatedV(4.0));
            tessellator.addVertexWithUV(
                d8,
                0.998,
                d8,
                (double) icon.getInterpolatedU(8.0),
                (double) icon.getInterpolatedV(4.0));
            tessellator.addVertexWithUV(d8, 0.002, d8, (double) icon.getInterpolatedU(8.0), iconMinV);
            tessellator.addVertexWithUV(d7, 0.002, d8, (double) icon.getInterpolatedU(4.0), iconMinV);
            tessellator.addVertexWithUV(
                d7,
                0.002,
                d7,
                (double) icon.getInterpolatedU(4.0),
                (double) icon.getInterpolatedV(4.0));
            tessellator.addVertexWithUV(
                d8,
                0.002,
                d7,
                (double) icon.getInterpolatedU(8.0),
                (double) icon.getInterpolatedV(4.0));
            tessellator.addTranslation((float) (-x), (float) (-y), (float) (-z));
            return true;
        }
    }
}
