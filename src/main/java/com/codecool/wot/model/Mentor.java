package com.codecool.wot.model;

import java.util.ArrayList;
import java.util.Random;


public class Mentor extends Account{

    // private CodecoolClass codecoolClass;

    private static ArrayList<Mentor> mentors = new ArrayList<>();


    public Mentor(){
    }

    public Mentor(String name, String surname, String login, String password){
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        generateId();
        Mentor.mentors.add(this);
    }

    // public setCodecoolClass(CodecoolClass codecoolClass){
    //     this.codecoolClass = codecoolClass;
    // }

    // public Class getCodecoolClass(){
    //     return this.codecoolClass
    // }

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