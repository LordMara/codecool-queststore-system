package com.codecool.wot.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FactoryDAO {

    public FactoryDAO() {

        File database = new File("src/main/resources/db/queststore.db");

        if (!database.exists()) {
            createDataBase();
        }
    }

    private void createDataBase() {

        try {
            Connection connection = DatabaseConnection.getDBConnection().getConnection();
            Statement stmt = connection.createStatement();

            stmt.executeUpdate(loadQuery());
            stmt.close();
            connection.close();

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
