package com.codecool.wot.model;

import java.util.ArrayList;
import java.util.LinkedList;
import com.codecool.wot.dao.*;

public class SchoolClass {
    private String name;
    private LinkedList<Student> students;
    private ArrayList<Mentor> mentors;

    public SchoolClass(String name) {
        this.students = StudentDAO.getByClass();
        this.mentors = MentorDAO.getByClass();
        this.name = name;
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

}
