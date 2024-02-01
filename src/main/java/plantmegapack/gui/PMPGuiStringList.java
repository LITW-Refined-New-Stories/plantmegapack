package plantmegapack.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.GuiScrollingList;

public class PMPGuiStringList extends GuiScrollingList {

    private PMPGuiConfigBase parent;
    private final Minecraft client = Minecraft.getMinecraft();
    private int top;
    private int bottom;
    private int left;
    private int right;
    private float initialMouseClickY = -2.0F;
    private int scrollUpActionId;
    private int scrollDownActionId;
    private long lastClickTime = 0L;
    private boolean field_25123_p = true;
    private boolean field_27262_q;
    private int field_27261_r;
    private ArrayList<String> list = null;
    private PMPGuiStringListState listState;

    public PMPGuiStringList(PMPGuiConfigBase parent, int width, int height, int top, int bottom, int left,
        int slotHeight) {
        super(Minecraft.getMinecraft(), width, height, top, bottom, left, slotHeight);
        this.parent = parent;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = width + this.left;
        this.listState = new PMPGuiStringListState();
    }

    private void applyScrollLimits() {
        int var1 = this.getContentHeight() - (this.bottom - this.top - 4);
        if (var1 < 0) {
            var1 /= 2;
        }

        if (this.listState.scrollDistance < 0.0F) {
            this.listState.scrollDistance = 0.0F;
        }

        if (this.listState.scrollDistance > (float) var1) {
            this.listState.scrollDistance = (float) var1;
        }

    }

    public void func_27258_a(boolean p_27258_1_) {
        this.field_25123_p = p_27258_1_;
    }

    protected void func_27259_a(boolean p_27259_1_, int p_27259_2_) {
        this.field_27262_q = p_27259_1_;
        this.field_27261_r = p_27259_2_;
        if (!p_27259_1_) {
            this.field_27261_r = 0;
        }

    }

    public void registerScrollButtons(List p_22240_1_, int p_22240_2_, int p_22240_3_) {
        this.scrollUpActionId = p_22240_2_;
        this.scrollDownActionId = p_22240_3_;
    }

    public int func_27256_c(int p_27256_1_, int p_27256_2_) {
        int var3 = this.left + 1;
        int var4 = this.left + this.listWidth - 7;
        int var5 = p_27256_2_ - this.top - this.field_27261_r + (int) this.listState.scrollDistance - 4;
        int var6 = var5 / this.slotHeight;
        return p_27256_1_ >= var3 && p_27256_1_ <= var4 && var6 >= 0 && var5 >= 0 && var6 < this.getSize() ? var6 : -1;
    }

    protected int getContentHeight() {
        return this.getSize() * this.slotHeight + this.field_27261_r;
    }

    protected int getSize() {
        return this.list == null ? 0 : this.list.size();
    }

    protected void elementClicked(int index, boolean doubleClick) {
        if (index != this.listState.selectedIndex) {
            this.listState.selectedIndex = index;
            this.parent.listItemSelected(this, index);
        }

        if (doubleClick) {
            this.parent.listItemActivate(this);
        }

    }

    protected boolean isSelected(int index) {
        return index == this.listState.selectedIndex;
    }

    public void actionPerformed(GuiButton button) {
        if (button.enabled) {
            PMPGuiStringListState var10000;
            if (button.id == this.scrollUpActionId) {
                var10000 = this.listState;
                var10000.scrollDistance -= (float) (this.slotHeight * 2 / 3);
                this.initialMouseClickY = -2.0F;
                this.applyScrollLimits();
            } else if (button.id == this.scrollDownActionId) {
                var10000 = this.listState;
                var10000.scrollDistance += (float) (this.slotHeight * 2 / 3);
                this.initialMouseClickY = -2.0F;
                this.applyScrollLimits();
            }
        }

    }

    protected void drawBackground() {}

    protected void drawSlot(int index, int var2, int var3, int var4, Tessellator var5) {
        if (this.list != null) {
            if (index == this.listState.selectedIndex) {
                this.parent.getFontRenderer()
                    .drawStringWithShadow(
                        this.parent.getFontRenderer()
                            .trimStringToWidth((String) this.list.get(index), this.listWidth - 10),
                        this.left + 3,
                        var3 + 2,
                        16777088);
            } else {
                this.parent.getFontRenderer()
                    .drawString(
                        this.parent.getFontRenderer()
                            .trimStringToWidth((String) this.list.get(index), this.listWidth - 10),
                        this.left + 3,
                        var3 + 2,
                        8437888);
            }
        }

    }

