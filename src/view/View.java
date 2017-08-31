package src.view;

import java.util.ArrayList;
import java.util.Scanner;

public class View<T> {

    Scanner scan;

    public View(){
        this.scan = new Scanner(System.in);
    }


    public String getStringInput(String message) {
        String fieldValue;

        System.out.println(message);
        fieldValue = scan.nextLine();

        return fieldValue;
    }

    public Integer getIntInput(String message) {
        Integer fieldValue;

        System.out.println(message);
        fieldValue = scan.nextInt();

        return fieldValue;
    }

    public void showAll(ArrayList<T> objectList) {
        for(T object: objectList) {
            System.out.println(object);
        }
    }
}
