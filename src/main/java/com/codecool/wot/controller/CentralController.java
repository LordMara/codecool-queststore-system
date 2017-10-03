package com.codecool.wot.controller;

import com.codecool.wot.model.*;
import com.codecool.wot.dao.*;
import com.codecool.wot.view.View;
import java.sql.Connection;
import java.sql.SQLException;

public class CentralController {
    private View view;
    private String login;
    private String password;


    public CentralController() throws SQLException {

        view = new View();
        setup();
    }

    public void startController() throws SQLException {


        FactoryDAO factoryDAO = new FactoryDAO();

        try (Connection connection = factoryDAO.loadDB()){

            // close in one object
            DAOs dao = new DAOs(connection);
            Tools tools = new Tools(this.view, dao);
            // close in one object


            Account user = validateUser(dao);

            if (!startProperController(user, dao, tools)) {
                this.view.printMessage("No such user");
            }
            connection.close();
        }
    }

    private Account validateUser(DAOs dao) {
        Account user = null;

        Admin admin = dao.getaDAO().getBy(this.login);
        Mentor mentor = dao.getmDAO().getBy(this.login);
        Student student = dao.getsDAO().getBy(this.login);

        if (admin != null) {
            if (this.password.equals(admin.getPassword())) {
                user = admin;
            }
        }

        if (mentor != null) {
            if (this.password.equals(mentor.getPassword())) {
                user = mentor;
            }
        }
        if (student != null) {
            if (this.password.equals(student.getPassword())) {
                user = student;
            }
        }
        return user;
    }

    private boolean startProperController(Account user, DAOs dao, Tools tools) throws SQLException{

        boolean operationSuccessful = true;
        if (user instanceof Admin) {
            AdministratorController adminController = new AdministratorController(dao, tools);
            adminController.startController();

        } else if (user instanceof Mentor) {
            MentorController mentorController = new MentorController(dao, tools);
            mentorController.startController();

        } else if (user instanceof Student) {

            StudentController studentController = new StudentController();
            studentController.startController();

        } else {
            operationSuccessful = false;
        }
        return operationSuccessful;
    }

    private void setup() {
        this.login = this.view.getStringInput("Enter login");
        this.password = this.view.getStringInput("Enter password");
    }
}
