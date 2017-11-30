package com.codecool.wot.web;

import com.codecool.wot.dao.CookieDAO;
import com.codecool.wot.model.Cookie;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class LogoutHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        Cookie coookie = CookieDAO.getInstance().getCookie(cookieStr);
        CookieDAO.getInstance().remove(coookie);

        String cookie = "sessionId=\"\"; max-age=0;";
        httpExchange.getResponseHeaders().add("Set-Cookie",cookie);

        httpExchange.getResponseHeaders().set("Location", "/");
        httpExchange.sendResponseHeaders(302, -1);
    }

}


