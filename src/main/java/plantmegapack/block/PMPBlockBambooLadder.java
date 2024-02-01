package plantmegapack.block;

import net.minecraft.block.BlockLadder;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPTab;

public class PMPBlockBambooLadder extends BlockLadder {

    private PMPBlockBambooBlock blockParent;
    @SideOnly(Side.CLIENT)
    private IIcon texture;
    private String blockName;

    public PMPBlockBambooLadder(String blockName, PMPBlockBambooBlock blockParent) {
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
        this.blockName = blockName;
        this.blockParent = blockParent;
        this.setHardness(1.2F);
        GameRegistry.registerBlock(this, blockName);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        this.texture = ir.registerIcon("plantmegapack:" + this.blockName);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData) {
        return this.texture;
    }

    public String getUnlocalizedName() {
        String name = this.blockParent.getUnlocalizedName();
        return name.substring(0, name.length() - 5) + "Ladder";
    }
}
