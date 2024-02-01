package plantmegapack.bin;

import cpw.mods.fml.client.registry.RenderingRegistry;
import plantmegapack.common.PMPPlantRenderType;
import plantmegapack.render.PMPRendererBambooPole;
import plantmegapack.render.PMPRendererEpiphyteHorizontal;
import plantmegapack.render.PMPRendererEpiphyteVertical;
import plantmegapack.render.PMPRendererFlatPlant;
import plantmegapack.render.PMPRendererFlatWaterPlant;
import plantmegapack.render.PMPRendererFloatingFlower;
import plantmegapack.render.PMPRendererGroundcover;
import plantmegapack.render.PMPRendererImmersed;
import plantmegapack.render.PMPRendererPlant;
import plantmegapack.render.PMPRendererStalkPlant;
import plantmegapack.render.PMPRendererVine;
import plantmegapack.render.PMPRendererWallBracketMetal;
import plantmegapack.render.PMPRendererWallBracketWood;
import plantmegapack.render.PMPRendererWaterPlant;

public class PMPRenderers {

    public static int renderBambooPoleID = -1;
    public static int renderEpiphyteHorizontalID = -1;
    public static int renderEpiphyteVerticalID = -1;
    public static int renderFlatPlantID = -1;
    public static int renderFlatWaterPlantID = -1;
    public static int renderFloatingFlowerID = -1;
    public static int renderGroundcoverID = -1;
    public static int renderImmersedID = -1;
    public static int renderStalkID = -1;
    public static int renderPlantID = -1;
    public static int renderVineID = -1;
    public static int renderWallBracketID = -1;
    public static int renderWallBracketMetalID = -1;
    public static int renderWaterPlantID = -1;

    public PMPRenderers() {
        this.registerCustomRenderers();
    }

    private void registerCustomRenderers() {
        renderBambooPoleID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderBambooPoleID, new PMPRendererBambooPole());
        renderEpiphyteHorizontalID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderEpiphyteHorizontalID, new PMPRendererEpiphyteHorizontal());
        renderEpiphyteVerticalID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderEpiphyteVerticalID, new PMPRendererEpiphyteVertical());
        renderFlatPlantID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderFlatPlantID, new PMPRendererFlatPlant());
        renderFlatWaterPlantID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderFlatWaterPlantID, new PMPRendererFlatWaterPlant());
        renderFloatingFlowerID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderFloatingFlowerID, new PMPRendererFloatingFlower());
        renderGroundcoverID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderGroundcoverID, new PMPRendererGroundcover());
        renderImmersedID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderImmersedID, new PMPRendererImmersed());
        renderStalkID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderStalkID, new PMPRendererStalkPlant());
        renderPlantID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderPlantID, new PMPRendererPlant());
        renderVineID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderVineID, new PMPRendererVine());
        renderWallBracketID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderWallBracketID, new PMPRendererWallBracketWood());
        renderWallBracketMetalID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderWallBracketMetalID, new PMPRendererWallBracketMetal());
        renderWaterPlantID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderWaterPlantID, new PMPRendererWaterPlant());
    }

    public static int getRendererFromRenderType(PMPPlantRenderType type) {
        switch (type) {
            case CROP:
                return renderPlantID;
            case EPIPHYTE_HORIZONTAL:
                return renderEpiphyteHorizontalID;
            case EPIPHYTE_VERTICAL:
                return renderEpiphyteVerticalID;
            case FLAT:
                return renderFlatPlantID;
            case FLOATING_FLAT:
                return 23;
            case FLOATING_FLOWER:
            case FLOATING_PLANT:
                return renderFloatingFlowerID;
            case GROUNDCOVER:
                return renderGroundcoverID;
            case IMMERSED:
                return renderImmersedID;
            case NORMAL:
                return renderPlantID;
            case STALK:
                return renderStalkID;
            case STAR:
                return 1;
            case VINE_FLOWER:
            case VINE_NORMAL:
            case VINE_RANDOM:
                return renderVineID;
            case VINE_VANILLA:
                return 20;
            case WATER:
                return 1;
            case WATER_FLAT:
                return 1;
            default:
                return 1;
        }
    }
}
