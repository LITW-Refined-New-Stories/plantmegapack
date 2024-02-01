package plantmegapack.gui;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;

public class PMPGuiCreativeTab extends CreativeTabs {

    private String iconBlock;

    public PMPGuiCreativeTab(String tabText, String iconBlock) {
        super(CreativeTabs.getNextID(), tabText);
        this.iconBlock = iconBlock;
    }

    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        if (this.iconBlock.matches("food")) {
            return PlantMegaPack.items.getFoodItem("foodCorn");
        } else {
            ArrayList<ItemStack> ores = OreDictionary.getOres(this.iconBlock);
            return ores != null && ores.size() != 0 ? ((ItemStack) ores.get(0)).getItem()
                : Item.getItemFromBlock(Blocks.deadbush);
        }
    }

    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel() {
        return "creativeTab." + this.getTabLabel();
    }
}
