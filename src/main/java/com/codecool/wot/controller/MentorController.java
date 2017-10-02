package com.codecool.wot.controller;

import com.codecool.wot.dao.*;
import com.codecool.wot.model.*;
import com.codecool.wot.view.*;

import java.sql.Connection;
import java.util.ArrayList;


public class MentorController{

    private View <Student> view;

    public Connection connection;
    private ClassDAO classDAO;
    private StudentDAO studentDAO;
    private MentorDAO mentorDAO;
    private QuestDAO questDAO;

    public MentorController(ClassDAO classDAO, StudentDAO studentDAO, MentorDAO mentorDAO, QuestDAO questDAO) {
        this.classDAO = classDAO;
        this.studentDAO = studentDAO;
        this.mentorDAO = mentorDAO;
        this.questDAO = questDAO;

        this.view = new View<>();
    }

    public void startController(){

        final String EXIT = "0";
        final String CREATE_STUDENT = "1";
        final String EDIT_STUDENT = "2";
        final String SHOW_STUDENTS = "3";
        final String CREATE_CLASS = "4";
        final String EDIT_CLASS = "5";
        final String CREATE_QUEST = "6";

        String choose = "";
        String[] menu = {"Create student", "Edit student", "Show students", "Create class", "Edit Class", "Create quest"};

        while (! choose.equals("0")){

            view.showMenu("Mentor Menu", menu, "EXIT ");
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

                case CREATE_CLASS :
                    createClass();
                    break;

                case EDIT_CLASS :
                    editClass();
                    break;

                case CREATE_QUEST:
                    createQuest();
                    break;

                case EXIT :
                    break;
            }
        }
    }


    private void createStudent() {

        if (classDAO.getObjectList().size() > 0) {
            String name = view.getStringInput("Enter student's name: ");
            String surname = view.getStringInput("Enter student's surname: ");
            String email = view.getStringInput("Enter student's email: ");
            String login = view.getStringInput("Enter student's login: ");
            String password = view.getStringInput("Enter student's password: ");
            Integer classId = getClassByName().getId();

            studentDAO.add(new Student(name, surname, email, login, password));

        } else{
            view.printMessage("Create class first ! ");
        }
    }

    private void editStudent(Student student){
        updateWholeStudent(student);

    }

    private void updateWholeStudent(Student student) {
        student.setName(view.getStringInput("Enter new student's name: "));
        student.setSurname(view.getStringInput("Enter new student's surname: "));
        student.setLogin(view.getStringInput("Enter new student's login: "));
        student.setPassword(view.getStringInput("Enter new student's password: "));
        student.setEmail(view.getStringInput("Enter new student's email: "));

        studentDAO.updateStudent(student);
    }

    private void showAllStudents() {

        view.showAll(studentDAO.getObjectList());
    }

    private Student getStudentByLogin() {

        boolean found = false;
        Student student = null;

        while (! found){

            try {
                String login = view.getStringInput("Enter student's login :");
                student = studentDAO.getByLogin(login);
                found = true;
            } catch (NullPointerException e){
                view.printMessage("NOT FOUND ! ");
                found = false;
            }
        }
        return student;
    }

    private void createClass() {

        String name = view.getStringInput("Enter class name :");
        classDAO.add(new SchoolClass(name));
    }

    private void editClass() {
        SchoolClass schoolClass = getClassByName();
        schoolClass.setName(view.getStringInput("Enter new class name: "));
        classDAO.updateClass(schoolClass);
    }

    private SchoolClass getClassByName() {

        boolean found = false;
        SchoolClass schoolClass = null;

        while (! found){

            try {
                String name = view.getStringInput("Enter class name :");
                schoolClass = classDAO.getByName(name);
                found = true;
            } catch (NullPointerException e){
                view.printMessage("NOT FOUND ! ");
                found = false;
            }
        }
        return schoolClass;
    }

    private void createQuest() {

        ArrayList<Student> students;
        String name = view.getStringInput("Enter quest name : ");
        String description = view.getStringInput("Enter quest short description :");
        Float price = view.getFloatInput("Enter quest price :");
        questDAO.add(new Quest(name, description, price));

        view.printMessage("Feature in development");
    }

    private void createTeam() {
    }

}