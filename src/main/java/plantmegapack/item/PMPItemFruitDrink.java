package plantmegapack.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.common.PMPTab;

public class PMPItemFruitDrink extends ItemFood {

    public PMPItemFruitDrink(String unlocalizedName, int foodValue, float saturation) {
        super(foodValue, saturation, false);
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName("plantmegapack:" + unlocalizedName);
        this.setCreativeTab(PlantMegaPack.creativeTabs.getCreativeTab(PMPTab.food));
        GameRegistry.registerItem(this, unlocalizedName);
        this.setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
        super.onEaten(itemStack, world, player);
        return new ItemStack(Items.glass_bottle);
    }
}
