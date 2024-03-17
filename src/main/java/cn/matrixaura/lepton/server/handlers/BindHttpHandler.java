package cn.matrixaura.lepton.server.handlers;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.util.bind.BindTransformer;
import cn.matrixaura.lepton.util.string.URLUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class BindHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String[] param = URLUtils.getValues(httpExchange);
        String module = param[0];
        int key = Integer.parseInt(param[1]);

        try {
            int lwjglKey = BindTransformer.stand2lwjgl(key);
            Lepton.INSTANCE.getModuleManager().get(module).getBind().setKeyCode(lwjglKey);
        } catch (NullPointerException ignored) {
            Lepton.INSTANCE.getModuleManager().get(module).getBind().setKeyCode(0);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", key);
        jsonObject.put("success", true);

        byte[] response = jsonObject.toString().getBytes(StandardCharsets.UTF_8);

        httpExchange.sendResponseHeaders(200, response.length);

        OutputStream out = httpExchange.getResponseBody();
        out.write(response);
        out.flush();
        out.close();
    }

}
