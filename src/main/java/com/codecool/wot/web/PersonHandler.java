package com.codecool.wot.web;

import com.codecool.wot.dao.CookieDAO;
import com.codecool.wot.dao.PersonDAO;
import com.codecool.wot.model.Account;
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
            Integer userId = CookieDAO.getInstance().getCookie(cookieStr).getUserId();
            T user = (T)PersonDAO.getInstance().getPerson(userId);

            if (user != null && Integer.toString(userId).equals(getIdFromURI(uri.getPath()))) {
                sendResponse();
            } else {
                handleWrongUser(httpExchange);
            }
        } else {
            handleWrongUser(httpExchange);
        }
    }

    protected void handleWrongUser(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().set("Location", "/");
        httpExchange.sendResponseHeaders(302,-1);
    }

    protected String getIdFromURI(String uri) {
        String userIdFromURI = "";
        String[] pairs = uri.split("/");
        userIdFromURI = pairs[2];

        return userIdFromURI;
    }

    protected abstract void sendResponse();
}
