package com.codecool.wot;

import com.codecool.wot.dao.DatabaseMigration;
import com.codecool.wot.web.*;
//import com.codecool.wot.web.Cookie;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

class Application {
    public static void main(String[] args) throws Exception {
        // migrate database at server start
        DatabaseMigration.migrateDatabase();
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // set routes
//        server.createContext("/hello", new Hello());
        server.createContext("/", new LoginHandler());
        server.createContext("/static", new Static());
        server.createContext("/admin", new AdminHandler());
        server.createContext("/mentor", new MentorHandler());
        server.createContext("/student", new StudentHandler());
//        server.createContext("/cookie", new Cookie());
        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }
}
