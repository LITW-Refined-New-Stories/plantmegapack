package plantmegapack.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPDecorator;
import plantmegapack.common.PMPDecoratorSet;
import plantmegapack.common.PMPDecoratorType;
import plantmegapack.data.PMPDataDecorator;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigDecorators extends PMPGuiConfigBase {

    private static final ResourceLocation imageLogoBOP = new ResourceLocation("plantmegapack:textures/gui/logoBOP.png");
    private static final ResourceLocation imageLogoEB = new ResourceLocation("plantmegapack:textures/gui/logoEB.png");
    private static final ResourceLocation imageLogoEBXL = new ResourceLocation(
        "plantmegapack:textures/gui/logoEBXL.png");
    private static final ResourceLocation imageLogoHL = new ResourceLocation("plantmegapack:textures/gui/logoHL.png");
    private static final ResourceLocation imageLogoRWG = new ResourceLocation("plantmegapack:textures/gui/logoRWG.png");
    private static final ResourceLocation imageLogoVC = new ResourceLocation("plantmegapack:textures/gui/logoVC.png");
    private static final ResourceLocation imageLogoVANILLA = new ResourceLocation(
        "plantmegapack:textures/gui/logoVANILLA.png");
    private static final int LIST_HEIGHT = 116;
    private static final int LIST_WIDTH = 150;
    private PMPGuiStringList decoratorList;
    private PMPGuiButton buttonCustomize;
    private PMPGuiButton buttonResetAllDecorators;
    private ArrayList<String> arrayDecoratorNames = new ArrayList();
    private ArrayList<String> arrayDecoratorIDs = new ArrayList();
    private PMPDataDecorator curDecorator;
    private PMPDecoratorSet decoratorSet;
    private static PMPGuiStringListState decoratorListState = new PMPGuiStringListState();

    public PMPGuiConfigDecorators(GuiScreen parent, PMPDecoratorSet decoratorSet) {
        super(parent, "gui.screen.configDecorators", 0);
        this.decoratorSet = decoratorSet;
        this.name = this.decoratorSet.getLocalizedName() + " "
            + LanguageRegistry.instance()
                .getStringLocalization("gui.screen.configDecorators");
        this.loadDecoratorData();
    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        int xPos = this.width / 2 - 152;
        int yPos = 36;
        this.decoratorList = new PMPGuiStringList(
            this,
            150,
            116,
            yPos,
            yPos + 116,
            xPos,
            this.getFontRenderer().FONT_HEIGHT + 2);
        this.decoratorList.setTextArray(this.arrayDecoratorNames);
        this.decoratorList.useListState(decoratorListState);
        this.curDecorator = PlantMegaPack.worldGenerator
            .getDecoratorByID((String) this.arrayDecoratorIDs.get(decoratorListState.selectedIndex));
        xPos = this.width / 2 + 2;
        xPos += 6;
        yPos = 84;
        this.buttonCustomize = new PMPGuiButton(
            4,
            xPos,
            yPos,
            150,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("gui.button.customize") + "...");
        this.buttonList.add(this.buttonCustomize);
        xPos = this.width / 2 - 90;
        yPos = 156;
        this.buttonResetAllDecorators = new PMPGuiButton(
            5,
            xPos,
            yPos,
            180,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("gui.button.resetAllDecorators") + "...");
        this.buttonList.add(this.buttonResetAllDecorators);
    }

    public void confirmClicked(boolean state, int ID) {
        if (ID == 5) {
            if (state) {
                PlantMegaPack.worldGenerator.resetAllDecoratorsInSet(this.decoratorSet);
            }

            this.mc.displayGuiScreen(this);
        }

    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 2:
                    decoratorListState.resetState();
                case 3:
                default:
                    break;
                case 4:
                    this.decoratorList.saveListState(decoratorListState);
                    this.mc.displayGuiScreen(
                        new PMPGuiConfigDecoratorCustomize(this, this.curDecorator, this.decoratorSet));
                    return;
                case 5:
                    this.decoratorList.saveListState(decoratorListState);
                    this.mc.displayGuiScreen(
                        new PMPGuiYesNo(
                            this,
                            "gui.screen.yesno.resetDecorators",
                            LanguageRegistry.instance()
                                .getStringLocalization("text.gui.warning"),
                            LanguageRegistry.instance()
                                .getStringLocalization("text.gui.warningResetDecorator1") + " "
                                + this.decoratorSet.getLocalizedName()
                                + " "
                                + LanguageRegistry.instance()
                                    .getStringLocalization("text.gui.warningResetDecorator2"),
                            LanguageRegistry.instance()
                                .getStringLocalization("gui.button.reset"),
                            LanguageRegistry.instance()
                                .getStringLocalization("gui.button.cancel"),
                            5));
                    return;
            }
        }

        super.actionPerformed(button);
    }

    protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);
    }

    protected void mouseMovedOrUp(int par1, int par2, int par3) {
        super.mouseMovedOrUp(par1, par2, par3);
    }

    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        this.decoratorList.drawScreen(par1, par2, par3);
        super.drawScreen(par1, par2, par3);
        this.drawModInstalledIndicator();
        this.drawModLogoImage();
        this.drawDecoratorInfo();
    }

    public void listItemSelected(PMPGuiStringList list, int index) {
        this.curDecorator = PlantMegaPack.worldGenerator.getDecoratorByID((String) this.arrayDecoratorIDs.get(index));
    }

    public void listItemActivate(PMPGuiStringList list) {}

    public void updateIntSetting(String name, int value) {}

    private void drawDecoratorInfo() {
        int xPos = this.width / 2 + 2;
        int yPos = 40;
        String text = this.decoratorList.getSelectedItemText();
        this.drawString(this.getFontRenderer(), text, xPos, yPos, 9502624);
        yPos += this.getFontRenderer().FONT_HEIGHT + 4;
        text = LanguageRegistry.instance()
            .getStringLocalization("gui.label.decoratorType") + ": ";
        this.drawString(this.getFontRenderer(), text, xPos, yPos, 6316128);
        text = this.curDecorator.decorator.decoratorType.getLocalizedName();
        this.drawString(this.getFontRenderer(), text, xPos + 32, yPos, 8437888);
        yPos += this.getFontRenderer().FONT_HEIGHT + 4;
        text = LanguageRegistry.instance()
            .getStringLocalization("gui.label.decoratorCount") + ": ";
        this.drawString(this.getFontRenderer(), text, xPos, yPos, 6316128);
        text = String.valueOf(this.arrayDecoratorNames.size());
        this.drawString(this.getFontRenderer(), text, xPos + 64, yPos, 8437888);
    }

    private void loadDecoratorData() {
        this.arrayDecoratorNames.clear();
        this.arrayDecoratorIDs.clear();
        PMPDecorator[] var1 = PMPDecorator.values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            PMPDecorator decorators = var1[var3];
            if (this.decoratorSet == decorators.decoratorSet && decorators.decoratorType != PMPDecoratorType.FEATURES) {
                this.arrayDecoratorNames.add(
                    LanguageRegistry.instance()
                        .getStringLocalization("decorator.biome." + decorators.decoratorSet.ID + "." + decorators.ID));
                this.arrayDecoratorIDs.add(decorators.ID);
            }
        }

    }

    private void drawModInstalledIndicator() {
        int xPos = this.width / 2 + 2;
        int yPos = 84;
        boolean isModLoaded = false;
        switch (this.decoratorSet) {
            case BOP:
                isModLoaded = Loader.isModLoaded("BiomesOPlenty");
                break;
            case EB:
                isModLoaded = Loader.isModLoaded("enhancedbiomes");
                break;
            case EBXL:
                isModLoaded = Loader.isModLoaded("ExtrabiomesXL");
                break;
            case HIGHLANDS:
                isModLoaded = Loader.isModLoaded("Highlands");
                break;
            case RWG:
                isModLoaded = Loader.isModLoaded("RWG");
                break;
            case VC:
                isModLoaded = Loader.isModLoaded("vintagecraft");
                break;
            case VANILLA:
                isModLoaded = true;
                break;
            default:
                return;
        }

        this.drawLED(xPos, yPos, 0, isModLoaded);
    }

    protected void drawModLogoImage() {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        switch (this.decoratorSet) {
            case BOP:
                this.mc.renderEngine.bindTexture(imageLogoBOP);
                break;
            case EB:
                this.mc.renderEngine.bindTexture(imageLogoEB);
                break;
            case EBXL:
                this.mc.renderEngine.bindTexture(imageLogoEBXL);
                break;
            case HIGHLANDS:
                this.mc.renderEngine.bindTexture(imageLogoHL);
                break;
            case RWG:
                this.mc.renderEngine.bindTexture(imageLogoRWG);
                break;
            case VC:
                this.mc.renderEngine.bindTexture(imageLogoVC);
                break;
            case VANILLA:
                this.mc.renderEngine.bindTexture(imageLogoVANILLA);
                break;
            default:
                return;
        }

        int x = this.width / 2 + 2;
        int y = 108;
        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        tess.addVertexWithUV((double) x, (double) (y + 40), (double) this.zLevel, 0.0, 1.0);
        tess.addVertexWithUV((double) (x + 150), (double) (y + 40), (double) this.zLevel, 1.0, 1.0);
        tess.addVertexWithUV((double) (x + 150), (double) y, (double) this.zLevel, 1.0, 0.0);
        tess.addVertexWithUV((double) x, (double) y, (double) this.zLevel, 0.0, 0.0);
        tess.draw();
    }
}
