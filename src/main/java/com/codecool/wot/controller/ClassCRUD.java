package com.codecool.wot.controller;

import com.codecool.wot.dao.ClassDAO;
import com.codecool.wot.dao.PersonDAO;
import com.codecool.wot.model.Admin;
import com.codecool.wot.model.SchoolClass;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;


public class ClassCRUD {

    private static final ClassCRUD INSTANCE = new ClassCRUD();

    public static ClassCRUD getInstance() {
        return INSTANCE;
    }


    public void addClass(HttpExchange httpExchange, Admin admin) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            ClassDAO.getInstance().add(parseFormData(formData));

            String uriPath = String.format("/admin/%s",admin.getId().toString());

            httpExchange.getResponseHeaders().set("Location", uriPath);
            httpExchange.sendResponseHeaders(302,-1);
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/admin-create-class.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("admin", admin);
        model.with("classes", ClassDAO.getInstance().read());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void showClass(HttpExchange httpExchange, String classId, Admin admin) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/admin-show-class.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("classes", ClassDAO.getInstance().read());
        model.with("thisClass", ClassDAO.getInstance().getClass(Integer.valueOf(classId)));
        model.with("admin", admin);
        model.with("students", ClassDAO.getInstance().getStudents(Integer.valueOf(classId)));
        model.with("mentors", ClassDAO.getInstance().getMentos(Integer.valueOf(classId)));
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private SchoolClass parseFormData(String formData) {
        String name;

        String[] pairs = formData.split("&");

        try {
            name = new URLDecoder().decode(pairs[0].split("=")[1], "UTF-8");

        } catch (ArrayIndexOutOfBoundsException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return new SchoolClass(name);
    }
}
