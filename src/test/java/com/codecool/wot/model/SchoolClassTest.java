package com.codecool.wot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolClassTest {
    SchoolClass schoolClass;

    @BeforeEach
    public void initSchoolClass() {
        this.schoolClass = new SchoolClass("1", "Klasa");
    }

    @Test
    public void testSchoolClassIsNotNull() {
        assertNotNull(this.schoolClass);
    }

    @Test
    public void testGetId() {
        assertEquals("1", this.schoolClass.getId());
    }

    @Nested
    class testName {
        SchoolClass schoolClass;

        @BeforeEach
        public void initClass() {
            this.schoolClass = new SchoolClass(null, "wartość");
        }

        @Test
        public void testSetName() {
            this.schoolClass.setName("nazwa");
            assertEquals("nazwa", this.schoolClass.getName());
        }

        @Test
        public void testGetName() {
            assertEquals("wartość", this.schoolClass.getName());
        }
    }

}