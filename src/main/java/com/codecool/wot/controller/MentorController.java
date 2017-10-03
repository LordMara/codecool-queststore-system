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
    private Tools tools;

    public MentorController(ClassDAO classDAO, StudentDAO studentDAO, MentorDAO mentorDAO, QuestDAO questDAO, Tools tools) {
        this.classDAO = classDAO;
        this.studentDAO = studentDAO;
        this.mentorDAO = mentorDAO;
        this.questDAO = questDAO;
        this.tools = tools;
        this.view = new View<>();
    }

    public void startController(){

        final String EXIT = "0";
        final String CREATE_STUDENT = "1";
        final String EDIT_STUDENT = "2";
        final String SHOW_STUDENTS = "3";
        final String CREATE_QUEST = "4";

        String choose = "";
        String[] menu = {"Create student", "Edit student", "Show students", "Create quest"};

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

            studentDAO.saveToDataBase(name, surname, email, login, password);

            String ID = studentDAO.getId(login);
            String classId = tools.getClassByName().getId();

            studentDAO.add(new Student(name, surname, email, login, password, ID, classId));

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
        Student student = null;

        while (student == null){
            String login = view.getStringInput("Enter student's login :");
            student = studentDAO.getByLogin(login);
            if (student == null) {
                view.printMessage("NOT FOUND ! ");
            }
        }
        return student;
    }

    private void createQuest() {

        String name = view.getStringInput("Enter quest name : ");
        String description = view.getStringInput("Enter quest short description :");
        Float price = view.getFloatInput("Enter quest price :");
        questDAO.saveToDataBase(name, description, price);

        String questID = questDAO.getId(name, description, price);

        questDAO.add(new Quest(name, description, price, questID));

        view.printMessage("Feature in development");
    }

    private void createTeam() {
    }

}