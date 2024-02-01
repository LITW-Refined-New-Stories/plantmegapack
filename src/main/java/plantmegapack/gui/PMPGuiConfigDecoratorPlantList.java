package plantmegapack.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.IIcon;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockPlant;
import plantmegapack.common.PMPDecoratorArea;
import plantmegapack.data.PMPDataDecorator;
import plantmegapack.data.PMPDataPlantSpawnParams;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigDecoratorPlantList extends PMPGuiConfigBase {

    private static final int LIST_HEIGHT = 92;
    private static final int LIST_WIDTH = 180;
    private PMPDataDecorator decorator;
    private PMPDecoratorArea decoratorArea;
    private PMPGuiStringList plantList;
    private ArrayList<String> arrayPlantNames = new ArrayList();
    private PMPGuiButton buttonAdd;
    private PMPGuiButton buttonRemove;
    private PMPGuiButton buttonDefault;
    private PMPGuiSlider sliderGenRate;
    private PMPGuiButton buttonOverwriteVanilla;
    private PMPGuiSlider sliderElevationVariance;
    private PMPGuiSlider sliderClusterAmount;
    private PMPGuiSlider sliderClusterSize;
    private IIcon plantIcon;
    private static PMPGuiStringListState plantListState = new PMPGuiStringListState();

    public PMPGuiConfigDecoratorPlantList(GuiScreen parent, PMPDataDecorator decorator,
        PMPDecoratorArea decoratorArea) {
        super(parent, "gui.screen.configDecoratorPlantList", 0);
        this.decorator = decorator;
        this.decoratorArea = decoratorArea;
        if (this.decorator != null && this.decoratorArea != null) {
            this.name = this.name + " - "
                + this.decorator.decorator.getLocalizedName()
                + " - "
                + this.decoratorArea.getLocalizedName();
        }

    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        int xPos = this.width / 2 - 182;
        int yPos = 36;
        this.plantList = new PMPGuiStringList(
            this,
            180,
            92,
            yPos,
            yPos + 92,
            xPos,
            this.getFontRenderer().FONT_HEIGHT + 2);
        this.plantList.useListState(plantListState);
        yPos += 96;
        this.buttonAdd = new PMPGuiButton(
            4,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("gui.button.add") + "...");
        this.buttonList.add(this.buttonAdd);
        xPos += 92;
        this.buttonRemove = new PMPGuiButton(
            5,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("gui.button.remove"));
        this.buttonList.add(this.buttonRemove);
        xPos -= 75;
        yPos += 24;
        this.buttonDefault = new PMPGuiButton(
            6,
            xPos,
            yPos,
            150,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("gui.button.defaultList"));
        this.buttonList.add(this.buttonDefault);
        xPos = this.width / 2 + 182 - 180;
        yPos = 84;
        this.sliderGenRate = new PMPGuiSlider(this, 7, xPos, yPos, 180, 20, "sliderGenRate", 1, 50, 100, 1);
        this.buttonList.add(this.sliderGenRate);
        this.sliderGenRate.setSliderMode(1);
        yPos = yPos + 24;
        this.buttonOverwriteVanilla = new PMPGuiButton(
            8,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("gui.button.overwriteVanilla"));
        this.buttonList.add(this.buttonOverwriteVanilla);
        this.buttonOverwriteVanilla.setButtonMode(1);
        xPos += 92;
        this.sliderElevationVariance = new PMPGuiSlider(
            this,
            9,
            xPos,
            yPos,
            88,
            20,
            "gui.slider.elevationVariance",
            0,
            0,
            16,
            1);
        this.buttonList.add(this.sliderElevationVariance);
        xPos = this.width / 2 + 182 - 180;
        yPos += 24;
        this.sliderClusterAmount = new PMPGuiSlider(
            this,
            10,
            xPos,
            yPos,
            180,
            20,
            "gui.slider.clusterAmount",
            1,
            1,
            64,
            1);
        this.buttonList.add(this.sliderClusterAmount);
        yPos += 24;
        this.sliderClusterSize = new PMPGuiSlider(this, 11, xPos, yPos, 180, 20, "gui.slider.clusterSize", 1, 1, 8, 1);
        this.buttonList.add(this.sliderClusterSize);
        this.loadPlantNames();
        this.plantList.setTextArray(this.arrayPlantNames);
        this.listItemSelected(this.plantList, 0);
    }

    private void updateControls() {
        if (this.arrayPlantNames.size() > 0) {
            PMPDataPlantSpawnParams params = this.decorator
                .getPlantFromListByName(this.decoratorArea, this.plantList.getSelectedItemText());
            if (params != null) {
                this.sliderGenRate.setIntValue(params.generationWeight);
                this.sliderGenRate.updateDisplayString();
                this.sliderGenRate.enabled = true;
                this.buttonOverwriteVanilla.setButtonState(params.overwriteVanilla);
                this.buttonOverwriteVanilla.enabled = this.decoratorArea != PMPDecoratorArea.EPIPHYTE;
                if (this.decoratorArea != PMPDecoratorArea.EPIPHYTE && this.decoratorArea != PMPDecoratorArea.VINE
                    && this.decoratorArea != PMPDecoratorArea.WATER_FLOATING
                    && this.decoratorArea != PMPDecoratorArea.WATER_IMMERSED) {
                    this.sliderElevationVariance.setIntValue(params.elevationVariance);
                    this.sliderElevationVariance.updateDisplayString();
                    this.sliderElevationVariance.enabled = true;
                } else {
                    this.sliderElevationVariance.setIntValue(0);
                    this.sliderElevationVariance.updateDisplayString();
                    this.sliderElevationVariance.enabled = false;
                }

                this.buttonRemove.enabled = true;
                this.sliderClusterAmount.setIntValue(params.clusterAmount);
                this.sliderClusterAmount.updateDisplayString();
                this.sliderClusterAmount.enabled = this.decoratorArea != PMPDecoratorArea.EPIPHYTE;
                this.sliderClusterSize.setIntValue(params.clusterSize);
                this.sliderClusterSize.updateDisplayString();
                this.sliderClusterSize.enabled = this.decoratorArea != PMPDecoratorArea.EPIPHYTE;
            }
        } else {
            this.sliderGenRate.setIntValue(50);
            this.sliderGenRate.updateDisplayString();
            this.sliderGenRate.enabled = false;
            this.buttonOverwriteVanilla.setButtonState(false);
            this.buttonOverwriteVanilla.enabled = false;
            this.buttonOverwriteVanilla.setButtonState(false);
            this.sliderElevationVariance.setIntValue(0);
            this.sliderElevationVariance.enabled = false;
            this.buttonRemove.enabled = false;
            this.sliderClusterAmount.setIntValue(1);
            this.sliderClusterAmount.updateDisplayString();
            this.sliderClusterAmount.enabled = false;
            this.sliderClusterSize.setIntValue(1);
            this.sliderClusterSize.updateDisplayString();
            this.sliderClusterSize.enabled = false;
        }

        if (this.arrayPlantNames.size() == 0) {
            this.plantIcon = null;
        }

    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            PMPDataPlantSpawnParams params = this.decorator
                .getPlantFromListByName(this.decoratorArea, this.plantList.getSelectedItemText());
            switch (button.id) {
                case 2:
                    plantListState.resetState();
                    this.decorator.saveUserConfig();
                case 3:
                case 7:
                default:
                    break;
                case 4:
                    plantListState.resetState();
                    this.mc.displayGuiScreen(new PMPGuiConfigAddPlant(this, this.decorator, this.decoratorArea));
                    return;
                case 5:
                    if (this.plantList.getSize() > 0) {
                        this.decorator.removePlantFromList(this.decoratorArea, params);
                        this.loadPlantNames();
                        plantListState.resetState();
                        this.plantList.resetSelectionScroll();
                        this.listItemSelected(this.plantList, 0);
                    }

                    return;
                case 6:
                    this.decorator.resetDecoratorArea(this.decoratorArea);
                    this.decorator.saveUserConfig();
                    this.loadPlantNames();
                    plantListState.resetState();
                    this.plantList.resetSelectionScroll();
                    this.listItemSelected(this.plantList, 0);
                    return;
                case 8:
                    params.overwriteVanilla = !params.overwriteVanilla;
                    this.updateControls();
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
        this.plantList.drawScreen(par1, par2, par3);
        this.drawIconTexture(this.plantIcon, this.width / 2 + 167 - 150, 36, true, false);
        super.drawScreen(par1, par2, par3);
    }

    public void listItemSelected(PMPGuiStringList list, int index) {
        if (this.arrayPlantNames.size() > 0) {
            PMPBlockPlant plantBlock = PlantMegaPack.blocks
                .getPlantBlock((String) this.arrayPlantNames.get(this.plantList.getSelectedItemIndex()));
            if (plantBlock != null) {
                this.plantIcon = plantBlock.getIcon(0, plantBlock.getInventoryIconIndex());
            }
        } else {
            this.plantIcon = null;
        }

        this.updateControls();
    }

    public void listItemActivate(PMPGuiStringList list) {}

    public void updateIntSetting(String name, int value) {
        PMPDataPlantSpawnParams params = this.decorator
            .getPlantFromListByName(this.decoratorArea, this.plantList.getSelectedItemText());
        if (params != null) {
            if (name.matches("sliderGenRate")) {
                params.generationWeight = this.sliderGenRate.getIntValue();
            } else if (name.matches("gui.slider.clusterAmount")) {
                params.clusterAmount = this.sliderClusterAmount.getIntValue();
            } else if (name.matches("gui.slider.clusterSize")) {
                params.clusterSize = this.sliderClusterSize.getIntValue();
            } else if (name.matches("gui.slider.elevationVariance")) {
                params.elevationVariance = this.sliderElevationVariance.getIntValue();
            }
        }

    }

    private void loadPlantNames() {
        ArrayList<PMPDataPlantSpawnParams> list = this.decorator.getPlantList(this.decoratorArea);
        this.arrayPlantNames.clear();
        if (list != null && list.size() > 0) {
            for (int index = 0; index < list.size(); ++index) {
                this.arrayPlantNames.add(((PMPDataPlantSpawnParams) list.get(index)).plantName);
            }

            this.plantList.setTextArray(this.arrayPlantNames);
            this.plantList.resetSelectionScroll();
        }

    }
}
