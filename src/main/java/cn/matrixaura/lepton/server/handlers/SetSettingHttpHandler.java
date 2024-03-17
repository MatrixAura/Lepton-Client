package cn.matrixaura.lepton.server.handlers;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.setting.settings.BooleanSetting;
import cn.matrixaura.lepton.setting.settings.ModeSetting;
import cn.matrixaura.lepton.setting.settings.NumberSetting;
import cn.matrixaura.lepton.setting.settings.StringSetting;
import cn.matrixaura.lepton.util.string.URLUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class SetSettingHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String[] param = URLUtils.getValues(httpExchange);
        String moduleName = param[0];
        String name = param[1];
        String value = param[2];

        Module module = Lepton.INSTANCE.getModuleManager().get(moduleName);
        JSONObject jsonObject = new JSONObject();

        for (Setting<?> setting : module.getSettings()) {
            if (setting.getName().equals(name)) {
                if (setting instanceof BooleanSetting) {
                    ((BooleanSetting) setting).setValue(value.equals("true"));
                    jsonObject.put("result", value.equals("true"));
                } else if (setting instanceof NumberSetting) {
                    ((NumberSetting) setting).setValue(Double.valueOf(value));
                    jsonObject.put("result", Double.valueOf(value));
                } else if (setting instanceof StringSetting) {
                    ((StringSetting) setting).setValue(value);
                    jsonObject.put("result", value);
                } else if (setting instanceof ModeSetting) {
                    ((ModeSetting) setting).setValue(URLUtils.decode(value));
                    jsonObject.put("result", URLUtils.encode(value));
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
