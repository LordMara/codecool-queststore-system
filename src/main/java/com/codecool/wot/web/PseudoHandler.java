package com.codecool.wot.web;

import com.codecool.wot.dao.*;
import com.codecool.wot.model.Student;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class PseudoHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {
            URI uri = httpExchange.getRequestURI();
            Integer userId = CookieDAO.getInstance().getCookie(cookieStr).getUserId();

            Student student = null;
            if (PersonDAO.getInstance().getPerson(userId) instanceof Student) {
                student = (Student)PersonDAO.getInstance().getPerson(userId);
            }

            if (student != null) {
                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student-wallet.html");
                JtwigModel model = JtwigModel.newModel();

                model.with("person", student);
                model.with("name", student.getName());
                model.with("wallets", WalletDAO.getInstance().read());
                model.with("personalArtifact", PersonalArtifactDAO.getInstance().read());
                model.with("bills", BillDAO.getInstance().read());
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
