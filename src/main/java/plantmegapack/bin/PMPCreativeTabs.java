package plantmegapack.bin;

import java.util.HashMap;

import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPTab;
import plantmegapack.gui.PMPGuiCreativeTab;

public class PMPCreativeTabs {

    private HashMap<String, PMPGuiCreativeTab> creativeTabs = new HashMap(PMPTab.values().length);

    public PMPCreativeTabs() {
        PMPTab[] var2 = PMPTab.values();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            PMPTab tabs = var2[var4];
            if (tabs.addToGame > 0
                && (PlantMegaPack.settingsGeneral.contentAquatic || !tabs.unlocalizedName.matches("aquatic"))
                && (PlantMegaPack.settingsGeneral.contentCrops || !tabs.unlocalizedName.matches("crops"))) {
                PMPGuiCreativeTab newTab = new PMPGuiCreativeTab(tabs.unlocalizedName, tabs.iconBlockID);
                this.creativeTabs.put(tabs.unlocalizedName, newTab);
            }
        }

    }

    public PMPGuiCreativeTab getCreativeTab(PMPTab tab) {
        return tab.addToGame > 0 ? (PMPGuiCreativeTab) this.creativeTabs.get(tab.unlocalizedName) : null;
    }
}
