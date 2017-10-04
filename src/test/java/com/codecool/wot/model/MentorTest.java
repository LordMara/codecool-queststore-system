package com.codecool.wot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class MentorTest {

    private Mentor mentor;

    @BeforeEach
    void initMentor() {
        this.mentor = new Mentor("Karol", "Kos", "kk@cc.pl", "kkos",
                              "kkosak", "10", "2");
    }

    @Test
    void testIsMentorNotNull() {
        assertNotNull(this.mentor);
    }

    @Nested
    @DisplayName("")
    class ClassIDSetterAndGetter {

        @Test
        public void testIsSetClassIDWorking() {
            mentor.setClassId("33");
            assertEquals("33", mentor.getClassId());
        }

        @Test
        public void testIsClassIDCorrect() {
            assertEquals("2", mentor.getClassId());
        }
    }
}