package com.codecool.wot.model;

import java.util.ArrayList;

public class SchoolClass {

    Mentor mentor;
    ArrayList<Student> students;
    ArrayList<Team> teams;
    String name;
    public Class(Mentor newMentor, ArrayList<Student> newStudents, ArrayList<Team> newTeams, String newName) {

        mentor = newMentor;
        students = newStudents;
        teams = newTeams;
        name = newName;
    }

    public Mentor getMentor() {
        return mentor;
    }


    public void setMentor(Mentor newMentor) {
        mentor = newMentor;
    }


    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> newStudents) {
        students = newStudents;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> newTeams) {
        teams = newTeams;
    }

    public void addTeam(Team newTeam) {
        teams.add(newTeam);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }
}
