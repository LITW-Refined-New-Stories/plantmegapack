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

public class PMPRendererEpiphyteVertical implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return PMPRenderers.renderEpiphyteVerticalID;
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        if (!(block instanceof PMPBlockPlant)) {
            return false;
        } else {
            Tessellator tessellator = Tessellator.instance;
            IIcon icon = block.getIcon(0, 0);
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
            switch (world.getBlockMetadata(x, y, z)) {
                case 2:
                    this.renderNorth(tessellator, iconMinU, iconMaxU, iconMinV, iconMaxV);
                    break;
                case 3:
                    this.renderSouth(tessellator, iconMinU, iconMaxU, iconMinV, iconMaxV);
                    break;
                case 4:
                    this.renderWest(tessellator, iconMinU, iconMaxU, iconMinV, iconMaxV);
                    break;
                case 5:
                    this.renderEast(tessellator, iconMinU, iconMaxU, iconMinV, iconMaxV);
            }

            tessellator.addTranslation((float) (-x), (float) (-y), (float) (-z));
            return true;
        }
    }

    private void renderNorth(Tessellator tessellator, double iconMinU, double iconMaxU, double iconMinV,
        double iconMaxV) {
        tessellator.addVertexWithUV(1.0, 0.0, 0.1, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.1, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.5, 1.0, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.5, 0.0, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 0.0, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 1.0, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.1, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.1, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 0.0, 0.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 1.0, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.5, 1.0, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.5, 0.0, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 0.0, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 1.0, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.5, 1.0, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.5, 0.0, 0.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.1, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.1, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.5, 1.0, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.5, 0.0, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 0.0, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 1.0, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.1, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.1, iconMaxU, iconMaxV);
    }

    private void renderSouth(Tessellator tessellator, double iconMinU, double iconMaxU, double iconMinV,
        double iconMaxV) {
        tessellator.addVertexWithUV(0.5, 0.0, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 1.0, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.9, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.9, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.9, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.9, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.5, 1.0, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.5, 0.0, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 0.0, 1.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 1.0, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.5, 1.0, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.5, 0.0, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 0.0, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 1.0, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.5, 1.0, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.5, 0.0, 1.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 0.0, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 1.0, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.9, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.9, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.9, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.9, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.5, 1.0, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.5, 0.0, 0.0, iconMinU, iconMaxV);
    }

    private void renderWest(Tessellator tessellator, double iconMinU, double iconMaxU, double iconMinV,
        double iconMaxV) {
        tessellator.addVertexWithUV(0.1, 0.0, 0.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.1, 1.0, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.5, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.5, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.1, 1.0, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.1, 0.0, 0.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.5, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.5, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.5, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.5, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.5, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.5, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.1, 0.0, 1.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.1, 1.0, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.5, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.5, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.1, 1.0, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.1, 0.0, 1.0, iconMaxU, iconMaxV);
    }

    private void renderEast(Tessellator tessellator, double iconMinU, double iconMaxU, double iconMinV,
        double iconMaxV) {
        tessellator.addVertexWithUV(0.0, 0.0, 0.5, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.9, 1.0, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.9, 0.0, 0.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.9, 0.0, 0.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.9, 1.0, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.5, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.5, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.5, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.5, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.5, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.5, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.5, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.5, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.9, 1.0, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.9, 0.0, 1.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.9, 0.0, 1.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.9, 1.0, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.5, iconMinU, iconMaxV);
    }
}
