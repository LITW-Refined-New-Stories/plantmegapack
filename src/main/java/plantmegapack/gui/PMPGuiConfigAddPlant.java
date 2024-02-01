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
import plantmegapack.common.PMPDecorator;
import plantmegapack.common.PMPDecoratorArea;
import plantmegapack.common.PMPPlantCategory;
import plantmegapack.data.PMPDataDecorator;
import plantmegapack.data.PMPDataPlantSpawnParams;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigAddPlant extends PMPGuiConfigBase {

    private static final int LIST_HEIGHT = 140;
    private static final int LIST_WIDTH = 180;
    private PMPDataDecorator decorator;
    private PMPDecoratorArea decoratorArea;
    private PMPGuiStringList list;
    private ArrayList<String> plantNames = new ArrayList();
    private IIcon plantIcon;

    public PMPGuiConfigAddPlant(GuiScreen parent, PMPDataDecorator decorator, PMPDecoratorArea decoratorArea) {
        super(parent, "gui.screen.configAddPlant", 1);
        this.decorator = decorator;
        this.decoratorArea = decoratorArea;
        if (this.decorator != null && this.decoratorArea != null) {
            this.name = this.name + " - "
                + this.decorator.decorator.getLocalizedName()
                + " - "
                + this.decoratorArea.getLocalizedName();
        }

        this.updatePlantList();
    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        ((PMPGuiButton) this.buttonList.get(0)).displayString = LanguageRegistry.instance()
            .getStringLocalization("gui.button.cancel");
        int xPos = this.width / 2 - 116;
        int yPos = 36;
        this.list = new PMPGuiStringList(
            this,
            180,
            140,
            yPos,
            yPos + 140,
            xPos,
            this.getFontRenderer().FONT_HEIGHT + 2);
        this.list.setTextArray(this.plantNames);
        this.listItemSelected(this.list, 0);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 1:
                    this.mc.displayGuiScreen(this.parentGuiScreen);
                    return;
                case 2:
                    PMPDataPlantSpawnParams params = new PMPDataPlantSpawnParams(
                        this.list.getItemText(this.list.getSelectedItemIndex()));
                    this.decorator.addPlantToList(this.decoratorArea, params);
                    this.mc.displayGuiScreen(this.parentGuiScreen);
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
        this.list.drawScreen(par1, par2, par3);
        this.drawIconTexture(this.plantIcon, this.width / 2 + 116 - 48, 36, true, false);
        super.drawScreen(par1, par2, par3);
    }

    public void listItemSelected(PMPGuiStringList list, int index) {
        PMPBlockPlant plantBlock = PlantMegaPack.blocks.getPlantBlock(this.list.getSelectedItemText());
        if (plantBlock != null) {
            this.plantIcon = plantBlock.getIcon(0, plantBlock.getInventoryIconIndex());
        } else {
            this.plantIcon = null;
        }

    }

    public void updateIntSetting(String name, int value) {}

    private void updatePlantList() {
        switch (this.decoratorArea) {
            case EPIPHYTE:
                PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.EPIPHYTE, this.plantNames);
                break;
            case PRIMARY:
            case SHADE:
            case WATER_EDGE:
                if (this.decorator.decorator != PMPDecorator.DESERT
                    && this.decorator.decorator != PMPDecorator.DESERT_EDGE
                    && this.decorator.decorator != PMPDecorator.DESERT_HILLS
                    && this.decorator.decorator != PMPDecorator.DESERT_HILLS_EDGE
                    && this.decorator.decorator != PMPDecorator.HL_DESERTISLAND
                    && this.decorator.decorator != PMPDecorator.HL_DESERTISLAND_EDGE
                    && this.decorator.decorator != PMPDecorator.HL_DESERTMOUNTAINS
                    && this.decorator.decorator != PMPDecorator.HL_DESERTMOUNTAINS_EDGE
                    && this.decorator.decorator != PMPDecorator.HL_DUNES
                    && this.decorator.decorator != PMPDecorator.HL_DUNES_EDGE) {
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.BAMBOO, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.BEACH, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.BERRYBUSH, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.FERN, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.FLOWER_MULTI, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.FLOWER_SINGLE, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.FOREST, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.FUNGUS, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.GRASS, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.GROUNDCOVER, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.JUNGLE, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.MOUNTAIN, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.PLAINS, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.SAVANNA, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.SHRUB, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.WETLANDS, this.plantNames);
                } else {
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.BEACH, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.CACTUS, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.DESERT, this.plantNames);
                    PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.GROUNDCOVER, this.plantNames);
                }
                break;
            case WATER_FLOATING:
                PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.FLOATING, this.plantNames);
                break;
            case WATER_IMMERSED:
                PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.CROP_AQUATIC, this.plantNames);
                PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.IMMERSED, this.plantNames);
                break;
            case WATER_SUBMERGED:
                PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.CORAL, this.plantNames);
                PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.FRESHWATER, this.plantNames);
                PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.SALTWATER, this.plantNames);
                break;
            case VINE:
                PlantMegaPack.blocks.addPlantNamesToArray(PMPPlantCategory.VINE, this.plantNames);
        }

    }

    public void listItemActivate(PMPGuiStringList list) {}
}
