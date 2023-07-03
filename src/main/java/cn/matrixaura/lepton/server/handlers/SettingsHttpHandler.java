package cn.matrixaura.lepton.server.handlers;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.setting.settings.BooleanSetting;
import cn.matrixaura.lepton.setting.settings.EnumSetting;
import cn.matrixaura.lepton.setting.settings.NumberSetting;
import cn.matrixaura.lepton.setting.settings.StringSetting;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class SettingsHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String moduleName = URLDecoder.decode(httpExchange.getRequestURI().getQuery(), "utf-8").split("=")[1];

        JSONObject jsonObject = new JSONObject();
        boolean isFound = false;

        for (Module module : Lepton.INSTANCE.getModuleManager().get()) {
            if (module.getName().equals(moduleName)) {
                JSONArray moduleJsonArray = new JSONArray();
                isFound = true;
                for (Setting<?> setting : module.getSettings()) {
                    JSONObject moduleSet = new JSONObject();
                    if (setting.getValue() instanceof String) {
                        moduleSet.put("name", setting.getName());
                        moduleSet.put("type", "input");
                        moduleSet.put("value", ((StringSetting) setting).getValue());
                    } else if (setting.getValue() instanceof Number) {
                        moduleSet.put("name", setting.getName());
                        moduleSet.put("type", "slider");
                        moduleSet.put("min", ((NumberSetting) setting).getMin().doubleValue());
                        moduleSet.put("max", ((NumberSetting) setting).getMax().doubleValue());
                        moduleSet.put("step", ((NumberSetting) setting).getInc().doubleValue());
                        moduleSet.put("value", ((NumberSetting) setting).getValue().doubleValue());
                    } else if (setting.getValue() instanceof Enum) {
                        moduleSet.put("name", setting.getName());
                        moduleSet.put("type", "selection");
                        JSONArray values = new JSONArray();
                        for (Enum<?> aEnum : ((Enum<?>) setting.getValue()).getDeclaringClass().getEnumConstants()) {
                            values.add(aEnum.name());
                        }
                        moduleSet.put("values", values);
                        moduleSet.put("value", ((EnumSetting<?>) setting).getValue().name());
                    } else if (setting.getValue() instanceof Boolean) {
                        moduleSet.put("name", setting.getName());
                        moduleSet.put("type", "checkbox");
                        moduleSet.put("value", ((BooleanSetting) setting).getValue());
                    }
                    moduleJsonArray.add(moduleSet);
                }
                jsonObject.put("result", moduleJsonArray);
            }
        }

        jsonObject.put("success", isFound);
        if (!isFound) jsonObject.put("reason", "Can't find module");

        byte[] response = jsonObject.toString().getBytes(StandardCharsets.UTF_8);

        httpExchange.sendResponseHeaders(200, response.length);

        OutputStream out = httpExchange.getResponseBody();
        out.write(response);
        out.flush();
        out.close();
    }

}
