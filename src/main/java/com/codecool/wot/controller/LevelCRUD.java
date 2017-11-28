package com.codecool.wot.controller;

import com.codecool.wot.dao.ClassDAO;
import com.codecool.wot.dao.LevelDAO;
import com.codecool.wot.model.Admin;
import com.codecool.wot.model.Level;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;


public class LevelCRUD {

    private static final LevelCRUD INSTANCE = new LevelCRUD();

    public static LevelCRUD getInstance() {
        return INSTANCE;
    }

    public void addLevel(HttpExchange httpExchange, Admin admin) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            LevelDAO.getInstance().add(parseFormData(formData));

            String uriPath = String.format("/admin/%s",admin.getId().toString());

            httpExchange.getResponseHeaders().set("Location", uriPath);
            httpExchange.sendResponseHeaders(302,-1);
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/admin-create-lvl.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("classes", ClassDAO.getInstance().read());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void showLvl(HttpExchange httpExchange, Admin admin) throws  IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/admin-show-lvls.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("classes", ClassDAO.getInstance().read());
        model.with("admin",admin);
        model.with("levels", LevelDAO.getInstance().read());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();    }

    private Level parseFormData(String formData) {
        String name;
        String description;
        String coolcoinValue;
        String[] pairs = formData.split("&");
        try {
            name = new URLDecoder().decode(pairs[0].split("=")[1], "UTF-8");
            coolcoinValue = new URLDecoder().decode(pairs[1].split("=")[1], "UTF-8");
            description= new URLDecoder().decode(pairs[2].split("=")[1], "UTF-8");


        } catch (ArrayIndexOutOfBoundsException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        Level level= new Level(name, description, Double.valueOf(coolcoinValue));
        return level ;
    }


}
