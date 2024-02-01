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

public class PMPRendererStalkPlant implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return PMPRenderers.renderStalkID;
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        if (!(block instanceof PMPBlockPlant)) {
            return false;
        } else {
            Tessellator tessellator = Tessellator.instance;
            IIcon icon = block.getIcon(0, world.getBlockMetadata(x, y, z));
            double iconMinU = (double) icon.getMinU();
            double iconMinV = (double) icon.getMinV();
            double iconMaxU = (double) icon.getMaxU();
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
            double d9 = 0.0;
            double d10 = 1.0;
            tessellator.addVertexWithUV(d7, 1.0, d9, iconMinU, iconMinV);
            tessellator.addVertexWithUV(d7, 0.0, d9, iconMinU, iconMaxV);
            tessellator.addVertexWithUV(d7, 0.0, d10, iconMaxU, iconMaxV);
            tessellator.addVertexWithUV(d7, 1.0, d10, iconMaxU, iconMinV);
            tessellator.addVertexWithUV(d7, 1.0, d10, iconMinU, iconMinV);
            tessellator.addVertexWithUV(d7, 0.0, d10, iconMinU, iconMaxV);
            tessellator.addVertexWithUV(d7, 0.0, d9, iconMaxU, iconMaxV);
            tessellator.addVertexWithUV(d7, 1.0, d9, iconMaxU, iconMinV);
            tessellator.addVertexWithUV(d8, 1.0, d10, iconMinU, iconMinV);
            tessellator.addVertexWithUV(d8, 0.0, d10, iconMinU, iconMaxV);
            tessellator.addVertexWithUV(d8, 0.0, d9, iconMaxU, iconMaxV);
            tessellator.addVertexWithUV(d8, 1.0, d9, iconMaxU, iconMinV);
            tessellator.addVertexWithUV(d8, 1.0, d9, iconMinU, iconMinV);
            tessellator.addVertexWithUV(d8, 0.0, d9, iconMinU, iconMaxV);
            tessellator.addVertexWithUV(d8, 0.0, d10, iconMaxU, iconMaxV);
            tessellator.addVertexWithUV(d8, 1.0, d10, iconMaxU, iconMinV);
            d7 = 0.0;
            d8 = 1.0;
            d9 = 0.378;
            d10 = 0.622;
            tessellator.addVertexWithUV(d7, 1.0, d9, iconMinU, iconMinV);
            tessellator.addVertexWithUV(d7, 0.0, d9, iconMinU, iconMaxV);
            tessellator.addVertexWithUV(d8, 0.0, d9, iconMaxU, iconMaxV);
            tessellator.addVertexWithUV(d8, 1.0, d9, iconMaxU, iconMinV);
            tessellator.addVertexWithUV(d8, 1.0, d9, iconMinU, iconMinV);
            tessellator.addVertexWithUV(d8, 0.0, d9, iconMinU, iconMaxV);
            tessellator.addVertexWithUV(d7, 0.0, d9, iconMaxU, iconMaxV);
            tessellator.addVertexWithUV(d7, 1.0, d9, iconMaxU, iconMinV);
            tessellator.addVertexWithUV(d8, 1.0, d10, iconMinU, iconMinV);
            tessellator.addVertexWithUV(d8, 0.0, d10, iconMinU, iconMaxV);
            tessellator.addVertexWithUV(d7, 0.0, d10, iconMaxU, iconMaxV);
            tessellator.addVertexWithUV(d7, 1.0, d10, iconMaxU, iconMinV);
            tessellator.addVertexWithUV(d7, 1.0, d10, iconMinU, iconMinV);
            tessellator.addVertexWithUV(d7, 0.0, d10, iconMinU, iconMaxV);
            tessellator.addVertexWithUV(d8, 0.0, d10, iconMaxU, iconMaxV);
            tessellator.addVertexWithUV(d8, 1.0, d10, iconMaxU, iconMinV);
            tessellator.addTranslation((float) (-x), (float) (-y), (float) (-z));
            return true;
        }
    }
}
