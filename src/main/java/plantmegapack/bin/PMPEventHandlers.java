package plantmegapack.bin;

import net.minecraftforge.event.entity.player.BonemealEvent;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import plantmegapack.block.PMPBlockPlant;

public class PMPEventHandlers {

    @SubscribeEvent
    public void onUseBonemeal(BonemealEvent event) {
        if (!event.world.isRemote && event.block.getClass() == PMPBlockPlant.class) {
            PMPBlockPlant block = (PMPBlockPlant) event.block;
            if (!block.isFullyGrown(event.world.getBlockMetadata(event.x, event.y, event.z))
                && block.growPlant(event.world, event.x, event.y, event.z)) {
                event.setResult(Result.ALLOW);
            }
        }

    }
}