    public void drawScreen(int mouseX, int mouseY, float p_22243_3_) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.drawBackground();
        int listLength = this.getSize();
        int scrollBarXStart = this.left + this.listWidth - 6;
        int scrollBarXEnd = scrollBarXStart + 6;
        int boxLeft = this.left;
        int boxRight = scrollBarXStart - 1;
        int var10;
        int var11;
        int var13;
        int var19;
        if (Mouse.isButtonDown(0)) {
            PMPGuiStringListState var10000;
            if (this.initialMouseClickY == -1.0F) {
                boolean var7 = true;
                if (mouseY >= this.top && mouseY <= this.bottom && mouseX >= this.left && mouseX <= this.right) {
                    var10 = mouseY - this.top - this.field_27261_r + (int) this.listState.scrollDistance - 4;
                    var11 = var10 / this.slotHeight;
                    if (mouseX >= boxLeft && mouseX <= boxRight && var11 >= 0 && var10 >= 0 && var11 < listLength) {
                        boolean var12 = var11 == this.listState.selectedIndex
                            && System.currentTimeMillis() - this.lastClickTime < 250L;
                        this.elementClicked(var11, var12);
                        this.lastClickTime = System.currentTimeMillis();
                    } else if (mouseX >= boxLeft && mouseX <= boxRight && var10 < 0) {
                        this.func_27255_a(
                            mouseX - boxLeft,
                            mouseY - this.top + (int) this.listState.scrollDistance - 4);
                        var7 = false;
                    }

                    if (mouseX >= scrollBarXStart && mouseX <= scrollBarXEnd) {
                        this.listState.scrollFactor = -1.0F;
                        var19 = this.getContentHeight() - (this.bottom - this.top - 2);
                        if (var19 < 1) {
                            var19 = 1;
                        }

                        var13 = (int) ((float) ((this.bottom - this.top) * (this.bottom - this.top))
                            / (float) this.getContentHeight());
                        if (var13 < 32) {
                            var13 = 32;
                        }

                        if (var13 > this.bottom - this.top - 8) {
                            var13 = this.bottom - this.top - 8;
                        }

                        var10000 = this.listState;
                        var10000.scrollFactor /= (float) (this.bottom - this.top - var13) / (float) var19;
                    } else {
                        this.listState.scrollFactor = 1.0F;
                    }

                    if (var7) {
                        this.initialMouseClickY = (float) mouseY;
                    } else {
                        this.initialMouseClickY = -2.0F;
                    }
                } else {
                    this.initialMouseClickY = -2.0F;
                }
            } else if (this.initialMouseClickY >= 0.0F) {
                var10000 = this.listState;
                var10000.scrollDistance -= ((float) mouseY - this.initialMouseClickY) * this.listState.scrollFactor;
                this.initialMouseClickY = (float) mouseY;
            }
        } else {
            this.initialMouseClickY = -1.0F;
        }

        this.applyScrollLimits();
        Tessellator tesselator = Tessellator.instance;
        if (this.client.theWorld != null) {
            this.drawGradientRect(this.left, this.top, this.right, this.bottom, -1072689136, -804253680);
        } else {
            GL11.glDisable(2896);
            GL11.glDisable(2912);
            this.client.renderEngine.bindTexture(Gui.optionsBackground);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            float var17 = 32.0F;
            tesselator.startDrawingQuads();
            tesselator.setColorOpaque_I(2105376);
            tesselator.addVertexWithUV(
                (double) this.left,
                (double) this.bottom,
                0.0,
                (double) ((float) this.left / var17),
                (double) ((float) (this.bottom + (int) this.listState.scrollDistance) / var17));
            tesselator.addVertexWithUV(
                (double) this.right,
                (double) this.bottom,
                0.0,
                (double) ((float) this.right / var17),
                (double) ((float) (this.bottom + (int) this.listState.scrollDistance) / var17));
            tesselator.addVertexWithUV(
                (double) this.right,
                (double) this.top,
                0.0,
                (double) ((float) this.right / var17),
                (double) ((float) (this.top + (int) this.listState.scrollDistance) / var17));
            tesselator.addVertexWithUV(
                (double) this.left,
                (double) this.top,
                0.0,
                (double) ((float) this.left / var17),
                (double) ((float) (this.top + (int) this.listState.scrollDistance) / var17));
            tesselator.draw();
        }

