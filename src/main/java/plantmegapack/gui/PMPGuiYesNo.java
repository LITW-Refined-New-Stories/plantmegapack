package plantmegapack.gui;

import java.util.Iterator;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PMPGuiYesNo extends PMPGuiConfigBase {

    protected String textTitle;
    private String textMessage;
    protected String confirmButtonText;
    protected String cancelButtonText;
    protected int parentButtonID;
    private int field_146353_s;

    public PMPGuiYesNo(GuiScreen parent, String screenTitle, String textTitle, String textMessage, int ID) {
        super(parent, screenTitle, -1);
        this.textTitle = textTitle;
        this.textMessage = textMessage;
        this.parentButtonID = ID;
        this.confirmButtonText = I18n.format("gui.yes", new Object[0]);
        this.cancelButtonText = I18n.format("gui.no", new Object[0]);
    }

    public PMPGuiYesNo(GuiScreen parent, String screenTitle, String textTitle, String textMessage, String textYesButton,
        String textNoButton, int ID) {
        super(parent, screenTitle, -1);
        this.textTitle = textTitle;
        this.textMessage = textMessage;
        this.confirmButtonText = textYesButton;
        this.cancelButtonText = textNoButton;
        this.parentButtonID = ID;
    }

    public void initGui() {
        int xPos = this.width / 2 - 152;
        int yPos = 156;
        this.buttonList.add(new PMPGuiButton(0, xPos, yPos, 150, 20, this.confirmButtonText));
        xPos += 154;
        this.buttonList.add(new PMPGuiButton(1, xPos, yPos, 150, 20, this.cancelButtonText));
    }

    protected void actionPerformed(GuiButton p_146284_1_) {
        this.parentGuiScreen.confirmClicked(p_146284_1_.id == 0, this.parentButtonID);
    }

    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        int yPos = 60;
        this.drawCenteredString(this.fontRendererObj, this.textTitle, this.width / 2, yPos, 16777088);
        yPos += 24;
        this.drawCenteredString(this.fontRendererObj, this.textMessage, this.width / 2, yPos, 8437888);
        super.drawScreen(par1, par2, par3);
    }

    public void func_146350_a(int p_146350_1_) {
        this.field_146353_s = p_146350_1_;

        GuiButton guibutton;
        for (Iterator iterator = this.buttonList.iterator(); iterator.hasNext(); guibutton.enabled = false) {
            guibutton = (GuiButton) iterator.next();
        }

    }

    public void updateScreen() {
        super.updateScreen();
        GuiButton guibutton;
        if (--this.field_146353_s == 0) {
            for (Iterator iterator = this.buttonList.iterator(); iterator.hasNext(); guibutton.enabled = true) {
                guibutton = (GuiButton) iterator.next();
            }
        }

    }

    public void listItemSelected(PMPGuiStringList list, int index) {}

    public void listItemActivate(PMPGuiStringList list) {}

    public void updateIntSetting(String name, int value) {}
}
