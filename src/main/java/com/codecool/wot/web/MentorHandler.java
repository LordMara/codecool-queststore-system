package com.codecool.wot.web;

import com.codecool.wot.dao.*;
import com.codecool.wot.model.Mentor;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class MentorHandler extends PersonHandler<Mentor> {


    @Override
    protected void sendResponse(HttpExchange httpExchange, Mentor mentor) throws IOException {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor.html");
        JtwigModel model = JtwigModel.newModel();

        model.with("name", mentor.getName());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
