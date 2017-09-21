package com.codecool.wot.functional_model.Controller;

import java.util.Scanner;
import com.codecool.wot.view.View;

public class Controller {
    public void startController() {
        String menuOption;

        View view = new View();
        do {
            view.showMenu(view);

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
}
