package com.codecool.wot.web;

import com.codecool.wot.controller.*;
import com.codecool.wot.dao.*;
import com.codecool.wot.model.Artifact;
import com.codecool.wot.model.Mentor;
import com.codecool.wot.model.Student;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MentorHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {

            URI uri = httpExchange.getRequestURI();
            Integer userId = CookieDAO.getInstance().getCookie(cookieStr).getUserId();

            Mentor mentor = null;
            if (PersonDAO.getInstance().getPerson(userId) instanceof Mentor) {
                mentor = (Mentor)PersonDAO.getInstance().getPerson(userId);
            }

            System.out.println(parseURIToGetId(uri.getPath()));
            if (mentor != null && Integer.toString(userId).equals(parseURIToGetId(uri.getPath()))) {
                sendResponse(httpExchange, mentor);
            } else {
                handleWrongUser(httpExchange);
            }
        } else {
            handleWrongUser(httpExchange);
        }
    }

    private void sendResponse(HttpExchange httpExchange, Mentor mentor) throws IOException {
        StudentCRUD studentCRUD = StudentCRUD.getInstance();
        ArtifactCRUD artifactCRUD = ArtifactCRUD.getInstance();
        QuestCRUD questCRUD = QuestCRUD.getInstance();

        String uri = httpExchange.getRequestURI().toString();
        Map<String, String> actionData = parseURI(uri);
        System.out.println(uri);

        for (String action : actionData.keySet()) {
            if (action.equals("addStudent")) {
                studentCRUD.addStudent(httpExchange, mentor);
            } else if (action.equals("students")) {
                studentCRUD.showStudents(httpExchange, mentor);
            } else if (action.equals("editStudent")) {
                studentCRUD.editStudent(httpExchange, actionData.get(action), mentor);
            } else if (action.equals("removeStudent")) {
                studentCRUD.removeStudent(httpExchange, actionData.get(action), mentor);
            } else if (action.equals("addArtifact")) {
                artifactCRUD.addArtifact(httpExchange, mentor);
            } else if (action.equals("artifacts")) {
                artifactCRUD.showArtifacts(httpExchange, mentor);
            } else if (action.equals("addQuest")) {
                questCRUD.addQuest(httpExchange, mentor);
            } else if (action.equals("quests")) {
                questCRUD.showQuests(httpExchange, mentor);
            }else index(httpExchange, mentor);
        }

    }


    private void index(HttpExchange httpExchange, Mentor mentor) throws  IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor.html");
        JtwigModel model = JtwigModel.newModel();

        model.with("mentor", mentor);
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
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
}
