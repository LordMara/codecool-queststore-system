package src.controller;

import src.model.*;
import src.view.*;


public class MentorController{

    MentorController(){

    }

    public void createStudent(){
        String name = Viev.getStringInput("Enter student's name: ");
        String surname = Viev.getStringInput("Enter student's surname: ");
        String login = Viev.getStringInput("Enter student's login: ");
        String password = Viev.getStringInput("Enter student's password: ");

        Student student = new Student(name, surname, login, password);
    }

    public void editStudent(Student student){
        student.name = Viev.getStringInput("Enter new student's name: ");
        student.surname = Viev.getStringInput("Enter new student's surname: ");
        student.login = Viev.getStringInput("Enter new student's login: ");
        student.password = Viev.getStringInput("Enter new student's password: ");

    }

    public void showAllStudents(){
        Viev.showAll(Student.students);

    }
}
