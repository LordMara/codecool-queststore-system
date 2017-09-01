package src.dao;

import java.util.ArrayList;
import java.util.Iterator;

import src.dao.DAOInterface;

public abstract class AbstractDAO<T> implements DAOInterface<T> {
    protected ArrayList<T> objectList;

    public ArrayList<T> getAllObjects() {
        return this.objectList;
    }

    public void addObject(T object) {
        this.objectList.add(object);
    }

    public boolean removeObject(Integer id) {
        Iterator iter = this.objectList.iterator();
        while(iter.hasNext()) {
            if(iter.next().getId.equals(id));
        }
        return true;
    }

}
