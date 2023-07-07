package cn.matrixaura.lepton.server.handlers;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.module.Module;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class ModuleInfoHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String category = URLDecoder.decode(httpExchange.getRequestURI().getQuery(), "utf-8").split("=")[1];

        JSONObject jsonObject = new JSONObject();
        JSONObject result = new JSONObject();

        Lepton.INSTANCE.getModuleManager().get().stream().filter(it -> it.getCategory().name().equals(category)).forEach(it -> result.put(it.getName(), it.isToggled()));

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
