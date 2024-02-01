package plantmegapack.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import plantmegapack.bin.PMPRenderers;
import plantmegapack.block.PMPBlockPlant;

public class PMPRendererGroundcover implements ISimpleBlockRenderingHandler {

    private boolean isNumberEven(int number) {
        return (number & 1) == 0;
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

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

            tessellator.setNormal(0.0F, 0.0F, 0.0F);
            tessellator.setColorOpaque_F(cf * colorRED, cf * colorGRN, cf * colorBLU);
            tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
            if (this.isNumberEven(x)) {
                if (this.isNumberEven(z)) {
                    renderer.uvRotateTop = 1;
                }
            } else if (this.isNumberEven(z)) {
                renderer.uvRotateTop = 2;
            } else {
                renderer.uvRotateTop = 3;
            }

            renderer.renderFaceYPos(
                block,
                (double) x,
                (double) y,
                (double) z,
                block.getIcon(0, world.getBlockMetadata(x, y, z)));
            renderer.uvRotateTop = 0;
            return true;
        }
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return PMPRenderers.renderGroundcoverID;
    }
}
