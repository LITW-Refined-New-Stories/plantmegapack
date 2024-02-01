package plantmegapack.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.common.PMPDecoratorSet;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigDecoratorSelect extends PMPGuiConfigBase {

    public PMPGuiConfigDecoratorSelect(GuiScreen parent) {
        super(parent, "gui.screen.configDecoratorSelect", 0);
    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        int xPos = this.width / 2 - 78;
        xPos += 6;
        int yPos = 36;
        this.buttonList.add(
            new PMPGuiButton(
                3,
                xPos,
                yPos,
                150,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("decorator.set.vanilla") + "..."));
        xPos = this.width / 2 - 154;
        yPos = 68;
        this.buttonList.add(
            new PMPGuiButton(
                4,
                xPos,
                yPos,
                150,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("decorator.set.bop") + "..."));
        yPos += 24;
        this.buttonList.add(
            new PMPGuiButton(
                5,
                xPos,
                yPos,
                150,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("decorator.set.eb") + "..."));
        yPos += 24;
        this.buttonList.add(
            new PMPGuiButton(
                6,
                xPos,
                yPos,
                150,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("decorator.set.ebxl") + "..."));
        xPos = this.width / 2 + 10;
        yPos = 68;
        this.buttonList.add(
            new PMPGuiButton(
                7,
                xPos,
                yPos,
                150,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("decorator.set.hl") + "..."));
        yPos = yPos + 24;
        this.buttonList.add(
            new PMPGuiButton(
                8,
                xPos,
                yPos,
                150,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("decorator.set.rwg") + "..."));
        this.updateButtonLabels();
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 1:
                case 2:
                default:
                    break;
                case 3:
                    this.mc.displayGuiScreen(new PMPGuiConfigDecorators(this, PMPDecoratorSet.VANILLA));
                    return;
                case 4:
                    this.mc.displayGuiScreen(new PMPGuiConfigDecorators(this, PMPDecoratorSet.BOP));
                    return;
                case 5:
                    this.mc.displayGuiScreen(new PMPGuiConfigDecorators(this, PMPDecoratorSet.EB));
                    return;
                case 6:
                    this.mc.displayGuiScreen(new PMPGuiConfigDecorators(this, PMPDecoratorSet.EBXL));
                    return;
                case 7:
                    this.mc.displayGuiScreen(new PMPGuiConfigDecorators(this, PMPDecoratorSet.HIGHLANDS));
                    return;
                case 8:
                    this.mc.displayGuiScreen(new PMPGuiConfigDecorators(this, PMPDecoratorSet.RWG));
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
        super.drawScreen(par1, par2, par3);
        this.drawModInstalledIndicators();
    }

    public void listItemSelected(PMPGuiStringList list, int index) {}

    public void listItemActivate(PMPGuiStringList list) {}

    public void updateIntSetting(String name, int value) {}

    private void updateButtonLabels() {}

    private void drawModInstalledIndicators() {
        int xPos = this.width / 2 - 78;
        int yPos = 36;
        this.drawLED(xPos, yPos, 0, true);
        boolean isModLoaded = Loader.isModLoaded("BiomesOPlenty");
        xPos = this.width / 2 - 154 - 6;
        yPos = 68;
        this.drawLED(xPos, yPos, 0, isModLoaded);
        isModLoaded = Loader.isModLoaded("enhancedbiomes");
        yPos += 24;
        this.drawLED(xPos, yPos, 0, isModLoaded);
        isModLoaded = Loader.isModLoaded("ExtrabiomesXL");
        yPos += 24;
        this.drawLED(xPos, yPos, 0, isModLoaded);
        xPos = this.width / 2 + 4;
        yPos = 68;
        isModLoaded = Loader.isModLoaded("Highlands");
        this.drawLED(xPos, yPos, 0, isModLoaded);
        isModLoaded = Loader.isModLoaded("RWG");
        yPos = yPos + 24;
        this.drawLED(xPos, yPos, 0, isModLoaded);
    }
}
