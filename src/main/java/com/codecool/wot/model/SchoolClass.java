package com.codecool.wot.model;

import java.util.ArrayList;

import com.codecool.wot.dao.*;

public class SchoolClass {

    private static Integer lastID = 0;
    private Integer Id;
    private String name;
    private ArrayList<Student> students;
    private ArrayList<Mentor> mentors;

    public SchoolClass(Integer Id, String name) {
        this.Id = Id;
        this.name = name;
    }

    public SchoolClass(String name) {

        this.students = new ArrayList <> ();
        this.mentors = new ArrayList <> ();
        this.name = name;
        generateID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Mentor> getMentors() {
        return mentors;
    }

    public void assignStudent(Student student) {
        this.students.add(student);
    }

    public void assignMentor(Mentor mentor) {
        this.mentors.add(mentor);
    }

    private void generateID() {
        this.Id = ++SchoolClass.lastID;
    }

    public Integer getId() {
        return Id;
    }
}
