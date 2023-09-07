package cn.matrixaura.lepton.module.impl.visual;

import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.ui.clickgui.ClickGUI;

//@ModuleInfo(name = "Click GUI", description = "Open the click gui", category = Category.Visual, key = Keyboard.KEY_RSHIFT)
public class ClickGUIModule extends Module { // TODO: ClickGUI Unlimited shelving

    @Override
    public void onEnable() {
        mc.displayGuiScreen(new ClickGUI());
        this.toggle();
    }

}
