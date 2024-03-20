package cn.matrixaura.lepton.test;

import cn.matrixaura.lepton.uiengines.LAIT.nodes.RectangleNode;
import com.google.gson.JsonParser;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Test1 {

    public static void main(String[] args) throws FileNotFoundException {
        RectangleNode toObject = new RectangleNode();
        toObject.height = 800;
        toObject.width = 1200;
        toObject.align = new Number[]{1, 1};
        toObject.offset = new Number[]{0, 0};
        toObject.color = Color.BLUE.getRGB();
        //System.out.println(YamlUtils.toYaml(toObject));
        System.out.println(new JsonParser().parse(new FileReader("F:\\Code\\Lepton-Client\\src\\main\\resources\\assets\\lepton\\client\\laits\\ClickGUI.LAIT.json5")).getAsJsonObject().toString());
    }

}
