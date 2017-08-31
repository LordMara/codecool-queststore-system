package src.view;

import java.util.Scanner;
import java.util.Iterator;
import src.model.School;
public class SchoolView {

    public void logIn() {
        
        Scanner in = new Scanner(System.in);
        Integer id;
        System.out.println("Provide ID: ");
        id = (Integer) in.next();

    }

    private Boolean isSuchID(Integer id, School school) {

        Iterator iter = school.getAccounts().iterator();

        while(iter.hasNext()) {
            if (iter.next().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        
    }
}
