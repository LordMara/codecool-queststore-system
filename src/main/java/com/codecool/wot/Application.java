package com.codecool.wot;

import com.codecool.wot.model.*;
import com.codecool.wot.view.*;
import com.codecool.wot.controller.*;

import java.sql.SQLException;

class Application {

    public static void main(String[] args) {

        Application app = new Application();
        app.start();
    }

    private void start() {

        CentralController controller = new CentralController();

        try {
            controller.startController();
        } catch (SQLException e) {
            System.out.println("Problem with connection to database");
            System.exit(0);
        }

    }

}
