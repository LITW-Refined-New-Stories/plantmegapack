package plantmegapack.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.bin.PMPSettings;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigSettingsPlants extends PMPGuiConfigBase {

    private GuiListExtended optionsRowList;
    private static final PMPSettings.PMPOptions[] options;

    public PMPGuiConfigSettingsPlants(GuiScreen parent) {
        super(parent, "gui.screen.configSettingsPlants", 1);
    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        this.optionsRowList = new PMPGuiOptionRowList(
            this.mc,
            this.width,
            this.height,
            32,
            this.height - 32,
            25,
            options);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 1:
                    PlantMegaPack.settingsGeneral.resetPlantDefaults();
                    PlantMegaPack.settingsGeneral.saveOptions();
                    break;
                case 2:
                    PlantMegaPack.settingsGeneral.saveOptions();
            }
        }

        super.actionPerformed(button);
    }

    protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);
        this.optionsRowList.func_148179_a(par1, par2, par3);
    }

    protected void mouseMovedOrUp(int par1, int par2, int par3) {
        super.mouseMovedOrUp(par1, par2, par3);
        this.optionsRowList.func_148181_b(par1, par2, par3);
    }

    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        this.optionsRowList.drawScreen(par1, par2, par3);
        super.drawScreen(par1, par2, par3);
    }

    public void listItemSelected(PMPGuiStringList list, int index) {}

    public void listItemActivate(PMPGuiStringList list) {}

    public void updateIntSetting(String name, int value) {}

    static {
        options = new PMPSettings.PMPOptions[] { PMPSettings.PMPOptions.PLANT_EFFECT_POISON,
            PMPSettings.PMPOptions.PLANT_BAMBOOFALLSWHENBROKEN, PMPSettings.PMPOptions.PLANT_EFFECT_THORNS,
            PMPSettings.PMPOptions.PLANT_SOLIDSHELFEPIPHYTES };
    }
}
