package plantmegapack.gui;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.bin.PMPSettings;

@SideOnly(Side.CLIENT)
public class PMPGuiOptionSlider extends PMPGuiButton {

    private float sliderValue;
    public boolean mousePressed;
    private PMPSettings.PMPOptions option;

    public PMPGuiOptionSlider(int ID, int posX, int posY, PMPSettings.PMPOptions option) {
        this(ID, posX, posY, option, 0.0F, 1.0F);
    }

    public PMPGuiOptionSlider(int ID, int xPos, int yPos, PMPSettings.PMPOptions option, float par5, float par6) {
        super(ID, xPos, yPos, 150, 20, "");
        this.sliderValue = 1.0F;
        this.option = option;
        this.sliderValue = option.normalizeValue(PlantMegaPack.settingsGeneral.getOptionFloatValue(option));
        this.updateDisplayString();
    }

    public int getHoverState(boolean state) {
        return 0;
    }

    protected void mouseDragged(Minecraft mc, int posX, int posY) {
        if (this.visible) {
            if (this.mousePressed) {
                this.sliderValue = (float) (posX - (this.xPosition + 4)) / (float) (this.width - 8);
                if (this.sliderValue < 0.0F) {
                    this.sliderValue = 0.0F;
                }

                if (this.sliderValue > 1.0F) {
                    this.sliderValue = 1.0F;
                }

                float f = this.option.denormalizeValue(this.sliderValue);
                PlantMegaPack.settingsGeneral.setOptionIntValue(this.option, (int) f);
                this.sliderValue = this.option.normalizeValue(f);
                this.updateDisplayString();
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(
                this.xPosition + (int) (this.sliderValue * (float) (this.width - 8)),
                this.yPosition,
                0,
                66,
                4,
                20);
            this.drawTexturedModalRect(
                this.xPosition + (int) (this.sliderValue * (float) (this.width - 8)) + 4,
                this.yPosition,
                196,
                66,
                4,
                20);
        }

    }

    public boolean mousePressed(Minecraft mc, int posX, int posY) {
        if (super.mousePressed(mc, posX, posY)) {
            this.sliderValue = (float) (posX - (this.xPosition + 4)) / (float) (this.width - 8);
            if (this.sliderValue < 0.0F) {
                this.sliderValue = 0.0F;
            }

            if (this.sliderValue > 1.0F) {
                this.sliderValue = 1.0F;
            }

            PlantMegaPack.settingsGeneral
                .setOptionIntValue(this.option, (int) this.option.denormalizeValue(this.sliderValue));
            this.updateDisplayString();
            this.mousePressed = true;
            return true;
        } else {
            return false;
        }
    }

    public void mouseReleased(int posX, int posY) {
        this.mousePressed = false;
    }

    private void updateDisplayString() {
        this.displayString = String.format(
            "%s%d",
            PlantMegaPack.settingsGeneral.getKeyBinding(this.option),
            (int) this.option.denormalizeValue(this.sliderValue)) + "%";
    }
}
