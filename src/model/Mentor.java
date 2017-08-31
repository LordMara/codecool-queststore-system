package src.models;

import java.util.ArrayList;
import java.util.Random;


public class Mentor{

    private Class class;

    private static ArrayList<Mentor> mentors = new ArrayList<>();


    public Mentor(String name, String surname, String email, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.ID = generateId("M");
    }

    // public setClass(Class class){
    //     this.class = class;
    // }

    // public Class getClass(){
    //     return this.class
    // }

}
