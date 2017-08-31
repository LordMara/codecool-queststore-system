package src.model;

import java.util.ArrayList;
import java.util.Scanner;



public class School {

    ArrayList<Account> accounts;
    ArrayList<Class> classes;

    public School(ArrayList<Account> newAccounts, ArrayList<Class> newClasses) {
        
        accounts = newAccounts;
        classes = newClasses;

    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }
}