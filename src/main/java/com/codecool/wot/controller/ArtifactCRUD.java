package com.codecool.wot.controller;

import com.codecool.wot.dao.ArtifactDAO;
import com.codecool.wot.model.Artifact;
import com.codecool.wot.model.Mentor;
import com.codecool.wot.model.Student;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;

public class ArtifactCRUD {

    private static final ArtifactCRUD INSTANCE = new ArtifactCRUD();

    public static ArtifactCRUD getInstance() {
        return INSTANCE;
    }

    public void addArtifact(HttpExchange httpExchange, Mentor mentor) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            ArtifactDAO.getInstance().add(parseFormData(formData));

            String uriPath = String.format("/mentor/%s/artifacts", mentor.getId().toString());

            httpExchange.getResponseHeaders().set("Location", uriPath);
            httpExchange.sendResponseHeaders(302,-1);
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/mentor-create-artifact.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("mentor", mentor);
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void showArtifacts(HttpExchange httpExchange, Mentor mentor) throws  IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/mentor-show-artifacts.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("artifacts", ArtifactDAO.getInstance().read());
        model.with("mentor", mentor);
        String response = template.render(model);
        System.out.println(response);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();    }

    private Artifact parseFormData(String formData) {
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
        return new Artifact(name, description, Double.valueOf(price));
    }

}
