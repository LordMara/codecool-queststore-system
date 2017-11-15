package com.codecool.wot.web;

import com.codecool.wot.dao.AdminDAO;
import com.codecool.wot.model.Admin;
import com.codecool.wot.model.Mentor;
import com.codecool.wot.model.Student;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        URI uri = httpExchange.getRequestURI();
        Map<String, String> actionData = parseURI(uri.getPath());

        for (String action : actionData.keySet()) {
            if (action.equals("login")) {
                login(httpExchange);
            } else if (action.equals("edit")) {
                edit(httpExchange, actionData.get(action));
            }
        }

//        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/login-page.html");
//        JtwigModel model = JtwigModel.newModel();
//        String response = template.render(model);
//
//        httpExchange.sendResponseHeaders(200, response.getBytes().length);
//        OutputStream os = httpExchange.getResponseBody();
//        os.write(response.getBytes());
//        os.close();
    }

    private void index(HttpExchange httpExchange) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/login-page.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("students", Student.getStudents());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void login(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map<String, String> loginData = parseLoginFormData(formData);

            String login = loginData.keySet().get(0);
            String password = loginData.keySet().get(1);
            String path = "";

            Admin admin = aDAO.getByLogin(login);
            Mentor mentor = mDAO.getByLogin(login);
            Student student = sDAO.getByLogin(login);

            if (admin != null && admin.getPassword().equals(password)) {
                cookie(httpExchange);
                path = "";
            }

            if (mentor != null && mentor.getPassword().equals(password)) {
                cookie(httpExchange);
                path = "";
            }
            if (student != null && student.getPassword().equals(password)) {
                cookie(httpExchange);
                path = "";
            }
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate(path);
        JtwigModel model = JtwigModel.newModel();
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void cookie(HttpExchange httpExchange) throws IOException {
        String response = "";

        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        HttpCookie cookie;

        if (cookieStr != null) {
            cookie = HttpCookie.parse(cookieStr).get(0);

        } else {
            cookie = new HttpCookie("sessionId", UUID.randomUUID().toString());

            httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
        }

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
    }

    private Map<String, String> parseURI (String uri) {
        HashMap<String, String> actionData = new HashMap<>();
        String[] pairs = formData.split("/");

        if (pairs.length == 3) {
            actionData.put(pairs[1], pairs[2]);
        } else {
            actionData.put(pairs[1], "");
        }

        return actionData;
    }

    private Map<String, String> parseLoginFormData(String formData) {
        Map<String, String> map = new HashMap<>();
        String login;
        String password;
        String[] pairs = formData.split("&");

        try {
            login = decoder.decode(pairs[0].split("=")[1], "UTF-8");
            password = decoder.decode(pairs[1].split("=")[1], "UTF-8");
            map.put(login, password);
        } catch (ArrayIndexOutOfBoundsException e) {
            return map;
        }


    }
}
