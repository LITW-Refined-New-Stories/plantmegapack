package plantmegapack.block;

import net.minecraft.block.BlockStairs;
import net.minecraft.util.IIcon;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPTab;

public class PMPBlockBambooStairs extends BlockStairs {

    private PMPBlockBambooBlock blockParent;

    public PMPBlockBambooStairs(String blockName, PMPBlockBambooBlock blockParent) {
        super(blockParent, 0);
        this.setHardness(1.2F);
        this.setStepSound(soundTypeWood);
        this.setBlockName(blockName);
        this.setLightOpacity(0);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
        this.blockParent = blockParent;
        GameRegistry.registerBlock(this, blockName);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData) {
        return side != 0 && side != 1 ? this.blockParent.getSideIcon(side) : this.blockParent.getTopIcon(side);
    }
}
