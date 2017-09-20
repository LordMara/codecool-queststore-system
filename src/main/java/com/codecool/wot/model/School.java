package com.codecool.wot.model;

import java.util.ArrayList;
import java.util.Scanner;
import src.model.Student;



public class School {

    ArrayList<Account> accounts;
    ArrayList<Class> classes;
    String name;

    public School(ArrayList<Account> newAccounts, ArrayList<Class> newClasses, String newName) {
        
        accounts = newAccounts;
        classes = newClasses;
        name = newName;

    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> newAccounts) {
        accounts = newAccounts;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> newClasses) {
        classes = newClasses;
    }

    public void addAccount(Account newMember) {
        accounts.add(newMember);
    }

    public void addClass(Class newClass) {
        classes.add(newClass);
    }
}