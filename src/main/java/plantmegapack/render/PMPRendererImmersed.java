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
import plantmegapack.common.PMPPlantCategory;
import plantmegapack.common.PMPPlantGrowthType;

public class PMPRendererImmersed implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return PMPRenderers.renderImmersedID;
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        if (!(block instanceof PMPBlockPlant)) {
            return false;
        } else {
            this.renderPlant(world, renderer, (PMPBlockPlant) block, x, y, z);
            return true;
        }
    }

    private void renderPlant(IBlockAccess world, RenderBlocks renderer, PMPBlockPlant block, int x, int y, int z) {
        Tessellator tessellator = Tessellator.instance;
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
        int indexBottom;
        int indexTop;
        IIcon iconBottom;
        IIcon iconTop;
        if (block.plantData.attributes.category == PMPPlantCategory.CROP_AQUATIC) {
            indexBottom = world.getBlockMetadata(x, y, z) * 2;
            indexTop = indexBottom + 1;
            iconBottom = block.getIcon(0, indexBottom);
            iconTop = block.getIcon(0, indexTop);
            if (iconBottom == null || iconTop == null) {
                return;
            }

            renderer.drawCrossedSquares(iconTop, d1, d2, d0, 1.0F);
            renderer.drawCrossedSquares(iconBottom, d1, d2 - 1.0, d0, 1.0F);
        } else if (block.plantData.attributes.growthType == PMPPlantGrowthType.NORMAL) {
            indexTop = world.getBlockMetadata(x, y, z) + 1;
            iconBottom = block.getIcon(0, 0);
            iconTop = block.getIcon(0, indexTop);
            if (iconBottom == null || iconTop == null) {
                return;
            }

            renderer.drawCrossedSquares(iconTop, d1, d2, d0, 1.0F);
            renderer.drawCrossedSquares(iconBottom, d1, d2 - 1.0, d0, 1.0F);
        } else if (block.plantData.attributes.growthType == PMPPlantGrowthType.DOUBLE) {
            renderer.drawCrossedSquares(block.getIcon(0, 0), d1, d2 - 1.0, d0, 1.0F);
            indexTop = 0;
            switch (world.getBlockMetadata(x, y, z)) {
                case 0:
                    indexBottom = 1;
                    break;
                case 1:
                    indexBottom = 2;
                    break;
                case 2:
                    indexBottom = 3;
                    indexTop = 4;
                    break;
                case 3:
                    indexBottom = 5;
                    indexTop = 6;
                    break;
                case 4:
                    indexBottom = 7;
                    indexTop = 8;
                    break;
                default:
                    indexBottom = 1;
            }

            renderer.drawCrossedSquares(block.getIcon(0, indexBottom), d1, d2, d0, 1.0F);
            if (indexTop > 0) {
                renderer.drawCrossedSquares(block.getIcon(0, indexTop), d1, d2 + 1.0, d0, 1.0F);
            }
        }

    }
}
