package plantmegapack.block;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPTab;

public class PMPBlockBambooGate extends BlockFenceGate {

    private PMPBlockBambooBlock blockParent;

    public PMPBlockBambooGate(String blockName, PMPBlockBambooBlock blockParent) {
        this.setBlockName(blockName);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
        this.blockParent = blockParent;
        this.setHardness(1.2F);
        GameRegistry.registerBlock(this, blockName);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {}

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData) {
        return side != 0 && side != 1 ? this.blockParent.getSideIcon(side) : this.blockParent.getTopIcon(side);
    }

    public String getUnlocalizedName() {
        String name = this.blockParent.getUnlocalizedName();
        return name.substring(0, name.length() - 5) + "Gate";
    }
}
