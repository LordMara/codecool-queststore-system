package com.codecool.wot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Mentor mentor;

    @BeforeEach
    void initAccount() {
        this.mentor = new Mentor("Jakub", "Janiszewski", "jj@cc.pl",
                                 "Jaja", "jajaja", "3", "10");
    }

    @Test
    void testIsAccountNotNull() {
        assertNotNull(this.mentor);
    }

    @Test
    void testIsGetIDWorking() {
        assertEquals("3", this.mentor.getId());
    }

    @Test
    void testIsGetSchoolClassWorking() {
        assertNull(this.mentor.getSchoolClass());
    }

    @Test
    void testIsToStringWorking() {
        assertEquals("ID: 3, name: Jakub, surname: Janiszewski, email: jj@cc.pl, login: Jaja",
                     this.mentor.toString());
    }

    @Nested
    class NameFieldTests {

        @Test
        void testIsGetNameWorking() {
            assertEquals("Jakub", mentor.getName());
        }

        @Test
        void testIsSetNameWorking() {
            mentor.setName("Kuba");
            assertEquals("Kuba", mentor.getName());
        }
    }

    @Nested
    class SurnameFieldTests {

        @Test
        void testIsGetSurnameWorking() {
            assertEquals("Janiszewski", mentor.getSurname());
        }

        @Test
        void testIsSetSurnameWorking() {
            mentor.setSurname("Kos");
            assertEquals("Kos", mentor.getSurname());
        }
    }

    @Nested
    class EmailFieldTests {

        @Test
        void testIsGetEmailWorking() {
            assertEquals("jj@cc.pl", mentor.getEmail());
        }

        @Test
        void testIsSetEmailWorking() {
            mentor.setEmail("kk@cc.pl");
            assertEquals("kk@cc.pl", mentor.getEmail());
        }
    }

    @Nested
    class LoginFieldTests {

        @Test
        void testIsGetLoginWorking() {
            assertEquals("Jaja", mentor.getLogin());
        }

        @Test
        void testIsSetLoginWorking() {
            mentor.setLogin("Kuba");
            assertEquals("Kuba", mentor.getLogin());
        }
    }

    @Nested
    class PasswordFieldTests {

        @Test
        void testIsGetPasswordWorking() {
            assertEquals("jajaja", mentor.getPassword());
        }

        @Test
        void testIsSetPasswordWorking() {
            mentor.setPassword("janiszewski");
            assertEquals("janiszewski", mentor.getPassword());
        }
    }
}