package plantmegapack.block;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPTab;

public class PMPBlockBambooBlock extends BlockLog {

    @SideOnly(Side.CLIENT)
    protected IIcon[] textures;

    public PMPBlockBambooBlock(String blockName, PMPBlockPlant blockParent) {
        this.setHardness(1.2F);
        this.setStepSound(soundTypeWood);
        this.setBlockName(blockName);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
        GameRegistry.registerBlock(this, blockName);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        String iconName = this.getUnlocalizedName()
            .substring(5);
        this.textures = new IIcon[2];

        for (int count = 0; count < 2; ++count) {
            this.textures[count] = ir.registerIcon("plantmegapack:" + iconName + count);
        }

    }

    @SideOnly(Side.CLIENT)
    public IIcon getSideIcon(int side) {
        return this.textures[0];
    }

    @SideOnly(Side.CLIENT)
    public IIcon getTopIcon(int side) {
        return this.textures[1];
    }

    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }
}
