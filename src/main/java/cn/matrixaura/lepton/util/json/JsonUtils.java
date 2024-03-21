package cn.matrixaura.lepton.util.json;

import com.google.gson.*;

import java.util.List;

public class JsonUtils {

    public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static Gson BeanGenerator = new GsonBuilder()
            .setPrettyPrinting()
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return !f.hasModifier(java.lang.reflect.Modifier.PUBLIC);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();

    public static JsonArray parseArray(List<?> list) {
        return GSON.fromJson(GSON.toJson(list), JsonArray.class);
    }

}
