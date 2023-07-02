package cn.matrixaura.lepton.server.handlers;

import cn.matrixaura.lepton.util.file.FileUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class RootHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        byte[] responseContentByte = FileUtils.read("/index.html").getBytes(StandardCharsets.UTF_8);

        httpExchange.getResponseHeaders().add("Content-Type:", "text/html;charset=utf-8");
        httpExchange.sendResponseHeaders(200, responseContentByte.length);

        OutputStream out = httpExchange.getResponseBody();
        out.write(responseContentByte);
        out.flush();
        out.close();
    }

}
