package cn.matrixaura.lepton.server.handlers;

import cn.matrixaura.lepton.module.Category;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CategoriesHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        JSONObject jsonObject = new JSONObject();
        JSONArray result = new JSONArray();

        for (Category category : Category.values()) {
            result.add(category.name());
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
