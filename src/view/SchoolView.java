package src.view;

import java.util.Scanner;
import java.util.Iterator;
import src.model.School;
public class SchoolView {

    public void logIn() {
        
        Scanner in = new Scanner(System.in);
        String login = new String();
        Boolean toLog = true;

        while (toLog){
        System.out.println("Provide login: ");
        login = in.next();
        if (!isSuchlogin(login, school)) {
            System.out.println("Such login doesn't exist \npress Y if you want to continue logging");
            String answer = in.next().toUpperCase();
            if (!answer.equals("Y")) {
                toLog = false;
            }

        }
        }

    }

    private Boolean isSuchlogin(String login, School school) {

        Iterator iter = school.getAccounts().iterator();

        while(iter.hasNext()) {
            if (iter.next().getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        
    }
}
