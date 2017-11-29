package com.codecool.wot.controller;

import com.codecool.wot.dao.ArtifactDAO;
import com.codecool.wot.dao.BillDAO;

import com.codecool.wot.dao.WalletDAO;
import com.codecool.wot.model.Student;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;

public class StoreCRUD {
    private static final StoreCRUD INSTANCE = new StoreCRUD();

    public static StoreCRUD getInstance() {
        return INSTANCE;
    }

    public void store(HttpExchange httpExchange, Student student) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/student-store.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("artifacts", ArtifactDAO.getInstance().read());
        model.with("wallet", WalletDAO.getInstance().getWallet(student));
        model.with("student", student);
        String response = template.render(model);
        System.out.println(response);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

        public void wallet(HttpExchange httpExchange, Student student) throws IOException {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/student-wallet.html");
            JtwigModel model = JtwigModel.newModel();
            model.with("artifacts", ArtifactDAO.getInstance().read());
            model.with("wallet", WalletDAO.getInstance().getWallet(student));
            model.with("bills", BillDAO.getInstance().read());
            String response = template.render(model);
            System.out.println(response);

            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
    }
}
