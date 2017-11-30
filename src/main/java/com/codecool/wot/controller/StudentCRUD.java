package com.codecool.wot.controller;

import com.codecool.wot.dao.BillDAO;
import com.codecool.wot.dao.ClassDAO;
import com.codecool.wot.dao.PersonDAO;
import com.codecool.wot.dao.QuestDAO;
import com.codecool.wot.model.Account;
import com.codecool.wot.model.Bill;
import com.codecool.wot.model.Mentor;
import com.codecool.wot.model.Student;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.text.ParseException;

public class StudentCRUD {

    private static final StudentCRUD INSTANCE = new StudentCRUD();

    public static StudentCRUD getInstance() {
        return INSTANCE;
    }


    public void addStudent(HttpExchange httpExchange, Mentor mentor) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            PersonDAO.getInstance().add(parseFormData(formData));

            String uriPath = String.format("/mentor/%s/students",mentor.getId().toString());

            httpExchange.getResponseHeaders().set("Location", uriPath);
            httpExchange.sendResponseHeaders(302,-1);
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/mentor-create-student.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("mentor", mentor);
        model.with("classes", ClassDAO.getInstance().read());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void editStudent(HttpExchange httpExchange, String id, Mentor mentor) throws IOException  {

        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            PersonDAO.getInstance().update(editFromForm(formData, id));

            String uriPath = String.format("/mentor/%s/students", mentor.getId().toString());

            httpExchange.getResponseHeaders().set("Location", uriPath);
            httpExchange.sendResponseHeaders(302,-1);
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/mentor-edit-student.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("mentor", mentor);
        model.with("student", PersonDAO.getInstance().getPerson(Integer.valueOf(id)));
        model.with("classes", ClassDAO.getInstance().read());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void removeStudent(HttpExchange httpExchange, String id, Mentor mentor) throws IOException  {
        PersonDAO dao = PersonDAO.getInstance();
        Account student = dao.getPerson(Integer.valueOf(id));
        dao.remove(student);

        String uriPath = String.format("/mentor/%s/studentSurname",mentor.getId().toString());

        httpExchange.getResponseHeaders().set("Location", uriPath);
        httpExchange.sendResponseHeaders(302,-1);
    }

    public void showStudent(HttpExchange httpExchange, String id, Mentor mentor) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            newBills(formData,Integer.valueOf(id));
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/mentor-show-student.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("quests", QuestDAO.getInstance().read());
        model.with("mentor", mentor);
        model.with("student", PersonDAO.getInstance().getPerson(Integer.valueOf(id)));
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void showStudents(HttpExchange httpExchange, Mentor mentor) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/mentor-show-students.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("persons", PersonDAO.getInstance().getStudents());
        model.with("mentor", mentor);
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Student parseFormData(String formData) {
        String name;
        String surname;
        String email;
        String login;
        String password;

        String[] pairs = formData.split("&");

        try {
            name = new URLDecoder().decode(pairs[0].split("=")[1], "UTF-8");
            surname = new URLDecoder().decode(pairs[1].split("=")[1], "UTF-8");
            email = new URLDecoder().decode(pairs[2].split("=")[1], "UTF-8");
            login = new URLDecoder().decode(pairs[3].split("=")[1], "UTF-8");
            password = new URLDecoder().decode(pairs[4].split("=")[1], "UTF-8");


        } catch (ArrayIndexOutOfBoundsException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return new Student(name, surname, email, login, password);
    }

    private void newBills(String formData, Integer personId) {
        String[] pairs = formData.split("&");

        try {
            for (int i = 0; i < pairs.length - 1; i++) {
                String strQuestId = new URLDecoder().decode(pairs[i].split("=")[0], "UTF-8");
                Integer questId = Integer.valueOf(strQuestId);
                Bill bill = new Bill(personId, questId);
                bill.setStatus();
                BillDAO.getInstance().add(bill);
            }

        } catch (ArrayIndexOutOfBoundsException | UnsupportedEncodingException | ParseException e) {
            e.printStackTrace();
        }
    }

    private Account editFromForm(String formData, String id) {


        Account student = PersonDAO.getInstance().getPerson(Integer.valueOf(id));
        String[] pairs = formData.split("&");

        try {
            student.setName(new URLDecoder().decode(pairs[0].split("=")[1], "UTF-8"));
            student.setSurname(new URLDecoder().decode(pairs[1].split("=")[1], "UTF-8"));
            student.setEmail(new URLDecoder().decode(pairs[2].split("=")[1], "UTF-8"));
            student.setLogin(new URLDecoder().decode(pairs[3].split("=")[1], "UTF-8"));
            student.setPassword(new URLDecoder().decode(pairs[4].split("=")[1], "UTF-8"));
        } catch (ArrayIndexOutOfBoundsException | UnsupportedEncodingException e) {
            return null;
        }
        return student;
    }
}
