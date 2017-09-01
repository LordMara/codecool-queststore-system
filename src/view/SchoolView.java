package src.view;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import src.model.School;
import src.model.Account;
import src.model.Student;
import src.model.Mentor;
import src.model.Admin;
import src.model.Class;

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

                        Student activeUser = (Student) returnUser(login, password);
                        return activeUser;
                    }
                    else {

                        if (returnUser(login, password) instanceof Mentor) {

                            Mentor activeUser = (Mentor) returnUser(login, password);
                            return activeUser;
                        }
                        else {

                            Admin activeUser = (Admin) returnUser(login, password);
                            return activeUser;
                        }
                    }
                    

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
        System.out.println("Login failed");
        return createDefaultAccount();

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
            return createDefaultAccount();
        }

    private Student createDefaultAccount() {
        Student defaultStudent = new Student();
        return defaultStudent;
    }



    public static void main(String[] args) {

        ArrayList<Account> ac = new ArrayList<>();
        ArrayList<Class> cl = new ArrayList<>();

        School codecool = new School(ac, cl, (String) "codecool");
        SchoolView zo = new SchoolView(codecool);
        
        Student kasia = new Student("kasia", "K", "kata", "mata");
        Student basia = new Student("basia", "B", "bata", "cata");

        codecool.addStudent(kasia);
        codecool.addStudent(basia);

        Account active = zo.logIn();

        System.out.println("The active user is: " + active.toString());
    }
}
