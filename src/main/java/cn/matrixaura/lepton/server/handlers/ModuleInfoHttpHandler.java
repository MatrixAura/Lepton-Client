package cn.matrixaura.lepton.server.handlers;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.util.string.URLUtils;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ModuleInfoHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String category = URLUtils.getValues(httpExchange)[0];

        JsonObject jsonObject = new JsonObject();
        JsonObject result = new JsonObject();

        Lepton.INSTANCE.getModuleManager().get().stream().filter(it -> it.getCategory().name().equals(category)).forEach(it -> result.addProperty(URLUtils.encode(it.getName()), it.isToggled()));

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
