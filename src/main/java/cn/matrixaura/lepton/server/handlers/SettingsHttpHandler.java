package cn.matrixaura.lepton.server.handlers;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.setting.settings.BooleanSetting;
import cn.matrixaura.lepton.setting.settings.ModeSetting;
import cn.matrixaura.lepton.setting.settings.NumberSetting;
import cn.matrixaura.lepton.setting.settings.StringSetting;
import cn.matrixaura.lepton.util.json.JsonUtils;
import cn.matrixaura.lepton.util.string.URLUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SettingsHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String moduleName = URLUtils.getValues(httpExchange)[0];

        JsonObject jsonObject = new JsonObject();
        boolean isFound = false;

        for (Module module : Lepton.INSTANCE.getModuleManager().get()) {
            if (module.getName().equals(moduleName)) {
                JsonArray moduleJsonArray = new JsonArray();
                isFound = true;
                for (Setting<?> setting : module.getSettings()) {
                    JsonObject moduleSet = new JsonObject();
                    if (setting instanceof StringSetting) {
                        moduleSet.addProperty("name", setting.getName());
                        moduleSet.addProperty("type", "input");
                        moduleSet.addProperty("value", ((StringSetting) setting).getValue());
                    } else if (setting instanceof NumberSetting) {
                        moduleSet.addProperty("name", setting.getName());
                        moduleSet.addProperty("type", "slider");
                        moduleSet.addProperty("min", ((NumberSetting) setting).getMin().doubleValue());
                        moduleSet.addProperty("max", ((NumberSetting) setting).getMax().doubleValue());
                        moduleSet.addProperty("step", ((NumberSetting) setting).getInc().doubleValue());
                        moduleSet.addProperty("value", ((NumberSetting) setting).getValue().doubleValue());
                    } else if (setting instanceof ModeSetting) {
                        JsonArray values = new JsonArray();
                        moduleSet.addProperty("name", setting.getName());
                        moduleSet.addProperty("type", "selection");
                        values.addAll(JsonUtils.parseArray(Arrays.asList(((ModeSetting) setting).getValues())));
                        moduleSet.add("values", values);
                        moduleSet.addProperty("value", URLUtils.encode(((ModeSetting) setting).getValue()));
                    } else if (setting instanceof BooleanSetting) {
                        moduleSet.addProperty("name", setting.getName());
                        moduleSet.addProperty("type", "checkbox");
                        moduleSet.addProperty("value", ((BooleanSetting) setting).getValue());
                    }
                    moduleJsonArray.add(moduleSet);
                }
                jsonObject.add("result", moduleJsonArray);
            }
        }

        jsonObject.addProperty("success", isFound);
        if (!isFound) jsonObject.addProperty("reason", "Can't find module");

        byte[] response = jsonObject.toString().getBytes(StandardCharsets.UTF_8);

        httpExchange.sendResponseHeaders(200, response.length);

        OutputStream out = httpExchange.getResponseBody();
        out.write(response);
        out.flush();
        out.close();
    }

}
