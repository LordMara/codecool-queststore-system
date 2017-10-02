package com.codecool.wot.controller;

import com.codecool.wot.model.Student;
import com.codecool.wot.model.Mentor;
import com.codecool.wot.model.Admin;
import com.codecool.wot.dao.*;
import com.codecool.wot.view.View;
import java.sql.Connection;

public class CentralController {

    public void startController() {

        Connection connection = DatabaseConnection.getDBConnection().getConnection();

        ArtifactDAO arDAO = new ArtifactDAO();
        QuestDAO qDAO = new QuestDAO();
        ClassDAO cDAO = new ClassDAO(connection);

        AdminDAO aDAO = new AdminDAO(connection);
        MentorDAO mDAO = new MentorDAO(connection);
        StudentDAO sDAO = new StudentDAO(connection);

        View view = new View();

        String login = view.getStringInput("Enter login");
        String password = view.getStringInput("Enter password");

        try {
            Admin admin = aDAO.getByLogin(login);
            if (password.equals(admin.getPassword())) {
                AdministratorController adminController = new AdministratorController(mDAO);
                adminController.startController();
            }
        } catch (NullPointerException e1) {

            try {
                Mentor mentor = mDAO.getByLogin(login);
                if (password.equals(mentor.getPassword())) {
                    MentorController mentorController = new MentorController(cDAO, sDAO, mDAO, qDAO);
                    mentorController.startController();
                }
            } catch (NullPointerException e2) {

                try {
                    Student student = sDAO.getByLogin(login);
                    if (password.equals(student.getPassword())) {

                        // add connection
                        StudentController studentController = new StudentController();
                        studentController.startController();
                    }
                } catch (NullPointerException e3) {
                    view.printMessage("No such user");
                }
            }
        }
    }
}
