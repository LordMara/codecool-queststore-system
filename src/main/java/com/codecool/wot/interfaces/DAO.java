package com.codecool.wot.interfaces;

import java.util.LinkedList;

public interface DAO <T> {
    public void addObject(T object);
    public void removeObject(T object);
    public void setObjectList(LinkedList<T> newObjectsList);
    public LinkedList<T> getObjectList();
}
