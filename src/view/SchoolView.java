package src.view;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import src.model.School;
import src.model.Account;
public class SchoolView {

    School school;
    public SchoolView(School newSchool) {
        school = newSchool;
    }

    public void logIn() {
        
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

    // private Account returnUser(String login) {

    // }

    public static void main(String[] args) {
        
    }
}
