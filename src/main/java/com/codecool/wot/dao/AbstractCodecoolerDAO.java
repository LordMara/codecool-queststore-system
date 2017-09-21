package com.codecool.wot.dao;
import com.codecool.wot.model.Account;

import java.util.ArrayList;

public abstract class AbstractCodecoolerDAO <T extends Account> extends AbstractDAO <T> {

    public ArrayList<T> getByClass(String className) {

        ArrayList<T> temp = new ArrayList<T>();

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
