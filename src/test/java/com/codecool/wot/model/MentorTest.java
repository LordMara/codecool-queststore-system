package com.codecool.wot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MentorTest {

    private Mentor mentor;

    @BeforeEach
    public void initMentor() {
        this.mentor = new Mentor("Karol", "Kos", "kk@cc.pl", "kkos",
                              "kkosak", "10", "2");
    }

    @Test
    public void testIsMentorNotNull() {
        assertNotNull(this.mentor);
    }

    @Test
    public void testIsNameCorrect() {
        assertEquals("Karol", this.mentor.getName());
    }

    @Test
    public void testIsSurnameCorrect() {
        assertEquals("Kos", this.mentor.getSurname());
    }

    @Test
    public void testIsEmailCorrect() {
        assertEquals("kk@cc.pl", this.mentor.getEmail());
    }

    @Test
    public void testIsLoginCorrect() {
        assertEquals("kkos", this.mentor.getLogin());
    }

    @Test
    public void testIsPasswordCorrect() {
        assertEquals("kkosak", this.mentor.getPassword());
    }

    @Test
    public void testIsIDCorrect() {
        assertEquals("10", this.mentor.getId());
    }

    @Test
    public void testIsClassIDCorrect() {
        assertEquals("2", this.mentor.getClassId());
    }

    @Test
    public void testIsSetClassIDWorking() {
        this.mentor.setClassId("33");
        assertEquals("33", this.mentor.getClassId());
    }
}