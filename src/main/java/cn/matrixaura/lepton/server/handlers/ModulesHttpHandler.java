package cn.matrixaura.lepton.server.handlers;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.util.bind.BindTransformer;
import cn.matrixaura.lepton.util.string.URLUtils;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ModulesHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String category = URLUtils.getValues(httpExchange)[0];

        JsonObject jsonObject = new JsonObject();
        JsonObject result = new JsonObject();

        for (Module module : Lepton.INSTANCE.getModuleManager().get()) {
            if (module.getCategory().name().equals(category)) {
                JsonObject moduleJsonObj = new JsonObject();
                moduleJsonObj.addProperty("state", module.isToggled());
                moduleJsonObj.addProperty("desc", module.getDescription());
                moduleJsonObj.addProperty("binding", BindTransformer.lwjgl2stand(module.getBind().getKeyCode()));
                moduleJsonObj.addProperty("settings", !module.getSettings().isEmpty());
                moduleJsonObj.addProperty("canToggle", module.canToggle());
                result.add(module.getName(), moduleJsonObj);
            }
        }

        jsonObject.add("result", result);
        jsonObject.addProperty("success", true);

        byte[] response = jsonObject.toString().getBytes(StandardCharsets.UTF_8);

        httpExchange.sendResponseHeaders(200, response.length);

        OutputStream out = httpExchange.getResponseBody();
        out.write(response);
        out.flush();
        out.close();
    }

}
