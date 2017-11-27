package com.codecool.wot.web;

import com.codecool.wot.dao.ClassDAO;
import com.codecool.wot.dao.CookieDAO;
import com.codecool.wot.dao.PersonDAO;
import com.codecool.wot.model.Account;
import com.codecool.wot.model.Admin;
import com.codecool.wot.model.Mentor;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class AdminCreateMentorHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {

            Integer userId = CookieDAO.getInstance().getCookie(cookieStr).getUserId();
            Admin admin = (Admin) PersonDAO.getInstance().getPerson(userId);

            if (admin != null) {

                String method = httpExchange.getRequestMethod();
                if (method.equals("POST")) {
                    InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    String formData = br.readLine();

                    List<String> mentorData = parseLoginFormData(formData);

                    String name = mentorData.get(0);
                    String surname = mentorData.get(1);
                    String email = mentorData.get(2);
                    String login = mentorData.get(3);
                    String password = mentorData.get(4);
                    String confirmedPassword = mentorData.get(5);

                    if (confirmedPassword.equals(password)) {

                        Account newMentor = new Mentor(name, surname, email, login, password);
                        PersonDAO.getInstance().add(newMentor);
                    } else {
                        //KOMUNIKAT O TYM, ZE SIEM NIE U-DAO
                    }
                }

                JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin-create-mentor.html");
                JtwigModel model = JtwigModel.newModel();

                model.with("name", admin.getName());
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

    private List<String> parseLoginFormData(String formData) throws UnsupportedEncodingException {
        List<String> list = new ArrayList<>();
        String name;
        String surname;
        String email;
        String login;
        String password;
        String confirmedPassword;
        String[] pairs = formData.split("&");

        try {
            name = new URLDecoder().decode(pairs[0].split("=")[1], "UTF-8");
            surname = new URLDecoder().decode(pairs[1].split("=")[1], "UTF-8");
            email = new URLDecoder().decode(pairs[2].split("=")[1], "UTF-8");
            login = new URLDecoder().decode(pairs[3].split("=")[1], "UTF-8");
            password = new URLDecoder().decode(pairs[4].split("=")[1], "UTF-8");
            confirmedPassword = new URLDecoder().decode(pairs[5].split("=")[1], "UTF-8");
            list.add(name);
            list.add(surname);
            list.add(email);
            list.add(login);
            list.add(password);
            list.add(confirmedPassword);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return list;
    }


}

