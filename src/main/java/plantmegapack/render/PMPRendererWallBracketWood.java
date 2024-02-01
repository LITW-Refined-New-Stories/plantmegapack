package plantmegapack.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import plantmegapack.bin.PMPRenderers;

public class PMPRendererWallBracketWood implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return PMPRenderers.renderWallBracketID;
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        IIcon icon = block.getIcon(0, 1);
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
                this.renderNorth(tessellator, icon, iconMinU, iconMaxU, iconMinV, iconMaxV);
                break;
            case 3:
                this.renderSouth(tessellator, icon, iconMinU, iconMaxU, iconMinV, iconMaxV);
                break;
            case 4:
                this.renderWest(tessellator, icon, iconMinU, iconMaxU, iconMinV, iconMaxV);
                break;
            case 5:
                this.renderEast(tessellator, icon, iconMinU, iconMaxU, iconMinV, iconMaxV);
        }

        tessellator.addTranslation((float) (-x), (float) (-y), (float) (-z));
        return true;
    }

    private void renderNorth(Tessellator tessellator, IIcon icon, double iconMinU, double iconMaxU, double iconMinV,
        double iconMaxV) {
        tessellator
            .addVertexWithUV(0.75, 0.0, 1.0, (double) icon.getInterpolatedU(8.0), (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.75, 0.5, 1.0, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(0.25, 0.5, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.25, 0.0, 1.0, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(
            0.25,
            0.0,
            0.9375,
            (double) icon.getInterpolatedU(8.0),
            (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.25, 0.5, 0.9375, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(0.75, 0.5, 0.9375, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.75, 0.0, 0.9375, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(
            0.25,
            0.5,
            0.9375,
            (double) icon.getInterpolatedU(8.0),
            (double) icon.getInterpolatedV(1.0));
        tessellator.addVertexWithUV(0.25, 0.5, 1.0, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(0.75, 0.5, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.75, 0.5, 0.9375, iconMinU, (double) icon.getInterpolatedV(1.0));
        tessellator
            .addVertexWithUV(0.25, 0.0, 1.0, (double) icon.getInterpolatedU(1.0), (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.25, 0.5, 1.0, (double) icon.getInterpolatedU(1.0), iconMinV);
        tessellator.addVertexWithUV(0.25, 0.5, 0.9375, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.25, 0.0, 0.9375, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(
            0.75,
            0.0,
            0.9375,
            (double) icon.getInterpolatedU(1.0),
            (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.75, 0.5, 0.9375, (double) icon.getInterpolatedU(1.0), iconMinV);
        tessellator.addVertexWithUV(0.75, 0.5, 1.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.75, 0.0, 1.0, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator
            .addVertexWithUV(0.25, 0.0, 1.0, (double) icon.getInterpolatedU(8.0), (double) icon.getInterpolatedV(1.0));
        tessellator.addVertexWithUV(0.25, 0.0, 0.9375, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(0.75, 0.0, 0.9375, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.75, 0.0, 1.0, iconMinU, (double) icon.getInterpolatedV(1.0));
        tessellator.addVertexWithUV(0.4375, 0.3125, 0.9375, (double) icon.getInterpolatedU(12.0), iconMinV);
        tessellator.addVertexWithUV(0.5625, 0.3125, 0.9375, (double) icon.getInterpolatedU(10.0), iconMinV);
        tessellator.addVertexWithUV(
            0.5625,
            0.3125,
            0.4375,
            (double) icon.getInterpolatedU(10.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.3125,
            0.4375,
            (double) icon.getInterpolatedU(12.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(0.4385, 0.1875, 0.9375, (double) icon.getInterpolatedU(12.0), iconMinV);
        tessellator.addVertexWithUV(0.4385, 0.3125, 0.9375, (double) icon.getInterpolatedU(10.0), iconMinV);
        tessellator.addVertexWithUV(
            0.4385,
            0.3125,
            0.4375,
            (double) icon.getInterpolatedU(10.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(
            0.4385,
            0.1875,
            0.4375,
            (double) icon.getInterpolatedU(12.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(0.5625, 0.3125, 0.9375, (double) icon.getInterpolatedU(13.0), iconMinV);
        tessellator.addVertexWithUV(0.5625, 0.1875, 0.9375, (double) icon.getInterpolatedU(11.0), iconMinV);
        tessellator.addVertexWithUV(
            0.5625,
            0.1875,
            0.4375,
            (double) icon.getInterpolatedU(11.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.3125,
            0.4375,
            (double) icon.getInterpolatedU(13.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(0.4375, 0.1875, 0.9375, (double) icon.getInterpolatedU(14.0), iconMinV);
        tessellator.addVertexWithUV(
            0.4375,
            0.1875,
            0.5625,
            (double) icon.getInterpolatedU(14.0),
            (double) icon.getInterpolatedV(5.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.1875,
            0.5625,
            (double) icon.getInterpolatedU(12.0),
            (double) icon.getInterpolatedV(5.0));
        tessellator.addVertexWithUV(0.5625, 0.1875, 0.9375, (double) icon.getInterpolatedU(12.0), iconMinV);
        tessellator.addVertexWithUV(
            0.4375,
            0.0,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.3125,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.5625, 0.3125, 0.4375, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.5625, 0.0, 0.4375, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.0,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.1875,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.4375, 0.1875, 0.5625, iconMinU, (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.4375, 0.0, 0.5625, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.0,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.1875,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.4375, 0.1875, 0.4375, iconMinU, (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.4375, 0.0, 0.4375, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.0,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.1875,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.5625, 0.1875, 0.5625, iconMinU, (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.5625, 0.0, 0.5625, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.0,
            0.5625,
            (double) icon.getInterpolatedU(1.0),
            (double) icon.getInterpolatedV(15.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.0,
            0.4375,
            (double) icon.getInterpolatedU(1.0),
            (double) icon.getInterpolatedV(14.0));
        tessellator.addVertexWithUV(0.5625, 0.0, 0.4375, iconMinU, (double) icon.getInterpolatedV(14.0));
        tessellator.addVertexWithUV(0.5625, 0.0, 0.5625, iconMinU, (double) icon.getInterpolatedV(15.0));
    }

    private void renderSouth(Tessellator tessellator, IIcon icon, double iconMinU, double iconMaxU, double iconMinV,
        double iconMaxV) {
        tessellator
            .addVertexWithUV(0.25, 0.0, 0.0, (double) icon.getInterpolatedU(8.0), (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.25, 0.5, 0.0, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(0.75, 0.5, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.75, 0.0, 0.0, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(
            0.75,
            0.0,
            0.0625,
            (double) icon.getInterpolatedU(8.0),
            (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.75, 0.5, 0.0625, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(0.25, 0.5, 0.0625, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.25, 0.0, 0.0625, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(
            0.75,
            0.5,
            0.0625,
            (double) icon.getInterpolatedU(8.0),
            (double) icon.getInterpolatedV(1.0));
        tessellator.addVertexWithUV(0.75, 0.5, 0.0, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(0.25, 0.5, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.25, 0.5, 0.0625, iconMinU, (double) icon.getInterpolatedV(1.0));
        tessellator
            .addVertexWithUV(0.75, 0.0, 0.0, (double) icon.getInterpolatedU(1.0), (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.75, 0.5, 0.0, (double) icon.getInterpolatedU(1.0), iconMinV);
        tessellator.addVertexWithUV(0.75, 0.5, 0.0625, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.75, 0.0, 0.0625, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(
            0.25,
            0.0,
            0.0625,
            (double) icon.getInterpolatedU(1.0),
            (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.25, 0.5, 0.0625, (double) icon.getInterpolatedU(1.0), iconMinV);
        tessellator.addVertexWithUV(0.25, 0.5, 0.0, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.25, 0.0, 0.0, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator
            .addVertexWithUV(0.75, 0.0, 0.0, (double) icon.getInterpolatedU(8.0), (double) icon.getInterpolatedV(1.0));
        tessellator.addVertexWithUV(0.75, 0.0, 0.0625, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(0.25, 0.0, 0.0625, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.25, 0.0, 0.0, iconMinU, (double) icon.getInterpolatedV(1.0));
        tessellator.addVertexWithUV(0.5625, 0.3125, 0.0625, (double) icon.getInterpolatedU(12.0), iconMinV);
        tessellator.addVertexWithUV(0.4375, 0.3125, 0.0625, (double) icon.getInterpolatedU(10.0), iconMinV);
        tessellator.addVertexWithUV(
            0.4375,
            0.3125,
            0.5625,
            (double) icon.getInterpolatedU(10.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.3125,
            0.5625,
            (double) icon.getInterpolatedU(12.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(0.5615, 0.1875, 0.0625, (double) icon.getInterpolatedU(12.0), iconMinV);
        tessellator.addVertexWithUV(0.5615, 0.3125, 0.0625, (double) icon.getInterpolatedU(10.0), iconMinV);
        tessellator.addVertexWithUV(
            0.5615,
            0.3125,
            0.5625,
            (double) icon.getInterpolatedU(10.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(
            0.5615,
            0.1875,
            0.5625,
            (double) icon.getInterpolatedU(12.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(0.4375, 0.3125, 0.0625, (double) icon.getInterpolatedU(13.0), iconMinV);
        tessellator.addVertexWithUV(0.4375, 0.1875, 0.0625, (double) icon.getInterpolatedU(11.0), iconMinV);
        tessellator.addVertexWithUV(
            0.4375,
            0.1875,
            0.5625,
            (double) icon.getInterpolatedU(11.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.3125,
            0.5625,
            (double) icon.getInterpolatedU(13.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(0.5625, 0.1875, 0.0625, (double) icon.getInterpolatedU(14.0), iconMinV);
        tessellator.addVertexWithUV(
            0.5625,
            0.1875,
            0.4375,
            (double) icon.getInterpolatedU(14.0),
            (double) icon.getInterpolatedV(5.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.1875,
            0.4375,
            (double) icon.getInterpolatedU(12.0),
            (double) icon.getInterpolatedV(5.0));
        tessellator.addVertexWithUV(0.4375, 0.1875, 0.0625, (double) icon.getInterpolatedU(12.0), iconMinV);
        tessellator.addVertexWithUV(
            0.5625,
            0.0,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.3125,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.4375, 0.3125, 0.5625, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.4375, 0.0, 0.5625, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.0,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.1875,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.5625, 0.1875, 0.4375, iconMinU, (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.5625, 0.0, 0.4375, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.0,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.1875,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.5625, 0.1875, 0.5625, iconMinU, (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.5625, 0.0, 0.5625, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.0,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.1875,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.4375, 0.1875, 0.4375, iconMinU, (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.4375, 0.0, 0.4375, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.0,
            0.4375,
            (double) icon.getInterpolatedU(1.0),
            (double) icon.getInterpolatedV(15.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.0,
            0.5625,
            (double) icon.getInterpolatedU(1.0),
            (double) icon.getInterpolatedV(14.0));
        tessellator.addVertexWithUV(0.4375, 0.0, 0.5625, iconMinU, (double) icon.getInterpolatedV(15.0));
        tessellator.addVertexWithUV(0.4375, 0.0, 0.4375, iconMinU, (double) icon.getInterpolatedV(14.0));
    }

    private void renderWest(Tessellator tessellator, IIcon icon, double iconMinU, double iconMaxU, double iconMinV,
        double iconMaxV) {
        tessellator
            .addVertexWithUV(1.0, 0.0, 0.25, (double) icon.getInterpolatedU(8.0), (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(1.0, 0.5, 0.25, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(1.0, 0.5, 0.75, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.75, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(
            0.9375,
            0.0,
            0.75,
            (double) icon.getInterpolatedU(8.0),
            (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.9375, 0.5, 0.75, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(0.9375, 0.5, 0.25, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.9375, 0.0, 0.25, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(
            0.9375,
            0.5,
            0.75,
            (double) icon.getInterpolatedU(8.0),
            (double) icon.getInterpolatedV(1.0));
        tessellator.addVertexWithUV(1.0, 0.5, 0.75, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(1.0, 0.5, 0.25, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.9375, 0.5, 0.25, iconMinU, (double) icon.getInterpolatedV(1.0));
        tessellator
            .addVertexWithUV(1.0, 0.0, 0.75, (double) icon.getInterpolatedU(1.0), (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(1.0, 0.5, 0.75, (double) icon.getInterpolatedU(1.0), iconMinV);
        tessellator.addVertexWithUV(0.9375, 0.5, 0.75, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.9375, 0.0, 0.75, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(
            0.9375,
            0.0,
            0.25,
            (double) icon.getInterpolatedU(1.0),
            (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.9375, 0.5, 0.25, (double) icon.getInterpolatedU(1.0), iconMinV);
        tessellator.addVertexWithUV(1.0, 0.5, 0.25, iconMinU, iconMinV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.25, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(
            0.9375,
            0.0,
            0.25,
            (double) icon.getInterpolatedU(8.0),
            (double) icon.getInterpolatedV(1.0));
        tessellator.addVertexWithUV(1.0, 0.0, 0.25, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(1.0, 0.0, 0.75, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.9375, 0.0, 0.75, iconMinU, (double) icon.getInterpolatedV(1.0));
        tessellator.addVertexWithUV(0.9375, 0.3125, 0.5625, (double) icon.getInterpolatedU(12.0), iconMinV);
        tessellator.addVertexWithUV(0.9375, 0.3125, 0.4375, (double) icon.getInterpolatedU(10.0), iconMinV);
        tessellator.addVertexWithUV(
            0.4375,
            0.3125,
            0.4375,
            (double) icon.getInterpolatedU(10.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.3125,
            0.5625,
            (double) icon.getInterpolatedU(12.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(0.9375, 0.1875, 0.5615, (double) icon.getInterpolatedU(12.0), iconMinV);
        tessellator.addVertexWithUV(0.9375, 0.3125, 0.5615, (double) icon.getInterpolatedU(10.0), iconMinV);
        tessellator.addVertexWithUV(
            0.4375,
            0.3125,
            0.5615,
            (double) icon.getInterpolatedU(10.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.1875,
            0.5615,
            (double) icon.getInterpolatedU(12.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(0.9375, 0.3125, 0.4375, (double) icon.getInterpolatedU(13.0), iconMinV);
        tessellator.addVertexWithUV(0.9375, 0.1875, 0.4375, (double) icon.getInterpolatedU(11.0), iconMinV);
        tessellator.addVertexWithUV(
            0.4375,
            0.1875,
            0.4375,
            (double) icon.getInterpolatedU(11.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.3125,
            0.4375,
            (double) icon.getInterpolatedU(13.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(0.9375, 0.1875, 0.5625, (double) icon.getInterpolatedU(14.0), iconMinV);
        tessellator.addVertexWithUV(
            0.5625,
            0.1875,
            0.5625,
            (double) icon.getInterpolatedU(14.0),
            (double) icon.getInterpolatedV(5.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.1875,
            0.4375,
            (double) icon.getInterpolatedU(12.0),
            (double) icon.getInterpolatedV(5.0));
        tessellator.addVertexWithUV(0.9375, 0.1875, 0.4375, (double) icon.getInterpolatedU(12.0), iconMinV);
        tessellator.addVertexWithUV(
            0.4375,
            0.0,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.3125,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.4375, 0.3125, 0.4375, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.4375, 0.0, 0.4375, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.0,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.1875,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.5625, 0.1875, 0.5625, iconMinU, (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.5625, 0.0, 0.5625, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.0,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.1875,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.4375, 0.1875, 0.5625, iconMinU, (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.4375, 0.0, 0.5625, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.0,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.1875,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.5625, 0.1875, 0.4375, iconMinU, (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.5625, 0.0, 0.4375, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.0,
            0.5625,
            (double) icon.getInterpolatedU(1.0),
            (double) icon.getInterpolatedV(15.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.0,
            0.5625,
            (double) icon.getInterpolatedU(1.0),
            (double) icon.getInterpolatedV(14.0));
        tessellator.addVertexWithUV(0.4375, 0.0, 0.4375, iconMinU, (double) icon.getInterpolatedV(15.0));
        tessellator.addVertexWithUV(0.5625, 0.0, 0.4375, iconMinU, (double) icon.getInterpolatedV(14.0));
    }

    private void renderEast(Tessellator tessellator, IIcon icon, double iconMinU, double iconMaxU, double iconMinV,
        double iconMaxV) {
        tessellator
            .addVertexWithUV(0.0, 0.0, 0.75, (double) icon.getInterpolatedU(8.0), (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.0, 0.5, 0.75, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(0.0, 0.5, 0.25, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.25, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(
            0.0625,
            0.0,
            0.25,
            (double) icon.getInterpolatedU(8.0),
            (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.0625, 0.5, 0.25, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(0.0625, 0.5, 0.75, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0625, 0.0, 0.75, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(
            0.0625,
            0.5,
            0.25,
            (double) icon.getInterpolatedU(8.0),
            (double) icon.getInterpolatedV(1.0));
        tessellator.addVertexWithUV(0.0, 0.5, 0.25, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(0.0, 0.5, 0.75, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0625, 0.5, 0.75, iconMinU, (double) icon.getInterpolatedV(1.0));
        tessellator
            .addVertexWithUV(0.0, 0.0, 0.25, (double) icon.getInterpolatedU(1.0), (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.0, 0.5, 0.25, (double) icon.getInterpolatedU(1.0), iconMinV);
        tessellator.addVertexWithUV(0.0625, 0.5, 0.25, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0625, 0.0, 0.25, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(
            0.0625,
            0.0,
            0.75,
            (double) icon.getInterpolatedU(1.0),
            (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.0625, 0.5, 0.75, (double) icon.getInterpolatedU(1.0), iconMinV);
        tessellator.addVertexWithUV(0.0, 0.5, 0.75, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.75, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(
            0.0625,
            0.0,
            0.75,
            (double) icon.getInterpolatedU(8.0),
            (double) icon.getInterpolatedV(1.0));
        tessellator.addVertexWithUV(0.0, 0.0, 0.75, (double) icon.getInterpolatedU(8.0), iconMinV);
        tessellator.addVertexWithUV(0.0, 0.0, 0.25, iconMinU, iconMinV);
        tessellator.addVertexWithUV(0.0625, 0.0, 0.25, iconMinU, (double) icon.getInterpolatedV(1.0));
        tessellator.addVertexWithUV(0.0625, 0.3125, 0.4375, (double) icon.getInterpolatedU(12.0), iconMinV);
        tessellator.addVertexWithUV(0.0625, 0.3125, 0.5625, (double) icon.getInterpolatedU(10.0), iconMinV);
        tessellator.addVertexWithUV(
            0.5625,
            0.3125,
            0.5625,
            (double) icon.getInterpolatedU(10.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.3125,
            0.4375,
            (double) icon.getInterpolatedU(12.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(0.0625, 0.1875, 0.4385, (double) icon.getInterpolatedU(12.0), iconMinV);
        tessellator.addVertexWithUV(0.0625, 0.3125, 0.4385, (double) icon.getInterpolatedU(10.0), iconMinV);
        tessellator.addVertexWithUV(
            0.5625,
            0.3125,
            0.4385,
            (double) icon.getInterpolatedU(10.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.1875,
            0.4385,
            (double) icon.getInterpolatedU(12.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(0.0625, 0.3125, 0.5625, (double) icon.getInterpolatedU(13.0), iconMinV);
        tessellator.addVertexWithUV(0.0625, 0.1875, 0.5625, (double) icon.getInterpolatedU(11.0), iconMinV);
        tessellator.addVertexWithUV(
            0.5625,
            0.1875,
            0.5625,
            (double) icon.getInterpolatedU(11.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.3125,
            0.5625,
            (double) icon.getInterpolatedU(13.0),
            (double) icon.getInterpolatedV(7.0));
        tessellator.addVertexWithUV(0.0625, 0.1875, 0.4375, (double) icon.getInterpolatedU(14.0), iconMinV);
        tessellator.addVertexWithUV(
            0.4375,
            0.1875,
            0.4375,
            (double) icon.getInterpolatedU(14.0),
            (double) icon.getInterpolatedV(5.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.1875,
            0.5625,
            (double) icon.getInterpolatedU(12.0),
            (double) icon.getInterpolatedV(5.0));
        tessellator.addVertexWithUV(0.0625, 0.1875, 0.5625, (double) icon.getInterpolatedU(12.0), iconMinV);
        tessellator.addVertexWithUV(
            0.5625,
            0.0,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.3125,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.5625, 0.3125, 0.5625, iconMinU, (double) icon.getInterpolatedV(8.0));
        tessellator.addVertexWithUV(0.5625, 0.0, 0.5625, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.0,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.1875,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.4375, 0.1875, 0.4375, iconMinU, (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.4375, 0.0, 0.4375, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.0,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.1875,
            0.4375,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.5625, 0.1875, 0.4375, iconMinU, (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.5625, 0.0, 0.4375, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.0,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.1875,
            0.5625,
            (double) icon.getInterpolatedU(2.0),
            (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.4375, 0.1875, 0.5625, iconMinU, (double) icon.getInterpolatedV(10.0));
        tessellator.addVertexWithUV(0.4375, 0.0, 0.5625, iconMinU, (double) icon.getInterpolatedV(13.0));
        tessellator.addVertexWithUV(
            0.4375,
            0.0,
            0.4375,
            (double) icon.getInterpolatedU(1.0),
            (double) icon.getInterpolatedV(15.0));
        tessellator.addVertexWithUV(
            0.5625,
            0.0,
            0.4375,
            (double) icon.getInterpolatedU(1.0),
            (double) icon.getInterpolatedV(14.0));
        tessellator.addVertexWithUV(0.5625, 0.0, 0.5625, iconMinU, (double) icon.getInterpolatedV(15.0));
        tessellator.addVertexWithUV(0.4375, 0.0, 0.5625, iconMinU, (double) icon.getInterpolatedV(14.0));
    }
}
