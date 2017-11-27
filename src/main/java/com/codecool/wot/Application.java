package com.codecool.wot;

import com.codecool.wot.dao.DatabaseMigration;
import com.codecool.wot.web.*;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

class Application {
    public static void main(String[] args) throws Exception {
        // migrate database at server start
        DatabaseMigration.migrateDatabase();
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // set routes
        server.createContext("/", new LoginHandler());
        server.createContext("/static", new Static());
        server.createContext("/admin", new AdminHandler());
        server.createContext("/admin-show-mentors", new AdminShowMentorsHandler());
        server.createContext("/admin-create-mentor", new AdminCreateMentorHandler());
        server.createContext("/mentor", new MentorHandler());
        server.createContext("/student", new StudentHandler());
        server.createContext("/logout", new LogoutHandler());
        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }
}
