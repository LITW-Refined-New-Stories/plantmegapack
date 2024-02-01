package plantmegapack.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockPlant;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigPlantAttributes extends PMPGuiConfigBase {

    private PMPGuiSlider sliderDropAlways;
    private PMPGuiSlider sliderDropRandomChance;
    private PMPGuiSlider sliderDropRandomAmount;
    private PMPGuiSlider sliderGrowthRate;
    private PMPBlockPlant plantBlock;

    public PMPGuiConfigPlantAttributes(GuiScreen parent, String plantName) {
        super(parent, "gui.screen.configPlantAttributes", 1);
        this.plantBlock = PlantMegaPack.blocks.getPlantBlock(plantName);
        if (this.plantBlock != null) {
            this.name = this.name + " - " + this.plantBlock.getLocalizedName();
        }

    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        if (this.plantBlock != null) {
            int xPos = this.width / 2 - 90;
            int yPos = 36;
            this.sliderGrowthRate = new PMPGuiSlider(
                this,
                3,
                xPos,
                yPos,
                180,
                20,
                "gui.slider.growthRate",
                0,
                this.plantBlock.plantData.growthRate,
                100,
                1);
            xPos = this.width / 2 - 152;
            yPos += 24;
            this.sliderDropAlways = new PMPGuiSlider(
                this,
                4,
                xPos,
                yPos,
                150,
                20,
                "gui.slider.dropAlways",
                1,
                this.plantBlock.plantData.dropAlways,
                3,
                1);
            xPos += 154;
            this.sliderDropRandomChance = new PMPGuiSlider(
                this,
                5,
                xPos,
                yPos,
                150,
                20,
                "gui.slider.dropRandomChance",
                0,
                this.plantBlock.plantData.dropRandomChance,
                100,
                1);
            yPos += 24;
            this.sliderDropRandomAmount = new PMPGuiSlider(
                this,
                6,
                xPos,
                yPos,
                150,
                20,
                "gui.slider.dropRandomAmount",
                1,
                this.plantBlock.plantData.dropRandomAmount,
                4,
                1);
            this.sliderDropRandomAmount.enabled = this.plantBlock.plantData.dropRandomChance > 0;
            this.buttonList.add(this.sliderDropAlways);
            this.buttonList.add(this.sliderDropRandomChance);
            this.buttonList.add(this.sliderDropRandomAmount);
            this.buttonList.add(this.sliderGrowthRate);
        }
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 1:
                    if (this.plantBlock != null) {
                        this.plantBlock.plantData.resetPlantDataDefaults();
                        this.plantBlock.plantData.saveUserConfig();
                    }
                    break;
                case 2:
                    if (this.plantBlock != null) {
                        this.plantBlock.plantData.dropAlways = this.sliderDropAlways.getIntValue();
                        this.plantBlock.plantData.dropRandomChance = this.sliderDropRandomChance.getIntValue();
                        this.plantBlock.plantData.dropRandomAmount = this.sliderDropRandomAmount.getIntValue();
                        this.plantBlock.plantData.growthRate = this.sliderGrowthRate.getIntValue();
                        this.plantBlock.plantData.saveUserConfig();
                    }
                    break;
                case 3:
                    return;
                case 4:
                    return;
                case 5:
                    return;
                case 6:
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
    }

    public void listItemSelected(PMPGuiStringList list, int index) {}

    public void listItemActivate(PMPGuiStringList list) {}

    public void updateIntSetting(String name, int value) {
        if (name.matches("gui.slider.dropRandomChance")) {
            this.sliderDropRandomAmount.enabled = value > 0;
        }

    }
}
