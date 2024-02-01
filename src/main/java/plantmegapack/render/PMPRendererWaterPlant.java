package plantmegapack.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import plantmegapack.bin.PMPRenderers;
import plantmegapack.block.PMPBlockPlant;

public class PMPRendererWaterPlant implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return PMPRenderers.renderWaterPlantID;
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        if (PMPBlockPlant.renderPass == 0) {
            renderer.renderCrossedSquares(block, x, y, z);
        } else {
            if (PMPBlockPlant.renderPass != 1) {
                return false;
            }

            renderer.renderStandardBlock(Blocks.water, x, y, z);
        }

        return true;
    }
}
