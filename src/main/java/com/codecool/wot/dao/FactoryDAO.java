package com.codecool.wot.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FactoryDAO {

    private Connection connection;

    public FactoryDAO(Connection connection) {

        System.out.println("dupa w Factory");

        this.connection = connection;

        File database = new File("src/main/resources/db/queststore.db");

        System.out.println("dupa przed ifem");

        if (!database.exists()) {
            System.out.println("dupa1");
            createDataBase();
        }
    }

    private void createDataBase() {

        try {
            System.out.println("dupa wchodzi do traja");
            Statement stmt = connection.createStatement();
            System.out.println("dupa tworzy stejtmenta");
            stmt.executeUpdate(loadQuery());
            System.out.println("dupa egzekwoje zapytanie");
            stmt.close();
            System.out.println("dupa zamyka stejtmenta");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    private String loadQuery() throws FileNotFoundException {
        return new Scanner(new File("src/main/resources/db/migration/queststore.sql")).useDelimiter("\\Z").next();
    }
}
