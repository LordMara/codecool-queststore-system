package com.codecool.wot.functional_model.Controller;

import java.util.Scanner;

import com.codecool.wot.controller.MentorController;
import com.codecool.wot.controller.StudentController;
import com.codecool.wot.controller.AdministratorController;
import com.codecool.wot.model.Student;
import com.codecool.wot.model.Mentor;
import com.codecool.wot.model.Admin;
import com.codecool.wot.dao.*;
import com.codecool.wot.view.View;

public class Controller {
    public void startController() {
        ArtifactDAO arDAO = new ArtifactDAO();
        QuestDAO qDAO = new QuestDAO();
        ClassDAO cDAO = new ClassDAO();

        AdminDAO aDAO = new AdminDAO();
        MentorDAO mDAO = new MentorDAO();
        StudentDAO sDAO = new StudentDAO();

        View view = new View();

        String login = view.getStringInput("Enter login");
        String password = view.getStringInput("Enter password");

        for (Student student : sDAO.getPersonList()) {
            if (login.equals(student.getLogin()) && password.equals(student.getPassword())) {
                StudentController studentController = new StudentController();
                studentController.startController();
            }
        }

        for (Mentor mentor : mDAO.getPersonList()) {
            if (login.equals(mentor.getLogin()) && password.equals(mentor.getPassword())) {
                MentorController mentorController = new MentorController();
                mentorController.startController();
            }
        }

        for (Admin admin : aDAO.getPersonList()) {
            if (login.equals(admin.getLogin()) && password.equals(admin.getPassword())) {
                AdministratorController adminController = new AdministratorController();
                adminController.startController();
            }
        }
    }
}

//        String menuOption;
//
//        View view = new View();
//        do {
//            displayMainMenu(view);
//
//            menuOption = view.getStringInput("Choose menu option");
//
//            switch (menuOption) {
//                case "0":
//                    break;
//                case "1":
//                    break;
//                case "2":
//                    break;
//                default:
//                    view.printMessage("No such option");
//                    break;
//            }
//        } while (!menuOption.equals("0"));
//    }
//
//    private void displayMainMenu(View view) {
//        String menuTitle = "Main menu";
//        String[] menuOptions = {"Option 1", "Option 2"};
//
//        String exitMessage = "Exit application";
//
//        view.showMenu(menuTitle, menuOptions, exitMessage);
//    }

