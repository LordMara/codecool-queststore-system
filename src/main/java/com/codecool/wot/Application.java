package com.codecool.wot;

import com.codecool.wot.model.*;
import com.codecool.wot.view.*;
import com.codecool.wot.controller.*;

class Application{

    public static void main(String[] args){

        Application app = new Application();
        app.start();
    }

    private void start(){

        final String MENTOR_CONTROLLER = "1";
        final String ADMIN_CONTROLLER = "2";
        final String EXIT = "0";

        String choose = "";
        String[] menu = {"Mentor ", "Admin"};
        AdministratorController admin = new AdministratorController();
        MentorController mentor = new MentorController();
        View view = new View();

        while (! choose.equals("0")){

            view.showMenu(menu);
            choose = view.getStringInput("");

            switch (choose){

                case MENTOR_CONTROLLER :
                    mentor.startController();
                    break;

                case ADMIN_CONTROLLER :
                    admin.startController();
                    break;

                case EXIT :
                    break;
            }
        }
    }
}
