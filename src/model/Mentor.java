package src.model;

import java.util.ArrayList;
import java.util.Random;


public class Mentor extends Account{

    // private CodecoolClass codecoolClass;

    private static ArrayList<Mentor> mentors = new ArrayList<>();
    private static  Integer lastID = 0;


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

    protected void generateId(){
        this.ID = Mentor.lastID;
        Mentor.lastID ++;
    }

    public String toString(){
        return String.format("Name : %s  | Surname : %s ", this.name, this.surname);
    }

    public static ArrayList<Mentor> getMentors(){
        return Mentor.mentors;
    }
}
