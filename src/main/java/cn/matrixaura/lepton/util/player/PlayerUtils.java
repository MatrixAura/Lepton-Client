package cn.matrixaura.lepton.util.player;

import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;

public class PlayerUtils {

    public static boolean isMoving() {
        return MinecraftWrapper.get().getPlayer().getMovementInputObj().getMoveForward() != 0 || MinecraftWrapper.get().getPlayer().getMovementInputObj().getMoveStrafe() != 0;
    }

}
