package com.codecool.wot.model;

import com.codecool.wot.dao.StudentDAO;

public class Student extends Account{

     private Integer classId;
//     private Integer teamId;

    public Student(String name, String surname, String email, String login, String password, StudentDAO studentDAO) {
        super(name, surname, email, login, password);

        studentDAO.add(this);
    }

    public Student(Integer ID, String name, String surname, String email, String login, String password,
                   StudentDAO studentDAO, Integer classId) {

        super(name, surname, email, login, password, ID);
        this.classId = classId;
//        this.teamId = teamId;

        studentDAO.add(this);
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
