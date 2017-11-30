package com.codecool.wot.controller;

import com.codecool.wot.dao.*;

import com.codecool.wot.model.*;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;

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
        model.with("quests", QuestDAO.getInstance().read());
        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

        public void wallet(HttpExchange httpExchange, Student student) throws IOException {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/student-wallet.html");
            JtwigModel model = JtwigModel.newModel();
            model.with("student", student);
            model.with("artifacts", PersonalArtifactDAO.getInstance().getPersonalArtifacts(student));
            model.with("wallet", WalletDAO.getInstance().getWallet(student));
            model.with("bills", BillDAO.getInstance().getBills(student));
            String response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
    }

    public void buyArfifact(HttpExchange httpExchange, String id, Student student) throws ParseException, IOException {
        Wallet wallet =  WalletDAO.getInstance().getWallet(student);
        Artifact artifact = ArtifactDAO.getInstance().getArtifact(Integer.valueOf(id));

        if(wallet.getBalance() < artifact.getPrice()) {
            sendBalanceError(httpExchange, student);
        }

        PersonalArtifactDAO.getInstance().add(new PersonalArtifact(student.getId(), artifact.getId()));

        String uriPath = String.format("/student/%s/wallet", student.getId().toString());

        httpExchange.getResponseHeaders().set("Location", uriPath);
        httpExchange.sendResponseHeaders(302,-1);

    }

    private void sendBalanceError(HttpExchange httpExchange, Student student) throws IOException {
        String uriPath = String.format("/student/%s/shop", student.getId().toString());

        httpExchange.getResponseHeaders().set("Location", uriPath);
        httpExchange.sendResponseHeaders(302,-1);
    }
}
