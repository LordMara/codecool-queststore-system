package com.codecool.wot.dao;

import com.codecool.wot.interfaces.*;
import java.util.LinkedList;

public abstract class AbstractDAO<T> implements DAO<T> {

    protected LinkedList<T> objectsList = new LinkedList<T>();

    public void add(T object) {
        objectsList.add(object);
    }

    public void remove(T object) {
        objectsList.remove(object);
    }

    public void setObjectList(LinkedList<T> newObjectsList) {
        objectsList = newObjectsList;
    }

    public LinkedList<T> getObjectList() {
        return objectsList;
    }


}
