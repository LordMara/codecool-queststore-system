package com.codecool.wot.functional_model.Controller;

import java.util.Scanner;

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

        String menuOption;

        View view = new View();
        do {
            displayMainMenu(view);

            menuOption = view.getStringInput("Choose menu option");

            switch (menuOption) {
                case "0":
                    break;
                case "1":
                    break;
                case "2":
                    break;
                default:
                    view.printMessage("No such option");
                    break;
            }
        } while (!menuOption.equals("0"));
    }

    private void displayMainMenu(View view) {
        String menuTitle = "Main menu";
        String[] menuOptions = {"Option 1", "Option 2"};

        String exitMessage = "Exit application";

        view.showMenu(menuTitle, menuOptions, exitMessage);
    }
}
