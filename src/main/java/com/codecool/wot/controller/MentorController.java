package com.codecool.wot.controller;

import com.codecool.wot.dao.ClassDAO;
import com.codecool.wot.dao.StudentDAO;
import com.codecool.wot.model.*;
import com.codecool.wot.view.*;


public class MentorController{

    private View <Student> view;

    public MentorController(){
        this.view = new View<>();
    }

    public void startController(){

        ClassDAO classDao = new ClassDAO();
        StudentDAO studentDAO = new StudentDAO();

        final String EXIT = "0";
        final String CREATE_STUDENT = "1";
        final String EDIT_STUDENT = "2";
        final String SHOW_STUDENTS = "3";

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


    private void createStudent(ClassDAO classDAO){

        if (classDAO.getObjectList().size() > 0) {
            String name = view.getStringInput("Enter student's name: ");
            String surname = view.getStringInput("Enter student's surname: ");
            String email = view.getStringInput("Enter student's email: ");
            String login = view.getStringInput("Enter student's login: ");
            String password = view.getStringInput("Enter student's password: ");
            SchoolClass schoolClass = getClassByName(classDAO);

            new Student(name, surname,email, login, password, schoolClass);
        }
        else{
            view.printMessage("Create class first ! ");
        }
    }

    private void editStudent(Student student){
        student.setName(view.getStringInput("Enter new student's name: "));
        student.setSurname(view.getStringInput("Enter new student's surname: "));
        student.setLogin(view.getStringInput("Enter new student's login: "));
        student.setPassword(view.getStringInput("Enter new student's password: "));

    }
    private void showAllStudents(StudentDAO studentDAO){

        view.showAll(studentDAO.getObjectList());
    }

    private Student getStudentByLogin(StudentDAO studentDAO){

        boolean found = false;
        Student student = null;

        while (! found){

            try {
                String login = view.getStringInput("Enter student's login :");
                student = studentDAO.getByLogin(login);
                found = true;
            }
            catch (NullPointerException e){
                view.printMessage("NOT FOUND ! ");
                found = false;
            }
        }
        return student;
    }

    private void createClass(ClassDAO classDao){

        String name = view.getStringInput("Enter class name :");
        new SchoolClass(name, classDao);
    }

    private void editClass(SchoolClass schoolClass){

    }

    private SchoolClass getClassByName(ClassDAO classDAO){

        boolean found = false;
        SchoolClass schoolClass = null;

        while (! found){

            try {
                String name = view.getStringInput("Enter class name :");
                schoolClass = classDAO.getByName(name);
                found = true;
            }
            catch (NullPointerException e){
                view.printMessage("NOT FOUND ! ");
                found = false;
            }
        }
        return schoolClass;
    }


}
