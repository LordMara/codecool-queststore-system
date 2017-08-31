package view;

import java.util.ArrayList;
import java.util.Scanner;

public class View<T> {
    public View() {}

    public String getStringInput(String message) {
        String fieldValue;

        try (Scanner userInput = new Scanner(System.in)) {
            System.out.println(message);
            fieldValue = userInput.nextLine();
        }
        return fieldValue;
    }

    public String getIntInput(String message) {
        String fieldValue;

        try (Scanner userInput = new Scanner(System.in)) {
            System.out.println(message);
            fieldValue = userInput.nextInt();
        }
        return fieldValue;
    }

    public void showAll(ArrayList<T> objectList) {
        for(T object: objectList) {
            System.out.println(object);
        }
    }
}
