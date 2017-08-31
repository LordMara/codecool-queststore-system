package src.dao;

import java.util.Scanner;
import java.nio.file.NoSuchFileException;

import src.dao.DAOInterface;

public abstract class AbstractDAO<T> implements DAOInterface<T> {
    private ArrayList<T> objectList;

    public abstract void load();

    public ArrayList<T> getAllObjects() {
        return this.objectList;
    }

    public void addObject(T object) {
        this.objectList.add(object);
    }

    public boolean removeObject(T object) {
        this.objectList.remove(object);
        return true;
    }

    public abstract void save();
}
