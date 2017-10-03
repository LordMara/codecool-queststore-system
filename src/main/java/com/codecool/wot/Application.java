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



        try {
            CentralController controller = new CentralController();
            controller.startController();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

}
