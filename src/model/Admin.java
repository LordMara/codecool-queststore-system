package src.model;

import java.util.ArrayList;

public class Admin extends Account{

    private static ArrayList<Admin> admins = new ArrayList<>();
    private static  Integer lastID = 0;

    public Admin(String name, String surname, String login, String password){
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        generateId();
    }

    protected void generateId(){
        this.ID = Admin.lastID;
        Admin.lastID ++;
    }

    public String toString(){
        return String.format("Name : %s  | Surname : %s ", this.name, this.surname);
    }
}
