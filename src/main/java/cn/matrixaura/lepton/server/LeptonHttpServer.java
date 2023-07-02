package cn.matrixaura.lepton.server;

import cn.matrixaura.lepton.server.handlers.RootHttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class LeptonHttpServer {

    private static HttpServer server;

    public static void main(String[] args) throws IOException {
        server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        server.createContext("/", new RootHttpHandler());
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();
    }

}
