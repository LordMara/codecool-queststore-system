package com.codecool.wot.web;

import com.codecool.wot.dao.*;
import com.codecool.wot.model.Admin;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;



public class AdminHandler extends PersonHandler<Admin>  {

    protected void sendResponse(HttpExchange httpExchange, Admin admin) throws IOException {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin.html");
        JtwigModel model = JtwigModel.newModel();

        model.with("name", admin.getName());
        model.with("classes", ClassDAO.getInstance().read());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
