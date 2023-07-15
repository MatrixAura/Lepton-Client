package cn.matrixaura.lepton.module.impl.visual;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.render.EventRender2D;
import cn.matrixaura.lepton.listener.events.render.EventShader;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.util.render.ColorUtils;
import cn.matrixaura.lepton.util.render.RenderUtils;
import com.sun.jna.Platform;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ModuleInfo(name = "HUD", description = "Head-Up Display", category = Category.Visual, enabled = true)
public class HUDModule extends Module {

    @Listener
    public void onRender2D(EventRender2D event) {
        arraylist:
        {
            List<Module> modules = Lepton.INSTANCE.getModuleManager().get()
                    .stream()
                    .filter(Module::isToggled)
                    .sorted(Comparator.comparingInt((x) -> -mc.getFontRenderer().getStringWidth(x.getName())))
                    .collect(Collectors.toList());

            if (modules.isEmpty()) break arraylist;

            float y = 2.0f;
            for (int i = 0; i < modules.size(); ++i) {
                Module module = modules.get(i);

                mc.getFontRenderer().drawStringShadow(module.getName(),
                        event.getWidth() - 2.0f - mc.getFontRenderer().getStringWidth(module.getName()), y,
                        ColorUtils.rainbowCycle(i * 200, 3.5));
                y += mc.getFontRenderer().getHeight() + 1;

            }
        }

    }
}
