package com.codecool.wot.model;

import java.util.ArrayList;

public class Admin extends Account{

    private static ArrayList<Admin> admins = new ArrayList<>();

    public Admin(String name, String surname, String email, String login, String password){

        super(name, surname, email, login, password);

    }

    public Admin(String name, String surname, String email, String login, String password, Integer adminId){

        super(name, surname, email, login, password, adminId);

    }

    public String toString(){

        return String.format("Name : %s  | Surname : %s ", this.name, this.surname);
    }
}
