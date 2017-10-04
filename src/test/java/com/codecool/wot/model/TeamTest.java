package com.codecool.wot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    private final String name = "teamname";
    private final ArrayList<Student> students = new ArrayList<>();
    private final Mentor mentor = new Mentor("1", "2", "3", "4", "5", "6", "7");
    private Team team;

    @BeforeEach
    void prepareTestEnvironment() {
        setTeamThroughSetters();
    }

    private void setTeamThroughConstructor() {
        team = new Team(this.name, this.students, this.mentor);
    }

    private void setTeamThroughSetters() {
        team = new Team(name, students, mentor);

        team.setName(name);
        team.setMembers(students);
        team.setMentor(mentor);
    }


    @Nested
    class TestSettersAndGetters {

        @BeforeEach
        void prepareTestEnvironment() {
            setTeamThroughSetters();
        }

        @Nested
        class TestGetters {

            @Test
            void testNameGetter() {
                assertEquals(name, team.getName());
            }

            @Test
            void testNewMembersGetter() {
                assertEquals(students, team.getMembers());
            }

            @Test
            void testMentorGetter() {
                assertEquals(mentor, team.getMentor());
            }
        }

        @Nested
        class TestSetters {

            @Test
            void testNameSetter() {
                String expected = "expected";
                team.setName(expected);

                assertEquals(expected, team.getName());
            }

            @Test
            void testNewMembersSetter() {
                ArrayList<Student> expected = new ArrayList<>();
                Student student = new Student("1", "2", "2", "2", "2", "2", "2");

                expected.add(student);
                team.setMembers(expected);

                assertEquals(expected, team.getMembers());
            }

            @Test
            void testMentorSetter() {
                Mentor expected = new Mentor( "3", "3", "3", "3", "3", "3", "3");
                team.setMentor(expected);

                assertEquals(expected, team.getMentor());
            }
        }
    }

    @Nested
    class TestConstructor {

        @BeforeEach
        void prepareTestEnvironment() {
            setTeamThroughConstructor();
        }

        @Nested
        class Fields {

            @Test
            void testNameField() {
                assertEquals(name, team.getName());
            }

            @Test
            void testNewMembersField() {
                assertEquals(students, team.getMembers());
            }

            @Test
            void testMentorField() {
                assertEquals(mentor, team.getMentor());
            }
        }
    }

    @Test
    void testAddNewStudent() {

        Student student = new Student("100", "001", "100", "001", "100", "2", "3");

        ArrayList<Student> actual = new ArrayList<>();
        actual.add(student);

        this.team.addStudent(student);
        ArrayList<Student> expected = this.team.getMembers();

        assertEquals(expected, actual);
    }
}