package plantmegapack.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.block.PMPBlockHangingPlant;

public class PMPItemHangingPlant extends ItemBlock {

    private PMPBlockHangingPlant blockParent;

    public PMPItemHangingPlant(Block block) {
        super(block);
        this.setUnlocalizedName(
            block.getUnlocalizedName()
                .substring(5));
        this.blockParent = (PMPBlockHangingPlant) block;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return this.blockParent.getIcon(0, 2);
    }
}
