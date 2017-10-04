package com.codecool.wot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Student student;

    @BeforeEach
    public void initStudent() {
        this.student = new Student("Paprykarz", "Szczeciński", "szczepa@cc.pl",
                                      "papaszcze", "papa", "20", "3");
    }

    @Test
    public void testIsNameNotNull() {
        assertNotNull(this.student.getName());
    }

    @Test
    public void testIsStudentNotNull() {
        assertNotNull(this.student);
    }

    @Test
    public void testIsNameCorrect() {
        assertEquals("Paprykarz", this.student.getName());
    }

    @Test
    public void testIsEmailCorrect() {
        assertEquals("szczepa@cc.pl", this.student.getEmail());
    }

    @Test
    public void testIsLoginCorrect() {
        assertEquals("papaszcze", this.student.getLogin());
    }

    @Test
    public void testIsPasswordCorrect() {
        assertEquals("papa", this.student.getPassword());
    }

    @Test
    public void testIsIDCorrect() {
        assertEquals("20", this.student.getId());
    }

    @Test
    public void testIsClassIDCorrect() {
        assertEquals("3", this.student.getClassId());
    }

    @Test
    public void testIsToStringCorrect() {
        assertEquals("ID: 20, name: Paprykarz, surname: Szczeciński, email: szczepa@cc.pl, login: papaszcze",
                     this.student.toString());
    }

    @Test
    public void testIsSetClassIDWorking() {
        this.student.setClassId("5");
        assertEquals("5", this.student.getClassId());
    }
}