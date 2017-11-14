package com.codecool.wot;

import com.codecool.wot.web.LoginHandler;
import com.codecool.wot.web.Cookie;
import com.codecool.wot.web.Static;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

class Application {
    public static void main(String[] args) throws Exception {
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // set routes
//        server.createContext("/hello", new Hello());
        server.createContext("/login", new LoginHandler());
        server.createContext("/static", new Static());
        server.createContext("/cookie", new Cookie());
        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }
}
