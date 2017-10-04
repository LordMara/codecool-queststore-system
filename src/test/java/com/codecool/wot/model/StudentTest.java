package com.codecool.wot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Student student;

    @BeforeEach
    public void initStudent() {
        this.student = new Student("Paprykarz", "Szczeciński", "szczepa@cc.pl",
                                      "papaszcze", "papa", "20", "3");
    }

    @Test
    public void testIsStudentNotNull() {
        assertNotNull(this.student);
    }


    @Nested
    class ClassIDTests {

        @Test
        public void testIsClassIDCorrect() {
            assertEquals("3", student.getClassId());
        }

        @Test
        public void testIsSetClassIDWorking() {
            student.setClassId("5");
            assertEquals("5", student.getClassId());
        }
    }
}