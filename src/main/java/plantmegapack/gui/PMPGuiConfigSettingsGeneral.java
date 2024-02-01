package plantmegapack.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigSettingsGeneral extends PMPGuiConfigBase {

    private PMPGuiButton tooltipLatinName;
    private PMPGuiButton tooltipCategory;
    private PMPGuiButton tooltipAttributes;
    private PMPGuiButton tooltipGrowthStages;

    public PMPGuiConfigSettingsGeneral(GuiScreen parent) {
        super(parent, "gui.screen.configSettingsGeneral", 1);
    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        int startX = this.width / 2 - 152;
        int yPos = 60;
        this.tooltipLatinName = new PMPGuiButton(
            3,
            startX,
            yPos,
            150,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("option.tooltipLatinName"));
        this.tooltipLatinName.setButtonMode(3);
        int xPos = startX + 154;
        this.tooltipCategory = new PMPGuiButton(
            4,
            xPos,
            yPos,
            150,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("option.tooltipCategory"));
        this.tooltipCategory.setButtonMode(3);
        yPos += 24;
        this.tooltipAttributes = new PMPGuiButton(
            5,
            startX,
            yPos,
            150,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("option.tooltipAttributes"));
        this.tooltipAttributes.setButtonMode(3);
        xPos = startX + 154;
        this.tooltipGrowthStages = new PMPGuiButton(
            6,
            xPos,
            yPos,
            150,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("option.tooltipGrowthStages"));
        this.tooltipGrowthStages.setButtonMode(3);
        this.updateButtons();
        this.buttonList.add(this.tooltipLatinName);
        this.buttonList.add(this.tooltipCategory);
        this.buttonList.add(this.tooltipAttributes);
        this.buttonList.add(this.tooltipGrowthStages);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 1:
                    PlantMegaPack.settingsGeneral.resetGeneralDefaults();
                    PlantMegaPack.settingsGeneral.saveOptions();
                    break;
                case 2:
                    PlantMegaPack.settingsGeneral.saveOptions();
                    break;
                case 3:
                    PlantMegaPack.settingsGeneral.tooltipLatinName = !PlantMegaPack.settingsGeneral.tooltipLatinName;
                    this.updateButtons();
                    return;
                case 4:
                    PlantMegaPack.settingsGeneral.tooltipCategory = !PlantMegaPack.settingsGeneral.tooltipCategory;
                    this.updateButtons();
                    return;
                case 5:
                    PlantMegaPack.settingsGeneral.tooltipAttributes = !PlantMegaPack.settingsGeneral.tooltipAttributes;
                    this.updateButtons();
                    return;
                case 6:
                    PlantMegaPack.settingsGeneral.tooltipGrowthStages = !PlantMegaPack.settingsGeneral.tooltipGrowthStages;
                    this.updateButtons();
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
        this.drawLabels();
    }

    public void listItemSelected(PMPGuiStringList list, int index) {}

    public void listItemActivate(PMPGuiStringList list) {}

    public void updateIntSetting(String name, int value) {}

    private void updateButtons() {
        this.tooltipLatinName.setButtonState(PlantMegaPack.settingsGeneral.tooltipLatinName);
        this.tooltipCategory.setButtonState(PlantMegaPack.settingsGeneral.tooltipCategory);
        this.tooltipAttributes.setButtonState(PlantMegaPack.settingsGeneral.tooltipAttributes);
        this.tooltipGrowthStages.setButtonState(PlantMegaPack.settingsGeneral.tooltipGrowthStages);
    }

    private void drawLabels() {
        int xPos = this.width / 2;
        int yPos = 40;
        String text = LanguageRegistry.instance()
            .getStringLocalization("gui.label.tooltips");
        this.drawCenteredString(this.getFontRenderer(), text, xPos, yPos, 5296160);
    }
}
