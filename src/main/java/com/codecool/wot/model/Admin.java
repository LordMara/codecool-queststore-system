package com.codecool.wot.model;

import java.util.ArrayList;

public class Admin extends Account{

    private static ArrayList<Admin> admins = new ArrayList<Admin>();

    public Admin(String name, String surname, String email, String login, String password, String ID) {
        super(name, surname, email, login, password, ID);

    }

    public String toString(){

        return String.format("Name : %s  | Surname : %s ", this.name, this.surname);
    }
}
