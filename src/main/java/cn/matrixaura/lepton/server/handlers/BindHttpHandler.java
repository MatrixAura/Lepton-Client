package cn.matrixaura.lepton.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class BindHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String[] param = URLDecoder.decode(httpExchange.getRequestURI().getQuery(), "utf-8").split("&");
        String module = param[0].split("=")[1];
        int key = Integer.parseInt(param[1].split("=")[1]);

        //Lepton.INSTANCE.getModuleManager().get(module).getBind().setKey(BindTransformer.standToLwjglKey(key));

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
