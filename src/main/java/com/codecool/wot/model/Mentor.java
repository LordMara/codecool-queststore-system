package com.codecool.wot.model;

public class Mentor extends Account{

    private Integer classId;

    public Mentor(String name, String surname, String email, String login, String password) {
        super(name, surname, email, login, password);
    }

    public Mentor(String name, String surname, String email, String login, String password, Integer ID, Integer classId) {
        super(name, surname, email, login, password, ID);
        this.classId = classId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
