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
import plantmegapack.common.PMPPlant;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigPlants extends PMPGuiConfigBase {

    private static final int LIST_HEIGHT = 140;
    private static final int LIST_WIDTH = 150;
    private PMPGuiStringList plantList;
    private PMPGuiButton buttonCustomize;
    private ArrayList<String> arrayPlantNames = new ArrayList();
    private IIcon plantIcon;
    private static PMPGuiStringListState plantListState = new PMPGuiStringListState();

    public PMPGuiConfigPlants(GuiScreen parent) {
        super(parent, "gui.screen.configPlants", 0);
        this.loadPlantNames();
    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        int xPos = this.width / 2 - 152;
        int yPos = 36;
        this.plantList = new PMPGuiStringList(
            this,
            150,
            140,
            yPos,
            yPos + 140,
            xPos,
            this.getFontRenderer().FONT_HEIGHT + 2);
        this.plantList.setTextArray(this.arrayPlantNames);
        this.plantList.useListState(plantListState);
        this.listItemSelected((PMPGuiStringList) null, plantListState.selectedIndex);
        xPos = this.width / 2 + 2;
        yPos = 156;
        this.buttonCustomize = new PMPGuiButton(
            3,
            xPos,
            yPos,
            150,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("gui.button.customize") + "...");
        this.buttonList.add(this.buttonCustomize);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 2:
                    plantListState.resetState();
                    break;
                case 3:
                    this.plantList.saveListState(plantListState);
                    this.mc.displayGuiScreen(
                        new PMPGuiConfigPlantAttributes(
                            this,
                            (String) this.arrayPlantNames.get(this.plantList.getSelectedItemIndex())));
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
        super.drawScreen(par1, par2, par3);
        this.drawIconTexture(this.plantIcon, this.width / 2 + 2, 36, true, false);
        this.drawPlantInfo();
    }

    public void listItemSelected(PMPGuiStringList list, int index) {
        PMPBlockPlant plantBlock = PlantMegaPack.blocks
            .getPlantBlock((String) this.arrayPlantNames.get(this.plantList.getSelectedItemIndex()));
        if (plantBlock != null) {
            this.plantIcon = plantBlock.getIcon(0, plantBlock.getInventoryIconIndex());
        } else {
            this.plantIcon = null;
        }

    }

    public void listItemActivate(PMPGuiStringList list) {}

    public void updateIntSetting(String name, int value) {}

    private void drawPlantInfo() {
        int xPos = this.width / 2 + 2 + 40;
        int yPos = 40;
        int labelWidth = 86;
        PMPBlockPlant plantBlock = PlantMegaPack.blocks
            .getPlantBlock((String) this.arrayPlantNames.get(this.plantList.getSelectedItemIndex()));
        String text = plantBlock.getLocalizedName();
        this.drawString(this.getFontRenderer(), text, xPos, yPos, 9502624);
        text = plantBlock.getLatinName();
        yPos += this.getFontRenderer().FONT_HEIGHT + 2;
        this.drawString(this.getFontRenderer(), text, xPos, yPos, 8437888);
        xPos -= 40;
        yPos += this.getFontRenderer().FONT_HEIGHT * 2 + 4;
        text = LanguageRegistry.instance()
            .getStringLocalization("gui.label.category") + ": ";
        this.drawString(this.getFontRenderer(), text, xPos, yPos, 6316128);
        text = plantBlock.plantData.attributes.category.getLocalizedName();
        this.drawString(this.getFontRenderer(), text, xPos + labelWidth, yPos, 8437888);
        yPos += this.getFontRenderer().FONT_HEIGHT + 2;
        text = LanguageRegistry.instance()
            .getStringLocalization("gui.label.soilType") + ": ";
        this.drawString(this.getFontRenderer(), text, xPos, yPos, 6316128);
        text = plantBlock.plantData.attributes.soilType.getLocalizedName();
        this.drawString(this.getFontRenderer(), text, xPos + labelWidth, yPos, 8437888);
        yPos += this.getFontRenderer().FONT_HEIGHT + 2;
        text = LanguageRegistry.instance()
            .getStringLocalization("gui.label.growthType") + ": ";
        this.drawString(this.getFontRenderer(), text, xPos, yPos, 6316128);
        text = plantBlock.plantData.attributes.growthType.getLocalizedName();
        this.drawString(this.getFontRenderer(), text, xPos + labelWidth, yPos, 8437888);
        yPos += this.getFontRenderer().FONT_HEIGHT + 2;
        text = LanguageRegistry.instance()
            .getStringLocalization("gui.label.growthStages") + ": ";
        this.drawString(this.getFontRenderer(), text, xPos, yPos, 6316128);
        text = String.format("%d", plantBlock.plantData.attributes.growthStages);
        this.drawString(this.getFontRenderer(), text, xPos + labelWidth, yPos, 8437888);
        yPos += this.getFontRenderer().FONT_HEIGHT + 2;
        text = LanguageRegistry.instance()
            .getStringLocalization("gui.label.renderType") + ": ";
        this.drawString(this.getFontRenderer(), text, xPos, yPos, 6316128);
        text = plantBlock.plantData.attributes.renderType.getLocalizedName();
        this.drawString(this.getFontRenderer(), text, xPos + labelWidth, yPos, 8437888);
        yPos += this.getFontRenderer().FONT_HEIGHT + 2;
        text = LanguageRegistry.instance()
            .getStringLocalization("gui.label.renderSubType") + ": ";
        this.drawString(this.getFontRenderer(), text, xPos, yPos, 6316128);
        text = String.format("%d", plantBlock.plantData.attributes.renderSubtype);
        this.drawString(this.getFontRenderer(), text, xPos + labelWidth, yPos, 8437888);
        yPos += this.getFontRenderer().FONT_HEIGHT + 2;
        text = LanguageRegistry.instance()
            .getStringLocalization("gui.label.breakSound") + ": ";
        this.drawString(this.getFontRenderer(), text, xPos, yPos, 6316128);
        text = plantBlock.plantData.attributes.breakSound.getLocalizedName();
        this.drawString(this.getFontRenderer(), text, xPos + labelWidth, yPos, 8437888);
    }

    private void loadPlantNames() {
        PMPPlant[] var1 = PMPPlant.values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            PMPPlant plants = var1[var3];
            this.arrayPlantNames.add(plants.ID);
        }

    }
}
