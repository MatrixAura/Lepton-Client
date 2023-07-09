package cn.matrixaura.lepton.module.impl.movement;

import cn.matrixaura.lepton.inject.wrapper.impl.HitResult;
import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.player.EventUpdate;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;

@ModuleInfo(name = "Bridge Assist", description = "Assists you at bridging", category = Category.Movement)
public class BridgeAssist extends Module {
    private boolean sneakOverride;

    @Override
    public void onDisable() {
        if (sneakOverride) {
            mc.getGameSettings().getKey("key.sneak").setPressed(false);
        }

        sneakOverride = false;
    }

    @Listener
    public void onUpdate(EventUpdate event) {
        if (mc.getHitResult().getType() == HitResult.Type.BLOCK) {
            sneakOverride = mc.getWorld().isAir(mc.getPlayer().getPos().add(0, -1, 0));
            mc.getGameSettings().getKey("key.sneak").setPressed(sneakOverride);
        } else {
            if (sneakOverride) {
                sneakOverride = false;
                mc.getGameSettings().getKey("key.sneak").setPressed(false);
            }
        }
    }
}
