package cn.matrixaura.lepton.util.json;

import cn.matrixaura.lepton.setting.settings.ModeSetting;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.List;

public class JsonUtils {

    public static Gson GSON = new Gson();

    public static JsonArray parseArray(List<?> list) {
        return GSON.fromJson(GSON.toJson(list), JsonArray.class);
    }

}
