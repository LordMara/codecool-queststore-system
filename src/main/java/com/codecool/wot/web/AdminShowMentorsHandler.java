package com.codecool.wot.web;

import com.codecool.wot.dao.ClassDAO;
import com.codecool.wot.dao.CookieDAO;
import com.codecool.wot.dao.PersonDAO;
import com.codecool.wot.model.Admin;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;

public class AdminShowMentorsHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {

            Integer userId = CookieDAO.getInstance().getCookie(cookieStr).getUserId();
            Admin admin = (Admin) PersonDAO.getInstance().getPerson(userId);

                if (admin != null) {
                    JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin-show-mentors.html");
                    JtwigModel model = JtwigModel.newModel();

                    model.with("name", admin.getName());
                    model.with("classes", ClassDAO.getInstance().read());
                    model.with("persons", PersonDAO.getInstance().read());
                    String response = template.render(model);

                    httpExchange.sendResponseHeaders(200, response.getBytes().length);
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

}