        var10 = this.top + 2 - (int) this.listState.scrollDistance;
        if (this.field_27262_q) {
            this.func_27260_a(boxRight, var10, tesselator);
        }

        for (var11 = 0; var11 < listLength; ++var11) {
            var19 = var10 + var11 * this.slotHeight + this.field_27261_r;
            var13 = this.slotHeight;
            if (var19 <= this.bottom && var19 + var13 >= this.top) {
                if (this.field_25123_p && this.isSelected(var11)) {
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    GL11.glDisable(3553);
                    tesselator.startDrawingQuads();
                    tesselator.setColorOpaque_I(8421504);
                    tesselator.addVertexWithUV((double) boxLeft, (double) (var19 + var13 + 1), 0.0, 0.0, 1.0);
                    tesselator.addVertexWithUV((double) boxRight, (double) (var19 + var13 + 1), 0.0, 1.0, 1.0);
                    tesselator.addVertexWithUV((double) boxRight, (double) (var19 - 1), 0.0, 1.0, 0.0);
                    tesselator.addVertexWithUV((double) boxLeft, (double) (var19 - 1), 0.0, 0.0, 0.0);
                    tesselator.setColorOpaque_I(0);
                    tesselator.addVertexWithUV((double) (boxLeft + 1), (double) (var19 + var13), 0.0, 0.0, 1.0);
                    tesselator.addVertexWithUV((double) (boxRight - 1), (double) (var19 + var13), 0.0, 1.0, 1.0);
                    tesselator.addVertexWithUV((double) (boxRight - 1), (double) var19, 0.0, 1.0, 0.0);
                    tesselator.addVertexWithUV((double) (boxLeft + 1), (double) var19, 0.0, 0.0, 0.0);
                    tesselator.draw();
                    GL11.glEnable(3553);
                }

                this.drawSlot(var11, boxRight, var19, var13, tesselator);
            }
        }

