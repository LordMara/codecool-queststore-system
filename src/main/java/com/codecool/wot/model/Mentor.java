package com.codecool.wot.model;

import java.util.ArrayList;
import java.util.Random;
import com.codecool.wot.dao.MentorDAO;


public class Mentor extends Account{

    private SchoolClass schoolClass;

    public Mentor(String name, String surname, String login, String password) {
        super(name, surname, login, password);
    }

    public void setSchoolClass(SchoolClass schoolClass){
         this.schoolClass = schoolClass;
     }

    public SchoolClass getSchoolClass(){
         return this.schoolClass;
     }

    public String toString(){
        return String.format("Name : %s  | Surname : %s ", this.name, this.surname);
    }

}
