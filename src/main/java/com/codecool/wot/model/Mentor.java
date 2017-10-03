package com.codecool.wot.model;

public class Mentor extends Account{

    private String classId;

    public Mentor(String name, String surname, String email, String login, String password, String ID, String classId) {
        super(name, surname, email, login, password, ID);
        this.classId = classId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
