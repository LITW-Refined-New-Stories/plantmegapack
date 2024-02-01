package plantmegapack.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;

import plantmegapack.block.PMPBlockBambooSlab;

public class PMPItemBambooSlab extends ItemSlab {

    public PMPItemBambooSlab(Block block, PMPBlockBambooSlab blockSlabSingle, PMPBlockBambooSlab blockSlabDouble) {
        super(block, blockSlabSingle, blockSlabDouble, false);
    }
}
