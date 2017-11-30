package com.codecool.wot.controller;

import com.codecool.wot.dao.ClassDAO;
import com.codecool.wot.dao.PersonDAO;
import com.codecool.wot.model.*;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.stream.Stream;

public class MentorCRUD {

    private static final MentorCRUD INSTANCE = new MentorCRUD();

    public static MentorCRUD getInstance() {
        return INSTANCE;
    }

    public void index(HttpExchange httpExchange, Admin admin) throws IOException {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin.html");
        JtwigModel model = JtwigModel.newModel();

        model.with("admin", admin);
        model.with("classes", ClassDAO.getInstance().read());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void addMentor(HttpExchange httpExchange, Admin admin) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            PersonDAO.getInstance().add(parseFormData(formData));

            String uriPath = String.format("/admin/%s/mentors",admin.getId().toString());

            httpExchange.getResponseHeaders().set("Location", uriPath);
            httpExchange.sendResponseHeaders(302,-1);
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/admin-create-mentor.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("admin", admin);
        model.with("classes", ClassDAO.getInstance().read());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void editMentor(HttpExchange httpExchange, String id, Admin admin) throws IOException  {

        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            PersonDAO.getInstance().update(editFromForm(formData, id));

            String uriPath = String.format("/admin/%s/mentors",admin.getId().toString());

            httpExchange.getResponseHeaders().set("Location", uriPath);
            httpExchange.sendResponseHeaders(302,-1);
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/admin-edit-mentor.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("admin", admin);
        model.with("mentor", PersonDAO.getInstance().getPerson(Integer.valueOf(id)));
        model.with("classes", ClassDAO.getInstance().read());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void removeMentor(HttpExchange httpExchange, String id, Admin admin) throws IOException  {
        PersonDAO dao = PersonDAO.getInstance();

        Account mentor = dao.getPerson(Integer.valueOf(id));
        dao.remove(mentor); //TODO


        String uriPath = String.format("/admin/%s/mentors",admin.getId().toString());

        httpExchange.getResponseHeaders().set("Location", uriPath);
        httpExchange.sendResponseHeaders(302,-1);
    }

    public void showMentors(HttpExchange httpExchange, Admin admin) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/admin-show-mentors.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("persons", PersonDAO.getInstance().getMentors());
        model.with("admin", admin);
        model.with("classes", ClassDAO.getInstance().read());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Mentor parseFormData(String formData) {
        String name;
        String surname;
        String email;
        String login;
        String password;
        String classId;

        Mentor mentor = null;

        String[] pairs = formData.split("&");

        try {
            name = new URLDecoder().decode(pairs[0].split("=")[1], "UTF-8");
            surname = new URLDecoder().decode(pairs[1].split("=")[1], "UTF-8");
            email = new URLDecoder().decode(pairs[2].split("=")[1], "UTF-8");
            login = new URLDecoder().decode(pairs[3].split("=")[1], "UTF-8");
            password = new URLDecoder().decode(pairs[4].split("=")[1], "UTF-8");
            classId = new URLDecoder().decode(pairs[6].split("=")[1], "UTF-8");

            mentor = new Mentor(name, surname, email, login, password);

            SchoolClass schoolClass =  ClassDAO.getInstance().getClass(Integer.valueOf(classId));
            schoolClass.assignPerson(mentor);


        } catch (ArrayIndexOutOfBoundsException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return mentor;
    }

    private Mentor editFromForm(String formData, String id) {
        System.out.println(formData);

        String classId;

        Mentor mentor = (Mentor) PersonDAO.getInstance().getPerson(Integer.valueOf(id));

        String[] pairs = formData.split("&");

        try {

            mentor.setName(new URLDecoder().decode(pairs[0].split("=")[1], "UTF-8"));
            mentor.setSurname(new URLDecoder().decode(pairs[1].split("=")[1], "UTF-8"));
            mentor.setEmail(new URLDecoder().decode(pairs[2].split("=")[1], "UTF-8"));
            mentor.setLogin(new URLDecoder().decode(pairs[3].split("=")[1], "UTF-8"));
            mentor.setPassword(new URLDecoder().decode(pairs[4].split("=")[1], "UTF-8"));

            classId = new URLDecoder().decode(pairs[6].split("=")[1], "UTF-8");

            ClassDAO.getInstance().getClass(mentor).removePerson(mentor);

            SchoolClass schoolClass =  ClassDAO.getInstance().getClass(Integer.valueOf(classId));
            schoolClass.assignPerson(mentor);

        } catch (ArrayIndexOutOfBoundsException | UnsupportedEncodingException e) {
            return null;
        }
        return mentor;
    }

}
