package com.codecool.wot.model;

import com.codecool.wot.dao.StudentDAO;

import java.lang.NullPointerException;
import java.util.ArrayList;
import java.util.Random;

public class Student extends Account{

     private SchoolClass schoolClass;
     private Team team;
    // private Wallet wallet;
    // private Level level; TODO

    public Student(String name, String surname, String email, String login, String password, StudentDAO studentDAO, SchoolClass schoolClass) {
        super(name, surname, email, login, password);
        this.schoolClass = schoolClass;
        studentDAO.add(this);
    }

    public Student(String name, String surname, String email, String login, String password, Integer ID) {
        super(name, surname, email, login, password, ID);
    }

    private void addToTeam(Team team) {
        if (this.ID.equals()) {
            this.team = team;
        }
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public Team getTeam() {
        return team;
    }

    // public CodecoolClass getSchoolClass(){
    //     return this.schoolClass;
    // }
    //
    // public void setSchoolClass( SchoolClass schoolClass){
    //     this.schoolClass = schoolClass;
    // }

    // public void setTeam(Team team){
    //     this.team = team; TODO
    // }

    // public Team getTeam(){
    //     return this.team; TODO
    // }

    // public Level getLevel(){
    //     return this.level;TODO
    // }


}
