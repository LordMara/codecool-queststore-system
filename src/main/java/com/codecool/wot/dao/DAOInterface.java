package com.codecool.wot.dao;

import java.util.ArrayList;

public interface DAOInterface<T> {
    public ArrayList<T> getAllObjects();
    public void addObject(T object);
    public boolean removeObject(Integer id);
}