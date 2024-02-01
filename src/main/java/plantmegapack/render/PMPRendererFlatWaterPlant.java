package plantmegapack.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import plantmegapack.bin.PMPRenderers;
import plantmegapack.block.PMPBlockPlant;

public class PMPRendererFlatWaterPlant implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return PMPRenderers.renderFlatWaterPlantID;
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
            if (PMPBlockPlant.renderPass == 0) {
                this.renderFlatPlant(
                    tessellator,
                    iconMinU,
                    iconMaxU,
                    iconMinV,
                    iconMaxV,
                    world.getBlockMetadata(x, y, z));
            } else {
                renderer.renderStandardBlock(Blocks.water, x, y, z);
            }

            tessellator.addTranslation((float) (-x), (float) (-y), (float) (-z));
            return true;
        }
    }

    private void renderFlatPlant(Tessellator tessellator, double iconMinU, double iconMaxU, double iconMinV,
        double iconMaxV, int metaData) {
        double radians = Math.toRadians((double) metaData * 11.25);
        double startX = 0.5;
        double startZ = 0.0;
        double rotStartX = 0.5 + Math.cos(radians) * (startX - 0.5) - Math.sin(radians) * (startZ - 0.5);
        double rotStartZ = 0.5 + Math.sin(radians) * (startX - 0.5) + Math.cos(radians) * (startZ - 0.5);
        startX = 0.5;
        startZ = 1.0;
        double rotEndX = 0.5 + Math.cos(radians) * (startX - 0.5) - Math.sin(radians) * (startZ - 0.5);
        double rotEndZ = 0.5 + Math.sin(radians) * (startX - 0.5) + Math.cos(radians) * (startZ - 0.5);
        tessellator.addVertexWithUV(rotStartX, 0.0, rotStartZ, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(rotStartX, 1.0, rotStartZ, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(rotEndX, 1.0, rotEndZ, iconMinU, iconMinV);
        tessellator.addVertexWithUV(rotEndX, 0.0, rotEndZ, iconMinU, iconMaxV);
        tessellator.addVertexWithUV(rotEndX, 0.0, rotEndZ, iconMaxU, iconMaxV);
        tessellator.addVertexWithUV(rotEndX, 1.0, rotEndZ, iconMaxU, iconMinV);
        tessellator.addVertexWithUV(rotStartX, 1.0, rotStartZ, iconMinU, iconMinV);
        tessellator.addVertexWithUV(rotStartX, 0.0, rotStartZ, iconMinU, iconMaxV);
    }
}
