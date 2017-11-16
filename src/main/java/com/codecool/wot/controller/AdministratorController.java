package com.codecool.wot.controller;


import com.codecool.wot.dao.MentorDAO;
import com.codecool.wot.interfaces.ControllerInterface;
import com.codecool.wot.model.*;
import com.codecool.wot.view.*;

public class AdministratorController implements ControllerInterface {

    private View <Mentor> view;
    private MentorDAO mentorDAO;

    private final String CREATE_MENTOR = "1";
    private final String EDIT_MENTOR = "2";
    private final String SHOW_MENTORS = "3";
    private final String EXIT = "0";

    public AdministratorController(MentorDAO mDAO) {
        this.view = new View();
        this.mentorDAO = mDAO;
    }

    public void startController(){

        String choose = "";
        String[] menu = {"Create mentor", "Edit mentor", "Show mentor"};

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

        mentorDAO.add(new Mentor(name, surname, email, login, password));
    }

    public void editMentor(Mentor mentor){
        mentor.setName(view.getStringInput("Enter new mentor's name: "));
        mentor.setSurname(view.getStringInput("Enter new mentor's surname: "));
        mentor.setLogin(view.getStringInput("Enter new mentor's login: "));
        mentor.setPassword(view.getStringInput("Enter new mentor's password: "));
        mentor.setEmail(view.getStringInput("Enter new student's email: "));

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

    private void showAllMentors() {

        view.showAll(mentorDAO.getObjectList());
    }
}
