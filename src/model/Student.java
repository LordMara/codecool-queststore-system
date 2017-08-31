package src.model;

import java.util.ArrayList;
import java.util.Random;

public class Student extends Account{

    private CodecoolClass codecoolClass;
    // private Team team;  TODO
    private Wallet wallet;
    // private Level level; TODO

    private static ArrayList<Student> students = new ArrayList<>();
    private static  Integer lastID = 0;

    public Student(){
    }

    public Student(String name, String surname, String login, String password, CodecoolClass codecoolClass){
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.codecoolClass = codecoolClass;
        generateId();
        this.wallet = null;
        // this.level = new Level(); TODO
        Student.students.add(this);
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

    protected void generateId(){
        this.ID = Student.lastID;
        Student.lastID ++;
    }

    public String toString(){
        return String.format("Name : %s  | Surname : %s ", this.name, this.surname )
    }
}
