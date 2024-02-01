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

public class PMPRendererEpiphyteHorizontal implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return PMPRenderers.renderEpiphyteHorizontalID;
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        if (!(block instanceof PMPBlockPlant)) {
            return false;
        } else {
            Tessellator tessellator = Tessellator.instance;
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

            tessellator.setColorOpaque_F(cf * colorRED, cf * colorGRN, cf * colorBLU);
            tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
            tessellator.addTranslation((float) x, (float) y, (float) z);
            switch (world.getBlockMetadata(x, y, z)) {
                case 2:
                    this.renderNorth(tessellator, block, renderer);
                    break;
                case 3:
                    this.renderSouth(tessellator, block, renderer);
                    break;
                case 4:
                    this.renderWest(tessellator, block, renderer);
                    break;
                case 5:
                    this.renderEast(tessellator, block, renderer);
            }

            tessellator.addTranslation((float) (-x), (float) (-y), (float) (-z));
            return true;
        }
    }

    private void renderNorth(Tessellator tessellator, Block block, RenderBlocks renderer) {
        IIcon icon = block.getIcon(0, 0);
        double iconMinU = (double) icon.getMinU();
        double iconMinV = (double) icon.getMinV();
        double iconMaxU = (double) icon.getMaxU();
        double iconMaxV = (double) icon.getMaxV();
        tessellator.addTranslation(-0.0625F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(0.0, 0.35, 0.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.35, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.35, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.35, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.35, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.35, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.35, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.35, 0.0, iconMaxU, iconMaxV);
        tessellator.addTranslation(0.0625F, 0.0F, 0.0F);
        icon = block.getIcon(0, 1);
        iconMinU = (double) icon.getMinU();
        iconMinV = (double) icon.getMinV();
        iconMaxU = (double) icon.getMaxU();
        iconMaxV = (double) icon.getMaxV();
        tessellator.addTranslation(0.125F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(0.0, 0.5, 0.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.5, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.5, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.5, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.5, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.5, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.5, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.5, 0.0, iconMaxU, iconMaxV);
        tessellator.addTranslation(-0.125F, 0.0F, 0.0F);
        icon = block.getIcon(0, 2);
        iconMinU = (double) icon.getMinU();
        iconMinV = (double) icon.getMinV();
        iconMaxU = (double) icon.getMaxU();
        iconMaxV = (double) icon.getMaxV();
        tessellator.addTranslation(-0.125F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(0.0, 0.65, 0.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.65, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.65, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.65, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.65, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.65, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.65, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.65, 0.0, iconMaxU, iconMaxV);
        tessellator.addTranslation(0.125F, 0.0F, 0.0F);
    }

    private void renderSouth(Tessellator tessellator, Block block, RenderBlocks renderer) {
        IIcon icon = block.getIcon(0, 0);
        double iconMinU = (double) icon.getMinU();
        double iconMinV = (double) icon.getMinV();
        double iconMaxU = (double) icon.getMaxU();
        double iconMaxV = (double) icon.getMaxV();
        tessellator.addTranslation(-0.0625F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(1.0, 0.35, 1.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.35, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.35, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.35, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.35, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.35, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.35, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.35, 1.0, iconMaxU, iconMaxV);
        tessellator.addTranslation(0.0625F, 0.0F, 0.0F);
        icon = block.getIcon(0, 1);
        iconMinU = (double) icon.getMinU();
        iconMinV = (double) icon.getMinV();
        iconMaxU = (double) icon.getMaxU();
        iconMaxV = (double) icon.getMaxV();
        tessellator.addTranslation(0.125F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(1.0, 0.5, 1.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.5, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.5, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.5, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.5, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.5, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.5, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.5, 1.0, iconMaxU, iconMaxV);
        tessellator.addTranslation(-0.125F, 0.0F, 0.0F);
        icon = block.getIcon(0, 2);
        iconMinU = (double) icon.getMinU();
        iconMinV = (double) icon.getMinV();
        iconMaxU = (double) icon.getMaxU();
        iconMaxV = (double) icon.getMaxV();
        tessellator.addTranslation(-0.125F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(1.0, 0.65, 1.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.65, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.65, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.65, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.65, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.65, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.65, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.65, 1.0, iconMaxU, iconMaxV);
        tessellator.addTranslation(0.125F, 0.0F, 0.0F);
    }

    private void renderWest(Tessellator tessellator, Block block, RenderBlocks renderer) {
        IIcon icon = block.getIcon(0, 0);
        double iconMinU = (double) icon.getMinU();
        double iconMinV = (double) icon.getMinV();
        double iconMaxU = (double) icon.getMaxU();
        double iconMaxV = (double) icon.getMaxV();
        tessellator.addTranslation(0.0F, 0.0F, -0.0625F);
        tessellator.addVertexWithUV(0.0, 0.35, 1.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.35, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.35, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.35, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.35, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.35, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.35, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.35, 1.0, iconMaxU, iconMaxV);
        tessellator.addTranslation(0.0F, 0.0F, 0.0625F);
        icon = block.getIcon(0, 1);
        iconMinU = (double) icon.getMinU();
        iconMinV = (double) icon.getMinV();
        iconMaxU = (double) icon.getMaxU();
        iconMaxV = (double) icon.getMaxV();
        tessellator.addTranslation(0.0F, 0.0F, 0.125F);
        tessellator.addVertexWithUV(0.0, 0.5, 1.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.5, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.5, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.5, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.5, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.5, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.5, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.5, 1.0, iconMaxU, iconMaxV);
        tessellator.addTranslation(0.0F, 0.0F, -0.125F);
        icon = block.getIcon(0, 2);
        iconMinU = (double) icon.getMinU();
        iconMinV = (double) icon.getMinV();
        iconMaxU = (double) icon.getMaxU();
        iconMaxV = (double) icon.getMaxV();
        tessellator.addTranslation(0.0F, 0.0F, -0.125F);
        tessellator.addVertexWithUV(0.0, 0.65, 1.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.65, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.65, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.65, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.65, 0.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.65, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.65, 1.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.65, 1.0, iconMaxU, iconMaxV);
        tessellator.addTranslation(0.0F, 0.0F, 0.125F);
    }

    private void renderEast(Tessellator tessellator, Block block, RenderBlocks renderer) {
        IIcon icon = block.getIcon(0, 0);
        double iconMinU = (double) icon.getMinU();
        double iconMinV = (double) icon.getMinV();
        double iconMaxU = (double) icon.getMaxU();
        double iconMaxV = (double) icon.getMaxV();
        tessellator.addTranslation(0.0F, 0.0F, -0.0625F);
        tessellator.addVertexWithUV(1.0, 0.35, 0.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.35, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.35, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.35, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.35, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.35, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.35, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.35, 0.0, iconMaxU, iconMaxV);
        tessellator.addTranslation(0.0F, 0.0F, 0.0625F);
        icon = block.getIcon(0, 1);
        iconMinU = (double) icon.getMinU();
        iconMinV = (double) icon.getMinV();
        iconMaxU = (double) icon.getMaxU();
        iconMaxV = (double) icon.getMaxV();
        tessellator.addTranslation(0.0F, 0.0F, 0.125F);
        tessellator.addVertexWithUV(1.0, 0.5, 0.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.5, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.5, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.5, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.5, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.5, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.5, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.5, 0.0, iconMaxU, iconMaxV);
        tessellator.addTranslation(0.0F, 0.0F, -0.125F);
        icon = block.getIcon(0, 2);
        iconMinU = (double) icon.getMinU();
        iconMinV = (double) icon.getMinV();
        iconMaxU = (double) icon.getMaxU();
        iconMaxV = (double) icon.getMaxV();
        tessellator.addTranslation(0.0F, 0.0F, -0.125F);
        tessellator.addVertexWithUV(1.0, 0.65, 0.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.65, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.65, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.65, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(1.0, 0.65, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 0.65, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.65, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.65, 0.0, iconMaxU, iconMaxV);
        tessellator.addTranslation(0.0F, 0.0F, 0.125F);
    }
}
