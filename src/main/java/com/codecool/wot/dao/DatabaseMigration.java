package com.codecool.wot.dao;

import org.flywaydb.core.Flyway;

public class DatabaseMigration {

    public static void migrateDatabase() {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:sqlite:src/main/resources/db/database.db", null, null);
        flyway.migrate();
    }

}
