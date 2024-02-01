package plantmegapack.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPDecorator;
import plantmegapack.common.PMPDecoratorType;
import plantmegapack.data.PMPDataDecorator;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigDecoratorCopy extends PMPGuiConfigBase {

    private static final int LIST_HEIGHT = 140;
    private static final int LIST_WIDTH = 242;
    private PMPGuiStringList list;
    private ArrayList<String> arrayDecoratorNames = new ArrayList();
    private ArrayList<String> arrayDecoratorIDs = new ArrayList();
    private PMPDataDecorator sourceDecorator;
    private PMPDataDecorator targetDecorator;

    public PMPGuiConfigDecoratorCopy(GuiScreen parent, PMPDataDecorator decorator) {
        super(parent, "gui.screen.configDecoratorCopy", 1);
        this.targetDecorator = decorator;
        this.loadDecoratorData();
    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        this.buttonDefault.displayString = LanguageRegistry.instance()
            .getStringLocalization("gui.button.cancel");
        int xPos = this.width / 2 - 121;
        int yPos = 36;
        this.list = new PMPGuiStringList(
            this,
            242,
            140,
            yPos,
            yPos + 140,
            xPos,
            this.getFontRenderer().FONT_HEIGHT + 2);
        this.list.setTextArray(this.arrayDecoratorNames);
        this.updateButtonLabels();
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 1:
                default:
                    break;
                case 2:
                    this.sourceDecorator = PlantMegaPack.worldGenerator
                        .getDecoratorByID((String) this.arrayDecoratorIDs.get(this.list.getSelectedItemIndex()));
                    this.targetDecorator.copyDecorator(this.sourceDecorator);
            }
        }

        this.mc.displayGuiScreen(this.parentGuiScreen);
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
        super.drawScreen(par1, par2, par3);
    }

    public void listItemSelected(PMPGuiStringList list, int index) {
        this.sourceDecorator = PlantMegaPack.worldGenerator
            .getDecoratorByID((String) this.arrayDecoratorIDs.get(index));
    }

    public void listItemActivate(PMPGuiStringList list) {}

    public void updateIntSetting(String name, int value) {}

    private void updateButtonLabels() {}

    private void loadDecoratorData() {
        this.arrayDecoratorNames.clear();
        this.arrayDecoratorIDs.clear();
        PMPDecorator[] var1 = PMPDecorator.values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            PMPDecorator decorators = var1[var3];
            if (decorators.decoratorType != PMPDecoratorType.FEATURES
                && decorators.decoratorType == this.targetDecorator.decorator.decoratorType
                && !decorators.ID.matches(this.targetDecorator.decorator.ID)) {
                this.arrayDecoratorNames.add(
                    decorators.decoratorSet.getLocalizedName() + " - "
                        + LanguageRegistry.instance()
                            .getStringLocalization(
                                "decorator.biome." + decorators.decoratorSet.ID + "." + decorators.ID));
                this.arrayDecoratorIDs.add(decorators.ID);
            }
        }

    }
}
