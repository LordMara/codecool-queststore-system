package com.codecool.wot.model;

import java.lang.NullPointerException;
import java.util.ArrayList;
import java.util.Random;

public class Student extends Account{

    // private CodecoolClass codecoolClass;
    // private Team team;  TODO
    // private Wallet wallet;
    // private Level level; TODO

    private static ArrayList<Student> students = new ArrayList<>();

    public Student(){
    }

    public Student(String name, String surname, String login, String password) {
        super(name, surname, login, password);
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

    public static Student getByLogin(String login) throws NullPointerException{
        for (Student student : Student.students){
            if (student.login.equals(login)){
                return student;
            }
        }
        throw new NullPointerException();
    }

}
