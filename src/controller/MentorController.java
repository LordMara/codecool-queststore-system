package src.controller;

import src.model.*;
import src.view.*;


public class MentorController{
    View <Student> view;

    private final String CREATE_STUDENT = "1";
    private final String EDIT_STUDENT = "2";
    private final String SHOW_STUDENTS = "3";
    private final String EXIT = "0";


    public MentorController(){
        this.view = new View<>();
    }

    public void startController(){

        String choose = "";
        String[] menu = {"Create student", "Edit student", "Show students"};

        while (! choose.equals("0")){

            view.showMenu(menu);
            choose = view.getStringInput("");

            switch (choose){

                case CREATE_STUDENT :
                    createStudent();
                    break;

                case EDIT_STUDENT :
                    editStudent(getStudentByLogin());
                    break;

                case SHOW_STUDENTS :
                    showAllStudents();
                    break;

                case EXIT :
                    break;
            }
        }
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
        view.showAll(Student.getStudents());
    }

    private Student getStudentByLogin(){

        boolean found = false;
        Student student = null;

        while (! found){

            try {
                String login = view.getStringInput("Enter student's login :");
                student = Student.getByLogin(login);
                found = true;
            }
            catch (NullPointerException e){
                view.printMessage("NOT FOUND ! ");
                found = false;
            }
        }
        return student;
    }
}
