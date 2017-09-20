package src.dao;

import java.util.ArrayList;
import java.util.Iterator;

import src.dao.DAOInterface;

public abstract class AbstractDAO<T> implements DAOInterface<T> {
    protected ArrayList<T> objectList;

    public ArrayList<T> getAllObjects() {
        return this.objectList;
    }