package com.codecool.wot.model;

import com.codecool.wot.dao.StudentDAO;

public class Student extends Account{

     private Integer codecoolClassId;
     private Integer teamId;

    public Student(String name, String surname, String email, String login, String password, StudentDAO studentDAO) {
        super(name, surname, email, login, password);

        studentDAO.add(this);
    }

    public Student(Integer ID, String name, String surname, String email, String login, String password,
                   StudentDAO studentDAO, Integer codecoolClassId, Integer teamId) {

        super(name, surname, email, login, password);
        this.ID = ID;
        this.codecoolClassId = codecoolClassId;
        this.teamId = teamId;

        studentDAO.add(this);
    }

    public Integer getCodecoolClassId() {
        return codecoolClassId;
    }

    public void setCodecoolClassId(Integer codecoolClassId) {
        this.codecoolClassId = codecoolClassId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
