package com.codecool.wot.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DatabaseConnectionTest {

    @Test
    public void testGetDBConnectionReturnsNotNull() {

        assertNotNull(DatabaseConnection.getDBConnection());
    }

    @Test
    public void testGetConnectionReturnsNotNull() {

        assertNotNull(DatabaseConnection.getDBConnection().getConnection());
    }

    @Test
    public void testGetDBConnectionReturnsSingleton() {

        assertEquals(DatabaseConnection.getDBConnection(), DatabaseConnection.getDBConnection());
    }

    @Test
    public void testGetConnectionReturnsSingleton() {

        assertEquals(DatabaseConnection.getDBConnection().getConnection(),
                     DatabaseConnection.getDBConnection().getConnection());
    }

}