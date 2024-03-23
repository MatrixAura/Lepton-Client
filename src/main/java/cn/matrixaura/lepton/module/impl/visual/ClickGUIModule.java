package cn.matrixaura.lepton.module.impl.visual;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.uiengines.LAIT.LAIT;
import org.lwjgl.input.Keyboard;

import java.io.File;

@ModuleInfo(name = "Click GUI", description = "Open the click gui", category = Category.Visual, key = Keyboard.KEY_RSHIFT)
public class ClickGUIModule extends Module { // TODO: ClickGUI Unlimited shelving

    @Override
    public void onEnable() {
        LAIT.parse("/assets/lepton/client/LAITGui/ClickGUI.LAIT.json5").register()
                .setPrevFunc(() -> Lepton.logger.info("prev"))
                .setPostFunc(() -> Lepton.logger.info("post"));
        this.toggle();
    }

}
