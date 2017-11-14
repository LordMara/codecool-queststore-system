package com.codecool.wot;

import com.codecool.wot.model.*;
import com.codecool.wot.view.*;
import com.codecool.wot.controller.*;
import com.codecool.wot.web.Static;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.sql.SQLException;


class Application {
    public static void main(String[] args) throws Exception {
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // set routes
//        server.createContext("/hello", new Hello());
        server.createContext("/static", new Static());
        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }
}
