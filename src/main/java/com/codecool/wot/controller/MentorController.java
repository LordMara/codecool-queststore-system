package com.codecool.wot.controller;

import com.codecool.wot.dao.*;
import com.codecool.wot.model.*;
import com.codecool.wot.view.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class MentorController{

    private View <Student> view;

    public Connection connection;
    private ClassDAO classDAO;
    private StudentDAO studentDAO;
    private MentorDAO mentorDAO;
    private QuestDAO questDAO;
    private Tools tools;

    public MentorController(DAOs dao, Tools tools) {
        this.classDAO = dao.getcDAO();
        this.studentDAO = dao.getsDAO();
        this.mentorDAO = dao.getmDAO();
//        this.questDAO = dao.getqDAO();
        this.tools = tools;
        this.view = new View<>();
    }

    public void startController() throws SQLException {

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


    private void createStudent() throws SQLException {

        if (classDAO.getObjectList().size() > 0) {

            String name = view.getStringInput("Enter student's name: ");
            String surname = view.getStringInput("Enter student's surname: ");
            String email = view.getStringInput("Enter student's email: ");
            String login = view.getStringInput("Enter student's login: ");
            String password = view.getStringInput("Enter student's password: ");

            studentDAO.saveToDataBase(name, surname, email, login, password);

            String classId = tools.getClassByName().getId();
            String ID = studentDAO.getIDFromDB(login);

            studentDAO.saveToDataBase(ID, classId);
            studentDAO.add(new Student(name, surname, email, login, password, ID, classId));

        } else{
            view.printMessage("Create class first ! ");
        }
    }
    private void editStudent(Student student) throws SQLException{
        updateWholeStudent(student);

    }

    private void updateWholeStudent(Student student) throws SQLException {
        student.setName(view.getStringInput("Enter new student's name: "));
        student.setSurname(view.getStringInput("Enter new student's surname: "));
        student.setLogin(view.getStringInput("Enter new student's login: "));
        student.setPassword(view.getStringInput("Enter new student's password: "));
        student.setEmail(view.getStringInput("Enter new student's email: "));

        studentDAO.update(student);
    }

    private void showAllStudents() {

        view.showAll(studentDAO.getObjectList());
    }

    private Student getStudentByLogin() {
        Student student = null;

        while (student == null){
            String login = view.getStringInput("Enter student's login :");
            student = studentDAO.getBy(login);
            if (student == null) {
                view.printMessage("NOT FOUND ! ");
            }
        }
        return student;
    }

    private void createQuest() throws SQLException{

//        String name = view.getStringInput("Enter quest name : ");
//        String description = view.getStringInput("Enter quest short description :");
//        String price = view.getFloatInput("Enter quest price :").toString();
//
//        questDAO.saveToDataBase(name, description, price);
//
//        String questID = questDAO.getIDFromDBQuery(name);
//
//        questDAO.add(new Quest(name, description, Float.parseFloat(price), questID));
    }

}