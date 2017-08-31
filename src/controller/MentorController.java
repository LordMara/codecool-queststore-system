package src.controller;

import src.model.*;
import src.view.*;


public class MentorController{
    View <Student> view;

    public MentorController(){
        this.view = new View<>();
    }

    public void createStudent(){
        String name = view.getStringInput("Enter student's name: ");
        String surname = view.getStringInput("Enter student's surname: ");
        String login = view.getStringInput("Enter student's login: ");
        String password = view.getStringInput("Enter student's password: ");

        Student student = new Student(name, surname, login, password);
    }

    public void editStudent(Student student){
        student.setName(view.getStringInput("Enter new student's name: "));
        student.setSurname(view.getStringInput("Enter new student's surname: "));
        student.setLogin(view.getStringInput("Enter new student's login: "));
        student.setPassword(view.getStringInput("Enter new student's password: "));

    }

    public void showAllStudents(){
        view.showAll(Student.students);
    }
}
