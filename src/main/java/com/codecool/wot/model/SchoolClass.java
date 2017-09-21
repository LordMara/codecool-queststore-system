package com.codecool.wot.model;

import java.util.ArrayList;
import com.codecool.wot.dao.*;

public class SchoolClass {

    private String name;
    private ArrayList<Student> students;
    private ArrayList<Mentor> mentors;

    public SchoolClass(String name, ClassDAO dao) {
        this.students = new ArrayList <> ();
        this.mentors = new ArrayList <> ();
        this.name = name;
        dao.add(this);
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
