package com.codecool.wot.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.util.UUID;

public class Cookie implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";

        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        HttpCookie cookie;

        if (cookieStr != null) {
            cookie = HttpCookie.parse(cookieStr).get(0);

        } else {
            cookie = new HttpCookie("sessionId", UUID.randomUUID().toString());

            httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
        }

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
