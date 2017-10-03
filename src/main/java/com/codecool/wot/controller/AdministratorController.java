package com.codecool.wot.controller;


import com.codecool.wot.dao.ClassDAO;
import com.codecool.wot.dao.MentorDAO;
import com.codecool.wot.model.*;
import com.codecool.wot.view.*;

public class AdministratorController{

    private View <Mentor> view;
    private MentorDAO mentorDAO;
    private Tools tools;
    private ClassDAO classDAO;

    private final String CREATE_MENTOR = "1";
    private final String EDIT_MENTOR = "2";
    private final String SHOW_MENTORS = "3";
    private final String CREATE_CLASS = "4";
    private final String EDIT_CLASS = "5";
    private final String EXIT = "0";

    public AdministratorController(MentorDAO mDAO, ClassDAO classDAO, Tools tools) {
        this.view = new View();
        this.mentorDAO = mDAO;
        this.classDAO = classDAO;
        this.tools = tools;

    }

    public void startController(){

        String choose = "";
        String[] menu = {"Create mentor", "Edit mentor", "Create class", "Edit class", "Show mentors"};

        while (! choose.equals("0")){

            view.showMenu("Menu", menu, "Bye, bye");
            choose = view.getStringInput("");

            switch (choose){

                case CREATE_MENTOR :
                    createMentor();
                    break;

                case EDIT_MENTOR :
                    editMentor(getMentorByLogin());
                    break;

                case CREATE_CLASS:
                    createClass();

                case EDIT_CLASS:
                    editClass();

                case SHOW_MENTORS :
                    showAllMentors();
                    break;

                case EXIT :
                    break;
            }
        }
    }

    public void createMentor(){
        String name = view.getStringInput("Enter mentor's name: ");
        String surname = view.getStringInput("Enter mentor's surname: ");
        String email = view.getStringInput("Enter mentor's email: ");
        String login = view.getStringInput("Enter mentor's login: ");
        String password = view.getStringInput("Enter mentor's password: ");

        String classID = classDAO.getID(name);
        mentorDAO.saveToDataBase(name, surname, email, login, password, classID);
        String ID = mentorDAO.getID(login);
        mentorDAO.add(new Mentor(name, surname, email, login, password, ID, classID));
    }

    public void editMentor(Mentor mentor){
        mentor.setName(view.getStringInput("Enter new mentor's name: "));
        mentor.setSurname(view.getStringInput("Enter new mentor's surname: "));
        mentor.setLogin(view.getStringInput("Enter new mentor's login: "));
        mentor.setPassword(view.getStringInput("Enter new mentor's password: "));

        mentorDAO.updateMentor(mentor);
    }

    private Mentor getMentorByLogin() {
        Mentor mentor = null;

        while (mentor == null) {
            String login = view.getStringInput("Enter mentor's login :");
            mentor = mentorDAO.getByLogin(login);
            if (mentor == null) {
                view.printMessage("NOT FOUND !");
            }
        }
        return mentor;
    }

    private void createClass() {

        String name = view.getStringInput("Enter class name :");

        classDAO.addToDataBase(name);
        String classID = classDAO.getId(name);

        classDAO.add(new SchoolClass(name, classID));
    }

    private void editClass() {
        SchoolClass schoolClass = tools.getClassByName());
        schoolClass.setName(view.getStringInput("Enter new class name: "));
        classDAO.updateClass(schoolClass);
    }

    private void showAllMentors() {
        view.showAll(mentorDAO.getObjectList());
    }
}
