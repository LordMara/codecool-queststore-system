package controller;

import model.*;
import view.*;


public class MentorController{

    MentorController(){
        View<Student>  view = new View();
    }

    public void createStudent(){
        String name = viev.getStringInput("Enter student's name: ");
        String surname = viev.getStringInput("Enter student's surname: ");
        String login = viev.getStringInput("Enter student's login: ");
        String password = viev.getStringInput("Enter student's password: ");

        Student student = new Student(name, surname, login, password);
    }

    public void editStudent(Student student){
        student.name = Viev.getStringInput("Enter new student's name: ");
        student.surname = Viev.getStringInput("Enter new student's surname: ");
        student.login = Viev.getStringInput("Enter new student's login: ");
        student.password = Viev.getStringInput("Enter new student's password: ");

    }

    public void showAllStudents(){
        viev.showAll(Student.students);
    }
}
