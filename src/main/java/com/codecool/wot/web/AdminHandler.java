package com.codecool.wot.web;

import com.codecool.wot.controller.ClassCRUD;
import com.codecool.wot.controller.LevelCRUD;
import com.codecool.wot.dao.*;
import com.codecool.wot.controller.MentorCRUD;
import com.codecool.wot.model.Admin;
import com.codecool.wot.model.Level;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


public class AdminHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");

        if (cookieStr != null) {
            URI uri = httpExchange.getRequestURI();
            Integer userId = CookieDAO.getInstance().getCookie(cookieStr).getUserId();

            Admin admin = null;
            if (PersonDAO.getInstance().getPerson(userId) instanceof Admin) {
                admin = (Admin)PersonDAO.getInstance().getPerson(userId);
            }


            if (admin != null && Integer.toString(userId).equals(parseURIToGetId(uri.getPath()))) {
               sendResponse(httpExchange, admin);
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

    protected void sendResponse(HttpExchange httpExchange, Admin admin) throws IOException {
        MentorCRUD mentorCRUD =  MentorCRUD.getInstance();
        ClassCRUD classCRUD = ClassCRUD.getInstance();
        LevelCRUD levelCRUD = LevelCRUD.getInstance();

        String uri = httpExchange.getRequestURI().toString();
        Map<String, String> actionData = parseURI(uri);
        System.out.println(uri);

        for (String action : actionData.keySet()) {
            if (action.equals("addMentor")) {
                mentorCRUD.addMentor(httpExchange, admin);
            } else if (action.equals("addClass")) {
                classCRUD.addClass(httpExchange, admin);
            } else if (action.equals("addLevel")) {
                levelCRUD.addLevel(httpExchange, admin);
            } else if (action.equals("editMentor")) {
                mentorCRUD.editMentor(httpExchange, actionData.get(action), admin);
            } else if (action.equals("removeMentor")) {
                mentorCRUD.removeMentor(httpExchange, actionData.get(action), admin);
            } else if (action.equals("mentors")) {
                mentorCRUD.showMentors(httpExchange, admin);
            } else if (action.equals("levels")) {
                levelCRUD.showLvl(httpExchange,admin);
            } else {
                mentorCRUD.index(httpExchange, admin);
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

}
