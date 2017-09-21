package com.codecool.wot.model;

import java.util.LinkedList;
import com.codecool.wot.dao.*;

public class SchoolClass {

    private String name;
    private LinkedList<Student> students;
    private LinkedList<Mentor> mentors;

    public SchoolClass(String name, ClassDAO dao) {
        this.students = LinkedList<Student>();
        this.mentors = new LinkedList<Mentor>();
        this.name = name;
        dao.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Student> getStudents() {
        return students;
    }

    public LinkedList<Mentor> getMentors() {
        return mentors;
    }

}
