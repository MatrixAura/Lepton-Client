package cn.matrixaura.lepton.server.handlers;

import cn.matrixaura.lepton.util.file.FileUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class JSHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        byte[] bytes = FileUtils.readPath("/assets/lepton/client/server/script.css").getBytes(StandardCharsets.UTF_8);

        httpExchange.getResponseHeaders().set("Content-Type", "application/javascript");
        httpExchange.sendResponseHeaders(200, bytes.length);
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

}
