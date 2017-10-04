package com.codecool.wot.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FactoryDAO {

    private Connection connection;



    public Connection loadDB(){

        Path path = Paths.get("src/main/resources/db/queststore.db");

        if (Files.notExists(path)) {
            connection = DatabaseConnection.getDBConnection().getConnection();
            createDataBase();

        }
        else connection = DatabaseConnection.getDBConnection().getConnection();

        return connection;

    }

    private void createDataBase() {

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(loadQuery());
            stmt.close();

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
