package com.codecool.wot.model;

import java.util.ArrayList;
import java.util.Random;
import com.codecool.wot.dao.MentorDAO;


public class Mentor extends Account{

    private SchoolClass schoolClass;

    public Mentor(String name, String surname, String email, String login, String password, ) {
        super(name, surname, email, login, password);
        this.schoolClass = null;
    }

    public void setSchoolClass(SchoolClass schoolClass){
         this.schoolClass = schoolClass;
     }

    public SchoolClass getSchoolClass(){
         return this.schoolClass;
     }


}
