package plantmegapack.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;

import org.lwjgl.opengl.GL11;

public class PMPGuiButton extends GuiButton {

    private boolean selected = false;
    private int buttonMode = 0;
    private boolean buttonState = false;
    private int textColorMode = 0;

    public PMPGuiButton(int id, int xPosition, int yPosition, int width, int height, String label) {
        super(id, xPosition, yPosition, label);
        this.width = width;
        this.height = height;
    }

    public void drawButton(Minecraft mc, int xPos, int yPos) {
        if (this.visible) {
            int buttonStartXPos = this.xPosition;
            int buttonWidth = this.width;
            if (this.buttonMode == 2 || this.buttonMode == 3) {
                this.drawLED(mc, this.xPosition, this.yPosition);
                buttonStartXPos += 6;
                buttonWidth -= 6;
            }

            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager()
                .bindTexture(buttonTextures);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_146123_n = xPos >= buttonStartXPos && yPos >= this.yPosition
                && xPos < buttonStartXPos + buttonWidth
                && yPos < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            GL11.glEnable(3042);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(770, 771);
            if (this.enabled) {
                if (this.selected) {
                    k = 1;
                    GL11.glColor4f(0.5F, 1.0F, 0.5F, 1.0F);
                } else {
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                }
            } else {
                GL11.glColor4f(0.5F, 0.55F, 0.5F, 1.0F);
            }

            this.drawTexturedModalRect(buttonStartXPos, this.yPosition, 0, 46 + k * 20, buttonWidth / 2, this.height);
            this.drawTexturedModalRect(
                buttonStartXPos + buttonWidth / 2,
                this.yPosition,
                200 - buttonWidth / 2,
                46 + k * 20,
                buttonWidth / 2,
                this.height);
            this.mouseDragged(mc, xPos, yPos);
            int textColor;
            switch (this.textColorMode) {
                case 0:
                default:
                    textColor = 13696976;
                    break;
                case 1:
                    textColor = 15761536;
            }

            if (this.selected) {
                switch (this.textColorMode) {
                    case 0:
                    default:
                        textColor = 9502624;
                        break;
                    case 1:
                        textColor = 8405056;
                }
            } else if (!this.enabled) {
                switch (this.textColorMode) {
                    case 0:
                    default:
                        textColor = 5267536;
                        break;
                    case 1:
                        textColor = 4194304;
                }
            } else if (this.field_146123_n) {
                switch (this.textColorMode) {
                    case 0:
                    default:
                        textColor = 16777088;
                        break;
                    case 1:
                        textColor = 16777088;
                }
            }

            this.drawCenteredString(
                fontrenderer,
                this.getDisplayString(),
                buttonStartXPos + buttonWidth / 2,
                this.yPosition + (this.height - 8) / 2,
                textColor);
        }

    }

    public boolean mousePressed(Minecraft mc, int xPos, int yPos) {
        if (this.selected) {
            return false;
        } else {
            return (this.buttonMode == 2 || this.buttonMode == 3) && xPos < this.xPosition + 6 ? false
                : super.mousePressed(mc, xPos, yPos);
        }
    }

    private String getDisplayString() {
        return this.buttonMode > 0
            ? this.displayString + ": "
                + (this.buttonState ? I18n.format("options.on", new Object[0])
                    : I18n.format("options.off", new Object[0]))
            : this.displayString;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setButtonMode(int mode) {
        if (mode >= 0 && mode <= 3) {
            this.buttonMode = mode;
        }
    }

    public void setButtonState(boolean state) {
        this.buttonState = state;
    }

    public void setTextColorMode(int mode) {
        if (mode >= 0 && mode <= 1) {
            this.textColorMode = mode;
        }
    }

    public void drawLED(Minecraft mc, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (!this.enabled) {
            switch (this.buttonMode) {
                case 2:
                    mc.getTextureManager()
                        .bindTexture(PMPGuiConfigBase.ledMIX0);
                    break;
                case 3:
                    mc.getTextureManager()
                        .bindTexture(PMPGuiConfigBase.ledGRNOff);
            }
        } else {
            mc.getTextureManager()
                .bindTexture(this.buttonState ? PMPGuiConfigBase.ledMIX1 : PMPGuiConfigBase.ledMIX2);
            switch (this.buttonMode) {
                case 2:
                    mc.getTextureManager()
                        .bindTexture(this.buttonState ? PMPGuiConfigBase.ledMIX1 : PMPGuiConfigBase.ledMIX2);
                    break;
                case 3:
                    mc.getTextureManager()
                        .bindTexture(this.buttonState ? PMPGuiConfigBase.ledGRNOn : PMPGuiConfigBase.ledGRNOff);
            }
        }

        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        tess.addVertexWithUV((double) x, (double) (y + 20), (double) this.zLevel, 0.0, 1.0);
        tess.addVertexWithUV((double) (x + 6), (double) (y + 20), (double) this.zLevel, 1.0, 1.0);
        tess.addVertexWithUV((double) (x + 6), (double) y, (double) this.zLevel, 1.0, 0.0);
        tess.addVertexWithUV((double) x, (double) y, (double) this.zLevel, 0.0, 0.0);
        tess.draw();
    }
}
