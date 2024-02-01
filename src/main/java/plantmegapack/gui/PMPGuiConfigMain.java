package plantmegapack.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigMain extends PMPGuiConfigBase {

    public PMPGuiConfigMain(GuiScreen parent) {
        super(parent, "gui.screen.configMain", 0);
    }

    public void initGui() {
        super.initGui();
        int xPos = this.width / 2 - 90;
        int yPos = 36;
        this.buttonList.add(
            new PMPGuiButton(
                3,
                xPos,
                yPos,
                180,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("gui.screen.configSettingsGeneral") + "..."));
        xPos = this.width / 2 - 154;
        yPos += 24;
        this.buttonList.add(
            new PMPGuiButton(
                4,
                xPos + 12,
                yPos,
                138,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("gui.screen.configSettingsPlants") + "..."));
        yPos += 24;
        this.buttonList.add(
            new PMPGuiButton(
                5,
                xPos,
                yPos,
                150,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("gui.screen.configPlants") + "..."));
        yPos += 24;
        this.buttonList.add(
            new PMPGuiButton(
                6,
                xPos + 12,
                yPos,
                138,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("gui.screen.configItems") + "..."));
        xPos = this.width / 2 + 4;
        yPos = 60;
        this.buttonList.add(
            new PMPGuiButton(
                7,
                xPos,
                yPos,
                138,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("gui.screen.configSettingsWorldgen") + "..."));
        yPos = yPos + 24;
        this.buttonList.add(
            new PMPGuiButton(
                8,
                xPos,
                yPos,
                150,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("gui.screen.configWorldgen") + "..."));
        yPos += 24;
        this.buttonList.add(
            new PMPGuiButton(
                9,
                xPos,
                yPos,
                138,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("gui.screen.configDecorators") + "..."));
        xPos = this.width / 2 - 90;
        yPos += 24;
        this.buttonList.add(
            new PMPGuiButton(
                10,
                xPos,
                yPos,
                180,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("gui.screen.configContent") + "..."));
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 3:
                    this.mc.displayGuiScreen(new PMPGuiConfigSettingsGeneral(this));
                    return;
                case 4:
                    this.mc.displayGuiScreen(new PMPGuiConfigSettingsPlants(this));
                    return;
                case 5:
                    this.mc.displayGuiScreen(new PMPGuiConfigPlants(this));
                    return;
                case 6:
                    this.mc.displayGuiScreen(new PMPGuiConfigItems(this));
                    return;
                case 7:
                    this.mc.displayGuiScreen(new PMPGuiConfigSettingsWorldgen(this));
                    return;
                case 8:
                    this.mc.displayGuiScreen(new PMPGuiConfigWorldgen(this));
                    return;
                case 9:
                    this.mc.displayGuiScreen(new PMPGuiConfigDecoratorSelect(this));
                    return;
                case 10:
                    this.mc.displayGuiScreen(new PMPGuiConfigContent(this));
                    return;
            }
        }

        super.actionPerformed(button);
    }

    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        super.drawScreen(par1, par2, par3);
    }

    public void listItemSelected(PMPGuiStringList list, int index) {}

    public void listItemActivate(PMPGuiStringList list) {}

    public void updateIntSetting(String name, int value) {}
}
