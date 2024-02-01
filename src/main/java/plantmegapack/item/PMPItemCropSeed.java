package plantmegapack.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSeeds;

import cpw.mods.fml.common.registry.GameRegistry;

public class PMPItemCropSeed extends ItemSeeds {

    public PMPItemCropSeed(Block block, String unlocalizedName) {
        super(block, (Block) null);
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName("plantmegapack:" + unlocalizedName);
        this.setCreativeTab(CreativeTabs.tabMaterials);
        GameRegistry.registerItem(this, unlocalizedName);
    }
}
