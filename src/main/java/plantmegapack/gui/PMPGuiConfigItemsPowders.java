package plantmegapack.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.IIcon;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigItemsPowders extends PMPGuiConfigBase {

    private PMPGuiSlider powderConditionerRadius;
    private PMPGuiSlider powderConditionerStrength;
    private PMPGuiSlider powderDefoliantRadius;
    private PMPGuiSlider powderDefoliantStrength;
    private PMPGuiSlider powderFertilizerRadius;
    private PMPGuiSlider powderFertilizerStrength;
    private IIcon iconConditioner;
    private IIcon iconDefoliant;
    private IIcon iconFertilizer;

    public PMPGuiConfigItemsPowders(GuiScreen parent) {
        super(parent, "gui.screen.configItemsPowders", 1);
        this.iconConditioner = PlantMegaPack.items.getPlantPowder("powderConditioner")
            .getIconFromDamage(0);
        this.iconDefoliant = PlantMegaPack.items.getPlantPowder("powderDefoliant")
            .getIconFromDamage(0);
        this.iconFertilizer = PlantMegaPack.items.getPlantPowder("powderFertilizer")
            .getIconFromDamage(0);
    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        int startX = this.width / 2 - 164;
        startX += 24;
        int yPos = 36;
        this.powderConditionerRadius = new PMPGuiSlider(
            this,
            3,
            startX,
            yPos,
            150,
            20,
            "option.powderConditionerRadius",
            1,
            PlantMegaPack.settingsGeneral.powderConditionerRadius,
            4,
            1);
        int xPos = startX + 154;
        this.powderConditionerStrength = new PMPGuiSlider(
            this,
            4,
            xPos,
            yPos,
            150,
            20,
            "option.powderConditionerStrength",
            1,
            PlantMegaPack.settingsGeneral.powderConditionerStrength,
            100,
            1);
        yPos = 60;
        this.powderDefoliantRadius = new PMPGuiSlider(
            this,
            5,
            startX,
            yPos,
            150,
            20,
            "option.powderDefoliantRadius",
            1,
            PlantMegaPack.settingsGeneral.powderDefoliantRadius,
            4,
            1);
        xPos = startX + 154;
        this.powderDefoliantStrength = new PMPGuiSlider(
            this,
            6,
            xPos,
            yPos,
            150,
            20,
            "option.powderDefoliantStrength",
            1,
            PlantMegaPack.settingsGeneral.powderDefoliantStrength,
            100,
            1);
        yPos = 84;
        this.powderFertilizerRadius = new PMPGuiSlider(
            this,
            7,
            startX,
            yPos,
            150,
            20,
            "option.powderFertilizerRadius",
            1,
            PlantMegaPack.settingsGeneral.powderFertilizerRadius,
            4,
            1);
        xPos = startX + 154;
        this.powderFertilizerStrength = new PMPGuiSlider(
            this,
            8,
            xPos,
            yPos,
            150,
            20,
            "option.powderFertilizerStrength",
            1,
            PlantMegaPack.settingsGeneral.powderFertilizerStrength,
            100,
            1);
        this.updateButtonLabels();
        this.buttonList.add(this.powderConditionerRadius);
        this.buttonList.add(this.powderConditionerStrength);
        this.buttonList.add(this.powderDefoliantRadius);
        this.buttonList.add(this.powderDefoliantStrength);
        this.buttonList.add(this.powderFertilizerRadius);
        this.buttonList.add(this.powderFertilizerStrength);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 1:
                    PlantMegaPack.settingsGeneral.resetItemPowderDefaults();
                    PlantMegaPack.settingsGeneral.saveOptions();
                    break;
                case 2:
                    PlantMegaPack.settingsGeneral.powderConditionerRadius = this.powderConditionerRadius.getIntValue();
                    PlantMegaPack.settingsGeneral.powderConditionerStrength = this.powderConditionerStrength
                        .getIntValue();
                    PlantMegaPack.settingsGeneral.powderDefoliantRadius = this.powderDefoliantRadius.getIntValue();
                    PlantMegaPack.settingsGeneral.powderDefoliantStrength = this.powderDefoliantStrength.getIntValue();
                    PlantMegaPack.settingsGeneral.powderFertilizerRadius = this.powderFertilizerRadius.getIntValue();
                    PlantMegaPack.settingsGeneral.powderFertilizerStrength = this.powderFertilizerStrength
                        .getIntValue();
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
        this.drawPowderIcons();
    }

    public void listItemSelected(PMPGuiStringList list, int index) {}

    public void listItemActivate(PMPGuiStringList list) {}

    public void updateIntSetting(String name, int value) {}

    private void updateButtonLabels() {
        this.powderConditionerRadius.displayString = LanguageRegistry.instance()
            .getStringLocalization("option.powderConditionerRadius") + ": "
            + this.powderConditionerRadius.getIntValue();
        this.powderConditionerStrength.displayString = LanguageRegistry.instance()
            .getStringLocalization("option.powderConditionerStrength") + ": "
            + this.powderConditionerStrength.getIntValue()
            + "%";
        this.powderDefoliantRadius.displayString = LanguageRegistry.instance()
            .getStringLocalization("option.powderDefoliantRadius") + ": "
            + this.powderDefoliantRadius.getIntValue();
        this.powderDefoliantStrength.displayString = LanguageRegistry.instance()
            .getStringLocalization("option.powderDefoliantStrength") + ": "
            + this.powderDefoliantStrength.getIntValue()
            + "%";
        this.powderFertilizerRadius.displayString = LanguageRegistry.instance()
            .getStringLocalization("option.powderFertilizerRadius") + ": "
            + this.powderFertilizerRadius.getIntValue();
        this.powderFertilizerStrength.displayString = LanguageRegistry.instance()
            .getStringLocalization("option.powderFertilizerStrength") + ": "
            + this.powderFertilizerStrength.getIntValue()
            + "%";
    }

    private void drawPowderIcons() {
        int startX = this.width / 2 - 164;
        int yPos = 36;
        this.drawIconTexture(this.iconConditioner, startX, yPos, false, true);
        yPos += 24;
        this.drawIconTexture(this.iconDefoliant, startX, yPos, false, true);
        yPos += 24;
        this.drawIconTexture(this.iconFertilizer, startX, yPos, false, true);
    }
}
