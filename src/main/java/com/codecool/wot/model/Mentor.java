package com.codecool.wot.model;

import java.util.ArrayList;
import java.util.Random;
import com.codecool.wot.dao.MentorDao;


public class Mentor extends Account{

    private SchoolClass schoolClass;


    public Mentor(){
    }

    public Mentor(String name, String surname, String login, String password){
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        generateId();
        MentorDao.add(this);
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

    public static ArrayList<Mentor> getMentors(){
        return Mentor.mentors;
    }

    public static Mentor getByLogin(String login) throws NullPointerException{
        for (Mentor mentor : Mentor.mentors){
            if (mentor.login.equals(login)){
                return mentor;
            }
        }
        throw new NullPointerException();
    }
}
