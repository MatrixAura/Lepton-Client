package cn.matrixaura.lepton.server.handlers;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.util.bind.BindTransformer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class ModulesHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String category = URLDecoder.decode(httpExchange.getRequestURI().getQuery(), "utf-8").split("=")[1];

        JSONObject jsonObject = new JSONObject();
        JSONObject result = new JSONObject();

        for (Module module : Lepton.INSTANCE.getModuleManager().get()) {
            if (module.getCategory().name().equals(category)) {
                JSONObject moduleJsonObj = new JSONObject();
                moduleJsonObj.put("state", module.isToggled());
                moduleJsonObj.put("desc", module.getDescription());
                moduleJsonObj.put("binding", BindTransformer.lwjglToStandKey(module.getBind().getKeyCode()));
                moduleJsonObj.put("settings", !module.getSettings().isEmpty());
                moduleJsonObj.put("canToggle", module.canToggle());
                result.put(module.getName(), moduleJsonObj);
            }
        }

        jsonObject.put("result", result);
        jsonObject.put("success", true);

        byte[] response = jsonObject.toString().getBytes(StandardCharsets.UTF_8);

        httpExchange.sendResponseHeaders(200, response.length);

        OutputStream out = httpExchange.getResponseBody();
        out.write(response);
        out.flush();
        out.close();
    }

}
