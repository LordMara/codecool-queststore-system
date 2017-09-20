package com.codecool.wot.dao;
import com.codecool.wot.model.Account;

import java.util.LinkedList;

public abstract class AbstractCodecoolerDAO <T extends Account> extends AbstractDAO <T> {

    public LinkedList<T> getByClass(String className){

        LinkedList<T> temp = new LinkedList<>();

        for (T elem : objectsList){
            if (elem.getSchoolClass().getName().equals(className)){
                temp.add(elem);
            }
        }
        return temp;
    }


    public T getByLogin(String login) throws NullPointerException{
        for (T elem : objectsList){
            if (elem.getLogin().equals(login)){
                return elem;
            }
        }
        throw new NullPointerException();
    }

}
