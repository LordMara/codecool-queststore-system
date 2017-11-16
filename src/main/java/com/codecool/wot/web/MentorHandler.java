package com.codecool.wot.web;

import com.codecool.wot.dao.*;
import com.codecool.wot.model.Mentor;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class MentorHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {
            URI uri = httpExchange.getRequestURI();
            CookieDAO cookieDAO = new CookieDAO(DatabaseConnection.getDBConnection().getConnection());
            Integer userId = cookieDAO.getUserIdBySessionId(cookieStr);
            MentorDAO mentorDAO = new MentorDAO(DatabaseConnection.getDBConnection().getConnection());
            Mentor mentor = mentorDAO.getById(userId);

            if (mentor != null && Integer.toString(userId).equals(parseURIToGetId(uri.getPath()))) {
                String response = String.format("Hello %s %s", mentor.getName(), mentor.getSurname());
                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                handleWrongUser(httpExchange);
            }
        } else {
            handleWrongUser(httpExchange);
        }
    }


    private void handleWrongUser(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().set("Location", "/");
        httpExchange.sendResponseHeaders(302,-1);
    }

    private String parseURIToGetId(String uri) {
        String userIdFromURI = "";
        String[] pairs = uri.split("/");
        userIdFromURI = pairs[2];

        return userIdFromURI;
    }
}
