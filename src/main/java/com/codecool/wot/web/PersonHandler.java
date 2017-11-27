package com.codecool.wot.web;

import com.codecool.wot.dao.CookieDAO;
import com.codecool.wot.dao.DatabaseConnection;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;

public abstract class PersonHandler<T> implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {
            URI uri = httpExchange.getRequestURI();
            CookieDAO cookieDAO = new CookieDAO(DatabaseConnection.getDBConnection().getConnection());
            Integer userId = cookieDAO.getUserIdBySessionId(cookieStr);
            PersonDAO personDAO = new PersonDAO(DatabaseConnection.getDBConnection().getConnection());
            T user = PersonDAO.getById(userId);

            if (person != null && Integer.toString(userId).equals(parseURIToGetId(uri.getPath()))) {
                sendResponse();
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

    protected abstract void sendResponse();
}
