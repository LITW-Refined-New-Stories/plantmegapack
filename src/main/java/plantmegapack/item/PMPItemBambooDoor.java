package plantmegapack.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockBambooDoor;
import plantmegapack.common.PMPTab;

public class PMPItemBambooDoor extends ItemBlock {

    private PMPBlockBambooDoor blockParent = null;

    public PMPItemBambooDoor(Block block) {
        super(block);
        this.blockParent = (PMPBlockBambooDoor) block;
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.build));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return this.blockParent.getIcon(0, 0);
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side,
        float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (side != 1) {
            return false;
        } else {
            ++y;
            PMPBlockBambooDoor blockDoor = PlantMegaPack.blocks.getBambooDoor(
                this.getUnlocalizedName()
                    .substring(5));
            if (player.canPlayerEdit(x, y, z, side, itemStack) && player.canPlayerEdit(x, y + 1, z, side, itemStack)) {
                if (!blockDoor.canPlaceBlockAt(world, x, y, z)) {
                    return false;
                } else {
                    int i1 = MathHelper.floor_double((double) ((player.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5)
                        & 3;
                    ItemDoor.placeDoorBlock(world, x, y, z, i1, blockDoor);
                    --itemStack.stackSize;
                    return true;
                }
            } else {
                return false;
            }
        }
    }
}
