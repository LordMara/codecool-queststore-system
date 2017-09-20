package com.codecool.wot.model;

import java.lang.NullPointerException;
import java.util.ArrayList;
import java.util.Random;

public class Student extends Account{

     private SchoolClass codecoolClass;
    // private Team team;  TODO
    // private Wallet wallet;
    // private Level level; TODO

    public Student(String name, String surname, String email, String login, String password) {
        super(name, surname, email, login, password);

        addToClass();
    }

    private void addToClass(SchoolClass codecoolClass) {
        if (this.ID.equals()) {
            this.codecoolClass = codecoolClass;
        }
    }

    // public CodecoolClass getCodecoolClass(){
    //     return this.codecoolClass;
    // }
    //
    // public void setCodecoolClass(Cprivate CodecoolClass codecoolClass){
    //     this.codecoolClass = codecoolClass;
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


    public String toString(){
        return String.format("Name : %s  | Surname : %s ", this.name, this.surname);
    }

    public static ArrayList<Student> getStudents(){
        return Student.students;
    }

}
