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
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class SetSettingHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String[] param = URLDecoder.decode(httpExchange.getRequestURI().getQuery(), "utf-8").split("&");
        String moduleName = param[0].split("=")[1];
        String name = param[1].split("=")[1];
        String value = param[2].split("=")[1];

        Module module = Lepton.INSTANCE.getModuleManager().get(moduleName);
        JSONObject jsonObject = new JSONObject();

        for (Setting<?> setting : module.getSettings()) {
            if (setting.getName().equals(name)) {
                if (setting.getValue() instanceof Boolean) {
                    ((BooleanSetting) setting).setValue(value.equals("true"));
                    jsonObject.put("result", value.equals("true"));
                } else if (setting.getValue() instanceof Number) {
                    ((NumberSetting) setting).setValue(Double.valueOf(value));
                    jsonObject.put("result", Double.valueOf(value));
                } else if (setting.getValue() instanceof String) {
                    ((StringSetting) setting).setValue(value);
                    jsonObject.put("result", value);
                } else if (setting.getValue() instanceof Enum) {
                    ((EnumSetting<?>) setting).setByName(value);
                    jsonObject.put("result", value);
                }
            }
        }

        jsonObject.put("success", true);

        byte[] response = jsonObject.toString().getBytes(StandardCharsets.UTF_8);

        httpExchange.sendResponseHeaders(200, response.length);

        OutputStream out = httpExchange.getResponseBody();
        out.write(response);
        out.flush();
        out.close();
    }

}
