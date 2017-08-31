package src.view;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import src.model.School;
import src.model.Account;
import src.model.Student;
import src.model.Mentor;
import src.model.Admin;
public class SchoolView {

    School school;
    public SchoolView(School newSchool) {
        school = newSchool;
    }

    public Account logIn() {
        
        Scanner in = new Scanner(System.in);
        String login;
        String password;
        Boolean toLog = true;

        while (toLog){

            System.out.println("Provide login: ");
            login = in.next();

            if (isSuchlogin(login)) {

                System.out.println("Provide password: ");
                password = in.next();

                if (isPasswordProper(login, password)) {

                    if (returnUser(login, password) instanceof Student) {

                        Student activeUser = returnUser(login, password);
                    }
                    else {

                        if (returnUser(login, password) instanceof Mentor) {

                            Mentor activeUser = returnUser(login, password);
                        }
                        else {

                            Admin activeUser = returnUser(login, password);
                        }
                    }
                    return activeUser;

                }
                else {

                    System.out.println("Password doesn't match the login \npress Y if you want to continue logging");
                    String answer = in.next().toUpperCase();
                    
                    if (!answer.equals("Y")) {
                        toLog = false;
                    }
                }
            }
            else {
                
                System.out.println("Such login doesn't exist \npress Y if you want to continue logging");
                String answer = in.next().toUpperCase();

                if (!answer.equals("Y")) {
                    toLog = false;
                }
            }
        }

    }


    private Boolean isSuchlogin(String login) {

        Iterator iter = school.getAccounts().iterator();

        while(iter.hasNext()) {
            Account account = (Account) iter.next();
            if (account.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    private Boolean isPasswordProper(String login, String password) {

        Iterator iter = school.getAccounts().iterator();
        
        while(iter.hasNext()) {
            Account account = (Account) iter.next();
            if (account.getLogin().equals(login)) {
                if (account.getPassword().equals(password)) {
                    return true;
                }
            }
        }

        return false;
    }

    private Account returnUser(String login, String password) {

        Iterator iter = school.getAccounts().iterator();
        
        while(iter.hasNext()) {
            Account account = (Account) iter.next();
            if (account.getLogin().equals(login) & account.getPassword().equals(password)) {
                    return account;
                }
            }
        }



    public static void main(String[] args) {
        
    }
}
