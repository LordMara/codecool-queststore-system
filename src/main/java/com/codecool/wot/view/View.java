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
        String fieldValue;

        System.out.println(message);
        fieldValue = scan.nextLine();

        return fieldValue;
    }

    public Integer getIntInput(String message) {
        Integer fieldValue = null;
        boolean operationSuccesful = false;

        System.out.println(message);

        do {
            try {
                fieldValue = scan.nextInt();
                operationSuccesful = true;
            } catch (InputMismatchException e) {
                System.out.println("Input correct value");
            }
        } while (!operationSuccesful);

        return fieldValue;
    }

    public Float getFloatInput(String message) {
        Float fieldValue = null;
        boolean operationSuccesful = false;

        System.out.println(message);

        do {
            try {
                fieldValue = scan.nextFloat();
                operationSuccesful = true;
            } catch (InputMismatchException e) {
                System.out.println("Input correct value");
            }
        } while (!operationSuccesful);

        return fieldValue;
    }

    public void showAll(List <T> objectList) {
        for(T object: objectList) {
            System.out.println(object);
        }
    }

    public void showMenu(String[] menu){

        int count = 1;

        for(String option : menu){
            String row = String.format("%d %s", count, option);
            System.out.println(row);
            count ++;
        }
        System.out.println("0 Exit");
    }

    public void printMessage(String message){
        System.out.println("^^^***" + message + "^^^***");
    }
}
