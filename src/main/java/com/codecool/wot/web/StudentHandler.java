package com.codecool.wot.web;

import com.codecool.wot.controller.StoreCRUD;
import com.codecool.wot.dao.*;
import com.codecool.wot.model.Student;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class StudentHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {
            URI uri = httpExchange.getRequestURI();
            Integer userId = CookieDAO.getInstance().getCookie(cookieStr).getUserId();

            Student student = null;
            if (PersonDAO.getInstance().getPerson(userId) instanceof Student) {
                student = (Student)PersonDAO.getInstance().getPerson(userId);
            }

            if (student != null && Integer.toString(userId).equals(parseURIToGetId(uri.getPath()))) {
                sendResponse(httpExchange, student);
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
        try {
            userIdFromURI = pairs[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return userIdFromURI;
    }

    private void sendResponse(HttpExchange httpExchange, Student student) throws IOException {
        StoreCRUD storeCRUD = StoreCRUD.getInstance();

        String uri = httpExchange.getRequestURI().toString();
        Map<String, String> actionData = parseURI(uri);
        System.out.println(uri);

        for (String action : actionData.keySet()) {
            if (action.equals("store")) {
                storeCRUD.store(httpExchange, student);
            } else if (action.equals("wallet")) {
                storeCRUD.wallet(httpExchange, student);
            } else if (action.equals("buyArtifact")) {
                try {
                    storeCRUD.buyArfifact(httpExchange, actionData.get(action), student);
                } catch (ParseException e) {
                    e.printStackTrace();
                    index(httpExchange,student);
                }
            }else {
                index(httpExchange, student);
            }
        }

    }

    private Map<String, String> parseURI (String uri) {
        Map<String, String> actionData = new HashMap<>();
        String[] pairs = uri.split("/");

        if (pairs.length == 5) {
            actionData.put(pairs[3], pairs[4]);
        } else if (pairs.length == 4) {
            actionData.put(pairs[3], "");
        } else {
            actionData.put("", "");
        }

        return actionData;
    }

    private void index(HttpExchange httpExchange, Student student) throws IOException {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student.html");
        JtwigModel model = JtwigModel.newModel();

        model.with("student", student);
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }


}
