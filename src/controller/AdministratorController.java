package src.controller;

import src.model.*;
import src.view.*;



public class AdministratorController{

    View <Mentor> view;

    public AdministratorController(){
        this.view = new View();
    }

    public void createMentor(){
        String name = view.getStringInput("Enter mentor's name: ");
        String surname = view.getStringInput("Enter mentor's surname: ");
        String login = view.getStringInput("Enter mentor's login: ");
        String password = view.getStringInput("Enter mentor's password: ");

        Mentor mentor = new Mentor(name, surname, login, password);
    }

    public void editMentor(Mentor mentor){
        mentor.setName(view.getStringInput("Enter new mentor's name: "));
        mentor.setSurname(view.getStringInput("Enter new mentor's surname: "));
        mentor.setLogin(view.getStringInput("Enter new mentor's login: "));
        mentor.setPassword(view.getStringInput("Enter new mentor's password: "));
    }

    public void showAllMentors(){
        view.showAll(Mentor.getMentors());
    }
}
