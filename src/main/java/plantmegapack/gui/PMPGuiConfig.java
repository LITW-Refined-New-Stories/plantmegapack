package plantmegapack.gui;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import cpw.mods.fml.client.IModGuiFactory;

public class PMPGuiConfig implements IModGuiFactory {

    public void initialize(Minecraft minecraftInstance) {}

    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return PMPGuiConfigMain.class;
    }

    public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    public IModGuiFactory.RuntimeOptionGuiHandler getHandlerFor(IModGuiFactory.RuntimeOptionCategoryElement element) {
        return null;
    }
}
