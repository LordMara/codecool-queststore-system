package com.codecool.wot.interfaces;

import java.util.ArrayList;

public interface DAO <T> {

    public void add(T object);

    public void remove(T object);

    public ArrayList<T> getObjectList();
}
