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
        this.ID = generateId();
    }

    // public setClass(Class class){
    //     this.class = class;
    // }

    // public Class getClass(){
    //     return this.class
    // }

    private generateId(){

        String ID = generateRandom();

        while(checkIsUnique(ID)){
            ID = generateRandom();
        }
        return ID;
    }

    private boolean checkIsUnique(String ID){
        for (Student mentor : Mentor.mentors){
            if mentor.getId().equals(ID){
                return false;
            }
        }
        return true;
    }

    private String generateRandom(){
        Random rand = new Random();
        Integer number = rand.nextInt(9999);
        return String.format("M%d",number);
    }
}
