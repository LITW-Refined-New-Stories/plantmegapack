package plantmegapack.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class PMPItemWallBracket extends ItemBlock {

    public PMPItemWallBracket(Block block) {
        super(block);
        String name = block.getUnlocalizedName()
            .substring(5);
        this.setUnlocalizedName(name);
        this.setTextureName("plantmegapack:" + name);
    }
}
