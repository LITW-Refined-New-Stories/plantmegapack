package plantmegapack.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PMPGuiConfigItems extends PMPGuiConfigBase {

    public PMPGuiConfigItems(GuiScreen parent) {
        super(parent, "gui.screen.configItems", 0);
    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        int xPos = this.width / 2 - 90;
        int yPos = 36;
        this.buttonList.add(
            new PMPGuiButton(
                3,
                xPos,
                yPos,
                180,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("gui.screen.configItemsSalves") + "..."));
        yPos += 24;
        this.buttonList.add(
            new PMPGuiButton(
                4,
                xPos,
                yPos,
                180,
                20,
                LanguageRegistry.instance()
                    .getStringLocalization("gui.screen.configItemsPowders") + "..."));
        this.updateButtonLabels();
    }

    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            switch (button.id) {
                case 1:
                case 2:
                default:
                    break;
                case 3:
                    this.mc.displayGuiScreen(new PMPGuiConfigItemsSalves(this));
                    return;
                case 4:
                    this.mc.displayGuiScreen(new PMPGuiConfigItemsPowders(this));
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

    public void updateIntSetting(String name, int value) {}

    private void updateButtonLabels() {}
}
