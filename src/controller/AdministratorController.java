package src.controller;

import src.model.*;
import src.view.*;



public class AdministratorController{

    AdministratorController(){
        View<Mentor>  view = new View();
    }

    public void createMantor(){
        String name = viev.getStringInput("Enter mentor's name: ");
        String surname = viev.getStringInput("Enter mentor's surname: ");
        String login = viev.getStringInput("Enter mentor's login: ");
        String password = viev.getStringInput("Enter mentor's password: ");

        Mentor mentor = new Mentor(name, surname, login, password);
    }

    public void editMentor(Mentor mentor){
        mentor.setName(Viev.getStringInput("Enter new mentor's name: "));
        mentor.setSurname(Viev.getStringInput("Enter new mentor's surname: "));
        mentor.setLogin(Viev.getStringInput("Enter new mentor's login: "));
        mentor.setPassword(Viev.getStringInput("Enter new mentor's password: "));

    }

    public void showAllStudents(){
        viev.showAll(Mentor.mentor);
    }
}
