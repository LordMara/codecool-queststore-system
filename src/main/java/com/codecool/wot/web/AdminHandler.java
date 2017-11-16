package com.codecool.wot.web;

import com.codecool.wot.dao.AdminDAO;
import com.codecool.wot.dao.ClassDAO;
import com.codecool.wot.dao.DatabaseConnection;
import com.codecool.wot.model.Admin;
import com.codecool.wot.tools.Tools;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class AdminHandler implements HttpHandler {
    private ClassDAO classDAO = new ClassDAO(DatabaseConnection.getDBConnection().getConnection());
    private AdminDAO adminDAO = new AdminDAO(DatabaseConnection.getDBConnection().getConnection());
    private Tools tools = new Tools();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }


}
