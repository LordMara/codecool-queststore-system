package com.codecool.wot.model;

import java.util.ArrayList;
import java.util.Random;

import com.codecool.wot.dao.ClassDAO;
import com.codecool.wot.dao.MentorDAO;


public class Mentor extends Account{

    private SchoolClass schoolClass;

    public Mentor(String name, String surname, String email, String login, String password, MentorDAO dao) {
        super(name, surname, email, login, password);
        dao.add(this);
    }

    public Mentor(String name, String surname, String email, String login, String password, Integer ID) {
        super(name, surname, email, login, password, ID);
    }

}
