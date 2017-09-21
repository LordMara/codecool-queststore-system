package com.codecool.wot.model;

import com.codecool.wot.dao.StudentDAO;

import java.lang.NullPointerException;
import java.util.ArrayList;
import java.util.Random;

public class Student extends Account{

    private Integer classId;
//        private Team team;
    // private Wallet wallet;
    // private Level level; TODO

    public Student(String name, String surname, String email, String login, String password, StudentDAO studentDAO, Integer classId) {
        super(name, surname, email, login, password);
        this.classId = classId;
        studentDAO.add(this);
    }

    public Student(String name, String surname, String email, String login, String password, Integer ID, Integer classId) {
        super(name, surname, email, login, password, ID);
        this.classId = classId;
    }

}
