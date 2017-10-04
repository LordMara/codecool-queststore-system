package com.codecool.wot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {
    
    Admin admin;

    @BeforeEach
    public void initAdmin() {
        this.admin = new Admin("Jan", "Kowalski", "jkowalski@buziaczek.pl", "jkowal", "haszło", "1");
    }

    @Test
    public void testToString() {
        assertEquals("Name : Jan  | Surname : Kowalski ", this.admin.toString());
    }

    @Test
    public void testNameField() {
        assertEquals("Jan", this.admin.getName());
    }

    @Test
    public void testSurnameField() {
        assertEquals("Kowalski", this.admin.getSurname());
    }

    @Test
    public void testEmailField() {
        assertEquals("jkowalski@buziaczek.pl", this.admin.getEmail());
    }

    @Test
    public void testLoginField() {
        assertEquals("jkowal", this.admin.getLogin());
    }

    @Test
    public void testPasswordField() {
        assertEquals("haszło", this.admin.getPassword());
    }

    @Test
    public void testAdminIsNotNull() {
        assertNotNull(this.admin);
    }



}