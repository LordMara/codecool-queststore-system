package com.codecool.wot.model;

public class Student extends Account{


     private Integer classId;
//     private Integer teamId;

    public Student(String name, String surname, String email, String login, String password) {
        super(name, surname, email, login, password);
    }

    public Student( String name, String surname, String email, String login, String password,Integer ID, Integer classId) {

        super(name, surname, email, login, password, ID);
        this.classId = classId;
//        this.teamId = teamId;

    }

    public Integer getClassId() {
        return this.classId;
    }


    public void setClassId(Integer classId) {
        this.classId = classId;
    }
//
//    public Integer getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(Integer teamId) {
//        this.teamId = teamId;
//    }

}
