package com.codecool.wot.web;

import com.codecool.wot.model.Student;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;

public class StudentHandler extends PersonHandler<Student> {

  protected void sendResponse(HttpExchange httpExchange, Student student ) throws IOException {
      JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student.html");
      JtwigModel model = JtwigModel.newModel();

      model.with("name", student.getName());
      String response = template.render(model);

      httpExchange.sendResponseHeaders(200, response.getBytes().length);
      OutputStream os = httpExchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
  }

}
