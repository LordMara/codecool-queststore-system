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
    public void testAdminIsNotNull() {
        assertNotNull(this.admin);
    }

    @Test
    public void testToString() {
        assertEquals("Name : Jan  | Surname : Kowalski ", this.admin.toString());
    }
    
}