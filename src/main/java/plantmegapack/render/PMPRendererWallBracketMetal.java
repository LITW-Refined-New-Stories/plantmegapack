package plantmegapack.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import plantmegapack.bin.PMPRenderers;

public class PMPRendererWallBracketMetal implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return PMPRenderers.renderWallBracketMetalID;
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
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

        tessellator.setNormal(0.0F, 0.0F, 0.0F);
        tessellator.setColorOpaque_F(cf * colorRED, cf * colorGRN, cf * colorBLU);
        tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        tessellator.addTranslation((float) x, (float) y, (float) z);
        switch (world.getBlockMetadata(x, y, z)) {
            case 2:
                this.renderNorth(tessellator, block);
                break;
            case 3:
                this.renderSouth(tessellator, block);
                break;
            case 4:
                this.renderWest(tessellator, block);
                break;
            case 5:
                this.renderEast(tessellator, block);
        }

        tessellator.addTranslation((float) (-x), (float) (-y), (float) (-z));
        return true;
    }

    private void renderNorth(Tessellator tessellator, Block block) {
        IIcon icon = block.getIcon(0, 1);
        double iconMinU = (double) icon.getMinU();
        double iconMinV = (double) icon.getMinV();
        double iconMaxU = (double) icon.getMaxU();
        double iconMaxV = (double) icon.getMaxV();
        tessellator.addVertexWithUV(0.0, 0.0, 0.9985, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.9985, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.9985, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.9985, iconMinU, iconMaxV);
        icon = block.getIcon(0, 0);
        iconMinU = (double) icon.getMinU();
        iconMinV = (double) icon.getMinV();
        iconMaxU = (double) icon.getMaxU();
        iconMaxV = (double) icon.getMaxV();
        tessellator.addVertexWithUV(0.5, 0.0, 1.0, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 1.0, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.5, 1.0, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.5, 0.0, 0.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 0.0, 0.0, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(0.5, 1.0, 0.0, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(0.5, 1.0, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.5, 0.0, 1.0, iconMinU, iconMaxV);
    }

    private void renderSouth(Tessellator tessellator, Block block) {
        IIcon icon = block.getIcon(0, 1);
        double iconMinU = (double) icon.getMinU();
        double iconMinV = (double) icon.getMinV();
        tessellator.addVertexWithUV(
            1.0,
            0.0,
            0.0015,
            (double) icon.getInterpolatedU(16.0),
            (double) icon.getInterpolatedV(16.0));
        tessellator.addVertexWithUV(1.0, 1.0, 0.0015, (double) icon.getInterpolatedU(16.0), iconMinV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.0015, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.0015, iconMinU, (double) icon.getInterpolatedV(16.0));
        icon = block.getIcon(0, 0);
        iconMinU = (double) icon.getMinU();
        iconMinV = (double) icon.getMinV();
        tessellator.addVertexWithUV(0.5, 0.0, 0.0, iconMinU, (double) icon.getInterpolatedV(16.0));
        tessellator.addVertexWithUV(0.5, 1.0, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.5, 1.0, 1.0, (double) icon.getInterpolatedU(16.0), iconMinV);
        tessellator
            .addVertexWithUV(0.5, 0.0, 1.0, (double) icon.getInterpolatedU(16.0), (double) icon.getInterpolatedV(16.0));
        tessellator
            .addVertexWithUV(0.5, 0.0, 1.0, (double) icon.getInterpolatedU(16.0), (double) icon.getInterpolatedV(16.0));
        tessellator.addVertexWithUV(0.5, 1.0, 1.0, (double) icon.getInterpolatedU(16.0), iconMinV);
        tessellator.addVertexWithUV(0.5, 1.0, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.5, 0.0, 0.0, iconMinU, (double) icon.getInterpolatedV(16.0));
    }

    private void renderWest(Tessellator tessellator, Block block) {
        IIcon icon = block.getIcon(0, 1);
        double iconMinU = (double) icon.getMinU();
        double iconMinV = (double) icon.getMinV();
        tessellator.addVertexWithUV(
            0.9985,
            0.0,
            1.0,
            (double) icon.getInterpolatedU(16.0),
            (double) icon.getInterpolatedV(16.0));
        tessellator.addVertexWithUV(0.9985, 1.0, 1.0, (double) icon.getInterpolatedU(16.0), iconMinV);
        tessellator.addVertexWithUV(0.9985, 1.0, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.9985, 0.0, 0.0, iconMinU, (double) icon.getInterpolatedV(16.0));
        icon = block.getIcon(0, 0);
        iconMinU = (double) icon.getMinU();
        iconMinV = (double) icon.getMinV();
        tessellator.addVertexWithUV(1.0, 0.0, 0.5, iconMinU, (double) icon.getInterpolatedV(16.0));
        tessellator.addVertexWithUV(1.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.5, (double) icon.getInterpolatedU(16.0), iconMinV);
        tessellator
            .addVertexWithUV(0.0, 0.0, 0.5, (double) icon.getInterpolatedU(16.0), (double) icon.getInterpolatedV(16.0));
        tessellator
            .addVertexWithUV(0.0, 0.0, 0.5, (double) icon.getInterpolatedU(16.0), (double) icon.getInterpolatedV(16.0));
        tessellator.addVertexWithUV(0.0, 1.0, 0.5, (double) icon.getInterpolatedU(16.0), iconMinV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.5, iconMinU, (double) icon.getInterpolatedV(16.0));
    }

    private void renderEast(Tessellator tessellator, Block block) {
        IIcon icon = block.getIcon(0, 1);
        double iconMinU = (double) icon.getMinU();
        double iconMinV = (double) icon.getMinV();
        tessellator.addVertexWithUV(
            0.0015,
            0.0,
            0.0,
            (double) icon.getInterpolatedU(16.0),
            (double) icon.getInterpolatedV(16.0));
        tessellator.addVertexWithUV(0.0015, 1.0, 0.0, (double) icon.getInterpolatedU(16.0), iconMinV);
        tessellator.addVertexWithUV(0.0015, 1.0, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0015, 0.0, 1.0, iconMinU, (double) icon.getInterpolatedV(16.0));
        icon = block.getIcon(0, 0);
        iconMinU = (double) icon.getMinU();
        iconMinV = (double) icon.getMinV();
        tessellator.addVertexWithUV(0.0, 0.0, 0.5, iconMinU, (double) icon.getInterpolatedV(16.0));
        tessellator.addVertexWithUV(0.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 1.0, 0.5, (double) icon.getInterpolatedU(16.0), iconMinV);
        tessellator
            .addVertexWithUV(1.0, 0.0, 0.5, (double) icon.getInterpolatedU(16.0), (double) icon.getInterpolatedV(16.0));
        tessellator
            .addVertexWithUV(1.0, 0.0, 0.5, (double) icon.getInterpolatedU(16.0), (double) icon.getInterpolatedV(16.0));
        tessellator.addVertexWithUV(1.0, 1.0, 0.5, (double) icon.getInterpolatedU(16.0), iconMinV);
        tessellator.addVertexWithUV(0.0, 1.0, 0.5, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.5, iconMinU, (double) icon.getInterpolatedV(16.0));
    }
}
