package plantmegapack.bin;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public abstract class PMPHelpers {

    public static int getPlayerFacingDirection(EntityLivingBase entity) {
        if (entity == null) {
            return 0;
        } else {
            int direction = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5) & 3;
            if (direction == 0) {
                return 2;
            } else if (direction == 1) {
                return 5;
            } else if (direction == 2) {
                return 3;
            } else {
                return direction == 3 ? 4 : 0;
            }
        }
    }
}