        GL11.glDisable(2929);
        byte var20 = 4;
        if (this.client.theWorld == null) {
            this.overlayBackground(this.top - 12, this.top, 255, 255);
            this.overlayBackground(this.bottom, this.bottom + 12, 255, 255);
        }

        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008);
        GL11.glShadeModel(7425);
        GL11.glDisable(3553);
        tesselator.startDrawingQuads();
        tesselator.setColorRGBA_I(0, 0);
        tesselator.addVertexWithUV((double) this.left, (double) (this.top + var20), 0.0, 0.0, 1.0);
        tesselator.addVertexWithUV((double) this.right, (double) (this.top + var20), 0.0, 1.0, 1.0);
        tesselator.setColorRGBA_I(0, 255);
        tesselator.addVertexWithUV((double) this.right, (double) this.top, 0.0, 1.0, 0.0);
        tesselator.addVertexWithUV((double) this.left, (double) this.top, 0.0, 0.0, 0.0);
        tesselator.draw();
        tesselator.startDrawingQuads();
        tesselator.setColorRGBA_I(0, 255);
        tesselator.addVertexWithUV((double) this.left, (double) this.bottom, 0.0, 0.0, 1.0);
        tesselator.addVertexWithUV((double) this.right, (double) this.bottom, 0.0, 1.0, 1.0);
        tesselator.setColorRGBA_I(0, 0);
        tesselator.addVertexWithUV((double) this.right, (double) (this.bottom - var20), 0.0, 1.0, 0.0);
        tesselator.addVertexWithUV((double) this.left, (double) (this.bottom - var20), 0.0, 0.0, 0.0);
        tesselator.draw();
        var19 = this.getContentHeight() - (this.bottom - this.top - 2);
        if (var19 > 0) {
            var13 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
            if (var13 < 32) {
                var13 = 32;
            }

            if (var13 > this.bottom - this.top - 8) {
                var13 = this.bottom - this.top - 8;
            }

            int var14 = (int) this.listState.scrollDistance * (this.bottom - this.top - var13) / var19 + this.top;
            if (var14 < this.top) {
                var14 = this.top;
            }

            tesselator.startDrawingQuads();
            tesselator.setColorRGBA_I(0, 255);
            tesselator.addVertexWithUV((double) scrollBarXStart, (double) this.bottom, 0.0, 0.0, 1.0);
            tesselator.addVertexWithUV((double) scrollBarXEnd, (double) this.bottom, 0.0, 1.0, 1.0);
            tesselator.addVertexWithUV((double) scrollBarXEnd, (double) this.top, 0.0, 1.0, 0.0);
            tesselator.addVertexWithUV((double) scrollBarXStart, (double) this.top, 0.0, 0.0, 0.0);
            tesselator.draw();
            tesselator.startDrawingQuads();
            tesselator.setColorRGBA_I(8421504, 255);
            tesselator.addVertexWithUV((double) scrollBarXStart, (double) (var14 + var13), 0.0, 0.0, 1.0);
            tesselator.addVertexWithUV((double) scrollBarXEnd, (double) (var14 + var13), 0.0, 1.0, 1.0);
            tesselator.addVertexWithUV((double) scrollBarXEnd, (double) var14, 0.0, 1.0, 0.0);
            tesselator.addVertexWithUV((double) scrollBarXStart, (double) var14, 0.0, 0.0, 0.0);
            tesselator.draw();
            tesselator.startDrawingQuads();
            tesselator.setColorRGBA_I(12632256, 255);
            tesselator.addVertexWithUV((double) scrollBarXStart, (double) (var14 + var13 - 1), 0.0, 0.0, 1.0);
            tesselator.addVertexWithUV((double) (scrollBarXEnd - 1), (double) (var14 + var13 - 1), 0.0, 1.0, 1.0);
            tesselator.addVertexWithUV((double) (scrollBarXEnd - 1), (double) var14, 0.0, 1.0, 0.0);
            tesselator.addVertexWithUV((double) scrollBarXStart, (double) var14, 0.0, 0.0, 0.0);
            tesselator.draw();
        }

        this.func_27257_b(mouseX, mouseY);
        GL11.glEnable(3553);
        GL11.glShadeModel(7424);
        GL11.glEnable(3008);
        GL11.glDisable(3042);
    }

    private void overlayBackground(int p_22239_1_, int p_22239_2_, int p_22239_3_, int p_22239_4_) {
        Tessellator var5 = Tessellator.instance;
        this.client.renderEngine.bindTexture(Gui.optionsBackground);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float var6 = 32.0F;
        var5.startDrawingQuads();
        var5.setColorRGBA_I(4210752, p_22239_4_);
        var5.addVertexWithUV((double) this.left, (double) p_22239_2_, 0.0, 0.0, (double) ((float) p_22239_2_ / var6));
        var5.addVertexWithUV(
            (double) this.left + (double) this.listWidth,
            (double) p_22239_2_,
            0.0,
            (double) ((float) this.listWidth / var6),
            (double) ((float) p_22239_2_ / var6));
        var5.setColorRGBA_I(4210752, p_22239_3_);
        var5.addVertexWithUV(
            (double) this.left + (double) this.listWidth,
            (double) p_22239_1_,
            0.0,
            (double) ((float) this.listWidth / var6),
            (double) ((float) p_22239_1_ / var6));
        var5.addVertexWithUV((double) this.left, (double) p_22239_1_, 0.0, 0.0, (double) ((float) p_22239_1_ / var6));
        var5.draw();
    }

    public final String getItemText(int index) {
        return this.list != null && index >= 0 && index < this.list.size() ? (String) this.list.get(index) : "";
    }

    public final int getSelectedItemIndex() {
        return this.listState.selectedIndex;
    }

    public final String getSelectedItemText() {
        return this.getItemText(this.listState.selectedIndex);
    }

    public void setTextArray(ArrayList<String> list) {
        this.list = list;
    }

    public void resetSelectionScroll() {
        this.listState.selectedIndex = 0;
        this.listState.scrollFactor = 0.0F;
        this.listState.scrollDistance = 0.0F;
        this.applyScrollLimits();
    }

    public void saveListState(PMPGuiStringListState targetState) {
        if (targetState != null) {
            targetState.saveState(this.listState);
        }

    }

    public void useListState(PMPGuiStringListState sourceState) {
        if (sourceState != null) {
            this.listState.saveState(sourceState);
            this.applyScrollLimits();
        }

    }
}
