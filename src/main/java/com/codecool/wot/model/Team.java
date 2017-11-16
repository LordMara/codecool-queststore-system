package com.codecool.wot.model;

import java.util.ArrayList;


public class Team {

    String name;
    ArrayList<Student> members;
    Mentor mentor;

    public Team(String newName, ArrayList<Student> newMembers, Mentor newMentor) {
        name = newName;
        members = newMembers;
        mentor = newMentor;
    }

    public String getName() {
        return name;
    }
    public void setName(String newName) {
        name = newName;
    }

    public ArrayList<Student> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Student> newMembers) {
        members = newMembers;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor newMentor) {
        mentor = newMentor;
    }

    public void addStudent(Student student) {
        members.add(student);
    }
    
}
