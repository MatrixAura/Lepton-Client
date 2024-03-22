package cn.matrixaura.lepton.test;

import cn.matrixaura.lepton.uiengines.LAIT.nodes.render.RectangleNode;
import cn.matrixaura.lepton.util.json.JsonUtils;

import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;

public class Test1 {

    public static void main(String[] args) throws FileNotFoundException {
        RectangleNode toObject = new RectangleNode();
        toObject.height = 800;
        toObject.width = 1200;
        toObject.align = new int[]{1, 1};
        toObject.offset = new float[]{0, 0};
        toObject.color = Color.BLUE.getRGB();
        String json = JsonUtils.BeanGenerator.toJson(toObject);
        printPublicFields(JsonUtils.BeanGenerator.fromJson(json, RectangleNode.class));
    }

    public static void printPublicFields(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getFields(); // 获取所有公开字段

        for (Field field : fields) {
            try {
                Object value = field.get(obj); // 获取字段的值
                System.out.println(field.getName() + ": " + value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
