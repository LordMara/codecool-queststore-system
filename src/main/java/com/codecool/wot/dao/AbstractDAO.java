package com.codecool.wot.dao;

import com.codecool.wot.interfaces.*;

import java.util.ArrayList;

public abstract class AbstractDAO<T> implements DAO<T> {

    protected ArrayList<T> objectsList = new ArrayList<T>();

    public void add(T object) {
        objectsList.add(object);
    }

    public void remove(T object) {
        objectsList.remove(object);
    }

    public void setObjectList(ArrayList<T> newObjectsList) {
        objectsList = newObjectsList;
    }

    public ArrayList<T> getObjectList() {
        return objectsList;
    }


}
