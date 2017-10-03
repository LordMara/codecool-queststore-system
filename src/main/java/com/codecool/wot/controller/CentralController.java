package com.codecool.wot.controller;

import com.codecool.wot.model.Account;
import com.codecool.wot.model.Student;
import com.codecool.wot.model.Mentor;
import com.codecool.wot.model.Admin;
import com.codecool.wot.dao.*;
import com.codecool.wot.view.View;
import java.sql.Connection;
import java.sql.SQLException;

public class CentralController {
    private View view;
    private String login;
    private String password;

    public CentralController() {
        view = new View();
    }

    public void startController() throws SQLException {

        try (Connection connection = DatabaseConnection.getDBConnection().getConnection()){

            // close in one object
            ArtifactDAO arDAO = new ArtifactDAO();
            QuestDAO qDAO = new QuestDAO();
            ClassDAO cDAO = new ClassDAO(connection);

            AdminDAO aDAO = new AdminDAO(connection);
            MentorDAO mDAO = new MentorDAO(connection);
            StudentDAO sDAO = new StudentDAO(connection);
            // close in one object

            setup();

            Account user = validateUser(aDAO, mDAO, sDAO);

            if (!startProperController(user, arDAO, qDAO, cDAO, aDAO, mDAO, sDAO)) {
                this.view.printMessage("No such user");
            }
        }
    }

    private Account validateUser(AdminDAO aDAO, MentorDAO mDAO, StudentDAO sDAO) {
        Account user = null;

        Admin admin = aDAO.getByLogin(this.login);
        Mentor mentor = mDAO.getByLogin(this.login);
        Student student = sDAO.getByLogin(this.login);

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

    private boolean startProperController(Account user, ArtifactDAO arDAO, QuestDAO qDAO,
            ClassDAO cDAO, AdminDAO aDAO, MentorDAO mDAO, StudentDAO sDAO) {

        boolean operationSuccessful = true;
        if (user instanceof Admin) {
            AdministratorController adminController = new AdministratorController(mDAO);
            adminController.startController();

        } else if (user instanceof Mentor) {
            MentorController mentorController = new MentorController(cDAO, sDAO, mDAO, qDAO);
            mentorController.startController();

        } else if (user instanceof Student) {
            // add connection
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
