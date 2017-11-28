package com.codecool.wot.controller;

import com.codecool.wot.dao.QuestDAO;
import com.codecool.wot.model.Mentor;
import com.codecool.wot.model.Quest;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;

public class QuestCRUD {
    private static final QuestCRUD INSTANCE = new QuestCRUD();

    public static QuestCRUD getInstance() {
        return INSTANCE;
    }

    public void addQuest(HttpExchange httpExchange, Mentor mentor) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            QuestDAO.getInstance().add(parseFormData(formData));

            String uriPath = String.format("/mentor/%s/quests", mentor.getId().toString());

            httpExchange.getResponseHeaders().set("Location", uriPath);
            httpExchange.sendResponseHeaders(302,-1);
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/mentor-create-quest.html");
        JtwigModel model = JtwigModel.newModel();
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void showQuests(HttpExchange httpExchange, Mentor mentor) throws  IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/mentor-show-quests.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("quests", QuestDAO.getInstance().read());
        model.with("mentor", mentor);
        String response = template.render(model);
        System.out.println(response);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void addCategory(HttpExchange httpExchange, Mentor mentor) throws  IOException {


    }

    private Quest parseFormData(String formData) {
        String name;
        String description;
        String price;
        String[] pairs = formData.split("&");
        try {
            name = new URLDecoder().decode(pairs[0].split("=")[1], "UTF-8");
            description= new URLDecoder().decode(pairs[1].split("=")[1], "UTF-8");
            price = new URLDecoder().decode(pairs[2].split("=")[1], "UTF-8");

        } catch (ArrayIndexOutOfBoundsException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return new Quest(name, description, Double.valueOf(price));
    }


}
