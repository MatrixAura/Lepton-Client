package cn.matrixaura.lepton.module.impl.visual;

import cn.matrixaura.lepton.module.Module;

//@ModuleInfo(name = "Click GUI", description = "Open the click gui", category = Category.Visual, key = Keyboard.KEY_RSHIFT)
public class ClickGUIModule extends Module { // TODO: ClickGUI Unlimited shelving

    @Override
    public void onEnable() {
        this.toggle();
    }

}
