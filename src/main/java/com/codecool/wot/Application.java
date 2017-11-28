package com.codecool.wot;

import com.codecool.wot.dao.*;

import com.codecool.wot.web.*;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

class Application {
    public static void main(String[] args) throws Exception {
        // migrate database at server start
        DatabaseMigration.migrateDatabase();
        preloadData();
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(9000), 0);

        // set routes
        server.createContext("/", new LoginHandler());
        server.createContext("/static", new Static());
        server.createContext("/admin", new AdminHandler());
        server.createContext("/mentor", new MentorHandler());
        server.createContext("/student", new StudentHandler());
        server.createContext("/logout", new LogoutHandler());
        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }

    private static void preloadData() {
        PersonDAO.getInstance();
        CookieDAO.getInstance();
        ClassDAO.getInstance();
        ArtifactDAO.getInstance();
        QuestDAO.getInstance();
        LevelDAO.getInstance();
        BillDAO.getInstance();
        PersonalArtifactDAO.getInstance();
        WalletDAO.getInstance();
    }
}
