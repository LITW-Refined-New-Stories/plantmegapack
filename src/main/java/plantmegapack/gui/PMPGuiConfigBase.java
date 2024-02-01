package plantmegapack.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class PMPGuiConfigBase extends GuiScreen {

    public static final ResourceLocation imageLogo = new ResourceLocation("plantmegapack:textures/gui/logoPMP.png");
    public static final ResourceLocation ledGRNOff = new ResourceLocation("plantmegapack:textures/gui/ledGRN0.png");
    public static final ResourceLocation ledGRNOn = new ResourceLocation("plantmegapack:textures/gui/ledGRN1.png");
    public static final ResourceLocation ledREDOff = new ResourceLocation("plantmegapack:textures/gui/ledRED0.png");
    public static final ResourceLocation ledREDOn = new ResourceLocation("plantmegapack:textures/gui/ledRED1.png");
    public static final ResourceLocation ledMIX0 = new ResourceLocation("plantmegapack:textures/gui/ledMIX0.png");
    public static final ResourceLocation ledMIX1 = new ResourceLocation("plantmegapack:textures/gui/ledMIX1.png");
    public static final ResourceLocation ledMIX2 = new ResourceLocation("plantmegapack:textures/gui/ledMIX2.png");
    protected static final int BORDER = 6;
    protected static final int BUTTON_HEIGHT = 20;
    protected static final int BUTTON_WIDTH_LARGE = 180;
    protected static final int BUTTON_WIDTH_MED = 150;
    protected static final int BUTTON_WIDTH_SMALL = 88;
    protected static final int BUTTON_SPACING = 4;
    protected static final int LED_COLOR_GREEN = 0;
    protected static final int LED_COLOR_RED = 1;
    protected static final int LED_HEIGHT = 20;
    protected static final int LED_SPACING = 0;
    protected static final int LED_WIDTH = 6;
    protected static final int LOGO_HEIGHT = 16;
    protected static final int LOGO_WIDTH = 128;
    protected static final int START_Y = 36;
    protected PMPGuiButton buttonDefault;
    protected PMPGuiButton buttonDone;
    protected String name;
    protected GuiScreen parentGuiScreen;
    private int buttonLayout;

    public PMPGuiConfigBase(GuiScreen parentGuiScreen, String screenName, int buttonLayout) {
        this.parentGuiScreen = parentGuiScreen;
        this.name = LanguageRegistry.instance()
            .getStringLocalization(screenName);
        this.buttonLayout = buttonLayout >= -1 && buttonLayout <= 1 ? buttonLayout : 0;
    }

    public void initGui() {
        int yPos = this.height - 27;
        if (this.buttonLayout >= 0) {
            int xPos;
            if (this.buttonLayout == 0) {
                xPos = this.width / 2 - 90;
                this.buttonDone = new PMPGuiButton(
                    2,
                    xPos,
                    this.height - 27,
                    180,
                    20,
                    I18n.format("gui.done", new Object[0]));
                this.buttonList.add(this.buttonDone);
            } else if (this.buttonLayout == 1) {
                xPos = this.width / 2 - 121;
                this.buttonDefault = new PMPGuiButton(
                    1,
                    xPos,
                    yPos,
                    88,
                    20,
                    LanguageRegistry.instance()
                        .getStringLocalization("gui.button.default"));
                xPos += 92;
                this.buttonDone = new PMPGuiButton(2, xPos, yPos, 150, 20, I18n.format("gui.done", new Object[0]));
                this.buttonList.add(this.buttonDefault);
                this.buttonList.add(this.buttonDone);
            }
        }

    }

    public void drawScreen(int par1, int par2, float par3) {
        super.drawScreen(par1, par2, par3);
        this.drawLogoImage();
        this.drawTitleText();
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 1:
                    this.mc.displayGuiScreen(this);
                    return;
                case 2:
                    this.mc.displayGuiScreen(this.parentGuiScreen);
                    return;
            }
        }

    }

    public void drawLED(int x, int y, int color, boolean indicatorOn) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        switch (color) {
            case 0:
                this.mc.renderEngine.bindTexture(indicatorOn ? ledGRNOn : ledGRNOff);
                break;
            case 1:
                this.mc.renderEngine.bindTexture(indicatorOn ? ledREDOn : ledREDOff);
                break;
            default:
                return;
        }

        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        tess.addVertexWithUV((double) x, (double) (y + 20), (double) this.zLevel, 0.0, 1.0);
        tess.addVertexWithUV((double) (x + 6), (double) (y + 20), (double) this.zLevel, 1.0, 1.0);
        tess.addVertexWithUV((double) (x + 6), (double) y, (double) this.zLevel, 1.0, 0.0);
        tess.addVertexWithUV((double) x, (double) y, (double) this.zLevel, 0.0, 0.0);
        tess.draw();
    }

    protected void drawLogoImage() {
        if (imageLogo != null) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.renderEngine.bindTexture(imageLogo);
            int x = (this.width - 128) / 2;
            int y = 3;
            Tessellator tess = Tessellator.instance;
            tess.startDrawingQuads();
            tess.addVertexWithUV((double) x, (double) (y + 16), (double) this.zLevel, 0.0, 1.0);
            tess.addVertexWithUV((double) (x + 128), (double) (y + 16), (double) this.zLevel, 1.0, 1.0);
            tess.addVertexWithUV((double) (x + 128), (double) y, (double) this.zLevel, 1.0, 0.0);
            tess.addVertexWithUV((double) x, (double) y, (double) this.zLevel, 0.0, 0.0);
            tess.draw();
        }

    }

    protected void drawTitleText() {
        this.drawCenteredString(this.fontRendererObj, this.name, this.width / 2, 20, 9502624);
    }

    protected void drawIconTexture(IIcon icon, int xPos, int yPos, boolean doubleSize, boolean isItem) {
        if (icon != null) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Tessellator tessellator = Tessellator.instance;
            this.mc.getTextureMapBlocks();
            if (isItem) {
                this.mc.getTextureManager()
                    .bindTexture(TextureMap.locationItemsTexture);
            } else {
                this.mc.getTextureManager()
                    .bindTexture(TextureMap.locationBlocksTexture);
            }

            tessellator.setNormal(0.0F, 0.0F, 0.0F);
            double iconMinU = (double) icon.getMinU();
            double iconMinV = (double) icon.getMinV();
            double iconMaxU = (double) icon.getMaxU();
            double iconMaxV = (double) icon.getMaxV();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(
                (double) xPos,
                (double) (yPos + (doubleSize ? 32 : 20)),
                (double) this.zLevel,
                iconMinU,
                iconMaxV);
            tessellator.addVertexWithUV(
                (double) (xPos + (doubleSize ? 32 : 20)),
                (double) (yPos + (doubleSize ? 32 : 20)),
                (double) this.zLevel,
                iconMaxU,
                iconMaxV);
            tessellator.addVertexWithUV(
                (double) (xPos + (doubleSize ? 32 : 20)),
                (double) yPos,
                (double) this.zLevel,
                iconMaxU,
                iconMinV);
            tessellator.addVertexWithUV((double) xPos, (double) yPos, (double) this.zLevel, iconMinU, iconMinV);
            tessellator.draw();
        }

    }

    protected FontRenderer getFontRenderer() {
        return this.fontRendererObj;
    }

    public abstract void listItemSelected(PMPGuiStringList var1, int var2);

    public abstract void listItemActivate(PMPGuiStringList var1);

    public abstract void updateIntSetting(String var1, int var2);
}
