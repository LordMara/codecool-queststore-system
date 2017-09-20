package com.codecool.wot.dao;
import com.codecool.wot.model.Account;

import java.util.LinkedList;

public abstract class AbstractCodecoolerDAO <T extends Account> extends AbstractDAO <T> {

    public LinkedList<T> getByClass(String className){

        LinkedList temp = new LinkedList();

        for (T elem : objectsList){
            if (elem.getSchoolClass().equals(className)){
                temp.add(elem);
            }
        }
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
