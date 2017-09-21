package com.codecool.wot.interfaces;

import java.util.ArrayList;

public interface DAO <T> {
    public void addObject(T object);
    public void removeObject(T object);

    public void setObjectList(ArrayList<T> newObjectsList);

    public ArrayList<T> getObjectList();
}
