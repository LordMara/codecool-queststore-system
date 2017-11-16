package com.codecool.wot.view;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class View<T> {

    Scanner scan;

    public View(){
        this.scan = new Scanner(System.in);
    }


    public String getStringInput(String message) {

        String fieldValue = null;
        boolean operationSuccesful = false;



        while (!operationSuccesful) {
            try {
                System.out.println(message);
                fieldValue = scan.nextLine();
                for (int i=0; i < fieldValue.length(); i++) {
                    if (fieldValue.charAt(i) == ';' || fieldValue.charAt(i) == '"' || fieldValue.charAt(i) == '\'') {
                        throw new IllegalArgumentException();
                    }
                }
                operationSuccesful = true;

            } catch (IllegalArgumentException e) {
                System.out.println("Don't hack");
                operationSuccesful = false;
//                scan.nextLine();
            }
        }


        return fieldValue;
    }

    public Integer getIntInput(String message) {
        Integer fieldValue = null;
        boolean operationSuccesful = false;


        while (!operationSuccesful) {

            System.out.println(message);
            try {
                fieldValue = scan.nextInt();
                operationSuccesful = true;
            } catch (InputMismatchException e) {
                System.out.println("Input correct value");
                scan.next();
            }
        }

        return fieldValue;
    }

    public Float getFloatInput(String message) {
        Float fieldValue = null;
        boolean operationSuccesful = false;


        while (!operationSuccesful) {

            System.out.println(message);
            try {
                fieldValue = scan.nextFloat();
                operationSuccesful = true;
            } catch (InputMismatchException e) {
                System.out.println("Input correct value");
                scan.next();
            }
        }

        return fieldValue;
    }

    public void showAll(List <T> objectList) {
        for(T object: objectList) {
            System.out.println(object);
        }
    }

    public void showMenu(String menuTitile, String[] menuOptions, String exitMessage) {
        System.out.println(String.format("%nWelcome in %s", menuTitile));
        for (int index = 0; index < menuOptions.length; index++) {
            System.out.println(String.format("\t%d. %s", index + 1, menuOptions[index]));
        }
        System.out.println(String.format("\t0. %s%n", exitMessage));
    }

    public void printMessage(String message){
        System.out.println("^^^***" + message + "^^^***");
    }
}