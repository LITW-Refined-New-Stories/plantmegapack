package plantmegapack.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.common.PMPDecoratorArea;
import plantmegapack.common.PMPDecoratorSet;
import plantmegapack.data.PMPDataDecorator;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigDecoratorCustomize extends PMPGuiConfigBase {

    private PMPDataDecorator decorator;
    private PMPDecoratorSet decoratorSet;
    private PMPGuiSlider sliderGenRate;
    private PMPGuiButton buttonPrimary;
    private PMPGuiButton buttonShade;
    private PMPGuiButton buttonEpiphytes;
    private PMPGuiButton buttonWaterEdge;
    private PMPGuiButton buttonWaterFloating;
    private PMPGuiButton buttonWaterImmersed;
    private PMPGuiButton buttonWaterSubmerged;
    private PMPGuiButton buttonTrees;
    private PMPGuiButton buttonVines;
    private PMPGuiButton buttonEmptyDecorator;
    private PMPGuiButton buttonResetDecorator;
    private PMPGuiButton buttonCopyDecorator;

    public PMPGuiConfigDecoratorCustomize(GuiScreen parent, PMPDataDecorator decorator, PMPDecoratorSet decoratorSet) {
        super(parent, "gui.screen.configDecoratorCustomize", 0);
        this.decorator = decorator;
        this.decoratorSet = decoratorSet;
        if (this.decorator != null) {
            this.name = this.name + ": "
                + this.decoratorSet.getLocalizedName()
                + " - "
                + this.decorator.decorator.getLocalizedName();
        }

    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        int xPos = this.width / 2 - 90;
        int yPos = 36;
        this.sliderGenRate = new PMPGuiSlider(
            this,
            3,
            xPos,
            yPos,
            180,
            20,
            "gui.slider.generationRate",
            0,
            this.decorator.generationRate,
            100,
            1);
        this.buttonList.add(this.sliderGenRate);
        xPos = this.width / 2 - 138;
        yPos = 84;
        this.buttonPrimary = new PMPGuiButton(
            4,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("decorator.area.primary") + "...");
        this.buttonList.add(this.buttonPrimary);
        yPos += 24;
        this.buttonShade = new PMPGuiButton(
            5,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("decorator.area.shade") + "...");
        this.buttonList.add(this.buttonShade);
        yPos += 24;
        this.buttonEpiphytes = new PMPGuiButton(
            6,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("decorator.area.epiphyte") + "...");
        this.buttonList.add(this.buttonEpiphytes);
        xPos = this.width / 2 - 44 - 4;
        yPos = 84;
        this.buttonTrees = new PMPGuiButton(
            8,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("decorator.area.tree") + "...");
        this.buttonList.add(this.buttonTrees);
        yPos = yPos + 24;
        this.buttonVines = new PMPGuiButton(
            9,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("decorator.area.vine") + "...");
        this.buttonList.add(this.buttonVines);
        yPos += 24;
        this.buttonWaterEdge = new PMPGuiButton(
            7,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("decorator.area.waterEdge") + "...");
        this.buttonList.add(this.buttonWaterEdge);
        xPos = this.width / 2 + 48 + 4;
        yPos = 84;
        this.buttonWaterFloating = new PMPGuiButton(
            14,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("decorator.area.waterFloating") + "...");
        this.buttonList.add(this.buttonWaterFloating);
        yPos = yPos + 24;
        this.buttonWaterImmersed = new PMPGuiButton(
            15,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("decorator.area.waterImmersed") + "...");
        this.buttonList.add(this.buttonWaterImmersed);
        yPos += 24;
        this.buttonWaterSubmerged = new PMPGuiButton(
            16,
            xPos,
            yPos,
            88,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("decorator.area.waterSubmerged") + "...");
        this.buttonList.add(this.buttonWaterSubmerged);
        int buttonWidth = 108;
        xPos = this.width / 2 - (buttonWidth * 3 + 8) / 2;
        yPos += 36;
        this.buttonEmptyDecorator = new PMPGuiButton(
            20,
            xPos,
            yPos,
            buttonWidth,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("gui.button.clear") + "...");
        this.buttonList.add(this.buttonEmptyDecorator);
        xPos += buttonWidth + 4;
        this.buttonResetDecorator = new PMPGuiButton(
            21,
            xPos,
            yPos,
            buttonWidth,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("gui.button.resetDefaults") + "...");
        this.buttonList.add(this.buttonResetDecorator);
        xPos += buttonWidth + 4;
        this.buttonCopyDecorator = new PMPGuiButton(
            22,
            xPos,
            yPos,
            buttonWidth,
            20,
            LanguageRegistry.instance()
                .getStringLocalization("gui.button.copyDecorator") + "...");
        this.buttonList.add(this.buttonCopyDecorator);
        this.updateButtonsForDecoratorType();
    }

    private void updateButtonsForDecoratorType() {
        switch (this.decorator.decorator.decoratorType) {
            case BEACH:
                this.buttonPrimary.enabled = true;
                this.buttonShade.enabled = true;
                this.buttonEpiphytes.enabled = true;
                this.buttonWaterEdge.enabled = true;
                this.buttonWaterFloating.enabled = true;
                this.buttonWaterImmersed.enabled = true;
                this.buttonWaterSubmerged.enabled = true;
                this.buttonTrees.enabled = true;
                this.buttonVines.enabled = true;
                break;
            case END:
                this.buttonPrimary.enabled = true;
                this.buttonShade.enabled = false;
                this.buttonEpiphytes.enabled = false;
                this.buttonWaterEdge.enabled = false;
                this.buttonWaterFloating.enabled = false;
                this.buttonWaterImmersed.enabled = false;
                this.buttonWaterSubmerged.enabled = false;
                this.buttonTrees.enabled = false;
                this.buttonVines.enabled = false;
                break;
            case LAND:
                this.buttonPrimary.enabled = true;
                this.buttonShade.enabled = true;
                this.buttonEpiphytes.enabled = true;
                this.buttonWaterEdge.enabled = true;
                this.buttonWaterFloating.enabled = true;
                this.buttonWaterImmersed.enabled = true;
                this.buttonWaterSubmerged.enabled = true;
                this.buttonTrees.enabled = true;
                this.buttonVines.enabled = true;
                break;
            case NETHER:
                this.buttonPrimary.enabled = true;
                this.buttonShade.enabled = false;
                this.buttonEpiphytes.enabled = false;
                this.buttonWaterEdge.enabled = false;
                this.buttonWaterFloating.enabled = false;
                this.buttonWaterImmersed.enabled = false;
                this.buttonWaterSubmerged.enabled = false;
                this.buttonTrees.enabled = false;
                this.buttonVines.enabled = false;
                break;
            case WATER:
                this.buttonPrimary.enabled = false;
                this.buttonShade.enabled = false;
                this.buttonEpiphytes.enabled = false;
                this.buttonWaterEdge.enabled = false;
                this.buttonWaterFloating.enabled = true;
                this.buttonWaterImmersed.enabled = true;
                this.buttonWaterSubmerged.enabled = true;
                this.buttonTrees.enabled = false;
                this.buttonVines.enabled = false;
        }

    }

    public void confirmClicked(boolean state, int ID) {
        if (ID == 20) {
            if (state) {
                this.decorator.clearAllDecoratorAreas();
                this.decorator.saveUserConfig();
            }

            this.mc.displayGuiScreen(this);
        }

        if (ID == 21) {
            if (state) {
                this.decorator.resetDecoratorAll();
                this.decorator.saveUserConfig();
            }

            this.mc.displayGuiScreen(this);
        }

        if (ID == 22 && state) {
            this.decorator.saveUserConfig();
        }

    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 2:
                    this.decorator.saveUserConfig();
                    ((PMPGuiConfigBase) this.parentGuiScreen).listItemSelected((PMPGuiStringList) null, 0);
                case 3:
                case 10:
                case 11:
                case 12:
                case 13:
                case 17:
                case 18:
                case 19:
                default:
                    break;
                case 4:
                    this.mc.displayGuiScreen(
                        new PMPGuiConfigDecoratorPlantList(this, this.decorator, PMPDecoratorArea.PRIMARY));
                    return;
                case 5:
                    this.mc.displayGuiScreen(
                        new PMPGuiConfigDecoratorPlantList(this, this.decorator, PMPDecoratorArea.SHADE));
                    return;
                case 6:
                    this.mc.displayGuiScreen(
                        new PMPGuiConfigDecoratorPlantList(this, this.decorator, PMPDecoratorArea.EPIPHYTE));
                    return;
                case 7:
                    this.mc.displayGuiScreen(
                        new PMPGuiConfigDecoratorPlantList(this, this.decorator, PMPDecoratorArea.WATER_EDGE));
                    return;
                case 8:
                    this.mc.displayGuiScreen(
                        new PMPGuiConfigDecoratorPlantList(this, this.decorator, PMPDecoratorArea.TREE));
                    return;
                case 9:
                    this.mc.displayGuiScreen(
                        new PMPGuiConfigDecoratorPlantList(this, this.decorator, PMPDecoratorArea.VINE));
                    return;
                case 14:
                    this.mc.displayGuiScreen(
                        new PMPGuiConfigDecoratorPlantList(this, this.decorator, PMPDecoratorArea.WATER_FLOATING));
                    return;
                case 15:
                    this.mc.displayGuiScreen(
                        new PMPGuiConfigDecoratorPlantList(this, this.decorator, PMPDecoratorArea.WATER_IMMERSED));
                    return;
                case 16:
                    this.mc.displayGuiScreen(
                        new PMPGuiConfigDecoratorPlantList(this, this.decorator, PMPDecoratorArea.WATER_SUBMERGED));
                    return;
                case 20:
                    this.mc.displayGuiScreen(
                        new PMPGuiYesNo(
                            this,
                            "gui.screen.yesno.emptyDecorator",
                            LanguageRegistry.instance()
                                .getStringLocalization("text.gui.warning"),
                            LanguageRegistry.instance()
                                .getStringLocalization("text.gui.warningEmptyDecorator1") + " "
                                + this.decoratorSet.getLocalizedName()
                                + " - "
                                + this.decorator.decorator.getLocalizedName()
                                + " "
                                + LanguageRegistry.instance()
                                    .getStringLocalization("text.gui.warningEmptyDecorator2"),
                            LanguageRegistry.instance()
                                .getStringLocalization("gui.button.clear"),
                            LanguageRegistry.instance()
                                .getStringLocalization("gui.button.cancel"),
                            20));
                    return;
                case 21:
                    this.mc.displayGuiScreen(
                        new PMPGuiYesNo(
                            this,
                            "gui.screen.yesno.resetDecoratorAreas",
                            LanguageRegistry.instance()
                                .getStringLocalization("text.gui.warning"),
                            LanguageRegistry.instance()
                                .getStringLocalization("text.gui.warningResetDecoratorArea"),
                            LanguageRegistry.instance()
                                .getStringLocalization("gui.button.reset"),
                            LanguageRegistry.instance()
                                .getStringLocalization("gui.button.cancel"),
                            21));
                    return;
                case 22:
                    this.mc.displayGuiScreen(new PMPGuiConfigDecoratorCopy(this, this.decorator));
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
        int xPos = this.width / 2 - 46 - 4;
        int yPos = 70;
        String text = LanguageRegistry.instance()
            .getStringLocalization("gui.label.plantsLand");
        this.drawCenteredString(this.getFontRenderer(), text, xPos, yPos, 5296160);
        xPos = this.width / 2 + 92;
        text = LanguageRegistry.instance()
            .getStringLocalization("gui.label.plantsWater");
        this.drawCenteredString(this.getFontRenderer(), text, xPos, yPos, 2121952);
    }

    public void listItemSelected(PMPGuiStringList list, int index) {}

    public void listItemActivate(PMPGuiStringList list) {}

    public void updateIntSetting(String name, int value) {
        if (name.matches("gui.slider.generationRate")) {
            this.decorator.generationRate = value;
        }

    }
}
