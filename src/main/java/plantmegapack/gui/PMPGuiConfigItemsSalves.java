package plantmegapack.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.IIcon;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigItemsSalves extends PMPGuiConfigBase {

    private PMPGuiSlider salveFireResist;
    private PMPGuiSlider salveHealth;
    private PMPGuiSlider salveNightVision;
    private PMPGuiSlider salveStrength;
    private PMPGuiSlider salveSwiftness;
    private PMPGuiSlider salveWaterBreathing;
    private IIcon iconFireResist;
    private IIcon iconHealth;
    private IIcon iconNightVision;
    private IIcon iconStrength;
    private IIcon iconSwiftness;
    private IIcon iconWaterBreathing;

    public PMPGuiConfigItemsSalves(GuiScreen parent) {
        super(parent, "gui.screen.configItemsSalves", 1);
        this.iconFireResist = PlantMegaPack.items.getSalve("salveFireResist")
            .getIconFromDamage(0);
        this.iconHealth = PlantMegaPack.items.getSalve("salveHealth")
            .getIconFromDamage(0);
        this.iconNightVision = PlantMegaPack.items.getSalve("salveNightVision")
            .getIconFromDamage(0);
        this.iconStrength = PlantMegaPack.items.getSalve("salveStrength")
            .getIconFromDamage(0);
        this.iconSwiftness = PlantMegaPack.items.getSalve("salveSwiftness")
            .getIconFromDamage(0);
        this.iconWaterBreathing = PlantMegaPack.items.getSalve("salveWaterBreathing")
            .getIconFromDamage(0);
    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        int startX = this.width / 2 - 104;
        startX += 24;
        int yPos = 36;
        this.salveFireResist = new PMPGuiSlider(
            this,
            3,
            startX,
            yPos,
            180,
            20,
            "option.salveFireResistDuration",
            10,
            PlantMegaPack.settingsGeneral.salveFireResistDuration,
            120,
            1);
        yPos += 24;
        this.salveHealth = new PMPGuiSlider(
            this,
            4,
            startX,
            yPos,
            180,
            20,
            "option.salveHealthHeartsHealed",
            2,
            PlantMegaPack.settingsGeneral.salveHealthHeartsHealed,
            8,
            1);
        this.salveHealth.enabled = false;
        yPos += 24;
        this.salveNightVision = new PMPGuiSlider(
            this,
            5,
            startX,
            yPos,
            180,
            20,
            "option.salveNightVisionDuration",
            10,
            PlantMegaPack.settingsGeneral.salveNightVisionDuration,
            120,
            1);
        yPos += 24;
        this.salveStrength = new PMPGuiSlider(
            this,
            6,
            startX,
            yPos,
            180,
            20,
            "option.salveStrengthDuration",
            10,
            PlantMegaPack.settingsGeneral.salveStrengthDuration,
            120,
            1);
        yPos += 24;
        this.salveSwiftness = new PMPGuiSlider(
            this,
            7,
            startX,
            yPos,
            180,
            20,
            "option.salveSwiftnessDuration",
            10,
            PlantMegaPack.settingsGeneral.salveSwiftnessDuration,
            120,
            1);
        yPos += 24;
        this.salveWaterBreathing = new PMPGuiSlider(
            this,
            8,
            startX,
            yPos,
            180,
            20,
            "option.salveWaterBreathingDuration",
            10,
            PlantMegaPack.settingsGeneral.salveWaterBreathingDuration,
            120,
            1);
        this.updateButtonLabels();
        this.buttonList.add(this.salveFireResist);
        this.buttonList.add(this.salveHealth);
        this.buttonList.add(this.salveNightVision);
        this.buttonList.add(this.salveStrength);
        this.buttonList.add(this.salveSwiftness);
        this.buttonList.add(this.salveWaterBreathing);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 1:
                    PlantMegaPack.settingsGeneral.resetItemSalveDefaults();
                    PlantMegaPack.settingsGeneral.saveOptions();
                    break;
                case 2:
                    PlantMegaPack.settingsGeneral.salveFireResistDuration = this.salveFireResist.getIntValue();
                    PlantMegaPack.settingsGeneral.salveHealthHeartsHealed = this.salveHealth.getIntValue();
                    PlantMegaPack.settingsGeneral.salveNightVisionDuration = this.salveNightVision.getIntValue();
                    PlantMegaPack.settingsGeneral.salveStrengthDuration = this.salveStrength.getIntValue();
                    PlantMegaPack.settingsGeneral.salveSwiftnessDuration = this.salveSwiftness.getIntValue();
                    PlantMegaPack.settingsGeneral.salveWaterBreathingDuration = this.salveWaterBreathing.getIntValue();
                    PlantMegaPack.settingsGeneral.saveOptions();
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
        this.drawSalveIcons();
    }

    public void listItemSelected(PMPGuiStringList list, int index) {}

    public void listItemActivate(PMPGuiStringList list) {}

    public void updateIntSetting(String name, int value) {}

    private void updateButtonLabels() {
        this.salveFireResist.displayString = LanguageRegistry.instance()
            .getStringLocalization("option.salveFireResistDuration") + ": "
            + this.salveFireResist.getIntValue();
        this.salveHealth.displayString = LanguageRegistry.instance()
            .getStringLocalization("option.salveHealthHeartsHealed") + ": "
            + this.salveHealth.getIntValue();
        this.salveNightVision.displayString = LanguageRegistry.instance()
            .getStringLocalization("option.salveNightVisionDuration") + ": "
            + this.salveNightVision.getIntValue();
        this.salveStrength.displayString = LanguageRegistry.instance()
            .getStringLocalization("option.salveStrengthDuration") + ": "
            + this.salveStrength.getIntValue();
        this.salveSwiftness.displayString = LanguageRegistry.instance()
            .getStringLocalization("option.salveSwiftnessDuration") + ": "
            + this.salveSwiftness.getIntValue();
        this.salveWaterBreathing.displayString = LanguageRegistry.instance()
            .getStringLocalization("option.salveWaterBreathingDuration") + ": "
            + this.salveWaterBreathing.getIntValue();
    }

    private void drawSalveIcons() {
        int startX = this.width / 2 - 104;
        int yPos = 34;
        this.drawIconTexture(this.iconFireResist, startX, yPos, false, true);
        yPos += 24;
        this.drawIconTexture(this.iconHealth, startX, yPos, false, true);
        yPos += 24;
        this.drawIconTexture(this.iconNightVision, startX, yPos, false, true);
        yPos += 24;
        this.drawIconTexture(this.iconStrength, startX, yPos, false, true);
        yPos += 24;
        this.drawIconTexture(this.iconSwiftness, startX, yPos, false, true);
        yPos += 24;
        this.drawIconTexture(this.iconWaterBreathing, startX, yPos, false, true);
    }
}
