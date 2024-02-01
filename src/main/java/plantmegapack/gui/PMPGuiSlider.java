package plantmegapack.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PMPGuiSlider extends PMPGuiButton {

    private PMPGuiConfigBase parent;
    private String name;
    private float sliderValue;
    public boolean mouseButtonDown;
    private final float valueMin;
    private final float valueMax;
    private final float valueStep;
    private int sliderMode;

    public PMPGuiSlider(PMPGuiConfigBase parent, int id, int xPosition, int yPosition, int width, int height,
        String name, int valueMin, int valueInitial, int valueMax, int valueStep) {
        super(id, xPosition, yPosition, width, height, name);
        this.parent = parent;
        this.name = name;
        this.sliderMode = 0;
        this.valueStep = (float) valueStep;
        this.valueMin = (float) valueMin;
        this.valueMax = (float) valueMax;
        this.sliderValue = this.normalizeValue((float) valueInitial);
        this.updateDisplayString();
    }

    public int getHoverState(boolean state) {
        return 0;
    }

    protected void mouseDragged(Minecraft mc, int xPos, int yPos) {
        if (this.visible) {
            if (this.mouseButtonDown) {
                this.sliderValue = (float) (xPos - (this.xPosition + 4)) / (float) (this.width - 8);
                if (this.sliderValue < 0.0F) {
                    this.sliderValue = 0.0F;
                }

                if (this.sliderValue > 1.0F) {
                    this.sliderValue = 1.0F;
                }

                this.parent.updateIntSetting(this.name, this.getIntValue());
                this.sliderValue = this.normalizeValue((float) this.getIntValue());
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

    public boolean mousePressed(Minecraft mc, int xPos, int yPos) {
        if (super.mousePressed(mc, xPos, yPos)) {
            this.sliderValue = (float) (xPos - (this.xPosition + 4)) / (float) (this.width - 8);
            if (this.sliderValue < 0.0F) {
                this.sliderValue = 0.0F;
            }

            if (this.sliderValue > 1.0F) {
                this.sliderValue = 1.0F;
            }

            this.parent.updateIntSetting(this.name, this.getIntValue());
            this.updateDisplayString();
            this.mouseButtonDown = true;
            return true;
        } else {
            return false;
        }
    }

    public void mouseReleased(int xPos, int yPos) {
        this.parent.updateIntSetting(this.name, this.getIntValue());
        this.mouseButtonDown = false;
    }

    public int getIntValue() {
        return (int) this.denormalizeValue(this.sliderValue);
    }

    public void setIntValue(int value) {
        if (!((float) value < this.valueMin) && !((float) value > this.valueMax)) {
            this.sliderValue = this.normalizeValue((float) value);
        }
    }

    public void updateDisplayString() {
        String label = "";
        if (this.sliderMode == 0) {
            label = LanguageRegistry.instance()
                .getStringLocalization(this.name) + ": ";
            if (this.valueStep > 0.0F) {
                if (this.getIntValue() > 0) {
                    label = label + String.valueOf(this.getIntValue());
                    if (this.valueMax == 100.0F) {
                        label = label + "%";
                    }
                } else {
                    label = label + I18n.format("options.off", new Object[0]);
                }
            } else {
                label = label + String.valueOf(this.getIntValue());
            }
        }

        if (this.sliderMode == 1) {
            label = String.format(
                "%s: %d",
                LanguageRegistry.instance()
                    .getStringLocalization("gui.label.plantSpawnWeight"),
                this.getIntValue());
        }

        this.displayString = label;
    }

    private float normalizeValue(float value) {
        return MathHelper
            .clamp_float((this.snapToStepClamp(value) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
    }

    private float denormalizeValue(float value) {
        return this.snapToStepClamp(
            this.valueMin + (this.valueMax - this.valueMin) * MathHelper.clamp_float(value, 0.0F, 1.0F));
    }

    private float snapToStepClamp(float value) {
        value = this.snapToStep(value);
        return MathHelper.clamp_float(value, this.valueMin, this.valueMax);
    }

    private float snapToStep(float value) {
        if (this.valueStep > 0.0F) {
            value = this.valueStep * (float) Math.round(value / this.valueStep);
        }

        return value;
    }

    public void setSliderMode(int mode) {
        if (mode >= 0 && mode <= 1) {
            this.sliderMode = mode;
        }
    }
}
