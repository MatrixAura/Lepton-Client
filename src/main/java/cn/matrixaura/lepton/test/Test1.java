package cn.matrixaura.lepton.test;

import cn.matrixaura.lepton.uiengines.LAIT.LAIT;
import cn.matrixaura.lepton.uiengines.LAIT.Node;
import cn.matrixaura.lepton.util.file.FileUtils;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;

public class Test1 {

    public static void main(String[] args) throws FileNotFoundException {

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
