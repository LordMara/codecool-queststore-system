package com.codecool.wot.controller;

import com.codecool.wot.interfaces.ControllerInterface;
import com.codecool.wot.view.View;

public class StudentController implements ControllerInterface {
    public void startController() {
        View view = new View();


        final String EXIT = "0";
        final String CHECK_WALLET = "1";
        final String ENTER_SHOP = "2";
        final String CHECK_EXPERIENCE = "3";

        String choose = "";
        String[] menu = {"Check wallet", "Enter shop", "Check experience", };

        while (! choose.equals("0")){

            view.showMenu("Student Menu", menu, "EXIT ");
            choose = view.getStringInput("");

            switch (choose){

                case CHECK_WALLET :
                    view.printMessage("Feature in development");
                    break;

                case ENTER_SHOP :
                    view.printMessage("Feature in development");
                    break;

                case CHECK_EXPERIENCE :
                    view.printMessage("Feature in development");
                    break;

                case EXIT :
                    break;
            }
        }
    }
}

