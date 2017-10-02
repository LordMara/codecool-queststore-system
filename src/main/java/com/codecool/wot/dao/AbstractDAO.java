package com.codecool.wot.dao;

import com.codecool.wot.interfaces.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public abstract class AbstractDAO<T> implements DAO<T> {


    protected ArrayList<T> objectsList = new ArrayList<T>();
    protected Connection connection;


    public void load(String query, String ... args) throws SQLException {

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            T obj = createObject(args);
            objectsList.add(obj);
        }
    }

    public T createObject(String ... args) {

        for (String attr: args) {

            
        }
    }


    public void add(T object) {
        objectsList.add(object);
    }

    public void update(T object) {}

    public void remove(T object) {
        objectsList.remove(object);
    }

    public T getBy(String identifier) {}

    public void setObjectList(ArrayList<T> newObjectsList) {
        objectsList = newObjectsList;
    }

    public ArrayList<T> getObjectList() {
        return objectsList;
    }

    public void saveToDataBase(T object) {}


}
