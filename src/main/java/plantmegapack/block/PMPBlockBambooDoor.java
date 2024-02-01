package plantmegapack.block;

import java.util.Random;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPTab;
import plantmegapack.item.PMPItemBambooDoor;

public class PMPBlockBambooDoor extends BlockDoor {

    @SideOnly(Side.CLIENT)
    private IIcon[] iconTop;
    @SideOnly(Side.CLIENT)
    private IIcon[] iconBottom;
    @SideOnly(Side.CLIENT)
    private IIcon iconInventory;

    public PMPBlockBambooDoor(String blockName) {
        super(Material.wood);
        this.setHardness(3.0F);
        this.setStepSound(soundTypeWood);
        this.setBlockName(blockName);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
        GameRegistry.registerBlock(this, PMPItemBambooDoor.class, blockName);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        String blockName = this.getUnlocalizedName()
            .substring(5);
        this.iconTop = new IIcon[2];
        this.iconBottom = new IIcon[2];
        this.iconTop[0] = ir.registerIcon("plantmegapack:" + blockName + "2");
        this.iconBottom[0] = ir.registerIcon("plantmegapack:" + blockName + "1");
        this.iconTop[1] = new IconFlipped(this.iconTop[0], true, false);
        this.iconBottom[1] = new IconFlipped(this.iconBottom[0], true, false);
        this.iconInventory = ir.registerIcon("plantmegapack:" + blockName + "0");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int metaData) {
        if (metaData != 1 && metaData != 0) {
            int i1 = this.func_150012_g(world, x, y, z);
            int j1 = i1 & 3;
            boolean flag = (i1 & 4) != 0;
            boolean flag1 = false;
            boolean flag2 = (i1 & 8) != 0;
            if (flag) {
                if (j1 == 0 && metaData == 2) {
                    flag1 = !flag1;
                } else if (j1 == 1 && metaData == 5) {
                    flag1 = !flag1;
                } else if (j1 == 2 && metaData == 3) {
                    flag1 = !flag1;
                } else if (j1 == 3 && metaData == 4) {
                    flag1 = !flag1;
                }
            } else {
                if (j1 == 0 && metaData == 5) {
                    flag1 = !flag1;
                } else if (j1 == 1 && metaData == 3) {
                    flag1 = !flag1;
                } else if (j1 == 2 && metaData == 4) {
                    flag1 = !flag1;
                } else if (j1 == 3 && metaData == 2) {
                    flag1 = !flag1;
                }

                if ((i1 & 16) != 0) {
                    flag1 = !flag1;
                }
            }

            return flag2 ? this.iconTop[flag1 ? 1 : 0] : this.iconBottom[flag1 ? 1 : 0];
        } else {
            return this.iconBottom[0];
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData) {
        return this.iconInventory;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(this);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(this);
    }
}
