package plantmegapack.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigWorldgen extends PMPGuiConfigBase {

    private PMPGuiSlider worldgenOverworldRate;
    private PMPGuiSlider worldgenOverworldPasses;
    private PMPGuiButton worldgenOverworldDefault;
    private PMPGuiSlider worldgenNetherRate;
    private PMPGuiSlider worldgenNetherPasses;
    private PMPGuiButton worldgenNetherDefault;
    private PMPGuiSlider worldgenSkyRate;
    private PMPGuiSlider worldgenSkyPasses;
    private PMPGuiButton worldgenSkyDefault;
    private PMPGuiSlider worldgenTheEndRate;
    private PMPGuiSlider worldgenTheEndPasses;
    private PMPGuiButton worldgenTheEndDefault;

    public PMPGuiConfigWorldgen(GuiScreen parent) {
        super(parent, "gui.screen.configWorldgen", 0);
    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        int startX = this.width / 2 - 167;
        int yPos = 36;
        this.worldgenOverworldRate = new PMPGuiSlider(
            this,
            3,
            startX,
            yPos,
            150,
            20,
            "dimension.overworld",
            0,
            PlantMegaPack.settingsGeneral.worldgenOverworldRate,
            100,
            1);
        int xPos = startX + 154;
        this.worldgenOverworldPasses = new PMPGuiSlider(
            this,
            4,
            xPos,
            yPos,
            88,
            20,
            "gui.slider.passes",
            1,
            PlantMegaPack.settingsGeneral.worldgenOverworldPasses,
            24,
            1);
        xPos += 92;
        this.worldgenOverworldDefault = new PMPGuiButton(
            5,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("gui.button.default"));
        yPos = 60;
        this.worldgenNetherRate = new PMPGuiSlider(
            this,
            6,
            startX,
            yPos,
            150,
            20,
            "dimension.nether",
            0,
            PlantMegaPack.settingsGeneral.worldgenNetherRate,
            100,
            1);
        xPos = startX + 154;
        this.worldgenNetherPasses = new PMPGuiSlider(
            this,
            7,
            xPos,
            yPos,
            88,
            20,
            "gui.slider.passes",
            1,
            PlantMegaPack.settingsGeneral.worldgenNetherPasses,
            24,
            1);
        xPos += 92;
        this.worldgenNetherDefault = new PMPGuiButton(
            8,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("gui.button.default"));
        yPos = 84;
        this.worldgenSkyRate = new PMPGuiSlider(
            this,
            9,
            startX,
            yPos,
            150,
            20,
            "dimension.sky",
            0,
            PlantMegaPack.settingsGeneral.worldgenSkyRate,
            100,
            1);
        xPos = startX + 154;
        this.worldgenSkyPasses = new PMPGuiSlider(
            this,
            10,
            xPos,
            yPos,
            88,
            20,
            "gui.slider.passes",
            1,
            PlantMegaPack.settingsGeneral.worldgenSkyPasses,
            24,
            1);
        xPos += 92;
        this.worldgenSkyDefault = new PMPGuiButton(
            11,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("gui.button.default"));
        yPos = 108;
        this.worldgenTheEndRate = new PMPGuiSlider(
            this,
            12,
            startX,
            yPos,
            150,
            20,
            "dimension.end",
            0,
            PlantMegaPack.settingsGeneral.worldgenEndRate,
            100,
            1);
        xPos = startX + 154;
        this.worldgenTheEndPasses = new PMPGuiSlider(
            this,
            13,
            xPos,
            yPos,
            88,
            20,
            "gui.slider.passes",
            1,
            PlantMegaPack.settingsGeneral.worldgenEndPasses,
            24,
            1);
        xPos += 92;
        this.worldgenTheEndDefault = new PMPGuiButton(
            14,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("gui.button.default"));
        this.updateButtonLabels();
        this.buttonList.add(this.worldgenOverworldRate);
        this.buttonList.add(this.worldgenOverworldPasses);
        this.buttonList.add(this.worldgenOverworldDefault);
        this.buttonList.add(this.worldgenNetherRate);
        this.buttonList.add(this.worldgenNetherPasses);
        this.buttonList.add(this.worldgenNetherDefault);
        this.buttonList.add(this.worldgenSkyRate);
        this.buttonList.add(this.worldgenSkyPasses);
        this.buttonList.add(this.worldgenSkyDefault);
        this.buttonList.add(this.worldgenTheEndRate);
        this.buttonList.add(this.worldgenTheEndPasses);
        this.buttonList.add(this.worldgenTheEndDefault);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 2:
                    PlantMegaPack.settingsGeneral.worldgenEndPasses = this.worldgenTheEndPasses.getIntValue();
                    PlantMegaPack.settingsGeneral.worldgenNetherPasses = this.worldgenNetherPasses.getIntValue();
                    PlantMegaPack.settingsGeneral.worldgenOverworldPasses = this.worldgenOverworldPasses.getIntValue();
                    PlantMegaPack.settingsGeneral.worldgenSkyPasses = this.worldgenSkyPasses.getIntValue();
                    PlantMegaPack.settingsGeneral.worldgenEndRate = this.worldgenTheEndRate.getIntValue();
                    PlantMegaPack.settingsGeneral.worldgenNetherRate = this.worldgenNetherRate.getIntValue();
                    PlantMegaPack.settingsGeneral.worldgenOverworldRate = this.worldgenOverworldRate.getIntValue();
                    PlantMegaPack.settingsGeneral.worldgenSkyRate = this.worldgenSkyRate.getIntValue();
                    PlantMegaPack.settingsGeneral.saveOptions();
                case 3:
                case 4:
                case 6:
                case 7:
                case 9:
                case 10:
                case 12:
                case 13:
                default:
                    break;
                case 5:
                    PlantMegaPack.settingsGeneral.resetWorldgenOverworld();
                    this.worldgenOverworldRate.setIntValue(PlantMegaPack.settingsGeneral.worldgenOverworldRate);
                    this.worldgenOverworldPasses.setIntValue(PlantMegaPack.settingsGeneral.worldgenOverworldPasses);
                    this.updateButtonLabels();
                    break;
                case 8:
                    PlantMegaPack.settingsGeneral.resetWorldgenNether();
                    this.worldgenNetherRate.setIntValue(PlantMegaPack.settingsGeneral.worldgenNetherRate);
                    this.worldgenNetherPasses.setIntValue(PlantMegaPack.settingsGeneral.worldgenNetherPasses);
                    this.updateButtonLabels();
                    break;
                case 11:
                    PlantMegaPack.settingsGeneral.resetWorldgenSky();
                    this.worldgenSkyRate.setIntValue(PlantMegaPack.settingsGeneral.worldgenSkyRate);
                    this.worldgenSkyPasses.setIntValue(PlantMegaPack.settingsGeneral.worldgenSkyPasses);
                    this.updateButtonLabels();
                    break;
                case 14:
                    PlantMegaPack.settingsGeneral.resetWorldgenEnd();
                    this.worldgenTheEndRate.setIntValue(PlantMegaPack.settingsGeneral.worldgenEndRate);
                    this.worldgenTheEndPasses.setIntValue(PlantMegaPack.settingsGeneral.worldgenEndPasses);
                    this.updateButtonLabels();
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
    }

    public void listItemSelected(PMPGuiStringList list, int index) {}

    public void listItemActivate(PMPGuiStringList list) {}

    public void updateIntSetting(String name, int value) {}

    private void updateButtonLabels() {
        this.worldgenOverworldRate.displayString = LanguageRegistry.instance()
            .getStringLocalization("dimension.overworld") + ": "
            + this.worldgenOverworldRate.getIntValue()
            + "%";
        this.worldgenOverworldPasses.displayString = LanguageRegistry.instance()
            .getStringLocalization("gui.slider.passes") + ": "
            + this.worldgenOverworldPasses.getIntValue();
        this.worldgenNetherRate.displayString = LanguageRegistry.instance()
            .getStringLocalization("dimension.nether") + ": "
            + this.worldgenNetherRate.getIntValue()
            + "%";
        this.worldgenNetherPasses.displayString = LanguageRegistry.instance()
            .getStringLocalization("gui.slider.passes") + ": "
            + this.worldgenNetherPasses.getIntValue();
        this.worldgenSkyRate.displayString = LanguageRegistry.instance()
            .getStringLocalization("dimension.sky") + ": "
            + this.worldgenSkyRate.getIntValue()
            + "%";
        this.worldgenSkyPasses.displayString = LanguageRegistry.instance()
            .getStringLocalization("gui.slider.passes") + ": "
            + this.worldgenSkyPasses.getIntValue();
        this.worldgenTheEndRate.displayString = LanguageRegistry.instance()
            .getStringLocalization("dimension.end") + ": "
            + this.worldgenTheEndRate.getIntValue()
            + "%";
        this.worldgenTheEndPasses.displayString = LanguageRegistry.instance()
            .getStringLocalization("gui.slider.passes") + ": "
            + this.worldgenTheEndPasses.getIntValue();
    }
}
