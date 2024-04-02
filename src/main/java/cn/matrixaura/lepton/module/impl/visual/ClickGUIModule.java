package cn.matrixaura.lepton.module.impl.visual;

import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.uiengines.LAIT.LAIT;
import com.google.gson.JsonParser;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@ModuleInfo(name = "Click GUI", description = "Open the click gui", category = Category.Visual, key = Keyboard.KEY_RSHIFT)
public class ClickGUIModule extends Module { // TODO: ClickGUI Unlimited shelving

    LAIT lait = LAIT.parse(new JsonParser().parse(new FileReader(new File("F:\\Code\\Lepton-Client\\src\\main\\resources\\assets\\lepton\\client\\LAITGui\\ClickGUI.LAIT.json5"))).getAsJsonObject());

    public ClickGUIModule() throws FileNotFoundException {
    }

    @Override
    public void onEnable() {
        try {
            lait = LAIT.parse(new JsonParser().parse(new FileReader(new File("F:\\Code\\Lepton-Client\\src\\main\\resources\\assets\\lepton\\client\\LAITGui\\ClickGUI.LAIT.json5"))).getAsJsonObject()); // 动态刷新
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        lait.register();
    }

    @Override
    public void onDisable() {
        lait.unregister();
    }
}
