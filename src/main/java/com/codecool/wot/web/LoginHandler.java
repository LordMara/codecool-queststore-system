package com.codecool.wot.web;

import com.codecool.wot.dao.AdminDAO;
import com.codecool.wot.dao.DatabaseConnection;
import com.codecool.wot.dao.MentorDAO;
import com.codecool.wot.dao.StudentDAO;
import com.codecool.wot.model.Admin;
import com.codecool.wot.model.Mentor;
import com.codecool.wot.model.Student;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

public class LoginHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        URI uri = httpExchange.getRequestURI();

        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {
            // ACTION
            System.out.println("dupa");
        } else {
            login(httpExchange);
        }
//
//        Map<String, String> actionData = parseURI(uri.getPath());
//
//        for (String action : actionData.keySet()) {
//            if (action.equals()) {
//            }
//        }
    }

//    private void index(HttpExchange httpExchange) throws IOException {
//        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/login-page.html");
//        JtwigModel model = JtwigModel.newModel();
//        String response = template.render(model);
//
//        httpExchange.sendResponseHeaders(200, response.length());
//        OutputStream os = httpExchange.getResponseBody();
//        os.write(response.getBytes());
//        os.close();
//    }

    private void login(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        String redirect = "";

        AdminDAO adminDAO = new AdminDAO(DatabaseConnection.getDBConnection().getConnection());
        MentorDAO mentorDAO = new MentorDAO(DatabaseConnection.getDBConnection().getConnection());
        StudentDAO studentDAO = new StudentDAO(DatabaseConnection.getDBConnection().getConnection());

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            List<String> loginData = parseLoginFormData(formData);

            String login = loginData.get(0);
            String password = loginData.get(1);

            Admin admin = adminDAO.getByLogin(login);
            Mentor mentor = mentorDAO.getByLogin(login);
            Student student = studentDAO.getByLogin(login);

            if (admin != null && admin.getPassword().equals(password)) {
                cookie(httpExchange);
                redirect = "<meta http-equiv=\"refresh\" content=\"0; url=/admin\" />";
            }

            if (mentor != null && mentor.getPassword().equals(password)) {
                cookie(httpExchange);
                redirect = "<meta http-equiv=\"refresh\" content=\"0; url=/mentor\" />";
            }
            if (student != null && student.getPassword().equals(password)) {
                cookie(httpExchange);
                redirect = "<meta http-equiv=\"refresh\" content=\"0; url=/student\" />";
            }
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/login-page.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("redirect", redirect);
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void cookie(HttpExchange httpExchange) throws IOException {
        HttpCookie cookie = new HttpCookie("sessionId", UUID.randomUUID().toString());
        httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
    }

//    private Map<String, String> parseURI (String uri) {
//        HashMap<String, String> actionData = new HashMap<>();
//        String[] pairs = uri.split("/");
//
//        if (pairs.length == 3) {
//            actionData.put(pairs[1], pairs[2]);
//        } else {
//            actionData.put(pairs[1], "");
//        }
//
//        return actionData;
//    }

    private List<String> parseLoginFormData(String formData) throws UnsupportedEncodingException {
        List<String> list = new ArrayList<>();
        String login;
        String password;
        String[] pairs = formData.split("&");

        try {
            login = new URLDecoder().decode(pairs[0].split("=")[1], "UTF-8");
            password = new URLDecoder().decode(pairs[1].split("=")[1], "UTF-8");
            list.add(login);
            list.add(password);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return list;
    }
}
